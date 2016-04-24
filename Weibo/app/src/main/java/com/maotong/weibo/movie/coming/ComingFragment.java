package com.maotong.weibo.movie.coming;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maotong.weibo.R;
import com.maotong.weibo.api.AccessTokenKeeper;
import com.maotong.weibo.base.WeiBoApplication;
import com.maotong.weibo.main.MovieDetailActivity;
import com.maotong.weibo.main.MovieModel;
import com.maotong.weibo.personal.LoginStatusEvent;
import com.maotong.weibo.utils.JsonResolveUtils;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class ComingFragment extends Fragment
{
	private RecyclerView mRecycler;
	private SwipeRefreshLayout mSwipe;
	private List<MovieModel> mComingList;
	private Oauth2AccessToken mAccessToken;

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
		mSwipe = (SwipeRefreshLayout) view.findViewById(R.id.id_coming_swipe);
		mComingList = WeiBoApplication.getInstance().getComingList();

		if (mComingList == null){
			initData();
		} else {
			setUpRecyclerView(mComingList);
		}

		mSwipe.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));

		mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				if (!mSwipe.isRefreshing()) {
					initData();
				}
			}
		});

		return view;

	}
	/**
	 * 登录或者注销之后，会发送LoinStatusEvent事件
	 * @param loginStatusEvent
	 */
	@Subscribe
	public void onEventMainThread(LoginStatusEvent loginStatusEvent){
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				initData();
			}
		});
	}
	@Subscribe
	public void onEventMainThread(final ComingMovieEvent comingMovieEvent){
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				setUpRecyclerView(comingMovieEvent.getMovieList());
			}
		});
	}

	private void setUpRecyclerView(final List<MovieModel> mComingList) {
		mSwipe.setRefreshing(false);
		ComingAdapter adapter = new ComingAdapter(getContext(), mComingList);
		mRecycler.setAdapter(adapter);
		mRecycler.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL , false));
		adapter.setOnItemClickListener(new ComingAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position , int isLike) {
				mComingList.get(position).setIsLike(isLike);
				//跳转到电影页面
				//跳转到电影页面
				Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable(MovieDetailActivity.MOVIE, mComingList.get(position));
				intent.putExtras(bundle);
				startActivity(intent);
			}

			@Override
			public void onItemLongClick(View view, int position) {

			}
		});
	}

	private void initData() {
		mComingList = new ArrayList<>();
		new Thread(new Runnable() {
			@Override
			public void run() {

				//根据登录状态获得不同的movie数据
				mAccessToken = AccessTokenKeeper.readAccessToken(getContext());
				if (mAccessToken != null && mAccessToken.isSessionValid()) {
					mComingList = new JsonResolveUtils().getComing(mAccessToken.getUid());
				} else {
					mComingList = new JsonResolveUtils().getComing();
				}

				EventBus.getDefault().post(new ComingMovieEvent(mComingList));
			}
		}).start();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	public void RecyclerNotify(){
		mRecycler.getAdapter().notifyDataSetChanged();
	}
}
