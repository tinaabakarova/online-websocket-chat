$(document).ready(function() {
    var senderId = $.urlParam('from', window.location.href);
    var recipientId = $.urlParam('to', window.location.href);
    connect();
    $.get('/messages/' + senderId + '/' + recipientId).done(function (messages) {
        messages.forEach(function (message) {
            if (senderId == message.senderId) {
                draw("right", message.body);
            } else {
                draw("left", message.body);
            }
        });
    });
});
function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        var senderId = $.urlParam('from', window.location.href);
        var recipientId = $.urlParam('to', window.location.href);
        console.log("connected: " + frame);
        stompClient.subscribe("/user/" + senderId + "/" + recipientId + "/queue/messages", function(response) {
            var data = JSON.parse(response.body);
            draw("left", data.body);
        });
    });
}

function draw(side, text) {
    console.log("drawing...");
    var $message;
    $message = $($('.message_template').clone().html());
    $message.addClass(side).find('.text').html(text);
    $('.messages').append($message);
    return setTimeout(function () {
        return $message.addClass('appeared');
    }, 0);

}
function disconnect(){
    stompClient.disconnect();
}
function sendMessage(){
    var senderId = $.urlParam('from', window.location.href);
    var recipientId = $.urlParam('to', window.location.href);
    stompClient.send("/app/chat", {}, JSON.stringify({
        'senderId':senderId,
        'recipientId':recipientId,
        'body': $("#message_input_value").val(),
    })
    );
    draw("right", $("#message_input_value").val());
    $("#message_input_value").val('');

}
$(window).unload(function() {
    disconnect();
});