package com.company;
import java.util.Scanner;

//test

public class Main {

    public static void main(String[] args) {
        System.out.println("Chess. Run with --game argument to make infinite moves. (c) 2021");

        if (args.equals("--game")) {
            gameMenu();
        }

        byte size_x, size_y, actual_x, actual_y;
        size_x = 7;
        size_y = 7;
        actual_x = 3;
        actual_y = 5;
        System.out.println("Initializing board...");
        HraSachovnice figure = new HraSachovnice(size_x, size_y, actual_x, actual_y);
        figure.drawGrid();
        System.out.println(figure);
        figure.nacti_direction();
        figure.drawGrid();
        System.out.println(figure);

        try {
            HraSachovnice fig2 = (HraSachovnice)figure.clone();
            System.out.println("We have succesfully cloned our object!");
            System.out.println(fig2);
        } catch (CloneNotSupportedException e) {
            System.out.println("Can't clone Object: " + e);
        }

    }

    public static void gameMenu(){
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
        System.exit(0);
    }
}
