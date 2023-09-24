function solution(s)
{
    let answer = 1
    const S_LEN = s.length
    const dp = new Array(S_LEN).fill().map(_ => new Array(S_LEN).fill(false))
    
    // 길이가 1, 2
    for(let i=0; i<S_LEN; i++) {
        dp[i][i] = true
        if(i+1 < S_LEN && s[i] === s[i+1]) {
            dp[i][i+1] = true
            answer = 2
        }
    }

    // 길이 3이상
    for(let i=3; i<=S_LEN; i++) {
        for(let start=0; start<=S_LEN-i; start++) {
            const end = start+i-1
            if(s[start] === s[end] && dp[start+1][end-1]) {
                dp[start][end] = true
                answer = answer > i ? answer : i
            }
        }
    }
    
    return answer
}