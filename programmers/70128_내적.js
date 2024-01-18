function solution(a, b) {
    let answer = 0;
    
    for(const i in a){
        answer += a[i] * b[i];
    }
    
    return answer;
}