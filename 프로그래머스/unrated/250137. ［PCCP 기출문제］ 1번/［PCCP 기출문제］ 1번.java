// https://github.com/KimYongJ/algorithm
class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int energy = health;// 현재 에너지 
        int max = attacks[attacks.length-1][0]; // 마지막 공격의 시간
        int time = 0; // 체력 회복 중인 시간
        int atIdx = 0;// 공격배열의 인덱스 
        for(int i=0; i<=max; i++){ // 마지막 공격 시간 까지 반복
            if(attacks[atIdx][0] == i){// 공격 받았다면 
                energy -= attacks[atIdx++][1];// 에너지를 깍음
                if(energy<=0){ // 에너지가 0이하면 -1 리턴
                    energy = -1;
                    break;
                } 
                time = 0;// 체력 회복 중인 시간 초기화
            }else{
                energy += bandage[1];// 에너지 추가
                if(++time == bandage[0]) {
                    energy += bandage[2];// 시간되면 추가 회복량 추가
                    time = 0; // 연속 성공 시간 초기화
                }
                if(energy>health) energy = health;// 최대 체력을 넘으면 최대채력으로 초기화 
            }
        }
        return energy;
    }
}