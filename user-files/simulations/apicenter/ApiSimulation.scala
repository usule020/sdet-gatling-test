package apicenter

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

  /**
   * Created an simulation class
   * */
class ApiSimulation extends Simulation {

    /**
     * Included http baseURL and relevant headers required
     */

  private val httpProtocol = http
    .baseUrl("https://linetencustomerapi.azurewebsites.net")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("en-GB,en-US;q=0.9,en;q=0.8")
    .contentTypeHeader("application/json-patch+json")
    .upgradeInsecureRequestsHeader("1")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")

    /**
     * Created the following object classes, which was refactored from the scenario definition
     * - Add
     * - Get
     * - GetById
     */

    /**
     * This Add object adds personal information using the POST request and checks that the code response is 200
     * - This includes the JSON file path within the body function
     */
  object Add {
    val successfulAdd = exec(http("Successful_Add")
      .post("/Api/add")
      .body(RawFileBody("data/details.json"))
      .check(status.is(200))
    )
    }

    /**
     *  This Get object retrieves all the personal information using the GET request and checks that the code response is 200
     */
    object Get {
    val successfulGet =
      exec(
        http("Successful_Get")
          .get("/Api/get")
          .check(status.is(200)
          ))
  }

    /**
     * This GetById object retrieves personal information using the GET request by a valid ID and checks that the code response is 200
     * */
    object GetById {
    val successfulGetById =
      exec(
        http("Successful_Get_By_Id")
          .get("/Api/get/c241a259-58b0-4936-b27d-6a393f0c95eb")
          .check(status.is(200)
          ))
  }

  /**
   * This statement assigns a load testing scenario to a variable. This includes the three object which are defined above
   * */
  val users = scenario("Users").exec(Add.successfulAdd, Get.successfulGet, GetById.successfulGetById)


    /**
     * This setUp function allows for the creation of the load simulation using the scenario we assigned above
     * In this load simulation, it performs the following:
     * - do nothing for 5 seconds
     * - inject one user at once
     * - inject 5 users for a given duration of 10 seconds
     * - injects 20 users every second at a constant rate for a given duration of 20 seconds
     */

	setUp(users.inject(
      nothingFor(5),
      atOnceUsers(1),
      rampUsers(5) during (10),
      constantUsersPerSec(20) during (20)
      ))
    .protocols(httpProtocol)
}
