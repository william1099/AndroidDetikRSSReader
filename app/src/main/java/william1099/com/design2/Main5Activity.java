package william1099.com.design2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Main5Activity extends AppCompatActivity {
    Bundle data;
    TextView judul, tanggal, desc, ket;
    ImageView gambar;
    Button btn;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        data = getIntent().getBundleExtra("bundle");
        judul = (TextView) findViewById(R.id.judul3);
        tanggal = (TextView) findViewById(R.id.tanggal3);
        desc = (TextView) findViewById(R.id.desc3);
        gambar = (ImageView) findViewById(R.id.gambar3);
        ket = (TextView) findViewById(R.id.ket3);
        btn = (Button) findViewById(R.id.btn3);

        judul.setText(data.getString("judul"));
        tanggal.setText(data.getString("tanggal"));
        desc.setText(data.getString("desc"));
        ket.setText(data.getString("ket"));
        Picasso.with(this).load(data.getString("gambar")).placeholder(R.drawable.ic_img).into(gambar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(Main5Activity.this);
                progressDialog.setTitle("Loading...");
                progressDialog.show();
                Intent intent =  new Intent(Main5Activity.this, Main2Activity.class);
                intent.putExtra("link", data.getString("link"));
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            progressDialog.dismiss();
        }
    }
}
