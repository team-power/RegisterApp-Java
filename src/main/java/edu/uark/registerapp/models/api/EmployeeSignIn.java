package edu.uark.registerapp.models.api;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import edu.uark.registerapp.models.entities.ProductEntity;
import org.apache.commons.lang3.StringUtils;


/*public class EmployeeSignIn {
    private String employeeID;
    private String password;
}*/

public class EmployeeSignIn extends ApiResponse {
    private UUID id;

    public UUID getId() {
        return this.id;
    }

    public EmployeeSignIn setId(final UUID id) {
        this.id = id;
        return this;
    }

    private String employeeID;

    public String getEmployeeID() {
        return this.employeeID;
    }

    public EmployeeSignIn setEmployeeID(final String employeeID) {
        this.employeeID = employeeID;
        return this;
    }

    private String password;

    public String getPassword() {
        return this.password;
    }

    public EmployeeSignIn setPassword(final String password) {
        this.password = password;
        return this;
    }

    public EmployeeSignIn() {
        super();

        this.employeeID = StringUtils.EMPTY;
        this.id = new UUID(0, 0);
        this.password = StringUtils.EMPTY;
    }

}