class Solution {
    public int solution(int[] num_list) {
        String a = "";
        String b = "";
        for(int x : num_list){
            if(x%2==0){
                a+=x;
            }else{
                b+=x;
            }
        }
        return Integer.parseInt(a)+Integer.parseInt(b); 
    }
}