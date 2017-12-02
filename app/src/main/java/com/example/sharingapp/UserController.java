package com.example.sharingapp;

import android.content.Context;

public class UserController {
    private UserList userList;
    public void addObserver(Observer observer){
        userList.addObserver(observer);
    }

    public void removeObserver(Observer observer){
        userList.removeObserver(observer);
    }

    public boolean editUser(UserList user_list, User old_user, User updated_user, Context context){
        EditUserCommand edit_user_command = new EditUserCommand(user_list, old_user, updated_user, context);
        edit_user_command.execute();
        return edit_user_command.isExecuted();
    }
}
