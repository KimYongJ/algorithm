// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
    class Solution {
        class Part{
        	String name;
        	int startTime;
        	int playTime;
        	Part(String name, int startTime,int playTime){
        		this.name = name;
        		this.startTime = startTime;
        		this.playTime = playTime;
        	}
        }
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Part> planList = new ArrayList<>(); // 조건을 Part클래스로 변환해 담는다.
        Stack<Part> st = new Stack<>();// 잠시 멈춘 과제 담는 큐
        
        public ArrayList<?> solution(String[][] plans) {
            int len = plans.length;

            
            for(int i=0; i<len; i++){
                String[] time = plans[i][1].split(":");
                int startTime = Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);// 시작시간(분)
                planList.add(new Part(plans[i][0],startTime,Integer.parseInt(plans[i][2])));
            }
            
            Collections.sort(planList,(a,b)->a.startTime-b.startTime);
            
            for(int i=0; i<len-1; i++){
            	Part nowPlan = planList.get(i);
            	Part nextPlan = planList.get(i+1);
                if(nowPlan.startTime+nowPlan.playTime>nextPlan.startTime){ // 과제시작 후 다음 과제 시간 도래
                    st.push(new Part(nowPlan.name,nowPlan.startTime, nowPlan.playTime- (nextPlan.startTime - nowPlan.startTime)));
                }else{// 과제가 끝난 경우
                    list.add(nowPlan.name);
                    int endTime = nowPlan.startTime + nowPlan.playTime;
                    int nextStartTime = nextPlan.startTime;
                    int spareTime = nextStartTime-endTime;
                    if(spareTime==0){// 과제 종료시간과 다음 과제 시작시간이 같을 경우 다음 과제 시작
                        continue;
                    }else{// 과제 종료시간과 다음 과제 시간 사이에 빈시간이 있을 경우
                        while(!st.isEmpty()){
                            Part holdData = st.pop();
                            int calculate = spareTime - holdData.playTime;
                            if(calculate >= 0){// 여유시간에 과제 1개를 끝낼 수 있는 경우
                                spareTime = calculate;
                                list.add(holdData.name);
                            }else{// 여유시간에 과제하나를 못 끝내는 경우
                                holdData.playTime -= spareTime;
                                st.push(holdData);
                                break;
                            }
                        }
                    }
                    
                }
            }

            list.add(planList.get(len-1).name); // 가장 늦은 시간에 시작하는 것은 무조건 넣어준다.
            
            while(!st.isEmpty()){// 그동안 대기중인 과제들을 순차적으로 list에 담아준다.
                Part hold = st.pop();
                list.add(hold.name);
            }
            
            
            return list;
        }
    }