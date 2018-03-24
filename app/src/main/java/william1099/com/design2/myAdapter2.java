package william1099.com.design2;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class myAdapter2 extends RecyclerView.Adapter<myAdapter2.viewHolder> {
    Context context;
    List<objectItem2> datasource;
    View vv;
    Dialog d;
    public myAdapter2(Context ctx, List<objectItem2> data) {
        this.context = ctx;
        this.datasource = data;
        d = new Dialog(ctx);
        vv = LayoutInflater.from(ctx).inflate(R.layout.custom_dialog, null);
        
    }
    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_recycle2, null);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(final viewHolder holder, final int position) {
        holder.judul.setText(datasource.get(position).judul);
        holder.tanggal.setText(datasource.get(position).tanggal);
        holder.desc.setText(datasource.get(position).deskripsi);
        Picasso.with(context).load(datasource.get(position).gambar).placeholder(R.drawable.ic_img).into(holder.gambar);
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                Log.d("possi click", position + "");
                b.putString("judul", datasource.get(position).judul);
                b.putString("tanggal", datasource.get(position).tanggal);
                b.putString("desc", datasource.get(position).deskripsi);
                b.putString("ket", datasource.get(position).keterangan);
                b.putString("link", datasource.get(position).link);
                b.putString("gambar", datasource.get(position).gambar);

                Intent intent = new Intent(context, Main5Activity.class);
                intent.putExtra("bundle", b);
                context.startActivity(intent);
            }
        });

        holder.ll.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                vv.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle b = new Bundle();
                        b.putString("judul", datasource.get(position).judul);
                        b.putString("tanggal", datasource.get(position).tanggal);
                        b.putString("desc", datasource.get(position).deskripsi);
                        b.putString("ket", datasource.get(position).keterangan);
                        b.putString("link", datasource.get(position).link);
                        b.putString("gambar", datasource.get(position).gambar);
                        b.putString("pos", ""+position);

                        Intent intent = new Intent(context, Main6Activity.class);
                        intent.putExtra("bundle", b);
                        context.startActivity(intent);
                        d.dismiss();
                    }
                });
                vv.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        d.dismiss();
                        Main4Activity.removeData(position, datasource.get(position).link, holder);
                        Toast.makeText(context, "Data berhasil dihapus ", Toast.LENGTH_SHORT).show();
                    }
                });
                d.setContentView(vv);
                d.show();
                return false;
            }
        });




    }

    @Override
    public int getItemCount() {
        return datasource.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView judul, tanggal, desc;
        ImageView gambar;
        LinearLayout ll;
        public viewHolder(View itemView) {
            super(itemView);
            judul = (TextView) itemView.findViewById(R.id.title2);
            tanggal = (TextView) itemView.findViewById(R.id.tanggal);
            desc = (TextView) itemView.findViewById(R.id.deskrip);
            gambar = (ImageView) itemView.findViewById(R.id.gambar);
            ll = (LinearLayout) itemView.findViewById(R.id.ll);
        }
    }
}
