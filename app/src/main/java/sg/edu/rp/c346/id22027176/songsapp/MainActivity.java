package sg.edu.rp.c346.id22027176.songsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvSongs;
    Button btnAdd;
    ArrayList<Songs> songs;
    ArrayAdapter<Songs> songsAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvSongs = findViewById(R.id.lvSongs);
        btnAdd = findViewById(R.id.btnAddPage);

        registerForContextMenu(lvSongs);

        btnAdd.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, InsertSongs.class);
            startActivity(i);
        });

        songs = new ArrayList<Songs>();
        songsAdapt = new ArrayAdapter<Songs>(this, android.R.layout.simple_list_item_1, songs);
        lvSongs.setAdapter(songsAdapt);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(MainActivity.this);
        songs.clear();
        songs.addAll(dbh.getSongs());
        songsAdapt.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int listPosition = info.position;
        Songs data = songs.get(listPosition);
        Log.d("data", String.valueOf(data));

        if(item.getItemId()==R.id.edit){
            Intent i = new Intent(MainActivity.this, EditSongs.class);
            i.putExtra("data", data);
            startActivity(i);
        }
        else{
            DBHelper dbh = new DBHelper(MainActivity.this);
            dbh.deleteSong(data.getId());
            onResume();
        }

        return super.onContextItemSelected(item);
    }
}