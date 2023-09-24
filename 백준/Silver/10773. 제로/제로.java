class Main{
    public static void main(String[] args)throws Exception{        
        int T = read();
        int[] arr = new int[T];
        int idx = 0;
        while(T-->0){
            int data = read();
            
            if(data!=0){
                arr[idx++] = data;
            }else{
                arr[--idx] = 0;
            }
            
        }
        
        int result = 0;
        for(int data : arr){
            result += data;
            if(data==0) 
                break;
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
