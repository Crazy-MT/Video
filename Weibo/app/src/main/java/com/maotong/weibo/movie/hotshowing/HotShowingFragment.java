package com.maotong.weibo.movie.hotshowing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maotong.weibo.R;

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

	private void initData() {
		mHotShowingList = new ArrayList<>();

	}

	public static HotShowingFragment newInstance(String title)
	{
		HotShowingFragment tabFragment = new HotShowingFragment();
		Bundle bundle = new Bundle();
		bundle.putString(TITLE, title);
		tabFragment.setArguments(bundle);
		return tabFragment;
	}

}
