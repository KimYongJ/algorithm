class Solution {
    public String solution(String id) {
        return new KAKAO(id)
                            .level_1()
                            .level_2()
                            .level_3()
                            .level_4()
                            .level_5()
                            .level_6()
                            .level_7()
                            .toString();
    }
    public class KAKAO{
        private String id;
        
        public KAKAO(String id){
            this.id = id;
        }
        public KAKAO level_1(){
            id = id.toLowerCase();
            return this;
        }
        public KAKAO level_2(){
            id = id.replaceAll("[^a-z0-9-_.]","");
            return this;
        }
        public KAKAO level_3(){
            id = id.replaceAll("[.]{2,}",".");
            return this;
        }
        public KAKAO level_4(){
            id = id.replaceAll("^[.]|[.]$","");
            return this;
        }
       public KAKAO level_5(){
            if(id.length()==0)
                    id = "a";
            return this;
        }
       public KAKAO level_6(){
            if(id.length()>15)
                    id = id.substring(0,15).replaceAll("^[.]|[.]$","");
            return this;
        }
        public KAKAO level_7(){
            if(id.length()<3)
                while(id.length()<3)
                    id += id.charAt(id.length()-1);
            return this;
        }
        public String toString(){
            return id;
        }
    }
}