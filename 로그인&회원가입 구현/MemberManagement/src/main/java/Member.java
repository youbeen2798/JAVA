import java.util.HashMap;
import java.util.Map;

public class Member {

    public static Map<Integer, Member> members= new HashMap<Integer, Member>();

    public static int idx = 0;
    public String id; //로그인용 아이디
    String password; //패스워드
    String name; //이름
    String email; //이메일
    String phoneNumber; //핸드폰 번호

    public static void putMember(Member member){ //회원가입 정보 추가하는 메소드
        members.put(idx++, member);
        System.out.println("회원가입 정보가 추가되었습니다.");
        MainPage.printMainPage();
    }


    //어떤 return 값이 보안상 좋을까..
    public static Member checkLoginInformation(String inputId, String inputPassword){ //사용자가 입력한 정보가 로그인이 되는지 확인하는 메소드
        for(Map.Entry<Integer, Member> entrySet: members.entrySet()){
            if(entrySet.getValue().id.equals(inputId) && entrySet.getValue().password.equals(inputPassword)){
                return entrySet.getValue();
            }
        }
        return null;
    }

    public static void deleteMember(Member member){ //회원정보 삭제
        for(Map.Entry<Integer, Member> entrySet: members.entrySet()){
            if(entrySet.getValue().id.equals(member.id) && entrySet.getValue().password.equals(member.password)){
                members.remove(entrySet.getKey());
                break;
            }
        }
    }

}
