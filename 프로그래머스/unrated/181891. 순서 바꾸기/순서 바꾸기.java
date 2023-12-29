class Solution {
    
    int i, j, len, answer[];
    
    public int[] solution(int[] num_list, int n) {
        len     = num_list.length;
        if(len==n) return num_list;
        answer  = new int[len];
        
        for(i=n, j=0 ; j<len; j++, i = i==len-1 ? 0 : i+1)
            answer[j] = num_list[i];
        
        return answer;
    }
}