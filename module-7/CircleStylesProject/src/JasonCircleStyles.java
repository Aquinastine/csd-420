/**
 * Jason Luttrell CSD 420 Module 7 Programming Assignment
 * Circle Styles
 *
 * Module 7 Programming Assignment
 * Demonstrates JavaFX CSS class and ID usage
 * Date: February 5, 2026
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class JasonCircleStyles extends Application {

    @Override
    public void start(Stage stage) {

        // Circles
        Circle c1 = new Circle(40);
        Circle c2 = new Circle(40);
        Circle c3 = new Circle(40);
        Circle c4 = new Circle(40);

        // Apply CSS class and IDs (must match your jasonstyle.css)
        c1.getStyleClass().add("plaincircle");
        c2.getStyleClass().add("plaincircle");
        c3.setId("redcircle");
        c4.setId("greencircle");

        // Optional: keep stroke consistent on colored circles (your CSS already sets stroke)
        // c3.setStroke(null);
        // c4.setStroke(null);

        // Left tall bordered container (like the screenshot)
        StackPane leftBox = new StackPane(c1);
        leftBox.getStyleClass().add("border");
        leftBox.setPrefSize(120, 320);     // tall rectangle
        leftBox.setAlignment(Pos.CENTER);  // center circle in the box

        // Main row
        HBox root = new HBox(25, leftBox, c2, c3, c4);
        root.setAlignment(Pos.CENTER_LEFT);
        root.setStyle("-fx-padding: 30; -fx-background-color: white;");

        Scene scene = new Scene(root, 520, 260);

        // Load your CSS file
        scene.getStylesheets().add(
                getClass().getResource("jasonstyle.css").toExternalForm()
        );

        stage.setTitle("Exercise31_01");
        stage.setScene(scene);
        stage.show();

        // Test method to verify styles applied correctly
        runTests(c1, c2, c3, c4, leftBox);
    }

    private void runTests(Circle c1, Circle c2, Circle c3, Circle c4, StackPane leftBox) {

        System.out.println("Running UI tests...");

        // Test 1: All circles are created
        assert c1 != null && c2 != null && c3 != null && c4 != null
                : "One or more circles were not created";

        // Test 2: White/plain circles have correct CSS class
        assert c1.getStyleClass().contains("plaincircle")
                : "First circle missing 'plaincircle' style";
        assert c2.getStyleClass().contains("plaincircle")
                : "Second circle missing 'plaincircle' style";

        // Test 3: Colored circles have correct IDs
        assert "redcircle".equals(c3.getId())
                : "Third circle should have id 'redcircle'";
        assert "greencircle".equals(c4.getId())
                : "Fourth circle should have id 'greencircle'";

        // Test 4: Left container has border style
        assert leftBox.getStyleClass().contains("border")
                : "Left container missing 'border' style";

        // Test 5: Layout sanity check
        assert leftBox.getChildren().contains(c1)
                : "First circle not inside left border container";

        System.out.println("All tests passed successfully.");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
