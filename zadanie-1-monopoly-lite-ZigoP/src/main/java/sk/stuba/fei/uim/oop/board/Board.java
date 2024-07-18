package sk.stuba.fei.uim.oop.board;

import sk.stuba.fei.uim.oop.fields.Field;
import sk.stuba.fei.uim.oop.fields.Property;
import sk.stuba.fei.uim.oop.fields.SpecialField;

public class Board extends Field{


    public Field[] makeBoard(){
        Field fields[] = new Field[24];
        fields[0] = new Property("Pizzeria",750,450,1);
        fields[1] = new Property("Aqua Park",1000,600,2);
        fields[2] = new Property("Restaurant",1250,750,4);
        fields[3] = new Property("Disco",1250,750,5);
        fields[4] = new Property("Cinema",1500,900,7);
        fields[5] = new Property("Boutique",1500,900,8);
        fields[6] = new Property("Theme Park",1750,1050,10);
        fields[7] = new Property("Beach Bar",1800,1100,11);
        fields[8] = new Property("Beach Club",2000,1200,13);
        fields[9] = new Property("Theater",2000,1200,14);
        fields[10] = new Property("Museum",2250,1450,16);
        fields[11] = new Property("Gallery",2250,1450,17);
        fields[12] = new Property("Ski Resort",2500,1600,19);
        fields[13] = new Property("Casino",3000,2000,20);
        fields[14] = new Property("Hotel",4000,2250,22);
        fields[15] = new Property("Palace",5000,2750,23);
        fields[16] = new SpecialField("Start", 0);
        fields[17] = new SpecialField("Chance", 3);
        fields[18] = new SpecialField("Jail", 6);
        fields[19] = new SpecialField("Chance", 9);
        fields[20] = new SpecialField("Police", 12);
        fields[21] = new SpecialField("Chance", 15);
        fields[22] = new SpecialField("Fee", 18);
        fields[23] = new SpecialField("Chance", 21);

        return fields;
    }

}

