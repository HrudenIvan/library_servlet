package tag;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Custom tag to format LocalDate and LocalDateTime on jsp pages
 */
public class FormatDateTime extends TagSupport {
	private static final long serialVersionUID = 1L;
	private Object value;
	private static final Logger logger;

	static {
		logger = LogManager.getLogger(FormatDateTime.class.getName());
	}

	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Method that invokes on start of tag. 
	 * Checks to what class belongs passed tag attribute and format its printed value according to current locale
	 */
	@Override
	public int doStartTag() throws JspException {
		Locale locale = (Locale) pageContext.getSession().getAttribute("locale");
		String result = null;
		if (value instanceof LocalDate) {
			DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).localizedBy(locale);
			result = formatter.format((LocalDate) value);
		}
		if (value instanceof LocalDateTime) {
			DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).localizedBy(locale);
			result = formatter.format((LocalDateTime) value);
		}

		if (result != null) {
			try {
				JspWriter out = pageContext.getOut();
				out.write(result);
			} catch (IOException e) {
				logger.error("Can`t localize date", e);
				throw new JspException("Can`t localize date", e);
			}
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}
