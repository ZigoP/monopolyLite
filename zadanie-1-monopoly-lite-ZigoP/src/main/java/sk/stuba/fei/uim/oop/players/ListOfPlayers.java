package sk.stuba.fei.uim.oop.players;

import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.ArrayList;

public class ListOfPlayers extends Player{

    public ListOfPlayers(){}

    public ArrayList<Player> AddPlayer(){
        int numberOfPlayers = KeyboardInput.readInt("Insert the number of players (between 2-4).");
        while(numberOfPlayers < 2 || numberOfPlayers > 4){
            numberOfPlayers = KeyboardInput.readInt("The number must be between 2-4.");
        }
        ArrayList<Player> players = new ArrayList<>();
        for(int i = 1; i < numberOfPlayers + 1; i++){
            int sameName = 0;
            Player player = new Player();
            player.setName(KeyboardInput.readString(i + ". player insert your name."));
            for(Player p : players){
                if (p.getName().equals(player.getName())) {
                    sameName = 1;
                    break;
                }
            }
            while(player.getName().equals("") || sameName == 1){
                if(sameName == 1){
                    System.out.println("This name is already taken. Try another one.");
                    player.setName(KeyboardInput.readString(i + ". player insert your name."));
                    sameName = 0;
                    for(Player p : players){
                        if (p.getName().equals(player.getName())) {
                            sameName = 1;
                            break;
                        }
                    }
                }
                else{
                    player.setName(KeyboardInput.readString(i + ". player insert your name. The name must be at least 1 character long."));
                }

            }
            players.add(player);
        }
        return players;
    }
}

