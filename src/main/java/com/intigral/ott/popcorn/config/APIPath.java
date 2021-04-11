package com.intigral.ott.popcorn.config;

/**
 * This class will hold the different Popcorn API path's for testing.
 */
public class APIPath {

    //GET
    public static final String GET_PROMOTION_DATA = "/popcorn-api-rs-7.9.10/v1/promotions?apikey=GDMSTGExy0sVDlZMzNDdUyZ";

    //GET : Invalid APIKey
    public static final String INVALID_APIKEY = "/popcorn-api-rs-7.9.10/v1/promotions?apikey=INVALID";

    //GET: Missing APIKey
    public static final String NO_APIKEY = "/popcorn-api-rs-7.9.10/v1/promotions";
}
