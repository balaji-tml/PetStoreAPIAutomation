package api.test;

import io.restassured.response.Response;
import api.endpoints.UserEndPoints;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.utilities.DataProviders;
import api.payload.User;

public class DataDrivenUserTests {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userID,String userName, String fName, String lName, String email, String password, String ph)
    {
        System.out.println("userID: "+userID);
        User userPayload = new User();
        //int uid = Integer.parseInt(userID); // 1001;
        //System.out.println("uid: "+uid);
        try {
            userPayload.setId(Integer.parseInt(userID));
            userPayload.setUsername(userName);
            userPayload.setFirstName(fName);
            userPayload.setLastName(lName);
            userPayload.setEmail(email);
            userPayload.setPassword(password);
            userPayload.setPhone(ph);
            Response response = UserEndPoints.createUser(userPayload);
            response.then().log().all();

            Assert.assertEquals(response.getStatusCode(), 200);
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }

}
