package com.example.waiduriid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waiduriid.database.entitas.Waiduri;

import java.util.List;

public class WaiduriAdapter extends RecyclerView.Adapter<WaiduriAdapter.ViewAdapter> {
    private List<Waiduri> list;
    private Context context;
    private Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }


    public WaiduriAdapter(Context context, List<Waiduri> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int view) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_waiduri,parent,false);
        return new ViewAdapter(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.tvNamaBarang.setText(list.get(position).Nama);
        holder.tvDeskripsi.setText(list.get(position).Harga);
        holder.tvStock.setText(list.get(position).Stock);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{

        TextView tvNamaBarang, tvDeskripsi, tvStock;

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);

            tvNamaBarang = itemView.findViewById(R.id.tv_namaBarang);
            tvDeskripsi =  itemView.findViewById(R.id.tv_deskripsi);
            tvStock = itemView.findViewById(R.id.tv_stockTersedia);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }

                }
            });
        }
    }


}
