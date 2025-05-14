class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        while( 
                !(
                    (wallet[0] >= bill[0] && wallet[1] >= bill[1])
                    || (wallet[0] >= bill[1] && wallet[1] >= bill[0])
                )
             )
        {
            if(bill[0] > bill[1])
                bill[0] /= 2;
            else
                bill[1] /= 2;
            
            ++answer;
        }
        return answer;
    }
}