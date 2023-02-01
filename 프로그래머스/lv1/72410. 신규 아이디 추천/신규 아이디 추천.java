class Solution {
    public String solution(String id) {
        id = id.toLowerCase().replaceAll("[^a-z0-9-_.]","").replaceAll("[.]{2,}",".");
        id = deleteDot(id);
        if(id.length()==0)
            id = "a";
        else if(id.length()>15)
            id = deleteDot(id.substring(0,15));

        
        if(id.length()<3){
            while(id.length()<3)
                id += id.charAt(id.length()-1);
        }
        
        return id;
    }
    public String deleteDot(String id){
        if(id.length()>0 && id.charAt(0)=='.')
            id = id.substring(1);
        if(id.length()>0 && id.charAt(id.length()-1)=='.')
            id = id.substring(0,id.length()-1);
        return id;
    }
}