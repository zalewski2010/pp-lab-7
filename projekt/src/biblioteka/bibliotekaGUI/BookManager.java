package biblioteka.bibliotekaGUI;

import java.util.ArrayList;
import java.util.List;

public class BookManager implements BookOperations {
    private List<Book> books;

    public BookManager() {
        this.books = new ArrayList<>();
        // Dodaj przykładowe książki do listy books
        books.add(new Book("Title1", "Author1", "ISBN1", 2001));
        books.add(new Book("Title2", "Author2", "ISBN2", 2002));
        books.add(new Book("Title3", "Author3", "ISBN3", 2003));
        books.add(new Book("Title4", "Author4", "ISBN4", 2004));
        books.add(new Book("Title5", "Author5", "ISBN5", 2005));
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public void removeBook(Book book) {
        books.remove(book);
    }

    @Override
    public void updateBook(String isbn, Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equals(isbn)) {
                books.set(i, updatedBook);
                return;
            }
        }
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }
}
