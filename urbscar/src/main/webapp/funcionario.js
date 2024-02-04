function invocaAjax() {

    $.ajax({
        type: "GET",
        url: "funcionario",
        dataType: "text",
        data: {},
        success: function(data, textStatus, jqXHR) {
            console.log(data);
            indiceTraco = data.indexOf("-")
            let numMan = parseInt( data.substring(0, 1) )
            let numFunc = parseInt( data.substring(indiceTraco + 1) )
            console.log("numMan: " + numMan + ", numFunc: " + numFunc)
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