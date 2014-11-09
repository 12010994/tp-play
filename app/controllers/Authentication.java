package controllers;

import play.data.Form;
import play.data.validation.ValidationError;
import play.mvc.Controller;
import play.mvc.Result;
import scala.NotImplementedError;

import java.util.ArrayList;
import java.util.List;

import static play.data.Form.form;

/**
 * Controller grouping actions related to authentication
 */
public class Authentication extends Controller {
    /**
     * Show the authentication form
     */
    public static Result login() {
        return ok(views.html.login.render(form(Login.class)));
    }

    /**
     * Handle the authentication form submission.
     *
     * If the submitted data is invalid (e.g. the user password is wrong), this action must return a 400 status code
     * and show again the form with its errors.
     *
     * Otherwise, the user must be authenticated (his user id should be stored into his session) and redirected to the index page.
     */
    public static Result authenticate() {
        throw new NotImplementedError();
    }

    /**
     * Logs out an user (remove his id from his session) and show a good bye message
     */
    public static Result logout() {
        throw new NotImplementedError();
    }

    /**
     * @return The current user name
     */
    public static String username() {
        return session("username");
    }

    /**
     * Map the data of the login form submission.
     *
     * Example of use:
     *
     * <pre>
     *     Form<Login> submission = form(Login.class).bindFromRequest();
     * </pre>
     */
    public static class Login {

        public String name;

        public String password;

        public List<ValidationError> validate() {
            return null;
        }

    }
}
