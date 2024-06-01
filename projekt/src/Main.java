import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.DirectoryChooser;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main extends Application{
    private TextField directoryPathField;
    private TextField searchField;
    private TextArea resultArea;


    // 	metoda main wyzwala metodę launch do której przekazuje args. 
    public static void main(String[] args) {
        launch(args);

}

@Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("File Browser and Search");

        // prywatna zmienna typu TextField o nazwie directoryPathField
        directoryPathField = new TextField();
        directoryPathField.setPromptText("Enter directory path");

        // prywatna zmienna typu TextField o nazwie searchField
        searchField = new TextField();
        searchField.setPromptText("Enter search phrase");


        //  TextArea resultArea
        resultArea = new TextArea();
        resultArea.setPrefHeight(400);

        //  przycisk browseButton
        Button browseButton = new Button("Browse");
        browseButton.setOnAction(e -> browseDirectory());

        //  przycisk searchButton
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> searchFiles());

        //zmienna hBox przechowująca instancję klasy HBox zainicjalizowany parametrami 10, directoryPathField oraz browseButton
        HBox hBox = new HBox(10, directoryPathField, browseButton);

        //zmienna vBox przechowująca instancję klasy VBox zainicjalizowany parametrami 10, hbox, searchField oraz searchButton, 
        VBox vBox = new VBox(10, hBox, searchField, searchButton, resultArea);

        // zmienna scene przechowująca instancję klasy Scene zainicjalizowany parametrami vBox, 600 oraz 200, 
        Scene scene = new Scene(vBox, 600, 400);
        primaryStage.setScene(scene);

        // Wyświetlenie aplikacji za pomocą primarystage
        primaryStage.show();
    }

    // Metoda browseDirectory prywatna
    //tworzy zmienną directoryChooser przechowującą instancję klasy DirectoryChooser, 
    //tworzy zmienną selectedDirectory przechowującą informację na temat wyświetlenia modala/dialogu z directoryChooser ustawioną na null,
    //o	sprawdza czy selectedDirectory jest różny od null a jeżeli tak to ustawia tekst directoryPathField na ścieżkę absolutną za pomocą selectedDirectory.  
    private void browseDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) {
            directoryPathField.setText(selectedDirectory.getAbsolutePath());
        }
    }

    // Metoda searchFiles
    private void searchFiles() {
        String directoryPath = directoryPathField.getText();
        String searchPhrase = searchField.getText();

        if (directoryPath == null || directoryPath.isEmpty()) {
            resultArea.setText("Please provide a directory path.");
            return;
        }

        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            resultArea.setText("The provided path is not a directory.");
            return;
        }

        StringBuilder results = new StringBuilder();
        searchInDirectory(directory, searchPhrase, results);

        resultArea.setText(results.toString());
    }

    // Metoda containsPhrase
    private boolean containsPhrase(File file, String searchPhrase) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchPhrase)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

                // Metoda searchInDirectory
    private void searchInDirectory(File directory, String searchPhrase, StringBuilder results) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    try {
                        if (containsPhrase(file, searchPhrase)) {
                            results.append(file.getAbsolutePath()).append("\n");
                        }
                    } catch (IOException e) {
                        results.append("Error reading file: ").append(file.getAbsolutePath()).append("\n");
                    }
                } else if (file.isDirectory()) {
                    searchInDirectory(file, searchPhrase, results);
                }
            }
        }
    }
}
