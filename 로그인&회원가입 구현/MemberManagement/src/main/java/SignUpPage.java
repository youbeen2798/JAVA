import java.util.Scanner;

public class SignUpPage extends Member{
    static Scanner scanner = new Scanner(System.in);

    public static void printSignupPage(){
        System.out.println("=================================================");
        System.out.println("==                                             ==");
        System.out.println("=                회원가입 페이지                  ==");
        System.out.println("==                                             ==");
        System.out.println("==   **메인페이지로 돌아가고 싶으면 m을 누르세요.      ==");
        System.out.println("==   **회원가입을 원하면 s를 누르세요.               ==");
        System.out.println("==                                             ==");
        System.out.println("=================================================");

        while(true){
            System.out.print("입력: ");
            String m = scanner.nextLine();
            if(m.equals("m")){
                MainPage.printMainPage();
                break;
            }
            else if(m.equals("s")){
                signup();
                break;
            }
            else{
                System.out.println("입력값이 유효하지 않습니다. 다시 입력하세요.");
            }
        }
    }
    public static void signup(){

        Member member = new Member();

        member.id = SignupRegulation.inputId();

        member.password = SignupRegulation.inputPassword();

        member.name = SignupRegulation.inputName();

        member.email = SignupRegulation.inputEmail();

        member.phoneNumber = SignupRegulation.inputPhoneNumber();

        Member.putMember(member);
    }
}
