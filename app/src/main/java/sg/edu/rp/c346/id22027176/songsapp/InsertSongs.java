package sg.edu.rp.c346.id22027176.songsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class InsertSongs extends AppCompatActivity {

    EditText etTitle, etSinger, etYear;
    RadioGroup rdBtnGrp;
    RadioButton btnStars;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_songs);

        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rdBtnGrp = findViewById(R.id.rdBtnGrp);
        btnAdd = findViewById(R.id.btnEdit);

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(InsertSongs.this);

                // Insert a song
                String title = etTitle.getText().toString();
                String singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int rBtnId = rdBtnGrp.getCheckedRadioButtonId();
                btnStars = findViewById(rBtnId);
                int stars = Integer.parseInt(btnStars.getText().toString());
                db.insertSong(title, singer, year, stars);

                finish();
            }
        });
    }
}