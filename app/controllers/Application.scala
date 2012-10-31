package controllers

import util.Commons._
import play.api.mvc._
import models.OPowerService
import play.api.data._
import play.api.data.Forms._
import play.api.libs.ws.WS

object Application extends Controller with Authentication {

  // Reference to the OPower service
  lazy val opower = OPowerService()

  /**
   * Show the dashboard of a user
   */
  val index = Authenticated { username => request =>
    Ok(views.html.index(username))
  }

  def loginForm: Form[(String, String)] = Form(tuple("name" -> text, "pwd" -> text))

  /**
   * Authenticate the user
   */
  val authenticate = Action { implicit request =>
  /*
   * Check if data from the form submission can authenticate the user
   * TODO Modify this function to call your service
   */
    def checkAuthentication(name: String, pwd: String): Boolean = true

    val username = "User" // TODO get the username from the form data
    val password = "password" // TODO get the password from the form data

    if (checkAuthentication(username, password)) {
      signingIn(username)(Ok)
    } else {
      BadRequest
    }
  }

  /**
   * Show the login page
   */
  val login = Action { request =>
    Ok(views.html.login(loginForm))
  }

  /**
   * Sign out the user
   */
  val logout = Action { implicit request =>
    NotImplemented
  }

  val onUnauthenticated = Forbidden

  /**
   * Work with PVWatts (http://developer.nrel.gov/doc/pvwatts)
   */
  val askPvwatts = Authenticated { username => request =>
    val userAddress = "???" // TODO retrieve the user address using your OPower service
    val pvwattsP = WS.url("http://developer.nrel.gov/api/georeserv/app/sam/pvwatts.json")
      .withQueryString(
        "api_key" -> ???,
        "address" -> userAddress,
        "system_size" -> "42",
        "timeframe" -> "monthly"
      ).get()
    NotImplemented
  }
}