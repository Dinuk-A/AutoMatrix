package com.automation.framework.JsonPlaceholder;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.automation.framework.utils.api.ApiUtils;
import com.automation.framework.utils.api.AssertionUtils;
import com.automation.framework.utils.api.CommonMethods;
import com.automation.framework.utils.api.HttpStatusCode;
import com.automation.framework.utils.common.ConfigReader;

import io.restassured.response.Response;

public class GetByParamsTest {

    private static final String BASE_URL = ConfigReader.getProperty("jp.base.url");

    private static final String QUERY_PARAM_URL = ConfigReader.getProperty("jp.queryparam.url");

    // ✅
    @Test
    public void getBySinglePathParam() {

        String SINGLE_PATH_URL = ConfigReader.getProperty("jp.single.pathparam.url");

        Response response = ApiUtils.getReqWithSinglePathParam(BASE_URL, SINGLE_PATH_URL, "id", "5");

        CommonMethods.printResponseBody(response);

        AssertionUtils.assertStatusCode(response, HttpStatusCode.OK.getCode());

        System.out.println(" getBySinglePathParam Test ran successfully.");

    }

    // ✅
    @Test
    public void getBySingleQueryParam() {

        Map<String, String> queryParamsMap = new HashMap<>();
        queryParamsMap.put("postId", "3");

        Response response = ApiUtils.getReqWithQueryParams(BASE_URL, QUERY_PARAM_URL, queryParamsMap);

        CommonMethods.printResponseBody(response);

        AssertionUtils.assertStatusCode(response, HttpStatusCode.OK.getCode());

        System.out.println(" getBySingleQueryParam Test ran successfully.");

    }

    @Test
    public void getByMultiQueryParams() {

        Map<String, String> multiQueryParams = new HashMap<>();
        multiQueryParams.put("postId", "5");
        multiQueryParams.put("id", "23");

        Response response = ApiUtils.getReqWithQueryParams(BASE_URL, QUERY_PARAM_URL, multiQueryParams);

        CommonMethods.printResponseBody(response);

        AssertionUtils.assertStatusCode(response, HttpStatusCode.OK.getCode());

        System.out.println(" getByMultiQueryParams Test ran successfully.");

    }

}
