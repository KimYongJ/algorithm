// https://github.com/KimYongJ/algorithm
import java.util.HashSet;
import java.util.Arrays;
class Solution {
    HashSet<String> set = new HashSet<>(); // * 모양이 표시될좌표를 문자열 형태로 저장하여 중복을 방지한다.
    StringBuilder sb = new StringBuilder();// 문자열 생성시 빠른 연산을 위해 사용할 스트링 빌더이다.
    
    long maxX = Long.MIN_VALUE, maxY = Long.MIN_VALUE,
         minX = Long.MAX_VALUE, minY = Long.MAX_VALUE;// x,y좌표의 최대 최소값을 각각 구한다.
    
    public void setDot(int[] line1, int[] line2){// 두점의 교차점을 구하는 함수
        long d = (long)line1[0]*line2[1] - (long)line1[1]*line2[0];// 분모를 구한다.
        if(d==0)// 분모가 0일 경우(평행일 경우) 리턴
            return;
        double x = (line1[1]*(long)line2[2] - line1[2]*(long)line2[1]) / (double)d;// 교차시 x좌표
        double y = (line1[2]*(long)line2[0] - line1[0]*(long)line2[2]) / (double)d;// 교차시 y좌표
        if(isInteger(x) && isInteger(y)){ // 둘다 정수일 경우 이하 실행
            long x1 = (long)x , y1 = (long)y; // 둘다 정수로 변경한다.
            maxX = Math.max(maxX,x1);// x의 최대 좌표를 미리 구해 놓는다.
            maxY = Math.max(maxY,y1);// y의 최대 좌표를 미리 구해 놓는다.
            minX = Math.min(minX,x1);// x의 최소 좌표를 미리 구해 놓는다.
            minY = Math.min(minY,y1);// y의 최소 좌표를 미리 구해 놓는다.
            sb.append(x1+"").append(",").append(y1+"");
            set.add(sb.toString()); // 좌표를 문자열로 변환
            sb.setLength(0);// 빌더 초기화
        }
    }
    public boolean isInteger(double x){// dobule형이 정수가 맞는지 확인하는 코드
        return x==(long)x;
    }
    
    public String[] solution(int[][] line) {
        int len = line.length;
        for(int x=0; x<len-1; x++)
            for(int i=x; i<len-1; i++)
                for(int j=i+1; j<len; j++)
                    setDot(line[i],line[j]);// 두 직선의 교점 좌표를 set에 저장하는 함수
        
        long xlen = maxX-minX+1;// 최대 x의 길이를 구한다.
        long ylen = maxY-minY+1;// 최대 y의 길이를 구한다.
        
        char[][] arr = new char[(int)ylen][(int)xlen];
        for(int i=0; i<(int)ylen; i++)
            Arrays.fill(arr[i],'.');// 모든 배열을 .으로 초기화 한다.
        
        for(String s : set){
            String[] xy = s.split(",");
            long x = (Long.parseLong(xy[0])-minX);// x좌표를 다시 연산한다.
            long y = (Long.parseLong(xy[1])-minY);// y좌표를 다시 연산한다.
            arr[(int)y][(int)x]='*';// 해당 좌표에 *모양을 넣어 준다.
        }
        
        String[] result = new String[(int)ylen];
        for(int i=0; i<(int)ylen; i++)
            result[(int)ylen-1-i] = new String(arr[i]);// arr 배열에 담긴 값이 뒤집어져있기 때문에 값을 넣으면서 위아래 반대로 넣어준다.
        
        return result;
    }
}