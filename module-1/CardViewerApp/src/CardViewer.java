//Jason Luttrell CSD 420 Module 1 Programming Assignment
//Card Viewer Application
//Displays four random playing cards from a standard 52-card deck.

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardViewer extends Application {

    private static final int NUM_CARDS = 52;   // total cards in deck
    private static final int NUM_TO_SHOW = 4;  // cards to display

    private HBox cardBox; // holds the four card ImageViews

    @Override
    public void start(Stage primaryStage) {
        cardBox = new HBox(10);  // spacing of 10 between cards

        // Button with a lambda event handler
        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> showRandomCards()); // <-- Lambda expression

        // Layout: cards on top, button below
        VBox root = new VBox(15);
        root.getChildren().addAll(cardBox, refreshButton);

        // Show initial set of cards
        showRandomCards();

        Scene scene = new Scene(root, 600, 300);
        primaryStage.setTitle("Random Card Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Randomly selects four *different* cards and displays them.
     */
    private void showRandomCards() {
        // Make a list [1, 2, ..., 52]
        List<Integer> deck = new ArrayList<>();
        for (int i = 1; i <= NUM_CARDS; i++) {
            deck.add(i);
        }

        // Shuffle and take the first 4
        Collections.shuffle(deck);
        List<Integer> selected = deck.subList(0, NUM_TO_SHOW);

        // Clear previous images
        cardBox.getChildren().clear();

        // Load and display the selected cards
        for (int cardNumber : selected) {
            // Assumes images are in a folder named "cards" in the working directory
            String path = "file:Cards/" + cardNumber + ".png";
            Image image = new Image(path);
            ImageView imageView = new ImageView(image);

            // Optional: size the cards nicely
            imageView.setFitHeight(180);
            imageView.setPreserveRatio(true);

            cardBox.getChildren().add(imageView);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
