import java.math.BigInteger;

class Rational{

    BigInteger x,y;

    Rational(BigInteger a, BigInteger b){ //분모가 음수라면, 분모와 분자의 부호를 각각 바꾼다.
        if(b.compareTo(BigInteger.ZERO) < 0){ //b가 음수라면 ( 분모가 음수라면)
            x = a.negate(); // 분자의 부호를 바꿈 ( 양수 -> 음수 )
            y = b.negate(); // 분모의 부호를 바꿈 ( 음수 -> 양수 ) // y가 true라면 false를, false라면 true를 반환
        }
        else{
            x = a;
            y = b;
        }
    }

    Rational add(Rational k){ // 예. r1 = 5/6, r2 = 2/9라고 할 때
        BigInteger r = y.gcd(k.y); //두 유리수의 분모들(6, 9) 끼리의 최대 공약수 : 3
        System.out.println("r: " + r );
        r = (y.multiply(k.y)).divide(r); //최종 두 유리수의 부모의 최소공배수 ( 두 유리수의 부모의 곱 / 최대 공약수 ) => 6 * 9 / 3 = 18
        System.out.println("r: " + r );
        BigInteger x1 = r.divide(y); // 최소공배수 (나누기) 첫번째 유리수의 부모 => 18 / 6 = 3
        System.out.println("x1: " + x1);
        BigInteger x2 = r.divide(k.y); //최소공배수 (나누기) 두번째 유리수의 부모 => 18 / 9 = 2
        System.out.println("x2: " + x2);

        Rational result = new Rational((x.multiply(x1)).add(x2.multiply(k.x)),r); // 5 * 3 +  2 * 2 = 19 ( 분자값 ), 18 (분모값)

        return result;
    }

    Rational subtract(Rational k){  // 예. r1 = 5/6, r2 = 2/9라고 할 때
        BigInteger r = y.gcd(k.y);   //두 유리수의 분모들(6, 9) 끼리의 최대 공약수 : 3
        r = (y.multiply(k.y)).divide(r); //최종 두 유리수의 부모의 최소공배수 ( 두 유리수의 부모의 곱 / 최대 공약수 ) => 6 * 9 / 3 = 18
        BigInteger x1 = r.divide(y); // 최소공배수 (나누기) 첫번째 유리수의 부모 => 18 / 6 = 3
        BigInteger x2 = r.divide(k.y);  //최소공배수 (나누기) 두번째 유리수의 부모 => 18 / 9 = 2

        Rational result = new Rational((x.multiply(x1)).subtract(x2.multiply(k.x)), r); // 5 * 3 - 2 * 2 = 11 (분자값), 18 (부모값)

        return result;
    }

    Rational multiply(Rational k){ // 분자는 분자끼리 분모는 분모끼리 곱해야 하므로, (x / y) * ( k.x / k.y) = (x * k.x) / (y * k.y)
        Rational result = new Rational(x.multiply(k.x), y.multiply(k.y));
        return result;
    }

    Rational divide(Rational k){ // 나누기는 두 번째 유리수의 분자와 부모값을 switch하면 됨
        Rational result = new Rational(x.multiply(k.y), y.multiply(k.x)); //첫번째 유리수의 분자와 두번째 유리수의 부모의 곱, 첫버째 유리수의 분모와 두 번째 유리수의 분자의 곱
        return result;
    }

    public String toString(){ //결과값을 보여줄 때, x와 y를 나타내는 방법 
        BigInteger gcf = y.gcd(x); //gcf는 x와 y의 최대공약수

        if(!(y.gcd(x)).equals(BigInteger.ONE)){ //y와 x의 최대공약수가 1이 아니라면
            x = x.divide(gcf); //x는 x / gcf(x와 y의 최대공약수)
            y = y.divide(gcf); //y는 y / gcf(x와 y의 최대공약수)
        } // ( 만약, x가 6 y가 4라면 -> x는 3, y는 2로 바꿔줌 6 / 4 =  3 / 2)

        String result = "";

        if(y.equals(BigInteger.ONE)){ //y가 1이라면(분모가 1이라면)
            result += x; // x만 받아줌 예시) 3 / 1 = 3인
        }
        else{
            result += x; //만약 x가 7, y가 3이라면 -> 7 / 3
            result += "/";
            result += y;
        }

        return result;
    }

    
}
