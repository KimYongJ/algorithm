class Solution {
    public int solution(int[] array) {
        int[] countingSort = new int[1001];
        for(int x : array)
            countingSort[x]++;
        
        int max = 0, cnt = 0, result = 0;
        for(int i=0; i<1001; i++)
            if(max<countingSort[i]){ 
                max = countingSort[i];
                result = i;
            }
        
        for(int j=0; j<1001; j++){
            if(max == countingSort[j]) cnt++;
            if(cnt>1) return -1;
        }
        return result;
    }
}