package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.ExtentReportManager;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
import io.restassured.response.Response;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ExtentReportManager.class})
@Epic("Regression Testing")
@Feature("User Tests")
public class UserTests {
    Faker facker;
    User userPayload;

    ExtentReportManager extent;

    public Logger logger;

    @BeforeClass
    public void setup()
    {
        facker = new Faker();
        userPayload = new User();

        userPayload.setId(facker.idNumber().hashCode());
        userPayload.setUsername(facker.name().username());
        userPayload.setFirstName(facker.name().firstName());
        userPayload.setLastName(facker.name().lastName());
        userPayload.setEmail(facker.internet().emailAddress());
        userPayload.setPassword(facker.internet().password());
        userPayload.setPhone(facker.phoneNumber().cellPhone());

        //Generate Logs
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Debugging...");


    }
    @Test(priority = 1, description = "Test Post User Functionality")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Test Post User Functionality using a payload")
    @Story("Post User Functionality using a payload")
    public void testPostUser()
    {
        logger.info(" ****** Creating user ******");
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
        logger.info(" ****** User Created ******");
    }

    @Test(priority = 2, description = "Test Get User Functionality")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Test Get User By username Functionality")
    @Story("Get User By username Functionality")
    public void testGetUserByName()
    {
        logger.info(" ****** Getting User By Name ******");
        Response response = UserEndPoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info(" ****** Retrieved User By Name ******");
    }

    @Test(priority = 3, description = "Test Update User by username Functionality")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Test Update User by username Functionality")
    @Story("Update User By username Functionality")
    public void testUpdateUserByName()
    {
        //Update data using payload
        logger.info(" ****** Updating User By Name ******");
        userPayload.setFirstName(facker.name().firstName());
        userPayload.setLastName(facker.name().lastName());
        userPayload.setEmail(facker.internet().emailAddress());

        Response response = UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);

        //Checking data after update
        Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
        logger.info(" ****** Updated User By Name ******");

    }

    @Test(priority = 4, description = "Test Delete User by username Functionality")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Test Delete User by username Functionality")
    @Story("Delete User By username Functionality")
    public void testDeleteUserByName()
    {
        logger.info(" ****** Deleting User By Name ******");
        Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info(" ****** Deleted User By Name ******");
    }

}
