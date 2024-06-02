package biblioteka;

import java.util.List;

public interface BookOperations {
    void addBook(Book book);
    void removeBook(Book book);
    void updateBook(String isbn, Book updatedBook);
    List<Book> getBooks();
}
