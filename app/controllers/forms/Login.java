package controllers.forms;

import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class Login {

    @Constraints.Required
    public String name;

    @Constraints.Required
    @Constraints.MinLength(4)
    public String password;

    public List<ValidationError> validate() {
        // TODO Appel du service
        return "julien".equals(name) ? null : new ArrayList<ValidationError>() {{ add(new ValidationError("", "Invalid user name or password")); }};
    }

}
