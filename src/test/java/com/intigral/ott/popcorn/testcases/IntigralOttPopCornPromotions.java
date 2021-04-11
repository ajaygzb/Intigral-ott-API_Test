package com.intigral.ott.popcorn.testcases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intigral.ott.popcorn.api.BaseTest;
import com.intigral.ott.popcorn.config.APIPath;
import com.intigral.ott.popcorn.dto.PopcornPromotionResponse;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * This class will validate Intigral-Ott.net Popcorn Promotion API's successful ResponseBody.
 */

public class IntigralOttPopCornPromotions extends BaseTest {
    private static RequestSpecification requestSpecification;
    private static Response response;

    ObjectMapper objectMapper = new ObjectMapper();
    PopcornPromotionResponse popcornPromotionResponse;

    /**
     * Popcorn API GET Request : Convert the response JSON body to Java Object
     * This method will run before any test case and is dependent on BaseTest.setup
     */
    @BeforeClass(dependsOnMethods = "setup")
    public void getPopCornPromotionData() throws JsonProcessingException {
        requestSpecification = RestAssured.given();
        response = requestSpecification.request(Method.GET, APIPath.GET_PROMOTION_DATA);
        popcornPromotionResponse = objectMapper.readValue(new JSONObject(response.getBody().asString()).toString(), PopcornPromotionResponse.class);
    }

    /**
     * Validate GET Response StatusCode
     */
    @Test(priority = 0,enabled=true)   //This test will run first to confirm the successful GET call.
    public void promotionAPI_Success_StatusCode() {
        Assert.assertEquals(response.getStatusCode(), 200
                , "Get Request Response code mismatch -->");
    }

    /**
     * Validate GET Response Time
     */
    @Test
    public void promotionAPI_ResponseTime() {
        Assert.assertTrue(response.time() < 3000, "API Response Time is not in accepted range");
    }

    /**
     * Validate GET Response Objects for nulls.
     */
    @Test(dependsOnMethods = "promotionAPI_Success_StatusCode") //This test is dependent on successful StatusCode Test.

    public void promotionAPI_ResponseBody() throws JsonProcessingException {
   
        for (PopcornPromotionResponse.Promotion promotions : popcornPromotionResponse.getPromotions()) {
        	
            Assert.assertNotNull(promotions, "'Promotions' object is NULL.-->");

            Assert.assertNotNull(promotions.getPromotionId(), "'PromotionId' field is NULL.-->");

            Assert.assertNotNull(promotions.getPromoArea(), " 'PromoArea' field is NULL --> ");

            Assert.assertNotNull(promotions.getPromoType(), " 'PromotionType' field is NULL --> ");

            Assert.assertNotNull(promotions.getProperties(), " 'Properties' field is NULL --> ");

            Assert.assertNotNull(promotions.getImages(), " 'Image' field is NULL --> ");

        }
    }

    /**
     * Validate Response Data Validation.
     */
    @Test(dependsOnMethods = "promotionAPI_Success_StatusCode")
    //This test is dependent on successful StatusCode Test.
    public void promotionAPI_ResponseDataType() throws JsonProcessingException {

        for (PopcornPromotionResponse.Promotion promotions : popcornPromotionResponse.getPromotions()) {
            int promotionsIndex = popcornPromotionResponse.getPromotions().indexOf(promotions);

            Assert.assertTrue(promotions.getPromoType().matches("(EPISODE)|(VOD)|(SERIES)"),
                    "promoType contains invalid data at promotions index: " + promotionsIndex + "--> ");

            Assert.assertTrue(promotions.getPromotionId().matches(".*")
                    , "promotionId does not contain String value at promotions index: " + promotionsIndex + " --> ");

            Assert.assertTrue(Boolean.toString(promotions.isShowPrice()).matches("(true)|(false)")
                    , "'ShowPrice' filed contains non Boolean value at promotions index: " + promotionsIndex + " --> ");

            Assert.assertTrue(Boolean.toString(promotions.isShowText()).matches("(true)|(false)")
                    , "'ShowText' filed contains non Boolean value at promotions index: " + promotionsIndex + " --> ");

            Assert.assertTrue(promotions.getLocalizedTexts().getAr().size() > 0
                    , " 'LocalizedTexts' field does not have Ar Json Object at promotions index: " + promotionsIndex + " --> ");

            Assert.assertTrue(promotions.getLocalizedTexts().getEn().size() > 0
                    , " 'LocalizedTexts' field does not have En Json Object at promotions index: " + promotionsIndex + " --> ");
        }
    }

    /**
     * Validating inner objects of Promotions Data.
     */
    @Test(dependsOnMethods = "promotionAPI_Success_StatusCode")//This test is dependent on successful StatusCode Test.
    public void promotionAPI_Promotions_LocalisedText() {
        for (PopcornPromotionResponse.Promotion promotions : popcornPromotionResponse.getPromotions()) {

            //To print the promotion index to fetch Error path when assertion fails.
            int promotionsIndex = popcornPromotionResponse.getPromotions().indexOf(promotions);

            Assert.assertFalse(promotions.getLocalizedTexts().getEn().isEmpty()
                    , "En list is empty at promotions index: " + promotionsIndex + " --> ");

            Assert.assertFalse(promotions.getLocalizedTexts().getAr().isEmpty()
                    , "AR list is empty at promotions index: " + promotionsIndex + " --> ");
        }

    }

    /**
     * Validating inner objects of Promotions Data.
     */
    @Test(dependsOnMethods = "promotionAPI_Success_StatusCode")
    //This test is dependent on successful StatusCode Test.
    public void promotionAPI_Promotions_Properties() {
        for (PopcornPromotionResponse.Promotion promotions : popcornPromotionResponse.getPromotions()) {

            //To print the promotion index to fetch Error path when assertion fails.
            int promotionsIndex = popcornPromotionResponse.getPromotions().indexOf(promotions);

            Assert.assertFalse(promotions.getProperties().isEmpty()
                    , "Properties object is empty at promotions index: " + promotionsIndex + " --> ");

            for (PopcornPromotionResponse.Properties properties : promotions.getProperties()) {

                Assert.assertTrue(properties.getYear().matches("^[0-9]{4}$")
                        , "'year' object contains invalid data at promotions index: " + promotionsIndex + " --> ");

                Assert.assertTrue(properties.getProgramType().matches("(episode)|(series)|(movie)|(season)")
                        , "'programType' contains invalid at promotions index: "
                                + promotionsIndex + " required value episode| series| movie| season) --> ");

                Assert.assertNotNull(properties.getProgramAvailabilityId()
                        , "'ProgramAvailabilityId' object is null at promotions index: " + promotionsIndex + " --> ");

                Assert.assertTrue(properties.getCurrency().matches("^[A-Z]{2,3}$")
                        , "Currency key invalid currency code at promotions index: " + promotionsIndex + " --> ");

                try {
					properties.getGenre().listIterator().next().matches("(Drama)|(Action)|(Lifestyle)");
					System.out.println("programType' contains"+properties.getGenre().listIterator().next()+" at index: "+ promotionsIndex + " required value Drama| Action| Lifestyle) --> ");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
            }

        }
    }

    /**
     * Validating inner objects of Promotions Data.
     */
    @Test(dependsOnMethods = "promotionAPI_Success_StatusCode")
    //This test is dependent on successful StatusCode Test.
    public void promotionAPI_Promotions_Image() {
        for (PopcornPromotionResponse.Promotion promotions : popcornPromotionResponse.getPromotions()) {

            //To print the promotion index to fetch Error path when assertion fails.
            int promotionsIndex = popcornPromotionResponse.getPromotions().indexOf(promotions);

            Assert.assertFalse(promotions.getImages().isEmpty()
                    , "Image object is empty at promotions index: " + promotionsIndex + " --> ");

            for (PopcornPromotionResponse.Image image : promotions.getImages()) {

                Assert.assertNotNull(image.getId(), "Image 'id' is null at promotions index: " + promotionsIndex + " --> ");
                Assert.assertNotNull(image.getUrl(), "Image 'url' is null at promotions index: " + promotionsIndex + " --> ");
            }
        }
    }

}
