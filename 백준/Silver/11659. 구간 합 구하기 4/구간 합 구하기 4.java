// https://github.com/KimYongJ/algorithm

class Main{
    public static void main(String[] args)throws Exception{
    	StringBuilder sb = new StringBuilder();
    	Reader r = new Reader();
        
        int n = r.read();
        int m = r.read();
        
        int[] dp = new int[n+1];
        
        for(int i=1; i<=n; i++)
            dp[i] = r.read() + dp[i-1];
        
        for(int t=0; t<m; t++){
            int i = r.read();
            int j = r.read();
            sb.append( dp[j] - dp[i-1] ).append("\n");
        }
        System.out.println(sb.toString());
    }
}

// 빠른 입력을 위해 만듦
class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;
    int read() throws Exception {
        int n = 0;
        byte c;
        while ((c = get()) <= 32);
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = get()));
        return n;
    }
    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }
    byte get() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}