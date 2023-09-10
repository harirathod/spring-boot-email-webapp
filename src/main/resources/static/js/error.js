var url = document.querySelector('.error-url > a');
if (url === null) {
    var urlContainer = document.querySelector(".error-url");
    var newUrl = document.createElement('a');
    newUrl.text = window.location.href;
    newUrl.href = window.location.href;
    urlContainer.appendChild(newUrl);
}