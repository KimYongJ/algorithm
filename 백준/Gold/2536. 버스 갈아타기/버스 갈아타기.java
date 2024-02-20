import java.io.*;
import java.util.*;

public class Main {
    static class Bus {
        int id, x1, y1, x2, y2;

        Bus(int id, int x1, int y1, int x2, int y2) {
            this.id = id;
            this.x1 = Math.min(x1, x2);
            this.y1 = Math.min(y1, y2);
            this.x2 = Math.max(x1, x2);
            this.y2 = Math.max(y1, y2);
        }

        boolean intersects(Bus other) {
            return !(this.x2 < other.x1 || this.x1 > other.x2 || this.y2 < other.y1 || this.y1 > other.y2);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());
        Bus[] buses = new Bus[k + 1];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            buses[id] = new Bus(id, x1, y1, x2, y2);
        }
        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken()), 
    		sy = Integer.parseInt(st.nextToken()), 
    		dx = Integer.parseInt(st.nextToken()), 
    		dy = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[k + 1];
        int[] distance = new int[k + 1];
        Arrays.fill(distance, -1);

        for (int i = 1; i <= k; i++) {
            if (buses[i].x1 <= sx && sx <= buses[i].x2 && buses[i].y1 <= sy && sy <= buses[i].y2) {
                queue.offer(i);
                visited[i] = true;
                distance[i] = 1;
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (buses[current].x1 <= dx && dx <= buses[current].x2 && buses[current].y1 <= dy && dy <= buses[current].y2) {
                System.out.println(distance[current]);
                return;
            }

            for (int i = 1; i <= k; i++) {
                if (!visited[i] && buses[current].intersects(buses[i])) {
                    queue.offer(i);
                    visited[i] = true;
                    distance[i] = distance[current] + 1;
                }
            }
        }
        System.out.println(-1); // Should not happen for a valid input
    }
}