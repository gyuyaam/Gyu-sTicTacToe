/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import ticTacToe.logic.XOXO;

/**
 *
 * @author gyukim
 */
public class Game {
    private final ArrayList<Button> buttons;
    private StringBuilder xs;
    private StringBuilder os;
    private final XOXO xo;
    private final Label turn;
    private String winner;
    
    public Game(XOXO xo) {
        this.buttons = new ArrayList<>();
        this.xo = xo;
        //
        this.turn = new Label();
        this.winner = "";
        //
        this.xs = new StringBuilder();
        this.os = new StringBuilder();
    }
    
    public Parent getView() {
        BorderPane finalLayout = new BorderPane();
        
        GridPane layout = new GridPane();
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        
        this.turn.setText("Turn: " + this.xo.player());
        
        for(int i = 0; i < 9; i++) {
            this.buttons.add(new Button(" "));
        }
        int help = 0;
        for(int row = 0; row < 3; row++) {
            for(int column = 0; column < 3; column++) {
                layout.add(this.buttons.get(help), row, column);
                help++;
            }
        }
        
        for(Button button:this.buttons) {
            button.setFont(Font.font("Courier New", 40));
            button.setOnAction((event) -> { 
                button.setText(this.xo.player());
                
                if(this.xo.player().equals("X")) {
                    this.xs.append(String.valueOf(layout.getChildren().indexOf(button)));
                }
                if(this.xo.player().equals("O")) {
                    this.os.append(String.valueOf(layout.getChildren().indexOf(button)));
                }
                
                this.xo.play();
                button.setDisable(true);
                
                if(this.xo.turn() > 8) {
                    this.turn.setText("The end! No one wins D:" + this.xs + this.os);
                } else if(this.thereIsWin()) {
                    this.turn.setText("The end! " + this.winner + " wins! :D ");
                    for(Button b:this.buttons) {
                        b.setDisable(true);
                    }
                } else {
                    this.turn.setText("Turn: " + this.xo.player()); 
                }
            });
        }
        
        finalLayout.setTop(this.turn);
        finalLayout.setCenter(layout);
        
        return finalLayout;
    }
    
    private boolean thereIsWin() {
        ArrayList<String> possibleWins = new ArrayList<>();
        possibleWins.add("0,1,2");
        possibleWins.add("3,4,5");
        possibleWins.add("6,7,8");
        possibleWins.add("0,3,6");
        possibleWins.add("1,4,7");
        possibleWins.add("2,5,8");
        possibleWins.add("0,4,8");
        possibleWins.add("2,4,6");
        
        for(String win:possibleWins) {
            String[] parts = win.split(",");
            if(this.xs.toString().contains(parts[0]) && 
                this.xs.toString().contains(parts[1]) && 
                this.xs.toString().contains(parts[2])) {
                this.winner = "X";
                return true; 
            }
            if(this.os.toString().contains(parts[0]) && 
                this.os.toString().contains(parts[1]) && 
                this.os.toString().contains(parts[2])) {
                this.winner = "O";    
                return true;
            }
        }
        
        return false;
    }
}
