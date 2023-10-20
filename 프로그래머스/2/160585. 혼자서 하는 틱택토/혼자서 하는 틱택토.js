function solution(board) {
    
    //O,X 개수세기
    let oCnt=0;
    let xCnt=0;
    for (let i=0 ; i<3 ; i++) {
        for (let j=0 ; j<3 ; j++){
            if (board[i][j]==="O") oCnt ++;
            else if (board[i][j]==="X") xCnt ++;
        }
    }
    
    if (![0,1].includes(oCnt-xCnt)) return 0
    
    //만들어진 점수 비교
    let oScore = 0
    let xScore = 0
    //가로,세로
    for (let i=0 ; i<3;i++){
        if (board[i]==='XXX') xScore++;
        else if (board[i]==='OOO') oScore++;
        if (board[0][i]+board[1][i]+board[2][i] === 'XXX')xScore++;
        else if (board[0][i]+board[1][i]+board[2][i] === 'OOO') oScore++;
    }
    //대각선
    if (board[0][0]+board[1][1]+board[2][2]==='XXX') xScore++;
    else if (board[0][0]+board[1][1]+board[2][2]==='OOO') oScore++;
    if (board[0][2]+board[1][1]+board[2][0]==='XXX') xScore++;
    else if (board[0][2]+board[1][1]+board[2][0]==='OOO') oScore++;
    
    if (0<xScore && 0<oScore) return 0
    if (0<xScore && xCnt!==oCnt) return 0
    if (0<oScore && xCnt+1!==oCnt) return 0
    
    
    return 1
}