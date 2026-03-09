const userList = document.getElementById("usersList");

function loadAllUsers() {
    fetch ('/users/all').then((response) => {
        return response.json();
    }).then(data => {
        data.forEach(user => {
            const userItemClick = document.createElement('a');
            userItemClick.href = `chat.html?userId=${user.id}`;
            const userItem = document.createElement('li');
            userItem.textContent = user.username;

            userItemClick.appendChild(userItem);
            if (user.id === parseInt(localStorage.getItem('userId'))) {
                userItem.textContent += " (You)";
            }
            userList.appendChild(userItemClick);
        });})
}

loadAllUsers();