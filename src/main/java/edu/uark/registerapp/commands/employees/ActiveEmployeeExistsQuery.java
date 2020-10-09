package edu.uark.registerapp.commands.employees;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.commands.exceptions.UnprocessableEntityException;
import edu.uark.registerapp.models.api.Product;
import edu.uark.registerapp.models.entities.ProductEntity;
import edu.uark.registerapp.models.repositories.ProductRepository;
import edu.uark.registerapp.models.api.Employee;
import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.models.repositories.EmployeeRepository;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.models.repositories.ActiveUserRepository;


/*@Service
public class ActiveEmployeeExistsQuery implements ResultCommandInterface<Employee> {
    @Override
    public Employee execute() {
        Employee employee = null;
       boolean isActive = employee.getIsInitialEmployee();
    return new NotFoundException("No active Users") ;
    }
}



  /*      final Optional<ActiveUserEntity> activeUserEntity =
                this.activeUserRepository.findBySessionKey(this.sessionKey);


        final Optional<EmployeeEntity> employeeEntity =
                EmployeeRepository.findByEmployeeId(this.employeeId);
        if (EmployeeRepository.existsByIsActive()) {
            return new Employee(employeeEntity.get());
        } else {
            throw new NotFoundException("No active Users");
    }
}


    // Helper methods
    private void validateProperties() {
        if (NumberUtils.(this.employeeId)) {
            throw new UnprocessableEntityException("activeUsers");
        }
    }

    // Properties
    private String employeeId;
    public String getActiveUsers() {
        return this.employeeId;
    }

    public edu.uark.registerapp.commands.employees.ActiveEmployeeExistsQuery setActiveUsers(final String employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    @Autowired
    private EmployeeRepository employeeRepository;
}

@Service
public class ProductByLookupCodeQuery implements ResultCommandInterface<Product> {
    @Override
    public Product execute() {
        this.validateProperties();

        final Optional<ProductEntity> productEntity =
                this.productRepository.findByLookupCode(this.lookupCode);
        if (productEntity.isPresent()) {
            return new Product(productEntity.get());
        } else {
            throw new NotFoundException("Product");
        }
    }

    // Helper methods
    private void validateProperties() {
        if (StringUtils.isBlank(this.lookupCode)) {
            throw new UnprocessableEntityException("lookupcode");
        }
    }

    // Properties
    private String lookupCode;
    public String getLookupCode() {
        return this.lookupCode;
    }
    public edu.uark.registerapp.commands.products.ProductByLookupCodeQuery setLookupCode(final String lookupCode) {
        this.lookupCode = lookupCode;
        return this;
    }

    @Autowired
    private ProductRepository productRepository;
}


