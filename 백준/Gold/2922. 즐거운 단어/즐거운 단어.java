// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static final String vowel = "AEIOU";
    static long ans = 0;
    static int LEN;
    static int index[];
    static char[] arr;
    
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = br.readLine().toCharArray();
        index = new int[10];
        
        for(int i=0; i<arr.length; i++) 
        	if(arr[i] == '_')
        		index[LEN++] = i;
        	
        findBlank(0,0, 1L);
        
        System.out.println(ans);
    }

    private static void findBlank(int i, int depth, long sum) {
        if(depth == LEN) 
        {
        	check(sum);
            return;
        }

        if(arr[index[i]] == '_') 
        {
        	arr[index[i]] = '1';// 자음
            findBlank(i+1, depth + 1, sum * 20L);
            arr[index[i]] = '2';// 모음
            findBlank(i+1, depth + 1, sum * 5L);
            arr[index[i]] = 'L';
            findBlank(i+1, depth + 1, sum);
            arr[index[i]] = '_';
        }
        
    }

    private static void check (long sum) {
        int st = 0;
        int ed = 2;
        boolean isL = false;// L값 체크
        while(ed<arr.length) 
        {
            int v = 0;// 모음 갯수
            int c = 0;// 자음 갯수
            for(int i=st; i<=ed; i++) 
            {
                 if(arr[i] == 'L') 
                	 isL = true;
                 if(vowel.indexOf(arr[i])>=0 || arr[i] == '2')
                	 v++;
                 else 
                	 c++;
            }

            if(v >=3 || c>=3)
            	return;
            st++;
            ed++;
        }
        // L값이 존재하며 자음 모음에 연속성 없음
        if(isL)
            ans+=sum;
    }
}