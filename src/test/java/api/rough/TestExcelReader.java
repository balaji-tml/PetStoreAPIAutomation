package api.rough;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.DataFormat;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExcelReader {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userID,String userName, String fName, String lName, String email, String password, String ph)
    {

        System.out.println("Before conversion userID: "+userID);
        User userPayload = new User();
        try {
             int uid = Integer.parseInt(userID); // 1001;
             System.out.println("After conversion uid: "+uid);
//            System.out.println("userID: "+userID);

            userPayload.setId(Integer.parseInt(userID));
//            userPayload.setId(userID);
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
