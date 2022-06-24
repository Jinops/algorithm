function solution(nums) {
    let answer = 0;
    
    const obj = {};
    const max = nums.length/2;
    
    for(const num of nums){
        if(obj[num]){
            obj[num] = 0;
        }
        obj[num] += 1;
        
        if(Object.keys(obj).length >= max){
            break;
        }
    }
    
    answer = Object.keys(obj).length;
    
    return answer;
}