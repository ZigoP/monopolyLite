package sk.stuba.fei.uim.oop.fields;

public class SpecialField extends Field{

    public SpecialField(String name, int position) {
        super(name,position);
    }

    public void printSpecialFieldInfo(){
        System.out.println();
        super.printFieldInfo();
        System.out.println();
    }
}

