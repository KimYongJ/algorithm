import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        int[][] data = new int[len][2];
        for(int i=0; i<len; i++){
            // [0]은 층 [1]은 호실
            data[i][0] = Integer.parseInt(br.readLine());//1
            data[i][1] = Integer.parseInt(br.readLine());//3
            int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
            for(int x=0; x<data[i][0]; x++){
                for(int y=1; y<14; y++){
                    arr[y] = arr[y-1]+arr[y];
                }
            }
            System.out.println(arr[data[i][1]-1]);            
        }
    }
}