$(document).ready(function() { 
    loadCategories();
});

$('#categoryBtn').click(function(){
    loadCategories();
});

$('#saveCategory').click(function(){
    if($('#xid').val() == ""){
        var url = base_url + "/category/createCategory";
    }else{
        var url = base_url + "/category/updateCategory";
    }
    var user = JSON.parse(localStorage.getItem('currentUser'))['id']; 
    var name = $('#name').val();
    var description = $('#description').val();
    if(name != "" && description != "" && user != "" ){
        data = {
            "description":description,
            "userId": user,
            "name": name
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
                loadCategories();
                $('#toast-successful').toast('show');
                $("#categoryModal").modal('hide');
            },
            error: function(xhr, resp, text) {
                alert("Category did not saved");
            }
        })
    }else{
        alert("Data Error xxx");
    }
});

function loadCategories(){
    
    var url = base_url + "/category/getAllCategories/"+JSON.parse(localStorage.getItem('currentUser'))['id'];
   
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
                row.push(o.name);
                row.push(o.description);
                row.push("<i class='fas fa-pencil-alt me-color-orange poi obj-edit' onClick=editCategory('"+o.id+"')></i>&nbsp;&nbsp;<i class='fas fa-ban me-color-red poi obj-dele' onClick=deleteCategory('"+o.id+"')></i>");
                data.push(row);
            });
            $('#dataTable').DataTable({
                data:data,
                "bDestroy": true
            });
            

            $('#toast-successful').toast('show');
            $("#categoryModal").modal('hide');
        },
        error: function(xhr, resp, text) {
            alert("Categories were not loaded");
        }
    });

}

$('#deleteCategory').click(function(){
    var url = base_url + "/category/deleteCategory";
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
            loadCategories();
            $('#toast-successful').toast('show');
            $("#categoryModalDelete").modal('hide');
        },
        error: function(xhr, resp, text) {
            alert("Category did not saved");
        }
    });
});

function editCategory(id){
    loadCategoryToModal(id);
    $("#categoryModal").modal('show');
}


function deleteCategory(id){
    $('#yid').val(id);
    $("#categoryModalDelete").modal('show');
}


function loadCategoryToModal(id){
    var url = base_url + "/category/getCategoryById/" + id;
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
            $('#name').val(result.name);
            $("#category option[value=" + result.category.id + "]").attr('selected','selected');
        },
        error: function(xhr, resp, text) {
            alert("data could not be loaded to edit");
        }
    });
}

