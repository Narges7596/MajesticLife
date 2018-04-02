package com.farazannajmi.majesticlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AccountManagementActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);
    }

    public void UiElementsOnClick(View view)
    {
        switch (view.getId())
        {
            case R.id.AccountM_Signup_btn:
            {
                break;
            }
            case R.id.AccountM_Login_btn:
            {
                break;
            }
            default:
                break;
        }
    }
}
