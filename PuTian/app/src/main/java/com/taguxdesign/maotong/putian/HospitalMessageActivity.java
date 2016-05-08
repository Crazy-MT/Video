package com.taguxdesign.maotong.putian;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;

import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class HospitalMessageActivity extends AppCompatActivity {

    public static String HOSPITAL = "hospital";
    private List<Hospital> hospitals = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_message);
        mContext = this;
        Bundle bundle = getIntent().getBundleExtra(HOSPITAL);
        hospitals = bundle.getParcelableArrayList(HOSPITAL);

        for (Hospital hospital : hospitals) {
            Log.e("tag", "onCreate: hospitals" + hospital.toString());
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.id_hospital_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration
                (mContext, DividerItemDecoration.VERTICAL_LIST));

        mRecyclerView.setAdapter(new CommonAdapter<Hospital>(mContext, R.layout.hospital_item, hospitals) {

            @Override
            public void convert(ViewHolder holder, Hospital hospital) {
                holder.setText(R.id.id_hospital_name, hospital.getHospitalName());
                if (!TextUtils.isEmpty(hospital.getPhone())) {
                    holder.setText(R.id.id_hospital_phone, spitFromJsonArray(hospital.getPhone()));
                } else {
                    holder.setText(R.id.id_hospital_phone, "暂无");
                }
                if (!TextUtils.isEmpty(hospital.getWebSite())) {
                    holder.setText(R.id.id_hospital_website, spitFromJsonArray(hospital.getWebSite()));
                } else {
                    holder.setText(R.id.id_hospital_website, "暂无");
                }
                if (!TextUtils.isEmpty(hospital.getLocation())) {
                    holder.setText(R.id.id_hospital_location, spitFromJsonArray(hospital.getLocation()));
                } else {
                    holder.setText(R.id.id_hospital_location, "暂无");
                }
                if (!TextUtils.isEmpty(hospital.getQq())) {
                    holder.setText(R.id.id_hospital_qq, spitFromJsonArray(hospital.getQq()));
                } else {
                    holder.setText(R.id.id_hospital_qq, "暂无");
                }
                if (!TextUtils.isEmpty(hospital.getWx())) {
                    holder.setText(R.id.id_hospital_wx, spitFromJsonArray(hospital.getWx()));
                } else {
                    holder.setText(R.id.id_hospital_wx, "暂无");
                }
                if (!TextUtils.isEmpty(hospital.getWxN())) {
                    holder.setText(R.id.id_hospital_wxn, spitFromJsonArray(hospital.getWxN()));
                } else {
                    holder.setText(R.id.id_hospital_wxn, "暂无");
                }
                if (!TextUtils.isEmpty(hospital.getEvidence())) {
                    holder.setText(R.id.id_hospital_evidence, spitFromJsonArray(hospital.getEvidence()));
                } else {
                    holder.setText(R.id.id_hospital_evidence, "暂无");
                }
            }
        });


    }

    private String spitFromJsonArray(String json) {
        List<String> stringList = new ArrayList<String>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                stringList.add(jsonArray.get(i).toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String stringArr = "";
        for (String string : stringList) {
            stringArr += string;
            stringArr += ",";
        }

        return stringArr;
    }
}
