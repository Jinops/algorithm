function solution(s) {
    let answer = s.length;
    const arr = [];
    
    for(var length=Math.floor(s.length/2); length>=1; length--){
        let newS = '';
        let count = 1;
        
        for(let i=0; i<s.length-length; i+=length){
            const str = s.substr(i,length);
            const nextStr = s.substr(i+length,length);
            
            if(str == nextStr){
                count += 1;
            } else if(count > 1){
                newS += str + count;
                count = 1;
            } else {
                newS += str;
            }
            
            if(i+length >= s.length-length){
                if(str == nextStr){
                    newS += str + count;
                    count = 1;
                }  else if(count > 1){
                    newS += str + count + nextStr;
                    count = 1;
                } else {
                    newS += nextStr;
                }
            }
        }
        
        if(newS.length < answer){
            answer = newS.length;
        }
    }
    return answer;
}