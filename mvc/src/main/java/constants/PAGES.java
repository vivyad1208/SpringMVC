package constants;

public enum PAGES {

	LOGIN("login"),
	LOGINREDIRECT("redirect:login"),
	HOMEPAGE("homepage"),
	HOMEPAGEREDIRECT("redirect:homepage"),
	AUTHENTICATE("authenticate"),
	AUTHENTICATEREDIRECT("redirect:authenticate"),
	FEEDBACK("feedback"),
	FEEDBACKREDIRECT("redirect:feedback");

	private String strPage;

	PAGES(String page) { strPage = page; }

	public String getPage() {
		return strPage;
	}
}
