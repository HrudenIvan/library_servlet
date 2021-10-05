package util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.entity.Author;
import model.entity.Book;
import model.entity.BookOrder;
import model.entity.OrderStatus;
import model.entity.Publisher;
import model.entity.User;

import java.time.LocalDate;

/**
 * Utility class for validation entities
 */
public class Validator {

	private static final String LOGIN_REGEX = "^[A-Za-z0-9]{1,20}$";
	private static final String PASSWORD_REGEX = "^[A-Za-z0-9]{10,}$";

	/**
	 * Protect constructor to deny instantiation
	 */
	private Validator() {
	}

	/**
	 * Validates {@link BookOrder} entity. In case of any errors falls back to old values
	 * @param request {@link HttpServletRequest}
	 * @param bookOrder  {@link BookOrder} to be validated
	 * @param oldOsId old {@link OrderStatus} id
	 * @param oldCloseDate old close date
	 * @param errors {@link Map} that stores errors
	 * @return true if {@link BookOrder} is valid, false - otherwise
	 */
	public static boolean validateAndUpdateBookOrder(HttpServletRequest request, BookOrder bookOrder, int oldOsId,
			LocalDate oldCloseDate, HashMap<String, String> errors) {
		boolean result;

		result = validateOrderStatus(request, bookOrder, oldOsId, errors);
		result = validateCloseDate(request, bookOrder, oldCloseDate, oldOsId, errors) && result;

		return result;
	}

	private static boolean validateCloseDate(HttpServletRequest request, BookOrder bookOrder, LocalDate oldCloseDate,
			int oldOsId, HashMap<String, String> errors) {
		boolean result = true;

		if (bookOrder.getOrderStatus().getId() > 2) {
			if (bookOrder.getCloseDate() == null) {
				result = false;
				bookOrder.setCloseDate(oldCloseDate);
				errors.put("closeDate", Localizer.getString(request, "validation.closedate.empty"));
			} else if (bookOrder.getCloseDate().isBefore(oldCloseDate)) {
				result = false;
				bookOrder.setCloseDate(oldCloseDate);
				errors.put("closeDate", Localizer.getString(request, "validation.closedate.before"));
			} else if (bookOrder.getCloseDate().isBefore(oldCloseDate)) {
				result = false;
				bookOrder.setCloseDate(oldCloseDate);
				errors.put("closeDate", Localizer.getString(request, "validation.closedate.after"));
			} else if ("reading room".equals(bookOrder.getOrderType())
					&& !bookOrder.getCloseDate().equals(bookOrder.getOpenDate())) {
				result = false;
				bookOrder.setCloseDate(oldCloseDate);
				errors.put("closeDate", Localizer.getString(request, "validation.closedate.readingroom"));
			}
		}
		return result;
	}

	private static boolean validateOrderStatus(HttpServletRequest request, BookOrder bookOrder, int oldOsId,
			HashMap<String, String> errors) {
		boolean result = true;

		if (bookOrder.getOrderStatus().getId() - oldOsId < 0) {
			result = false;
			errors.put("orderStatus", Localizer.getString(request, "validation.orderstatus.orderBack"));
			bookOrder.getOrderStatus().setId(oldOsId);
		} else if (bookOrder.getOrderStatus().getId() - oldOsId > 1) {
			result = false;
			errors.put("orderStatus", Localizer.getString(request, "validation.orderstatus.orderForward"));
			bookOrder.getOrderStatus().setId(oldOsId);
		}

		return result;
	}

	/**
	 * Validates {@link Publisher} entity.
	 * @param request {@link HttpServletRequest}
	 * @param publisher {@link Publisher} to be validated
	 * @param errors {@link Map} that stores errors
	 * @return true if {@link Publisher} is valid, false - otherwise
	 */
	public static boolean validatePublisher(HttpServletRequest request, Publisher publisher,
			HashMap<String, String> errors) {
		boolean result;

		result = validatePublisherName(request, publisher.getName(), errors);

		return result;
	}

	private static boolean validatePublisherName(HttpServletRequest request, String name,
			HashMap<String, String> errors) {
		boolean result = true;

		if (name.isEmpty()) {
			result = false;
			errors.put("name", Localizer.getString(request, "validation.pablisher.name.empty"));
		} else if (name.length() > 45) {
			result = false;
			errors.put("name", Localizer.getString(request, "validation.pablisher.name.lenght"));
		}

		return result;
	}

	/**
	 * Validates {@link Book} entity after update. In case of any errors falls back to old values
	 * @param request {@link HttpServletRequest}
	 * @param book {@link Book} to be validated
	 * @param oldQuantity old quantity value
	 * @param errors {@link Map} that stores errors
	 * @return true if {@link Book} is valid, false - otherwise
	 */
	public static boolean valideteAndUpdateBookUpdate(HttpServletRequest request, Book book, int oldQuantity,
			HashMap<String, String> errors) {
		boolean result;

		result = validateTitle(request, book.getTitle(), errors);
		result = validateAndUpdateQuantity(request, book, oldQuantity, errors) && result;
		result = validateReleaseDate(request, book.getReleaseDate(), errors) && result;

		return result;
	}

	private static boolean validateAndUpdateQuantity(HttpServletRequest request, Book book, int oldQuantity,
			HashMap<String, String> errors) {
		boolean result = true;

		int delta = book.getQuantity() - oldQuantity;
		if (delta + book.getAvailable() < 0) {
			result = false;
			book.setQuantity(oldQuantity);
			errors.put("quantity", Localizer.getString(request, "validation.quantity.update"));
		} else {
			book.setAvailable(book.getAvailable() + delta);
		}

		return result;
	}

	/**
	 * Validates {@link Book} entity after creation.
	 * @param request {@link HttpServletRequest}
	 * @param book {@link Book} to be validated
	 * @param errors {@link Map} that stores errors
	 * @return true if {@link Book} is valid, false - otherwise
	 */
	public static boolean valideteBook(HttpServletRequest request, Book book, HashMap<String, String> errors) {
		boolean result;

		result = validateTitle(request, book.getTitle(), errors);
		result = validateQuantity(request, book.getQuantity(), errors) && result;
		result = validateReleaseDate(request, book.getReleaseDate(), errors) && result;

		return result;
	}

	private static boolean validateReleaseDate(HttpServletRequest request, int releaseDate,
			HashMap<String, String> errors) {
		boolean result = true;

		if (LocalDate.now().getYear() < releaseDate) {
			result = false;
			errors.put("releaseDate", Localizer.getString(request, "validation.releasedate"));
		}

		return result;
	}

	private static boolean validateQuantity(HttpServletRequest request, int quantity, HashMap<String, String> errors) {
		boolean result = true;

		if (quantity < 1) {
			result = false;
			errors.put("quantity", Localizer.getString(request, "validation.quantity.add"));
		}

		return result;
	}

	private static boolean validateTitle(HttpServletRequest request, String title, HashMap<String, String> errors) {
		boolean result = true;

		if (title.isEmpty()) {
			result = false;
			errors.put("title", Localizer.getString(request, "validation.title.empty"));
		} else if (title.length() > 45) {
			result = false;
			errors.put("title", Localizer.getString(request, "validation.title.length"));
		}

		return result;
	}

	/**
	 * Validates {@link Author} entity
	 * @param request {@link HttpServletRequest}
	 * @param author {@link Author} to be validated
	 * @param errors {@link Map} that stores errors
	 * @return true if {@link Author} is valid, false - otherwise
	 */
	public static boolean validateAuthor(HttpServletRequest request, Author author, HashMap<String, String> errors) {
		boolean result;

		result = validateFirstName(request, author.getFirstName(), errors);
		result = validateLastName(request, author.getLastName(), errors) && result;

		return result;
	}

	/**
	 * Validates password
	 * @param request {@link HttpServletRequest}
	 * @param password1 raw password
	 * @param password2 confirmation of password
	 * @param errors {@link Map} that stores errors
	 * @return true if password is valid, false - otherwise
	 */
	public static boolean validatePassword(HttpServletRequest request, String password1, String password2,
			HashMap<String, String> errors) {
		boolean result = true;
		StringBuilder message = new StringBuilder();

		if (password1 == null || password1.isEmpty()) {
			result = false;
			message.append(Localizer.getString(request, "validation.password.empty"));
		} else {
			if (!password1.equals(password2)) {
				result = false;
				message.append(Localizer.getString(request, "validation.password.equal"));
			}
			if (!password1.matches(PASSWORD_REGEX)) {
				result = false;
				message.append(Localizer.getString(request, "validation.password.regex"));
			}
		}
		if (!result) {
			errors.put("password", message.toString());
		}

		return result;
	}

	/**
	 * Validates {@link User} entity
	 * @param request {@link HttpServletRequest}
	 * @param user {@link User} to be validated
	 * @param errors {@link Map} that stores errors
	 * @return true if {@link User} is valid, false - otherwise
	 */
	public static boolean validateUser(HttpServletRequest request, User user, HashMap<String, String> errors) {
		boolean result;

		result = validateLogin(request, user.getLogin(), errors);
		result = validateFirstName(request, user.getFirstName(), errors) && result;
		result = validateLastName(request, user.getLastName(), errors) && result;
		result = validatePenalty(request, user.getPenalty(), errors) && result;

		return result;
	}

	private static boolean validatePenalty(HttpServletRequest request, double penalty, HashMap<String, String> errors) {
		boolean result = true;

		if (penalty < 0) {
			result = false;
			errors.put("penalty", Localizer.getString(request, "validation.penalty"));
		}

		return result;
	}

	private static boolean validateLogin(HttpServletRequest request, String login, HashMap<String, String> errors) {
		boolean result = true;

		if (login == null || login.isEmpty()) {
			result = false;
			errors.put("login", Localizer.getString(request, "validation.login.empty"));
		} else if (!login.matches(LOGIN_REGEX)) {
			result = false;
			errors.put("login", Localizer.getString(request, "validation.login.regex"));
		}

		return result;
	}

	private static boolean validateFirstName(HttpServletRequest request, String field, HashMap<String, String> errors) {
		boolean result = true;

		if (field == null || field.isEmpty()) {
			result = false;
			errors.put("firstname", Localizer.getString(request, "validation.firstname.empty"));
		} else if (field.length() > 30) {
			result = false;
			errors.put("firstname", Localizer.getString(request, "validation.firstname.length"));
		}

		return result;
	}

	private static boolean validateLastName(HttpServletRequest request, String field, HashMap<String, String> errors) {
		boolean result = true;

		if (field == null || field.isEmpty()) {
			result = false;
			errors.put("lastname", Localizer.getString(request, "validation.lastname.empty"));
		} else if (field.length() > 30) {
			result = false;
			errors.put("lastname", Localizer.getString(request, "validation.lastname.length"));
		}

		return result;
	}

}
