class Solution{
    public static void main(String[] args){
        for(int x = 0; x<5; x++){
            for(int i=0; i<5; i++)
				if(x==i)
                    System.out.print('#');
                else
                	System.out.print('+');
            System.out.println();
        }
    }
}