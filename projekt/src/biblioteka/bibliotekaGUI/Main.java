package biblioteka.bibliotekaGUI;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

import javafx.scene.layout.VBox;

import java.util.List;

public class Main extends Application {
    private TextField titleField;
    private TextField authorField;
    private TextField isbnField;
    private TextField yearField;
    private TextArea resultArea;
    private static final BookManager bookManager = new BookManager();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Book Manager");

        titleField = new TextField();
        titleField.setPromptText("Enter book title");

        authorField = new TextField();
        authorField.setPromptText("Enter book author");

        isbnField = new TextField();
        isbnField.setPromptText("Enter book ISBN");

        yearField = new TextField();
        yearField.setPromptText("Enter book year");

        resultArea = new TextArea();
        resultArea.setPrefHeight(200);

        Button addButton = new Button("Add Book");
        addButton.setOnAction(e -> addBook());

        Button removeButton = new Button("Remove Book");
        removeButton.setOnAction(e -> removeBook());

        Button updateButton = new Button("Update Book");
        updateButton.setOnAction(e -> updateBook());

        Button listButton = new Button("List Books");
        listButton.setOnAction(e -> listBooks());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> primaryStage.close());

        VBox vBox = new VBox(10,
                titleField, authorField, isbnField, yearField,
                addButton, removeButton, updateButton, listButton, exitButton, resultArea);

        Scene scene = new Scene(vBox, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String isbn = isbnField.getText();
        int year = Integer.parseInt(yearField.getText());
        Book book = new Book(title, author, isbn, year);
        bookManager.addBook(book);
        resultArea.setText("Book added: " + book);
    }

    private void removeBook() {
        String isbn = isbnField.getText();
        Book bookToRemove = null;
        for (Book book : bookManager.getBooks()) {
            if (book.getIsbn().equals(isbn)) {
                bookToRemove = book;
                break;
            }
        }
        if (bookToRemove != null) {
            bookManager.removeBook(bookToRemove);
            resultArea.setText("Book removed: " + bookToRemove);
        } else {
            resultArea.setText("Book not found with ISBN: " + isbn);
        }
    }

    private void updateBook() {
        String isbn = isbnField.getText();
        String title = titleField.getText();
        String author = authorField.getText();
        int year = Integer.parseInt(yearField.getText());
        Book updatedBook = new Book(title, author, isbn, year);
        bookManager.updateBook(isbn, updatedBook);
        resultArea.setText("Book updated: " + updatedBook);
    }

    private void listBooks() {
        List<Book> books = bookManager.getBooks();
        StringBuilder results = new StringBuilder("Books:\n");
        for (Book book : books) {
            results.append(book).append("\n");
        }
        resultArea.setText(results.toString());
    }
}
