package com.airline.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.models.Gender;
import com.airline.models.Passenger;

/**
 * Servlet implementation class AddPassenger
 */
@WebServlet("/AddPassenger") //url for this serverlet
public class AddPassenger extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddPassenger() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("first-name", "");
		request.setAttribute("last-name", "");
		request.setAttribute("dob", "");
		request.setAttribute("gender", "Unspecified");

		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/add_passenger.jsp");

		view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("errors", false);

		Passenger p = new Passenger();

		String firstName = request.getParameter("first-name");
		System.out.println(firstName);

		if (firstName.length() == 0) {
			System.out.println("Empty first name error");
			request.setAttribute("errors", true);
			request.setAttribute("firstNameError", true);
			request.setAttribute("first-name", "");
		} else {
			p.setFirstName(firstName);
			request.setAttribute("first-name", firstName);
		}

		String lastName = request.getParameter("last-name");
		System.out.println(lastName);

		if (lastName.length() == 0) {
			System.out.println("Empty last name error");
			request.setAttribute("errors", true);
			request.setAttribute("lastNameError", true);
			request.setAttribute("last-name", "");
		} else {
			p.setLastName(lastName);
			request.setAttribute("last-name", lastName);
		}

		String dobRaw = request.getParameter("dob");
		System.out.println(dobRaw);

		String dobArray[] = dobRaw.split("\\/");

		String pattern = "^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$";
		Pattern r = Pattern.compile(pattern);

		Matcher m = r.matcher(dobRaw);

		if (m.find()) {
			String month = dobArray[0];
			String day = dobArray[1];
			String year = dobArray[2];

			Calendar cal = Calendar.getInstance();

			cal.set(Calendar.YEAR, Integer.parseInt(year));
			cal.set(Calendar.MONTH, Integer.parseInt(month));
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));

			Date dob = cal.getTime();

			p.setDob(dob);
			request.setAttribute("dob", dobRaw);
		} else {
			System.out.println("Invalid date of birth");
			request.setAttribute("errors", true);
			request.setAttribute("dateFormatError", true);
			request.setAttribute("dob", dobRaw);
			if(dobRaw.length() ==0){
				request.setAttribute("dob", "");
			}
		}

		String gender = request.getParameter("gender");
		System.out.println("Gender: " + gender);
		p.setGender(Gender.valueOf(gender));

		if ((Boolean) request.getAttribute("errors") == true) {
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/add_passenger.jsp");

			view.forward(request, response);

		} else {

			ServletContext sc = this.getServletContext();

			synchronized (this) {
				ArrayList<Passenger> pList = (ArrayList<Passenger>) sc.getAttribute("passengers");

				pList.add(p);

				sc.setAttribute("passengers", pList);
			}

			response.sendRedirect("");
			
		}
	}

}
