package ticTacToe;


import javafx.application.Application;

import javafx.scene.Scene;

import javafx.stage.Stage;
import ticTacToe.logic.XOXO;


public class TicTacToeApplication extends Application {


    public static void main(String[] args) {
        launch(TicTacToeApplication.class);
       
    }

    @Override
    public void start(Stage stage) throws Exception {

        XOXO xoxo = new XOXO();

        Game view = new Game(xoxo);

        Scene scene = new Scene(view.getView());
        stage.setScene(scene);
        stage.show();
        
    }
    
}
