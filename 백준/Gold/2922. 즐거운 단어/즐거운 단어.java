// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static final String vowel = "AEIOU";
    static char arr[];
    
    public static void main(String[] args)throws Exception {
        arr = new BufferedReader(new InputStreamReader(System.in)).readLine().toCharArray();
        System.out.print( DFS(0, 0, 0, 0) );
    }

    private static long DFS(int idx, int v, int c, int hasL) {
    	if(v>=3 || c>=3)
    		return 0;
    	
        if(idx == arr.length) 
        	return hasL;
        
        long cnt = 0;
        if(arr[idx] == '_') 
        {
        	cnt += DFS(idx + 1, v+1, 0, hasL) * 5; // 모음
        	cnt += DFS(idx + 1, 0, c+1, hasL) * 20; // 자음
        	cnt += DFS(idx + 1, 0, c+1, 1); // L을 강제로 추가
        }
        else if(vowel.indexOf(arr[idx]) >= 0)
        	cnt += DFS(idx + 1, v+1, 0, hasL);
        else
        	cnt += DFS(idx + 1, 0, c+1, arr[idx] == 'L' ? 1 : hasL);
        
        return cnt;
    }

}