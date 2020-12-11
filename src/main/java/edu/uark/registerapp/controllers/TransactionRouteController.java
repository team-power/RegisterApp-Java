package edu.uark.registerapp.controllers;

import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.models.api.Product;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.models.entities.ProductEntity;
import edu.uark.registerapp.models.repositories.ProductRepository;

@Controller
@RequestMapping(value = "/transaction")
public class TransactionRouteController extends BaseRouteController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showTransaction(final HttpServletRequest request, @RequestParam Map<String, String> products) {
        final Optional<ActiveUserEntity> activeUserEntity =
            this.getCurrentUser(request);
        if (!activeUserEntity.isPresent()) {
            return this.buildInvalidSessionResponse();
        } else if (!this.isElevatedUser(activeUserEntity.get())) {
            return this.buildNoPermissionsResponse();
        }   

        ModelAndView modelAndView = 
            new ModelAndView(ViewNames.TRANSACTION.getViewName());

            final LinkedList<Product> productsInTransaction = new LinkedList<Product>();

        for (Map.Entry<String, String> product : products.entrySet()) {
            Optional<ProductEntity> productEntity = productRepository.findByLookupCode(product.getKey());
            if (productEntity.isPresent() && productEntity.get().getCount() > 0) {
                try {
                    productsInTransaction.addLast(new Product(productEntity.get()).setCount(Integer.parseInt(product.getValue())));
                } catch (final Exception e) {
                    continue;
                }
            }
        }

        modelAndView.addObject(
				ViewModelNames.PRODUCTS.getValue(),
				productsInTransaction);

        return modelAndView;
    }

    // Properties
	@Autowired
	private ProductRepository productRepository;
}

