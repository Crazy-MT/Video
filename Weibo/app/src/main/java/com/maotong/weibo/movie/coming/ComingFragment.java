package com.maotong.weibo.movie.coming;

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

public class ComingFragment extends Fragment
{
	private RecyclerView mRecycler;
	private List<ComingModel> mComingList;

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
		View view = inflater.inflate(R.layout.fragment_coming , container , false);
		mRecycler = (RecyclerView) view.findViewById(R.id.id_coming_recycler);
		initData();
		return view;

	}

	@Subscribe
	public void onEventMainThread(List<ComingModel> comingModelList){
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				setUpRecyclerView();
			}
		});
	}

	private void setUpRecyclerView() {
		ComingAdapter adapter = new ComingAdapter(getContext(), mComingList);
		mRecycler.setAdapter(adapter);
		mRecycler.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL , false));

	}

	private void initData() {
		mComingList = new ArrayList<>();
		new Thread(new Runnable() {
			@Override
			public void run() {
				mComingList = new JsonResolveUtils().getComing();
				Log.e("tag", "run: mComingList"+ mComingList.size() );
				EventBus.getDefault().post(mComingList);
			}
		}).start();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
}
