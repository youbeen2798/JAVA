import java.util.Map;

//우리가 만들 연산자들
public interface Expression {
    //어떻게 해석할지 구현
    public int interpret(Map<String, Expression> variables);
}
