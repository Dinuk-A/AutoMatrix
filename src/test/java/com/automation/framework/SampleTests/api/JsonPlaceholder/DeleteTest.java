package com.automation.framework.SampleTests.api.JsonPlaceholder;

import org.testng.annotations.Test;

import com.automation.framework.utils.api.ApiUtils;
import com.automation.framework.utils.api.AssertionUtils;
import com.automation.framework.utils.api.HttpStatusCode;
import com.automation.framework.utils.common.ConfigPropertyReader;

import io.restassured.response.Response;

//✅
//https://jsonplaceholder.typicode.com/posts/1
public class DeleteTest {

    private static final String BASE_URL = ConfigPropertyReader.getProperty("jp.base.url");

    private static final String ENDPOINT_URL = ConfigPropertyReader.getProperty("jp.put.delete.url");

    @Test
    public void basicDeleteTest() {
        Response response = ApiUtils.deleteRequest(BASE_URL, ENDPOINT_URL);

        AssertionUtils.assertStatusCode(response, HttpStatusCode.OK.getCode());

        System.out.println("basicDeleteTest Test ran successfully.");
    }

}
