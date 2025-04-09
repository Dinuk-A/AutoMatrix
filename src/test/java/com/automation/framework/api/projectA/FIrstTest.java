package com.automation.framework.api.projectA;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.automation.framework.api.ApiUtils;
import com.automation.framework.config.ConfigReader;
import com.automation.framework.utils.AssertionUtils;
import com.automation.framework.utils.CommonMethods;
import com.automation.framework.utils.HttpStatusCode;
import com.automation.framework.utils.JsonReaderNew;

import io.restassured.response.Response;

public class FIrstTest {

    private static final String SEMP_BASE_URI = ConfigReader.getProperty("sempsarc.base.url");

    private static final String ENDPOINT_URL = ConfigReader.getProperty("sempsarc.post.url");

    // CALL THE POST REQ AND RETRIEVE THE TOKEN

    // mvn test -Dtest=FIrstTest#retrieveTokenByPost
    @Test
    public void retrieveTokenByPost() {

        // read the json file to retrieve body
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("clientId", "MecNecILet@Met001");
        placeholders.put("password", "d2nEH8G4dj");
        placeholders.put("userId", "363538");

        String reqBody = JsonReaderNew.readDynamicJsonFiles("src/test/resources/data/SempsarcBody.json", placeholders);

        Response response = ApiUtils.postReqWithRawJsonNew(SEMP_BASE_URI, ENDPOINT_URL, reqBody);

        // verify the body
        CommonMethods.printResponseBody(response);

        // Test status code
        AssertionUtils.assertStatusCode(response, HttpStatusCode.OK.getCode());

        //POST wala 201 (created) enna one, but meke enne 200(OK). ay ?💥💥💥

        System.out.println("retrieveTokenByPost success");

    }

}
