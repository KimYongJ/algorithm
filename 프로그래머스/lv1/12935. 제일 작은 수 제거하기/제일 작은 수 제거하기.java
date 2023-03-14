class Solution {
    public int[] solution(int[] arr) {
        int len = arr.length;
        int min = 999999;
        int i=0;
        
        if(len==1) 
            return new int[]{-1};
        int[] result = new int[len-1];
        
        for(int x : arr)
            min = min>x ? x : min;
        
        for(int x : arr)
            if(x!=min)
                result[i++] = x;
        
        return result;
    }
}