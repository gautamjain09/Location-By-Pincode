package com.gautamjain.locationbypincode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CurrentAdapter extends RecyclerView.Adapter<CurrentAdapter.ViewHolder> {

    private ArrayList<CurrentModal> currentModalArrayList;
    private Context context;
    private static DecimalFormat df6 = new DecimalFormat("#.######");

    public CurrentAdapter(ArrayList<CurrentModal> currentModalArrayList, Context context) {
        this.currentModalArrayList = currentModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.current_item, parent, false);
        return new CurrentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentAdapter.ViewHolder holder, int position) {

        CurrentModal current = currentModalArrayList.get(position);

        holder.placeName.setText(current.getPlaceName());
        holder.stateName.setText(current.getStateName());
        holder.longitude.setText( df6.format(current.getLongitude()));
        holder.latitude.setText( df6.format(current.getLatitude()));
        holder.stateAbbrevation.setText(current.getStateAbbrevation());
    }

    @Override
    public int getItemCount() {
        return currentModalArrayList.size();
    }

    public void filterList(ArrayList<CurrentModal> filteredList)
    {
        currentModalArrayList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView placeName;
        private TextView stateName;
        private TextView stateAbbrevation;
        private TextView latitude;
        private TextView longitude;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            placeName = itemView.findViewById(R.id.idPlaceName);
            stateName = itemView.findViewById(R.id.idStateName);
            stateAbbrevation = itemView.findViewById(R.id.idStateAbbrevation);
            latitude = itemView.findViewById(R.id.idLatitude);
            longitude = itemView.findViewById(R.id.idLongitude);
        }
    }
}
