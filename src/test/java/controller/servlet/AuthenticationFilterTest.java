package controller.servlet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;

public class AuthenticationFilterTest {
	
	@Test
	public void whenActionNullAndNoAllowedPageThenForwardToDefaultCommand() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		FilterChain filterChain = mock(FilterChain.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher("main?action=default")).thenReturn(reqDisp);
		AuthorizationFilter filter = new AuthorizationFilter();
		filter.doFilter(request, response, filterChain);
		
		verify(reqDisp).forward(request, response);
	}
	
	@Test
	public void whenActionNullAndPageIsLoginThenForwardToLoginPage() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		FilterChain filterChain = mock(FilterChain.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(request.getServletPath()).thenReturn("/login.jsp");
		when(request.getRequestDispatcher("/login.jsp")).thenReturn(reqDisp);
		AuthorizationFilter filter = new AuthorizationFilter();
		filter.doFilter(request, response, filterChain);
		
		verify(reqDisp).forward(request, response);
	}
	
	@Test
	public void whenActionNullAndPageIsRegisterThenForwardToRegisterPage() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		FilterChain filterChain = mock(FilterChain.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(request.getServletPath()).thenReturn("/register.jsp");
		when(request.getRequestDispatcher("/register.jsp")).thenReturn(reqDisp);
		AuthorizationFilter filter = new AuthorizationFilter();
		filter.doFilter(request, response, filterChain);
		
		verify(reqDisp).forward(request, response);
	}
	
	@Test
	public void whenActionNotLogoutAndUserTypeIsUserAndIsBlockedThenForwardToPrivateCabinet() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		FilterChain filterChain = mock(FilterChain.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("action")).thenReturn("default");
		when(session.getAttribute("isBlocked")).thenReturn(Boolean.TRUE);
		when(session.getAttribute("currentUserType")).thenReturn("user");
		when(request.getRequestDispatcher("main?action=prepareCabinet")).thenReturn(reqDisp);
		AuthorizationFilter filter = new AuthorizationFilter();
		filter.doFilter(request, response, filterChain);
		
		verify(reqDisp).forward(request, response);
	}
	
	@Test
	public void whenActionNotLogoutAndUserTypeIsAdminAndIsBlockedThenForwardToDefaultAction() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		FilterChain filterChain = mock(FilterChain.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("action")).thenReturn("default");
		when(session.getAttribute("isBlocked")).thenReturn(Boolean.TRUE);
		when(session.getAttribute("currentUserType")).thenReturn("admin");
		when(request.getRequestDispatcher("main?action=default")).thenReturn(reqDisp);
		AuthorizationFilter filter = new AuthorizationFilter();
		filter.doFilter(request, response, filterChain);
		
		verify(reqDisp).forward(request, response);
	}
	
	@Test
	public void whenActionNotLogoutAndUserTypeIsLibrarianAndIsBlockedThenForwardToDefaultAction() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		FilterChain filterChain = mock(FilterChain.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("action")).thenReturn("default");
		when(session.getAttribute("isBlocked")).thenReturn(Boolean.TRUE);
		when(session.getAttribute("currentUserType")).thenReturn("librarian");
		when(request.getRequestDispatcher("main?action=default")).thenReturn(reqDisp);
		AuthorizationFilter filter = new AuthorizationFilter();
		filter.doFilter(request, response, filterChain);
		
		verify(reqDisp).forward(request, response);
	}
	
	@Test
	public void whenActionNotInUserEnumAndUserTypeIsUserAndNotIsBlockedThenForwardToDefaultAction() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		FilterChain filterChain = mock(FilterChain.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("action")).thenReturn("addBook");
		when(session.getAttribute("isBlocked")).thenReturn(Boolean.FALSE);
		when(session.getAttribute("currentUserType")).thenReturn("user");
		when(request.getRequestDispatcher("main?action=default")).thenReturn(reqDisp);
		AuthorizationFilter filter = new AuthorizationFilter();
		filter.doFilter(request, response, filterChain);
		
		verify(reqDisp).forward(request, response);
	}
	
	@Test
	public void whenActionNotInLibrarianEnumAndUserTypeIsLibrarianAndNotIsBlockedThenForwardToDefaultAction() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		FilterChain filterChain = mock(FilterChain.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("action")).thenReturn("addBook");
		when(session.getAttribute("isBlocked")).thenReturn(Boolean.FALSE);
		when(session.getAttribute("currentUserType")).thenReturn("librarian");
		when(request.getRequestDispatcher("main?action=default")).thenReturn(reqDisp);
		AuthorizationFilter filter = new AuthorizationFilter();
		filter.doFilter(request, response, filterChain);
		
		verify(reqDisp).forward(request, response);
	}
	
	@Test
	public void whenActionNotInAdminEnumAndUserTypeIsAdminAndNotIsBlockedThenForwardToDefaultAction() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		FilterChain filterChain = mock(FilterChain.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("action")).thenReturn("addBookOrder");
		when(session.getAttribute("isBlocked")).thenReturn(Boolean.FALSE);
		when(session.getAttribute("currentUserType")).thenReturn("admin");
		when(request.getRequestDispatcher("main?action=default")).thenReturn(reqDisp);
		AuthorizationFilter filter = new AuthorizationFilter();
		filter.doFilter(request, response, filterChain);
		
		verify(reqDisp).forward(request, response);
	}
	
	@Test
	public void whenActionNotInGuestEnumAndUserTypeIsGuestAndNotIsBlockedThenForwardToDefaultAction() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		FilterChain filterChain = mock(FilterChain.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("action")).thenReturn("addBookOrder");
		when(session.getAttribute("isBlocked")).thenReturn(Boolean.FALSE);
		when(session.getAttribute("currentUserType")).thenReturn("guest");
		when(request.getRequestDispatcher("main?action=default")).thenReturn(reqDisp);
		AuthorizationFilter filter = new AuthorizationFilter();
		filter.doFilter(request, response, filterChain);
		
		verify(reqDisp).forward(request, response);
	}

	@Test
	public void whenActionCorrectForUserTypeAndNotIsBlockedThenFilterShouldPassToNextFilter() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		FilterChain filterChain = mock(FilterChain.class);
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("action")).thenReturn("default");
		when(session.getAttribute("isBlocked")).thenReturn(Boolean.FALSE);
		when(session.getAttribute("currentUserType")).thenReturn("user");
		AuthorizationFilter filter = new AuthorizationFilter();
		filter.doFilter(request, response, filterChain);
		
		verify(filterChain).doFilter(request, response);;
	}
	
}
