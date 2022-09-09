import java.util.Scanner;

public class AfterLoginPage {

    public static Scanner scanner = new Scanner(System.in);
    public static void printAfterLoginPage(Member member){ //로그인 직후 페이지

        //수정할 것 회원정보를 모두 보이게 할 것인지? 아이디와 비밀번호만 보이게 할 것인지?
        System.out.println("아이디: " + member.id);
        System.out.println("비밀번호: " + member.password);
        System.out.println("=================================================");
        System.out.println("==                                             ==");
        System.out.println("==                                             ==");
        System.out.println("==   **로그아웃을 원하면 o를 누르세요.               ==");
        System.out.println("==   **회원탈퇴를 원하시면 x를 누르세요.             ==");
        System.out.println("==                                             ==");
        System.out.println("=================================================");

        while(true){
            System.out.print("입력: ");
            String m = scanner.nextLine();

            if(m.equals("o")){ //로그아웃할 때
                System.out.println("로그아웃 되셨습니다.");
                System.out.println("메인페이지로 돌아갑니다.");
                MainPage.printMainPage();
                break;
            }
            else if(m.equals("x")){ //회원탈퇴를 할 때
                DeleteMemberInformationPage.PrintDeleteMemberInformationPage(member);
                break;
            }
            else{
                System.out.println("입력값이 유효하지 않습니다. 다시 입력하세요.");
                printAfterLoginPage(member);
            }
        }
    }
}
