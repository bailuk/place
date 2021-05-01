

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
        statusElement.innerHTML = `<div>signed in as ${status.login} <button class="btn btn-warning" onclick="_sessionSignout()">sign out</button></div>`;
    } else {
        statusElement.innerHTML = 
            `<div>
                <label for="sessionLoginId">login</label><input type="text" id="sessionLoginId">
                <label for="sessionPasswordId">passwort</label><input type="password" id="sessionPasswordId">
                <button class="btn btn-success" onclick="_sessionSignin()">sign in</button>
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


function _sessionSignin() {
    const login = document.getElementById('sessionLoginId').value;
    const password = document.getElementById('sessionPasswordId').value;

    fetch(`${baseUrl}/auth/`, {
        method: 'POST',
        body: JSON.stringify({ login: login, password: password })
    }).then(response => {
        if (!response.ok) {
            response.text().then(text => displayError(text)).catch(err => displayError(err));
        }
        sessionUpdateStatus();
    }).catch(err => displayError(err));
}

function _sessionSignout() {
    fetch(`${baseUrl}/auth/`, {
        method: 'DELETE'
    }).then(response => {
        if (!response.ok) {
            response.text().then(text => displayError(text)).catch(err => displayError(err));
        }
        sessionUpdateStatus();
    }).catch(err => displayError(err));
}
