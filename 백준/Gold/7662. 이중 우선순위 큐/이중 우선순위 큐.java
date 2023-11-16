// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T-->0){
            TreeMap<Integer,Integer> tree = new TreeMap<>();
            int k = Integer.parseInt(br.readLine());
            while(k-->0){
                String[] cmd = br.readLine().split(" ");
                int num = Integer.parseInt(cmd[1]);
                switch(cmd[0]){
                    case "I":
                        tree.put(num,tree.getOrDefault(num, 0)+1);
                        break;
                    case "D":
                    	if(tree.size()>0) {
                    		int key = num == 1 ? tree.lastKey() : tree.firstKey();
                    		int value = tree.get(key);
                    		if(value==1) {
                    			tree.remove(key);
                    		}else {
                    			tree.put(key, value-1);
                    		}
	                        break;
                    	}
                }
            }
            String result = "EMPTY";
            if(tree.size()>0) {
            	result = tree.lastEntry().getKey()+" " + tree.firstEntry().getKey();
            }
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }
}
// 이하 시간초과 코드 
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.PriorityQueue;
//class Main{
//    public static void main(String[] args)throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        int T = Integer.parseInt(br.readLine());
//        while(T-->0){
//            DualPriority_q dq = new DualPriority_q();
//            int k = Integer.parseInt(br.readLine());
//            while(k-->0){
//                String[] cmd = br.readLine().split(" ");
//                switch(cmd[0]){
//                    case "I":
//                        dq.insert(Integer.parseInt(cmd[1]));
//                        break;
//                    case "D":
//                        if(cmd[1].charAt(0)=='-'){
//                            dq.poll_Min();
//                        }else{
//                            dq.poll_Max();
//                        }
//                        break;
//                }
//            }
//            sb.append(dq.get_Max_Min()).append("\n");
//        }
//        System.out.print(sb);
//    }
//}
//class DualPriority_q{
//    PriorityQueue<Integer> o = new PriorityQueue<>(); // 오름차순
//    PriorityQueue<Integer> n = new PriorityQueue<>((a,b)->b-a);// 내림차순
//    int size = 0; // 큐의 크기
//    
//    public String get_Max_Min(){
//        if(size==0) return "EMPTY";
//        else return n.poll() + " " + o.poll();
//    }
//    public void insert(int num){
//        o.add(num);
//        n.add(num);
//        size++;
//    }
//    public void poll_Min(){
//        if(size>0){
//            n.remove(o.poll());
//            size--;
//        }
//    }
//    public void poll_Max(){
//        if(size>0){
//            o.remove(n.poll());
//            size--;
//        }
//    }
//}
