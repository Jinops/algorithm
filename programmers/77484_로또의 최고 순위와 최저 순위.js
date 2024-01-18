function solution(lottos, win_nums) {
    var answer = [];
    
    var win_count = 0;
    var unknown_count = lottos.filter(element => element == 0).length;
    
    for(var win_num of win_nums){
        if(lottos.includes(win_num)){
            win_count += 1;
        }
    }
    
    var max_rank = checkRank(win_count + unknown_count);
    var min_rank = checkRank(win_count);
    
    answer = [max_rank, min_rank];
    
    return answer;
}

function checkRank(win_count){
    switch(win_count){
        case 6: return 1;
        case 5: return 2;
        case 4: return 3;
        case 3: return 4;
        case 2: return 5;
        default: return 6;
    }
}