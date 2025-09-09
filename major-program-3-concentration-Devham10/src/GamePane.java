// Devin Hamilton
// 4/21/25
// Comp167 Section 003
// This is a BorderPane that will hold the Grid of Card Objects
// and its controls

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import java.io.File;

public class GamePane extends BorderPane {
    private int turns;
    private int rows;
    private int cols;
    private int numClicks;
    private int numMatched;
    private Card clickedCardOne;
    private Card clickedCardTwo;
    private CardGridPane cardGridPane;
    private CardMatchTimer cardMatchTimer;
    private CommandPane commandPane;

    public GamePane() {
        this.turns = 0;
        this.cardGridPane = new CardGridPane(100);
        this.commandPane = new CommandPane();
        this.cardGridPane.setAlignment(Pos.CENTER);
        this.setCenter(cardGridPane);
        this.setBottom(commandPane);
        this.registerCardListeners();
    }

    /**
     *
     * @param cardSize
     */
    public GamePane(int cardSize){
        this.turns = 0;
        this.cardGridPane = new CardGridPane(cardSize);
        this.commandPane = new CommandPane();
        this.cardGridPane.setAlignment(Pos.CENTER);
        this.setCenter(cardGridPane);
        this.setBottom(commandPane);
        this.registerCardListeners();
    }
    public void newGame() {
        CommandPane commandPane = new CommandPane();
        commandPane.getNewGameButton().getOnMouseClicked();
    }
    public void registerCardListeners() {
        for (int i = 0; i < getCols(); i++) {
            for (int j = 0; j < getRows(); j++) {
                Card card = cardGridPane.getCard(i, j);
                card.setOnMousePressed(click -> {
                    if (!card.isFlipped() && !card.isMatched()) {
                        if (numClicks == 0) {
                            clickedCardOne = card;
                            card.flipCard();
                            numClicks++;
                        } else if (numClicks == 1) {
                            clickedCardTwo = card;
                            card.flipCard();
                            this.cardMatchTimer = new CardMatchTimer();
                            cardMatchTimer.start();
                        }
                    }
                });
            }
        }
    }
    private class CommandPane extends HBox {
        private Button newGameButton;
        private Button exitButton;
        private ComboBox<String> dropDown;
        private Label lblTurns;
        public CommandPane() {
            setNewGameButton(new Button("New Game"));
            setExitButton(new Button("Exit"));
            this.dropDown = new ComboBox<>();
            this.setLblTurns(new Label("Turns: "));
            this.dropDown.getItems().addAll(
                "Level 1", "Level 2", "Level 3",
                "Level 4", "Level 5", "Level 6"
            );

            //layout of the HBox
            this.getChildren().addAll(getNewGameButton(), getExitButton(), dropDown, getLblTurns());
            setSpacing(10);
            setPadding(new Insets(10));
            setAlignment(Pos.CENTER);
            
            //button controls
            dropDown.setOnAction(click -> {
                if (dropDown.getValue() == "Level 1") {
                    cardGridPane.initCards(2, 3);
                    setRows(2);
                    setCols(3);
                    registerCardListeners();
                } else if (dropDown.getValue() == "Level 2") {
                    cardGridPane.initCards(2, 4);
                    setCols(4);
                    setRows(2);
                    registerCardListeners();
                } else if (dropDown.getValue() == "Level 3") {
                    cardGridPane.initCards(4, 4);
                    setRows(4);
                    setCols(4);
                    registerCardListeners();
                } else if (dropDown.getValue() == "Level 4") {
                    setCols(6);
                    setRows(4);
                    cardGridPane.initCards(4, 6);
                    registerCardListeners();
                } else if (dropDown.getValue() == "Level 5") {
                    setRows(6);
                    setCols(6);
                    cardGridPane.initCards(6, 6);
                    registerCardListeners();
                } else if (dropDown.getValue() == "Level 6") {
                    setRows(8);
                    setCols(8);
                    cardGridPane.initCards(8, 8);
                    registerCardListeners();
                }
                numClicks = 0;
                numMatched = 0;
                clickedCardOne = null;
                clickedCardTwo = null;
                turns = 0;
                commandPane.updateLblTurns(turns);
            });
            getNewGameButton().setOnMousePressed(click-> {
                if (getNewGameButton().isPressed()) {
                    int rows = cardGridPane.getCurrentRows();
                    int cols = cardGridPane.getCurrentCols();
                    cardGridPane.initCards(rows,cols);
                    registerCardListeners();
                    turns = 0;
                    commandPane.updateLblTurns(turns);
                }});
            getExitButton().setOnMousePressed(click ->{
                if(exitButton.isPressed()){
                    System.exit(0);
                }
            });
        }

        /**
         *
         * @param turns
         */
        public void updateLblTurns(int turns){lblTurns.setText("Turns: " + turns);}
        public ComboBox<String> getDropDown() { return dropDown; }
        public Button getNewGameButton() {return newGameButton;}

        /**
         *
         * @param newGameButton
         */
        public void setNewGameButton(Button newGameButton) {this.newGameButton = newGameButton;}
        public Button getExitButton() {return exitButton;}

        /**
         *
         * @param exitButton
         */
        public void setExitButton(Button exitButton) {this.exitButton = exitButton;}
        public Label getLblTurns() {return lblTurns;}

        /**
         *
         * @param lblTurns
         */
        public void setLblTurns(Label lblTurns) {this.lblTurns = lblTurns;}
    }
    public int getRows() {return rows;}

    /**
     *
     * @param rows
     */
    public void setRows(int rows) {this.rows = rows;}
    public int getCols() {return cols;}

    /**
     *
     * @param cols
     */
    public void setCols(int cols) {this.cols = cols;}
    public int getNumClicks() {return numClicks;}

    /**
     *
     * @param numClicks
     */
    public void setNumClicks(int numClicks) {this.numClicks = numClicks;}
    public int getNumMatched() {return numMatched;}

    /**
     *
     * @param numMatched
     */
    public void setNumMatched(int numMatched) {this.numMatched = numMatched;}
    public Card getClickedCardOne() {return clickedCardOne;}

    /**
     *
     * @param clickedCardOne
     */
    public void setClickedCardOne(Card clickedCardOne) {this.clickedCardOne = clickedCardOne;}
    public Card getClickedCardTwo() {return clickedCardTwo;}

    /**
     *
     * @param clickedCardTwo
     */
    public void setClickedCardTwo(Card clickedCardTwo) {this.clickedCardTwo = clickedCardTwo;}
    public CardGridPane getCardGridPane() {return cardGridPane;}

    /**
     *
     * @param cardGridPane
     */
    public void setCardGridPane(CardGridPane cardGridPane) {this.cardGridPane = cardGridPane;}

    /**
     *  This AnimationTimer will handle the matching of the two clicked cards,
     *  the updating of the turns label and the display of the alert.
     */
    private class CardMatchTimer extends AnimationTimer {
        private long startTime;
        private AudioClip audioClip1;
        private AudioClip audioClip2;
        public CardMatchTimer() {
            startTime = System.nanoTime();
            //you will have to change these to your own directory when you download the sound files
            File wrongSound = new File("C:\\Users\\narut\\IdeaProjects\\major-program-3-concentration-Devham10\\sounds\\WrongSound.mp4");
            File correctSound = new File("C:\\Users\\narut\\IdeaProjects\\major-program-3-concentration-Devham10\\sounds\\Correct.wav");
            //correctly acquires the string path using the toURI() method
            audioClip1 = new AudioClip(correctSound.toURI().toString());
            audioClip2 = new AudioClip(wrongSound.toURI().toString());
        }

        /**
         *
         * @param now
         */
        @Override
        public void handle(long now) {
            // Check if 800ms (0.8 seconds) have passed
            if ((now - startTime) / 1000000 >= 800) {
                // Check if cards match and both cards are not null
                if (clickedCardOne != null && clickedCardTwo != null) {
                    turns++;
                    commandPane.updateLblTurns(turns);
                    if (clickedCardOne.getPath() != null && clickedCardTwo.getPath() != null &&
                            clickedCardOne.getPath().equals(clickedCardTwo.getPath())) {
                        audioClip1.play(); //plays correctSound
                        clickedCardTwo.flipCard();
                        clickedCardOne.flipCard();

                        // Change image to black to show that the cards are a match
                        clickedCardOne.getFrontCard().setFill(Color.BLACK);
                        clickedCardTwo.getFrontCard().setFill(Color.BLACK);

                        // set cards match property to true
                        clickedCardOne.setMatched(true);
                        clickedCardTwo.setMatched(true);

                        // Increment match counter
                        numMatched += 2;

                        /* Show alert if all cards are matched
                         Platform.runLater() ensures that the alert will show after
                         the UI is done updating. I was having issues with the last
                         two matched cards updating, so this ensures they execute first. */
                        if (numMatched == getCols() * getRows()) {
                            Platform.runLater(() -> {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText("LEVEL COMPLETED!");
                                alert.setContentText("YOU HAVE MATCHED ALL THE CARDS!");
                                alert.showAndWait();
                                numMatched = 0; //reset
                            });
                        }
                    }
                    else {
                        // Cards don't match - flip them back
                        if (clickedCardOne.isFlipped()){clickedCardOne.flipCard();}
                        if (clickedCardTwo.isFlipped()){clickedCardTwo.flipCard();}
                        audioClip2.play(); //plays wrongSound
                    }
                    // Reset clicked cards
                    clickedCardOne = null;
                    clickedCardTwo = null;
                    numClicks = 0;
                }
                this.stop(); // Stop the timer
            }
        }
    }
}

