abstract class Shape{
    protected int height;
    protected int base;

    public Shape(int height, int base){
       this.height = height;
       this.base = base;    
    }

   abstract double area();

}

class Triangle extends Shape{
    
    public Triangle(int height, int base){
        super(height, base);
    }

    double area(){
        return (double)height * (double)base / 2;
    }
    
}

class Rectangle extends Shape{

    public Rectangle(int height, int base){
        super(height, base);
    }

    double area(){
        return height * base;
    }

}

class Sqaure extends Rectangle{
    
    public Sqaure(int height, int base){
        super(height, base);
    }

    double area(){
        return super.area();
    }

}

class Pentagon{

}

class Shapes{
    public static void main(String[] args){
        Triangle triangle = new Triangle(2,3);
        System.out.println(triangle.area());
        
        Rectangle rectangle = new Rectangle(2,4);
        System.out.println(rectangle.area());

        Sqaure square = new Sqaure(2,4);
        System.out.println(square.area());


    }
}