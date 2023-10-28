// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
import java.util.HashMap;
class Solution {
    
    private HashMap<String,ArrayList<Integer>> db = new HashMap<>();
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        db_setting(info);// 데이터 베이스 생성
        for(ArrayList<Integer> list : db.values())// 벨류의 오름차순 정렬
            list.sort(null);
        excute(answer,query);// 쿼리 실행
        
        return answer;
    }
    public void excute(int[] answer,String[] query){
        for(int i=0; i<query.length; i++){
            String[] q = query[i].replaceAll(" and ","").split(" ");// 쿼리를 명령어+점수 형태로 간소화 시킨다. ex) javabackendjuniorpizza 100
            int score = Integer.parseInt(q[1]);
            ArrayList<Integer> list = db.get(q[0]);
            if(list!=null){
                // 이하 이분탐색 시작
                int left = 0;
                int right = list.size();
                while(left<right){
                    int mid = (left + right)/2;
                    if(list.get(mid)<score){
                        left = mid+1;
                    }else{
                        right = mid;
                    }
                }
                answer[i] = list.size() - left;
            }
        }
    }
    public void db_setting(String[] info){
        for(int i=0; i<info.length; i++){
            String[] str = info[i].split(" ");
            for(int j=0; j<=4; j++){// 4개 중 0부터~4개 까지 뽑는 조합 구하는 코드
                boolean[] visit = new boolean[4];
                combination(str,j,0,visit);
            }
        }
    }
    public void combination(String[] info,int r,int start,boolean[] visit){
        if(r==0){
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<4; i++){
                if(visit[i]) sb.append(info[i]);
                else sb.append("-");
            }
            String str = sb.toString();
            ArrayList<Integer> list = db.getOrDefault(str,new ArrayList<Integer>());
            list.add(Integer.parseInt(info[4]));
            db.put(str,list); //구해진 조합을 해시맵에 [명령어 : 점수] 순으로 넣는다.
            return;
        }
        for(int i=start; i<4; i++){
            visit[i] = true;
            combination(info,r-1,i+1,visit);
            visit[i] = false;
        }
    }
}




// 이하 실패코드 
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.Map;
// class Solution {
    
//     ArrayList<HashMap<Integer,HashSet<String>>> DataBase = new ArrayList<>();

//     public int[] solution(String[] info, String[] query) {
//         int[] answer = new int[query.length];
        
//         setting(info);// 데이터 베이스 생성
//         excute(answer,query);// 쿼리 실행
        
//         return answer;
//     }
//     public void excute(int[] answer,String[] query){
//         for(int i=0; i<answer.length; i++){// 쿼리를 하나씩 돌린다.
//             String[] q = query[i].split(" ");// 쿼리 명령어를 split으로 나눠서 배열로 담는다.
//             int cnt = 0, len = q.length;
//             int score = Integer.parseInt(q[len-1]);// 몇점이상을 원하는지 뽑는다.
            
//             Loop:
//             for(int d=0; d<DataBase.size(); d++){
//                 for(Map.Entry<Integer,HashSet<String>>entry : DataBase.get(d).entrySet()){// 데이터 검색
//                     if(entry.getKey()>=score){// 데이터가 원하는 점수 이상인지 체크
//                         HashSet<String> data = entry.getValue();// 사용자가 입력한 4가지 정보
//                         for(int j=0; j<len-1; j++){// 쿼리의 키워드를 탐색한다.
//                             char cmd = q[j].charAt(0);
//                             if(cmd=='a' || cmd=='-'){continue;}// and나 - 이면 다음 키워드 탐색
//                             if(!data.contains(q[j])){// 데이터베이스에 없으면 데이터베이스의 다음 행으로 강제 이동
//                                 continue Loop;
//                             }
//                         }
//                         cnt++;// 조건에 부함한다면 cnt+1
//                     }
//                 }
//             }
//             answer[i] = cnt;
//         }
//     }
//     public void setting(String[] info){ // dataBase 생성
//         for(String datalist : info){
//             String[] data = datalist.split(" ");
//             DataBase.add(new HashMap<>(){{
//                 put(Integer.parseInt(data[4]),new HashSet<>(){{
//                     add(data[0]);
//                     add(data[1]);
//                     add(data[2]);
//                     add(data[3]);
//                 }});
//             }});
//         }
//     }
// }