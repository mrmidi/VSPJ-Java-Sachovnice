package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //System.out.println(TheNoDefFoundClass.class.getProtectionDomain().getCodeSource().getLocation());
	// write your code here
        // int[] pos = new int[]{5, 7}; //initial position
        // HraSachovnice figure = new HraSachovnice(pos); //initialize with position
        HraSachovnice figure = new HraSachovnice(); //start at 0,0 with 8x8 board
        Scanner input = new Scanner(System.in);
        String key;
        System.out.println("That is the chess board. Move you figure with \"WSAD\" keys. Hit \"K\" to quit");
        do {
            figure.drawGrid();
            System.out.println(figure);
            key = input.nextLine().toLowerCase();
            switch (key) {
                case "w" ->
                        //up
                        figure.moveUp();
                case "s" ->
                        //down
                        figure.moveDown();
                case "a" ->
                        //left
                        figure.moveLeft();
                case "d" ->
                        //right
                        figure.moveRight();
                default -> System.out.println("Wrong key! \"WSAD\" to move and \"K\" to quit");
            }
        } while (!key.equals("k"));
        System.out.println("bye!");

    }
}
