import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<>();
        int T = Integer.parseInt(br.readLine());
        while(T-->0){
            int data = Integer.parseInt(br.readLine());
            
            if(data==0){
                list.remove(list.size()-1);
            }else{
                list.add(data);
            }
        }
        int result = 0;
        for(int data : list){
            result += data;
        }
        System.out.println(result);
    }
}