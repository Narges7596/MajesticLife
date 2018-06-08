package com.farazannajmi.majesticlife.AccountPackage;

import android.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
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
import com.farazannajmi.majesticlife.R;

public class AccountManagementActivity extends FragmentActivity
        implements SignupDialogFragment.SignupDialogListener
{
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
        Avatar_img.setImageResource(DataHolder.User.getAvatar_ResIndex());
        Username_txt.setText(DataHolder.CurrentBacktoryUser.getUsername());
        XpLevel_txt.setText(Integer.toString(DataHolder.User.getXpLevel()));
        HpLevel_txt.setText(Integer.toString(DataHolder.User.getHpLevel()));
        SpLevel_txt.setText(Integer.toString(DataHolder.User.getSpLevel()));
        XpLevel_progBar.setProgress(DataHolder.User.getXP());
        HpLevel_progBar.setProgress(DataHolder.User.getHP());
        SpLevel_progBar.setProgress(DataHolder.User.getSP());

        if(BacktoryUser.getCurrentUser().isGuest())
            Signup_btn.setText(R.string.sign_up);
        else
        {
            Signup_btn.setText(R.string.change_account);
            Login_btn.setVisibility(View.INVISIBLE);
            Or_txt.setVisibility(View.INVISIBLE);
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
                //todo
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String username, String email, String password)
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

                            DataHolder.CurrentBacktoryUser = BacktoryUser.getCurrentUser();

                            Username_txt.setText(uName);
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
