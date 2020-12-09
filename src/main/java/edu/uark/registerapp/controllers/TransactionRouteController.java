package edu.uark.registerapp.controllers;

import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.controllers.enums.ViewNames;

@Controller
@RequestMapping(value = "/transaction")
public class TransactionRouteController extends BaseRouteController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showTransaction(final HttpServletRequest request) {
        final Optional<ActiveUserEntity> activeUserEntity =
            this.getCurrentUser(request);

        ModelAndView modelAndView = 
            new ModelAndView(ViewNames.TRANSACTION.getViewName());

            return modelAndView;
    }
}