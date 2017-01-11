/**
 * Deletes user by id
 * @param id
 */
function deleteUser(id) {
    $.ajax({
        url: '/user/'+id,
        type: 'DELETE',
        success: function() {
            getUsers();
        },
        error: function() {
            console.error("Unable to remove user");
        }
    });
}

/**
 * Refreshes users list
 */
function getUsers() {
    $.get("/user", function(users) {
        console.log(users);
        $("#usersList li").remove();
        for (var i = 0; i < users.length; i++) {
            $("#usersList").append("<li>" + users[i].name +
                "<a href = \"javascript:deleteUser(\'"+users[i].id+"\')\">X</a></li>");
        }
    });
}

/**
 * Adds new user
 */
function createUser() {
    var firstName = $("#firstName").val();
    $.post("/user",
        {"name":firstName},
        function() {
            $("#firstName").val("");
            getUsers();
        }
    );
}

$(document).ready(
    function() {

        getUsers();

        $("#addUserButton").click(createUser);
    }
);