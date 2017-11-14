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
