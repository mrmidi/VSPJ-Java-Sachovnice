package com.company;

import java.util.Scanner;


public class HraSachovniceManual extends HraSachovnice implements Cloneable {
    //int[] pos = new int[]{0,0};
    Boolean firstDraw = true;
    int moveCount = 0;
    byte size_x, size_y, actual_x, actual_y;



    //constructor w/o parameters
    public HraSachovniceManual() {
        size_x = 7;
        size_y = 7;
        actual_x = 0;
        actual_y = 0;
    }

    //constructor with parameters
    public HraSachovniceManual(byte size_x, byte size_y, byte actual_x, byte actual_y) {
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
        return isInBounds();
    }

    public boolean can_down() {
        return  isInBounds();
    }

    public boolean can_left() {
        return isInBounds();
    }

    public boolean can_right() {
        return isInBounds();
    }

    //what for?!
    public boolean can_move() {
        return isInBounds();
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
        if (isInBounds()) {
            this.actual_x++;
        }

    }
    public void moveLeft() {
        if (isInBounds()) {
            this.actual_x--;
        }
    }
    public void moveUp() {
        if (isInBounds()) {
            this.actual_y++;
        }
    }
    public void moveDown() {
        if (isInBounds()) {
            this.actual_y--;
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
        nacti_direction();
    }
}
