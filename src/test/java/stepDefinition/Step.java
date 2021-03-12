package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import excel.Excel;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Step extends Excel {

	static RequestSpecification request;
	static Response responsetoken;

	static String username;
	static String password;
	static String email;
	static String phone;


	static String baseuri;
	static String geturi1;
	static String geturi2;
	static String viewURI;
	static String likeURI;
	static String likeURI1;
	static String likeURI2;
	static String commentURI;
	static String commentURI2;

	static String code;
	static String token;
	static String userId;
	static String postId;


	static JSONObject body;
	static JSONObject body2;
	static JSONObject body3;
	static JSONObject body4;
	static JSONObject body5;
	static JSONObject body6;
	static JSONObject body7;
	static JSONObject body8;
	static JSONObject body9;
	static JSONObject body10;
	static JSONObject body11;
	static JSONObject body12;

	static 	Response Accountresponse;
	static Response Accountresponse2;
	static Response Accountresponse3;
	static 	Response Accountresponse4;
	static 	Response Accountresponse5;
	static 	Response Accountresponse6;
	static 	Response Accountresponse7;
	static 	Response Accountresponse8;
	static 	Response Accountresponse9;
	static 	Response Accountresponse10;
	static 	Response Accountresponse11;
	static 	Response Accountresponse12;
	static 	Response Accountresponse13;



	@Given("Im in SBN app using the baseURI")
	public void im_in_SBN_app_using_the_baseURI() throws Throwable {
		baseuri = excelread(1, 1, "AccountCreation");

		System.out.println(baseuri);
		RestAssured.baseURI = baseuri;
		request = RestAssured.given();
	}


	@When("I am creating a new account")
	public void i_am_creating_a_new_account() throws Throwable {
		body = new JSONObject();
		body.put(excelread(1, 2,"AccountCreation" ), excelread(1, 3, "AccountCreation"));
		body.put(excelread(2, 2, "AccountCreation") , excelread(2, 3, "AccountCreation"));
		body.put(excelread(3, 2,"AccountCreation") , excelread(3, 3, "AccountCreation"));
		body.put(excelread(4, 2, "AccountCreation"), excelread(4, 3,"AccountCreation"));
		body.put(excelread(5, 2, "AccountCreation") , excelread(5, 3, "AccountCreation"));
		body.put(excelread(6, 2, "AccountCreation") , excelread(6, 3, "AccountCreation"));
		body.put(excelread(7, 2, "AccountCreation") , excelread(7, 3, "AccountCreation"));
		body.put(excelread(8, 2, "AccountCreation"), excelread(8, 3, "AccountCreation"));

		request.header("Content-Type", "application/json");
	}


	@When("Im posting the body in the URI")
	public void im_posting_the_body_in_the_URI() throws Throwable {
		Accountresponse= request.body(body).post(excelread(1, 4, "AccountCreation"));
	}


	@Then("account is created and I am printing the details of the account")
	public void account_is_created_and_I_am_printing_the_details_of_the_account() throws Throwable {

		Accountresponse.prettyPrint();

	}


	@And("I am getting the username and password")
	public void i_am_getting_the_username_and_password() throws Throwable {
		String jsonstring = Accountresponse.getBody().asString();
		username	=JsonPath.from(jsonstring).get("username");
		password	=JsonPath.from(jsonstring).get("password");
		email	=JsonPath.from(jsonstring).get("email");
		phone	=JsonPath.from(jsonstring).get("phone");
		System.err.println(username);
		System.err.println(password);
		System.err.println(email);
		System.err.println(phone);
		assertEquals(excelread(1, 3, "AccountCreation"), username);
		assertEquals(excelread(3, 3, "AccountCreation"), password);

		int statuscode = Accountresponse.getStatusCode();
		System.out.println(statuscode);
		assertEquals(statuscode, 200);
		System.err.println("Account Created");
	}


	@Given("I have created an account and load the base URI")
	public void i_have_created_an_account_and_load_the_base_URI()   throws Throwable {

		RestAssured.baseURI = baseuri;
		request = RestAssured.given();
	}


	@When("I login with username and password")
	public void i_login_with_username_and_password() throws Throwable {
		body2 = new JSONObject();
		body2.put(excelread(1, 2, "LogIN"), username);
		body2.put(excelread(2, 2, "LogIN"), password);

		request.header("Content-Type", "application/json");
	}


	@And("^I post login details in the URI /oauth/code$")
	public void i_post_login_details_in_the_uri_oauthcode() throws Throwable {
		Accountresponse2= request.body(body2).post(excelread(1, 4, "LogIN"));
	}


	@Then("^I should able to login$")
	public void i_should_able_to_login() throws Throwable {
		Accountresponse2.prettyPrint();
	}


	@And("^I print the code$")
	public void i_print_the_code() throws Throwable {
		String jsonstring = Accountresponse2.getBody().asString();
		code	=JsonPath.from(jsonstring).get("code");
		userId	=JsonPath.from(jsonstring).get("userId");
		System.out.println(code);
		System.out.println(userId);

		int statuscode = Accountresponse2.getStatusCode();
		System.out.println(statuscode);
		assertEquals(statuscode, 200);

		System.err.println("Log-in Done");
	}


	@Given("I have created userID and code and load the base URI")
	public void i_have_created_userID_and_code_and_load_the_base_URI()  throws Throwable {

		RestAssured.baseURI = baseuri;
		request = RestAssured.given();
	}


	@When("I load the code and post in the URI \\/oauth\\/code")
	public void i_load_the_code_and_post_in_the_URI_oauth_code() throws Throwable {
		body3 = new JSONObject();
		body3.put(excelread(1, 2, "TokenGenerate"), excelread(1, 3, "TokenGenerate"));
		body3.put(excelread(2, 2, "TokenGenerate"), code);

		request.header("Content-Type", "application/json");
	}


	@Then("^I should able to generate token$")
	public void i_should_able_to_generate_token() throws Throwable {
		Accountresponse3= request.body(body3).post(excelread(1, 4, "TokenGenerate"));
		Accountresponse3.prettyPrint();
	}


	@And("^I am printing the token$")
	public void i_am_printing_the_token() throws Throwable {
		String jsonstring =	Accountresponse3.getBody().asString();
		token = JsonPath.from(jsonstring).get("access_token.token.token");
		System.out.println(token);
		int statuscode = Accountresponse3.getStatusCode();
		System.out.println(statuscode);
		assertEquals(statuscode, 200);
		System.err.println("Account Created- Logged in- and- Token Generated");
	}


	@Given("I have the token and userID to view the profile and load the base URI")
	public void i_have_the_token_and_userID_to_view_the_profile_and_load_the_base_URI() throws Throwable {


		RestAssured.baseURI = baseuri;
		request = RestAssured.given();

	}

	@When("^I post the token and userid in the URI$")

	public void i_post_the_token_and_userid_in_the_uri() throws Throwable {
		viewURI = excelread(1, 4, "ViewAccount");

	}

	@Then("I should able to view the profile")
	public void i_should_able_to_view_the_profile() throws Throwable {
		given().header("Authorization", "bearer "+ token).
		param("userId", userId).when().get(viewURI+userId).then().statusCode(200).log().all(true);

		System.err.println("Account viewed");
	}

	@Given("I have a new account and load the base URI")
	public void i_have_a_new_account_and_load_the_base_URI() throws Throwable {


		RestAssured.baseURI = baseuri;
		request = RestAssured.given();
	}


	@When("^I create a body and post in the URI$")
	public void i_create_a_body_and_post_in_the_uri() throws Throwable {

		//		String newpost = excelread(1, 2, "CreatePost");
		//		body4 = newpost;

		JSONObject filename = new JSONObject();

		JSONArray arrayvalue = new JSONArray();
		filename.put(excelread(9, 3, "CreatePost"), excelread(9, 4, "CreatePost"));
		arrayvalue.add(filename);

		body4 = new JSONObject();
		body4.put(excelread(3, 2, "CreatePost"), excelread(3, 4, "CreatePost"));
		body4.put(excelread(4, 2, "CreatePost"), excelread(4, 4, "CreatePost"));
		body4.put(excelread(5, 2, "CreatePost"), excelread(5, 4, "CreatePost"));
		body4.put(excelread(6, 2, "CreatePost"), excelread(6, 4, "CreatePost"));
		body4.put(excelread(7, 2, "CreatePost"), excelread(7, 4, "CreatePost"));
		body4.put(excelread(8, 2, "CreatePost"), excelread(8, 4, "CreatePost"));
		body4.put(excelread(9, 2, "CreatePost"), arrayvalue);




		request.header("Authorization", "bearer "+token).header("Content-Type", "application/json");

		System.out.println(body4);


	}


	@Then("I am printing the response")
	public void i_am_printing_the_response()  throws Throwable {

		Accountresponse4= request.body(body4).post(excelread(1, 5, "CreatePost"));
		System.out.println(body4);
		Accountresponse4.prettyPrint();
	}


	@And("I get the post-ID from the body")
	public void i_get_the_post_ID_from_the_body() throws Throwable {
		String jsonstring = Accountresponse4.getBody().asString();
		postId	=JsonPath.from(jsonstring).get("_id");

		System.out.println(postId);
		System.out.println(Accountresponse4.getStatusCode());

		System.err.println("New post added");

	}


	@Given("^I have added a new post to the account$")
	public void i_have_added_a_new_post_to_the_account() throws Throwable {

		RestAssured.baseURI = baseuri;
		request = RestAssured.given();

	}


	@When("^I post the Post-ID and user-ID in the URI$")
	public void i_post_the_postid_and_userid_in_the_uri() throws Throwable {
		geturi1 = excelread(1, 4, "GetPost");

		given().header("Authorization", "bearer "+token).
		param("userId", postId).header("Content-Type", "application/json");


	}


	@Then("^I should get the corresponding post information$")
	public void i_should_get_the_corresponding_post_information() throws Throwable {

		Accountresponse5 = request.get(geturi1+postId);
		Accountresponse5.prettyPrint();
		int statuscode =	Accountresponse5.getStatusCode();
		assertEquals(statuscode, 200);

		System.err.println("Account Created then it is Validated then Post Added and the Created Post is Viewed");
	}


	@Given("I have a new post")
	public void i_have_a_new_post() {
		RestAssured.baseURI = baseuri;
		request = RestAssured.given();

	}

	@When("I like the post")
	public void i_like_the_post() throws Throwable {
		likeURI = excelread(1, 4, "LikePost");

	}

	@Then("I should get status code as {int}")
	public void i_should_get_status_code_as(Integer int1) {
		given().header("Authorization", "bearer "+ token).header("Content-Type", "application/json").
		when().post(likeURI+postId).then().statusCode(200).log().all(true);


		System.err.println("Post liked");

	}

	@Given("I have liked a post")
	public void i_have_liked_a_post() {
		RestAssured.baseURI = baseuri;
		request = RestAssured.given();

	}

	@When("I give the token and postID")
	public void i_give_the_token_and_postID() throws Throwable {

		likeURI1 = excelread(1, 4,"Get-LikedPost");
		likeURI2 = excelread(1, 5, "Get-LikedPost");

	}

	@Then("I can get the post which is liked")
	public void i_can_get_the_post_which_is_liked() {

		given().header("Authorization", "bearer "+token).
		param("userId", postId).
		param("objectName", "like").
		param("isDetailed", "true").when().get(likeURI1+postId+likeURI2).
		then().statusCode(200).log().all(true);		
		System.err.println("Liked post viewed");

	}

	@Given("I have a post to comment")
	public void i_have_a_post_to_comment() {
		RestAssured.baseURI = baseuri;
		request = RestAssured.given();

	}

	@When("I comment in the post")
	public void i_comment_in_the_post() throws Throwable {
		commentURI = excelread(1, 4, "Post-Comment");
		body5 = new JSONObject();
		body5.put(excelread(1, 2, "Post-Comment"), excelread(1, 3, "Post-Comment"));
		request.header("Authorization", "bearer "+ token).header("Content-Type", "application/json");

	}

	@Then("I get status code as {int}")
	public void i_should_get_statuscode_as(Integer int1 ) throws Throwable{

		Accountresponse6 = request.body(body5).post(commentURI+postId);
		Accountresponse6.prettyPrint();
		int statuscode3 = Accountresponse6.getStatusCode();
		assertEquals(statuscode3, 200);
		System.err.println("Comment posted");

	}

	@Given("I have a post with the comment")
	public void i_have_a_post_with_the_comment() {
		RestAssured.baseURI = baseuri;
		request = RestAssured.given();


	}

	@When("I give the token")
	public void i_give_the_token() throws Throwable {
		commentURI2 = excelread(1, 4, "Get-Comment");
		given().header("Authorization", "bearer "+token);


	}

	@Then("I should get the comment from the post")
	public void i_should_get_the_comment_from_the_post() {


		given().header("Authorization", "bearer "+ token).
		when().get(commentURI2+postId).then().statusCode(200).log().all(true);
		System.err.println("Comment viewed");
	}

	@Given("Im in the SBN app using the baseURI")
	public void im_in_the_SBN_app_using_the_baseURI() {
		RestAssured.baseURI = baseuri;
		request = RestAssured.given();

	}

	@When("I try to create an account with existing details")
	public void i_try_to_create_an_account_with_existing_details() throws Throwable {
		body6 = new JSONObject();
		body6.put(excelread(1, 2,"AccountCreation" ), excelread(1, 3, "Account-Invalid"));
		body6.put(excelread(2, 2, "AccountCreation") , excelread(2, 3, "Account-Invalid"));
		body6.put(excelread(3, 2,"AccountCreation") , excelread(3, 3, "Account-Invalid"));
		body6.put(excelread(4, 2, "AccountCreation"), excelread(4, 3,"Account-Invalid"));
		body6.put(excelread(5, 2, "AccountCreation") , excelread(5, 3, "AccountCreation"));
		body6.put(excelread(6, 2, "AccountCreation") , excelread(6, 3, "AccountCreation"));
		body6.put(excelread(7, 2, "AccountCreation") , excelread(7, 3, "AccountCreation"));
		body6.put(excelread(8, 2, "AccountCreation"), excelread(8, 3, "AccountCreation"));

		request.header("Content-Type", "application/json");

	}

	@When("I post the body in the URI")
	public void i_post_the_body_in_the_URI()throws Throwable {

		Accountresponse7= request.body(body6).post(excelread(1, 4, "AccountCreation"));

	}

	@Then("I should get error message")
	public void i_should_get_error_message() {

		System.err.println("Account can't be created because all values existing: "+ Accountresponse7.prettyPrint());
	}



	@Given("Im in the SBN app using the baseURI scenario2")
	public void im_in_the_SBN_app_using_the_baseURI_scenario2() {

		RestAssured.baseURI = baseuri;
		request = RestAssured.given();

	}

	@When("I try to create an account with existing username")
	public void i_try_to_create_an_account_with_existing_username() throws Throwable {

		body7 = new JSONObject();
		body7.put(excelread(1, 2,"AccountCreation" ), username);
		body7.put(excelread(2, 2, "AccountCreation") , excelread(2, 3, "AccountCreation"));
		body7.put(excelread(3, 2,"AccountCreation") , excelread(3, 3, "AccountCreation"));
		body7.put(excelread(4, 2, "AccountCreation"), excelread(4, 3,"AccountCreation"));
		body7.put(excelread(5, 2, "AccountCreation") , excelread(5, 3, "AccountCreation"));
		body7.put(excelread(6, 2, "AccountCreation") , excelread(6, 3, "AccountCreation"));
		body7.put(excelread(7, 2, "AccountCreation") , excelread(7, 3, "AccountCreation"));
		body7.put(excelread(8, 2, "AccountCreation"), excelread(8, 3, "AccountCreation"));

		request.header("Content-Type", "application/json");

	}

	@And ("I post the body in the URI scenario2")
	public void i_post_the_body_in_the_URI_scenario2() throws Throwable {

		Accountresponse8= request.body(body7).post(excelread(1, 4, "AccountCreation"));
	}

	@Then("I should get error message scenario2")
	public void i_should_get_error_message_scenario2() {
		System.err.println("Account can't be created because existing username is given: "+ username );

	}

	@Given("Im in the SBN app using the baseURI scenario3")
	public void im_in_the_SBN_app_using_the_baseURI_scenario3() {
		RestAssured.baseURI = baseuri;
		request = RestAssured.given();


	}

	@When("I try to create an account with existing emailid")
	public void i_try_to_create_an_account_with_existing_emailid() throws Throwable {
		body8 = new JSONObject();
		body8.put(excelread(1, 2,"AccountCreation" ), excelread(1, 3, "AccountCreation"));
		body8.put(excelread(2, 2, "AccountCreation") , email);
		body8.put(excelread(3, 2,"AccountCreation") , excelread(3, 3, "AccountCreation"));
		body8.put(excelread(4, 2, "AccountCreation"), excelread(4, 3,"AccountCreation"));
		body8.put(excelread(5, 2, "AccountCreation") , excelread(5, 3, "AccountCreation"));
		body8.put(excelread(6, 2, "AccountCreation") , excelread(6, 3, "AccountCreation"));
		body8.put(excelread(7, 2, "AccountCreation") , excelread(7, 3, "AccountCreation"));
		body8.put(excelread(8, 2, "AccountCreation"), excelread(8, 3, "AccountCreation"));

		request.header("Content-Type", "application/json");
	}

	@And("I post the body in the URI scenario3")
	public void i_post_the_body_in_the_URI_scenario3() throws Throwable {

		Accountresponse9= request.body(body8).post(excelread(1, 4, "AccountCreation"));
	}

	@Then("I should get error message scenario3")
	public void i_should_get_error_message_scenario3() {
		System.err.println("Account can't be created because existing emaii-id is given: "+ email);

	}

	@Given("Im in the SBN app using the baseURI scenario4")
	public void im_in_the_SBN_app_using_the_baseURI_scenario4() {

		RestAssured.baseURI = baseuri;
		request = RestAssured.given();

	}

	@When("I try to create an account with existing phone number")
	public void i_try_to_create_an_account_with_existing_phone_number() throws Throwable {
		body9 = new JSONObject();
		body9.put(excelread(1, 2,"AccountCreation" ), excelread(1, 3, "AccountCreation"));
		body9.put(excelread(2, 2, "AccountCreation") , excelread(2, 3, "AccountCreation"));
		body9.put(excelread(3, 2,"AccountCreation") , excelread(3, 3, "AccountCreation"));
		body9.put(excelread(4, 2, "AccountCreation"), phone );
		body9.put(excelread(5, 2, "AccountCreation") , excelread(5, 3, "AccountCreation"));
		body9.put(excelread(6, 2, "AccountCreation") , excelread(6, 3, "AccountCreation"));
		body9.put(excelread(7, 2, "AccountCreation") , excelread(7, 3, "AccountCreation"));
		body9.put(excelread(8, 2, "AccountCreation"), excelread(8, 3, "AccountCreation"));

		request.header("Content-Type", "application/json");

	}

	@And("I post the body in the URI scenario4")
	public void i_post_the_body_in_the_URI_scenario4() throws Throwable {

		Accountresponse10= request.body(body9).post(excelread(1, 4, "AccountCreation"));
	}

	@Then("I should get error message scenario4")
	public void i_should_get_error_message_scenario4() {
		System.err.println("Account can't be created because existing phone num is given: "+ phone);

	}



	@Given("I have created an account")
	public void i_have_created_an_account1() {
		RestAssured.baseURI = baseuri;
		request = RestAssured.given();

	}

	@When("I login with username and wrong password")
	public void i_login_with_username_and_wrong_password()throws Throwable {

		body10 = new JSONObject();
		body10.put(excelread(1, 2, "LogIN"), username);
		body10.put(excelread(2, 2, "LogIN"), excelread(1, 3, "LogIN-Invalid"));
		request.header("Content-Type", "application/json");
	}

	@And("I post login details in the URI")
	public void i_post_login_details_in_the_URI1()throws Throwable {

		Accountresponse11= request.body(body10).post(excelread(1, 4, "LogIN"));


	}

	@Then("I should get error message as Invalid Credentials")
	public void i_should_get_error_message_as_Invalid_Credentials() throws Throwable {

		System.err.println("Account can't be logged-in because invalid password is given: "+ excelread(1, 3, "LogIN-Invalid"));
	}

	@Given("I have created an account scenario2")
	public void i_have_created_an_account_scenario2() {

		RestAssured.baseURI = baseuri;
		request = RestAssured.given();

	}

	@When("I login with invalid username and valid password scenario2")
	public void i_login_with_invalid_username_and_valid_password_scenario2() throws Throwable {
		body11 = new JSONObject();
		body11.put(excelread(1, 2, "LogIN"), excelread(1, 2, "LogIN-Invalid"));
		body11.put(excelread(2, 2, "LogIN"), password);
		request.header("Content-Type", "application/json");

	}

	@And("I post login details in the URI scenario2")
	public void i_post_login_details_in_the_URI_scenario2() throws Throwable {

		Accountresponse12= request.body(body11).post(excelread(1, 4, "LogIN"));

	}

	@Then("I should get error message as Invalid Credentials scenario2")
	public void i_should_get_error_message_as_Invalid_Credentials_scenario2() throws Throwable {
		System.err.println("Account can't be logged-in because invalid username and valid password is given: "+ excelread(1, 2, "LogIN-Invalid"));

	}

	@Given("I have created an account scenario3")
	public void i_have_created_an_account_scenario3() {
		RestAssured.baseURI = baseuri;
		request = RestAssured.given();


	}


	@When("I login with invalid username and invalid password scenario3")
	public void i_login_with_invalid_username_and_invalid_password_scenario3() throws Throwable {
		body12 = new JSONObject();
		body12.put(excelread(1, 2, "LogIN"), excelread(1, 2, "LogIN-Invalid"));
		body12.put(excelread(2, 2, "LogIN"), excelread(1, 3, "LogIN-Invalid"));
		request.header("Content-Type", "application/json");


	}

	@And("I post login details in the URI scenario3")
	public void i_post_login_details_in_the_URI_scenario3() throws Throwable {


		Accountresponse13= request.body(body12).post(excelread(1, 4, "LogIN"));
	}

	@Then("I should get error message as Invalid Credentials scenario3")
	public void i_should_get_error_message_as_Invalid_Credentials_scenario3() throws Throwable {

		System.err.println("Account can't be logged-in because invalid username and invalid password is given: "+ excelread(1, 2, "LogIN-Invalid") + excelread(1, 3, "LogIN-Invalid"));
	}

}