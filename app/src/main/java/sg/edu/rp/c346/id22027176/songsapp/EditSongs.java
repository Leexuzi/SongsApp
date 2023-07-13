package sg.edu.rp.c346.id22027176.songsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditSongs extends AppCompatActivity {

    EditText etTitle, etSinger, etYear;
    RadioGroup rdBtnGrp;
    RadioButton btnStars;
    Button btnEdit;

    Songs data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_songs);

        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rdBtnGrp = findViewById(R.id.rdBtnGrp);
        btnEdit = findViewById(R.id.btnEdit);

        Intent i = getIntent();
        data = (Songs) i.getSerializableExtra("data");

        etTitle.setText(data.getTitle());
        etSinger.setText(data.getSingers());
        Log.d("year", String.valueOf(data.getYear()));
        etYear.setText(data.getYear());
        rdBtnGrp.check(data.getStars());

        btnEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(EditSongs.this);

                // Insert a song
                String title = etTitle.getText().toString();
                String singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int rBtnId = rdBtnGrp.getCheckedRadioButtonId();
                btnStars = findViewById(rBtnId);
                int stars = Integer.parseInt(btnStars.getText().toString());
                db.updateSong(data);

                finish();
            }
        });

    }
}