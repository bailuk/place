
function getFormattedTime() {
    const date = new Date();
    return date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
}


function clearErrors() {
    document.getElementById('errorId').innerHTML = '';
}


function displayError(err) {

    let html = document.getElementById('errorId').innerHTML;

    if (html == '') {
        html = `<button onclick="clearErrors()">clear</button>`;
    }

    const time = getFormattedTime();

    html = `${html}<pre>${time} ${err}</pre>`;

    document.getElementById('errorId').innerHTML = html;
}
