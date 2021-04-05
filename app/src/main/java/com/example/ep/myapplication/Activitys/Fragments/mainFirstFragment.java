package com.example.ep.myapplication.Activitys.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ep.myapplication.Activitys.Activitys.MainActivity;
import com.example.ep.myapplication.Activitys.Adapters.CallBackStates;
import com.example.ep.myapplication.Activitys.Adapters.StateAdapter;
import com.example.ep.myapplication.Activitys.Model.State;
import com.example.ep.myapplication.Activitys.Services.DataService;
import com.example.ep.myapplication.R;

import java.util.ArrayList;


public class mainFirstFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private StateAdapter theAdapter;
    private RecyclerView theRecyclerView;
    private ArrayList<State> allstates;

    private String mParam1;
    private String mParam2;

    private OnFirstFragmentInteractionListener mListener;

    public mainFirstFragment() {
        // Required empty public constructor
    }
    public static mainFirstFragment newInstance(String param1, String param2) {
        mainFirstFragment fragment = new mainFirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        final DataService ds = new DataService();
        final View v = inflater.inflate(R.layout.fragment_main_first, container, false);

        allstates = ds.getArrState();

        theAdapter = new StateAdapter(getContext(), allstates);
        theAdapter.getCallBack(callBack_top);

        theRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        theRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        theRecyclerView.setItemAnimator(new DefaultItemAnimator());
        theRecyclerView.setAdapter(theAdapter);

        EditText inputSearch = (EditText) v.findViewById(R.id.inputSearch);

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                allstates=theAdapter.custumeFilter(allstates, cs.toString());
                theAdapter = new StateAdapter(getActivity(), theAdapter.custumeFilter(allstates, cs.toString()));
                theAdapter.getCallBack(callBack_top);
                theRecyclerView.setAdapter(theAdapter);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

    return v;
    }

        CallBackStates callBack_top = new CallBackStates() {

            @Override
            public void LoadSecFragment(State state, View v) {
                Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
                v.startAnimation(hyperspaceJumpAnimation);

                int itemPosition = theRecyclerView.getChildLayoutPosition(v);

                State s = (State) allstates.get(itemPosition);
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.LoadSecFragment(s,v);
            }
        };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFirstFragmentInteractionListener) {
            mListener = (OnFirstFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFirstFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFirstFragmentInteraction(Uri uri);
    }
}
