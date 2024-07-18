package sk.stuba.fei.uim.oop.fields;

public class Property extends Field{
    private int cost;
    private int rent;
    private String ownerName;


    public Property(String name, int cost, int rent, int position) {
        super(name,position);
        this.cost = cost;
        this.rent = rent;
        this.ownerName = "";
    }

    public int getCost() {
        return cost;
    }

    public int getRent() {
        return rent;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void printPropertyInfo(){
        System.out.println();
        super.printFieldInfo();
        System.out.println("Property cost: "+ cost + "$");
        System.out.println("Property rent: "+ rent + "$");
        System.out.println("Property owner: "+ ownerName);
        System.out.println();
    }
}
