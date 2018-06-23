package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class PageNotFound
 */
@WebServlet({ "*.*", "/" })
public class PageNotFound extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(PageNotFound.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageNotFound() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			PrintWriter out = response.getWriter();
			out.append("<html><body><h1>Web Application Error</h1><h3>Error 404: Page Not Found!</h3></body></html>");
		}
		catch (Exception e) {
			logger.error("This is Error message at Welcome GET!", new Exception("Testing"));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			doGet(request, response);
		}
		catch (Exception e) {
			logger.error("This is Error message at Welcome POST!", new Exception("Testing"));
		}
	}

}
