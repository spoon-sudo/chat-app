const params = new URLSearchParams(window.location.search);
const recipientId = params.get('userId');
const socket = new SockJS('/ws');
const stompClient = Stomp.over(socket);
const messagesList = document.getElementById('messagesList');
const messageInput = document.getElementById('messageInput');
const sendBtn = document.getElementById('sendBtn');


function loadMessages() {
    fetch(`/messages?senderId=${localStorage.getItem('userId')}&recipientId=${recipientId}`).then((response) => {
        return response.json();
    }).then(data => {
        data.forEach(message => {
            const messageItem = document.createElement('li');
            messageItem.textContent = `${message.senderID}: ${message.messageContent}`;
            messagesList.appendChild(messageItem);
        });
    })
}

stompClient.heartbeat.outgoing = 0;
stompClient.heartbeat.incoming = 0;

stompClient.connect({ userId: localStorage.getItem('userId') }, function() {
    console.log("STOMP connected!");
    stompClient.subscribe(`/user/${localStorage.getItem('userId')}/queue/messages`, function(message) {
        
        const body = JSON.parse(message.body);
        const messageItem = document.createElement('li');
        messageItem.textContent = `${body.senderId}: ${body.messageContent}`;
        messagesList.appendChild(messageItem);
    });
}, function(error) {
    console.error("STOMP error:", error);
});


loadMessages();


function sendMessage(messageContent) {
    const message = {
        senderId: parseInt(localStorage.getItem('userId')),
        recipientId: parseInt(recipientId),
        messageContent: messageContent
    };
    stompClient.send("/app/chat", {}, JSON.stringify(message));
    messageInput.value = '';
}

sendBtn.addEventListener('click', () => {
    const messageContent = messageInput.value;
    if (messageContent.trim() !== '') {
        sendMessage(messageContent);
    }
});