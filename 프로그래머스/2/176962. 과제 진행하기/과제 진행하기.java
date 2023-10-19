// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
class Solution {
    
    ArrayList<String> list = new ArrayList<>();
    HashMap<Integer,String> map = new HashMap<>(); // 키 : 시작시간(분) , 값 : 이름
    Stack<int[]> st = new Stack<>();// 잠시 멈춘 과제 담는 큐 // // 순서 : 시작시간(분) / 플레이타임
    public ArrayList<?> solution(String[][] plans) {
        int len = plans.length;
        int[][] plan = new int[len][2]; // 순서 : 시작시간(분) / 플레이타임
        
        for(int i=0; i<len; i++){
            String[] time = plans[i][1].split(":");
            plan[i][0] = Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);// 시작시간(분)
            plan[i][1] = Integer.parseInt(plans[i][2]); // 플레이 타임
            map.put(plan[i][0] , plans[i][0]);
        }
        
        Arrays.sort(plan,(a,b)->a[0]-b[0]);
        
        for(int i=0; i<len-1; i++){
            if(plan[i][0]+plan[i][1]>plan[i+1][0]){ // 과제시작 후 다음 과제 시간 도래
                st.push(new int[]{plan[i][0], plan[i][1]-(plan[i+1][0]-plan[i][0])});
            }else{// 과제가 끝난 경우
                list.add(map.get(plan[i][0]));
                int endTime = plan[i][0] + plan[i][1];
                int nextStartTime = plan[i+1][0];
                int spareTime = nextStartTime-endTime;
                if(spareTime==0){// 과제 종료시간과 다음 과제 시작시간이 같을 경우 다음 과제 시작
                    continue;
                }else{// 과제 종료시간과 다음 과제 시간 사이에 빈시간이 있을 경우
                    while(!st.isEmpty()){
                        int[] holdData = st.pop();
                        int calculate = spareTime - holdData[1];
                        if(calculate >= 0){// 여유시간에 과제 1개를 끝낼 수 있는 경우
                            spareTime = calculate;
                            list.add(map.get(holdData[0]));
                        }else{// 여유시간에 과제하나를 못 끝내는 경우
                            holdData[1] -= spareTime;
                            st.push(holdData);
                            break;
                        }
                    }
                }
                
            }
        }
        list.add(map.get(plan[len-1][0])); // 가장 늦은 시간에 시작하는 것은 무조건 넣어준다.
        
        while(!st.isEmpty()){// 그동안 대기중인 과제들을 순차적으로 list에 담아준다.
            int[] hold = st.pop();
            list.add(map.get(hold[0]));
        }
        
        
        return list;
    }
}