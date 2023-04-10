import java.util.Date;
import java.text.SimpleDateFormat;
class Main{
    public static void main(String[] args)throws Exception{
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sf.format(date));
    }
}