import java.util.Map;
    import java.util.Stack;
    
    public class Evaluator implements Expression{
        
        private Expression syntax; //만들 최종 해석
        
        public Evaluator(String expression){ //return 값이 없음
            //expression을 입력받음
            Stack<Expression> stack = new Stack<Expression>();
    
            for(String strToken : expression.split(" ")){

                if(strToken.equals("+")){ // 3 5

                    Expression SecondNumber = stack.pop(); // 5
                    System.out.println("SecondNumber: " + SecondNumber);
                    Expression FirstNumber = stack.pop(); // 3
                    System.out.println("FirstNumber: " + FirstNumber);
                    Expression expressionPlus = new Plus(FirstNumber,SecondNumber);
                                        
                    stack.push(expressionPlus);
                }

                else if(strToken.equals("-")){
                    
                    Expression SecondNumber = stack.pop(); // 5
                    Expression FirstNumber = stack.pop(); // 3                    
                    Expression expressionMinus = new Minus(FirstNumber,SecondNumber);
                    
                    stack.push(expressionMinus);
                }

                else if(strToken.equals("*")){
                    
                    Expression SecondNumber = stack.pop(); // 2
                    Expression FirstNumber = stack.pop(); // 12                    
                    Expression expressionMultiply = new Multiply(FirstNumber,SecondNumber); // 객체 생성
                    
                    stack.push(expressionMultiply); // 
                }

                else if(strToken.equals("/")){
                    
                    Expression SecondNumber = stack.pop(); // 5
                    Expression FirstNumber = stack.pop(); // 3                    
                    Expression expressionDivide = new Divide(FirstNumber,SecondNumber);
                    
                    stack.push(expressionDivide);
                }
                
                else {
                    stack.push(new Variable(strToken)); // 12 2
                }
                
            }

            //해석 결과로 나온 계산기 
            syntax = stack.pop();
        }
    
        //사용자가 입력한 숫자로 계산기에 계산돌리기
        @Override
        public int interpret(Map<String, Expression> variables) {
            return syntax.interpret(variables);
        }
    }
