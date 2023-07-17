package by.htp.ex.util.name;

public class LinkName {
	private LinkName() {
	}

	public static final String LOGIN_JSP = "/WEB-INF/pages/login.jsp";
	public static final String COMMAND_GO_TO_AUTHENTICATION = "controller?command=go_to_authentication";
	public static final String COMMAND_GO_TO_VIEW_NEWS = "controller?command=go_to_view_news&id=";
	public static final String COMMAND_GO_TO_BASE_PAGE = "controller?command=go_to_base_page";
	public static final String COMMAND_GO_TO_ACCOUNT = "controller?command=go_to_account";
	public static final String COMMAND_GO_TO_REGISTRATION = "controller?command=go_to_registration";
	public static final String COMMAND_GO_TO_ERROR_PAGE = "controller?command=go_to_error_page";
	public static final String COMMAND_DO_SIGN_OUT = "controller?command=do_sign_out";
	public static final String REGISTRATION_JSP = "WEB-INF/pages/registration.jsp";
	public static final String INDEX_JSP = "index.jsp";
	public static final String ERROR_JSP = "error.jsp";
	public static final String MESSAGE_DONE = "&message=done";
	public static final String MESSAGE_ERROR = "controller?error=error";
	public static final String BASE_LAYOUT_JSP = "WEB-INF/pages/layouts/baseLayout.jsp";

}
