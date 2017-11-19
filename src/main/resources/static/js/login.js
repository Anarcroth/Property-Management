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

// Executes on pressing the register button.
// Sends a POST request to the sign-up endpoint.
// In case the request is successful, prompts the user to register with the credentials.
const register = function(event) {

    // Same logic as signing in.
    if (!event || (event && event.keyCode === 13)) {

        const username = document.getElementById('input2UserForm').value;
        const fullName = document.getElementById('input2User2Form').value;
        const address = document.getElementById('input2User3Form').value;
        const email = document.getElementById('email').value;
        const password = document.getElementById('input2PasswordForm').value;
        const verificationPassword = document.getElementById('input2Password2Form').value;

        if (password === verificationPassword && verificationPassword.length < 5) {
            showWarningDiv('input2Password2Form', 'The password should be 5 or more characters. Try again?');

        } else if (password !== verificationPassword) {
            showWarningDiv('input2Password2Form', 'These passwords don\'t match. Try again?');

        } else {
            hideWarningDiv('input2Password2Form');

            const data = new FormData();
            data.append('username', username);
            data.append('fullName', fullName);
            data.append('address', address);
            data.append('email', email);
            data.append('password', password);

            xhrRequest('POST', '/log/sign_up', data).then(response => handleRegisterResponse(response), error => console.trace(error));
        }
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

// Response should be boolean.
const handleRegisterResponse = function(response) {
    if (response.success) {
        hideWarningDiv('input2UserForm');

        $('#login-btn').click();

        const sectionDiv = document.getElementById('success-alert');
        sectionDiv.style.display = 'block';
    } else if (response.error) {
        showWarningDiv('input2UserForm', response.error);
    } else {
        showWarningDiv('input2UserForm');
    }

};

const showWarningDiv = function(inputElementId, message) {
    const intputElement = document.getElementById(inputElementId);
    const rowDiv = intputElement.parentElement.parentElement;
    const feedbackDiv = rowDiv.children[1].children[1];
    rowDiv.classList.add('has-danger');
    intputElement.classList.add('form-control-danger');
    if (message) {
        feedbackDiv.innerHTML = message;
    }
    feedbackDiv.style.display = 'block';
};

const hideWarningDiv = function(inputElementId) {
    const intputElement = document.getElementById(inputElementId);
    const rowDiv = intputElement.parentElement.parentElement;
    const feedbackDiv = rowDiv.children[1].children[1];
    rowDiv.classList.remove('has-danger');
    intputElement.classList.remove('form-control-danger');
    feedbackDiv.style.display = 'none';
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