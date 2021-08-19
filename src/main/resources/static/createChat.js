$(document).delegate('#search', 'click', function (event) {
    event.preventDefault();

    var nick = $('#nickname-input').val();

    $.ajax({
        type: "get",
        url: "/api/users/all?nickname=" + nick,
        success: function (users) {
            users.forEach(function (user) {
                $('#users').append(`
                                <div class="row">
                                      <label for="nickname-ref">${user.username}:</label>
                                      <button id="nickname-ref" onclick="createChatByRecipientId(${user.id});">${user.nickname}</button>
                                </div>
                            `)
            });

        }
    });

});

function createChatByRecipientId(recipientId) {
    event.preventDefault();

    $.ajax({
        type: "POST",
        url: "/api/chat?id=" + recipientId,
        contentType: "application/json",
        cache: false,
        success: function (result) {
            window.location = "/";
        }
    });
}

