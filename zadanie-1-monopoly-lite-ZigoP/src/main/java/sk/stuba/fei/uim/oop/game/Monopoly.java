package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.cards.ChanceCard;
import sk.stuba.fei.uim.oop.cards.DeckOfCards;
import sk.stuba.fei.uim.oop.players.ListOfPlayers;
import sk.stuba.fei.uim.oop.players.Player;
import sk.stuba.fei.uim.oop.fields.Field;
import sk.stuba.fei.uim.oop.fields.Property;
import sk.stuba.fei.uim.oop.fields.SpecialField;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.ArrayList;
import java.util.Random;

public class Monopoly {
    public Monopoly(){
        playMonopoly();
    }

    public void playMonopoly(){
        final String RESET = "\033[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED_BACKGROUND = "\u001B[41m";
        final String ANSI_GREEN_BACKGROUND = "\u001B[42m";

        Board monopolyBoard = new Board();
        Field fields[] = monopolyBoard.makeBoard();

        DeckOfCards deckOfCards = new DeckOfCards();
        ArrayList<ChanceCard> chanceCards = deckOfCards.makeDeckOfCards();

        ListOfPlayers listOfPlayers = new ListOfPlayers();
        ArrayList<Player> players = listOfPlayers.AddPlayer();

        while(players.size() > 1){
            for(int i = 0; i < players.size(); i++){
                if(i == 0){
                    System.out.print(ANSI_RED);
                }
                else if(i == 1){
                    System.out.print(ANSI_BLUE);
                }
                else if(i == 2){
                    System.out.print(ANSI_GREEN);
                }
                else if (i == 3){
                    System.out.print(ANSI_YELLOW);
                }
                Player currentPlayer = players.get(i);
                System.out.println( currentPlayer.getName()+ "'s turn.");
                if(currentPlayer.getJailTime() > 0) {
                    System.out.println("You are in jail for " + currentPlayer.getJailTime() + " more rounds.");
                    currentPlayer.inJail();
                    System.out.println("Your jail time after this round is: " + currentPlayer.getJailTime() + ".");
                }
                else{
                    KeyboardInput.readChar("Press enter to throw the dice.");
                    currentPlayer.printStats();
                    Random randNum = new Random();
                    int diceRoll = randNum.nextInt(6) + 1;
                    System.out.println("Your roll is: "+ diceRoll + ".");
                    currentPlayer.step(diceRoll);
                    for (Field field : fields) {
                        if (currentPlayer.getPosition() == field.getPosition()) {
                            if (field instanceof Property) {
                                Property currentProperty = (Property) field;
                                currentProperty.printPropertyInfo();
                                if (currentProperty.getOwnerName().equals("")) {
                                    if (currentPlayer.getMoney() >= currentProperty.getCost()) {
                                        System.out.println("You can buy this property for " + currentProperty.getCost() + "$.");
                                        int buy = KeyboardInput.readInt("Do you want to buy this property? [1/0]");
                                        while(buy != 0 && buy != 1){
                                            buy = KeyboardInput.readInt("If you want to buy this property write 1 ,if you don't want to buy this property write 0.");
                                        }
                                        System.out.println();
                                        if (buy == 1) {
                                            currentPlayer.buyProperty(currentProperty);
                                            currentProperty.setOwnerName(currentPlayer.getName());
                                            System.out.println("You have successfully purchased " + currentProperty.getName() + ".");
                                            System.out.println("Your new balance: " + currentPlayer.getMoney() + "$.");
                                        }
                                    } else {
                                        System.out.println("Although this property doesn't have an owner yet, you don't have enough money to purchase it.");
                                    }
                                } else if(!currentPlayer.getName().equals(currentProperty.getOwnerName())){
                                    int amount = currentProperty.getRent();
                                    String ownerName = currentProperty.getOwnerName();
                                    for (Player player : players) {
                                        if (ownerName.equals(player.getName())) {
                                            System.out.println("This property belongs to " + player.getName() + ". You have to pay the rent in the amount of " + amount + "$.");
                                            if (currentPlayer.getMoney() - amount < 0) {
                                                System.out.print(ANSI_RED_BACKGROUND + ANSI_BLACK);
                                                System.out.println("##################################################");
                                                System.out.println("You can't afford the rent. You have lost the game.");
                                                System.out.println("##################################################");
                                                player.finances(currentPlayer.getMoney());
                                                currentPlayer.finances(-currentPlayer.getMoney());
                                                System.out.println(player.getName() + "'s new balance: " + player.getMoney() + "$.");
                                                currentPlayer.setEnd(true);
                                            } else {
                                                currentPlayer.finances(-amount);
                                                player.finances(amount);
                                                System.out.println(currentPlayer.getName() + "'s new balance: " + currentPlayer.getMoney() + "$.");
                                                System.out.println(player.getName() + "'s new balance: " + player.getMoney() + "$.");
                                            }
                                        }
                                    }
                                }
                            }

                            else {
                                SpecialField currentSpecialField = (SpecialField) field;
                                currentSpecialField.printSpecialFieldInfo();
                                if(currentPlayer.getPosition() == currentSpecialField.getPosition()){
                                    if(currentSpecialField.getPosition() == 0){
                                        System.out.println("You have stepped on START, you have earned 1000$.");
                                        System.out.println("Your new balance: " + currentPlayer.getMoney() + "$.");
                                    }
                                    else if(currentSpecialField.getPosition() == 3 || currentSpecialField.getPosition() == 9 || currentSpecialField.getPosition() == 15 || currentSpecialField.getPosition() == 21){

                                        System.out.println("You have drawn a chance card:");
                                        chanceCards.get(0).readCard();
                                        if(currentPlayer.getMoney()+chanceCards.get(0).getAmount() < 0){
                                            System.out.print(ANSI_RED_BACKGROUND + ANSI_BLACK);
                                            System.out.println("##################################################");
                                            System.out.println("Seems like you ran out of money. You have lost the game.");
                                            System.out.println("##################################################");
                                            currentPlayer.setEnd(true);
                                        }
                                        else{
                                            currentPlayer.finances(chanceCards.get(0).getAmount());
                                            System.out.println("Your new balance: " + currentPlayer.getMoney() + "$.");
                                        }
                                        chanceCards.add(chanceCards.remove(0));
                                    }
                                    else if(currentSpecialField.getPosition() == 6){
                                        System.out.println("You are just visiting the jail.");
                                    }
                                    else if(currentSpecialField.getPosition() == 12){
                                        System.out.println("You have been arrested. Your sentence is 3 rounds.");
                                        currentPlayer.arrest();
                                    }
                                    else if(currentSpecialField.getPosition() == 18){
                                        System.out.println("You have to pay fee in amount of 1000$.");
                                        if(currentPlayer.getMoney()-1000 < 0){
                                            System.out.print(ANSI_RED_BACKGROUND + ANSI_BLACK);
                                            System.out.println("##################################################");
                                            System.out.println("Seems like you ran out of money. You have lost the game.");
                                            System.out.println("##################################################");
                                            currentPlayer.setEnd(true);
                                        }
                                        else{
                                            currentPlayer.finances(-1000);
                                            System.out.println("Your new balance: " + currentPlayer.getMoney() + "$.");
                                        }

                                    }

                                }
                            }

                        }
                    }
                    if(currentPlayer.getEnd()){
                        for(int x = 0; x < currentPlayer.getProperties().size(); x++){
                            Property currentProperty = currentPlayer.getProperties().get(x);
                            for(Field field : fields){
                                if(field instanceof Property){
                                    Property checkProperty = (Property) field;
                                    if(checkProperty.getName().equals(currentProperty.getName())){
                                        checkProperty.setOwnerName("");
                                    }
                                }
                            }
                        }
                        players.remove(i);
                    }
                }
                System.out.print(RESET);
                System.out.println("------------------------------------------");
            }

        }
        System.out.print(ANSI_GREEN_BACKGROUND + ANSI_BLACK);
        System.out.println("**************************************************************");
        System.out.println("**************************************************************");
        System.out.println("!!!Congratulation " + players.get(0).getName() +". You have won the game!!!");
        System.out.println("**************************************************************");
        System.out.println("**************************************************************");
    }
}

