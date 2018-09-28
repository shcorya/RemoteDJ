package org.corya.openseason;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.corya.openseason.models.Server;

import java.util.LinkedList;
import java.util.List;

public class ServerDBSQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "ServerDB";
    // Servers table name
    private static final String TABLE_SERVERS = "servers";
    // Servers Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_IP = "ip";
    private static final String KEY_PORT = "port";
    private static final String KEY_NAME = "name";

    private static final String[] COLUMNS = {KEY_ID, KEY_IP, KEY_PORT, KEY_NAME};

    public ServerDBSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create servers table
        String CREATE_SERVER_TABLE = "CREATE TABLE servers ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ip TEXT, " +
                "port INTEGER, " +
                "name TEXT )";

        // create servers table
        db.execSQL(CREATE_SERVER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older servers table if existed
        db.execSQL("DROP TABLE IF EXISTS servers");

        // create fresh servers table
        this.onCreate(db);
    }

    public void addServer(Server server){
        //for logging
        Log.d("addServer()", server.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_IP, server.getIp()); // get IP
        values.put(KEY_PORT, server.getPort()); // get port
        values.put(KEY_NAME, server.getName()); // get name

        // 3. insert
        if (db != null) {
            db.insert(TABLE_SERVERS, // table
                    null, //nullColumnHack
                    values); // key/value -> keys = column names/ values = column values
        }

        // 4. close
        if (db != null) {
            db.close();
        }
    }

    public Server getServer(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor = null; // h. limit
        if (db != null) {
            cursor = db.query(TABLE_SERVERS, // a. table
                    COLUMNS, // b. column names
                    " id = ?", // c. selections
                    new String[] { String.valueOf(id) }, // d. selections args
                    null, // e. group by
                    null, // f. having
                    null, // g. order by
                    null);
        }

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build server object
        Server server = new Server();
        if (cursor != null) {
            server.setId(Integer.parseInt(cursor.getString(0)));
            server.setIp(cursor.getString(1));
            server.setPort(Integer.parseInt(cursor.getString(2)));
            server.setName(cursor.getString(3));
        }

        //log
        Log.d("getServer("+id+")", server.toString());

        // 5. return server
        return server;
    }

    public List<Server> getAllServers() {
        List<Server> servers = new LinkedList<Server>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_SERVERS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        // 3. go over each row, build server and add it to list
        Server server = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    server = new Server();
                    server.setId(Integer.parseInt(cursor.getString(0)));
                    server.setIp(cursor.getString(1));
                    server.setPort(Integer.parseInt(cursor.getString(2)));
                    server.setName(cursor.getString(3));

                    // Add server to servers
                    servers.add(server);
                } while (cursor.moveToNext());
            }
        }

        Log.d("getAllServers()", servers.toString());

        // return servers
        return servers;
    }

    public int updateServer(Server server) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_IP, server.getIp()); // get IP
        values.put(KEY_PORT, server.getPort()); // get port
        values.put(KEY_NAME, server.getName()); // get name

        // 3. updating row
        int i = 0; //selection args
        if (db != null) {
            i = db.update(TABLE_SERVERS, //table
                    values, // column/value
                    KEY_ID+" = ?", // selections
                    new String[] { String.valueOf(server.getId()) });
        }

        // 4. close
        if (db != null) {
            db.close();
        }

        return i;
    }

    public void deleteServer(Server server) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        if (db != null) {
            db.delete(TABLE_SERVERS, //table name
                    KEY_ID+" = ?",  // selections
                    new String[] { String.valueOf(server.getId()) }); //selections args
        }

        // 3. close
        if (db != null) {
            db.close();
        }

        //log
        Log.d("deleteServer()", server.toString());
    }
}