// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
class Main{
	
	static int[] parent;
	public static void main(String[] args)throws Exception{
		int N = read(); // 사람수
		int M = read(); // 파티 수
		
		ArrayList<int[]> partyList = new ArrayList<>();
		parent = new int[N+1]; // 부모노드를 담을 배열 선언
		for(int i=1; i<N+1; i++)
			parent[i] = i;
		

		int r = read();
		for(int i=0; i<r; i++) { 
			parent[read()] = 0; // 진실을 알고 있다면 부모가 -1값임 
		}
		
		for(int i=0; i<M; i++) {// 파티에 따른 입장 사람들
			int[] arr = new int[read()];
			for(int j=0; j<arr.length; j++) {
				arr[j] = read();
			}

			partyList.add(arr); // 파티 리스트를 담는다.

			for(int j=0; j<arr.length-1; j++) {
				int aParent = getParent(arr[j]);
				int bParent = getParent(arr[j+1]);
				
				if(aParent<bParent) { // a부모노드가 더 작으면 b부모노드를 갖고있는 모든 값을 a로 바꾼다.
					for(int p=0; p<parent.length; p++) {
						if(parent[p] == bParent) {
							parent[p] = aParent;
						}
					}
				}else {
					for(int p=0; p<parent.length; p++) {
						if(parent[p] == aParent) {
							parent[p] = bParent;
						}
					}
				}
			}
		}
		
		int lie = 0;
		Loop:
		for(int i=0; i<partyList.size(); i++) {
			for(int people : partyList.get(i)) { // 파티를 하나씩 순회한다.
				if(getParent(people) == 0)// 부모노드가 하나라도 -1이면 진실을 알고 있으므로 다음 파티로 넘어간다.
					continue Loop;
			}
			lie++;
		}
		System.out.println(lie);
	}	
	// 부모 노드를 찾아 주는 함수 
	public static int getParent(int x) {
		if(parent[x]==x) return x;
		return getParent(parent[x]);
	}
    private static int read() throws Exception{
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32)
	    	n = (n << 3) + (n << 1) + (c & 15);
	    return n;
	}
}