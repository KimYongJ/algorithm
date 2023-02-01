class Solution {
    public String solution(String id) {
        id = id.toLowerCase()
               .replaceAll("[^a-z0-9-_.]","")
               .replaceAll("[.]{2,}",".")
               .replaceAll("^[.]|[.]$","");
        if(id.length()==0)
            id = "a";
        else if(id.length()>15)
            id = id.substring(0,15).replaceAll("^[.]|[.]$","");

        if(id.length()<3){
            while(id.length()<3)
                id += id.charAt(id.length()-1);
        }
        
        return id;
    }
}