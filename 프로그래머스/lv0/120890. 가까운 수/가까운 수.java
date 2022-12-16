import java.util.*;
class Solution {
    public int solution(int[] array, int n) {
        if(same(array,n)) return n;
        int len = array.length+1;
        int[] narray = new int[len];
        int index =0;
        System.arraycopy(array,0,narray,0,len-1);
        narray[len-1]=n;
        Arrays.sort(narray);
        for(int i=0;i<len;i++){
            if(n==narray[i]){
                index = i;
                break;
            }
        }
        if(index==0){ return narray[1]; }
        else if(index==len-1) {return narray[len-2];}
        else{
            if(narray[index]-narray[index-1]<=narray[index+1]-narray[index]){
                return narray[index-1];}
            else{
                return narray[index+1];}
        }
        
    }

    private static boolean same(int[] array, int n){
        for(int i=0;i<array.length;i++){
            if(n==array[i]) return true;
        }
        return false;
    }
}