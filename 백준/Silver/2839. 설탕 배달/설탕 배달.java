class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
    public static void main(String[] args)throws Exception{
        int k = read();
        int three = 0 ;
        while(true){
            if(k%5==0){
                System.out.println(k/5+three);
                return;
            }else if(k<0){
                System.out.println(-1);
                return;
            }
            k-=3;
            three++;          
        }
    }
}