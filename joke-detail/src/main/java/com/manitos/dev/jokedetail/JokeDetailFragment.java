package com.manitos.dev.jokedetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class JokeDetailFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_joke_detail, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Intent i = getActivity().getIntent();
        String resultGcp = getActivity().getIntent().getStringExtra(JokeDetailActivity.KEY_JOKE_GCP_RESULT);

        TextView tv = (TextView) view.findViewById(R.id.tv_detail);
        tv.setText(resultGcp);
    }
}
