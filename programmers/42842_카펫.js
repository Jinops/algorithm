function solution(brown, yellow) {
    for(let w=brown+yellow-1; w>0; w--){
        const h=(brown+yellow)/w;
        if(h%1) {
            continue;
        }

         if(brown==w*2+h*2-4){
             return [w, h];
         }
    }
}