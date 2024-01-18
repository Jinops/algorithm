function solution(record) {
    const answer = [];
    const uidObj = {};
    
    for(const data of record){
        const type = data.split(' ')[0];
        if(type=='Leave') 
            continue;
        const uid = data.split(' ')[1];
        const nickname = data.split(' ')[2];
        uidObj[uid] = nickname;
    }
    
    for(const data of record){
        const type = data.split(' ')[0];
        if(type=='Change') 
            continue;
        const uid = data.split(' ')[1];
        const nickname = data.split(' ')[2];
        
        let message = '';
        if(type=='Enter'){
            message = uidObj[uid] + '님이 들어왔습니다.';
        } else if(type=='Leave'){
            message = uidObj[uid] + '님이 나갔습니다.';
        }
        answer.push(message);
    }
    
    return answer;
}