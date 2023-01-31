class Solution {
    public int[] solution(int[] l, int[] w) {
        int[] arr = new int[2];
        
        for(int a : l){
            if(a==0) arr[1]++;
            for(int b : w)
                if(a==b)
                    arr[0]++;
        }
        
        
        int max = 7-(arr[0]+arr[1]);
        int min = 7-(arr[0]);
        if(max>5) max = 6;
        if(min>5) min = 6;

        return new int[]{max,min};
    }
}