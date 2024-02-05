function carregarPontos() {
    var xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var pontos = JSON.parse(this.responseText);
            var select = document.getElementById("pontos");


            select.innerHTML = "";

            for (var i = 0; i < pontos.length; i++) {
                var option = document.createElement("option");
                option.text = pontos[i].nome;
                option.value = pontos[i].nome;
                select.appendChild(option);
            }
        }
    }

 
    xmlhttp.open("GET", "interfaceusuario", true);
    xmlhttp.send();
}


window.onload = carregarPontos;


function carregarDados() {
    var pontoSelecionado = document.getElementById("pontos");
    var valorSelecionado = pontoSelecionado.value; 

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "interfaceusuario", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    var hora = new Date();
    var horaFormatada = hora.getHours().toString().padStart(2, '0') + ":" + 
                        hora.getMinutes().toString().padStart(2, '0') + ":" +
                        hora.getSeconds().toString().padStart(2, '0');
    

   
    var params = "nomePonto=" + encodeURIComponent(valorSelecionado) + "&hora=" + horaFormatada; 

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var data = JSON.parse(xhr.responseText);
            atualizarTabelaLinhas(data);
        }
    }
    xhr.send(params);
}


function atualizarTabelaLinhas(linhas) {
    var tabela = document.getElementById("tabelaResultado");
    var tbody = tabela.getElementsByTagName("tbody")[0];
    tbody.innerHTML = "";

    for (var i = 0; i < linhas.length; i++) {
        var linha = linhas[i];

        var tr = document.createElement("tr");
        var tdNumeroLinha = document.createElement("td");
        tdNumeroLinha.textContent = linha.numeroLinha;

        var tdNomeLinha = document.createElement("td");
        tdNomeLinha.textContent = linha.nomeLinha;

        var tdHorario = document.createElement("td");
        tdHorario.textContent = linha.horario;

        tr.appendChild(tdNumeroLinha);
        tr.appendChild(tdNomeLinha);
        tr.appendChild(tdHorario);

        tbody.appendChild(tr);
    }
}

setInterval(carregarDados, 5000);
