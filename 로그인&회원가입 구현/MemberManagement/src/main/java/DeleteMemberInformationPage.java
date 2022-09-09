import java.rmi.NoSuchObjectException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DeleteMemberInformationPage {

    static Scanner scanner = new Scanner(System.in);
    public static void PrintDeleteMemberInformationPage(Member member){
        System.out.println("회원 탈퇴 페이지");
        System.out.println("=================================================");
        System.out.println("==                                             ==");
        System.out.println("==                                             ==");
        System.out.println("==   **정말로 회원 탈퇴를 원하십니까?                ==");
        System.out.println("==   **회원탈퇴를 원하시면 y를 누르세요.             ==");
        System.out.println("==   **로그인 페이지로 돌아가기 원하시면 l를 누르세요.  ==");
        System.out.println("==                                             ==");
        System.out.println("=================================================");

        while(true){
            System.out.print("입력: ");
            String input = scanner.nextLine();
            if(input.equals("y")){
                try{
                    System.out.println("고객님의 정보를 한번 더 확인하겠습니다.");
                    System.out.print("아이디를 입력하세요: ");
                    String inputId = scanner.nextLine();
                    System.out.print("비밀번호를 입력하세요: ");
                    String inputPassword = scanner.nextLine();

                    if(inputId.equals(member.id) && inputPassword.equals(member.password)){ //아이디와 비밀번호가 다 맞다면
                        System.out.println("고객님의 회원정보를 삭제 중입니다.");
                        Member.deleteMember(member); //회원 정보 삭제 후
                        System.out.println("고객님의 회원정보가 삭제되었습니다.");
                        System.out.println("다시 메인페이지로 돌아갑니다.");
                        MainPage.printMainPage(); //메인 페이지로 돌아가기
                    }
                    else{
                        System.out.println("입력값이 유효하지 않습니다.");
                        throw new NoSuchElementException();
                    }
                }
                catch(NoSuchElementException e){
                    System.out.println(e.toString());
                    PrintDeleteMemberInformationPage(member);
                }
            }
            else if(input.equals("l")){
                System.out.println("로그인 페이지로 이동합니다.");
                LoginPage.printLoginPage();
            }
        }

    }
}
