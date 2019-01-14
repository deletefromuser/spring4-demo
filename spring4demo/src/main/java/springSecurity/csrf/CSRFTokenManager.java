package springSecurity.csrf;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CSRFTokenManager {

	static final String CSRF_PARAM_NAME = "CSRFToken";

	private final static String CSRF_TOKEN_FOR_SESSION_ATTR_NAME = CSRFTokenManager.class.getName() + ".tokenval";

	static String getTokenForSession(HttpSession session) {
		String token = null;
		// I cannot allow more than one token on a session - in the case of two
		// requests trying to
		// init the token concurrently.
		// Notice: in real life I wouldn't synchronize on the session instance.
		// This should be done on an attribute on the session. But for the
		// blog demo this is fine
		synchronized (session) {
			token = (String) session.getAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME);
			if (null == token) {
				token = UUID.randomUUID().toString();
				session.setAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME, token);
			}
		}
		return token;
	}

	static String getTokenFromRequest(HttpServletRequest request) {
		return request.getParameter(CSRF_PARAM_NAME);
	}

	private CSRFTokenManager() {
	};

}
