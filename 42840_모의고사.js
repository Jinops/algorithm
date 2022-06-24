function solution(answers) {
    const user01 = [1,2,3,4,5];
    const user02 = [2,1,2,3,2,4,2,5];
    const user03 = [3,3,1,1,2,2,4,4,5,5];
    
    const answerCounts = [
        getAnswerCount(user01, answers),
        getAnswerCount(user02, answers),
        getAnswerCount(user03, answers)
    ];
    
    let winners = [1];
    
    for(let i=1; i<answerCounts.length; i++){
        if(answerCounts[i] > answerCounts[winners[0]-1]){
            winners = [i+1];
        } else if (answerCounts[i] == answerCounts[winners[0]-1]){
            winners.push(i+1);
        }
    }
    
    return winners;
}
        
function getAnswerCount(myAnswers, answers){
    let count = 0;
    
    for(let i=0; i<answers.length; i++){
        if(myAnswers[i%myAnswers.length] == answers[i]){
            count += 1;
        }
    }
    
    return count;
}