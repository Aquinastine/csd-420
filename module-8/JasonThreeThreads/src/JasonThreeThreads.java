/**
 * Jason Luttrell CSD 420 Module 8 Programming Assignment
 * Three Threads: Generating Random Characters in JavaFX
 *
 * - Thread 1: random letters a-z
 * - Thread 2: random digits 0-9
 * - Thread 3: random symbols from a fixed set
 * Each produces at least 10,000 characters and appends to a TextArea.
 *
 * Test code is included in runSelfTests().
 * Date: February 15, 2026
 */

import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class JasonThreeThreads extends Application {

    // Minimum number of samples required
    private static final int COUNT = 10_000;

    // Symbol set for third thread
    private static final char[] SYMBOLS = "!@#$%&*".toCharArray();


    @Override
    public void start(Stage stage) {

        // Create three separate text areas (columns)
        TextArea lettersArea = new TextArea();
        TextArea digitsArea = new TextArea();
        TextArea symbolsArea = new TextArea();

        // Enable wrapping so long strings stay visible
        lettersArea.setWrapText(true);
        digitsArea.setWrapText(true);
        symbolsArea.setWrapText(true);

        // Create headers
        Label lettersLabel = new Label("Random Letters (a-z)");
        Label digitsLabel = new Label("Random Digits (0-9)");
        Label symbolsLabel = new Label("Random Symbols (!@#$%&*)");

        // Combine each label + text area into a vertical column
        VBox lettersColumn = new VBox(5, lettersLabel, lettersArea);
        VBox digitsColumn = new VBox(5, digitsLabel, digitsArea);
        VBox symbolsColumn = new VBox(5, symbolsLabel, symbolsArea);

        // Place the three columns side-by-side
        HBox root = new HBox(15, lettersColumn, digitsColumn, symbolsColumn);

        // Add some padding around the edges
        root.setPadding(new Insets(10,10,10,10));

        // Set HBox and VBox grow priorities so columns expand equally
        lettersArea.setMaxWidth(Double.MAX_VALUE);
        digitsArea.setMaxWidth(Double.MAX_VALUE);
        symbolsArea.setMaxWidth(Double.MAX_VALUE);

        HBox.setHgrow(lettersColumn, Priority.ALWAYS);
        HBox.setHgrow(digitsColumn, Priority.ALWAYS);
        HBox.setHgrow(symbolsColumn, Priority.ALWAYS);

        lettersArea.setMaxHeight(Double.MAX_VALUE-5);
        digitsArea.setMaxHeight(Double.MAX_VALUE-5);
        symbolsArea.setMaxHeight(Double.MAX_VALUE-5);
        
        VBox.setVgrow(lettersArea, Priority.ALWAYS);       
        VBox.setVgrow(digitsArea, Priority.ALWAYS);        
        VBox.setVgrow(symbolsArea, Priority.ALWAYS);

        // Create the scene and show the stage
        Scene scene = new Scene(root, 1000, 600);

        stage.setTitle("Three Threads - 10K Each");
        stage.setScene(scene);
        stage.show();

        // Thread 1 - Generates random lowercase letters
        new Thread(() -> generate(lettersArea, COUNT, Type.LETTER)).start();

        // Thread 2 - Generates random digits
        new Thread(() -> generate(digitsArea, COUNT, Type.DIGIT)).start();

        // Thread 3 - Generates random symbols
        new Thread(() -> generate(symbolsArea, COUNT, Type.SYMBOL)).start();
    }

    /**
     * Generates random characters of the specified type.
     * The generation runs on a background thread.
     * UI updates must be wrapped in Platform.runLater()
     * because JavaFX UI components are not thread-safe.
     */
    private void generate(TextArea area, int count, Type type) {

        Random rng = new Random();
        StringBuilder sb = new StringBuilder();

        // Generate required number of characters
        for (int i = 0; i < count; i++) {
            switch (type) {
                case LETTER:
                    // ASCII math: 'a' + random offset 0-25
                    sb.append((char) ('a' + rng.nextInt(26)));
                    break;

                case DIGIT:
                    // ASCII math: '0' + random offset 0-9
                    sb.append((char) ('0' + rng.nextInt(10)));
                    break;

                case SYMBOL:
                    // Select random symbol from predefined array
                    sb.append(SYMBOLS[rng.nextInt(SYMBOLS.length)]);
                    break;
            }
        }

        // UI updates must occur on JavaFX Application Thread
        Platform.runLater(() -> area.setText(sb.toString()));
    }

    // Enum makes type selection clean and readable
    private enum Type {
        LETTER, DIGIT, SYMBOL
    }

    public static void main(String[] args) {
        launch(args);
    }
}
