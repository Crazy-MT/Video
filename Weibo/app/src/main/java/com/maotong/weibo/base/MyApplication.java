package com.maotong.weibo.base;
 


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class MyApplication extends  Application  {

	private static MyApplication myApplication = null;
	private String BASEURL;
	private boolean isUser ;
	private int userId   ;
	//private d_User dUser  ;
	private SharedPreferences sp ; 
	private static final String REM_PW = "rem_pw" ; 
	private static final String ACCOUNT = "account";
	private static final String PASSWORD = "password";
	protected static final String AUTO_LOGIN = "auto_login";
	public static final String UploadFaceImage = "http://192.168.31.158:8080/freemeal/UploadFaceImage";

	public static MyApplication getMyApplication() {
		return myApplication;
	}
	
	private MyApplication(){
		
	}

	 /*http://freelmeal.sinaapp.com/
	  *http://freelmeal.sinaapp.com/uploads/
      *http://192.168.31.158:8080/freemeal/uploads/
	  */
	
	@Override
	public void onCreate() {
		super.onCreate();
		myApplication = this;
		setBASEURL("http://192.168.31.158:8080");//http://192.168.31.158:8080/freemeal/GetAllActivity
		setUser(false);
		setUserId(0);
		//dUser = new d_User();
		sp = getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		if(sp.getBoolean(AUTO_LOGIN, true)){
			//Toast.makeText(getApplicationContext(), sp.getBoolean(REM_PW, false)+"",1).show();
			login();
		}
		
		
	}
	private void login() { 
		new NewsAsyncTask().execute();
			
	}
	
	class NewsAsyncTask extends AsyncTask<Void, Void, Boolean>{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		@Override
		protected Boolean doInBackground(Void... arg0) {
			return true;
			/*return new com.free.utils.JsonResolveUtils(getApplicationContext())
			.getLogin(sp.getString(ACCOUNT, ACCOUNT), sp.getString(PASSWORD, PASSWORD));*/
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if(result){
				// Start the service
				/*ServiceManager serviceManager = new ServiceManager(
						getApplicationContext());
				serviceManager.setNotificationIcon(R.drawable.ic_launcher);
				serviceManager.startService();
				serviceManager.setAlias("毛蛋");*/
			}
		}
	}
	
	/*public d_User getdUser() {
		return dUser;
	}

	public void setdUser(d_User dUser) {
		this.dUser = dUser;
	}*/

	public boolean isUser() {
		return isUser;
	}

	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}

	public String getBASEURL() {
		return BASEURL;
	}

	public void setBASEURL(String bASEURL) {
		BASEURL = bASEURL;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
