/*  Jason Luttrell
    CSD 420 Advanced Java Programming
    Module 2 View Randoms Application
    December 14, 2025
    
This program "LuttrellDataFile.dat", and appends new
data each time it is run.

     */ 

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewRandomsApp extends Application {


    private static final String FILENAME = "data/LuttrellDataFile.dat";

    @Override
    public void start(Stage stage) {

        // Run writer first
        LuttrellWriteData.writeRandomData(FILENAME);

        // Set up TextArea to display file contents
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);

        StringBuilder fileContents = new StringBuilder();

        try (BufferedReader reader = 
            new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContents.append(line).append("\n");
            }
        } catch (IOException e) {
            fileContents.append("Data file not found.");
        }

        textArea.setText(fileContents.toString());

        VBox root = new VBox(textArea);
        Scene scene = new Scene(root, 400, 300);

        stage.setTitle("Jason Luttrell's Random Number Viewer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}
    
