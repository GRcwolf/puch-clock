$(document).ready(() => {
    if ($.cookie('authToken')) {
        window.location.href = 'entries.html';
    }
    $('#login-form').on('submit', (evt) => {
        evt.preventDefault();
        const username = $('#username').val();
        const password = $('#password').val();

        $.ajax({
            url: "http://localhost:8081/login",
            context: document.body,
            method: 'POST',
            data: JSON.stringify({
               username,
               password
            }),
            contentType: 'application/json; charset=utf-8',
            success: function(data) {
                console.log(data);
                $.cookie('authToken', data, { expires: 14, path: '/' });
                window.location.href = 'entries.html';
            },
            error: function (a, b, c) {
                console.log({a, b, c})
            }
        })
    });
});