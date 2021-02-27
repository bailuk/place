function getFormattedTime() {
    const d = new Date();
    return ('0' + d.getHours()).slice(-2) + ":" + ('0' + d.getMinutes()).slice(-2) + ":" + ('0' + d.getSeconds()).slice(-2);
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
