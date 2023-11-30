class Main{
    public static void main(String[] args)throws Exception{
        System.out.print("long ".repeat( read()/4 ));
        System.out.print("int");
        
    }
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while((c = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		return n;
	}
}