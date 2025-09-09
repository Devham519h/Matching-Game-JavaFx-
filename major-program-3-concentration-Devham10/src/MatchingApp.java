// Devin Hamilton
// 4/21/25
// Comp167 Section 003
// This is where the game application will be set and launched
// Game will consist of cards with images of Duke Basketball Players

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MatchingApp extends Application {
    /**
     *
     * @param appStage
     */
    @Override
    public void start(Stage appStage) {
        //Initializes a GamePane with 64x64 size cards in a (row x column) GridPane
        GamePane gamePane = new GamePane(64);
        Scene scene = new Scene(gamePane, 575,575);
        appStage.setTitle("Duke Matching Game");
        appStage.setScene(scene);
        appStage.show(); //opens the application window
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {launch(args);}
}
