package util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.any;

import org.mockito.MockedStatic;
import org.mockito.Mockito;

import model.entity.Author;
import model.entity.Book;
import model.entity.BookOrder;
import model.entity.OrderStatus;
import model.entity.Publisher;
import model.entity.User;

public class ValidatorTest {
	private User user;
	private Author author;
	private BookOrder bookOrder;
	private Publisher publisher;
	private Book book;
	private HashMap<String, String> errors;

	@Before
	public void setUp() {
		bookOrder = new BookOrder();
		bookOrder.setOrderDate(LocalDateTime.now());
		OrderStatus os = new OrderStatus();
		os.setId(2);
		bookOrder.setOrderStatus(os);
		bookOrder.setOpenDate(LocalDate.now());
		bookOrder.setCloseDate(LocalDate.now());
		bookOrder.setReturnDate(LocalDate.now());
		publisher = new Publisher();
		publisher.setName("123");
		book = new Book();
		book.setTitle("123");
		book.setQuantity(3);
		book.setAvailable(3);
		book.setReleaseDate(LocalDate.now().getYear());
		author = new Author();
		author.setFirstName("123");
		author.setLastName("123");
		user = new User();
		user.setLogin("123");
		user.setPenalty(0);
		user.setFirstName("123");
		user.setLastName("123");
		errors = new HashMap<String, String>();
	}

	@Test
	public void whenValidBookOrderThenReturnTrue() {
		boolean result = Validator.validateAndUpdateBookOrder(null, bookOrder, 2, LocalDate.now(), errors);
		assertTrue(result);
	}

	@Test
	public void whenOrderStatusChangedBackWardsThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			boolean result = Validator.validateAndUpdateBookOrder(null, bookOrder, 3, LocalDate.now(), errors);
			assertFalse(result);
			assertEquals("error", errors.get("orderStatus"));
		}
	}
	
	@Test
	public void whenOrderStatusChangedNonConsecutivelyThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			bookOrder.getOrderStatus().setId(4);
			boolean result = Validator.validateAndUpdateBookOrder(null, bookOrder, 1, LocalDate.now(), errors);
			assertFalse(result);
			assertEquals("error", errors.get("orderStatus"));
		}
	}

	@Test
	public void whenCloseDateBeforeNowThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			bookOrder.setCloseDate(LocalDate.ofEpochDay(LocalDate.now().toEpochDay() - 1));
			boolean result = Validator.validateAndUpdateBookOrder(null, bookOrder, 3, LocalDate.now(), errors);
			assertFalse(result);
			assertEquals("error", errors.get("closeDate"));
		}
	}

	@Test
	public void whenCloseDateNullThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			bookOrder.setCloseDate(null);
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			boolean result = Validator.validateAndUpdateBookOrder(null, bookOrder, 3, LocalDate.now(), errors);
			assertFalse(result);
			assertEquals("error", errors.get("closeDate"));
		}
	}

	@Test
	public void whenCloseDateBeforeOldCloseDateThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			boolean result = Validator.validateAndUpdateBookOrder(null, bookOrder, 3,
					LocalDate.ofEpochDay(LocalDate.now().toEpochDay() + 1), errors);
			assertFalse(result);
			assertEquals("error", errors.get("closeDate"));
		}
	}

	@Test
	public void whenOrderTypeReadingRoomAndCloseDateNotEqualOpenDateThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			bookOrder.setCloseDate(LocalDate.ofEpochDay(LocalDate.now().toEpochDay() + 1));
			bookOrder.setOrderType("reading room");
			boolean result = Validator.validateAndUpdateBookOrder(null, bookOrder, 3, LocalDate.now(), errors);
			assertFalse(result);
			assertEquals("error", errors.get("closeDate"));
		}
	}

	@Test
	public void whenValidPublisherThenReturnTrue() {
		boolean result = Validator.validatePublisher(null, publisher, errors);
		assertTrue(result);
	}

	@Test
	public void whenPublisherNameNullThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			publisher.setName("");
			boolean result = Validator.validatePublisher(null, publisher, errors);
			assertFalse(result);
			assertEquals("error", errors.get("name"));
		}
	}
	
	@Test
	public void whenPublisherNameTooLongThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			publisher.setName("11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
			boolean result = Validator.validatePublisher(null, publisher, errors);
			assertFalse(result);
			assertEquals("error", errors.get("name"));
		}
	}
	
	@Test
	public void whenValidBookUpdateThenReturnTrue() {
		boolean result = Validator.valideteAndUpdateBookUpdate(null, book, 3, errors);
		assertTrue(result);
	}

	@Test
	public void whenUpdatedBookTitleEmptyThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			book.setTitle("");
			boolean result = Validator.valideteAndUpdateBookUpdate(null, book, 3, errors);
			assertFalse(result);
			assertEquals("error", errors.get("title"));
		}
	}
	
	@Test
	public void whenUpdatedBookTitleTooLongThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			book.setTitle("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
			boolean result = Validator.valideteAndUpdateBookUpdate(null, book, 3, errors);
			assertFalse(result);
			assertEquals("error", errors.get("title"));
		}
	}
	
	@Test
	public void whenUpdatedBookReleaseDateGreaterThenNowThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			book.setReleaseDate(LocalDate.now().getYear()+1);
			boolean result = Validator.valideteAndUpdateBookUpdate(null, book, 3, errors);
			assertFalse(result);
			assertEquals("error", errors.get("releaseDate"));
		}
	}
	
	@Test
	public void whenUpdatedBookQuantityChangeMoreThanAvailableThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			boolean result = Validator.valideteAndUpdateBookUpdate(null, book, 10, errors);
			assertFalse(result);
			assertEquals("error", errors.get("quantity"));
		}
	}
	
	@Test
	public void whenValidBookThenReturnTrue() {
		boolean result = Validator.valideteBook(null, book, errors);
		assertTrue(result);
	}
	
	@Test
	public void whenBookQuantityLessThanOneThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			book.setQuantity(0);
			boolean result = Validator.valideteBook(null, book, errors);
			assertFalse(result);
			assertEquals("error", errors.get("quantity"));
		}
	}
	
	@Test
	public void whenBookReleaseDateGreaterThenNowThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			book.setReleaseDate(LocalDate.now().getYear()+1);
			boolean result = Validator.valideteBook(null, book, errors);
			assertFalse(result);
			assertEquals("error", errors.get("releaseDate"));
		}
	}
	
	@Test
	public void whenValidAuthorThenReturnTrue() {
		boolean result = Validator.validateAuthor(null, author, errors);
		assertTrue(result);
	}
	
	@Test
	public void whenAuthorFirstNameIsEmptyThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			author.setFirstName("");
			boolean result = Validator.validateAuthor(null, author, errors);
			assertFalse(result);
			assertEquals("error", errors.get("firstname"));
		}
	}
	
	@Test
	public void whenAuthorFirstNameIsTooLongThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			author.setFirstName("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
			boolean result = Validator.validateAuthor(null, author, errors);
			assertFalse(result);
			assertEquals("error", errors.get("firstname"));
		}
	}
	
	@Test
	public void whenAuthorLastNameIsEmptyThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			author.setLastName("");
			boolean result = Validator.validateAuthor(null, author, errors);
			assertFalse(result);
			assertEquals("error", errors.get("lastname"));
		}
	}
	
	@Test
	public void whenAuthorLastNameIsTooLongThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			author.setLastName("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
			boolean result = Validator.validateAuthor(null, author, errors);
			assertFalse(result);
			assertEquals("error", errors.get("lastname"));
		}
	}
	
	@Test
	public void whenValidPasswordThenReturnTrue() {
		boolean result = Validator.validatePassword(null, "1234567890", "1234567890", errors);
		assertTrue(result);
	}
	
	@Test
	public void whenAnyOfPasswordsIsEmptyThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			boolean result = Validator.validatePassword(null, "", "", errors);
			assertFalse(result);
			assertEquals("error", errors.get("password"));
		}
	}
	
	@Test
	public void whenPasswordsNotEqualsThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			boolean result = Validator.validatePassword(null, "1234567890", "01234567890", errors);
			assertFalse(result);
			assertEquals("error", errors.get("password"));
		}
	}
	
	@Test
	public void whenPasswordNotSatisfyRegexThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			boolean result = Validator.validatePassword(null, "123456 7890", "123456 7890", errors);
			assertFalse(result);
			assertEquals("error", errors.get("password"));
		}
	}
	
	@Test
	public void whenValidUserThenReturnTrue() {
		boolean result = Validator.validateUser(null, user, errors);
		assertTrue(result);
	}
	
	@Test
	public void whenUserFirstnameIsEmptyThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			user.setFirstName("");
			boolean result = Validator.validateUser(null, user, errors);
			assertFalse(result);
			assertEquals("error", errors.get("firstname"));
		}
	}
	
	@Test
	public void whenUserFirstnameIsToLongThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			user.setFirstName("11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
			boolean result = Validator.validateUser(null, user, errors);
			assertFalse(result);
			assertEquals("error", errors.get("firstname"));
		}
	}
	
	@Test
	public void whenUserLastnameIsEmptyThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			user.setLastName("");
			boolean result = Validator.validateUser(null, user, errors);
			assertFalse(result);
			assertEquals("error", errors.get("lastname"));
		}
	}
	
	@Test
	public void whenUserLastnameIsToLongThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			user.setLastName("11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
			boolean result = Validator.validateUser(null, user, errors);
			assertFalse(result);
			assertEquals("error", errors.get("lastname"));
		}
	}
	
	@Test
	public void whenUserPenaltyLesserThanZeroThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			user.setPenalty(-1);
			boolean result = Validator.validateUser(null, user, errors);
			assertFalse(result);
			assertEquals("error", errors.get("penalty"));
		}
	}
	
	@Test
	public void whenUserLoginIsEmptyThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			user.setLogin("");
			boolean result = Validator.validateUser(null, user, errors);
			assertFalse(result);
			assertEquals("error", errors.get("login"));
		}
	}
	
	@Test
	public void whenUserLoginNotSatisfyRegexThenReturnFalseAndError() {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			user.setLogin("1 23");
			boolean result = Validator.validateUser(null, user, errors);
			assertFalse(result);
			assertEquals("error", errors.get("login"));
		}
	}
	
}
