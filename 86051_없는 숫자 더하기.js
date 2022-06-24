function solution(numbers) {
    var answer = 0;
    
    var numberList = [0,1,2,3,4,5,6,7,8,9];
    numberList.forEach(number =>{
       if(!numbers.includes(number)){
           answer += number;
       }
    });
    
    return answer;
}