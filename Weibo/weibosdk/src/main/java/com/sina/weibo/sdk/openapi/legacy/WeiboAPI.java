package com.sina.weibo.sdk.openapi.legacy;

import android.os.Handler;
import android.os.Message;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboParameters;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.RequestListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 微博 API的基类，每个接口类都继承了此抽象类
 * @author xiaowei6@staff.sina.com.cn
 *
 */
public abstract class WeiboAPI {
    /**
     * 访问微博服务接口的地址
     */
	public static final String API_SERVER = "https://api.weibo.com/2";
	/**
	 * post请求方式
	 */
	public static final String HTTPMETHOD_POST = "POST";
	/**
	 * get请求方式
	 */
	public static final String HTTPMETHOD_GET = "GET";
	protected Oauth2AccessToken mAccessToken;
	private String accessToken;

	/** 用于转发回调函数的消息 */
	private static final int MSG_ON_COMPLETE            = 1;
	private static final int MSG_ON_COMPLETE_FOR_BINARY = 2;
	private static final int MSG_ON_IOEXCEPTION         = 3;
	private static final int MSG_ON_ERROR               = 4;
	/** 异步请求回调接口 */
	private RequestListener mRequestListener;

	/**
	 * 构造函数，使用各个API接口提供的服务前必须先获取Oauth2AccessToken
	 * @param accesssToken Oauth2AccessToken
	 */
	public WeiboAPI(Oauth2AccessToken oauth2AccessToken){
	    this.mAccessToken=oauth2AccessToken;
	    if(mAccessToken!=null){
	        accessToken=mAccessToken.getToken();
	    }
	   
	}
	public enum FEATURE {
		ALL, ORIGINAL, PICTURE, VIDEO, MUSICE
	}

	public enum SRC_FILTER {
		ALL, WEIBO, WEIQUN
	}

	public enum TYPE_FILTER {
		ALL, ORIGAL
	}

	public enum AUTHOR_FILTER {
		ALL, ATTENTIONS, STRANGER
	}

	public enum TYPE {
		STATUSES, COMMENTS, MESSAGE
	}

	public enum EMOTION_TYPE {
		FACE, ANI, CARTOON
	}

	public enum LANGUAGE {
		cnname, twname
	}

	public enum SCHOOL_TYPE {
		COLLEGE, SENIOR, TECHNICAL, JUNIOR, PRIMARY
	}

	public enum CAPITAL {
		A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z
	}

	public enum FRIEND_TYPE {
		ATTENTIONS, FELLOWS
	}

	public enum RANGE {
		ATTENTIONS, ATTENTION_TAGS, ALL
	}

	public enum USER_CATEGORY {
		DEFAULT, ent, hk_famous, model, cooking, sports, finance, tech, singer, writer, moderator, medium, stockplayer
	}

	public enum STATUSES_TYPE {
		ENTERTAINMENT, FUNNY, BEAUTY, VIDEO, CONSTELLATION, LOVELY, FASHION, CARS, CATE, MUSIC
	}

	public enum COUNT_TYPE {
	    /**
	     * 新微博数
	     */
		STATUS, 
		/**
		 * 新粉丝数
		 */
		FOLLOWER, 
		/**
		 * 新评论数
		 */
		CMT, 
		/**
		 * 新私信数
		 */
		DM, 
		/**
		 * 新提及我的微博数
		 */
		MENTION_STATUS, 
		/**
		 * 新提及我的评论数
		 */
		MENTION_CMT
	}
	/**
	 * 分类
	 * @author xiaowei6@staff.sina.com.cn
	 *
	 */
	public enum SORT {
	    Oauth2AccessToken, 
	    SORT_AROUND
	}

	public enum SORT2 {
		SORT_BY_TIME, SORT_BY_HOT
	}
	
	public enum SORT3 {
		SORT_BY_TIME, SORT_BY_DISTENCE
	}
	
	public enum COMMENTS_TYPE {
		NONE, CUR_STATUSES, ORIGAL_STATUSES, BOTH
	}
	
	protected void request( final String url, final WeiboParameters params,
			final String httpMethod,RequestListener listener) {

		mRequestListener = listener;

		params.add("access_token", accessToken);
		AsyncWeiboRunner.request(url, params, httpMethod, mInternalListener);
	}

	/**
	 * 该 Handler 用于将后台线程回调转发到 UI 线程。
	 */
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (null == mRequestListener) {
				return;
			}

			switch (msg.what) {
				case MSG_ON_COMPLETE:
					mRequestListener.onComplete((String)msg.obj);
					break;

				case MSG_ON_COMPLETE_FOR_BINARY:
					mRequestListener.onComplete4binary((ByteArrayOutputStream)msg.obj);
					break;

				case MSG_ON_IOEXCEPTION:
					mRequestListener.onIOException((IOException)msg.obj);
					break;

				case MSG_ON_ERROR:
					mRequestListener.onError((WeiboException)msg.obj);
					break;

				default:
					break;
			}
		}
	};

	/**
	 * 请注意：默认情况下，{@link RequestListener} 对应的回调是运行在后台线程中的，
	 *        因此，需要使用 Handler 来配合更新 UI。
	 */
	private RequestListener mInternalListener = new RequestListener() {
		@Override
		public void onComplete(String response) {
			mHandler.obtainMessage(MSG_ON_COMPLETE, response).sendToTarget();
		}

		@Override
		public void onComplete4binary(ByteArrayOutputStream responseOS) {
			mHandler.obtainMessage(MSG_ON_COMPLETE_FOR_BINARY, responseOS).sendToTarget();
		}

		@Override
		public void onIOException(IOException e) {
			mHandler.obtainMessage(MSG_ON_IOEXCEPTION, e).sendToTarget();
		}

		@Override
		public void onError(WeiboException e) {
			mHandler.obtainMessage(MSG_ON_ERROR, e).sendToTarget();
		}
	};
}
