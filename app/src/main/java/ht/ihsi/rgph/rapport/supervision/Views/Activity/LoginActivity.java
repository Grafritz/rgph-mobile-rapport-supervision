package ht.ihsi.rgph.rapport.supervision.Views.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import ht.ihsi.rgph.rapport.supervision.R;
import ht.ihsi.rgph.rapport.supervision.Constant.Constant;
import ht.ihsi.rgph.rapport.supervision.Models.PersonnelModel;
import ht.ihsi.rgph.rapport.supervision.Utilities.Tools;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private Context context;

    public BootstrapButton btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init(Constant.FORM_DATA_MANAGER);

        context = LoginActivity.this;
        mEmailView = (EditText) findViewById(R.id.tv_UserName);
        mPasswordView = (EditText) findViewById(R.id.tv_Password);
        mPasswordView.setOnEditorActionListener(this);

        btnSignIn = (BootstrapButton) findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);

    }//

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignIn:
                LoginConnexion();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == Constant.imeActionId_EtReponse_6){
            LoginConnexion();
            return true;
        }
        return false;
    }
    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void LoginConnexion() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.msg_Username_Obligatoire));
            focusView = mEmailView;
            cancel = true;
        }else
        if (TextUtils.isEmpty(password)) {
            // Check for a valid password, if the user entered one.
            mPasswordView.setError(getString(R.string.msg_MotDePasse_Obligatoire));
            focusView = mPasswordView;
            cancel = true;
        }else
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !Tools.isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.msg_MotDePasse_TropCourt));
            focusView = mPasswordView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            new UserLoginTask(email, password).execute();
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        private final String mEmail;
        private final String mPassword;
        private PersonnelModel personnelModel = null;
        private ProgressDialog nDialog;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            nDialog = new ProgressDialog(context);
            //nDialog.setTitle("Checking Network");
            nDialog.setMessage(getString(R.string.msg_WaitAMinit));
            nDialog.setIndeterminate(false);
            nDialog.setCancelable(false);
            nDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                //Thread.sleep(2000);
                personnelModel = formDataMngr.getPersonnelInfo(mEmail, mPassword);
                if(personnelModel == null ) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            try {
                if (nDialog != null) {
                    nDialog.dismiss();
                }

                if (success) {
                    if (personnelModel != null) {
                        if (!personnelModel.getEstActif()) {
                            message = getString(R.string.label_CompteUtilisateur) + " " + getString(R.string.msg_Inactif);
                            Tools.LogCat(message);
                            Tools.ToastMessage(context, message, Constant.GravityCenter);
                            mEmailView.setError(message);
                            mEmailView.requestFocus();
                        } else {
                            personnelModel.setIsConnected(true);

                            message = "Bienvenu: " + personnelModel.getPrenom() + " " + personnelModel.getNom();
                            Tools.LogCat(message);
                            Tools.ToastMessage(context, message, Constant.GravityBottom);
                            Tools.StoreInfoPresonnel_PreferenceManager(context, personnelModel);
                            finish();
                        }
                    } else {
                        message = getString(R.string.label_CompteUtilisateur) + " et/ou " + getString(R.string.label_MotDePasse)
                                + " " + getString(R.string.label_Incorrect);
                        Tools.LogCat(message);
                        Tools.ToastMessage(context, message, Constant.GravityCenter);
                        mEmailView.setError(message);
                        mEmailView.requestFocus();
                    }
                } else {
                    message = getString(R.string.msg_UserName_ou_MotDePasse_Incorrect);
                    Tools.ToastMessage(context, message, Constant.GravityCenter);
                    mPasswordView.setError(message);
                    mPasswordView.requestFocus();
                }
            } catch (Exception e) {
                message =getString(R.string.msg_UserName_ou_MotDePasse_Incorrect);
                Tools.ToastMessage(context, message, Constant.GravityCenter);
                mPasswordView.setError(message);
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            //showProgress(false);
        }
    }
}

