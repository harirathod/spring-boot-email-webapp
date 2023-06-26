// Can probably make xhp a global variable, so we don't need to recreate it for every button click. And then we only call xhp.send() in btn.addEventListener().

function getRecentEmails(callback) {
    var xhp = new XMLHttpRequest();
    xhp.open("GET", "/data", true);
    xhp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var response = JSON.parse(this.responseText);
            callback(response);
        }
    }
    xhp.send();
}

function displayInDocument(arrayOfEmails, nameOfHtmlContainer) {
    var container = document.querySelector(nameOfHtmlContainer);
    for (i = 0; i < arrayOfEmails.length; i++) {
        var email = document.createElement('div');
        email.textContent = `Sender: ${arrayOfEmails[i].sender}, Recipient: ${arrayOfEmails[i].recipient},\n Content: ${arrayOfEmails[i].content}`;
        container.appendChild(email);
        console.log(container);
        console.log(email);
    }
}


var btn = document.querySelector('.view-recent-btn');
btn.addEventListener('click', (e) => {
    getRecentEmails(function(data) {
        displayInDocument(data, ".recent-emails-ctnr")
    });
});