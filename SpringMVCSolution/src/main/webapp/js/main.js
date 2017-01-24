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


function selectUser(userId) {
  if (userId) {

    // Saving userID into a hidden field
    // This is just to keep the example simple
    $("#userId").val(userId);

    loadDocuments(userId);
  }

}

function loadDocuments(userId) {
  function createDocumentElement(document) {
    return [
      "<li>",
      document.name,
      "<a href = \"\">",
      "X",
      "</a>",
      "</li>"
    ].join("");
  }


  $.get("/user/" + userId + "/documents",
      function documentsCallback(documents) {

        $("#documentsList li").remove();
        for (var i = 0; i < documents.length; i++) {
          $("#documentsList").append(createDocumentElement(documents[i]));
        }
        $("#documents").show();
      });
}

/**
 * Refreshes users list
 */
function getUsers() {

  function createNewUserRow(user) {
    var userDocumentsLink = ["<a href = \"javascript:selectUser(", user.id, ")\">", user.name, "</a>"].join("");
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

function addDocument() {


  var userId = $("#userId").val();
  var documentName = $("#documentName").val();
  $.post("/user/" + userId + "/documents", {
    "name": documentName
  }, function () {
    loadDocuments(userId)
  });
}

$(document).ready(
    function () {

      getUsers();

      $("#addUserButton").click(createUser);
      $("#addDocumentButton").click(addDocument);

      $("#documents").hide();
    }
);

/*
 Shortcut for the above:

 $(function() {
 });
 */