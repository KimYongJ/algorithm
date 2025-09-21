//https://www.acmicpc.net/problem/20006
//1초 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int p = Integer.parseInt(st.nextToken());// 플레이어 수(1<=300)
		int m = Integer.parseInt(st.nextToken());// 방의 정원(1<=300)
		List<Room> list = new ArrayList<>();
		
		while(p-->0)
		{
			st = new StringTokenizer(br.readLine());
			int level = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			boolean isMatch = false;
			
			for(Room room : list)
			{
				if(room.list.size() < m && room.min <= level && level <= room.max)
				{
					room.add(new Player(level, name));
					isMatch = true;
					break;
				}
			}
			
			if(!isMatch)
				list.add(new Room(level - 10, level + 10, new Player(level, name)));
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(Room room : list)
		{
			sb.append(room.list.size() == m ? "Started!" : "Waiting!").append('\n');
			
			Collections.sort(room.list);
			
			for(Player player : room.list)
				sb.append(player.level).append(' ').append(player.name).append('\n');
			
		}
		
		System.out.print(sb);
	}
	static class Room{
		int min, max;
		List<Player> list;
		Room(int min, int max, Player player){
			this.list = new ArrayList<>();
			this.min = min;
			this.max = max;
			this.list.add(player);
		}
		void add(Player player) {
			this.list.add(player);
		}
	}
	static class Player implements Comparable<Player>{
		int level;
		String name;
		Player(int l, String n){
			level = l;
			name = n;
		}
		@Override
		public int compareTo(Player p) {
			return name.compareTo(p.name);
		}
	}
}