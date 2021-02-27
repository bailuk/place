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


function getSaveMarker(index) {
    index = parseInt(index);
    if ( isNaN(index) || index  < 0 || index >= markers.length) {
        index=0;
    }
    return index;
}