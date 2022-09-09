import java.util.Scanner;

public class MainPage {

    public static void printMainPage(){
        System.out.println("===================================");
        System.out.println("==           메인 페이지            ==");
        System.out.println("==                                ==");
        System.out.println("==                                ==");
        System.out.println("==       둘 중 하나를 선택하세요      ==");
        System.out.println("==             1.로그인            ==");
        System.out.println("==             2.회원가입           ==");
        System.out.println("==                                ==");
        System.out.println("====================================");

        selectLoginOrSignup();
    }

    public static void selectLoginOrSignup(){ //메인 페이지에서 로그인할지 회원가입할지 선택하는 메소드

        System.out.print("입력: ");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();

        if(num == 1){
            System.out.println("로그인을 선택하셨습니다.");
            LoginPage.printLoginPage();
        }
        if(num == 2){
            System.out.println("회원가입을 선택하셨습니다.");
            SignUpPage.printSignupPage();
        }
    }
}
