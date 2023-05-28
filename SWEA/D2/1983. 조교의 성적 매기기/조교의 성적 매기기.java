import java.io.*;
import java.util.*;

class Solution{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int r = Integer.parseInt(st.nextToken());
        
        for(int i=1; i<=r; i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());
            Double[] student = new Double[l];
            for(int j=0; j<l; j++){
                st = new StringTokenizer(br.readLine());
                student[j] = Integer.parseInt(st.nextToken()) * 0.35
                    			+Integer.parseInt(st.nextToken()) * 0.45
                     		   +Integer.parseInt(st.nextToken()) * 0.2;
            }
            // 학생들 점수를 전달하면 , 학점 출력
            sb.append("#").append(i).append(" ")
                .append(find(student,idx-1,l/10)).append("\n");
        }
        System.out.println(sb);
    }
    public static String find(Double[] arr, int idx, int same_number){
        String[] result = {"A+","A0","A-","B+","B0","B-","C+","C0","C-","D0"};
        double base = arr[idx];
        Arrays.sort(arr, Collections.reverseOrder());
        int i = 0;
        for(; i<arr.length; i++)
            if(arr[i]==base)
                break;
        return result[i/same_number];
    }
}