import java.util.Map;

public class Variable implements Expression{
    private String alphabet;

    public Variable(String alphabet) {
        this.alphabet = alphabet;
    }

    @Override
    public int interpret(Map<String, Expression> variables) {
        // 사용자가 입력한 Map에 alphabet이 있으면 그 알파벳에 해당하는 숫자 반환
        // 없으면 0 반환 
        if(variables.get(alphabet) == null)
            return 0;
        return variables.get(alphabet).interpret(variables);
    }
}