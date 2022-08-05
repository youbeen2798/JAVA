import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;
    
    public class Main {
        public static String charToString(char ch) {
            return String.valueOf(ch);
        }

        public static boolean isNumeric(String s) {
            try {
                Double.parseDouble(s);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        public static void main(String[] args) {

            System.out.print("구문을 입력하세요: ");
            Scanner scanner = new Scanner(System.in);
            String st = scanner.next(); //3-5

            String expression2 = Exchange.transform(st); //postfix로 바꿈
            System.out.println("expression2: " + expression2);
            //문장에 따른 해석기를 제작

            Evaluator sentence2 = new Evaluator(expression2); //

            Map<String, Expression> variables2 = new HashMap<String, Expression> ();

            String stt[] = expression2.split(" "); //4*(6-4)가 됨

            for(int i=0; i<stt.length; i++){
                if(isNumeric(stt[i])){
                    int k = Integer.parseInt(stt[i]);
                    variables2.put(stt[i], new Number(k));
                //    System.out.println(stt[i]);
                }
            }

             //해석기에 입력을 넣고 결과 얻기
            int result2 = sentence2.interpret(variables2);

            System.out.println(result2);


        }
    
    }
