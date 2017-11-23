// Executes on pressing the enter key in the input fields or pressing the sign-in button.
const login = function(event) {

    // If the sign-in button is clicked the event will be undefined otherwise 
    // 'event' contains the context of the input element which we should check for the pressed key.
    // Enter has a keycode of 13.
    if (!event || (event && event.keyCode === 13)) {

        const username = document.getElementById('inputUserForm').value;
        const password = document.getElementById('inputPasswordForm').value;

        const data = new FormData();
        data.append('username', username);
        data.append('password', password);

        xhrLoginRequest('POST', '/log', data).then(response => handleLoginResponse(response), error => console.trace(error));
    }
};

// Response should be boolean.
// On successful login, the user gets redirected to the root page.
const handleLoginResponse = function(response) {
    if (response) {
        window.location = '/prop';
    } else {
        showWarningDiv('inputPasswordForm');
    }
};

const showWarningDiv = function(inputElementId, message) {
    const inputElement = document.getElementById(inputElementId);
    const rowDiv = inputElement.parentElement.parentElement;
    const feedbackDiv = rowDiv.children[1].children[1];
    rowDiv.classList.add('has-danger');
    inputElement.classList.add('form-control-danger');
    if (message) {
        feedbackDiv.innerHTML = message;
    }
    feedbackDiv.style.display = 'block';
};

// Makes asynchronous calls to the login API using the supplied arguments.
const xhrLoginRequest = function(method, endpoint, data) {
    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest();

        xhr.overrideMimeType('application/json');
        xhr.open(method, endpoint, true);
        xhr.onload = () => {
            if (xhr.responseURL && xhr.responseURL.indexOf('error') !== -1) {
                resolve(false);
            } else {
                resolve(true);
            }
        };
        xhr.onerror = () => reject(xhr.statusText);
        xhr.send(data);
    });
};