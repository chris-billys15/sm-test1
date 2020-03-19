package suitmedia.test.intern.ui.event;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import suitmedia.test.intern.R;
import suitmedia.test.intern.data.EventModel;
import suitmedia.test.intern.ui.ItemClickListener;

public class EventRVAdapter extends RecyclerView.Adapter<EventRVAdapter.ViewHolder> implements View.OnClickListener{

    ArrayList<EventModel> mData;
    Context context;
    ItemClickListener itemClickListener;
    MapListener mapListener;
    String type;

    public EventRVAdapter(Context context, String type){
        mData = new ArrayList<>();
        this.context = context;
        this.type = type;
    }

    public void setData(ArrayList<EventModel> mData){
        this.mData = mData;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);;
        if (type.equals("mapFragment")){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_event, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get()
                .load(mData.get(position).image)
                .into(holder.ivEvent);
        holder.tvEventName.setText(mData.get(position).eventName);
        holder.tvEventDate.setText(mData.get(position).eventDate);
        if(type != "mapFragment"){
            holder.tvEventDesc.setText(mData.get(position).eventDesc);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public void setClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public void setMapListener(MapListener mapListener){
        this.mapListener = mapListener;
    }
    @Override
    public void onClick(View v) {

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView ivEvent;
        TextView tvEventName;
        TextView tvEventDate;
        TextView tvEventDesc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivEvent = itemView.findViewById(R.id.iv_event);
            tvEventName =itemView.findViewById(R.id.tv_event_name);
            tvEventDate = itemView.findViewById(R.id.tv_event_date);
            tvEventDesc = itemView.findViewById(R.id.tv_desc);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(type.equals("mapFragment")){
                int idx = searchGeoLoc(tvEventName.getText().toString());
                mapListener.pinPointGeoLoc(mData.get(idx).latitude, mData.get(idx).longitude);
                mapListener.showInfoMarker(idx);
            }
            else{
                itemClickListener.onItemClick(v, tvEventName.getText().toString(), null);
                Toast.makeText(context, tvEventName.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        }

        public int searchGeoLoc(String eventName){
            int i = 0;
            while (i < mData.size()){
                if(mData.get(i).eventName.equals(eventName)){
                    break;
                }
                i++;
            }
            return i;
        }
    }
}
