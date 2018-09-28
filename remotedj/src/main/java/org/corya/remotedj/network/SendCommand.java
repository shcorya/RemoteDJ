package org.corya.openseason.network;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendCommand extends AsyncTask<String, Void, Void> {

    public static final String NULL = "-1";

    @Override
    protected Void doInBackground(String... strings) {
        try {
            Socket socket = new Socket(strings[0], Integer.parseInt(strings[1]));  //connect to server
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeInt(Integer.parseInt(strings[2]));
            out.writeInt(Integer.parseInt(strings[3]));
            out.writeInt(Integer.parseInt(strings[4]));
            socket.close();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}