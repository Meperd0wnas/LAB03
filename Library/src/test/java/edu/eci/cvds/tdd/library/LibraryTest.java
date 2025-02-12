package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    @Test
    public void testAddNewBookSuccessfully() {
        Library library = new Library();
        Book book = new Book("The Catcher in the Rye", "J.D. Salinger", "978-0-316-76948-0");

        boolean added = library.addBook(book);

        assertTrue(added, "El libro debería haberse agregado correctamente.");
    }

    @Test
    public void testAddExistingBookIncreasesCount() {
        Library library = new Library();
        Book book = new Book("1984", "George Orwell", "978-0-452-28423-4");

        library.addBook(book);
        boolean addedAgain = library.addBook(book);

        assertTrue(addedAgain, "El libro existente debería haber aumentado su cantidad.");
    }

    @Test
    public void testAddNullBookReturnsFalse() {
        Library library = new Library();

        boolean added = library.addBook(null);

        assertFalse(added, "No se debería permitir agregar un libro nulo.");
    }
}
