//프로그램 설명
//행의 크기(n)와 열의 크기(m)를 차례대로 입력받아
//해당 크기의 배열(n*m)에 랜덤으로 0과 1를 배정한다
//1은 이동할 수 있는 칸, 0은 이동할 수 없는 칸으로 설정하여
//(0,0)에서 (n,m)으로 이동할 수 있는 미로를 찾아 해당 경로를 이루는 칸들은 2로 바꾸어 출력한다.
//배열이 (0,0)에서 (n,m)으로 이동할 수 없다면, "이동 할 수 없습니다"라는 메시지를 출력한다.

import java.util.*;

public class MazeStack {
    public static int m,n;
    public int[][] maze; //미로 배열
    public int[][] mark; //이미 지난 길을 1로 표시하는 배열
    public int[][] stack; //미로에서 한번 움직일때마다 움직일 방향을 선택해야하므로(현재의 위치와 마지막 움직인 방향을 저장)
    public int[][] result; 
    public static int[][] move = {{0,1}, {0,-1}, {1,0}, {-1,0}}; //동서남북

    public MazeStack(int m,int n, int[][] maze){

        this.m = m; //미로 행크기
        this.n = n; //미로 열크기
        this.maze = maze; //미로배열 ( 갔다온 곳은 1로 저장함)

        mark = new int[m+2][n+2];

        for(int i=0; i<m+2; i++){
            for(int j=0; j<n+2; j++){
                mark[i][j] = 0;
            }
        }

        stack = new int[(n+2)*(m+2)][3]; //배열의 top pointer용
    }

    void path(){

        mark[1][1] = 1; //맨 처음에 (1,1)부터 시작
        stack[0][0] = 1; // 현재 위치 행
        stack[0][1] = 1; //현재 위치 열
        stack[0][2] = 0; //마지막 움직인 방향

        int top = 0, i,j,mov, g,h; //i,j : 현재 위치, g,h : 새로 이동할 위치

        while(top>=0){

            //동서남북 모두 갈곳이 없다면, 스택에서 이전 위치를 꺼냄
            i = stack[top][0];
            j = stack[top][1];
            mov = stack[top][2];
            top--;

            while(mov<4){ //동 서 남 북
                g = i+ move[mov][0]; //새로 이동
                h = j+ move[mov][1]; //새로 이동

                if(g == m && h == n){ //(n,m)에 도착하면 실행
                    
                    result = new int[m][n];
                    
                    for(int k=0; k<m; k++){
                        for(int t=0; t<n; t++){
                            result[k][t] = maze[k+1][t+1]; 
                        }
                    }

                    for(int p=0; p<=top; p++){
                        result[stack[p][0]-1][stack[p][1]-1] =2;
                    }

                    result[i-1][j-1] = 2;
                    result[m-1][n-1] = 2;

                    
                    for(int k=0; k<m; k++){
                        for(int p = 0; p<n; p++){
                            System.out.print(result[k][p] + " ");
                        }
                        System.out.println();
                    }

                    return;
                }

                if(maze[g][h] == 1 && mark[g][h] == 0){ //미로 매열에서 0으로 간적없는(mark[g][h]가 0) 곳으로 새 좌표 이동 
                    mark[g][h] = 1; //간 곳은 1로 표시
                    top++;

                    //스택에 현재 위치, 이동한 방향 저장
                    stack[top][0] = i; //현재 행의 위치
                    stack[top][1] = j; //현재 열의 위치
                    stack[top][2] = mov; //이동한 방향
                    mov = -1; //while문 빠져나가지 않기 위해
                    i = g;
                    j = h;
                }
                mov++;
            }
        }
        //만약 (0,0)에서 (n,m)으로 갈 수 없다면
        System.out.println("(" + m +"," + n + ")" + "으로 갈 수 있는 미로 값이 없습니다.");
    }

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        //만약 배열 값을 직접 지정해준다면, 올바른 결과값이 나오지만
        // n행 m열의 배열에 무작위로 0과 1을 넣었을 때
        //(0,0)에서 (n,m)까지 도달할 수 있는 미로가 배정되지 않을 확률이 많음

        /* 
        int [][] input = {
        {1,1,1,1,1,1,1,1,1,1,1},
        {1,0,1,0,0,0,0,0,0,0,1},
        {1,0,1,0,1,0,1,1,1,0,1},
        {1,0,1,0,1,0,1,0,0,0,1},
        {1,0,1,0,1,0,1,0,1,0,1},
        {1,0,1,0,1,0,1,0,1,0,1},
        {1,0,1,0,1,0,1,0,1,1,1},
        {1,0,1,0,1,0,1,0,0,0,1},
        {1,0,1,1,1,0,1,0,1,0,1},
        {1,0,0,0,0,0,1,0,1,0,1},
        {1,1,1,1,1,1,1,1,1,1,1}
        };
        */
        
        int [][] input = new int[n+2][m+2];

        Random r = new Random();

        for(int i=0; i<=n+1; i++){
            for(int j=0; j<=m+1;j++){
                input[i][j] = 0;
            }
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=m;j++){
                input[i][j] = r.nextInt(2);
            }
        }

        input[1][1] = 1; //시작점
        input[n][m] = 1; //끝점
        

        System.out.println("길을 찾기 전 미로");

        for(int i=1; i<= n; i++){
            for(int j=1; j<= m; j++){
                System.out.print(input[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("길을 찾은 후 미로");

        MazeStack miro = new MazeStack(n,m,input);

        miro.path();

        scanner.close();

    }
}
