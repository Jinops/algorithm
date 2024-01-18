function solution(new_id) {
    var answer = '';
    var id_array = [];
    
    id_array = new_id.toLowerCase().split(''); // 1
    
    for(var i=0; i < id_array.length; i++){
        var character = id_array[i];
        var reg=/[a-z00-9-_.]/;
        if(reg.test(character)){
            continue;
        }
        id_array.splice(i, 1);
        i--;
    } // 2
    
    var dotStartIndex;
    var moreDotCount = 0;
    for(var i=1; i < id_array.length; i++){
        if(id_array[i-1] == '.' && id_array[i] == '.'){
            if(dotStartIndex == null){
                dotStartIndex = i-1;
            }
            moreDotCount += 1;
        } else if(dotStartIndex != null){
            id_array.splice(dotStartIndex, moreDotCount);
            i=i-moreDotCount;
            dotStartIndex = null;
            moreDotCount = 0;
        }
        
        if(i == id_array.length-1 && dotStartIndex != null){
            id_array.splice(dotStartIndex, moreDotCount);
            i=i-moreDotCount;
            dotStartIndex = null;
            moreDotCount = 0;
        }
    } // 3
    
    if(id_array[0] == '.'){
        id_array.shift();
    }
    if(id_array[id_array.length-1] == '.'){
        id_array.pop();
    } // 4
    
    if(id_array.length == 0){
        id_array.push('a');
    } // 5
    
    if(id_array.length >= 16){
        id_array.splice(15);
    }
    while(id_array[id_array.length-1] == '.'){
        id_array.pop();
    } // 6
    
    while(id_array.length <= 2){
        var lastCharacter = id_array[id_array.length-1];
        id_array.push(lastCharacter);
    } // 7
    
    answer = getStringFromArray(id_array);
    
    return answer;
}

function getStringFromArray(array){
    var result ='';
    for(var character of array){
        result += character;
    }
    return result;
}