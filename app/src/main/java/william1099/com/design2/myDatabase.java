package william1099.com.design2;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;

public class myDatabase extends SQLiteOpenHelper {
    private static String DB_NAME = "newsreader.db";
    private static int DB_VERSION = 1;
    private static String DB_PATH;
    public static myDatabase mInstance = null;
    public static Context context;
    static SQLiteDatabase db;
    public static AtomicInteger counter = new AtomicInteger();
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public myDatabase(Context ctx, String path) {
        super(ctx, DB_NAME, null, DB_VERSION);
        context = ctx;
        DB_PATH = path;
    }

    public void importDB() {
        this.getWritableDatabase();
        InputStream in = null;
        FileOutputStream out = null;
        try {
            in = context.getAssets().open("newsreader.db");
            out = new FileOutputStream(DB_PATH + DB_NAME);
            int read = -1;
            byte[] buffer = new byte[1024];
            while((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
                Log.d("b", read + "");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Toast.makeText(context, "DB berhasil diimport", Toast.LENGTH_SHORT).show();
    }

    public boolean DBexist() {
        File f = new File(DB_PATH + DB_NAME);
        return f.exists();
    }

    public static synchronized myDatabase getInstance() {
        if (mInstance == null) {
            mInstance = new myDatabase(myApplication.getAppContext(), "/data/data/" + myApplication.getAppContext().getPackageName() + "/databases/");
        }
        return  mInstance;
    }

    public synchronized SQLiteDatabase openDatabase() {
        if (counter.incrementAndGet() == 1) {
            db = mInstance.getWritableDatabase();
        }
        return db;
    }

    public synchronized void closeDatabase() {
        if (counter.decrementAndGet() == 0) {
            db.close();
        }
    }

}
