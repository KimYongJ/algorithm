class Solution {
    static int[] sale;
    static int[] sdata = {10,20,30,40};
    static int[] answer = new int[2];
    public int[] solution(int[][] u, int[] e) {
        sale = new int[e.length];
        back(u,e,0);
        return answer;
    }
    public void back(int[][] u, int[] e,int depth){
        if(depth == e.length){
            // 여기서 해당 비교코드 들어가면됨
            int[] result = new int[2];
            for(int i=0; i<u.length; i++){ // 사람에 대해서 반복
                int emosum = 0; // 이모티콘 가격 총합
                for(int x=0; x<e.length; x++) // 이모티콘에 대해서 반복
                    if(u[i][0]<=sale[x]) // 퍼센테이지가 해당될 때
                        emosum += e[x]*(100-sale[x])/100;
                if(emosum>=u[i][1])
                    result[0]++;
                else
                    result[1] += emosum;
            }
            if(answer[0]<result[0]){
                answer[0] = result[0];
                answer[1] = result[1];
            }else if(answer[0]==result[0] && answer[1]<result[1]){
                answer[1] = result[1];
            }
            return;
        }
        for(int i=0; i<4; i++){
                sale[depth] = sdata[i];
                back(u,e,depth+1);
        }
    }
} 