package suitmedia.test.intern.ui.event;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import suitmedia.test.intern.R;
import suitmedia.test.intern.data.EventModel;
import suitmedia.test.intern.ui.ItemClickListener;

public class EventRVAdapter extends RecyclerView.Adapter<EventRVAdapter.ViewHolder> implements View.OnClickListener{

    ArrayList<EventModel> mData;
    Context context;
    ItemClickListener itemClickListener;

    public EventRVAdapter(Context context){
        mData = new ArrayList<>();
        this.context = context;
    }

    public void setData(ArrayList<EventModel> mData){
        this.mData = mData;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get()
                .load(mData.get(position).image)
                .into(holder.ivEvent);
        holder.tvEventName.setText(mData.get(position).eventName);
        holder.tvEventDate.setText(mData.get(position).eventDate);
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

        ImageView ivEvent;
        TextView tvEventName;
        TextView tvEventDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivEvent = itemView.findViewById(R.id.iv_event);
            tvEventName =itemView.findViewById(R.id.tv_event_name);
            tvEventDate = itemView.findViewById(R.id.tv_event_date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, tvEventName.getText().toString(), null);
            Toast.makeText(context, tvEventName.getText().toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
