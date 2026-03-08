const registerBtn = document.getElementById('register-btn');
const usernameInput = document.getElementById('username');
const passwordInput = document.getElementById('password');

registerBtn.addEventListener('click', () => {
    const username = usernameInput.value;
    const password = passwordInput.value;

    fetch(`/auth/register?username=${username}&password=${password}`, {
        method: 'POST',
    })
    .then(response => response.json())
    .then(data => {
        if (data.id) {
            window.location.href = 'login.html';
        } else {
            alert('Registration failed: ' + data.message);
        }
    })
})