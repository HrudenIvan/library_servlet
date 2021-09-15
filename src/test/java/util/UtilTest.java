package util;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

public class UtilTest {
	
	@Test
	public void whenNoCookiesThenDefaultLocatShouldReturns () {
		HttpServletRequest req = mock(HttpServletRequest.class);
		when(req.getCookies()).thenReturn(null);
		Locale expected = new Locale("en", "US");
		Locale actual = Util.defineLocale(req);
		assertEquals(expected, actual);
	}
	
	@Test
	public void whenCookiesExistsThenActualLocatShouldReturns () {
		HttpServletRequest req = mock(HttpServletRequest.class);
		Cookie[] cookies = new Cookie[2]; 
		cookies[0] = new Cookie("language", "ru");
		cookies[1] = new Cookie("country", "RU");
		when(req.getCookies()).thenReturn(cookies);
		Locale expected = new Locale("ru", "RU");
		Locale actual = Util.defineLocale(req);
		assertEquals(expected, actual);
	}
}
