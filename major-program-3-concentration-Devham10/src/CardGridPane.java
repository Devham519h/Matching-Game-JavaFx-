// Devin Hamilton
// 4/21/25
// Comp167 Section 003
// This is a GridPane that holds and controls the Card Objects

import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.Collections;

public class CardGridPane extends GridPane {
    private static final int MAXROWS = 8;
    private static final int MAXCOLS = 8;
    private Card[][] cards;
    private ArrayList<String> cardList;
    private int cardSize;
    private int currentRows;
    private int currentCols;

    public CardGridPane(){
        setCardSize(64);
        setCurrentRows(8);
        setCurrentCols(8);
        this.cardList = new ArrayList<String>();
        cards = new Card[MAXCOLS][MAXROWS];
        
        // Initialize cards
        for(int i=0; i < getCurrentCols(); i++){
            for (int j = 0; j < getCurrentRows(); j++){
                Card card = new Card();
                card.setCardAndImageSize(getCardSize(), getCardSize());
                cards[i][j] = card;
                this.add(card, i, j);
                card.setDisable(true);
            }
        }
        int imagesNeeded = (currentCols * currentRows) / 2;
        createCardImageList(imagesNeeded);
        shuffleImages();
        setCardImages();
        setGridLinesVisible(true);
    }
    /**
     *
     * @param cardSize
     */
    public CardGridPane(int cardSize){
        setCardSize(cardSize);
        setCurrentRows(8);
        setCurrentCols(8);
        this.cardList = new ArrayList<String>();
        cards = new Card[MAXCOLS][MAXROWS];
        
        // Initialize cards
        for(int i=0; i < getCurrentCols(); i++){
            for (int j = 0; j < getCurrentRows(); j++){
                Card card = new Card();
                card.setCardAndImageSize(getCardSize(), getCardSize());
                cards[i][j] = card;
                this.add(card, i, j);
                card.setDisable(true);
            }
        }
        int imagesNeeded = (currentCols * currentRows) / 2;
        createCardImageList(imagesNeeded);
        shuffleImages();
        setCardImages();
        setGridLinesVisible(true);
    }

    public void setCardImages(){
        int index = 0;
        if (cardList != null && !cardList.isEmpty()){
            for(int i = 0; i < currentCols;i++){
                for(int j = 0; j < currentRows;j++){
                    if((cards[i][j] != null) && (index < cardList.size())){
                        cards[i][j].setPath(cardList.get(index));
                        index++;
                    }
                }
            }
        }
    }
    public void shuffleImages(){Collections.shuffle(cardList);}
    /**
     *
     * @param r
     * @param c
     * @return
     */
    public Card getCard(int r, int c){return cards[r][c];}
    /**
     *
     * @param rows
     * @param cols
     */
    public void initCards(int rows, int cols) {
        // Clear the grid first
        this.getChildren().clear();
        
        // Update dimensions
        setCurrentRows(rows);
        setCurrentCols(cols);
        this.setGridLinesVisible(false);
        // Add only the cards needed for current dimensions
        for(int i=0; i < getCurrentCols(); i++){
            for (int j = 0; j < getCurrentRows(); j++){
                // Create a new card for each position in the grid
                Card card = new Card();
                card.setCardAndImageSize(getCardSize(), getCardSize());
                cards[i][j] = card;
                // Add to grid
                this.add(card, i, j);
            }
        }
        // Create card image list
        cardList.clear(); //clear what may be in the ArrayList
        int imagesNeeded = (currentCols * currentRows) / 2;
        createCardImageList(imagesNeeded);
        shuffleImages();
        setCardImages();
        this.setGridLinesVisible(true);
    }
    /**
     *
     * @param size
     */
    public void createCardImageList(int size){
        cardList.clear();
        int counter = 0;
        for (int i = 0; i < size; i++) {
            //add two of the same image for card matches
            addCardList("images/image_" + counter + ".jpg");
            addCardList("images/image_" + counter + ".jpg");
            counter++;
        }
    }
    public Card[][] getCards() {return cards;}
    /**
     *
     * @param cards
     */
    public void setCards(Card[][] cards) {this.cards = cards;}
    public int getCardSize() {return cardSize;}
    /**
     *
     * @param cardSize
     */
    public void setCardSize(int cardSize) {this.cardSize = cardSize;}
    public int getCurrentRows() {return currentRows;}
    /**
     *
     * @param currentRows
     */
    public void setCurrentRows(int currentRows) {this.currentRows = currentRows;}
    public int getCurrentCols() {return currentCols;}
    /**
     *
     * @param currentCols
     */
    public void setCurrentCols(int currentCols) {this.currentCols = currentCols;}
    public int getCardListSize(){return cardList.size();}
    /**
     *
     * @param index
     * @return
     */
    public String getCardList(int index) {
        if(index >= 0 && index < cardList.size()){return cardList.get(index);}
        return null;
    }
    /**
     *
     * @param index
     * @return
     */
    public String removeCardList(int index) {
        if(index >= 0 && index < cardList.size()){return cardList.remove(index);}
        return null;
    }
    /**
     *
     * @param index
     * @param cardPath
     */
    public void setCardList(int index,String cardPath) {
        if(index >= 0 && index < cardList.size()){cardList.set(index,cardPath);}
    }
    /**
     *
     * @param cardPath
     */
    public void addCardList(String cardPath){cardList.add(cardPath);}
}