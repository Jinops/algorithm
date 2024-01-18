function solution(N, stages) {
    let answer = [];
    const currentUserObj = {};
    const failRateObj = {};
    
    for(const stage of stages){
        if(!currentUserObj[stage]){
            currentUserObj[stage] = 0;
        }
        currentUserObj[stage] += 1;
    }
    
    for(let stage=1; stage <= N; stage++){
        const stageUser = currentUserObj[stage];
        if(!stageUser){
            failRateObj[stage] = 0;
            continue;
        }
        
        let clearUser = 0;
        for(let nextStage=stage+1; nextStage <= N+1; nextStage++){
            if(currentUserObj[nextStage]){
            clearUser += currentUserObj[nextStage];
            }
        }
        failRateObj[stage] = stageUser / (clearUser + stageUser);
    }
    
    const keys = Object.keys(failRateObj);
    for(let i=0; i<keys.length; i++){
        for(let j=i+1; j<keys.length; j++){
            if(failRateObj[keys[j]] > failRateObj[keys[i]] ||
              (failRateObj[keys[j]] == failRateObj[keys[i]] && Number(keys[j]) < Number(keys[i]))){
                [keys[i], keys[j]] = [keys[j], keys[i]];
            }
        }
    }
    answer = keys.map(element => Number(element));
    
    return answer;
}