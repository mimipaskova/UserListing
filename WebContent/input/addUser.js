$(document).ready(function() {
    "use strict";

    $("#addUserButton").on("click", addUser);
});

var createUser = function() {
	var userID = $("#ID").val(),
		userName = $("#name").val(),
		userAddress = $("#address").val(),
		userTelNo = $("#tel").val();

	var user = {
		userID: userID,
		userName: userName,
		userAddress: userAddress,
		userTelNo: userTelNo
	}

	return user;
}

var addUser = function(){
	var user = createUser();

	$.ajax({
		url: "http://localhost:8080/UserListing/rest/users/",
        type: "POST",
        data: user
   	}).done(function(data) {
		console.log(data);
	});

}

