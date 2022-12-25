import java.io.*;
import java.util.stream.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] result = new int[arr[0]][arr[1]];
        for(int x=0; x<2;x++){
            for(int i=0; i<arr[0]; i++){
                int[] plus = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                for(int j=0; j<arr[1]; j++){
                    result[i][j] +=plus[j];
                }
            }
        }
        for(int y=0; y<arr[0]; y++){
            for(int z=0; z<arr[1]; z++){
                sb.append(result[y][z]).append(" ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb);
    }
}