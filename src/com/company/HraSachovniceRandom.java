package com.company;

import java.util.Random;
import java.util.Scanner;

public class HraSachovniceRandom extends HraSachovnice implements Cloneable {
    //int[] pos = new int[]{0,0};
    Boolean firstDraw = true;
    int moveCount = 0;
    byte size_x, size_y, actual_x, actual_y;



    //constructor w/o parameters
    public HraSachovniceRandom() {
        size_x = 7;
        size_y = 7;
        actual_x = 0;
        actual_y = 0;
    }

    //constructor with parameters
    public HraSachovniceRandom(byte size_x, byte size_y, byte actual_x, byte actual_y) {
        this.size_x = size_x;
        this.size_y = size_y;
        this.actual_x = actual_x;
        this.actual_y = actual_x;
        if (!test()) { //set default size and position if initialized not properly
            this.size_x = 7;
            this.size_y = 7;
            this.actual_x = 0;
            this.actual_y = 0;
        }
    }

    public String toString() {
        return "Current position is: [" + toLetter() + "," + (this.actual_y + 1) + "]. Total moves: " + moveCount;
    }

//    public HraSachovnice(int[] pos) {
//        this.pos = pos;
//    }
//
//    public void getPos() {
//        System.out.println("Current position is: [" + toLetter() + "," + (this.pos[1] + 1) + "]");
//    }

    private String toLetter() {
        char letter = (char)(65 + this.actual_x);
        return String.valueOf(letter);
    }

    public boolean isInBounds() {
        if (this.actual_x == 0 || this.actual_y == 0) { return false; }
        if (this.actual_x == this.size_x || this.actual_y == this.size_y) { return false; }
        return true;
    }

    public boolean test() {
        if (this.size_x <= 0 && this.size_y <= 0) {
            return false; }
        if (this.actual_x > size_x || this.actual_y > size_y) { return false; }
        return true;
    }

    //doesn't need this, but to be accomplished with TOR
    public boolean can_up() {
        if (this.actual_y + 1 > this.size_y) { return false; }
        return true;
    }

    public boolean can_down() {
        if (this.actual_y - 1 < 0) { return false; }
        return true;
    }

    public boolean can_left() {
        if (this.actual_x - 1 > 0) { return false; }
        return true;
    }

    public boolean can_right() {
        if (this.actual_y + 1 > this.size_y) { return false; }
        return true;
    }

    public boolean can_move(String direction) {
        direction = direction.toLowerCase();
        if (direction.equals("up")) { return can_up(); }
        else if (direction.equals("down")) {return can_down(); }
        else if (direction.equals("left")) {return can_left(); }
        else return can_right();

    }

    public void nacti_direction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hit WSAD to move your figure on the board: ");
        String cmd = scanner.nextLine();
        switch (cmd) {
            case "w":
                moveUp();
                break;
            case "s":
                moveDown();
                break;
            case "a":
                moveLeft();
                break;
            case "d":
                moveRight();
                break;
        }
    }

    public void moveRight() {
        if (can_right()) {
            this.actual_x++;
        }

    }
    public void moveLeft() {
        if (can_left()) {
            this.actual_x--;
        }
    }
    public void moveUp() {
        if (can_up()) {
            this.actual_y++;
        }
    }
    public void moveDown() {
        if (can_down()) {
            this.actual_y--;
        }
    }

    public void move(String direction) {
        switch (direction) {
            case "up":
                moveUp();
                break;
            case "down":
                moveDown();
                break;
            case "left":
                moveLeft();
                break;
            case "right":
                moveRight();
                break;
        }


    }

    //draws chessboard line by line from upper side to down
    public void drawGrid() {
        System.out.println(" -A-B-C-D-E-F-G-H-");
        for (int i = this.size_x; i > -1; i--) {
            drawLine(i);
        }
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void drawLine(int linenumber) {
        if (!this.firstDraw) { //TODO maybe clear console?
        }
        this.firstDraw = false;
        System.out.print(linenumber + 1 + "|");
        String c = " ";
        for (int i = 0; i <= this.size_x; i++) {
            //TODO write black and white squares
            if (this.actual_y == linenumber) { //checks if figure on that row
                //System.out.println("catch");
                if (this.actual_x == i) { //check if figore on that collumn
                    c = "♕";
                } else {
                    c = " ";
                }
            }
            System.out.print(c + "|");
        }

        System.out.println();
        System.out.println(" -----------------");
    }


    @Override
    void Execute() {
        boolean moved = false;
        String direction = gen();
        while (!moved) {
            if (can_move(direction)) {
                move(direction);
                moved = true;
            } else { direction = gen(); };
        }
        System.out.println(this);

    }

    private String gen() {
        int direction;
        Random random = new Random();
        direction = random.nextInt(0,4);
        System.out.println(direction);
        if (direction == 0) { return "up"; }
        else if (direction == 1) { return "down"; }
        else if (direction == 2 ) {return "left"; }
        else { return "right"; }
    }
/*
    public int[] genMove() {
        /*
        TODO Direction generator.
        for X values:
        0 stay at place
        1 move left
        2 move right
        for Y values:
        stay at place
        1 move up
        2 move down
        Main idea that figure can't stay, it should move somewhere.
        It can move diagonally.

        Random random = new Random();
        int rand_x;
        int rand_y;
        rand_x = random.nextInt(0, 2);
        rand_y = random.nextInt(0, 2);
        //todo write logging. temporary is here
        System.out.println("x: " + rand_x + " y: " + rand_y);

        while (can_left() && can_right() && can_up() && can_down()) {
            int cyclecount = 0;
            while (rand_x == 0 & rand_y == 0) {
                cyclecount++;
                System.out.println("both values are zeroes, regenerate numbers");
                rand_x = random.nextInt(0, 2);
                rand_y = random.nextInt(0, 2);
                System.out.println("x: " + rand_x + " y: " + rand_y);
            }
            System.out.println("can't move, regenerate numbers");

        }

        int[] pos = {0, 0};
        return pos;

    }
    */
}
