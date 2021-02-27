
function sessionLogin(sessionId) {
    let name = document.getElementById('name').value;
    let password = document.getElementById('password').value;

    r = fetch(`${baseUrl}/auth/`, { 
                method: 'POST',
                body: JSON.stringify({name: name, password: password})
            } ).then( () => { 
                initSession(sessionId);
            } );
}

function sessionLogout(sessionId) {
    r = fetch(`${baseUrl}/auth/?logout=true`, { 
                method: 'POST'
            } ).then( () => { 
                initSession(sessionId);
            } );
}
