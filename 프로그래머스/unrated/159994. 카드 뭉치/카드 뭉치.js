function solution(cards1, cards2, goal) {
    let answer = ''
    let i = 0
    let j = 0
    
    while(true) {
        if(!goal.length) break
        if(!cards1[i] && !cards2[j]) break
        
        const word = goal.shift()
                                   
        if(cards1[i] === word) i++
        else if(cards2[j] === word) j++
        else {
            answer = 'No'
            break
        }
    }

    if(!answer) answer = goal.length ? 'No' : 'Yes'
    
    return answer
}