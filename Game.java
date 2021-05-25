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
import javafx.scene.text.FontPosture;
import ticTacToe.logic.XOXO;

/**
 *
 * @author gyukim
 */
public class Game {
    private ArrayList<Button> buttons;
    private ArrayList<String> list;
    private XOXO xo;
    private Label turn;
    
    public Game(XOXO xo) {
        this.buttons = new ArrayList<>();
        this.list = new ArrayList<>();
        this.xo = xo;
        this.turn = new Label("Turn: " + this.xo.player());
        this.turn.setFont(Font.font("Courier New", 40));
        
        for(int i = 0; i < 9; i++) {
            this.buttons.add(new Button(" "));
        }
        
        for(Button b:this.buttons) {
            b.setFont(Font.font("Courier New", 40));
            b.setOnAction((event) -> {
                b.setText(this.xo.player());
                b.setDisable(true);
                this.list.add(this.xo.player());
                this.xo.play();
                this.turn.setText("Turn: " + this.xo.player());
                
                if(thereIsWin()) {
                    this.turn.setText("The end!");
                    for(Button v:this.buttons) {
                        v.setDisable(true);
                    }
                }       
                
            });
        }
    }
    
    public Parent getView() {
        BorderPane finalLayout = new BorderPane();
        
        GridPane layout = new GridPane();
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        
        int i = 0;
        for(int row = 0; row < 3; row++) {
            for(int column = 0; column < 3; column++) {
                layout.add(this.buttons.get(i), row, column);
                i++;
            }
        }
        
        finalLayout.setTop(this.turn);
        finalLayout.setCenter(layout);
        
        return finalLayout;
    }
    
    private boolean thereIsWin() {
        StringBuilder x = new StringBuilder();
        StringBuilder o = new StringBuilder();
        int helper = 0;
        
        for(String player:this.list) {
            if(player.equals("X")) {
                x.append(String.valueOf(helper));
            } else if(player.equals("O")) {
                o.append(String.valueOf(helper));
            }
            helper++;
        }
        
        ArrayList<String> possibleWins = new ArrayList<>();
        possibleWins.add("012");
        possibleWins.add("345");
        possibleWins.add("678");
        possibleWins.add("036");
        possibleWins.add("147");
        possibleWins.add("258");
        possibleWins.add("048");
        possibleWins.add("246");
        
        for(String win:possibleWins) {
            if(x.toString().contains(win) || o.toString().contains(win)) {
                return true;
            }
        }
        
        return false;
    }
}
