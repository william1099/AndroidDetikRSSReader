package william1099.com.design2;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.HashMap;

public class myAdapter extends RecyclerView.Adapter<myAdapter.viewHolder>{
    ArrayList<HashMap<String, String>> datasource;
    Context context;
    ImageLoader imageLoader;
    Activity activity;
    Dialog d;
    View vv;
    Button tambah;
    EditText text;
    static ProgressDialog progressDialog;
    public myAdapter(ArrayList<HashMap<String, String>> datasource, Context context, Activity activity) {
        this.datasource = datasource;
        this.context = context;
        this.activity = activity;
        imageLoader = mySingleton.getInstance().imageLoader;
        d = new Dialog(context);
        vv = LayoutInflater.from(context).inflate(R.layout.custom_dialog2, null);
        tambah = vv.findViewById(R.id.tambah);
        text = vv.findViewById(R.id.ulasa);
        d.setContentView(vv);
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_recycle, parent, false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, final int position) {
        holder.title.setText(datasource.get(position).get("title"));
        holder.date.setText(datasource.get(position).get("date"));
        holder.desc.setText(datasource.get(position).get("description"));
        holder.image.setImageUrl(datasource.get(position).get("image"), imageLoader);
        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                d.show();
                tambah.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        insertData(datasource.get(position).get("title"), datasource.get(position).get("date"), datasource.get(position).get("description"), datasource.get(position).get("image"), datasource.get(position).get("link"));
                        text.setText("");
                        d.dismiss();
                    }
                });
                return false;
            }
        });

        holder.title.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(context);
                progressDialog.setTitle("Loading...");
                progressDialog.show();
                Intent intent =  new Intent(context, Main2Activity.class);
                intent.putExtra("link", datasource.get(position).get("link"));
                activity.startActivityForResult(intent, 1);

            }
        });


    }

    @Override
    public int getItemCount() {
        return datasource.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        public TextView title, desc, date;
        public NetworkImageView image;
        public LinearLayout linearLayout;

        public viewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.judul);
            this.desc = (TextView) itemView.findViewById(R.id.desc);
            this.date = (TextView) itemView.findViewById(R.id.date);
            this.image = (NetworkImageView) itemView.findViewById(R.id.networkimage);
            this.linearLayout = (LinearLayout) itemView.findViewById(R.id.linearlay);
        }
    }

    public void insertData(String judul, String tgl, String desc, String img, String link) {
        SQLiteDatabase myDB = myDatabase.getInstance().openDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Judul", judul);
        cv.put("Gambar", img);
        cv.put("Tanggal", tgl);
        cv.put("Deskripsi", desc);
        cv.put("Keterangan", text.getText().toString());
        cv.put("Link", link);
        cv.put("Sumber", "1");
        myDB.insert("berita", null, cv);
        Toast.makeText(context, "Berita berhasil ditambah", Toast.LENGTH_SHORT).show();
        myDatabase.getInstance().closeDatabase();
    }
}
