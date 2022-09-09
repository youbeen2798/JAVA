import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SignupRegulation {

    public static Set<String> idSet = new HashSet<String>();
    public static Set<String> emailSet = new HashSet<String>();
    public static Scanner scanner = new Scanner(System.in);

    public static String inputId(){ //중복 여부 검사 안했음
        String idRegExp = "^[a-z0-9]*$"; //영문 소문자와 숫자만 허용
        String id;
        System.out.println("아이디는 영문 소문자와 숫자만 허용하며 중복이 불가합니다.");
        while(true){
            System.out.print("아이디를 입력해주세요: ");
            id = scanner.nextLine();
            if(id.matches(idRegExp) && !idSet.contains(id)){
                idSet.add(id); //아이디 집합에 추가
                break;
            }
            else if(!id.matches(idRegExp)){
                System.out.println("아이디 생성 규칙에 어긋납니다.");
            }
            else if(idSet.contains(id)){
                System.out.println("이미 존재하는 아이디입니다.");
            }
        }
        return id;
    }

    public static String inputPassword(){
        String passwordRegExp = "^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\\\(\\\\)\\-_=+]).{8,20}$"; //영문 대소문자, 숫자, 특수문제 무조건 포함 && 8자 이상 20자 이하
        String password;
        while(true){
            System.out.println("비밀번호는 영문 대소문자와 숫자, 특수문자를 무조건 포함해야 하며 8글자 이상 20글자 이하여야 합니다.");
            System.out.print("비밀번호를 입력해주세요: ");
            password = scanner.nextLine();
            if(password.matches(passwordRegExp)){
                break;
            }
            System.out.println("비밀번호 생성 규칙에 어긋납니다.");
        }
        return password;
    }

    public static String inputName(){
        System.out.print("이름을 입력해주세요: ");
        String name = scanner.nextLine();
        return name;
    }

    public static String inputEmail(){
        String emailRegExp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+$"; //이메일 형식
        String email;
        while(true){
            System.out.println("이메일 형식에 맞추어 작성해주세요. ");
            System.out.println("예시: aaa@example.com");
            System.out.print("이메일을 입력해주세요: ");
            email = scanner.nextLine();
            if(email.matches(emailRegExp) && !emailSet.contains(email)){
                break;
            }
            else if(!email.matches(emailRegExp)){
                System.out.println("이메일 생성 규칙에 어긋납니다.");
            }
            else if(emailSet.contains(email)){
                System.out.println("이미 존재하는 이메일입니다.");
            }
        }
        return email;
    }

    public static String inputPhoneNumber(){
        String phoneNumberRegExp = "^01(?:0|1|[6-9])-?(\\d{3}|\\d{4})-?(\\d{4})$";
        String phoneNumber;
        while(true){
            System.out.println("전화번호 작성 예시1 : 010-1234-5678 ");
            System.out.println("전화번호 작성 예시2 : 01012345678 ");
            System.out.print("전화번호를 입력해주세요: ");
            phoneNumber = scanner.nextLine();
            if(phoneNumber.matches(phoneNumberRegExp)){
                break;
            }
            System.out.println("전화번호 생성 규칙에 어긋납니다.");
        }
        return phoneNumber;
    }


}
