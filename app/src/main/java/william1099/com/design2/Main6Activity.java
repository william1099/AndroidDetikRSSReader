package william1099.com.design2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Main6Activity extends AppCompatActivity {
    Bundle data;
    TextView judul, tanggal, desc, ket;
    ImageView gambar;
    SQLiteDatabase myDB;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        data = getIntent().getBundleExtra("bundle");
        judul = (TextView) findViewById(R.id.judul4);
        tanggal = (TextView) findViewById(R.id.tanggal4);
        desc = (TextView) findViewById(R.id.desc4);
        gambar = (ImageView) findViewById(R.id.gambar4);
        ket = (TextView) findViewById(R.id.ket4);
        btn = (Button) findViewById(R.id.btn4);

        judul.setText(data.getString("judul"));
        tanggal.setText(data.getString("tanggal"));
        desc.setText(data.getString("desc"));
        ket.setText(data.getString("ket"));
        Picasso.with(this).load(data.getString("gambar")).placeholder(R.drawable.ic_img).into(gambar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               updateData(Integer.parseInt(data.getString("pos")));
                Toast.makeText(Main6Activity.this, "Berhasil diupdate", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void updateData(int pos) {
        myDB = myDatabase.getInstance().openDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Judul", judul.getText().toString());
        cv.put("Tanggal", tanggal.getText().toString());
        cv.put("Deskripsi", desc.getText().toString());
        cv.put("Keterangan", ket.getText().toString());
        cv.put("Link", data.getString("link"));
        cv.put("Gambar", data.getString("gambar"));
        cv.put("Sumber", "1");
        objectItem2 x = new objectItem2(0, judul.getText().toString(), data.getString("gambar"), desc.getText().toString(), data.getString("link"), tanggal.getText().toString(), ket.getText().toString());
        myDB.update("berita", cv, "Gambar == '" + data.getString("gambar") + "'", null);

        Main4Activity.update(pos, x);
    }
}
