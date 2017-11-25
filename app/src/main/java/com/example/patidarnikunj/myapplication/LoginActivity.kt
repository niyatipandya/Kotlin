package com.example.patidarnikunj.myapplication

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.example.patidarnikunj.myapplication.AlertDialogManager
import com.example.patidarnikunj.myapplication.ConnectionDetector
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.facebook.share.Sharer
import com.facebook.share.widget.ShareDialog
import com.google.android.gms.common.api.GoogleApiClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import org.json.JSONException
import org.json.JSONObject
import java.net.MalformedURLException
import java.net.URL
import java.util.*

/**
 * Created by Niyati on 22-11-2017.
 */

internal var includeLayout: View? = null
private val TAG = "LoginActivity"
private var progressDialog: ProgressDialog? = null
private var isFbLogin = false
private var googleApiClient: GoogleApiClient? = null
private var callbackManager: CallbackManager? = null
private val profileTracker: ProfileTracker? = null
private var loginActivity: LoginActivity? = null
 var shareDialog: ShareDialog? = null
//private var pendingAction = PendingAction.NONE
 private var cd: ConnectionDetector? = null
private var isUserExist = false
private var profile_pic: URL? = null
private var alertDialogManager: AlertDialogManager? = null
public var editor: SharedPreferences.Editor? = null

private var sharedPreferences: SharedPreferences? = null;
var client: HashMap<String, String>? = null

var respo: AsyncHttpResponseHandler? = null
var params: RequestParams? = null

@Suppress("UNREACHABLE_CODE")
public class LoginActivity : AppCompatActivity() {

    private val shareCallback = object : FacebookCallback<Sharer.Result> {
        override fun onCancel() {
            Log.d("HelloFacebook", "Canceled")
        }

        override fun onError(error: FacebookException) {
            LoginManager.getInstance().logOut()
            Log.d("HelloFacebook", String.format("Error: %s", error.toString()))
            val title = getString(R.string.error)
            val alertMessage = error.message
            if (alertMessage != null) {
                showResult(title, alertMessage)
            }
        }

        @SuppressLint("StringFormatInvalid")
        override fun onSuccess(result: Sharer.Result) {
            LoginManager.getInstance().logOut()
            Log.d("HelloFacebook", "Success!")
            if (result.postId != null) {
                val title = getString(R.string.success)
                val id = result.postId
                val alertMessage = getString(R.string.successfully_posted_post, id)
                showResult(title, alertMessage)
            }
        }

        private fun showResult(title: String, alertMessage: String) {
            AlertDialog.Builder(this@LoginActivity)
                    .setTitle(title)
                    .setMessage(alertMessage)
                    .setPositiveButton(R.string.ok, null)
                    .show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FacebookSdk.sdkInitialize(this.applicationContext)
        callbackManager = CallbackManager.Factory.create()

        shareDialog = ShareDialog(this)
        shareDialog!!.registerCallback(callbackManager, shareCallback)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        setContentView(R.layout.activity_login)

        CommonUtil.hideSoftKeyboard(this@LoginActivity)

        loginActivity = this

        AppHelper.chageStatusBar(0, this@LoginActivity)

        var sharedPreferences: SharedPreferences = getSharedPreferences(VariableBag.SHAREDPREFRENCE, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        cd = com.example.patidarnikunj.myapplication.ConnectionDetector(this@LoginActivity)
        alertDialogManager = com.example.patidarnikunj.myapplication.AlertDialogManager()

        var mContext: Context = this

        var txtLoginAccount = findViewById<TextView>(R.id.txtLoginAccount) as TextView
        var txtEmailAddressText = findViewById<TextView>(R.id.txtEmailAddressText) as TextView
        var edEmail = findViewById<EditText>(R.id.edEmail) as EditText
        var relativeEmailAddress = findViewById<RelativeLayout>(R.id.relativeEmailAddress) as RelativeLayout
        var txtPasswordText = findViewById<TextView>(R.id.txtPasswordText) as TextView
        var edPassword = findViewById<EditText>(R.id.edPassword) as EditText
        var relativePassword = findViewById<RelativeLayout>(R.id.relativePassword) as RelativeLayout
        var txtForgotPassword = findViewById<TextView>(R.id.txtForgotPassword) as TextView
        var txtLogin = findViewById<TextView>(R.id.txtLogin) as TextView
        var loginButton = findViewById<LoginButton>(R.id.login_button) as LoginButton
        var imgFacebook = findViewById<ImageView>(R.id.imgFacebook) as ImageView
        var txtLoginWithFacebook = findViewById<TextView>(R.id.txtLoginWithFacebook) as TextView
        var relativeLoginFacebook = findViewById<RelativeLayout>(R.id.relativeLoginFacebook) as RelativeLayout
        var txtDontHaveLogin = findViewById<TextView>(R.id.txtDontHaveLogin) as TextView

        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday"))
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {

            override fun onSuccess(loginResult: LoginResult) {
                val request = GraphRequest.newMeRequest(
                        loginResult.accessToken
                ) { `object`, response ->
                    Log.v("Main", response.toString())

                    var accessToken = loginResult.accessToken.token
                    Log.i("accessToken", accessToken)
                    editor!!.putString(VariableBag.TOKEN, "Bearer " + accessToken)

                    getFacebookData(`object`)
                    Log.i("Object", `object`.toString())
                }
                val parameters = Bundle()
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location")
                request.parameters = parameters
                request.executeAsync()
            }

            override fun onCancel() {
                // App code
            }

            override fun onError(exception: FacebookException) {
                Toast.makeText(this@LoginActivity, resources.getString(R.string.error_to_login_facebook), Toast.LENGTH_SHORT).show()
            }
        })

        relativeLoginFacebook.setOnClickListener {
            loginButton.performClick()
        }
    }

    private fun getFacebookData(`object`: JSONObject) {

        try {
            val bundle = Bundle()
            var id = ""
            var first_name = ""
            var last_name = ""
            var email = ""
            var gender = ""
            var profilePic = ""
            val phone = ""
            var dob = ""
            val mobile_phone = ""

            if (`object`.has("id")) {
                id = `object`.getString("id")
                editor?.putString(VariableBag.fb_id, id)
            }

            if (`object`.has("first_name")) {
                first_name = `object`.getString("first_name")
                editor?.putString(VariableBag.first_nameFB, first_name)
            }

            if (`object`.has("last_name")) {
                last_name = `object`.getString("last_name")
                editor?.putString(VariableBag.last_nameFB, last_name)
            }

            if (`object`.has("email")) {
                email = `object`.getString("email")
                editor?.putString(VariableBag.emailFB, email)
            }

            if (`object`.has("gender")) {
                gender = `object`.getString("gender")
                editor?.putString(VariableBag.genderFB, gender)
            }
            if (`object`.has("birthday")) {
                dob = `object`.getString("birthday")
                editor?.putString(VariableBag.dobFB, dob)

            }
            if (`object`.has("mobile_phone")) {
                dob = `object`.getString("mobile_phone")
            }
            try {
                profile_pic = URL("https://graph.facebook.com/$id/picture?width=200&height=150")
                Log.i("profile_pic", profile_pic.toString() + "")
                profilePic = profile_pic.toString() + ""
                editor?.putString(VariableBag.imageFB, profile_pic.toString())

            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }

            if (`object`.has("location")) {
                editor?.putString(VariableBag.locationFB, `object`.getJSONObject("location").getString("name"))
                Log.i("location", "" + `object`.getJSONObject("location").getString("name"))
            }
            editor?.commit()
            Log.i(TAG, "id" + id)
            Log.i(TAG, "first_name" + first_name)
            Log.i(TAG, "last_name" + last_name)
            Log.i(TAG, "email" + email)
            Log.i(TAG, "gender" + gender)
            Log.i(TAG, "dob" + dob)
            Log.i(TAG, "mobile_phone" + mobile_phone)

            isFbUserExist(id, email, first_name, last_name, gender, profilePic, phone, dob)

        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    /*public fun isFbUserExist(facebookId: String, email: String, firstName: String, lastName: String, uGender: String, uProfilePic: String, phone: String, dob: String): Boolean {
        LoginManager.getInstance().logOut();
        Log.i("isFbUserExist", "isFbUserExist....");

        params = RequestParams()
        params?.put("email", email);
        params?.put("fb_id", facebookId);
        params?.put("first_name", firstName);
        params?.put("last_name", lastName);
        params?.put("phone", phone);
        params?.put("dob", dob);
        params?.put("gender", uGender);
        params?.put("image", uProfilePic);

        client = HashMap<String, String>()
        client?.put("device-os", "a");
        client?.put("os-v", sharedPreferences!!.getString(VariableBag.OSVERSION, ""));
        client?.put("app-v", sharedPreferences!!.getString(VariableBag.APPVERSION, ""));
        client?.put("device-token", "123456");
        client?.put("device-name", sharedPreferences!!.getString(VariableBag.DEVICENAME, ""));
        client?.put("language", "en");

        respo = fun AsyncHttpResponseHandler(function: () -> Unit): AsyncHttpResponseHandler? {

            fun onStart() {
                super.onStart()
            }


            fun onSuccess(content: String) {
                Log.i("content Login", "" + content)
                try {
                    var root: JSONObject = JSONObject("" + content)

                    if (root.has("data")) {
                        var data: JSONObject = root.getJSONObject("data")
                        var token: String = data.getString("token")
                        isUserExist = true
                        Log.i("isUserExist", "" + isUserExist)
                        editor?.putString(VariableBag.TOKEN, "Bearer " + token)

                        editor?.putBoolean(VariableBag.ISLOGIN, true)


                        editor?.putString(VariableBag.ID, data.getString("id"))
                        editor?.putString(VariableBag.email, data.getString("email"))
                        editor?.putString(VariableBag.image, data.getString("image"))
                        editor?.putString(VariableBag.first_name, data.getString("first_name"))
                        editor?.putString(VariableBag.last_name, data.getString("last_name"))
                        editor?.commit()

                        isUserExist = true
                        Log.i("isUserExist", "" + isUserExist);

                        *//* Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                         i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent . FLAG_ACTIVITY_CLEAR_TASK);
                         startActivity(i);
                         finish();*//*
                        val intent = Intent(this, HomeActivityKT::class.java)
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
                        finish()

                    } else {
                        isUserExist = false;
                        //startActivity( Intent (this, CreateNewAccount::class).putExtra("fbid", "" + facebookId).putExtra("firstname", "" + firstName).putExtra("lastname", "" + lastName).putExtra("email", "" + email).putExtra("contact", "").putExtra("birthday", "").putExtra("gender", "" + uGender).putExtra("about", "").putExtra("profile_pic", "" + uProfilePic));
                        //finish();
                    }

                } catch (e:JSONException) {
                    e.printStackTrace();
                }
            }

            fun onFailure(error: Throwable) {
                Toast.makeText(this, getResources().getString(R.string.something_wrong_happened), Toast.LENGTH_SHORT).show();
//                isUserExist = false;
//                startActivity(new Intent(LoginRegisterActivity.this, RegistrationActivity.class).putExtra("fbid", "" + facebookId).putExtra("firstname", "" + firstName).putExtra("lastname", "" + lastName).putExtra("email", "" + email).putExtra("contact", "").putExtra("birthday", "").putExtra("gender", "" + uGender).putExtra("about", "").putExtra("profile_pic", "" + uProfilePic));
//                finish();
            }

            fun onFinish() {
                // pDialog.dismiss();
            }
        }
        HttpRestClient.post("fb_register", params, respo, client);
        return isUserExist
    }
*/
    fun isFbUserExist(facebookId: String, email: String, firstName: String, lastName: String, uGender: String, uProfilePic: String, phone: String, dob: String): Boolean {
        LoginManager.getInstance().logOut()
        Log.i("isFbUserExist", "isFbUserExist....")

        val params = RequestParams()
        params.put("email", email)
        params.put("fb_id", facebookId)
        params.put("first_name", firstName)
        params.put("last_name", lastName)
        params.put("phone", phone)
        params.put("dob", dob)
        params.put("gender", uGender)
        params.put("image", uProfilePic)

        val client = HashMap<String, String>()
        client.put("device-os", "a")
        client.put("os-v", sharedPreferences!!.getString(VariableBag.OSVERSION, ""))
        client.put("app-v", sharedPreferences!!.getString(VariableBag.APPVERSION, ""))
        client.put("device-token", "123456")
        client.put("device-name", sharedPreferences!!.getString(VariableBag.DEVICENAME, ""))
        client.put("language", "en")

        val respo = object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.i("content Login", "" + responseBody)
                try {
                    val root = JSONObject("" + responseBody)
                    if (root.has("data")) {
                        val data = root.getJSONObject("data")
                        val token = data.getString("token")
                        isUserExist = true
                        Log.i("isUserExist", "" + isUserExist)
                        editor?.putString(VariableBag.TOKEN, "Bearer " + token)
                        editor?.putBoolean(VariableBag.ISLOGIN, true)
                        editor?.putString(VariableBag.ID, data.getString("id"))
                        editor?.putString(VariableBag.email, data.getString("email"))
                        editor?.putString(VariableBag.image, data.getString("image"))
                        editor?.putString(VariableBag.first_name, data.getString("first_name"))
                        editor?.putString(VariableBag.last_name, data.getString("last_name"))
                        editor?.commit()
                        isUserExist = true
                        Log.i("isUserExist", "" + isUserExist)

                        val i = Intent(this@LoginActivity, HomeActivityKT::class.java)
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(i)
                        finish()
                    } else {
                        isUserExist = false
                        // startActivity(Intent(this@LoginActivity, CreateNewAccount::class.java).putExtra("fbid", "" + facebookId).putExtra("firstname", "" + firstName).putExtra("lastname", "" + lastName).putExtra("email", "" + email).putExtra("contact", "").putExtra("birthday", "").putExtra("gender", "" + uGender).putExtra("about", "").putExtra("profile_pic", "" + uProfilePic))
                        //finish();
                    }
                } catch (e: Exception) {

                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Toast.makeText(this@LoginActivity, getResources().getString(R.string.something_wrong_happened), Toast.LENGTH_SHORT).show()
                isUserExist = false;
                // startActivity(new Intent(LoginRegisterActivity.this, RegistrationActivity.class).putExtra("fbid", "" + facebookId).putExtra("firstname", "" + firstName).putExtra("lastname", "" + lastName).putExtra("email", "" + email).putExtra("contact", "").putExtra("birthday", "").putExtra("gender", "" + uGender).putExtra("about", "").putExtra("profile_pic", "" + uProfilePic));
                // finish(); }
            }
        }
        HttpRestClient.post("fb_register", params, respo, client)
        return isUserExist
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}