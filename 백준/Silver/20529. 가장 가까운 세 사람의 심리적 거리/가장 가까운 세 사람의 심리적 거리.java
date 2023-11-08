//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
    static int min,N;
    static String[] mbti;
    static String[] comb = new String[3];
    static StringTokenizer st;
  public static void main(String[] args)throws Exception{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringBuilder sb = new StringBuilder();
      int T = Integer.parseInt(br.readLine());
      while(T-->0){
          N = Integer.parseInt(br.readLine());
          if(N>33){
              br.readLine();
              sb.append(0).append('\n');
              continue;
          }
          mbti = new String[N];
          st = new StringTokenizer(br.readLine());
          for(int i=0; i<N; i++)
              mbti[i] = st.nextToken();
          min = Integer.MAX_VALUE;
          combination(0,0); // N개 중 3개를 뽑는 조합을 구현한다
          sb.append(min).append('\n');
      }
      System.out.println(sb.toString());
  }
  public static void combination(int depth,int start){
      if(depth==3){ // 3개를 뽑을 경우 거리를 측정한다.
          min = Math.min( min , getDist() );
          return;
      }
      if(min == 0) 
          return;
      for(int i=start; i<N; i++){
          comb[depth] = mbti[i];
          combination(depth+1,i+1);
      }
  }
  public static int getDist(){ // 거리 측정 함수
      int dist = 0;
      for(int i=0; i<4; i++){
          if(comb[0].charAt(i) != comb[1].charAt(i))dist++;
          if(comb[2].charAt(i) != comb[1].charAt(i))dist++;
          if(comb[2].charAt(i) != comb[0].charAt(i))dist++;
      }
      return dist;
  }
}