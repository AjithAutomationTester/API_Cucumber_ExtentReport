Feature: I am creating an account in SBN application 
and using the username and password I perform login to generate code 
then using this code I generate token and UserID
then using this token and userID I view the profile
then I add a post in the account and I view the added post
@AccountCreation
Scenario: I am creating an account in SBN application 
Given Im in SBN app using the baseURI 
When I am creating a new account 
And Im posting the body in the URI 
Then account is created and I am printing the details of the account
And I am getting the username and password

@Login
Scenario: I am logging-In using the username and password
Given I have created an account and load the base URI
When I login with username and password
And I post login details in the URI /oauth/code
Then I should able to login
And I print the code

@TokenGenerate
Scenario: Im generating token for the corresponding account
Given I have created userID and code and load the base URI
When I load the code and post in the URI /oauth/code
Then I should able to generate token
And I am printing the token

@ViewProfile
Scenario: I am viewing the profile which I created 
Given I have the token and userID to view the profile and load the base URI 
When I post the token and userid in the URI
Then I should able to view the profile 

@NewPost
Scenario: I am creating a new post in the account
Given I have a new account and load the base URI
When I create a body and post in the URI
Then I am printing the response
And I get the post-ID from the body

@ViewPost
Scenario: I am viewing the post which I added
Given I have added a new post to the account
When I post the Post-ID and user-ID in the URI
Then I should get the corresponding post information

@LikeandUnlike
Scenario: I am liking the post 
Given I have a new post 
When I like the post
Then I should get status code as 200

@ViewTheLikedPost
Scenario: I am getting the post I gave like
Given I have liked a post
When I give the token and postID
Then I can get the post which is liked

@PostComment
Scenario: I am posting a comment in the post
Given I have a post to comment
When I comment in the post
Then I get status code as 200

@GetComment
Scenario: I getting the post with comments
Given I have a post with the comment 
When I give the token 
Then I should get the comment from the post

@AccountCreationNegativeScenario
Scenario: I am creating an account in SBN application with negative scenario
Given Im in the SBN app using the baseURI 
When I try to create an account with existing details
And I post the body in the URI 
Then I should get error message 

@AccountCreationNegativeScenario2
Scenario: I am creating an account in SBN application with negative scenario2
Given Im in the SBN app using the baseURI scenario2
When I try to create an account with existing username
And I post the body in the URI scenario2
Then I should get error message scenario2

@AccountCreationNegativeScenario3
Scenario: I am creating an account in SBN application with negative scenario3
Given Im in the SBN app using the baseURI scenario3
When I try to create an account with existing emailid
And I post the body in the URI scenario3
Then I should get error message scenario3

@AccountCreationNegativeScenario4
Scenario: I am creating an account in SBN application with negative scenario4
Given Im in the SBN app using the baseURI scenario4
When I try to create an account with existing phone number
And I post the body in the URI scenario4
Then I should get error message scenario4

@LoginNegative
Scenario: I am logging-In using the username and wrong password
Given I have created an account 
When I login with username and wrong password
And I post login details in the URI 
Then I should get error message as Invalid Credentials

@LoginNegative2
Scenario: I am logging-In using the invalid username and valid password scenario2
Given I have created an account scenario2 
When I login with invalid username and valid password scenario2
And I post login details in the URI scenario2
Then I should get error message as Invalid Credentials scenario2


@LoginNegative3
Scenario: I am logging-In using the invalid username and invalid password scenario3
Given I have created an account scenario3
When I login with invalid username and invalid password scenario3
And I post login details in the URI scenario3
Then I should get error message as Invalid Credentials scenario3
