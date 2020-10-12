package edu.uark.registerapp.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uark.registerapp.commands.employees.EmployeeSignInCommand;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.models.api.EmployeeSignIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.commands.employees.ActiveEmployeeExistsQuery;
import edu.uark.registerapp.models.entities.ActiveUserEntity;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;


import java.io.IOException;
import java.util.Map;
import java.util.Optional;


@Controller
@RequestMapping(value = "/")
public class SignInRouteController extends BaseRouteController {
	// TODO: Route for initial page load
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView start(
			@RequestParam final Map<String, String> queryParameters,
			final HttpServletRequest request
	) {
		ModelAndView modelAndView = new ModelAndView(ViewNames.SIGN_IN.getViewName());

			final boolean activeUserExists = this.activeUserExists();
			final Optional<ActiveUserEntity> activeUserEntity = this.getCurrentUser(request);

			if (!activeUserExists) {
					modelAndView = new ModelAndView(
							REDIRECT_PREPEND.concat(
									ViewNames.EMPLOYEE_DETAIL.getRoute()));
				}

			if (activeUserEntity.isPresent()) {
				modelAndView = new ModelAndView(
					REDIRECT_PREPEND.concat(
							ViewNames.MAIN_MENU.getRoute()));
			}

			return modelAndView;
		}


	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView performSignIn(
		// TODO: Define an object that will represent the sign in request and add it as a parameter here
		final EmployeeSignIn employeeSignIn,
		final HttpServletRequest request,
		final HttpServletResponse response
	) throws IOException {
		String employeeId = request.getParameter("employeeId");
		String password = request.getParameter("password");
		String sessionId = request.getSession().getId();
		employeeSignIn.setEmployeeId(employeeId);
		employeeSignIn.setPassword(password);
		this.employeeSignInCommand.setEmployeeSignIn(employeeSignIn);
		this.employeeSignInCommand.setSessionId(sessionId);


		final boolean validCredentials = this.validCredentials();

		ModelAndView modelAndView = null;

		// TODO: Use the credentials provided in the request body
		//  and the "id" property of the (HttpServletRequest)request.getSession() variable
		//  to sign in the user

		if (validCredentials) {
			modelAndView = new ModelAndView(
					REDIRECT_PREPEND.concat(
							ViewNames.MAIN_MENU.getRoute()));
		}
		else{

			modelAndView = new ModelAndView(ViewNames.SIGN_IN.getViewName());
			request.setAttribute("errorMessage","INVALID EmployeeId or INVALID password");
		}

		return modelAndView;
	}


	private boolean validCredentials(){
		try{
			this.employeeSignInCommand.execute();
			return true;
		} catch (final NotFoundException e) {
			return false;
		}
	}

	private boolean activeUserExists() {
		try {
			this.activeEmployeeExistsQuery.execute();
			return true;
		} catch (final NotFoundException e) {
			return false;
		}
	}


	// Properties

	@Autowired
	private EmployeeSignInCommand employeeSignInCommand;

	@Autowired
	private ActiveEmployeeExistsQuery activeEmployeeExistsQuery;


}