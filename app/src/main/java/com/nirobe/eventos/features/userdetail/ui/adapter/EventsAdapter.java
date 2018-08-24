package com.nirobe.eventos.features.userdetail.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nirobe.eventos.R;
import com.nirobe.eventos.features.userdetail.domain.entities.Event;

import java.util.ArrayList;

import butterknife.BindView;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {

    private ArrayList<Event> events;

    public EventsAdapter(){
        events = new ArrayList<>();
    }

    public void setEventsList(ArrayList<Event> events){
        this.events = events;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.events_list, parent, false);

        return new EventsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {
        Event event = events.get(position);
        holder.idEvent.setText(String.valueOf(event.getIdEvento()));
        holder.nameEvent.setText(event.getNombreEvento());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class EventsViewHolder extends RecyclerView.ViewHolder {

        TextView idEvent;
        TextView nameEvent;

        public EventsViewHolder(View view) {
            super(view);
            idEvent = (TextView) view.findViewById(R.id.idEvent);
            nameEvent = (TextView) view.findViewById(R.id.nameEvent);
        }
    }
}
