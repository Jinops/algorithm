function solution(nums) {
    const sumArr = [];
    
   for(let i=0; i<nums.length; i++){
       for(let j=i+1; j<nums.length; j++){
           for(let k=j+1; k<nums.length; k++){
               const sum = nums[i] + nums[j] + nums[k];
               if(isPrimeNumber(sum)){
               sumArr.push(nums[i] + nums[j] + nums[k]);
               }
           }
       }
   }
    
    return sumArr.length;
}


function isPrimeNumber(num){
    if(num >=1 && num <=3){
        return true;
    }
    for(let i=2; i<num; i++){
        if(num%i==0){
            return false;
        }
    }
    return true;
}