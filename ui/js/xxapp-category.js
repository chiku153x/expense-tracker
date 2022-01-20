$(document).ready(function() { 
    $('#login-error').hide();
    //$('#dataTable').DataTable();
    loadTransactions();
    loadCategories();
 });




$('#transactionBtn').click(function(){
    loadCategories();
});

$('#login-button').click(function(){
    var url = "http://localhost:8080/expense-tracker/api/user/login";
    console.log("login clickedccgggsss");
    var un = $('#username').val(); 
    var pw = $('#password').val(); 
    if(un != "" && pw != ""){
        data = {
            "password": pw,
            "userName": un
        }
        $.ajax({
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: url, 
            type : "POST", 
            dataType : 'json',
            data : JSON.stringify(data), 
            success : function(result) {
                console.log(result);
                localStorage.setItem('currentUser', JSON.stringify(result));
                window.location.href = "file:///Users/gchinthaka/Desktop/expense-tracker-ui/index.html";
            },
            error: function(xhr, resp, text) {
                $('#login-error span').text(xhr.responseJSON.Message);
                $('#login-error').show();
            }
        })
    }else{
        alert("Data Error");
    }
    
});


$('#saveTransaction').click(function(){
    if($('#xid').val() == ""){
        var url = "http://localhost:8080/expense-tracker/api/transaction/createTransaction";
    }else{
        var url = "http://localhost:8080/expense-tracker/api/transaction/updateTransaction";
    }
    console.log("login clickedccgggsss");
    var amount = $('#amount').val(); 
    var category = $('#category').val(); 
    var user = JSON.parse(localStorage.getItem('currentUser'))['id']; 
    var transactionDate = $('#date').val(); 
    var note = $('#note').val();
    var description = $('#description').val();
    if(amount != "" && category != "" && user != "" && transactionDate != ""){
        data = {
            "amount": amount,
            "description":description,
            "category": category,
            "isIncome": $('#isIncome').is(':checked'),
            "isRecurrent": $('#isRecurrent').is(':checked'),
            "note": note,
            "transactionDate": transactionDate,
            "user": user
          }
        if($('#xid').val() != ""){
            data.id = $('#xid').val() ;
        }
        $.ajax({
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' ,
                "X-Auth-Token": JSON.parse(localStorage.getItem('currentUser'))['token']
            },
            url: url, 
            type : "POST", 
            dataType : 'json',
            data : JSON.stringify(data), 
            success : function(result) {
                console.log(result);
                loadTransactions();
                $('#toast-successful').toast('show');
                $("#transactionModal").modal('hide');
            },
            error: function(xhr, resp, text) {
                alert("Transaction did not saved error");
            }
        })
    }else{
        alert("Data Error xxx");
    }
});




function loadTransactions(){
    
    var url = "http://localhost:8080/expense-tracker/api/transaction/getAllTransactions/"+JSON.parse(localStorage.getItem('currentUser'))['id'];
   
    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' ,
            "X-Auth-Token": JSON.parse(localStorage.getItem('currentUser'))['token']
        },
        url: url, 
        type : "GET", 
        dataType : 'json',
        success : function(result) {
            console.log(result);
            var data = [];
            var categories = localStorage.getItem('categories');
            console.log(categories);
            $.each(result, function (i, o) {
                
                var row = [];
                row.push(o.id);
                row.push(o.description);
                row.push(getDateFromTimestamp(o.transactionDate));
                row.push(getCategoryNameFromId(categories,o.category));
                row.push(getYesNofromBool(o.isRecurrent));
                row.push(o.note);
                row.push(o.amount);
                row.push(getYesNofromBool(o.isIncome));
                row.push("<i class='fas fa-pencil-alt me-color-orange poi obj-edit' onClick=editTransaction('"+o.id+"')></i>&nbsp;&nbsp;<i class='fas fa-ban me-color-red poi obj-dele' onClick=deleteTransaction('"+o.id+"')></i>");
                data.push(row);
            });
            $('#dataTable').DataTable({
                data:data,
                "bDestroy": true
            });
            

            $('#toast-successful').toast('show');
            $("#transactionModal").modal('hide');
        },
        error: function(xhr, resp, text) {
            alert("Transaction were not loaded");
        }
    });

}

$('#deleteTransaction').click(function(){
    var url = "http://localhost:8080/expense-tracker/api/transaction/deleteTransaction";
    data = {
        "id": $('#yid').val()
    }
    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' ,
            "X-Auth-Token": JSON.parse(localStorage.getItem('currentUser'))['token']
        },
        url: url, 
        type : "POST", 
        dataType : 'json',
        data : JSON.stringify(data), 
        success : function(result) {
            console.log(result);
            loadTransactions();
            $('#toast-successful').toast('show');
            $("#transactionModalDelete").modal('hide');
        },
        error: function(xhr, resp, text) {
            alert("Transaction did not saved error");
        }
    });
});

function editTransaction(id){
    loadTransitionToModal(id);
    $("#transactionModal").modal('show');
}


function deleteTransaction(id){
    $('#yid').val(id);
    $("#transactionModalDelete").modal('show');
}


function loadTransitionToModal(id){
    var url = "http://localhost:8080/expense-tracker/api/transaction/getTransactionById/" + id;
    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            "X-Auth-Token": JSON.parse(localStorage.getItem('currentUser'))['token']
        },
        url: url, 
        type : "GET", 
        dataType : 'json',
        success : function(result) {
            console.log(result);
            //var data = JSON.parse(result);
            $('#xid').val(id);
            $('#description').val(result.description);
            $('#date').val(getDateFromTimestamp(result.transactionDate));
            $('#note').val(result.note);
            $('#amount').val(result.amount);
            if(result.isRecurrent){
                $('#isRecurrent').prop('checked',true);
            }else{
                $('#isRecurrent').prop('checked',false);
            }
            if(result.isIncome){
                $('#isIncome').prop('checked',true);
            }else{
                $('#isIncome').prop('checked',false);
            }

            $("#category option[value=" + result.category.id + "]").attr('selected','selected');
        },
        error: function(xhr, resp, text) {
            alert("data could not be loaded to edit");
        }
    });
}





function loadCategories(){
    var url = "http://localhost:8080/expense-tracker/api/category/getAllCategories/" + JSON.parse(localStorage.getItem('currentUser'))['id'];
    console.log("loading categories");
    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' , 
            "X-Auth-Token": JSON.parse(localStorage.getItem('currentUser'))['token']
        },
        url: url, 
        type : "GET", 
        dataType : 'json',
        success : function(result) {
            console.log(result);
            localStorage.setItem("categories",JSON.stringify(result));
            var category = $('#category').empty();
            category.append($("<option />").val(0).text("Select Category"));
            $.each(result,function(k,v){
                category.append($("<option />").val(v['id']).text(v['name']));
            });
        },
        error: function(xhr, resp, text) {
           alert("categories did not loaded");
        }
    }); 
}



function getDateFromTimestamp(timestamp){
    var d = new Date(timestamp);
    return d.getDate()  + "/" + (d.getMonth()+1) + "/" + d.getFullYear() 
}

function getCategoryNameFromId(categories,id){
    var name = "";
    $.each(JSON.parse(categories), function(k,v){
        if(v.id == id){
            name = v.name;
            return v.name;
        }
    });
    return name;
}

function getYesNofromBool(bool){
    if(bool){
        return "Yes";
    }else{
        return "No";
    }
}


