package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Editing a pre-existing user consists of deleting the old user and adding a new user with the old
 * user's id.
 * Note: You will not be able contacts which are "active" borrowers
 */
public class EditUserActivity extends AppCompatActivity implements Observer{

    //private UserList user_list = new UserList();
    private User user;
    private EditText email;
    private EditText username;
    private Context context;
    private UserList user_list = new UserList();
    private UserListController userListController​ = new UserListController(user_list);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        context = getApplicationContext();
        user_list.loadUsers(context);

        Intent intent = getIntent();
        int pos = intent.getIntExtra("position", 0);

        user = user_list.getUser(pos);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);

        username.setText(user.getUsername());
        email.setText(user.getEmail());

        System.out.println("onCreate 1-------------------------");
        userListController​.addObserver(this);
        System.out.println("onCreate 2-------------------------");
    }

    public void saveUser(View view) {
        System.out.println("saveUser 1-------------------------");
        String email_str = email.getText().toString();

        if (email_str.equals("")) {
            email.setError("Empty field!");
            return;
        }

        if (!email_str.contains("@")){
            email.setError("Must be an email address!");
            return;
        }

        String username_str = username.getText().toString();

        // Check that username is unique AND username is changed (Note: if username was not changed
        // then this should be fine, because it was already unique.)
        if (!user_list.isUsernameAvailable(username_str) && !(user.getUsername().equals(username_str))) {
            username.setError("Username already taken!");
            return;
        }

        String id = user.getId(); // Reuse the user id
        User updated_user = new User(username_str, email_str, id);
        System.out.println("saveUser 2-------------------------");
        // Edit user
        //EditUserCommand edit_user_command = new EditUserCommand(user_list, user, updated_user, context);
        //edit_user_command.execute();

        //boolean success = edit_user_command.isExecuted();

        boolean success = userListController​.editUser(user_list, user, updated_user, context);
        if (!success){
            System.out.println("saveUser 3-------------------------");
            return;
        }

        System.out.println("saveUser 4-------------------------");
        userListController​.removeObserver(this);

        System.out.println("saveUser 5-------------------------");
        // End EditUserActivity
        finish();
        System.out.println("saveUser 6-------------------------");
    }

    public void deleteUser(View view) {

        // Delete user
        DeleteUserCommand delete_user_command = new DeleteUserCommand(user_list, user, context);
        delete_user_command.execute();

        boolean success = delete_user_command.isExecuted();
        if (!success){
            return;
        }

        // End EditUserActivity
        finish();
    }

    @Override
    public void update() {

    }
}
