package edu.guilford;
import java.util.Random;
import java.util.Arrays;

public class Dice {
    Random random = new Random();

    //attributes
    private int numSides;
    private int numDice;
    private int[] diceRolls;
    private String color;

    //constructor
    public Dice(){ //no longer random
        this.numSides = 6;
        this.numDice = 2;
        this.diceRolls = new int[numDice];
        this.color = "white";
    }

    public Dice(int numSides, int numDice, String color){
        this.numSides = numSides;
        this.numDice = numDice;
        this.diceRolls = new int[numDice];
        this.color = color;
    }


    //methods
    public int[] rollDice(){
        for (int i = 0; i < numDice; i++){
            diceRolls[i] = (int)(Math.random() * numSides) + 1;
        }
        return diceRolls;
    }

    public int getNumSides(){
        return numSides;
    }

    public int getNumDice(){
        return numDice;
    }

    public int[] getDiceRolls(){
        return diceRolls;
    }

    public void setNumSides(int numSides){
        this.numSides = numSides;
    }

    public void setNumDice(int numDice){
        this.numDice = numDice;
    }

    public void setDiceRolls(int[] diceRolls){
        this.diceRolls = diceRolls;
    }

    public void setColor(String color){
        this.color = color;
    }

    public String getColor(){
        return color;
    }

    public String toString(){
        String output = "";
        for (int i = 0; i < numDice; i++){
            output += diceRolls[i] + " ";
        }
        return output;
    }

    public int sumDice(){
        // int sum = 0;
        // for (int i = 0; i < numDice; i++){
        //     sum += diceRolls[i];
        // }
        int sum = Arrays.stream(diceRolls).sum(); //looked up the specific method first, copilot finished it :)
        return sum;
    }

    public int productDice(){ //copilot :o
        int product = 1;
        for (int i = 0; i < numDice; i++){
            product *= diceRolls[i];
        }
        return product;
    }
    public static void main(String[] args){
        Dice d = new Dice(6, 2,"blue");
        System.out.println(d);
        d.rollDice();
        System.out.println(d);
        System.out.println("Sum of dice: " + d.sumDice());
        System.out.println("Product of dice: " + d.productDice());
        Dice d2 = new Dice();
        d2.rollDice();
        System.out.println(d2);
        System.out.println("Sum of dice: " + d2.sumDice());
        System.out.println("Product of dice: " + d2.productDice());
    }
    
}
