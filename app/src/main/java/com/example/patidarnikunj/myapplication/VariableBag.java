package com.example.patidarnikunj.myapplication;

import java.util.ArrayList;

/**
 * Created by Harsh on 12/28/2015.
 */
public class VariableBag {

 public static String pass = "pass";
    public static String miniBIO = "miniBio";
    public static final String CLOSE = "close";
    public static final String SEARCH = "search";
    public static final String MATCH = "match";
    public static final String CHAT = "chat";
    public static final String PROFILE = "profile";
    public static final String DISCOVERYSETTING = "discovery_setting";
    public static final String APPSETTING = "app_setting";
    public static final String SHARERATEAPP = "share_rate_app";
    public static final String LOGOUT = "logout";
    // public static String ID = "idKey";
    public static String TOKEN = "token";
    public static String EMAIL = "email";
    public static String NAME = "name";
    public static String AVTAR = "avtar";
    public static String LIKES = "likes";
    public static String ORDERSTATUS = "order_status";
    public static String CUSTOMERID = "customerId";
    public static String ADDRESSID = "addressId";
    public static String SELLERID = "sellerId";
    public static String PHONE = "phone";
    public static String DEVICETIME = "device_type";
    public static String DEVICETOKEN = "device_token";
    public static String ORDERID = "orderId";
    public static String DELIVERYSTATUS = "deliveryStatus";
    public static String INVOICENO = "invoice_no";
   // public static String FIREBASEAUTHTOKEN = "firebaseAuthToken";
    public static String ACCESSTOKEN = "access_token";
    public static boolean ISKGCHECKED;
    public static String REASON = "reason";
    public static String PICKTIME = "pick_time";
    public static String DELIVERYTIME = "dilivery_time";
    public static String MESSAGE = "message";
    public static String AMOUNT = "amount";
    public static String STATUS = "status";
    public static String CREATED = "created";
    public static String MODIFIED = "modified";
    public static String CUSTOMERNAME = "customer_name";
    public static String CUSTOMERPHONE = "customer_phone";
    public static String CUSTOMERALTERNATENUMBER = "customer_alt_phone";
    public static String CUSTOMERADDRESSTYPE = "customer_address_type";

    // ----------------------- Sliding Menu items --------------------
    public static String CUSTOMERADDRESS = "customer_address";
    public static String CUSTOMERLANDMARK = "customer_landmark";
    public static String CUSTOMERLAT = "customer_lat";
    public static String CUSTOMERLONG = "customer_long";
    public static String WEIGHT = "weight";
    public static String SELLERCODE = "seller_code";
    public static String DISTANCE = "distance";
    public static String SELLERNAME = "seller_name";
    public static String PICKAMOUNT = "pick_amount";

    // ------------------- Shared prefrence keys ----------------
    public static String SHAREDPREFRENCE = "sharedPrefrenceKey";
    public static String ISLOGIN = "isLoginKey";
    public static String IMEI = "imeiKey";
    public static String TIMEZONE = "timeZoneKey";
    public static String OSNAME = "osNameKey";
    public static String OSVERSION = "osVersionKey";
    public static String DEVICENAME = "deviceNameKey";
    public static String APPVERSION = "appVersionKey";
    public static String NEWMATCHSETTING = "newMatchKey";
    public static String LOADIMAGESSETTINGS = "loadImageKey";
    public static String MINAGESETTING = "minAgeKey";
    public static String MAXAGESETTING = "maxAgeKey";
    public static String DISTANCESETTING = "distanceKey";
    public static String ISDISCOVERABLESETTING = "isDiscoverableKey";
    public static String MENSETTING = "menKey";
    public static String WOMENSETTING = "womenKey";
    public static String USERID = "userIdKey";
    public static String USERFIRSTNAME = "userFirstNameKey";
    public static String USERLASTNAME = "userLastNameKey";
    public static String USERGENDER = "userGenderKey";
    public static String USEREMAIL = "userEmailKey";
    public static String USERCONTACT = "userContactKey";
    public static String USERCITY = "userCityKey";
    public static String USERSTATE = "userStateKey";
    public static String USERCOUNTRY = "userCountryKey";
    public static String USERBOD = "userBODKey";
    public static String USERABOUT = "userAboutKey";
    public static String GCMID = "GCMIdKey";
    public static String USERPROFILEPIC = "userProfilePicKey";
    public static String USERPROFILEPICThumb = "userProfilePicThumbKey";
    public static String ISNEWUSER = "isNewUserKey";
    public static String OLDUSERID = "oldUserIdKey";
    public static String ISNEWDISCOVERYSETTING = "isNewDiscoverySettingKey";
    public static String ISNEWAPPSETTING = "isNewAppSettingKey";
    public static String USERLOGINTYPE = "userLoginTypeKey";
    public static String SEARCHRESULT = "searchResultKey";
    public static String LATITUDE = "latitudeKey";
    public static String LONGITUDE = "longitudeKey";
    public static String KINDLINGID = "kindlingID";
    public static String ISFIRSTTIME = "isFirstID";

    // ---------------- Extra flags -------------------------
    public static String GCMFLAGFORCURRENTVIEW = "";
    public static boolean ISAPPOPENWITHINFRAGMENT = false;
    public static boolean isHomeActivity = false;

    // ------------------ Login user Images Array ----------------------
    public static ArrayList<String> userImages = new ArrayList<>();

    public static String alreadyused = "alreadyused";


    //PIPCars
    public static String ID = "myID";
    public static String fb_id = "fb_id";
    public static String first_name = "first_name";
    public static String last_name = "last_name";
    public static String email = "email";
    public static String phone = "phone";
    public static String dob = "dob";
    public static String gender = "gender";
    public static String image = "image";
    public static String status = "status";
    public static String is_active = "is_active";
    public static String created_at = "created_at";
    public static String updated_at = "updated_at";
    public static String isFbLogin = "fbLogin";
    public static String imageProfile = "imageProfile";




    //PIPCars
    public static String IDFB = "myID";
    public static String first_nameFB = "first_name";
    public static String last_nameFB = "last_name";
    public static String emailFB = "email";
    public static String phoneFB = "phone";
    public static String dobFB = "dob";
    public static String genderFB = "gender";
    public static String imageFB = "image";
    public static String locationFB = "image";
    public static String statusFB = "status";


    public static final int CARD_NUMBER_TOTAL_SYMBOLS = 27; // size of pattern 0000-0000-0000-0000
    public static final int CARD_NUMBER_TOTAL_DIGITS = 22; // max numbers of digits in pattern: 0000 x 4
    public static final int CARD_NUMBER_DIVIDER_MODULO = 5; // means divider position is every 5th symbol beginning with 1
    public static final int CARD_NUMBER_DIVIDER_POSITION = CARD_NUMBER_DIVIDER_MODULO - 1; // means divider position is every 4th symbol beginning with 0
   // public static final char CARD_NUMBER_DIVIDER = '-';
 public static final char CARD_NUMBER_DIVIDER = ' ';

    public static final int CARD_DATE_TOTAL_SYMBOLS = 5; // size of pattern MM/YY
    public static final int CARD_DATE_TOTAL_DIGITS = 4; // max numbers of digits in pattern: MM + YY
    public static final int CARD_DATE_DIVIDER_MODULO = 3; // means divider position is every 3rd symbol beginning with 1
    public static final int CARD_DATE_DIVIDER_POSITION = CARD_DATE_DIVIDER_MODULO - 1; // means divider position is every 2nd symbol beginning with 0
    public static final char CARD_DATE_DIVIDER = '/';

    public static final int CARD_CVC_TOTAL_SYMBOLS = 3;
}
