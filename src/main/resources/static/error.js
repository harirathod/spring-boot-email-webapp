var x = document.querySelector('.error-url > a');
if (x === null) {
    var div = document.querySelector(".error-url");
    var newNode = document.createElement('a');
    newNode.text = window.location.href;
    newNode.href = window.location.href;
    div.appendChild(newNode);
}