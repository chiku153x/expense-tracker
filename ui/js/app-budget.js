$(document).ready(function() { 
    loadBudgets();
});

$('#budgetBtn').click(function(){
    loadCategories();
});

$('#saveBudget').click(function(){
    if($('#xid').val() == ""){
        var url = base_url + "/budget/createBudget";
    }else{
        var url = base_url + "/budget/updateBudget";
    }
    var amount = $('#amount').val(); 
    var category = $('#category').val(); 
    var user = JSON.parse(localStorage.getItem('currentUser'))['id']; 
    var year = $('#year').val(); 
    var month = $('#month').val();
    var description = $('#description').val();
    if(amount != "" && category != "" && user != "" && year != "" && month != ""){
        data = {
            "amount": amount,
            "description":description,
            "category": category,
            "month": month,
            "year": year,
            "user":user
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
                loadBudgets();
                $('#toast-successful').toast('show');
                $("#budgetModal").modal('hide');
            },
            error: function(xhr, resp, text) {
                alert("Budget did not saved");
            }
        })
    }else{
        alert("Data Validation Error on budget creation");
    }
});

function loadBudgets(){
    
    var url = base_url + "/budget/getAllBudgets/"+JSON.parse(localStorage.getItem('currentUser'))['id'];
   
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
                row.push(getCategoryNameFromId(categories,o.category));
                row.push(o.month);
                row.push(o.year);
                row.push(o.amount);
                row.push("<i class='fas fa-pencil-alt me-color-orange poi obj-edit' onClick=editBudget('"+o.id+"')></i>&nbsp;&nbsp;<i class='fas fa-ban me-color-red poi obj-dele' onClick=deleteBudget('"+o.id+"')></i>");
                data.push(row);
            });
            $('#dataTable').DataTable({
                data:data,
                "bDestroy": true
            });
            

            $('#toast-successful').toast('show');
            $("#budgetModal").modal('hide');
        },
        error: function(xhr, resp, text) {
            alert("Budgets were not loaded");
        }
    });

}

$('#deleteBudget').click(function(){
    var url = base_url + "/budget/deleteBudget";
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
            loadBudgets();
            $('#toast-successful').toast('show');
            $("#budgetModalDelete").modal('hide');
        },
        error: function(xhr, resp, text) {
            alert("Transaction did not saved error");
        }
    });
});

function editBudget(id){
    loadBudgetToModal(id);
    $("#budgetModal").modal('show');
}


function deleteBudget(id){
    $('#yid').val(id);
    $("#budgetModalDelete").modal('show');
}


function loadBudgetToModal(id){
    var url = base_url + "/budget/getBudgetById/" + id;
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
            $('#xid').val(id);
            $('#description').val(result.description);
            $('#year').val(result.year);
            $('#month').val(result.month);
            $('#amount').val(result.amount);
            $("#category option[value=" + result.category.id + "]").attr('selected','selected');
        },
        error: function(xhr, resp, text) {
            alert("data could not be loaded to edit");
        }
    });
}
