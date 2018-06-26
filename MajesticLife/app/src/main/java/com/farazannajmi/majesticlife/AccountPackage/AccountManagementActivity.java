package com.farazannajmi.majesticlife.AccountPackage;

import android.app.DialogFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.backtory.java.HttpStatusCode;
import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryResponse;
import com.backtory.java.internal.BacktoryUser;
import com.backtory.java.model.GuestRegistrationParam;
import com.farazannajmi.majesticlife.DataHolder;
import com.farazannajmi.majesticlife.DataStructures.UserViewModel;
import com.farazannajmi.majesticlife.MainMenuActivity;
import com.farazannajmi.majesticlife.R;

public class AccountManagementActivity extends AppCompatActivity
        implements SignupDialogFragment.SignupDialogListener
{
    public static AppCompatActivity appCompatActivity;

    public ImageView Avatar_img;
    public TextView Username_txt;
    public TextView XpLevel_txt;
    public TextView HpLevel_txt;
    public TextView SpLevel_txt;
    public ProgressBar XpLevel_progBar;
    public ProgressBar HpLevel_progBar;
    public ProgressBar SpLevel_progBar;
    public Button Signup_btn;
    public Button Login_btn;
    public TextView Or_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);
        appCompatActivity = this;

        //getting UI elements:
        Avatar_img = findViewById(R.id.AccountM_Avatar_img);
        Username_txt = findViewById(R.id.AccountM_Username_txt);
        XpLevel_txt = findViewById(R.id.AccountM_XpLevel_txt);
        HpLevel_txt = findViewById(R.id.AccountM_HpLevel_txt);
        SpLevel_txt = findViewById(R.id.AccountM_SpLevel_txt);
        XpLevel_progBar = findViewById(R.id.AccountM_XpLevel_progBar);
        HpLevel_progBar = findViewById(R.id.AccountM_HP_progBar);
        SpLevel_progBar = findViewById(R.id.AccountM_SP_progBar);
        Signup_btn = findViewById(R.id.AccountM_Signup_btn);
        Login_btn = findViewById(R.id.AccountM_Login_btn);
        Or_txt = findViewById(R.id.AccountM_or_txt);

        //region setting right UI values!
        Avatar_img.setImageResource(DataHolder.ThisUser.getAvatar_ResIndex());
        Username_txt.setText(DataHolder.ThisUser.getUsername());
        XpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getXpLevel()));
        HpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getHpLevel()));
        SpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getSpLevel()));
        XpLevel_progBar.setProgress(DataHolder.ThisUser.getXP());
        HpLevel_progBar.setProgress(DataHolder.ThisUser.getHP());
        SpLevel_progBar.setProgress(DataHolder.ThisUser.getSP());


        //for checking internet connection:
        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean _isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(_isConnected && BacktoryUser.getCurrentUser().isGuest())
        {
            Signup_btn.setText(R.string.sign_up);
        }
        else if(_isConnected)
        {
            Signup_btn.setText(R.string.change_account);
            Login_btn.setVisibility(View.INVISIBLE);
            Or_txt.setVisibility(View.INVISIBLE);
        }
        else if(!_isConnected)
        {
            //todo
//            Signup_btn.setText(R.string.change_account);
//            Login_btn.setVisibility(View.INVISIBLE);
//            Or_txt.setVisibility(View.INVISIBLE);
        }
        //endregion
    }

    public void UiElementsOnClick(View view)
    {
        switch (view.getId())
        {
            case R.id.AccountM_Signup_btn:
            {
                Log.d("WorkFlow", "Clicked on sign up btn in account management activity.");

                //showing dialogue popup to get username, password and email for sign up
                SignupDialogFragment signupDialogue = new SignupDialogFragment();
                signupDialogue.show(getFragmentManager(), "SignupDialogFragment");
                break;
            }
            case R.id.AccountM_Login_btn:
            {
                Log.d("WorkFlow", "Clicked on log in btn in account management activity.");
                //todo
                break;
            }
            case R.id.AccountM_editAvatar_btn:
            {
                Intent AvatsrShopIntent = new Intent(AccountManagementActivity.this, AvatarShopActivity.class);
                startActivity(AvatsrShopIntent);
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, final String username, final String email, String password)
    {
        Log.d("WorkFlow", "Clicked Sign up on SignupDialog");

        GuestRegistrationParam params = new GuestRegistrationParam.
                Builder().
                setNewUsername(username).
                setEmail(email).
                setNewPassword(password).
                build();

        // CurrentUser is your guest user, so get your currentUser and complete his/her registration
        BacktoryUser.getCurrentUser().completeRegistrationInBackground(params,
                new BacktoryCallBack<BacktoryUser>()
                {
                    @Override
                    public void onResponse(BacktoryResponse<BacktoryUser> response)
                    {
                        if (response.isSuccessful())
                        {
                            String uName = response.body().getUsername();
                            Log.d("Backtory", "Successfully completed registered as " + uName);

                            Username_txt.setText(uName);

                            DataHolder.CurrentBacktoryUser = BacktoryUser.getCurrentUser();
                            DataHolder.ThisUser.setUsername(username);
                            DataHolder.ThisUser.setEmail(email);

                            //saving in database:
                            UserViewModel userViewModel = ViewModelProviders.of(appCompatActivity).get(UserViewModel.class);
                            userViewModel.update(DataHolder.ThisUser);
                        }
                        else if (response.code() == HttpStatusCode.Conflict.code())
                        {
                            Log.d("Backtory", "Failed completing registration: " + response.message());
                            Toast.makeText(getApplicationContext(), "Username is already taken! Please try another one.", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Log.d("Backtory", "Failed completing registration: " + response.message() + response.code());
                            Toast.makeText(getApplicationContext(), "Failed completing registration! Please try again later.", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
}
