//프로그램 설명
// 정사각형 속 빈칸의 숫자들을 사용자와 컴퓨터가 번갈아가며 빙고 게임을 하여 승자를 결정한다.
// 사용자가 이겼으면 "축하합니다. 게임을 승리하였습니다" 라는 메시지를 출력하며,
// 반대로 컴퓨터가 이기면 "흑흑 게임을 졌습니다"라는 문자열을 출력한다.
// 게임이 끝나면 다시 게임을 실행할 것인지 의사를 물어보는데 사용자가 y, Y, YES, yes를 입력하면, 다시 컴퓨터와 빙고 게임을 한다.
// 만약 게임을 종료한다면, 게임을 종료한다는 메시지와 함께 프로그램이 종료된다.

import java.util.*;

class TicTacToe{

    Scanner scanner = new Scanner(System.in);
    static int x;
    static int y;
    static int num;
    static int cnt = 0;

    public static void print_array(char [][]array){
        int cnt = 0;
        for(int i=0; i<array.length; i++){
            for(int j = 0; j<array[i].length; j++){
                cnt++;
                if(array[i][j] == ' '){
                    System.out.print(cnt);
                }
                else{
                    System.out.print(array[i][j]);
                }
                if(j == array[i].length -1){
                    continue;
                }
                else{
                    System.out.print(" ㅣ ");
                }
            }
            System.out.println();
            if(i != array.length -1){
                System.out.println("------------");
            }
        }
    }

    //게임의 중단 유무 ( 컴퓨터와 사용자 중 이긴 사람이 있는지 확인)
    public static boolean winner(char[][] arr){
        //가로 확인
        for(int i=0; i<arr.length; i++){  //3이 아니라 arr.length인 이유는 나중에 빙고칸이 많아질 경우를 대비하기 위함임!
            int my_cnt =0;
            int com_cnt = 0;
            for(int j=0; j<arr.length;j++){
                if(arr[i][j] == 'O'){
                    my_cnt++;
                }
                else if(arr[i][j] == 'X'){
                    com_cnt++;
                }
            }

            if(my_cnt == arr.length){ //mt_cnt == 3이라면
                System.out.println("축하합니다! 게임을 승리하였습니다.");
                return true;
            }
            else if(com_cnt == arr.length){
                System.out.println("흑흑 게임을 졌습니다.");
                return true;
            }
        }

        // 세로 확인
        for(int i=0; i<arr.length; i++){ //3이 아니라 arr.length인 이유는 나중에 빙고칸이 많아질 경우를 대비하기 위함임!
            int my_cnt = 0;
            int com_cnt = 0;
            for(int j=0; j<arr.length; j++){
                if(arr[j][i] == 'O'){
                    my_cnt++;
                }
                else if(arr[j][i] == 'X'){
                    com_cnt++;
                }
            }

 

            if(my_cnt == arr.length){
                System.out.println("축하합니다 게임을 승리하였습니다.");
                return true;
            }
            else if(com_cnt == arr.length){
                System.out.println("흑흑 게임을 졌습니다.");
                return true;
            }
        }

        //왼쪽 위에서 오른쪽 아래로 대각선 확인
        int my_cnt = 0;
        int com_cnt = 0;
        for(int i=0; i<arr.length; i++){ // i = 0 1 2
            if(arr[i][i] == 'O'){
                my_cnt++;
            }
            else if(arr[i][i] == 'X'){
                com_cnt++;
            }
        }
        if(my_cnt == arr.length){
            System.out.println("축하합니다! 게임을 승리하였습니다.");
            return true;
        }
        else if(com_cnt == arr.length){
            System.out.println("흑흑 게임을 졌습니다.");
            return true;
        }


        //오른쪽 위에서 왼쪽 아래로 대각선 확인
        
        my_cnt = 0;
        com_cnt = 0;
        for(int i=0; i<arr.length; i++){
            if(arr[i][arr.length-1- i] == 'O'){
                my_cnt++;
            }
            else if(arr[i][arr.length-i - 1] == 'X'){
                com_cnt++;
            }
        }

        if(my_cnt == arr.length){
            System.out.println("축하합니다. 게임을 승리하였습니다.");
            return true;
        }
        else if(com_cnt == arr.length){
            System.out.println("흑흑 게임을 졌습니다.");
            return true;
        }

        return false;
    }
    public static void main(String[] args){

        boolean again = false; //게임을 재실행할건지 물어보는 불리안 ( 승자가 생긴 후 사용자의 결정에 따라 달라짐 )
        char[][] arr = new char[3][3];

        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[i].length; j++){
                arr[i][j] = ' ';
            }
        }

        System.out.println(" " + 1 + " | " + 2 + " | " + 3 + " ");
        System.out.println("------------");
        System.out.println(" " + 4 + " | " + 5 + " | " + 6 + " ");
        System.out.println("------------");
        System.out.println(" " + 7 + " | " + 8 + " | " + 9 + " ");


        int iter = 0;
        while(true){

            if(again){ //게임이 재실행될때 쓰이는 코드
                iter = 0;
                arr = new char[3][3];

                for(int i=0; i<arr.length; i++){
                    for(int j=0; j<arr[i].length; j++){
                        arr[i][j] = ' ';
                    }
                }
        
                System.out.println(" " + 1 + " | " + 2 + " | " + 3 + " ");
                System.out.println("------------");
                System.out.println(" " + 4 + " | " + 5 + " | " + 6 + " ");
                System.out.println("------------");
                System.out.println(" " + 7 + " | " + 8 + " | " + 9 + " ");
                again = false;
            }


            System.out.println();
            System.out.print("1~9 사이 빈칸을 선택하세요: ");

            TicTacToe tictactoe = new TicTacToe();

            while(true){
                try{
                    num = tictactoe.scanner.nextInt();
                    break;
                }
                catch(InputMismatchException e){
                    System.out.println("숫자를 입력하세요");
                    break;
                }
            }

            switch(num){
                case 1:
                    x = 0; y = 0;
                    break;
                case 2:
                    x = 0; y = 1;
                    break;
                case 3:
                    x = 0; y = 2;
                    break;
                case 4:
                    x = 1; y = 0;
                    break;
                case 5:
                    x = 1; y = 1;
                    break;
                case 6:
                    x = 1; y = 2;
                    break;
                case 7:
                    x = 2; y = 0;
                    break;
                case 8:
                    x = 2; y = 1;
                    break;
                case 9:
                    x = 2; y = 2;
                    break;
                default:
                    System.out.println("1부터 9까지의 값만 입력하시오");
                    continue;
            }

            if(arr[x][y] != ' '){
                System.out.println("빈 칸의 값을 입력해주십시오");
                continue;
            }
            
            arr[x][y] =  'O';
            iter++;
            print_array(arr); //빙고 게임판 그리기
            if(winner(arr)){ //사용자가 이겼는지 확인

                while(true){
                    System.out.println("게임을 다시 실행하시겠습니까?(Y/N)");
                
                    String s = tictactoe.scanner.next();
                    char input = s.charAt(0);

                    if(input == 'y' || input == 'Y' || s == "yes" || s == "YES"){
                        again = true;
                        break;
                    }
                    else if(input == 'N' || input == 'n' || s == "NO" || s == "YES"){
                        break;
                    }
                }   
               
                if(again){
                    continue;
                }
                else{
                System.out.println("게임을 종료합니다.");
                System.exit(0);
                }
            }

            try{
                Thread.sleep(1500); //1.5초 기다리기
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }

            if(iter == 9){
                    System.out.println("승자가 없습니다.");

                    while(true){
                        System.out.println("게임을 다시 실행하시겠습니까?(Y/N)");
                    
                        String s = tictactoe.scanner.next();
                        char input = s.charAt(0);
    
                        if(input == 'y' || input == 'Y' || s == "yes" || s == "YES"){
                            again = true;
                            break;
                        }
                        else if(input == 'N' || input == 'n' || s == "NO" || s == "YES"){
                            break;
                        }
                    }
                    if(again){
                        continue;
                    }
                    else{
                    System.exit(0);
                    }

            }
            System.out.println("컴퓨터 차례");
            
            while(true){

                Random random = new Random();

                num = random.nextInt(8) + 1; //1부터 9까지의 숫자

                switch(num){
                    case 1:
                        x = 0; y = 0;
                        break;
                    case 2:
                        x = 0; y = 1;
                        break;
                    case 3:
                        x = 0; y = 2;
                        break;
                    case 4:
                        x = 1; y = 0;
                        break;
                    case 5:
                        x = 1; y = 1;
                        break;
                    case 6:
                        x = 1; y = 2;
                        break;
                    case 7:
                        x = 2; y = 0;
                        break;
                    case 8:
                        x = 2; y = 1;
                        break;
                    case 9:
                        x = 2; y = 2;
                        break;
                }

                if(arr[x][y] != ' '){
                    continue;               
                }
                else{
                    arr[x][y] = 'X';
                    break;
                }
            }

            iter++;
            print_array(arr); //빙고판 그리기

            if(winner(arr)){ //컴퓨터가 이겼는지 확인

                while(true){
                    System.out.println("게임을 다시 실행하시겠습니까?(Y/N)");
                
                    String s = tictactoe.scanner.next();
                    char input = s.charAt(0);

                    if(input == 'y' || input == 'Y' || s == "yes" || s == "YES"){
                        again = true;
                        break;
                    }
                    else if(input == 'N' || input == 'n' || s == "NO" || s == "YES"){
                        break;
                    }
                }   
               
                if(again){
                    continue;
                }
                else{
                System.out.println("게임을 종료합니다.");
                System.exit(0);
                }
            }
        }
    
    }     
}
