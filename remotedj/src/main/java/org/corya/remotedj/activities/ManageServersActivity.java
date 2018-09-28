package org.corya.openseason.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.corya.openseason.R;
import org.corya.openseason.models.Server;
import org.corya.openseason.ServerDBSQLiteHelper;

import java.util.List;

// ACTIVITY TO MANAGE LIST OF SERVERS

public class ManageServersActivity extends ListActivity {
    private ArrayAdapter<String> serverNames;
    private List<Server> servers;
    private ServerDBSQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_servers);
        getActionBar().setTitle(R.string.action_manage_servers);
        db = new ServerDBSQLiteHelper(this);
        servers = db.getAllServers();

        // toast to add servers if there are none
        if(servers.size() < 1)
            Toast.makeText(getApplicationContext(), "Add some servers!", Toast.LENGTH_SHORT).show();

        // populate list of server names
        serverNames = new ArrayAdapter<String>(this, R.layout.manage_servers_list_item);
        for(int i = 0; i < servers.size(); i++){
            serverNames.add(servers.get(i).getName());
        }
        setListAdapter(serverNames);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.manage_servers, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_add) {
            startActivity(new Intent(this, AddServerActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}