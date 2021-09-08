package util;

import java.util.HashMap;

import model.entity.Author;
import model.entity.Book;
import model.entity.Publisher;
import model.entity.User;

import java.time.LocalDate;

public class Validator {

	private static final String LOGIN_REGEX = "^[A-Za-z0-9]{1,20}$";
	private static final String PASSWORD_REGEX = "^[A-Za-z0-9]{10,}$";

	private Validator() {
	}

	public static boolean validatePublisher(Publisher publisher, HashMap<String, String> errors) {
		boolean result;
		
		result = validatePublisherName(publisher.getName(), errors);
		
		return result;
	}
	
	private static boolean validatePublisherName(String name, HashMap<String, String> errors) {
		boolean result = true;

		if (name.isBlank()) {
			result = false;
			errors.put("name", "Name can not be empty!");
		} else if (name.length() > 45) {
			result = false;
			errors.put("name", "Name must be shorter than 45 characters!");
		}

		return result;
	}

	public static boolean valideteAndUpdateBookUpdate(Book book, int oldQuantity, HashMap<String, String> errors) {
		boolean result;

		result = validateTitle(book.getTitle(), errors);
		result = validateAndUpdateQuantity(book, oldQuantity, errors) && result;
		result = validateReleaseDate(book.getReleaseDate(), errors) && result;

		return result;
	}
	
	private static boolean validateAndUpdateQuantity(Book book, int oldQuantity, HashMap<String, String> errors) {
		boolean result = true;

		int delta = book.getQuantity() - oldQuantity;
		if ( delta + book.getAvailable() < 0) {
			result = false;
			book.setQuantity(oldQuantity);
			errors.put("quantity", "Quantity can not be changed more than available amount!");
		} else {
			book.setAvailable(book.getAvailable() + delta);
		}

		return result;
	}

	public static boolean valideteBook(Book book, HashMap<String, String> errors) {
		boolean result;

		result = validateTitle(book.getTitle(), errors);
		result = validateQuantity(book.getQuantity(), errors) && result;
		result = validateReleaseDate(book.getReleaseDate(), errors) && result;

		return result;
	}

	private static boolean validateReleaseDate(int releaseDate, HashMap<String, String> errors) {
		boolean result = true;

		if (LocalDate.now().getYear() < releaseDate) {
			result = false;
			errors.put("releaseDate", "Release date must be lesser or equal than current year!");
		}

		return result;
	}

	private static boolean validateQuantity(int quantity, HashMap<String, String> errors) {
		boolean result = true;

		if (quantity < 1) {
			result = false;
			errors.put("quantity", "Quantity must be greater than zero!");
		}

		return result;
	}

	private static boolean validateTitle(String title, HashMap<String, String> errors) {
		boolean result = true;

		if (title.isBlank()) {
			result = false;
			errors.put("title", "Title can not be empty!");
		} else if (title.length() > 45) {
			result = false;
			errors.put("title", "Title must be shorter than 45 characters!");
		}

		return result;
	}

	public static boolean validateAuthor(Author author, HashMap<String, String> errors) {
		boolean result;

		result = validateName("Firstname", author.getFirstName(), errors);
		result = validateName("Lastname", author.getLastName(), errors) && result;

		return result;
	}

	public static boolean validatePassword(String password1, String password2, HashMap<String, String> errors) {
		boolean result = true;
		StringBuilder message = new StringBuilder();

		if (password1 == null || password1.isBlank()) {
			result = false;
			message.append("Password must not be empty");
		} else {
			if (!password1.equals(password2)) {
				result = false;
				message.append("Both passwords must be equal!");
			}
			if (!password1.matches(PASSWORD_REGEX)) {
				result = false;
				message.append(" Password must contain at least 10 latin characters or numbers!");
			}
		}
		if (!result) {
			errors.put("password", message.toString());
		}

		return result;
	}

	public static boolean validateUser(User user, HashMap<String, String> errors) {
		boolean result;

		result = validateLogin(user.getLogin(), errors);
		result = validateName("Firstname", user.getFirstName(), errors) && result;
		result = validateName("Lastname", user.getLastName(), errors) && result;
		result = validatePenalty(user.getPenalty(), errors) && result;

		return result;
	}

	private static boolean validatePenalty(double penalty, HashMap<String, String> errors) {
		boolean result = true;

		if (penalty < 0) {
			result = false;
			errors.put("penalty", "Penalty must be greater or equal zero!");
		}

		return result;
	}

	private static boolean validateLogin(String login, HashMap<String, String> errors) {
		boolean result = true;

		if (login == null || login.isBlank()) {
			result = false;
			errors.put("login", "Login must not be empty!");
		} else if (!login.matches(LOGIN_REGEX)) {
			result = false;
			errors.put("login", "Login must contain form 1 to 20 latin characters or numbers!");
		}

		return result;
	}

	private static boolean validateName(String fieldName, String field, HashMap<String, String> errors) {
		boolean result = true;

		if (field == null || field.isBlank()) {
			result = false;
			errors.put(fieldName.toLowerCase(), fieldName + " must not be empty!");
		} else if (field.length() > 30) {
			result = false;
			errors.put(fieldName.toLowerCase(), fieldName + " must be shorter then 30 characters!");
		}

		return result;
	}

}
