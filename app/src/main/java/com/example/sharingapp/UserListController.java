package com.example.sharingapp;

import android.content.Context;

public class UserListController {
    private UserList userList;

    public UserListController(UserList user_list){
        this.userList = user_list;
    }

    public void addObserver(Observer observer) {
        System.out.println("UserListController.addObserver 1-------------------------");
        userList.addObserver(observer);
    }

    public void removeObserver(Observer observer) {
        userList.removeObserver(observer);
    }

    public boolean addUser(UserList user_list, User user, Context context) {
        AddUserCommand add_user_command = new AddUserCommand(user_list, user, context);
        add_user_command.execute();
        return add_user_command.isExecuted();
    }

    public boolean editUser(UserList user_list, User old_user, User updated_user, Context context) {
        EditUserCommand edit_user_command = new EditUserCommand(user_list, old_user, updated_user, context);
        edit_user_command.execute();
        return edit_user_command.isExecuted();
    }

    public boolean deleteUser(UserList user_list, User user, Context context) {
        DeleteUserCommand delete_user_command = new DeleteUserCommand(user_list, user, context);
        delete_user_command.execute();
        return delete_user_command.isExecuted();
    }
}
