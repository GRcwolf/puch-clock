$(document).ready(() => {
    if (!$.cookie('authToken')) {
        window.location.href = 'index.html';
    }
    getUsers();

    $('#logout').on('click', evt => {
        evt.preventDefault();
        $.cookie('authToken', '', { expires: -1 });
    });
});

function getUsers() {
    const tableBody = $('tbody');
    $.ajax({
        url: "http://localhost:8081/users/",
        context: document.body,
        method: 'GET',
        dataType: 'json',
        headers: {
            Authorization: $.cookie('authToken'),
        },
    }).done(response => {
        tableBody.empty();
        for (let i = 0; i < response.length; i += 1) {
            tableBody.append(
                `<tr>
                  <td>${response[i].id}</td>
                  <td>${response[i].username}</td>
                  <td>
                    <button class="waves-effect waves-light btn" onclick="deleteUser(${response[i].id});">Delete</button>
                  </td>
                </tr>`
            );
        }
    });
}

function deleteUser(id) {
    $.ajax({
        url: `http://localhost:8081/users/${id}`,
        method: 'DELETE',
        headers: {
            'Authorization': $.cookie('authToken'),
        },
        contentType: "application/json; charset=utf-8",
    }).done(() => {
        tableBody.empty();
        M.toast({ html: 'User has been deleted' });
        getData();
    });
}