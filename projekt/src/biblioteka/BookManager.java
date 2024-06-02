package biblioteka;

import java.util.ArrayList;
import java.util.List;

public class BookManager implements BookOperations {
    private List<Book> books;

    public BookManager() {
        books = new ArrayList<>();
        // Dodaj początkowe książki do listy
        books.add(new Book("Title1", "Author1", "ISBN001", 2001));
        books.add(new Book("Title2", "Author2", "ISBN002", 2002));
        books.add(new Book("Title3", "Author3", "ISBN003", 2003));
        books.add(new Book("Title4", "Author4", "ISBN004", 2004));
        books.add(new Book("Title5", "Author5", "ISBN005", 2005));
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
        return new ArrayList<>(books);
    }
}
