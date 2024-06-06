/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 0.0, "KoPercent": 100.0};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.0, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.0, 500, 1500, "Request 3"], "isController": false}, {"data": [0.0, 500, 1500, "Request 1"], "isController": false}, {"data": [0.0, 500, 1500, "Request 2"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 192, 192, 100.0, 8117.078124999998, 3885, 12308, 7874.0, 11455.300000000001, 11525.0, 12305.21, 2.0931449502878077, 0.47448366365777084, 0.3209216378859236], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["Request 3", 64, 64, 100.0, 8381.15625, 4896, 12301, 8010.0, 11481.0, 11527.0, 12301.0, 0.6977149834292691, 0.1580760509331938, 0.10697387929530787], "isController": false}, {"data": ["Request 1", 64, 64, 100.0, 7848.796874999999, 3885, 12308, 7870.5, 10518.5, 11540.5, 12308.0, 0.7309524081455508, 0.16578486014824628, 0.1120698516395034], "isController": false}, {"data": ["Request 2", 64, 64, 100.0, 8121.281249999998, 4893, 12305, 7877.5, 11475.5, 11515.0, 12305.0, 0.6977149834292691, 0.15816122121925694, 0.10697387929530787], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["The operation lasted too long: It took 11,567 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 11,257 milliseconds, but should not have lasted longer than 510 milliseconds.", 2, 1.0416666666666667, 1.0416666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 11,525 milliseconds, but should not have lasted longer than 510 milliseconds.", 2, 1.0416666666666667, 1.0416666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 10,770 milliseconds, but should not have lasted longer than 510 milliseconds.", 3, 1.5625, 1.5625], "isController": false}, {"data": ["The operation lasted too long: It took 9,301 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 12,305 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 9,318 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 11,561 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 6,518 milliseconds, but should not have lasted longer than 510 milliseconds.", 3, 1.5625, 1.5625], "isController": false}, {"data": ["The operation lasted too long: It took 9,911 milliseconds, but should not have lasted longer than 510 milliseconds.", 2, 1.0416666666666667, 1.0416666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 11,365 milliseconds, but should not have lasted longer than 510 milliseconds.", 2, 1.0416666666666667, 1.0416666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 9,928 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 6,529 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 11,485 milliseconds, but should not have lasted longer than 510 milliseconds.", 2, 1.0416666666666667, 1.0416666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 11,247 milliseconds, but should not have lasted longer than 510 milliseconds.", 2, 1.0416666666666667, 1.0416666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 11,185 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 9,941 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 11,474 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 11,241 milliseconds, but should not have lasted longer than 510 milliseconds.", 3, 1.5625, 1.5625], "isController": false}, {"data": ["The operation lasted too long: It took 6,600 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 6,597 milliseconds, but should not have lasted longer than 510 milliseconds.", 2, 1.0416666666666667, 1.0416666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 6,513 milliseconds, but should not have lasted longer than 510 milliseconds.", 2, 1.0416666666666667, 1.0416666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 6,510 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 9,286 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 9,926 milliseconds, but should not have lasted longer than 510 milliseconds.", 3, 1.5625, 1.5625], "isController": false}, {"data": ["500/Internal Server Error", 108, 56.25, 56.25], "isController": false}, {"data": ["The operation lasted too long: It took 8,305 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 6,539 milliseconds, but should not have lasted longer than 510 milliseconds.", 2, 1.0416666666666667, 1.0416666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 10,251 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 12,308 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 9,304 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 6,599 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 12,301 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 11,243 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 6,540 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 10,257 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 11,488 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 11,467 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 6,601 milliseconds, but should not have lasted longer than 510 milliseconds.", 4, 2.0833333333333335, 2.0833333333333335], "isController": false}, {"data": ["The operation lasted too long: It took 11,477 milliseconds, but should not have lasted longer than 510 milliseconds.", 2, 1.0416666666666667, 1.0416666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 6,609 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 9,930 milliseconds, but should not have lasted longer than 510 milliseconds.", 3, 1.5625, 1.5625], "isController": false}, {"data": ["The operation lasted too long: It took 10,203 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 11,242 milliseconds, but should not have lasted longer than 510 milliseconds.", 2, 1.0416666666666667, 1.0416666666666667], "isController": false}, {"data": ["The operation lasted too long: It took 11,408 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 6,531 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 11,194 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 11,527 milliseconds, but should not have lasted longer than 510 milliseconds.", 3, 1.5625, 1.5625], "isController": false}, {"data": ["The operation lasted too long: It took 8,320 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 11,479 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 6,512 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 10,267 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 10,836 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 8,307 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 11,428 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 11,480 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 6,515 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 9,927 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}, {"data": ["The operation lasted too long: It took 9,924 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, 0.5208333333333334, 0.5208333333333334], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 192, 192, "500/Internal Server Error", 108, "The operation lasted too long: It took 6,601 milliseconds, but should not have lasted longer than 510 milliseconds.", 4, "The operation lasted too long: It took 10,770 milliseconds, but should not have lasted longer than 510 milliseconds.", 3, "The operation lasted too long: It took 6,518 milliseconds, but should not have lasted longer than 510 milliseconds.", 3, "The operation lasted too long: It took 11,241 milliseconds, but should not have lasted longer than 510 milliseconds.", 3], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": ["Request 3", 64, 64, "500/Internal Server Error", 32, "The operation lasted too long: It took 11,527 milliseconds, but should not have lasted longer than 510 milliseconds.", 3, "The operation lasted too long: It took 11,257 milliseconds, but should not have lasted longer than 510 milliseconds.", 2, "The operation lasted too long: It took 10,251 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, "The operation lasted too long: It took 10,770 milliseconds, but should not have lasted longer than 510 milliseconds.", 1], "isController": false}, {"data": ["Request 1", 64, 64, "500/Internal Server Error", 40, "The operation lasted too long: It took 9,926 milliseconds, but should not have lasted longer than 510 milliseconds.", 3, "The operation lasted too long: It took 6,539 milliseconds, but should not have lasted longer than 510 milliseconds.", 2, "The operation lasted too long: It took 11,567 milliseconds, but should not have lasted longer than 510 milliseconds.", 1, "The operation lasted too long: It took 12,308 milliseconds, but should not have lasted longer than 510 milliseconds.", 1], "isController": false}, {"data": ["Request 2", 64, 64, "500/Internal Server Error", 36, "The operation lasted too long: It took 6,601 milliseconds, but should not have lasted longer than 510 milliseconds.", 3, "The operation lasted too long: It took 11,525 milliseconds, but should not have lasted longer than 510 milliseconds.", 2, "The operation lasted too long: It took 9,930 milliseconds, but should not have lasted longer than 510 milliseconds.", 2, "The operation lasted too long: It took 9,304 milliseconds, but should not have lasted longer than 510 milliseconds.", 1], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
