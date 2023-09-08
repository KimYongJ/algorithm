// https://github.com/KimYongJ/algorithm
class Solution {
    public int[] solution(int n) {
        int n1 = 0; // 1~n까지 증가하면서 현재 해야될 것이 아래로 내려가기, 오른쪽으로 가기, 위로올라가기 중 어떤 것인지 알게 해준다.
        int y=-1, x=0; // xy 좌표
        int num = 1; // 1씩 증가시킬 숫자
        int[][] arr = new int[n][n]; // 달팽이를 담을 배열
        while(n!=0){
            ++n1; // n1을 1씩 더해준다.
            if(n1%3==1){ // 3으로 나눈 나머지가 1인 경우 아래로 내려가기
                for(int j=0; j<n; j++){
                    arr[++y][x] = num++;
                }
            }else if( n1%3==2){// 3으로 나눈 나머지가 2인 경우 오른쪽으로 이동하기
                for(int j=0; j<n; j++){
                    arr[y][++x] = num++;
                }
            }else if(n1%3==0){// 3으로 나눈 나머지가 0인 경우 왼쪽위로 올라가기
                for(int j=0; j<n; j++){
                    arr[--y][--x] = num++;
                }
            }
            n--; // n을 1씩 마이너스 해주어 몇번의 반복문을 돌려야 하는지 알게 한다.
        }
        int index = 0;
        int[] result = new int[n1*(n1+1)/2];// n1의 값이 처음 부여된 n의 값과 같기 때문에 n1으로 함, 이 때 n값은 0이다
        for(int i=0; i<n1; i++){
            for(int j=0; j<n1; j++){
                if(arr[i][j]==0) break;
                result[index++] = arr[i][j];
            }
        }
        return result;
    }
}