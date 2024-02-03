import java.util.HashMap;
class Solution 
{
    
    int answer, len, gift_number[][];
    
    HashMap<String, Integer> rate, position;
    
    public int solution(String[] friends, String[] gifts) 
    {
        rate        = new HashMap<>();
        position    = new HashMap<>();     
        len         = friends.length;
        answer      = 0;
        gift_number = new int[len][len];
        
        for(int i=0; i<len; i++)
        {
            position.put(friends[i], i);    // 이름의 인덱스를 저장
            rate.put(friends[i],0);         // 이름에 대한 선물 비율을 0으로 초기화
        }
        
        for(String gift : gifts)
        {
            String[] people = gift.split(" ");
            // 선물 비율을 구한다. 이 선물비율은 거래를 한적이 없거나, 주고 받은 횟수가 같을 때 사용된다.
            rate.put(people[0], rate.get(people[0])+1);
            rate.put(people[1], rate.get(people[1])-1);
            
            // 주고 받은 횟수를 구해야 한다.
            gift_number[position.get(people[0])][position.get(people[1])] += 1;
        }
        
        for(int i=0; i<len; i++)
        {
            int gift = 0;
            for(int j=0; j<len; j++)
            {
                if(i==j) continue;
                int giftn1 = gift_number[i][j];     // 서로 주고 받은 선물 갯수를 가져온다.
                int giftn2 = gift_number[j][i];     // 서로 주고 받은 선물 갯수를 가져온다.
                int rate1 = rate.get(friends[i]);   // 선물 비율을 가져온다.
                int rate2 = rate.get(friends[j]);   // 선물 비율을 가져온다.
                if(giftn1 != 0 || giftn2 != 0)
                {                                   // 선물을 주고 받은 적이 있는 경우
                    if(giftn1 == giftn2)
                    {                               // 선물을 주고 받은 갯수가 같은 경우 비율로 계싼
                        if( rate1 > rate2 )
                            gift++;
                    }else if(giftn1 > giftn2)       // 선물을 더 많이 준경우
                        gift++; 
                }
                else
                {                                   // 선물을 주고 받은 적이 없다면 비율 계산
                    if( rate1 > rate2 )
                        gift++;
                }
                
            }
            answer = Math.max(answer, gift);
        }
        
        return answer;
    }
}