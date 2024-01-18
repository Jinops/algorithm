function solution(participant, completion) {
    const obj = {};
    for(let name of completion){
        if(!obj[name]){
            obj[name] = 0;
        }
        obj[name] += 1;
    }
    
    for(let name of participant){
        if(!obj[name]){
            return name;
        }
        obj[name] -= 1;
    }
}