public class Algorithm {
    static final int addFrom1To10 = 55;
    
    public static int sigma(int begin, int end, int step) {
        return accumulate(new Adder(), 0, begin, end, step); 
    }

    public static int pi(int begin, int end, int step) {
        return accumulate(new Multiplier(), 1, begin, end, step);
    }

    public static int sub(int begin, int end, int step) {
        return accumulate(new Subtractor(), 1, begin, end, step);
    }

    static int accumulate(BinaryOp binder, int init, int begin, int end, int step) {
        int result = init;
        for (int next = begin; next <= end; next = next + step) {
            result = binder.apply(result, next);
        }
        return result;
    }

    //단항 연산자 inter 인터페이스 만들고, -1, -2 처럼 값에 무엇을
    //c#으로 Console, c#에는 deligate라는 것이 있음 (function point)
    //우리가ㅏ 한 다형성을 구현할 수 있음.

    /*
    static int accumulate(Adder adder, int init, int begin, int end, int step) {
        int result = init;
        for (int next = begin; next <= end; next = next + step) {
            result = adder.apply(result, next);
        }
        return result;
    }

    static int accumulate(Multiplier multiplier, int init, int begin, int end, int step) {
        int result = init;
        for (int next = begin; next <= end; next = next + step) {
            result = multiplier.apply(result, next);
        }
        return result;
    }
*/
    public static void main(String[] args) {
        System.out.println(sigma(1, 10, 1));
        System.out.println(pi(1, 10, 1));
        System.out.println(sub(1, 10, 1));

    }
}