package sk.stuba.fei.uim.oop.players;

import sk.stuba.fei.uim.oop.fields.Property;

import java.util.ArrayList;

public class Player {
    private String name = "";
    private int money = 10000;
    private int position = 0;
    private int jailTime = 0;
    private ArrayList<Property> properties = new ArrayList<>();;
    private Boolean isEnd = false;

    /*Player(){
        this.name = "";
        this.money = 10000;
        this.position = 0;
        this.jailTime = 0;
        this.properties = new Arraylist<>();
        this.isEnd = false;
    }*/
    Player(){}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getMoney() {
        return money;
    }

    public int getJailTime() {
        return jailTime;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public Boolean getEnd() {
        return isEnd;
    }


    public void setEnd(Boolean end) {
        isEnd = end;
    }

    public void step(int amount){
        if(position + amount > 23){
            position = position + amount - 24;
            finances(1000);
            if(position > 0){
                System.out.println("You have crossed the START field. You have earned 1000$.");
                System.out.println("Your new balance: " + money + "$.");
            }
        }
        else{
            position += amount;
        }
    }

    public void finances(int amount){
        if(money + amount < 0){
            isEnd = true;
        }
        else{
            money += amount;
        }
    }

    public void arrest(){
        jailTime = 3;
    }

    public void inJail(){
        jailTime-= 1;
    }

    public void printStats(){
        System.out.println();
        System.out.println("Player name: " + name);
        System.out.println("Balance: " + money + "$");
        System.out.println("Position on the board: " + position);
        System.out.println("Player jail time: " + jailTime);
        System.out.print("Player properties: ");
        if(properties.size()!=0){
            for(var i : properties){
                System.out.print(i.getName() + ", ");
            }
        }
        System.out.println();
        System.out.println("Has player lost: " + isEnd);
        System.out.println();
    }

    public void buyProperty(Property property){
        properties.add(property);
        finances(-property.getCost());
    }
}
