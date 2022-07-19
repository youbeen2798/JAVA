import java.util.Scanner;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("테스트할 횟수를 입력하시오: ");

        int T = scanner.nextInt();

        for(int test_case = 0; test_case < T; test_case++){
            System.out.println("첫번째 유리수의 분자와 분모를 차례대로 입력하시오: ");

            BigInteger num1_1 = new BigInteger(scanner.next());
            BigInteger num1_2 = new BigInteger(scanner.next());
    
            Rational r1 = new Rational(num1_1, num1_2);
    
            System.out.println("두번째 유리수의 분자와 분모를 차례대로 입력하시오: ");
    
            BigInteger num2_1 = new BigInteger(scanner.next());
            BigInteger num2_2 = new BigInteger(scanner.next());
    
            Rational r2 = new Rational(num2_1, num2_2); 
    
            System.out.println(r1 + " + "  + r2 + " = " + r1.add(r2)); //더하기
            System.out.println(r1 + " - "  + r2 + " = " + r1.subtract(r2)); //빼기
            System.out.println(r1 + " * "  + r2 + " = " + r1.multiply(r2)); //곱하기
            System.out.println(r1 + " / "  + r2 + " = " + r1.divide(r2)); //나누기
        }
       

    }
}
