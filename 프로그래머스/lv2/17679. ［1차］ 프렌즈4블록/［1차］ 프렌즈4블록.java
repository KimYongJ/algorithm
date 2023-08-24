class Solution {
    char[][] arr;           // 처음에 주어진 board를 char형 배열로 바꿔 저장한다.
    boolean[][] deleteArr;  // 원배열에서 지울 정보를 담는 배열
    int m,n,result = 0;

    public boolean cntCheck(){ // 지울 데이터를 실제로 지워주는 로직
        int base = result;
        for(int i=0; i<m; i++)
            for(int j=0; j<n; j++)
                if(deleteArr[i][j]){
                    result++;
                    arr[i][j] = 0;
                    deleteArr[i][j] = false;
                }
        return base==result ? false : true; // 지워진게 없다면 false, 있다면 true
    }
    
    public void sort(){// 지운 후 데이터를 정렬하는 로직
        for(int i=m-1; i>0; i--)
          Loop:for(int j=0; j<n; j++)
            if(arr[i][j]==0)
                for(int x=i-1;x>=0; x--)
                    if(arr[x][j]!=0){
                        arr[i][j] = arr[x][j];
                        arr[x][j] = 0;
                        continue Loop;
                    }
    }
    
    public void findValue(){ //지울 데이터를 추가해주는 로직
        for(int i=0; i<m-1; i++)
            for(int j=0; j<n-1; j++){
                char base = arr[i][j];
                if(base !=0 && base == arr[i+1][j] && base == arr[i][j+1] && base == arr[i+1][j+1]){
                    deleteArr[i][j]   = deleteArr[i+1][j] = 
                    deleteArr[i][j+1] = deleteArr[i+1][j+1] = true;
                }
            }
    }
    
    public int solution(int m, int n, String[] board) {
        this.m = m; 
        this.n =n;
        deleteArr = new boolean[m][n];
        arr = new char[m][n];
        for(int i=0; i<m; i++) arr[i] = board[i].toCharArray();
        
        while(true){
            findValue();        // 지울 데이터를 추가해주는 로직
            if(!cntCheck()){    // 지울 데이터를 실제로 지워주는 로직 지울게 없다면 false반환
                break;
            }
            sort();             // 지운 후 데이터를 정렬하는 로직
        }
        
        return result;
    }
}