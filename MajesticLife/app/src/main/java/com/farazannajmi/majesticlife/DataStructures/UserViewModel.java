package com.farazannajmi.majesticlife.DataStructures;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Narges on 6/9/2018.
 */

public class UserViewModel extends AndroidViewModel
{
    private UserRepository mRepository;
    private LiveData<User> mUser;

    public LiveData<User> getUser() {return mUser;}

    public UserViewModel (Application application)
    {
        super(application);
        mRepository = new UserRepository(application);
        mUser = mRepository.getUser();
    }

    public void insert(User user)
    {
        mRepository.insert(user);
    }

    public void update(User user)
    {
        mRepository.update(user);
    }

    public void delete(User user)
    {
        mRepository.delete(user);
    }


//    private List<User> userList;
//
//    public List<User> getUserList() {
//        if (userList == null) {
//            userList = loadUsers();
//        }
//        return userList;
//    }
//
//    private List<User> loadUsers() {
//        // do something to load users
//    }
}