class Solution {
    public int solution(int[] arr1, int[] arr2) {
        int ans = 0;
        if(arr1.length > arr2.length){
            ans = 1;
        }else if(arr1.length < arr2.length){
            ans = -1;
        }else{
            int sum = 0;
            for(int i=0; i<arr1.length; i++){
                sum += arr1[i];
                sum -= arr2[i];
            }
            if(sum>0){
                ans = 1;
            }else if(sum < 0){
                ans = -1;
            }
        }
        
        return ans;
    }
}