import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LoginPage extends Member{

    static Scanner scanner = new Scanner(System.in);

    public static void printLoginPage(){

        System.out.println("=================================================");
        System.out.println("==                                             ==");
        System.out.println("=                    로그인 페이지                ==");
        System.out.println("==                                             ==");
        System.out.println("==   **메인페이지로 돌아가고 싶으면 m을 누르세요.      ==");
        System.out.println("==   **로그인을 원하면 l를 누르세요.                ==");
        System.out.println("==                                             ==");
        System.out.println("=================================================");

        while(true){
            System.out.print("입력: ");
            String m = scanner.nextLine();
            if(m.equals("m")){
                MainPage.printMainPage();
                break;
            }
            else if(m.equals("l")){
                login();
                break;
            }
            else{
                System.out.println("입력값이 유효하지 않습니다. 다시 입력하세요.");
            }
        }
    }
    public static void login(){ //아이디와 비밀번호 입력하기

        System.out.print("아이디를 입력하세요: ");
        String inputId = scanner.nextLine(); //youbeen
        System.out.print("비밀번호를 입력하세요: ");
        String inputPassword = scanner.nextLine(); //1234

        try{

            Member member = Member.checkLoginInformation(inputId, inputPassword);

            if(member != null){ //만약 로그인을 성공하면
                loginSuccessful(member.id);
                AfterLoginPage.printAfterLoginPage(member);
            }
            else{ //로그인을 실패하면
                System.out.println("로그인에 실패하셨습니다.");
                throw new NoSuchElementException();
            }
        }
        catch(NoSuchElementException e){
            System.out.println(e.toString());
            printLoginPage();
            //        e.printStackTrace(); //이 주석을 풀면 조금 이상함
        }
    }

    public static void loginSuccessful(String inputId){ //로그인이 성공했을 때 실행하는 메소드
        System.out.println(inputId + "님 로그인 되셨습니다.");
    }

}
