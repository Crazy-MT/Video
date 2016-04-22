package org.androidpn.client;
 
import java.util.ArrayList;
import java.util.List;

import org.androidpn.demoapp.R;
import org.litepal.crud.DataSupport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class NotificationHistoryActivity extends Activity {
	
	private ListView mListView;
	private NotificationHistoryAdapter mAdapter;
	private List<NotificationHistory> mList = new ArrayList<NotificationHistory>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.notification_history);
		mList = DataSupport.findAll(NotificationHistory.class);
		mListView = (ListView) findViewById(R.id.list_view);
		mAdapter = new NotificationHistoryAdapter(this, 0, mList);
		mListView.setAdapter(mAdapter);
		registerForContextMenu(mListView); 
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				NotificationHistory history = mList.get(index);
				Intent intent = new Intent(NotificationHistoryActivity.this,
	                    NotificationDetailsActivity.class); 
	            intent.putExtra(Constants.NOTIFICATION_API_KEY, history.getApiKey());
	            intent.putExtra(Constants.NOTIFICATION_TITLE, history.getTitle());
	            intent.putExtra(Constants.NOTIFICATION_MESSAGE, history.getMessage());
	            intent.putExtra(Constants.NOTIFICATION_URI, history.getUri());
	            intent.putExtra(Constants.NOTIFICATION_IMAGE_URL, history.getImageUrl());
				startActivity(intent);
			}
		});
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, 0, 0, "Remove");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if(item.getItemId() == 0){
			AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
			int index = menuInfo.position;
			NotificationHistory history = mList.get(index);
			history.delete();
			mList.remove(index);
			mAdapter.notifyDataSetChanged();
		}
		return super.onContextItemSelected(item);
	}

	class NotificationHistoryAdapter extends ArrayAdapter<NotificationHistory>{

		public NotificationHistoryAdapter(Context context, int resource,
				List<NotificationHistory> objects) {
			super(context, resource, objects); 
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			NotificationHistory history = getItem(position);
			View view;
			if(convertView == null){
				view = LayoutInflater.from(getContext()).inflate(R.layout.notification_history_item, null);
			} else {
				view = convertView;
			}
			
			TextView titleTextView = (TextView) view.findViewById(R.id.tv_title);
			TextView timeTextView = (TextView) view.findViewById(R.id.tv_time);
			titleTextView.setText(history.getTitle());
			timeTextView.setText(history.getTime());
			return view;
		}
		
	}
	
}
