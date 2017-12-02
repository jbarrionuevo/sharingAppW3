package com.example.sharingapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Add a new contact
 */
public class AddUserActivity extends AppCompatActivity {

    private UserList user_list = new UserList();
    private Context context;

    private EditText username;
    private EditText email;
    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);

        context = getApplicationContext();
        user_list.loadUsers(context);

        userController = new UserController();
    }

    public void saveUser(View view) {

        String username_str = username.getText().toString();
        String email_str = email.getText().toString();

        if (username_str.equals("")) {
            username.setError("Empty field!");
            return;
        }

        if (email_str.equals("")) {
            email.setError("Empty field!");
            return;
        }

        if (!email_str.contains("@")){
            email.setError("Must be an email address!");
            return;
        }

        for (User u : user_list.getUsers()) {
            if (u.getUsername().equals(username_str)) {
                username.setError("Username already taken!");
                return;
            }
        }

        User user = new User(username_str, email_str, null);

        // Add User
        AddUserCommand add_user_command = new AddUserCommand(user_list, user, context);
        add_user_command.execute();

        boolean success = add_user_command.isExecuted();
        if (!success) {
            return;
        }

        // End AddUserActivity
        finish();
    }
}
