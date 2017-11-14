// Executes on pressing the register button.
// Sends a POST request to the sign-up endpoint.
// In case the request is successful, prompts the user to register with the credentials.
const regprop = function (event) {

    // Same logic as signing in.
    if (!event || (event && event.keyCode === 13)) {

        const type = document.getElementById('propType').value;
        const address = document.getElementById('address').value;
        const description = document.getElementById('description').value;
        const sale = document.getElementById('sale').value;
        const rent = document.getElementById('rent').value;
        const noRooms = document.getElementById('roomsNumber').value;
        const noBedrooms = document.getElementById('bedroomsNumber').value;
        const noBathrooms = document.getElementById('bathroomsNumber').value;
        const price = document.getElementById('price').value;
        const rentPerMonth = document.getElementById('rentPerMonth').value;


        const data = new FormData();
        data.append('type', type);
        data.append('address', address);
        data.append('description', description);
        data.append('forSale', sale);
        data.append('forRent', rent);
        data.append('numberOfRooms', noRooms);
        data.append('numberOfBedrooms', noBedrooms);
        data.append('numberOfBathrooms', noBathrooms);
        data.append('price', price);
        data.append('rentPerMonth', rentPerMonth);

        xhrRequest('POST', '/prop/addProperty', data).then(response => handleRegisterResponse(response), error => console.trace(error));
    }
};
