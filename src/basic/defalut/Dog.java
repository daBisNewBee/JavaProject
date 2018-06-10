package basic.defalut;

public class Dog implements SexAnimal{
    @Override
    public void speek() {
        System.out.println("Dog.speek");
    }

    @Override
    public void say() {
        System.out.println("Dog.say");
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.speek();
        dog.say();
    }
}
