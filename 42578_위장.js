function solution(clothes) {
    const countObj = {};
    let count = 1;
    clothes.forEach(cloth=>{
        const type = cloth[1];
        if(!countObj[type]){
            countObj[type] = 0;
        }
        countObj[type] += 1;
    });
    Object.keys(countObj).forEach(key=>count *= countObj[key]+1);
    
    return count-1;
}