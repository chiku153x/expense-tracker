var base_url = "http://localhost:8080/expense-tracker/api";
$(document).ready(function() { 
    $('#login-error').hide();
    loadCategories();
 });

$('#logout').click(function(){
    localStorage.setItem('currentUser', null);
    window.location.href = "login.html";
}); 

$('#login-button').click(function(){
    var url = base_url + "/user/login";
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
                localStorage.setItem('currentUser', JSON.stringify(result));
                window.location.href = "index.html";
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

function loadCategories(){
    var url = base_url + "/category/getAllCategories/" + JSON.parse(localStorage.getItem('currentUser'))['id'];
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


