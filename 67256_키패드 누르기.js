const star = 10;
const zero = 11;
const sharp = 12;
    
const leftNumList = [1, 4, 7, star];
const centerNumList = [2, 5, 8, zero];
const rightNumList = [3, 6, 9, sharp];

function solution(numbers, hand) {
    let answer = '';
    
    let lastNumber = {
        left: star,
        right: sharp,
    };
    
    for(let number of numbers){
        number = (number == 0) ? zero : number;
        
        if(leftNumList.includes(number)){
            console.log(number, lastNumber);
                console.log('->L');
            answer += 'L';
            lastNumber.left = number;
        } else if(rightNumList.includes(number)){
            console.log(number, lastNumber);
                console.log('->R');
            answer += 'R';
            lastNumber.right = number;
        } else {
            const cost = getCost(number, lastNumber);
            console.log(number, lastNumber, cost);
            if(cost.left < cost.right){
                answer += 'L';
                lastNumber.left = number;
                console.log('->L');
            } else if (cost.left > cost.right){
                answer += 'R';
                lastNumber.right = number;
                console.log('->R');
            } else{
                answer += (hand =='left') ? 'L' : 'R';
                lastNumber[hand] = number;
                console.log((hand =='left') ? 'L' : 'R');
            }
        }
        //console.log(lastNumber);
    }
    
    return answer;
}
    
function getCost(value, lastNumberObj){
    let leftIndex = leftNumList.indexOf(lastNumberObj.left);
    let rightIndex = rightNumList.indexOf(lastNumberObj.right);
    const centerIndex = centerNumList.indexOf(value);
    
    let leftHorizontalCost = 1;
    let rightHorizontalCost = 1;
    
    if(leftIndex == -1){
        leftIndex = centerNumList.indexOf(lastNumberObj.left);
        leftHorizontalCost = 0;
    }
    if(rightIndex == -1){
        rightIndex = centerNumList.indexOf(lastNumberObj.right);
        rightHorizontalCost = 0;
    }
    
    const leftCost = Math.abs(centerIndex - leftIndex) + leftHorizontalCost;
    const rightCost = Math.abs(centerIndex - rightIndex) + rightHorizontalCost;

    return {
        left: leftCost,
        right: rightCost,
    };
}