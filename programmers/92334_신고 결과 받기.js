function solution(id_list, report, k) {
    var answer = [];
    
    id_list.forEach(id=>answer.push(0));
    
    var reportedIdObj = {};
    id_list.forEach(id=>reportedIdObj[id] = []);
    
    report.forEach(data=>{
        var splitedData = data.split(' ');
        var reporter = splitedData[0];
        var reported = splitedData[1];
        
        if(reportedIdObj[reported].includes(reporter)){
            return;
        }
        reportedIdObj[reported].push(reporter);
    })
    
    for(var id of id_list){
        var reporters = reportedIdObj[id];
        if(reporters.length >= k){
            for(var reporter of reporters){
                answer[id_list.indexOf(reporter)] += 1;
            }
        }
    }
    
    return answer;
}