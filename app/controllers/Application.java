package controllers;

import controllers.forms.Login;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.Twitter;

import static play.data.Form.form;

public class Application extends Controller {

    final static Twitter twitter = Twitter.taa();

    @Security.Authenticated(Authenticator.class)
    public static Result index() {
        return ok(views.html.index.render(request().username()));
    }

    public static Result loginForm() {
        return ok(views.html.login.render(form(Login.class)));
    }

    public static Result login() {
        Form<Login> submission = form(Login.class).bindFromRequest();
        if (submission.hasErrors()) {
            return badRequest(views.html.login.render(submission));
        } else {
            session().put("username", submission.get().name);
            return redirect(routes.Application.index());
        }
    }

    public static Result logout() {
        session().remove("username");
        return ok(views.html.logout.render());
    }

    public static Result twitterFeed() {
        final EventSource eventSource = new EventSource() {
            @Override
            public void onConnected() {
                twitter.publicStream("endomondo", this);
            }

        };
        return ok(eventSource);
    }

}
