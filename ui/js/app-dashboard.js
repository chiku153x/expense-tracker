var myChartx;
var myCharty;
$(document).ready(function() { 
    const ctx = document.getElementById('myChart').getContext('2d');
    const ctx2 = document.getElementById('myChart2').getContext('2d');

    myChartx = new Chart(ctx2, {
        type: 'doughnut',
        data: {
            labels: ["Budget",'Expenses'],
            datasets: [{
                label: '',
                data: [0,0],
                backgroundColor: [
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 99, 132, 0.2)'
                ],
                borderColor: [
                'rgba(54, 162, 235, 0.5)',
                'rgba(255, 99, 132, 0.5)'
                
            ],
                hoverOffset: 4
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });



    myCharty = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: [],
            datasets: [{
                label: 'Budget',
                data: [],
                backgroundColor: [
                ],
                borderColor: [
                ],
                borderWidth: 1
            },{
                label: 'Expenses',
                data: [],
                backgroundColor: [
                ],
                borderColor: [
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });




   loadSummary();
   
});

$('#loadData').click(function(){
    loadSummary();
});



function loadSummary(){
    
    var year = $('#year').val();
    var month = $('#month').val();
    var url = base_url + "/transaction/getTransactionById/"+JSON.parse(localStorage.getItem('currentUser'))['id'] + "/" + year + "/" + month;
   
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
            var totalBudget = 0;
            var totalExpenses = 0;

            var budgetData = [];
            var expenseData = [];
            var xcategories = [];

            $.each(result, function (i, o) {
                var cate = getCategoryNameFromId(categories,o.category);
                var row = [];
                row.push(o.no);
                row.push(cate);
                row.push(o.totalExpenses);
                row.push(o.budget);

                if(o.totalExpenses > o.budget){
                    row.push('<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-arrow-up-circle-fill me-color-red" viewBox="0 0 16 16"> <path d="M16 8A8 8 0 1 0 0 8a8 8 0 0 0 16 0zm-7.5 3.5a.5.5 0 0 1-1 0V5.707L5.354 7.854a.5.5 0 1 1-.708-.708l3-3a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1-.708.708L8.5 5.707V11.5z"/></svg>');
                }else if(o.totalExpenses < o.budget){
                    row.push('<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-arrow-down-circle-fill me-color-orange" viewBox="0 0 16 16"><path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.5 4.5a.5.5 0 0 0-1 0v5.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V4.5z"/></svg>');
                }else{
                    row.push('');
                }
  
                
                data.push(row);
 
                budgetData.push(o.budget);
                expenseData.push(o.totalExpenses);
                xcategories.push(cate);

                totalBudget = totalBudget + o.budget;
                totalExpenses = totalExpenses + o.totalExpenses;

                showChartPie([totalBudget,totalExpenses]);
                showChartBar(budgetData,expenseData,xcategories);
            });
            $('#dataTable').DataTable({
                data:data,
                "bDestroy": true
            });
            

            $('#toast-successful').toast('show');
        },
        error: function(xhr, resp, text) {
            alert("Summary data was not loaded");
        }
    });
}



function showChartPie(data){
    myChartx.data.datasets[0].data = data;
    myChartx.update();
}


function showChartBar(budgetData,expenseData,xcategories){
    var data = getDataset(budgetData,expenseData,xcategories)
    myCharty.data = data;
    myCharty.update();
}



function getDataset(budgetData,expenseData,categories){
   var data =  {
                    labels: categories,
                    datasets: [{
                        label: 'Budget',
                        data: budgetData,
                        backgroundColor: [
                            'rgba(54, 162, 235, 0.2)' 
                        ],
                        borderColor: [
                            'rgba(54, 162, 235, 1)'
                        ],
                        borderWidth: 1
                    },{
                        label: 'Expenses',
                        data: expenseData,
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)'
                        ],
                        borderColor: [
                            'rgba(255, 99, 132, 1)'
                        ],
                        borderWidth: 1
                    }]
                };

    return data;
}

