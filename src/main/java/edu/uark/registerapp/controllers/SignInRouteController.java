package edu.uark.registerapp.controllers;

import javax.servlet.http.HttpServletRequest;

import edu.uark.registerapp.commands.employees.EmployeeQuery;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.models.api.EmployeeSignIn;
import edu.uark.registerapp.models.api.Product;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.models.repositories.EmployeeRepository;
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
		ModelAndView modelAndView = new ModelAndView(ViewNames.SIGN_IN.getViewName());;

			final boolean activeUserExists = this.activeUserExists();

			if (!activeUserExists) {
					modelAndView = new ModelAndView(
							REDIRECT_PREPEND.concat(
									ViewNames.MAIN_MENU.getRoute()));
				}

			return modelAndView;
		}


	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView performSignIn( @RequestBody EmployeeSignIn employeeSignIn,
		// TODO: Define an object that will represent the sign in request and add it as a parameter here

		HttpServletRequest request
	) {


		// TODO: Use the credentials provided in the request body
		//  and the "id" property of the (HttpServletRequest)request.getSession() variable
		//  to sign in the user



		return new ModelAndView(
			REDIRECT_PREPEND.concat(
				ViewNames.MAIN_MENU.getRoute()));
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
	private ActiveEmployeeExistsQuery activeEmployeeExistsQuery;

}

/*@Controller
@RequestMapping(value = "/")
public class SignInRouteController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView start() {
		return (new ModelAndView("productListing"));
	}
}*/