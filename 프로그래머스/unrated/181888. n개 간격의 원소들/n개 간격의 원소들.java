class Solution {
    public int[] solution(int[] num_list, int n) {
        int[] answer = new int[ (int)Math.ceil(num_list.length/(double)n) ];
        for(int i=0,j=0; i<answer.length; i++,j+=n)
            answer[i] = num_list[j];
        
        return answer;
    }
}