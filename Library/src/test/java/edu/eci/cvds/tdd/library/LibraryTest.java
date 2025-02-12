package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.eci.cvds.tdd.library.Library;

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

    @Test
    public void testLoanABookSuccessfully() {
        Library library = new Library();
        User user = new User("123", "John Doe");
        Book book = new Book("Title", "Author", "ISBN123");
        
        library.addUser(user);
        library.addBook(book);
        
        Loan loan = library.loanABook("123", "ISBN123");
        
        assertNotNull(loan);
        assertEquals(user, loan.getUser());
        assertEquals(book, loan.getBook());
        assertEquals(LoanStatus.ACTIVE, loan.getStatus());
    }


    @Test
    public void testLoanBookUserNotFound() {
        Library library = new Library();
        Book book = new Book("Title", "Author", "ISBN123");
        
        library.addBook(book);
        
        Loan loan = library.loanABook("999", "ISBN123"); // Usuario inexistente
        
        assertNull(loan);
    }

    @Test
    public void testLoanBookAlreadyLoanedByUser() {
        Library library = new Library();
        User user = new User("123", "John Doe");
        Book book = new Book("Title", "Author", "ISBN123");
        
        library.addUser(user);
        library.addBook(book);
        library.loanABook("123", "ISBN123");
        
        Loan secondLoan = library.loanABook("123", "ISBN123");
        
        assertNull(secondLoan);
    }

    @Test
public void testReturnLoanShouldReturnLoanSuccessfully() {
    Library library = new Library();
    User user = new User("123", "John Doe");
    Book book = new Book("Title", "Author", "ISBN123");
    library.addUser (user);
    library.addBook(book);
    Loan loan = library.loanABook(user.getId(), book.getIsbn());
    Loan returnedLoan = library.returnLoan(loan);
    assertNotNull(returnedLoan);
    assertEquals(LoanStatus.RETURNED, returnedLoan.getStatus());
    assertNotNull(returnedLoan.getReturnDate());
}

@Test
public void testReturnLoanShouldntReturnNonExistentLoan() {
    Library library = new Library();
    Loan nonExistentLoan = new Loan();
    Loan returnedLoan = library.returnLoan(nonExistentLoan);
    assertNull(returnedLoan);
}

@Test
public void testLoanBookShouldntAllowMultipleLoansForSameUserAndBook() {
    Library library = new Library();
    User user = new User("123", "John Doe");
    Book book = new Book("Title", "Author", "ISBN123");
    library.addUser (user);
    library.addBook(book);
    Loan firstLoan = library.loanABook(user.getId(), book.getIsbn());
    assertNotNull(firstLoan, "El primer préstamo debería ser exitoso.");
    assertEquals(1, library.getLoans().size(), "Debería haber un préstamo en la lista.");
    Loan secondLoan = library.loanABook(user.getId(), book.getIsbn());
    assertNull(secondLoan, "El segundo préstamo no debería ser permitido, ya que el usuario ya tiene el libro prestado.");
    assertEquals(1, library.getLoans().size(), "Debería seguir habiendo un préstamo en la lista.");
}


}

