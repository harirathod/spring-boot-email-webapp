// Can probably make xhp a global variable, so we don't need to recreate it for every button click. And then we only call xhp.send() in btn.addEventListener().

function getRecentEmails(callbackFunction) {
    var xhp = new XMLHttpRequest();
    // Set the xhp to be a GET request of email data.
    xhp.open("GET", "/sendEmail/data", true);
    xhp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var response = JSON.parse(this.responseText);
            // When the email data is correctly returned, do something with it (using a passed function-parameter).
            callbackFunction(response);
        }
    }
    xhp.send();
}

function displayInDocument(arrayOfEmails, nameOfHtmlContainer) {
    // This function takes an array of emails and inserts the emails into a provided container.
    var container = document.querySelector(nameOfHtmlContainer);
    container.replaceChildren();
    if (arrayOfEmails.length == 0) {
        var none = document.createElement('div');
        none.textContent = `No emails to display.`
        container.appendChild(none);
        return;
    }
    for (i = 0; i < arrayOfEmails.length; i++) {
        var email = document.createElement('div');
        email.className = "email-ctnr";

        var container1 = document.createElement('div');
        container1.className = "sender-recipient-ctnr";
        var from = document.createElement('div');
        var arrow = document.createElement('span');
        var to = document.createElement('div');

        from.textContent = `${arrayOfEmails[i].sender}`;
        to.textContent = `${arrayOfEmails[i].recipient}`;
        arrow.textContent = "==>"
        container1.replaceChildren(from, arrow, to);

        var container2 = document.createElement('div');

        container2.classList.add("fill");
        container2.classList.add("content-ctnr");
        container2.textContent = `"${arrayOfEmails[i].content}"`;

        var container3 = document.createElement('div');
        container3.className = "timestamp-ctnr";
        container3.textContent = `${arrayOfEmails[i].timestamp}`;

        email.replaceChildren(container1, container2, container3);
        container.appendChild(email);
    }
}

var btn = document.querySelector('.view-recent-btn');
btn.addEventListener('click', (e) => {
    // The getRecentEmails(lambda) function takes a function as a parameter, and inserts the 'data' into that function.
    getRecentEmails((data) => displayInDocument(data, ".recent-emails-ctnr"));
});