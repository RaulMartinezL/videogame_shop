package com.example.tienda_videojuegos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tienda_videojuegos.notedb.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class XboxGames extends AppCompatActivity {

    Database database = new Database();
    ListView listView;

    List<String> gameTitle = new ArrayList<String>();
    List<String> gameDescription = new ArrayList<String>();

    int[] gamePicture = {R.drawable.m9, R.drawable.m9, R.drawable.m9};

    void loadDatabase(){
        try {
            String object = database.loadJSONFromAsset(this);
            JSONObject obj = new JSONObject(object);
            JSONArray m_jArray = obj.getJSONArray("xbox_games");

            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArray.length(); i++){
                JSONObject jo_inside = m_jArray.getJSONObject(i);
                Log.d("Details->>", jo_inside.getString("game_name"));
                String game_name = jo_inside.getString("game_name");
                String game_description = jo_inside.getString("game_description");

                gameTitle.add(game_name);
                gameDescription.add(game_description);

            }


        } catch (JSONException ex) {
            ex.printStackTrace();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xbox_games);
        loadDatabase();

        listView = findViewById(R.id.xboxGames);

        MyAdapter adapter = new MyAdapter(this, gameTitle, gameDescription, gamePicture);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), GameDetail.class);
                intent.putExtra("AlbumTitle", gameTitle.get(position));
                intent.putExtra("AlbumDescription", gameDescription.get(position));
                intent.putExtra("AlbumImage", gamePicture[position]);
                startActivity(intent);
            }
        });
    }


    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String[] gameTitle;
        String[] gameDescription;
        int[] gamePicture;

        MyAdapter(Context c, List<String> title, List<String> description, int[] imgs) {
            super(c, R.layout.row_game, R.id.gameTitle, title);
            this.context = c;
            this.gameTitle = title.toArray(new String[0]);
            this.gameDescription = description.toArray(new String[0]);
            this.gamePicture = imgs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row_game, parent, false);
            ImageView images = row.findViewById(R.id.gameImage);
            TextView myTitle = row.findViewById(R.id.gameTitle);
            TextView myYear = row.findViewById(R.id.gameDescription);

            images.setImageResource(gamePicture[position]);
            myTitle.setText(gameTitle[position]);
            myYear.setText(gameDescription[position]);

            return row;
        }
    }
}
