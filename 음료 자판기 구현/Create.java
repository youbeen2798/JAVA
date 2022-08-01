import ingredient.IUseChocolate;
import ingredient.IUseCoffee;
import ingredient.IUseMilk;
import ingredient.IUsePeach;

public class Create {
    
    public static Beverage CreateBeverage(int num){

        switch(num){
            case 1:              
                return new Americano("Americano",1200);
            case 2:
                return new CafeLattee("Cafe Latte", 1700);
            case 3:
                return new Mochaccino("Mochaccino", 1700);
            case 4:
                return new HotChocolate("Hot Chocolate", 1700);
            case 5:
                return new IcedAmericano("Iced Americano", 1700);
            case 6:
                return new IcedChocolate("Iced Chocolate", 1700);
            case 7:
                return new IcedCafeLatte("Iced Cafe Latte", 1700);
            case 8:
                return new IcedPeachTree("Iced Peach Tree", 1700);
            default:
                return null;
        }
    }
}

class Americano extends HotBeverage implements IUseCoffee { // 아메리카노는 뜨거운 커피에 커피를 넣음
    public Americano(String name,int price) {
        super(name, price);
    }
}

class CafeLattee extends HotBeverage implements IUseCoffee, IUseMilk{ //카페라떼는 뜨거운 커피에 우유를 넣음
    public CafeLattee(String name, int price) {
        super(name,price);
    }
}

class Mochaccino extends HotBeverage implements IUseCoffee, IUseMilk, IUseChocolate{ //모카치노는 뜨거운 커피에 우유, 초콜릿시럽을 넣음
    public Mochaccino(String name,int price) {
        super(name, price);
    }
}


class HotChocolate extends HotBeverage implements IUseMilk, IUseChocolate{ //핫초코는 뜨거운 우유와 초코시럽이 나옴
    public HotChocolate(String name, int price) {
        super(name, price);
    }
}

class IcedAmericano extends IceBeverage implements IUseCoffee{ //아이스아메리카노는 뜨거운 커피가 나옴
    public IcedAmericano(String name, int price) {
        super(name, price);
    }
}

class IcedChocolate extends IceBeverage implements IUseMilk, IUseChocolate{ //아이스초코는 뜨거운 우유에 초코시럽이 나옴
    public IcedChocolate(String name, int price) {
        super(name, price);
    }
}

class IcedCafeLatte extends IceBeverage implements IUseCoffee, IUseMilk{ //아이스카페라떼는 뜨거운 커피에 우유가 나옴
    public IcedCafeLatte(String name, int price) {
        super(name, price);
    }
}

class IcedPeachTree extends IceBeverage implements IUsePeach{ //복숭아아이스티는 복숭아시럽이 나옴
    public IcedPeachTree(String name, int price) {
        super(name, price);
    }
}