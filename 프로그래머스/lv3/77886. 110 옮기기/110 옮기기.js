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