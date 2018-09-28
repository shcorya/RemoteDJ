package org.corya.openseason.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.corya.openseason.R;
import org.corya.openseason.models.Server;
import org.corya.openseason.ServerDBSQLiteHelper;


// ACTIVITY USED TO CREATE A NEW SERVER OBJECT

public class AddServerActivity extends Activity {
    private Button button;
    private EditText ipField;
    private EditText portField;
    private EditText nameField;
    private String ip;
    private int port;
    private String name;

    private ServerDBSQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_server);
        db = new ServerDBSQLiteHelper(this);

        ipField = (EditText) findViewById(R.id.ip_input);
        portField = (EditText) findViewById(R.id.port_input);
        nameField = (EditText) findViewById(R.id.name_input);

        // create and add server on button click
        button = (Button) findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            // read text fields
            ip = ipField.getText().toString(); //get the text message on the text field
            port = Integer.parseInt(portField.getText().toString());
            name = nameField.getText().toString();

            // reset text fields
            ipField.setText("");
            portField.setText("");
            nameField.setText("");

            // add server
            db.addServer(new Server(ip, port, name));

            // toast to success
            Toast.makeText(getApplicationContext(), "Server added successfully!", Toast.LENGTH_SHORT).show();

            // restart manage servers activity
            startActivity(new Intent(AddServerActivity.this, ManageServersActivity.class));
            }
        });
    }
}