/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe.logic;

/**
 *
 * @author gyukim
 */
public class XOXO {
    private String player;
    private int turn;
    
    public XOXO() {
        this.player = "X";
        this.turn = 0;
    }
    
    public void play() {
        if(this.turn == 0) {
            this.player = "O";
        } else if(this.turn % 2 == 1) {
            this.player = "X";
        } else {
            this.player = "O";
        }
        this.turn++;
    }
    
    public String player() {
        return this.player;
    }
    
    public int turn() {
        return this.turn;
    }
}
