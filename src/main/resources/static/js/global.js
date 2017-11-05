// Makes asynchronous calls to an API using the supplied arguments.
const xhrRequest = function(method, endpoint, data) {
    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest();

        xhr.overrideMimeType('application/json');
        xhr.open(method, endpoint, true);
        xhr.onload = () => {
            if (xhr.responseText !== null && xhr.responseText !== '') {
                resolve(JSON.parse(xhr.responseText));
            } else {
                resolve(xhr.responseText);
            }
        };
        xhr.onerror = () => reject(xhr.statusText);
        if (!data) {
            xhr.send();
        } else {
            xhr.send(data);
        }
    });
};

// Returns a Promise with the user object.
const getUser = function() {
    return xhrRequest('GET', '/usr').then(user => user
    , err => console.trace(err.message));

};


// Response on pressing the 'Enter' key in the search box or pressing the search button.
// 'event' contains the context of the search input element or the search button.
// Reloads the page.
// It does not take into account the filter so it clears it for now.
const searchFor = function(event) {
    let searchPhrase = null;
    // Handle a click on the button.
    if (event.nodeName = 'BUTTON') {
        event.keyCode = 13;
        searchPhrase = document.getElementById('search-box').value;
    } else {
        searchPhrase = event.target.value;
    }
    // The 'Enter' key has a code of 13.
    if (event.keyCode == 13 && searchPhrase != null && searchPhrase != '') {
        window.location = './index.html?searchFor=' + searchPhrase;
    }
};