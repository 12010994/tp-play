package controllers;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

public class Authenticator extends Security.Authenticator {

    @Override
    public Result onUnauthorized(Http.Context context) {
        return redirect(routes.Application.login());
    }

}
