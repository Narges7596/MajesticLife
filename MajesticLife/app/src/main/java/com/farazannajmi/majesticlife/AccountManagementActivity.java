package com.farazannajmi.majesticlife;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.backtory.java.internal.BacktoryUser;
import com.backtory.java.model.GuestRegistrationParam;

public class AccountManagementActivity extends AppCompatActivity
{
    public AppManager TheAppManager;

    public ImageView Avatar_img;
    public TextView Username_txt;
    public TextView XpLevel_txt;
    public TextView HpLevel_txt;
    public TextView SpLevel_txt;
    public ProgressBar XpLevel_progBar;
    public ProgressBar HpLevel_progBar;
    public ProgressBar SpLevel_progBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);

        TheAppManager = (AppManager) getApplicationContext();

        Avatar_img = findViewById(R.id.AccountM_Avatar_img);
        Username_txt = findViewById(R.id.AccountM_Username_txt);
        XpLevel_txt = findViewById(R.id.AccountM_XpLevel_txt);
        HpLevel_txt = findViewById(R.id.AccountM_HpLevel_txt);
        SpLevel_txt = findViewById(R.id.AccountM_SpLevel_txt);
        XpLevel_progBar = findViewById(R.id.AccountM_XpLevel_progBar);
        HpLevel_progBar = findViewById(R.id.AccountM_HP_progBar);
        SpLevel_progBar = findViewById(R.id.AccountM_SP_progBar);

        //region setting right UI values!

        //Avatar_img.setImageBitmap(TheAppManager.User.Avatar);
        Username_txt.setText(TheAppManager.User.CurrentBacktoryUser.getUsername());
        XpLevel_txt.setText(Integer.toString(TheAppManager.User.XpLevel));
        HpLevel_txt.setText(Integer.toString(TheAppManager.User.HpLevel));
        SpLevel_txt.setText(Integer.toString(TheAppManager.User.SpLevel));
        XpLevel_progBar.setProgress(TheAppManager.User.XP);
        HpLevel_progBar.setProgress(TheAppManager.User.HP);
        SpLevel_progBar.setProgress(TheAppManager.User.SP);

        //endregion
    }

    public void UiElementsOnClick(View view)
    {
        switch (view.getId())
        {
            case R.id.AccountM_Signup_btn:
            {
                Log.d("WorkFlow", "Clicked on sign up btn in account management activity.");

                //https://developer.android.com/guide/topics/ui/dialogs
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext()   );
                //region complete registration from guest to user
//
//                GuestRegistrationParam params = new GuestRegistrationParam.Builder().
//                        //setFirstName("alireza").
//                        //setLastName("farahani").
//                        setEmail("alireza.farahani@gmail.com").
//                        setNewPassword("123456").
//                        setNewUsername("a_farahani").
//                        build();
//
//                // CurrentUser is your guest user, so get your currentUser and complete his/her registration
//                BacktoryUser.getCurrentUser().completeRegistrationInBackground(params,
//                        new BacktoryCallBack<BacktoryUser>() {
//
//                            // CompleteRegistration done (fail or success), handling it:
//                            @Override
//                            public void onResponse(BacktoryResponse<BacktoryUser> response) {
//                                // Checking result of operation
//                                if (response.isSuccessful()) {
//                                    // Hooray, You are a normal user now
//                                    String firstName = response.body().getFirstName();
//                                    Log.d(TAG, firstName + ", thanks for registration");
//                                } else if (response.code() == HttpStatusCode.Conflict.code())
//                                    // You request username(= a_farahani) already exists
//                                    Log.d(TAG, "a user with this username already exist");
//                            } else {
//                                // Operation generally failed, maybe internet connection issue
//                                Log.d(TAG, "registration failed");
//                            }
//                        }
//            });
                //endregion
                break;
            }
            case R.id.AccountM_Login_btn:
            {
                Log.d("WorkFlow", "Clicked on log in btn in account management activity.");
                break;
            }
            default:
                break;
        }
    }
}
