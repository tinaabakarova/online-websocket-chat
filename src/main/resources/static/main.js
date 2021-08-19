    $(document).ready(function() {
        $.get('/api/chat/all').done(function (chats) {
            chats.forEach(function (chat) {
                $('tbody').append(`
                <tr>
                        <td>
                            <a href="/user?to=${chat.recipientId}&from=${chat.senderId}" id="user">${chat.recipientName}</a>
                        </td>
                        <td>
                            <a>Last message</a>
                        </td>
                        <td>
                            <a href="/delete?id=${chat.recipientId}" id="delete">Delete</a>
                        </td>
                 </tr>
                `)
            });
        });
        $(document).delegate('#delete', 'click', function() {
        	var href =$(this).attr('href');
            var recipientId = $.urlParam('id', href);
            $.ajax({
                url: '/api/chat?id=' + recipientId,
                method: 'DELETE',
                contentType: 'application/json',
                success: function(result) {
                    window.location = "/";
                }
            });
        });
        $(document).delegate('#user', 'click', function(event) {
            event.preventDefault();
            var href =$(this).attr('href');
            var recipientId = $.urlParam('to', href);
            var senderId = $.urlParam('from', href);

            window.location = "/user?to=" + recipientId + '&from=' + senderId;

        });

    });
