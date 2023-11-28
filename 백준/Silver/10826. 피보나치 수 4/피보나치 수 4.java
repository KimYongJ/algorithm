// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if(N<2) {
        	System.out.println(N);
        	return;
        }
        
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        BigInteger c = BigInteger.ZERO;

        for(int i=2; i<=N; i++){
            c = a.add(b);
            a = b;
            b = c;
        }
        System.out.println(c);
    }
}
