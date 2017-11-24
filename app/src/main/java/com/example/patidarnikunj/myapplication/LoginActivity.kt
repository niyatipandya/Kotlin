package com.example.patidarnikunj.myapplication

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.Layout
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.facebook.share.Sharer
import com.facebook.share.widget.ShareDialog
import com.google.android.gms.common.api.GoogleApiClient
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
private var shareDialog: ShareDialog? = null
//private var pendingAction = PendingAction.NONE
private var cd: ConnectionDetector? = null
private var isUserExist = false
private var profile_pic: URL? = null
private var alertDialogManager: AlertDialogManager? = null
public var editor: SharedPreferences.Editor? = null

public class LoginActivity() : AppCompatActivity() {

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
        setContentView(R.layout.activity_login)

        FacebookSdk.sdkInitialize(this.applicationContext)
        callbackManager = CallbackManager.Factory.create()

        shareDialog = ShareDialog(this)
        shareDialog!!.registerCallback(callbackManager, shareCallback)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        setContentView(R.layout.activity_login)

        CommonUtil.hideSoftKeyboard(this)

        loginActivity = this

        AppHelper.chageStatusBar(0, this)

        var sharedPreferences: SharedPreferences = getSharedPreferences(VariableBag.SHAREDPREFRENCE, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        cd = ConnectionDetector(this@LoginActivity)
        alertDialogManager = AlertDialogManager()

        var mContext: Context = this

        var imgBackBlue = findViewById<ImageView>(R.id.imgBackBlue) as ImageView
        var txtSkip = findViewById<TextView>(R.id.txtSkip) as TextView
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

                   // getFacebookData(`object`)
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

    }
/*
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
                editor.putString(VariableBag.fb_id, id)
            }

            if (`object`.has("first_name")) {
                first_name = `object`.getString("first_name")
                editor.putString(VariableBag.first_nameFB, first_name)
            }

            if (`object`.has("last_name")) {
                last_name = `object`.getString("last_name")
                editor.putString(VariableBag.last_nameFB, last_name)
            }

            if (`object`.has("email")) {
                email = `object`.getString("email")
                editor.putString(VariableBag.emailFB, email)
            }

            if (`object`.has("gender")) {
                gender = `object`.getString("gender")
                editor.putString(VariableBag.genderFB, gender)
            }
            if (`object`.has("birthday")) {
                dob = `object`.getString("birthday")
                editor.putString(VariableBag.dobFB, dob)

            }
            if (`object`.has("mobile_phone")) {
                dob = `object`.getString("mobile_phone")
            }
            try {
                profile_pic = URL("https://graph.facebook.com/$id/picture?width=200&height=150")
                Log.i("profile_pic", profile_pic.toString() + "")
                profilePic = profile_pic.toString() + ""
                editor.putString(VariableBag.imageFB, profile_pic.toString())

            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }

            if (`object`.has("location")) {
                editor.putString(VariableBag.locationFB, `object`.getJSONObject("location").getString("name"))
                Log.i("location", "" + `object`.getJSONObject("location").getString("name"))
            }
            editor.commit()
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

    }*/

}