package com.intigral.ott.popcorn.api;

import com.intigral.ott.popcorn.utils.PropertyFile;
import com.intigral.ott.popcorn.utils.ReportListener;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

/**
 * This class will initialise the Intigral-Ott.net API BASEURI and headers.
 * This class will run at the very beginning of the test.
 */
public class BaseTest {

    @BeforeClass
    public void setup(){
        RestAssured.baseURI="http://api.intigral-ott.net";
       // RestAssured.baseURI=PropertyFile.envFile().get("ServerUrl");
    }
}
