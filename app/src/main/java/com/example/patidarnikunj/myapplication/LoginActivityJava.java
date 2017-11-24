/*
package com.example.patidarnikunj.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patidarnikunj.myapplication.permission.PermissionInterface;
import com.example.patidarnikunj.myapplication.permission.PermissionResponse;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.Sharer;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

*/
/**
 * Created by PatidarNikunj on 12-05-2017.
 *//*


public class LoginActivity extends AppCompatActivity implements View.OnClickListener, PermissionInterface {

    protected static final int REQUEST_CHECK_SETTINGS_FB = 0x2;
    protected static final int REQUEST_CHECK_SETTINGS_CUSTOM = 0x3;
    private final String PENDING_ACTION_BUNDLE_KEY = "com.example.hellofacebook:PendingAction";
    String accessToken;
    Unbinder unbinder;
    */
/* @BindView(R.id.layout)
     RelativeLayout layout;
    *//*
 Toolbar loginToolbar;
    //  DrawerLayout drawerLayout;
    Context mContext;
    //@BindView(R.id.imgBackBlue)
    ImageView imgBackBlue;
    //@BindView(R.id.txtSkip)
    TextView txtSkip;
    @BindView(R.id.txtLoginAccount)
    TextView txtLoginAccount;
    @BindView(R.id.txtEmailAddressText)
    TextView txtEmailAddressText;
    @BindView(R.id.edEmail)
    EditText edEmail;
    @BindView(R.id.relativeEmailAddress)
    RelativeLayout relativeEmailAddress;
    @BindView(R.id.txtPasswordText)
    TextView txtPasswordText;
    @BindView(R.id.edPassword)
    EditText edPassword;
    @BindView(R.id.relativePassword)
    RelativeLayout relativePassword;
    @BindView(R.id.txtForgotPassword)
    TextView txtForgotPassword;
    @BindView(R.id.txtLogin)
    TextView txtLogin;
    @BindView(R.id.login_button)
    LoginButton loginButton;
    @BindView(R.id.imgFacebook)
    ImageView imgFacebook;
    @BindView(R.id.txtLoginWithFacebook)
    TextView txtLoginWithFacebook;
    @BindView(R.id.relativeLoginFacebook)
    RelativeLayout relativeLoginFacebook;
    @BindView(R.id.txtDontHaveLogin)
    TextView txtDontHaveLogin;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    View includeLayout;
    private String TAG = "LoginActivity";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ProgressDialog progressDialog;
    private boolean isFbLogin = false;
    private GoogleApiClient googleApiClient;
    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;
    private LoginActivity loginActivity;
    private ShareDialog shareDialog;
    private PendingAction pendingAction = PendingAction.NONE;
    private ConnectionDetector cd;
    private boolean isUserExist = false;
    private URL profile_pic;
    private AlertDialogManager alertDialogManager;
    private FacebookCallback<Sharer.Result> shareCallback = new FacebookCallback<Sharer.Result>() {
        @Override
        public void onCancel() {
            Log.d("HelloFacebook", "Canceled");
        }

        @Override
        public void onError(FacebookException error) {
            LoginManager.getInstance().logOut();
            Log.d("HelloFacebook", String.format("Error: %s", error.toString()));
            String title = getString(R.string.error);
            String alertMessage = error.getMessage();
            showResult(title, alertMessage);
        }

        @Override
        public void onSuccess(Sharer.Result result) {
            LoginManager.getInstance().logOut();
            Log.d("HelloFacebook", "Success!");
            if (result.getPostId() != null) {
                String title = getString(R.string.success);
                String id = result.getPostId();
                String alertMessage = getString(R.string.successfully_posted_post, id);
                showResult(title, alertMessage);
            }
        }

        private void showResult(String title, String alertMessage) {
            new AlertDialog.Builder(LoginActivity.this)
                    .setTitle(title)
                    .setMessage(alertMessage)
                    .setPositiveButton(R.string.ok, null)
                    .show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(this.getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

//        parentToolbar.setNavigationIcon(R.drawable.back);

        shareDialog = new ShareDialog(this);
        shareDialog.registerCallback(callbackManager, shareCallback);

      */
/*  if (savedInstanceState != null) {
            String name = savedInstanceState.getString(PENDING_ACTION_BUNDLE_KEY);
            pendingAction = PendingAction.valueOf(name);
        }
*//*

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_login);

        CommonUtil.hideSoftKeyboard(this);

        loginActivity = this;

        AppHelper.chageStatusBar(0,this);

        sharedPreferences = getSharedPreferences(VariableBag.SHAREDPREFRENCE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        cd = new ConnectionDetector(LoginActivity.this);
        alertDialogManager = new AlertDialogManager();

        mContext = this;

        includeLayout = (View) findViewById(R.id.login_toolbar);
        imgBackBlue = (ImageView) includeLayout.findViewById(R.id.imgBackBlue);
        txtSkip = (TextView) includeLayout.findViewById(R.id.txtSkip);

        txtSkip.setVisibility(View.GONE);

        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(final LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("Main", response.toString());

                                */
/*String accessToken = loginResult.getAccessToken().getToken();
                                Log.i("accessToken", accessToken);
                                Log.i("loginResult", object.toString());
                               *//*

                                accessToken = loginResult.getAccessToken().getToken();
                                Log.i("accessToken", accessToken);
                                editor.putString(VariableBag.TOKEN, "Bearer " + accessToken);

                                getFacebookData(object);
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(LoginActivity.this, getResources().getString(R.string.error_to_login_facebook), Toast.LENGTH_SHORT).show();
            }
        });

        txtLogin.setOnClickListener(this);
        txtDontHaveLogin.setOnClickListener(this);
        txtForgotPassword.setOnClickListener(this);

        imgBackBlue.setOnClickListener(this);
        txtSkip.setOnClickListener(this);
        // setHasOptionsMenu(true);

        unbinder = ButterKnife.bind(this);
    }


    public void doLogin() {

        RequestParams params = new RequestParams();

        params.put("email", "" + edEmail.getText().toString());
        params.put("password", "" + edPassword.getText().toString());

        HashMap<String, String> client = new HashMap<String, String>();

        client.put("device-os", "a");
        client.put("os-v", sharedPreferences.getString(VariableBag.OSVERSION, ""));
        client.put("app-v", sharedPreferences.getString(VariableBag.APPVERSION, ""));
        client.put("device-token", "123456");
        client.put("device-name", sharedPreferences.getString(VariableBag.DEVICENAME, ""));
        client.put("language", "en");


        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Please wait");
                progressDialog.setCancelable(false);
                progressDialog.show();
                super.onStart();
            }

            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Log.i(TAG, "Response" + s);
                progressDialog.dismiss();
                try {
                    JSONObject root = new JSONObject("" + s);
                    Log.i(TAG, "S" + root);
                    JSONObject data = root.getJSONObject("data");

                    String token = data.getString("token");

                    Log.i(TAG, "token do Login" + token);

                    editor.putString(VariableBag.TOKEN, "Bearer " + token);
                    editor.putBoolean(VariableBag.ISLOGIN, true);
                    editor.putBoolean(VariableBag.isFbLogin, false);


                    editor.putString(VariableBag.pass, edPassword.getText().toString());
                    editor.putString(VariableBag.ID, data.getString("id"));
                    editor.putString(VariableBag.email, data.getString("email"));
                    editor.putString(VariableBag.image, data.getString("image"));
                    editor.putString(VariableBag.first_name, data.getString("first_name"));
                    editor.putString(VariableBag.last_name, data.getString("last_name"));
                    editor.commit();

                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable throwable, String s) {
                super.onFailure(throwable, s);
                Log.i(TAG, "throwable" + throwable);
                Log.i(TAG, "s" + s);
                //{"message":"invalid_credentials"}
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    ToastUtil.show(LoginActivity.this, "" + jsonObject.getString("message"),coordinatorLayout);
                    progressDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (progressDialog != null && progressDialog.isShowing()) {
                }
            }
        };
        HttpRestClientForLogin.post("login", params, responseHandler, client);
    }

    public void setUpGCM() {
        final String regId = "" + FirebaseInstanceId.getInstance().getToken();
        Log.i(TAG, "RegID:" + regId);
        //editor.putString(VariableBag.FIREBASEAUTHTOKEN, "" + regId);
        //editor.commit();
        // Log.i(TAG, "Firebase Instance Id:" + sharedPreferences.getString(VariableBag.FIREBASEAUTHTOKEN, ""));

        doLogin();
    }

    */
/*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 64206) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
            // onLoginSuccessFull();
        }
        *//*
*/
/*if (requestCode == REQUEST_CODE_RESOLVE_ERR) {
            sConnectionResult = null;
            sPlusClient.connect();
        }*//*
*/
/*

    }*//*



    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public boolean validateEmail(String email) {

        final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher;

        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String password) {
        return password.length() > 0;
    }

    private boolean checkValidation() {
        hideKeyboard();
        String email = edEmail.getText().toString();
        String password = edPassword.getText().toString();
        if (!validateEmail(email)) {
            ToastUtil.show(LoginActivity.this, getResources().getString(R.string.not_valid_email_address),coordinatorLayout);
            return false;
        } else if (!validatePassword(password)) {
            ToastUtil.show(LoginActivity.this, getResources().getString(R.string.not_a_valid_password),coordinatorLayout);
            return false;
        } else {
            return true;
        }
    }

    private boolean validation() {
        if (edEmail.getText().toString().length() == 0) {
            ToastUtil.show(getApplicationContext(), getResources().getString(R.string.please_enter_email),coordinatorLayout);
            return false;
        } else if (edPassword.getText().toString().length() == 0) {
            ToastUtil.show(getApplicationContext(), getResources().getString(R.string.please_enter_password),coordinatorLayout);;
            return false;
        } else {
            return true;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtLogin:
                isFbLogin = false;
                editor.putBoolean(VariableBag.isFbLogin, false);
                editor.commit();
                alreadyUsed();
                if (cd.isConnectingToInternet()) {
                    googleApiClient = null;
                    if (validation()) {
                        if (validateEmail(edEmail.getText().toString())) {
                            setUpGCM();
                        }
                    }
                    //checkGpsCustom();
                } else {
                    Toast.makeText(LoginActivity.this, "" + getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.txtDontHaveLogin:
                Intent i = new Intent(LoginActivity.this, CreateNewAccount.class);
                startActivity(i);
                //finish();
                break;
            case R.id.relativeLoginFacebook:
                isFbLogin = true;
                editor.putBoolean(VariableBag.isFbLogin, true);
                editor.commit();
                alreadyUsed();
                if (cd.isConnectingToInternet()) {
                    googleApiClient = null;
                    faceBookLogin();
                } else {
                    Toast.makeText(LoginActivity.this, "" + getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.txtForgotPassword:
                i = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(i);
                //finish();
                break;
            case R.id.imgBackBlue:
                finish();
                break;
            case R.id.txtSkip:
                finish();
                break;
        }
    }

             */
/* @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            menu.clear();
            super.onCreateOptionsMenu(menu, inflater);
        }
*//*


    private void faceBookLogin() {

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i("loginResult", "" + loginResult);
                //handlePendingAction();
                //updateUI();
                String accessToken = loginResult.getAccessToken().getToken();
                Log.i("accessToken", accessToken);
                editor.putString(VariableBag.TOKEN, "Bearer " + accessToken);
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.i("LoginActivity", response.toString());
                        // Get facebook data from login
                        getFacebookData(object);
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.i("onCancel", "Cancel");
                LoginManager.getInstance().logOut();
                if (pendingAction != PendingAction.NONE) {
                    showAlert();
                    pendingAction = PendingAction.NONE;
                }
            }

            @Override
            public void onError(FacebookException error) {
                LoginManager.getInstance().logOut();
                if (pendingAction != PendingAction.NONE && error instanceof FacebookAuthorizationException) {
                    showAlert();
                    pendingAction = PendingAction.NONE;
                }
                //updateUI();
            }

            private void showAlert() {
                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle(R.string.cancelled)
                        .setMessage(R.string.permission_not_granted)
                        .setPositiveButton(R.string.ok, null)
                        .show();
            }
        });

    }

    private void getFacebookData(JSONObject object) {

        try {
            Bundle bundle = new Bundle();
            String id = "";
            String first_name = "";
            String last_name = "";
            String email = "";
            String gender = "";
            String profilePic = "";
            String phone = "";
            String dob = "";
            String mobile_phone = "";

            if (object.has("id")) {
                id = object.getString("id");
                editor.putString(VariableBag.fb_id, id);
            }

            if (object.has("first_name")) {
                first_name = object.getString("first_name");
                editor.putString(VariableBag.first_nameFB, first_name);
            }

            if (object.has("last_name")) {
                last_name = object.getString("last_name");
                editor.putString(VariableBag.last_nameFB, last_name);
            }

            if (object.has("email")) {
                email = object.getString("email");
                editor.putString(VariableBag.emailFB, email);
            }

            if (object.has("gender")) {
                gender = object.getString("gender");
                editor.putString(VariableBag.genderFB, gender);
            }
            if (object.has("birthday")) {
                dob = object.getString("birthday");
                editor.putString(VariableBag.dobFB, dob);

            }
            if (object.has("mobile_phone")) {
                dob = object.getString("mobile_phone");
            }
            try {
                profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
                profilePic = profile_pic + "";
                editor.putString(VariableBag.imageFB, profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            if (object.has("location")) {
                editor.putString(VariableBag.locationFB, object.getJSONObject("location").getString("name"));
                Log.i("location", "" + object.getJSONObject("location").getString("name"));
            }
            editor.commit();
            Log.i(TAG, "id" + id);
            Log.i(TAG, "first_name" + first_name);
            Log.i(TAG, "last_name" + last_name);
            Log.i(TAG, "email" + email);
            Log.i(TAG, "gender" + gender);
            Log.i(TAG, "dob" + dob);
            Log.i(TAG, "mobile_phone" + mobile_phone);

            isFbUserExist(id, email, first_name, last_name, gender, profilePic, phone, dob);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean isFbUserExist(final String facebookId, final String email, final String firstName, final String lastName, final String uGender, final String uProfilePic, final String phone, final String dob) {
        LoginManager.getInstance().logOut();
        Log.i("isFbUserExist", "isFbUserExist....");

        RequestParams params = new RequestParams();
        params.put("email", email);
        params.put("fb_id", facebookId);
        params.put("first_name", firstName);
        params.put("last_name", lastName);
        params.put("phone", phone);
        params.put("dob", dob);
        params.put("gender", uGender);
        params.put("image", uProfilePic);

        HashMap<String, String> client = new HashMap<String, String>();

        client.put("device-os", "a");
        client.put("os-v", sharedPreferences.getString(VariableBag.OSVERSION, ""));
        client.put("app-v", sharedPreferences.getString(VariableBag.APPVERSION, ""));
        client.put("device-token", "123456");
        client.put("device-name", sharedPreferences.getString(VariableBag.DEVICENAME, ""));
        client.put("language", "en");

        AsyncHttpResponseHandler respo = new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                Log.i("content Login", "" + content);
                try {
                    JSONObject root = new JSONObject("" + content);

                    if (root.has("data")) {
                        JSONObject data = root.getJSONObject("data");
                        String token = data.getString("token");
                        isUserExist = true;
                        Log.i("isUserExist", "" + isUserExist);
                        editor.putString(VariableBag.TOKEN, "Bearer " + token);

                        editor.putBoolean(VariableBag.ISLOGIN, true);


                        editor.putString(VariableBag.ID, data.getString("id"));
                        editor.putString(VariableBag.email, data.getString("email"));
                        editor.putString(VariableBag.image, data.getString("image"));
                        editor.putString(VariableBag.first_name, data.getString("first_name"));
                        editor.putString(VariableBag.last_name, data.getString("last_name"));
                        editor.commit();

                        isUserExist = true;
                        Log.i("isUserExist", "" + isUserExist);

                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        finish();

                    } else {
                        isUserExist = false;
                        startActivity(new Intent(LoginActivity.this, CreateNewAccount.class).putExtra("fbid", "" + facebookId).putExtra("firstname", "" + firstName).putExtra("lastname", "" + lastName).putExtra("email", "" + email).putExtra("contact", "").putExtra("birthday", "").putExtra("gender", "" + uGender).putExtra("about", "").putExtra("profile_pic", "" + uProfilePic));
                        //finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable error) {
                super.onFailure(error);
                Toast.makeText(LoginActivity.this, getResources().getString(R.string.something_wrong_happened), Toast.LENGTH_SHORT).show();
//                isUserExist = false;
//                startActivity(new Intent(LoginRegisterActivity.this, RegistrationActivity.class).putExtra("fbid", "" + facebookId).putExtra("firstname", "" + firstName).putExtra("lastname", "" + lastName).putExtra("email", "" + email).putExtra("contact", "").putExtra("birthday", "").putExtra("gender", "" + uGender).putExtra("about", "").putExtra("profile_pic", "" + uProfilePic));
//                finish();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                // pDialog.dismiss();
            }
        };
        HttpRestClient.post("fb_register", params, respo, client);
        return isUserExist;
    }

    public void getFbLogin() {
        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("email", "public_profile"));
    }

    private void alreadyUsed() {
        editor.putString(VariableBag.alreadyused, VariableBag.alreadyused);
        editor.commit();
    }

    public void onClickFb(View v) {
        if (v == relativeLoginFacebook) {
            loginButton.performClick();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(PENDING_ACTION_BUNDLE_KEY, pendingAction.name());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onGranted(PermissionResponse permissionResponse) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public enum PendingAction {
        NONE,
        POST_PHOTO,
        POST_STATUS_UPDATE
    }
}
*/
