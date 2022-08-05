import java.util.Stack;

public class Exchange {


private static int precedence(String s) {

	if (s.equals(new String("*")) || s.equals(new String("/"))) 
		return 2;
	else if (s.equals(new String("+")) || s.equals(new String("-"))) 
		return 1;
	else return 0;

}
 
public static String transform(String inFix) {

	Stack <String> stack = new Stack<>(); //문자 받을 스택

	char[] charArray = inFix.toCharArray(); // char[] charArray = 21+5*3
	
	
	String postFix = "";
 
	String numtmp = "";

	for (char c : charArray) {

		switch(c){
			case '0': case '1':  case '2':  case '3':  case '4': case '5':  case '6':  case '7':  case '8':  case '9':
				System.out.println("numtmp에" + c + "들어감!");
				numtmp += c; //새로 짠 코드
				break;

			case '(':

				System.out.println("스택에 " + c + "들어감");
				stack.push(String.valueOf(c));

				if(numtmp != ""){
					System.out.println("1. postfix에" + numtmp + " 들어갑니당");
					postFix += numtmp  + " ";
					numtmp = "";
				}

				break;

			case ')':

				if(numtmp != ""){
//					System.out.println("2. postfix에" + numtmp + " 들어갑니당");
					postFix += numtmp  + " ";
					numtmp = "";
				}
				
				while (!stack.isEmpty() && !stack.peek().equals(new String("("))){
//					System.out.println("postFix에" + stack.peek() + "들어감");
					postFix += stack.pop() + " ";
				}

				if(!stack.empty()){
					stack.pop();
				}

				break;

			default: // "+" , "-", "*", "/"일때
						
				if(numtmp != null){
//					System.out.println("3. postfix에" + numtmp + " 들어갑니당");
					postFix += numtmp  + " ";
					numtmp = "";
				}

				while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(String.valueOf(c))){ // 여기서 "("와 "*"가 들어가게 됨
//					System.out.println("postFix에" + stack.peek() + "들어감");
					postFix += stack.pop() + " ";
				} 

//				System.out.println("스택에 " + c + "들어감");
				stack.push(String.valueOf(c)); // +

				break;
		}
	}

	System.out.println("남은 numtmp:" + numtmp + "!");

	//여기 때문인듯
	if(numtmp != ""){
//		System.out.println("들어갑니당" + numtmp);
		postFix += numtmp + " ";
	}

	while (!stack.isEmpty()){
//		System.out.println("남아있는 것: " + stack.peek());
		postFix += stack.pop() + " ";
	}

	postFix = postFix.trim();

//	System.out.println("마지막 postFix: " + postFix);

	return postFix;

	}
}
