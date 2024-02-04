function invocaAjax() {

    setInterval( function(){

        $.ajax({
            type: "GET",
            url: "funcionario",
            dataType: "text",
            data: {
                op: "emManutencao"
            },
            success: function(data, textStatus, jqXHR) {
                drawPieChart(data)
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                $('#piechart').text('Erro!');
            }
        })

        $.ajax({
            type: "GET",
            url: "funcionario",
            dataType: "text",
            data: {
                op: "atrasados"
            },
            success: function(data, textStatus, jqXHR) {
                drawColumnChart(data)
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                $('#columnchart').text('Erro!');
            }
        })

    }, 1000);

};

function drawPieChart(data) {

    console.log(data);
    indiceTraco = data.indexOf("-")
    let numMan = parseInt( data.substring(0, indiceTraco) )
    let numFunc = parseInt( data.substring(indiceTraco + 1) )
    console.log("numMan: " + numMan + ", numFunc: " + numFunc)

    var data = google.visualization.arrayToDataTable([
        ['Status', 'Quantidade'],
        ['Em Manutencao', numMan],
        ['Disponiveis', numFunc],
    ]);
    
    var options = {
        title: 'Distribuicao de onibus em Manutencao e Disponiveis'
    };
    
    var chart = new google.visualization.PieChart(document.getElementById('piechart'));
    
    chart.draw(data, options);
}

function drawColumnChart(data) {

    console.log(data);

    indiceTraco = data.indexOf("-")
    let numAtr = parseInt( data.substring(0, indiceTraco) )
    let numPont = parseInt( data.substring(indiceTraco + 1) )
    console.log("numAtr: " + numAtr + ", numPont: " + numPont)

    var data = google.visualization.arrayToDataTable([
        ['Status', 'Quantidade'],
        ['Atrasados', numAtr],
        ['Pontuais', numPont],
    ]);
    
    // Define as opções do gráfico
    var options = {
        title: 'Quantidade de Onibus Atrasados e Pontuais',
        hAxis: {
            title: 'Parada',
        },
        vAxis: {
            title: 'Quantidade de Onibus',
            minValue: 0
        },
        legend: 'top',
    };
    
    // Cria e desenha o gráfico de colunas
    var chart = new google.visualization.ColumnChart(document.getElementById('columnchart'));
    chart.draw(data, options);
}