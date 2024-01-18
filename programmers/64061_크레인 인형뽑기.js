function solution(board, moves) {
    var answer = 0;
    
    var newBoard = [];
    var pocket = [];
    
    for(var x=0; x<board[0].length; x++){
        var dollByX = [];
        for(var y=0; y<board.length; y++){
            var doll = board[y][x];
            if(doll){
                dollByX.push(doll);
            }
        }
        newBoard.push(dollByX);
    }
    
    moves.forEach(move=>{
        var dolls = newBoard[move-1];
        if(dolls.length > 0){
            pocket.push(dolls[0]);
            dolls.shift();
        }
    })
    
    answer = removeDuplication(pocket);
    
    return answer;
}
                
function removeDuplication(array){
    var removeCount = 0;
    var startIndex;
    
    for(var i=array.length; i>=0; i--){
        if(i>0 && array[i]==array[i-1]){
            startIndex = startIndex ? startIndex : i;
        } else if(startIndex){
            var duplicatedCount = startIndex - i + 1;
            array.splice(i, duplicatedCount);
            removeCount += duplicatedCount;
            i=array.length;
            startIndex = null;
        }
    }
    
    return removeCount;
}