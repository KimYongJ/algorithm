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
        String str		= br.readLine();
        boolean hasL	= false;
        arr 			= new char[str.length()];
        index 			= new int[10];
        
        for(int i=0; i<arr.length; i++) 
        {
        	char c = str.charAt(i);
        	arr[i] = c;
        	if(c == '_')
        		index[LEN++] = i;
        	else if(c == 'L')
        		hasL = true;
        }
        	
        findBlank(0, 1L, hasL);
        
        System.out.println(ans);
    }

    private static void findBlank(int depth, long sum, boolean hasL) {
        if(depth == LEN) 
        {
        	if(hasL)
        		check(sum);
            return;
        }

        int idx = index[depth];
        if(arr[idx] == '_') 
        {
        	arr[idx] = '1';// 자음
            findBlank(depth + 1, sum * 20L, hasL);
            arr[idx] = '2';// 모음
            findBlank(depth + 1, sum * 5L, hasL);
            arr[idx] = 'L';
            findBlank(depth + 1, sum, true);
            arr[idx] = '_';
        }
        
    }

    private static void check (long sum) {
        int st = 0;
        int ed = 2;
        while(ed<arr.length) 
        {
            int v = 0;// 모음 갯수
            int c = 0;// 자음 갯수
            for(int i=st; i<=ed; i++) 
            {
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
        ans+=sum;
    }
}