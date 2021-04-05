package com.example.ep.myapplication.Activitys.Adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ep.myapplication.Activitys.Activitys.MainActivity;
import com.example.ep.myapplication.Activitys.Model.State;
import com.example.ep.myapplication.R;

import java.util.ArrayList;
import java.util.Date;

import javax.security.auth.callback.Callback;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder_Normal> {

    private Context mContext;
    private ArrayList<State> allStates;
    private CallBackStates callBackStates;

    public StateAdapter(Context context, ArrayList<State> allStates) {
        this.allStates = allStates;
        this.mContext = context;
    }

    public void getCallBack(CallBackStates callBackStates) {
        this.callBackStates = callBackStates;

    }

    // get the size of the list
    @Override
    public int getItemCount() {
        return allStates == null ? 0 : allStates.size();
    }

    // specify the row layout file and click for each row
    @Override
    public ViewHolder_Normal onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        return new ViewHolder_Normal(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_Normal holder, int position) {
        holder.nativeName.setText(allStates.get(position).getNativeName());
        holder.countryName.setText(allStates.get(position).getName());
    }

    public ArrayList<State> custumeFilter(ArrayList<State> allstates, String str) {
        ArrayList<State> allstatesTemp= new ArrayList<>();
        for (State state: allstates) {
            if(str!= "") {
                if (state.getName().toLowerCase().startsWith(str.toLowerCase()))
                    allstatesTemp.add(state);
            }
        }
        return allstatesTemp;
    }

    public class ViewHolder_Normal extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView countryName;
        public TextView nativeName;

        public ViewHolder_Normal(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            countryName = itemView.findViewById(R.id.countryName);
            nativeName = itemView.findViewById(R.id.nativeName);

        }

        private State getItem(int position) {
            return allStates.get(position);
        }
        @Override
        public void onClick(View v) {

            callBackStates.LoadSecFragment(getItem(getAdapterPosition()),v);
        }
    }
}
