function invocaAjax() {

    $.ajax({
        type: "GET",
        url: "funcionario",
        dataType: "json",
        data: "",
        success: function(data, textStatus, jqXHR) {
            numMan = int(data.substring(0, data.indexOf("-")))
            numFunc = int(data.substring(data.indexOf("-")) + 1)
            drawChart(numMan, numFunc)
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            $('#piechart').text('Erro!');
        }
    });

};

function drawChart(numMan, numFunc) {
    var data = google.visualization.arrayToDataTable([
        ['Status', 'Quantidade'],
        ['Em Manutenção', numMan],
        ['Disponíveis', numFunc]
    ]);
    
    var options = {
        title: 'Distribuição de Ônibus em Manutenção e Disponíveis'
    };
    
    var chart = new google.visualization.PieChart(document.getElementById('piechart'));
    
    chart.draw(data, options);
}