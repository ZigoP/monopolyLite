package sk.stuba.fei.uim.oop.fields;


public class Field {
    protected String name;
    protected int position;

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public Field() {
    }

    public Field(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public void printFieldInfo(){
        System.out.println("Field name: " + name);
        System.out.println("Field position: " + position);

    }
}

