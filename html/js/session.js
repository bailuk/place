

let sessionElementId;
let sessionStartSession;


function initSession(elementId, startSession) {
    sessionElementId = elementId;
    sessionStartSession = startSession;

    sessionUpdateStatus();

}

function sessionUpdateStatus() {
    r = fetch(`${baseUrl}/auth/`).then(response => response.json()).then(json => {
        const statusElement = document.getElementById(sessionElementId);
        
        if (json.auth) {
            statusElement.innerHTML = `<div>signed in as ${json.name} <button onclick="sessionLogout()">sign out</button></div>`;

        } else {
            statusElement.innerHTML = 
                        `<div>
                            <label for="name">login</label><input type="text" name="name" id="sessionLoginId">
                            <label for="password">passwort</label><input type="password" name="password" id="sessionPasswordId">
                            <button onclick="sessionLogin()">sign in</button>
                        </div>`;
        }
        
        sessionStartSession(json);
    });
}


function sessionLogin() {
    const name = document.getElementById('sessionLoginId').value;
    const password = document.getElementById('sessionPasswordId').value;

    r = fetch(`${baseUrl}/auth/`, {
        method: 'POST',
        body: JSON.stringify({ name: name, password: password })
    }).then(() => {
        sessionUpdateStatus();
    });
}

function sessionLogout() {
    r = fetch(`${baseUrl}/auth/?logout=true`, {
        method: 'POST'
    }).then(() => {
        sessionUpdateStatus();
    });
}
