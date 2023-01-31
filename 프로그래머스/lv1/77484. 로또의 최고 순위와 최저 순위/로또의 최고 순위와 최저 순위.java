class Solution {
    public int[] solution(int[] l, int[] w) {
        int[] arr = new int[2];
        
        for(int i=0; i<6; i++){
            if(l[i]==0) arr[1]++;
            for(int j=0; j<6; j++){
                if(l[i]==w[j])
                    arr[0]++;
            }
        }
        
        int max = 7-(arr[0]+arr[1] < 2 ? 1 : arr[0]+arr[1]);
        int min = 7-(arr[0]<2 ? 1 : arr[0]);
        return new int[]{max,min};
    }
}