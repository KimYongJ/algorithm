function solution(topping) {
    let answer = 0
    const TOPPING_LEN = topping.length
    const older = topping.reduce((toppings, t) => {
        toppings[t] ? toppings[t]++ : toppings[t] = 1
        return toppings
    }, {})
    let olderLen = Object.keys(older).length
    let younger = []
    let youngerLen = 0
    
    for(let i=TOPPING_LEN; i--; i>0) {
        if(younger.indexOf(topping[i]) === -1) {
            younger.push(topping[i])
            youngerLen++
        }
        older[topping[i]]--
        if(!older[topping[i]]) {
            delete older[topping[i]]
            olderLen--
        }
        if(youngerLen === olderLen) answer++
    }
    
    return answer
}