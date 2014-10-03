var table;

$(document).ready(function() {
    clickButtonTable();
    clickButtonUser();
    clearInput();
});

var createTable = function(numberOfRows) {

    table = document.createElement('div');
    table.className = "entireTable";
    $(".entireTable").empty(); //Clear the old table

    //Header
    var header = document.createElement('div');
    header.className = "table header";
    createRows(listHeaders, header, true);
    //Body of Table

    for (var j = 0; j < numberOfRows; j++) {
        var rows = document.createElement('div');
        rows.className = "table row";
        rows.id = "r" + j;
        if(numberOfRows === 1){
            createRows(listHeaders, rows, false, list);
        }
        else{
        createRows(listHeaders, rows, false, list[j]);
        // table.appendChild(rows);
        }
    }
    document.body.appendChild(table);
};

var createRows = function(cellContent, body, isHeader, myRow) {
    for (var i = 0; i < cellContent.length; i++) {
        var column = document.createElement('div');
        // column.onclick = function(){
        //     alert("lqlqq");
        // };
        var textName =
            (isHeader === true) ? document.createTextNode(cellContent[i]) :
            document.createTextNode(myRow[cellContent[i]]);
        column.appendChild(textName);

        body.appendChild(column);
        table.appendChild(body);
    }
};

var clearInput = function(){
    $(".numberID").on("click", function(){
        $(".numberID").val('');
    });
};
