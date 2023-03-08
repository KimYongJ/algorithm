// https://github.com/KimYongJ
class Solution {
    static boolean[] bool;
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] rString = new String[n];
        for(int i=0; i<n; i++){
            StringBuilder sb = new StringBuilder();
            bool = new boolean[n];
            check(n,arr1[i]);
            check(n,arr2[i]);
            for(boolean x : bool)
                if(x) sb.append("#");
                else sb.append(" ");
            rString[i] = sb.toString();
        }
        return rString;
    }
    public void check(int n,int data){
        String bin = Integer.toBinaryString(data);
        
        while(n!=bin.length()) bin = "0"+bin;
        
        for(int x=0; x<bin.length(); x++)
            if(bin.charAt(x)=='1') bool[x]=true;
    }
}