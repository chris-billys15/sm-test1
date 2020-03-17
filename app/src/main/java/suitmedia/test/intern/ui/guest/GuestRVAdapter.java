package suitmedia.test.intern.ui.guest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import suitmedia.test.intern.R;
import suitmedia.test.intern.data.GuestModel;
import suitmedia.test.intern.ui.ItemClickListener;

public class GuestRVAdapter extends RecyclerView.Adapter<GuestRVAdapter.ViewHolder> implements View.OnClickListener{

    ItemClickListener itemClickListener;
    ArrayList<GuestModel> mData;
    Context context;

    public GuestRVAdapter(Context context){
        mData = new ArrayList<>();
        this.context = context;
    }

    public void setData(ArrayList<GuestModel> mData){
        this.mData = mData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_guest, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvIDGuest.setText(mData.get(position).id);
        holder.tvNameGuest.setText(mData.get(position).name);
        holder.tvBirthGuest.setText(mData.get(position).birthdate);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvIDGuest;
        TextView tvNameGuest;
        TextView tvBirthGuest;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIDGuest = itemView.findViewById(R.id.id_guest);
            tvNameGuest = itemView.findViewById(R.id.name_guest);
            tvBirthGuest = itemView.findViewById(R.id.birth_guest);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, tvNameGuest.getText().toString(), tvBirthGuest.getText().toString());
        }
    }
}
