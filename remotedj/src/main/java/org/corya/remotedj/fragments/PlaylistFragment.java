package org.corya.openseason.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.corya.openseason.R;
import org.corya.openseason.models.Playlist;
import org.corya.openseason.models.Server;
import org.corya.openseason.network.SendCommand;

public class PlaylistFragment extends ListFragment {

    private Server server;
    private Playlist playlist;

    public PlaylistFragment (Server server, Playlist playlist) {
        Bundle args = new Bundle();
        args.putString(Server.KEY, server.toJsonString());
        args.putString(Playlist.KEY, playlist.toJsonString());
        setArguments(args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        server = Server.fromJsonString(getArguments().getString(Server.KEY));
        playlist = Playlist.fromJsonString(getArguments().getString(Playlist.KEY));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        ArrayAdapter<String> titles = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1);
        for(int i = 0; i < playlist.size(); i++){
            titles.add(playlist.getTrackAt(i).getTitle());
        }
        setListAdapter(titles);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        new SendCommand().execute(server.getIp(), Integer.toString(server.getPort()), Integer.toString('g'), Integer.toString(position), SendCommand.NULL);
    }
}