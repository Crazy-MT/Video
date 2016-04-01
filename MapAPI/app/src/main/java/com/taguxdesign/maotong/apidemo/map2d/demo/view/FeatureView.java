package com.taguxdesign.maotong.apidemo.map2d.demo.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.taguxdesign.maotong.apidemo.map2d.demo.util.Constants;
import com.taguxdesign.maotong.apidemo.map2d.demo.util.ToastUtil;
import com.taguxdesign.maotong.mapapi.R;

public final class FeatureView extends FrameLayout {

	public FeatureView(Context context) {
		super(context);

		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.feature, this);
	}

	public synchronized void setTitleId(int titleId) {
		((TextView) (findViewById(R.id.title))).setText(titleId);
	}

	public synchronized void setDescriptionId(int descriptionId) {
		((TextView) (findViewById(R.id.description))).setText(descriptionId);
	}

}
