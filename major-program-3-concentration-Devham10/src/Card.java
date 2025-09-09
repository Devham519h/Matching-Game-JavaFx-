// Devin Hamilton
// 4/21/25
// Comp167 Section 003
// This is a Stack Pane that will be used to control the
// display of the Object (card)

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Card extends StackPane {
    private boolean flipped;
    private boolean matched;
    private String path;
    private Image image;
    private ImageView imageView;
    private int numRows;
    private int numCols;
    private Rectangle frontCard;

    /**
     *
     * @param givenPath
     */
    public Card(String givenPath){
        this.setFlipped(false);
        this.setMatched(false);
        this.setPath(givenPath);
        this.setImage(null);
        this.setNumCols(0);
        this.setNumRows(0);
        this.setImageView(new ImageView());
        setHeight(64);
        setWidth(64);

        setFrontCard(new Rectangle(getWidth(),getHeight()));
        getFrontCard().setFill(Color.BLUE); //This will be what shows at the beginning of the game
        this.getChildren().add(getFrontCard());
    } //constructor
    public Card(){
        this.setFlipped(false);
        this.setMatched(false);
        this.setPath(null);
        this.setImage(null);
        this.setNumCols(0);
        this.setNumRows(0);
        this.setImageView(new ImageView());
        setHeight(64);
        setWidth(64);

        setFrontCard(new Rectangle(getWidth(),getHeight()));
        getFrontCard().setFill(Color.BLUE); //This will be what shows at the beginning of the game
        this.getChildren().add(getFrontCard());
    } //no-arg

    /**
     *
     * @param width
     * @param height
     */
    public void setCardAndImageSize(int width, int height){
        this.setWidth(width);
        this.setHeight(height);
        if(imageView != null){
            this.imageView.setFitWidth(width);
            this.imageView.setFitHeight(height);
            imageView.setPreserveRatio(false); //makes the images fit the cardSize
        }
        getFrontCard().setHeight(height);
        getFrontCard().setWidth(width);
    }

    public void flipCard(){
        this.getChildren().clear(); //Clears what is currently in the pane

        if(isMatched()){return;} //keeps matched cards from flipping

        if(isFlipped()){
            // If already flipped, show front (blue side)
            this.getChildren().add(this.getFrontCard());
            setFlipped(false);
        } else {
            // If not flipped, show back (image side)
            if (this.imageView != null){
                imageView.setFitWidth(getWidth());
                imageView.setFitHeight(getHeight());
                imageView.setPreserveRatio(false); //this makes the images fit the card
                this.getChildren().add(this.imageView);
                setFlipped(true);
            }
        }
    }

    /**
     *
     * @param path
     */
    public void setPath(String path){
        this.path = path;
        if(path != null){
            this.image = new Image(getPath());
            setImageView(new ImageView(image));
            this.imageView.setFitHeight(getHeight());
            this.imageView.setFitHeight(getWidth());
            imageView.setPreserveRatio(false);
        }
    }
    public String getPath() {return path;}

    /**
     *
     * @param r
     * @param c
     */
    public void setGridPos(int r, int c){}

    /**
     *
     * @param nr
     * @param nc
     */
    public void setGridSize(int nr, int nc){}
    public boolean isFlipped() {return flipped;}

    /**
     *
     * @param flipped
     */
    public void setFlipped(boolean flipped) {this.flipped = flipped;}
    public boolean isMatched() {return matched;}

    /**
     *
     * @param matched
     */
    public void setMatched(boolean matched) {this.matched = matched;}
    public Image getImage() {return image;}

    /**
     *
     * @param image
     */
    public void setImage(Image image) {this.image = image;}
    public ImageView getImageView() {return imageView;}

    /**
     *
     * @param imageView
     */
    public void setImageView(ImageView imageView) {this.imageView = imageView;}
    public int getNumRows() {return numRows;}

    /**
     *
     * @param numRows
     */
    public void setNumRows(int numRows) {this.numRows = numRows;}
    public int getNumCols() {return numCols;}

    /**
     *
     * @param numCols
     */
    public void setNumCols(int numCols) {this.numCols = numCols;}
    public Rectangle getFrontCard() {return frontCard;}

    /**
     *
     * @param frontCard
     */
    public void setFrontCard(Rectangle frontCard) {this.frontCard = frontCard;}
}
