const registerBtn = document.getElementById('login-btn');
const usernameInput = document.getElementById('username');
const passwordInput = document.getElementById('password');

registerBtn.addEventListener('click', () => {
    const username = usernameInput.value;
    const password = passwordInput.value;

    fetch(`/auth/login?username=${username}&password=${password}`, {
        method: 'POST',
    })
        .then(response => response.json())
        .then(data => {
            if (data.id) {
                localStorage.setItem('userId', data.id);
                localStorage.setItem('username', data.username);
                window.location.href = 'friends.html';
            } else {
                alert('Login failed: ' + data.message);
            }
        })
})