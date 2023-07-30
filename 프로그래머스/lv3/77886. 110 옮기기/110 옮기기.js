function solution(s) {
    let answer = []
    
    s.forEach(str => {
        
        // '110' 찾아서 꺼내기
        let oneCnt = '';
        let newStr = [];
        let len = 0;
        for(let i = 0; i<str.length; i++){
            newStr.push(str[i]);
            len = newStr.length;
            if(newStr.length>2){
                if(newStr[len-3]=='1' && newStr[len-2]=='1' && newStr[len-1]=='0'){
                    oneCnt += '110';
                    newStr.pop();newStr.pop();newStr.pop();
                }
            }
        }
        // 110 넣기
        let lastZero = newStr.lastIndexOf('0')+1;
        newStr = newStr.join('');
        newStr = newStr.slice(0, lastZero) + oneCnt + newStr.slice(lastZero, newStr.length)
1
        answer.push(newStr)
    })
    
    return answer
}

//////////////////////////////////////////////// 다른 버전 ///////////////////////////////////////
function solution(s) {
    let answer = []
    
    s.forEach(str => {
        let idx = -1
        let stack = 0
        let STR_LEN = str.length
        
        // '110' 찾아서 꺼내기
        let oneCnt = 0
        let newStr = []
        for(let j=0; j<STR_LEN; j++) {
            if(str[j] === '1') {
                oneCnt++
                newStr.push(str[j])
            } else {
                if(oneCnt >= 2) {
                    oneCnt -= 2
                    newStr.pop();
                    newStr.pop();
                    stack++
                } else {
                    newStr.push(str[j])
                    oneCnt = 0
                }
            }
        }

        // 110 넣기
        let lastZero = newStr.lastIndexOf('0')+1; // +1을 해주면 -1인 경우를 생각할 필요가 없게 됨
        
        
//         if(stack && lastZero === -1) {
//             newStr = '110' + newStr
//             lastZero = 2
//             stack--
//         }
        
//         while(stack) {
//             newStr = newStr.slice(0, lastZero+1) + '110' + newStr.slice(lastZero+1, newStr.length)
//             lastZero += 3
//             stack--
//         }
        newStr = newStr.join('')
        newStr = newStr.slice(0, lastZero) + '110'.repeat(stack) + newStr.slice(lastZero, newStr.length)
        
        answer.push(newStr)
    })
    
    return answer
}
