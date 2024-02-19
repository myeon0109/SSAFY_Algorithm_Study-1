import java.util.*;

class Solution {
    static ArrayList<Integer>[] graph;
    static int min;
    public int solution(int n, int[][] wires) {
        graph = new ArrayList[n+1];
        min = Integer.MAX_VALUE;
        
        for(int i =1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i<wires.length; i++){
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            graph[v1].add(v2);
            graph[v2].add(v1);
        }
        
        for(int i=0; i<wires.length; i++){
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            
            boolean[] visit = new boolean[n+1];
            
            graph[v1].remove(Integer.valueOf(v2));
            graph[v2].remove(Integer.valueOf(v1));
            
            int cnt = DFS(1, visit);
                
            int diff = Math.abs(cnt - (n-cnt));
            min = Math.min(diff,min);
            
            graph[v1].add(v2);
            graph[v2].add(v1);
        }
        return min;
    }
    public static int DFS(int num, boolean[] visit){
        visit[num]= true;
        int cnt = 1;
        
        for(int next : graph[num]){
            if(!visit[next]){
                cnt += DFS(next, visit);
            }
        }
        return cnt;
    }
}
