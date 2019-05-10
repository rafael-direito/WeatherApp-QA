google.load('visualization', '1', {
    packages: ['corechart', 'line']
});
google.setOnLoadCallback(drawBackgroundColor);

function drawBackgroundColor() 
{
    /*
    Temperature
    */
    var data_temp = new google.visualization.DataTable();
    data_temp.addColumn('string', 'Day');
    data_temp.addColumn('number', 'Min. Temperature');
    data_temp.addColumn('number', 'Max. Temperature');
    //console.log("--");
    data_temp.addRows([
        ["25 may", 0, 0],
        ["27 may", 10, 15],
        ["29 may", 20, 65]
    ]);
    //console.log(data);
    var options_temp = {
        height: 350,
        pointSize: 5,

        legend: {
            position: 'bottom'
        },
        vAxis: {
            title: 'Celsius (ºC)'
        },
        //backgroundColor: '#fff'
    };

    /*
    Humidity
    */
   var data_hum = new google.visualization.DataTable();
   data_hum.addColumn('string', 'Day');
   data_hum.addColumn('number', 'Humidty');

   data_hum.addRows([
    ["25 may", 0],
    ["27 may", 15],
    ["29 may", 65]
]);

   var options_hum = {
    height: 350,
    pointSize: 5,
    legend: {
        position: 'bottom'
    },
    vAxis: {
        title: 'xxx'
    },
    };


    function resize() {
        var chart_temp = new google.visualization.LineChart(document.getElementById('temperature_chart'));
        chart_temp.draw(data_temp, options_temp);

        var chart_hum = new google.visualization.LineChart(document.getElementById('humidity_chart'));
        chart_hum.draw(data_hum, options_hum);
    }
    window.onload = resize();
    window.onresize = resize;
}