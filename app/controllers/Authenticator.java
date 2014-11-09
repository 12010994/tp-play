package controllers;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

public class Authenticator extends play.mvc.Security.Authenticator {
    @Override
    public Result onUnauthorized(Http.Context ctx) {
        return Results.redirect(routes.Authentication.login());
    }
}
