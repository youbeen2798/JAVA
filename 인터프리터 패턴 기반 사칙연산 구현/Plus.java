import java.util.Map;

public class Plus implements Expression{
    //+의 왼쪽에 올 표현(숫자일 수도 있고 이미 연산된 결과일 수 있음)
    Expression leftNumber;
     //+의 오른쪽에 올 표현
    Expression rightNumber;

    public Plus(Expression leftNumber, Expression rightNumber) {
        this.leftNumber = leftNumber;
        this.rightNumber = rightNumber;
    }

    @Override
    public int interpret(Map variables) {
        //왼쪽과 오른쪽의 결과를 더해서 반환
        return leftNumber.interpret(variables)+rightNumber.interpret(variables);
    }
}