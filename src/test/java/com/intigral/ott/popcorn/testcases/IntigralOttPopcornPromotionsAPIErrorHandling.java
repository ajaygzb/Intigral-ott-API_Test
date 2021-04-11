package com.intigral.ott.popcorn.testcases;

import com.intigral.ott.popcorn.config.APIPath;
import com.intigral.ott.popcorn.api.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.intigral.ott.popcorn.dto.ErrorResponse;

/**
 * This class will validate the Intigral-Ott.net Popcorn Promotion API error management.
 */

public class IntigralOttPopcornPromotionsAPIErrorHandling extends BaseTest {

    private static RequestSpecification requestSpecification;
    private static Response response;
    ObjectMapper objectMapper = new ObjectMapper();
    ErrorResponse errorResponse;

    /**
     *
      * GET call with invalid APIKey
     */
    @BeforeClass(dependsOnMethods = "setup")
    public void getInvalidAPIKeyResponse () throws JsonProcessingException {
        requestSpecification = RestAssured.given();
        response = requestSpecification.request(Method.GET, APIPath.INVALID_APIKEY);
        errorResponse  = objectMapper.readValue(new JSONObject(response.getBody().asString()).toString(), ErrorResponse.class);
        
    }

    //StatusCode check
    @Test(priority = 0)
    public void popcornAPI_InvalidAPIKey_StatusCode(){
        Assert.assertEquals(response.getStatusCode(),403
                ,"Invalid API Get Request Response code Mismatch -->");
    }

    //Response Body validation.
    @Test
    public void popcornAPI_InvalidAPIKey_ResponseValidation(){
        Assert.assertNotNull(errorResponse.getError().getRequestId().toString());

        Assert.assertEquals(errorResponse.getError().getCode()
                ,"8001","Invalid APIKey response code mismatch. It should be 8001");

        Assert.assertEquals(errorResponse.getError().getMessage()
                ,"invalid api key", "Invalid APIKey response message mismatch");
    }

}
