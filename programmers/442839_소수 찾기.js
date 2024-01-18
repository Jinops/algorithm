function solution(numberStr) {
    const numbers = numberStr.split('');
    const results = [];
    for(let i=1; i<=numberStr.length; i++){
        const ps = getP(numbers, i);
        ps.map(p =>{
           const number = Number(p.join(''));
            if(isPrimeNumber(number) && !results.includes(number)){
                results.push(number);
            }
        });
    }
    
    return results.length;
}

function getP(arr, size){
    if(size == 1){
        return arr.map(elem=>[elem]);
    }
    const result = [];
    
    arr.forEach((elem, i) =>{
        const rest = arr.slice(0,i).concat(arr.slice(i+1));
        const ps = getP(rest, size-1);
        const attach = ps.map(p=>[elem, ...p]);
        
        result.push(...attach);
    });
    
    return result;
}

function isPrimeNumber(number){
    if(number < 2){
        return false;
    }
    for(let i=2; i<number; i++){
        if(number%i==0){
            return false;
        }
    }
    
    return true;
}