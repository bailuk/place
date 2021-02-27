

let sessionElementId;
let sessionStartSession;


function initSession(elementId, startSession) {
    sessionElementId = elementId;
    sessionStartSession = startSession;

    sessionUpdateStatus();

}

function _sessionUpdateStatus(status) {
    const statusElement = document.getElementById(sessionElementId);
        
    if (status.auth) {
        statusElement.innerHTML = `<div>signed in as ${status.name} <button onclick="sessionLogout()">sign out</button></div>`;
    } else {
        statusElement.innerHTML = 
            `<div>
                <label for="name">login</label><input type="text" name="name" id="sessionLoginId">
                <label for="password">passwort</label><input type="password" name="password" id="sessionPasswordId">
                <button onclick="sessionLogin()">sign in</button>
            </div>`;
    }
    sessionStartSession(status);
}


function sessionUpdateStatus() {
    fetch(`${baseUrl}/auth/`).then(response => {
        if (response.ok) {
            response.json().then(json =>_sessionUpdateStatus(json)).catch(err => displayError(err));
        } else {
            response.text().then(text => displayError(text)).catch(err => displayError(err));
        }
    }).catch(err => displayError(err));
}


function sessionLogin() {
    const name = document.getElementById('sessionLoginId').value;
    const password = document.getElementById('sessionPasswordId').value;

    fetch(`${baseUrl}/auth/`, {
        method: 'POST',
        body: JSON.stringify({ name: name, password: password })
    }).then(response => {
        if (!response.ok) {
            response.text().then(text => displayError(text)).catch(err => displayError(err));
        }
        sessionUpdateStatus();
    }).catch(err => displayError(err));
}

function sessionLogout() {
    fetch(`${baseUrl}/auth/?logout=true`, {
        method: 'POST'
    }).then(response => {
        if (!response.ok) {
            response.text().then(text => displayError(text)).catch(err => displayError(err));
        }
        sessionUpdateStatus();
    }).catch(err => displayError(err));
}
