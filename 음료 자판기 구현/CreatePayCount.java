//오늘 현금, 신용카드, 페이코가 결제수단으로 각각 얼만큼 쓰였는지 확인하는 메소드(싱글톤 사용)
package payment;
public class CreatePayCount {
    
    private static CreatePayCount[] payCountNum;

    private String netCash = "0"; //현금
    private String nextCard = "0"; //신용 카드
    private String nextOnline = "0"; //온라인페이

    private CreatePayCount(){} // 객체 생성 막기 위함

    public static CreatePayCount getPayCountNum(int num){
        if(payCountNum == null){
            payCountNum = new CreatePayCount[] { new CreatePayCount(), new CreatePayCount(), new CreatePayCount()};
        }
        return payCountNum[num];
    }

    public String createAccountNumber(int instanceNumber) { 

        if(instanceNumber == 0){ //현금
            int accountNumber = Integer.parseInt(this.netCash); //0
            netCash = Integer.toString(++accountNumber); //1
            return "0000-" + netCash;
        }

        if(instanceNumber == 1){ //카드
            int accountNumber = Integer.parseInt(this.nextCard);
            nextCard = Integer.toString(++accountNumber);
            return "0001-" + nextCard;
        }

        
        if(instanceNumber == 2){ //온라인 페이
            int accountNumber = Integer.parseInt(this.nextOnline);
            nextOnline = Integer.toString(++accountNumber);
            return "0002-" + nextOnline;
        }

        return null;      
    }
    
}
