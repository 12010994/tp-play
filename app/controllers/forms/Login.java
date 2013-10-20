package controllers.forms;

import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Récupère les données soumises par le formulaire de login (défini dans la vue `views/login.scala.html`).<br />
 * Exemple d’utilisation (dans un controller) :
 * <pre>
 *     Form&lt;Login&gt; submission = form(Login.class).bindFromRequest();
 * </pre>
 */
public class Login {

    public String name;

    public String password;

    public List<ValidationError> validate() {
        // TODO Appel de votre service Endomondo
        return "toto".equals(name) ? null : new ArrayList<ValidationError>() {{ add(new ValidationError("", "Invalid user name or password")); }};
    }

}
