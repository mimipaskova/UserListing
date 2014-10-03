var list, listHeaders;
$(document).ready(function() {
    "use strict";
});

var clickButtonTable = function() {
    $(".createTable").on("click", function() {

        $.ajax({
            url: "http://localhost:8080/UserListing/rest/users/",
            type: "GET",
            // dataType: "json",
            // data: data
        }).done(function(data) {
            console.log(data);
            list = data;
            listHeaders = Object.keys(list[0]);
            console.log(listHeaders);
            createTable(list.length);
        }).fail(function(e) {

        }).error(function() {});
    });
};


var clickButtonUser = function() {
    $(".printUser").on("click", function() {
        var i = $(".numberID").val();
        $.ajax({
            url: "http://localhost:8080/UserListing/rest/users/" + i,
            type: "GET",
        }).done(function(data) {
            list = data;
            if(data === null){
                alert("The ID isn`t correct. Please enter new ID.");
                $(".numberID").val('');
                $(".entireTable").empty();
            }
            else{
                listHeaders = Object.keys(list);
                createTable(1);
            }
        }).fail(function(e) {

        }).error(function() {});
    });
};
