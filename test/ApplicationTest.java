import controllers.routes;
import org.junit.Test;
import play.libs.F;
import play.test.TestBrowser;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;
import static play.test.Helpers.HTMLUNIT;

public class ApplicationTest {

    @Test
    public void signingUser() {
        running(testServer(3333), HTMLUNIT, new F.Callback<TestBrowser>() {
            @Override
            public void invoke(TestBrowser browser) {
                // Try to go to the index page
                browser.goTo("http://localhost:3333/");
                // User is redirected to the login page
                assertThat(browser.url()).isEqualTo("http://localhost:3333/???");

                // Fill the signing in form
                browser.fill("[name=name]").with("???");
                browser.fill("[name=password]").with("???");
                browser.submit("form");
                // User is logged in and redirected to the index page
                // TODO

                // Logout
                // TODO
            }
        });
    }

    @Test
    public void invalidLogin() {
        running(testServer(3333), HTMLUNIT, new F.Callback<TestBrowser>() {
            @Override
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333/");
                // Submit an empty form
                // TODO
                // Validation error
                assertThat(browser.pageSource()).contains("???");

                // Submit an invalid form
                browser.fill("[name=name]").with("???");
                browser.fill("[name=password]").with("???");
                browser.submit("form");
                assertThat(browser.pageSource()).contains("???");
            }
        });
    }

}
