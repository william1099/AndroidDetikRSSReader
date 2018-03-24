package william1099.com.design2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    static myAdapter2 adapter;
    static List<objectItem2> data;
    static SQLiteDatabase myDB;
    static String judul;
    static String gambar;
    static String link;
    static String desc;
    static String ket;
    static String tanggal;
    static int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        data = new ArrayList<>();
        getData();

        recyclerView = (RecyclerView) findViewById(R.id.recycleview2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new myAdapter2(this, data);
        recyclerView.setAdapter(adapter);
    }

    public static void getData() {
        myDB = myDatabase.getInstance().openDatabase();
        //Log.d("db", myDB + "");
        Cursor res = myDB.query("berita", new String[] {"ID", "Judul", "Gambar", "Tanggal", "Deskripsi", "Keterangan", "Link"}, null, null, null, null,null);
        while(res.moveToNext()) {
           judul = res.getString(res.getColumnIndex("Judul"));
           tanggal = res.getString(res.getColumnIndex("Tanggal"));
           gambar = res.getString(res.getColumnIndex("Gambar"));
            desc = res.getString(res.getColumnIndex("Deskripsi"));
            link = res.getString(res.getColumnIndex("Link"));
            ket = res.getString(res.getColumnIndex("Keterangan"));
            id = res.getInt(res.getColumnIndex("ID"));
            data.add(new objectItem2(id, judul, gambar, desc, link, tanggal, ket));

        }
        myDatabase.getInstance().closeDatabase();
    }

    public static void removeData(int pos, String link, RecyclerView.ViewHolder holder) {
        myDB = myDatabase.getInstance().openDatabase();
        Cursor r = myDB.query("berita", new String[]{"ID"}, "link == '" + link + "'", null, null, null, null);
        r.moveToFirst();
        int id = r.getInt(0);
        data.remove(pos);
        int res = myDB.delete("berita", "ID == " + id, null);
        adapter.notifyDataSetChanged();
        myDatabase.getInstance().closeDatabase();

    }

    public static void update(int pos, objectItem2 item) {
        data.set(pos, item);
        adapter.notifyDataSetChanged();
    }
}
