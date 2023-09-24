class Main{
    public static void main(String[] args)throws Exception{        
        int T = read(), idx = 0,result = 0,data = 0;
        int[] arr = new int[T];

        while(T-->0){
            
            if((data=read())!=0)
                result +=(arr[idx++] = data);
            else
                result -= arr[--idx];
            
        }
        
        System.out.println(result);
        
    }
    static int read() throws Exception { // 숫자 입력된 것 
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
