function solution(array, commands) {
    const answer = [];
    
    for(let command of commands){
        const arr = array.slice(command[0]-1, command[1]);
        arr.sort((a, b) =>{
            if(a>b) return 1;
            if(a<b) return -1;
            if(a==b) return 0;
        });
        answer.push(arr[command[2]-1]);
    } 
    
    return answer;
}