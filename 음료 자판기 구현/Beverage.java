public abstract class Beverage extends Test {
    
    private int price;
    private String name;

    public Beverage(String name, int price){
        this.name = name;
        this.price = price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public Integer getPrice(){
        return this.price;
    }

    public String getName(){
        return this.name;
    }

    public void pay(PaymentMethod paymentMethod, int totalprice, int instanceNumber){ //음료 결제하는 메소드
		paymentMethod.pay(totalprice, instanceNumber);
	}

}
