package sk.stuba.fei.uim.oop.cards;

import java.util.ArrayList;

public class DeckOfCards extends ChanceCard {

    public DeckOfCards(){}

    public ArrayList<ChanceCard> makeDeckOfCards(){
        ArrayList<ChanceCard> chanceCards = new ArrayList<>();
        chanceCards.add(new ChanceCard("You have been fined for 250$.",-250));
        chanceCards.add(new ChanceCard("You have won 500$.",500));
        chanceCards.add(new ChanceCard("You have won 1000$.",1000));
        chanceCards.add(new ChanceCard("You have been fined for 1000$.",-1000));
        chanceCards.add(new ChanceCard("You have won the lottery. Your reward is 5000$.",5000));
        chanceCards.add(new ChanceCard("You have been fined for 2500$.",-2500));
        return chanceCards;
    }

}

