package sk.stuba.fei.uim.oop.cards;

public class ChanceCard {
    private String text;
    private int amount;

    public ChanceCard(){}

    public ChanceCard(String text, int amount) {
        this.text = text;
        this.amount = amount;
    }

    public void readCard(){
        System.out.println(text);
    }

    public int getAmount() {
        return amount;
    }
}

