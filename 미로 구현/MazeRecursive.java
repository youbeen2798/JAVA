//프로그램 설명
//행의 크기(n)와 열의 크기(m)를 차례대로 입력받아
//해당 크기의 배열(n*m)에 랜덤으로 0과 1를 배정한다
//1은 이동할 수 있는 칸, 0은 이동할 수 없는 칸으로 설정하여
//(0,0)에서 (n-1,m-1)으로 이동할 수 있는 미로를 찾아 해당 경로를 이루는 칸들은 2로 바꾸어 출력한다.
//배열이 (0,0)에서 (n-1,m-1)으로 이동할 수 없다면, (n-1,m-1)로 갈 수 있는 미로 값이 없습니다.라는 메시지를 출력한다.
//해당 프로그램은 stack을 이용하였음

import java.util.*;

public class MazeRecursive {

    public static int n,m;
    public static int[][] map; //최초 경로 (랜덤 값)
    public static int[][] truemap;//방문한 노드를 2로 출력해주는 배열
    ;
    public static boolean[][] visited; //해당 노드를 방문했는지 확인할 배열

    public static int[] dx = {0,1,0,-1};
    public static int[] dy = {1,0,-1,0};

    public static String answer = "NO"; //(0,0)에서 (n-1,m-1)까지 도달하면 "YES" / 도달하지 못하면 "NO"

    public static void dfs(int x,int y){

        visited[x][y] = true;
        truemap[x][y] = 2;

        int cnt = 0;
        for(int i=0; i<4; i++){ //상 하 좌 우
            cnt++;
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(0<= nx && nx < n && 0 <= ny && ny < m){
                if(visited[nx][ny] == false && map[nx][ny] == 1){
                    visited[nx][ny] = true;
                    truemap[nx][ny] = 2;

                    if(nx == n-1 && ny == m-1){

                        truemap[nx][ny] = 2;

                        //만약 미로까지 도달했으면
                        answer = "YES";
                        
                        System.out.println("( " + nx + " , " + ny + " ) " + "까지 도달하는 경로");
                        for(int k=0; k<n; k++){
                            for(int p=0; p<m; p++){
                                System.out.print(truemap[k][p] + " ");
                            }
                            System.out.println();
                        }
                        System.exit(0);   
                    }
                    dfs(nx,ny);
                }
            }
        }

        if(cnt==4){ //만약 1의 값을 가졌지만, (2,2)로 도달하는데 쓰이지 않는 경로라면
            truemap[x][y] = map[x][y];
        }
        
    }

    public static void main(String[] args){

         
        Scanner scanner = new Scanner(System.in);

        System.out.print("가로(행) 값을 입력하시오: ");
        n = scanner.nextInt(); 
        System.out.print("세로(열) 값을 입력하시오: ");
        m = scanner.nextInt();

        map = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                visited[i][j] = false;
            }
        }

        Random r = new Random();

         
        for(int i=0; i<n; i++){
            for(int j=0; j<m;j++){
                map[i][j] = r.nextInt(2); //랜덤으로 0 또는 1
            }
        }
        

        map[0][0] = 1;
        map[n-1][m-1] = 1;

        System.out.println("미로");

        for(int i=0; i<n; i++){
            for(int j =0; j<m; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        truemap =  new int[n][m]; 
        //미로 복사
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                truemap[i][j] = map[i][j];
            }
        }


        dfs(0,0);

        if(answer.equals("NO")){

            int x = n-1;
            int y = m-1;
            System.out.println("(" + x +"," + y + ")" + "으로 갈 수 있는 미로 값이 없습니다.");
        }


        scanner.close();

    }
}
