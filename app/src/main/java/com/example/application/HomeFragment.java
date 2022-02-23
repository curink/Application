package com.example.application;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import java.util.HashMap;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.example.application.api.RequestNetwork;
import com.example.application.api.RequestNetworkController;

public class HomeFragment extends Fragment {
	private HashMap<String, Object> responseMap = new HashMap<>();
	
	private RequestNetwork req;
	private RequestNetwork.RequestListener req_listener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		getActivity().setTitle("Home");
		final View view = inflater.inflate(R.layout.home_fragment, container, false);
		
		req = new RequestNetwork(getActivity());
		
		req_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(final String tag, final String response, final HashMap<String, Object> headers, final int status) {
			        Toast.makeText(getActivity(), responseMap.get("name"), Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onErrorResponse(final String tag, final String message) {
				Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
			}
		};

		req.startRequestNetwork(RequestNetworkController.GET, "https://api.github.com/users/curink", "profile", req_listener);
		
		return view;
	}
}
