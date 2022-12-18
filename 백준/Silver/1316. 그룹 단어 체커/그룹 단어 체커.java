import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        int result = 0;
        for(int i=0; i<len; i++){
            result += comp(br.readLine());
        }
        System.out.println(result);
    }
    public static int comp(String str){
        // 전달 받은게 합성 문자열인지 아닌지만 판별해서 맞으면 1, 아니면 0반환
        // 소문자만 나온다 97~122
        // 배열에 1이상이면  return 0 반복문이 잘 끝나면 return 1
        boolean[] arr = new boolean[26];
        int len = str.length();      
        for(int i=0; i<len; i++){
            int c = str.charAt(i)-'a';
            if(len !=  i+1){
                 if(str.charAt(i)==str.charAt(i+1))
                     continue;
            }
            if(arr[c])
                 return 0;
            else
                 arr[c] = true;
         }   
         return 1;

    }
    
}