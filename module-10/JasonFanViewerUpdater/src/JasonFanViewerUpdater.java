import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.sql.*;

/**
 * Jason Luttrell CSD 420 Module 10 Programming Assignment
 * 
 * FanViewerUpdater
 *
 * A simple JavaFX + JDBC application that:
 *  1) Displays a fan record by ID (user enters an ID, clicks Display)
 *  2) Updates the currently displayed fan record (user edits fields, clicks Update)
 *
 * Database:
 *  - databasedb
 *  - table: fans
 *  - columns: ID (PK), firstname, lastname, favoriteteam
 *
 * NOTE: This program does NOT create or drop the table.
 * 3/1/26
 */
public class JasonFanViewerUpdater extends Application {

    // --- Database connection settings ---
    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/databasedb?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "student1";
    private static final String DB_PASS = "pass";

    // --- UI controls ---
    private TextField idInput;          // user types the ID they want to load
    private TextField idDisplay;        // shows the loaded record ID (read-only)
    private TextField firstNameField;   // editable firstname for loaded record
    private TextField lastNameField;    // editable lastname for loaded record
    private TextField favoriteTeamField;// editable favoriteteam for loaded record
    private Label statusLabel;          // shows messages / errors to the user

    /**
     * JavaFX entry point for building the UI.
     */
    @Override
    public void start(Stage stage) {

        // --- Build input fields ---
        idInput = new TextField();
        idInput.setPromptText("Enter ID to load (e.g., 1)");

        // This field shows the record's ID once loaded; user should not edit it.
        idDisplay = new TextField();
        idDisplay.setEditable(false);

        firstNameField = new TextField();
        lastNameField = new TextField();
        favoriteTeamField = new TextField();

        // --- Buttons ---
        Button displayBtn = new Button("Display");
        Button updateBtn = new Button("Update");

        // Wire buttons to their actions
        displayBtn.setOnAction(e -> displayFan());
        updateBtn.setOnAction(e -> updateFan());

        // --- Status label ---
        statusLabel = new Label();

        // --- Layout using GridPane ---
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);

        // Row counter to keep layout clean and readable
        int r = 0;

        // Lookup row
        grid.add(new Label("Lookup ID:"), 0, r);
        grid.add(idInput, 1, r++);

        // Displayed record fields
        grid.add(new Label("ID:"), 0, r);
        grid.add(idDisplay, 1, r++);

        grid.add(new Label("First Name:"), 0, r);
        grid.add(firstNameField, 1, r++);

        grid.add(new Label("Last Name:"), 0, r);
        grid.add(lastNameField, 1, r++);

        grid.add(new Label("Favorite Team:"), 0, r);
        grid.add(favoriteTeamField, 1, r++);

        // Buttons row
        HBox buttons = new HBox(10, displayBtn, updateBtn);
        grid.add(buttons, 1, r++);

        // Status message row (spans 2 columns)
        grid.add(statusLabel, 0, r, 2, 1);

        // --- Scene and Stage ---
        Scene scene = new Scene(grid, 480, 280);
        stage.setTitle("Fan Viewer / Updater (JDBC)");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Loads a fan record by the ID entered in idInput.
     * If found, populates the display fields.
     * If not found, clears the fields and shows a message.
     */
    private void displayFan() {
        statusLabel.setText("");

        // Validate the ID entered in the lookup box
        Integer id = parseId(idInput.getText());
        if (id == null) return;

        // Parameterized query prevents SQL injection and handles typing cleanly
        String sql = "SELECT ID, firstname, lastname, favoriteteam FROM fans WHERE ID = ?";

        // Try-with-resources ensures connection and statement are closed automatically
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Set the parameter (?) to the requested ID
            ps.setInt(1, id);

            // Execute query and process result
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Record found: populate UI fields
                    idDisplay.setText(String.valueOf(rs.getInt("ID")));
                    firstNameField.setText(rs.getString("firstname"));
                    lastNameField.setText(rs.getString("lastname"));
                    favoriteTeamField.setText(rs.getString("favoriteteam"));
                    statusLabel.setText("Record loaded.");
                } else {
                    // No record found: clear UI fields
                    clearDisplayFields();
                    statusLabel.setText("No record found for ID " + id + ".");
                }
            }

        } catch (SQLException ex) {
            // Any DB error: clear and show the error message
            clearDisplayFields();
            statusLabel.setText("DB error while loading: " + ex.getMessage());
        }
    }

    /**
     * Updates the currently displayed fan record.
     * The ID comes from idDisplay (meaning the user must load a record first).
     * Uses a transaction (commit/rollback) for safety.
     */
    private void updateFan() {
        statusLabel.setText("");

        // We only update a record that is currently loaded/displayed
        Integer id = parseId(idDisplay.getText());
        if (id == null) {
            statusLabel.setText("Load a record first using Display.");
            return;
        }

        // Grab current values from the UI
        String fn = safeTrim(firstNameField.getText());
        String ln = safeTrim(lastNameField.getText());
        String ft = safeTrim(favoriteTeamField.getText());

        // Basic validation: no blank values
        if (fn.isEmpty() || ln.isEmpty() || ft.isEmpty()) {
            statusLabel.setText("First name, last name, and favorite team cannot be empty.");
            return;
        }

        // Enforce the varchar(25) requirement (prevents DB truncation errors)
        if (fn.length() > 25 || ln.length() > 25 || ft.length() > 25) {
            statusLabel.setText("Each field must be 25 characters or fewer.");
            return;
        }

        // Parameterized UPDATE statement
        String sql = "UPDATE fans SET firstname = ?, lastname = ?, favoriteteam = ? WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Use a transaction so we can roll back if something goes wrong
            conn.setAutoCommit(false);

            // Bind parameters in the correct order
            ps.setString(1, fn);
            ps.setString(2, ln);
            ps.setString(3, ft);
            ps.setInt(4, id);

            // Execute update; result is number of affected rows
            int rows = ps.executeUpdate();

            if (rows == 1) {
                // Success: commit transaction
                conn.commit();
                statusLabel.setText("Record updated successfully (ID " + id + ").");
            } else {
                // If 0 rows affected, the ID didn't exist (or something unexpected happened)
                conn.rollback();
                statusLabel.setText("Update failed (no matching record for ID " + id + ").");
            }

        } catch (SQLException ex) {
            // On exception, the connection closes; MySQL will effectively roll back if not committed
            statusLabel.setText("DB error while updating: " + ex.getMessage());
        }
    }

    /**
     * Parses and validates an ID string as a positive integer.
     * If invalid, sets an error in statusLabel and returns null.
     */
    private Integer parseId(String text) {
        String t = safeTrim(text);

        // Empty string = invalid
        if (t.isEmpty()) {
            statusLabel.setText("Please enter an ID.");
            return null;
        }

        try {
            int id = Integer.parseInt(t);

            // We only allow positive IDs
            if (id <= 0) {
                statusLabel.setText("ID must be a positive integer.");
                return null;
            }

            return id;
        } catch (NumberFormatException ex) {
            // Non-numeric input
            statusLabel.setText("ID must be an integer.");
            return null;
        }
    }

    /**
     * Clears the display/edit fields (not the lookup box).
     */
    private void clearDisplayFields() {
        idDisplay.clear();
        firstNameField.clear();
        lastNameField.clear();
        favoriteTeamField.clear();
    }

    /**
     * Safely trims strings (handles null).
     */
    private String safeTrim(String s) {
        return s == null ? "" : s.trim();
    }

    /**
     * Main method launches the JavaFX application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}