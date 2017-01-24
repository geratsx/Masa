/**
 * Deletes user by id
 * @param id
 */
function deleteUser(id) {
  $.ajax({
    url:     '/user/' + id,
    type:    'DELETE',
    success: function () {
      getUsers();
    },
    error:   function () {
      console.error("Unable to remove user");
    }
  });
}

/**
 * Refreshes users list
 */
function getUsers() {

  function createNewUserRow(user) {
    var userDocumentsLink = ["<a href = \"javascript:viewDocuments(", user.id, ")\">", user.name, "</a>"].join("");
    var deleteUserLink = ["<a href = \"javascript:deleteUser(", user.id, ")\">", "X", "</a>"].join("");

    return [
      "<li>",
      userDocumentsLink,
      deleteUserLink,
      "</li>"
    ].join("");
  }


  $.get("/user", function (users) {
    console.log(users);
    $("#usersList li").remove();
    for (var i = 0; i < users.length; i++) {
      var user = users[i];
      var userRow = createNewUserRow(user);
      $("#usersList").append(userRow);
    }
  });
}

/**
 * Adds new user
 */
function createUser() {
  var firstName = $("#firstName").val();
  $.post("/user",
      {"name": firstName},
      function () {
        $("#firstName").val("");
        getUsers();
      }
  );
}

$(document).ready(
    function () {

      getUsers();

      $("#addUserButton").click(createUser);
    }
);

/*
 Shortcut for the above:

 $(function() {
 });
 */