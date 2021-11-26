//Using a servlet and getting input from a jsp page, this program gets numbers
//from a jsp page and then from a chosen +, -, /, or * will perform the operation on 
//either initially 0 or the last number that was provided, and will continue to present
//the numbers until no more are left

package calc;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

@WebServlet("/UseCalc")
public class UseCalc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//for all numbers for calculations and total displayed
	String sol = "0";
	double numTot = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UseCalc() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//display calculator jsp
		RequestDispatcher rd = request.getRequestDispatcher("/displayCalc.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//display numbers for calculator with trail of numbers 
		HttpSession session = request.getSession();
		//if clear is set, resets calcuator page to 0 with operand and number inputted
		String  clearSession=(String)request.getParameter("clearSession");
		logger("clearSession: "+clearSession);
		if (clearSession != null && clearSession.equals("on"))
		{
			session.setAttribute("sol", null);
			session.setAttribute("trail", "");
			session.setAttribute("numTot", 0);
		}
		try {
			//gets number from jsp
			String  nums=(String)request.getParameter("nums");
			logger("post nums: "+nums);
			double numIn =0;
			if (nums!=null)
				numIn = Double.parseDouble(nums);
			String op = request.getParameter("op");
			String trail = "";
			//display previous trail of numbers with new total
			if (session != null && session.getAttribute("numTot") != null) {
				trail = (String)session.getAttribute("trail");
				if (session.getAttribute("numTot") instanceof Double)
					numTot = (Double) session.getAttribute("numTot");
				else if (session.getAttribute("numTot") instanceof Integer)
					numTot = (Integer) session.getAttribute("numTot");
			} else {
				numTot = 0;
			}
				//display calculation with trail of numbers and calcuations to jsp
				DoCalc cal = new DoCalc();
				double total = cal.Calculate(numTot, numIn, op);
				if("".equals(trail))
					trail = numTot+" ";
				trail += String.format("%s %.2f = %.2f ", op, numIn, total );
				session.setAttribute("trail", trail);
				session.setAttribute("numTot", total);
				sol = "";
				if (session.getAttribute("sol") != null)
					sol = (String) session.getAttribute("sol");
				else
					sol = "0";
				session.setAttribute("sol", sol);
				RequestDispatcher rd = request.getRequestDispatcher("/displayCalc.jsp");
				request.setAttribute("sol", sol);
				request.setAttribute("trail", trail);
				rd.forward(request, response);

		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("/displayCalc.jsp");
			rd.forward(request, response);
		}
	}
	private static void logger (String msg)
	{
		System.out.println(msg);
	}
}
