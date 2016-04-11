package com.maotong.weibo.movie.hotshowing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maotong.weibo.R;
import com.maotong.weibo.utils.JsonResolveUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class HotShowingFragment extends Fragment
{
	public static final String TITLE = "title";
	private String mTitle = "Defaut Value";
	private RecyclerView mRecycler;
	private List<HotShowingModel> mHotShowingList;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_hot_showing , container , false);
		mRecycler = (RecyclerView) view.findViewById(R.id.id_hot_showing_recycler);
		initData();
		return view;

	}

	@Subscribe
	public void onEventMainThread(List<HotShowingModel> hotShowingModelList){
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				setUpRecyclerView();
			}
		});
	}

	private void setUpRecyclerView() {
		HotShowingAdapter adapter = new HotShowingAdapter(getContext(),mHotShowingList);
		mRecycler.setAdapter(adapter);
		mRecycler.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL , false));

	}

	private void initData() {
		mHotShowingList = new ArrayList<>();
		new Thread(new Runnable() {
			@Override
			public void run() {
				mHotShowingList = new JsonResolveUtils().getMovie();
				EventBus.getDefault().post(mHotShowingList);
			}
		}).start();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
}