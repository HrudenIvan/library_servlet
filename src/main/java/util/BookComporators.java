package util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import model.entity.Book;

public class BookComporators {

	private BookComporators() {
	}

	public static List<BookComporators.Comparators> getAll(){
		List<BookComporators.Comparators> comparators = new ArrayList<>();
		for (BookComporators.Comparators comp : BookComporators.Comparators.values()) {
			comparators.add(comp);
		}
		
		return comparators;
	}
	
	public static Comparator<Book> defineComparator(String sortOrder) {
		if (sortOrder == null) {
			return Comparators.YEARDESC.getComparator();
		}
		Comparator<Book> comparator = null;
		Comparators comp = null;
		try {
			comp = Comparators.valueOf(sortOrder.toUpperCase());
			comparator = comp.getComparator();
		} catch (IllegalArgumentException e) {
			comparator = Comparators.YEARASC.getComparator();
		}
		return comparator;
	}

	public static class YearAsc implements Comparator<Book> {

		@Override
		public int compare(Book book1, Book book2) {
			int result = 0;
			if (book1.getReleaseDate() - book2.getReleaseDate() != 0) {
				result = book1.getReleaseDate() - book2.getReleaseDate();
			} else {
				result = book1.getTitle().compareTo(book2.getTitle());
			}
			return result;
		}

	}

	public static class TitleAsc implements Comparator<Book> {

		@Override
		public int compare(Book book1, Book book2) {
			int result = 0;
			if (book1.getTitle().compareTo(book2.getTitle()) != 0) {
				result = book1.getTitle().compareTo(book2.getTitle());
			} else if (book1.getAuthor().getLastName().compareTo(book2.getAuthor().getLastName()) != 0) {
				result = book1.getAuthor().getLastName().compareTo(book2.getAuthor().getLastName());
			} else {
				result = book1.getAuthor().getFirstName().compareTo(book2.getAuthor().getFirstName());
			}
			return result;
		}

	}

	public static class TitleDesc implements Comparator<Book> {

		@Override
		public int compare(Book book1, Book book2) {
			int result = 0;
			if (book2.getTitle().compareTo(book1.getTitle()) != 0) {
				result = book2.getTitle().compareTo(book1.getTitle());
			} else if (book1.getAuthor().getLastName().compareTo(book2.getAuthor().getLastName()) != 0) {
				result = book1.getAuthor().getLastName().compareTo(book2.getAuthor().getLastName());
			} else {
				result = book1.getAuthor().getFirstName().compareTo(book2.getAuthor().getFirstName());
			}
			return result;
		}

	}

	public static class YearDesc implements Comparator<Book> {

		@Override
		public int compare(Book book1, Book book2) {
			int result = 0;
			if (book2.getReleaseDate() - book1.getReleaseDate() != 0) {
				result = book2.getReleaseDate() - book1.getReleaseDate();
			} else {
				result = book1.getTitle().compareTo(book2.getTitle());
			}
			return result;
		}

	}

	public static class AuthorAsc implements Comparator<Book> {

		@Override
		public int compare(Book book1, Book book2) {
			int result = 0;
			if (book1.getAuthor().getLastName().compareTo(book2.getAuthor().getLastName()) != 0) {
				result = book1.getAuthor().getLastName().compareTo(book2.getAuthor().getLastName());
			} else if (book1.getAuthor().getFirstName().compareTo(book2.getAuthor().getFirstName()) != 0) {
				result = book1.getAuthor().getFirstName().compareTo(book2.getAuthor().getFirstName());
			} else {
				result = book1.getTitle().compareTo(book2.getTitle());
			}
			return result;
		}
	}
	
	public static class AuthorDesc implements Comparator<Book> {

		@Override
		public int compare(Book book1, Book book2) {
			int result = 0;
			if (book2.getAuthor().getLastName().compareTo(book1.getAuthor().getLastName()) != 0) {
				result = book2.getAuthor().getLastName().compareTo(book1.getAuthor().getLastName());
			} else if (book2.getAuthor().getFirstName().compareTo(book1.getAuthor().getFirstName()) != 0) {
				result = book2.getAuthor().getFirstName().compareTo(book1.getAuthor().getFirstName());
			} else {
				result = book1.getTitle().compareTo(book2.getTitle());
			}
			return result;
		}
	}
	
	public static class PublisherAsc implements Comparator<Book> {

		@Override
		public int compare(Book book1, Book book2) {
			int result = 0;
			if (book1.getPublisher().getName().compareTo(book2.getPublisher().getName()) != 0) {
				result = book1.getPublisher().getName().compareTo(book2.getPublisher().getName());
			} else if (book2.getReleaseDate() - book1.getReleaseDate() != 0) {
				result = book2.getReleaseDate() - book1.getReleaseDate();
			} else {
				result = book1.getTitle().compareTo(book2.getTitle());
			}
			return result;
		}
		
	}
	
	public static class PublisherDesc implements Comparator<Book> {

		@Override
		public int compare(Book book1, Book book2) {
			int result = 0;
			if (book2.getPublisher().getName().compareTo(book1.getPublisher().getName()) != 0) {
				result = book2.getPublisher().getName().compareTo(book1.getPublisher().getName());
			} else if (book2.getReleaseDate() - book1.getReleaseDate() != 0) {
				result = book2.getReleaseDate() - book1.getReleaseDate();
			} else {
				result = book1.getTitle().compareTo(book2.getTitle());
			}
			return result;
		}
		
	}

	public enum Comparators {
		YEARASC("Release year asc", "yearAsc", new YearAsc()),
		YEARDESC("Release year desc", "yearDesc", new YearDesc()),
		TITLEASC("Title asc", "titleAsc", new TitleAsc()),
		TITLEDESC("Title desc", "titleDesc", new TitleDesc()),
		AUTHORASC("Athor asc", "authorAsc", new AuthorAsc()),
		AUTHORDESC("Athor desc", "athorDesc", new AuthorDesc()),
		PUBLISHERASC("Publisher asc", "publisherAsc", new PublisherAsc()),
		PUBLISHERDESC("Publisher desc", "publisherDesc", new PublisherDesc());

		private String title;
		private String name;
		private Comparator<Book> comparator;

		private Comparators(String title, String name, Comparator<Book> comparator) {
			this.title = title;
			this.name = name;
			this.comparator = comparator;
		}

		public String getName() {
			return name;
		}

		public String getTitle() {
			return title;
		}

		Comparator<Book> getComparator() {
			return comparator;
		}
	}

}
