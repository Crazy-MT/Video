package com.taguxdesign.maotong.putian;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<City> citys = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private SearchView mSearchView;
    private Context mContext;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setUpRecyclerView();
        }


    };

    private void setUpRecyclerView() {
        mRecyclerView.setAdapter(new CommonAdapter<City>(mContext, R.layout.item, citys) {


            @Override
            public void convert(final ViewHolder holder, City city) {
                Log.e("tag", "convert: city.getCityName()" + city.getCityName());
                holder.setText(R.id.id_text, city.getCityName());
                holder.setOnClickListener(R.id.id_item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startHospitalActivity(holder.getPosition());
                        //Toast.makeText(MainActivity.this, holder.getPosition()+"", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void startHospitalActivity(int index) {
        Intent intent = new Intent(MainActivity.this, HospitalMessageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(HospitalMessageActivity.HOSPITAL, (ArrayList<? extends Parcelable>) citys.get(index).getHospitals());
        intent.putExtra(HospitalMessageActivity.HOSPITAL, bundle);
        startActivity(intent);
    }

    private void startHospitalActivity(City city) {
        Intent intent = new Intent(MainActivity.this, HospitalMessageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(HospitalMessageActivity.HOSPITAL, (ArrayList<? extends Parcelable>) city.getHospitals());
        intent.putExtra(HospitalMessageActivity.HOSPITAL, bundle);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mSearchView = (SearchView) findViewById(R.id.id_search);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration
                (mContext, DividerItemDecoration.VERTICAL_LIST));


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    parserJson();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                boolean hasHospital = false;
                for (City city : citys) {
                    if (query.equals(city.getCityName()) || city.getCityName().contains(query)) {
                        startHospitalActivity(city);
                        hasHospital = true;
                    } else {
                        for (Hospital hospital : city.getHospitals()) {
                            if (query.equals(hospital.getHospitalName()) || hospital.getHospitalName().contains(query)) {
                                //startHospitalActivity(city);
                                hasHospital = true;
                            }
                        }
                    }
                }
                if (!hasHospital) {
                    Toast.makeText(MainActivity.this, "暂时未收录此医院", Toast.LENGTH_SHORT).show();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void parserJson() throws JSONException {
        JSONObject jsonObject = new JSONObject(Json.json.trim());
        Iterator<String> keys = jsonObject.keys();
        City city = null;
        while (keys.hasNext()) {
            String key = keys.next();
            if (jsonObject.get(key) instanceof JSONObject) {
                city = new City();
                city.setCityName(key);
                citys.add(city);
            }
        }

        for (City cityHospital : citys) {
            JSONObject hospitalObject = jsonObject.getJSONObject(cityHospital.getCityName());

            Iterator<String> hospitalNameKeys = hospitalObject.keys();
            Hospital hospital = null;
            List<Hospital> mHospitals = new ArrayList<>();
            while (hospitalNameKeys.hasNext()) {
                String key = hospitalNameKeys.next();
                if (hospitalObject.get(key) instanceof JSONObject) {
                    //得到名字
                    hospital = new Hospital();
                    hospital.setHospitalName(key);

                    JSONObject data = hospitalObject.getJSONObject(key);
                    //Log.e("tag", "parserJson: " + data.toString());
                    JSONArray phone = null;
                    JSONArray webSite = null;
                    JSONArray location = null;
                    JSONArray qq = null;
                    JSONArray wx = null;
                    JSONArray wxN = null;
                    JSONArray evidence = null;
                    if (data.has("电话")) {
                        phone = data.getJSONArray("电话");
                        hospital.setPhone(phone.toString());
                    }
                    if (data.has("网址")) {
                        webSite = data.getJSONArray("网址");
                        hospital.setWebSite(webSite.toString());
                    }
                    if (data.has("地址")) {
                        location = data.getJSONArray("地址");
                        hospital.setLocation(location.toString());
                    }
                    if (data.has("QQ")) {
                        qq = data.getJSONArray("QQ");
                        hospital.setQq(qq.toString());
                    }
                    if (data.has("微信")) {
                        wx = data.getJSONArray("微信");
                        hospital.setWx(wx.toString());
                    }
                    if (data.has("微信公众号")) {
                        wxN = data.getJSONArray("微信公众号");
                        hospital.setWxN(wxN.toString());
                    }
                    if (data.has("证据")) {
                        evidence = data.getJSONArray("证据");
                        hospital.setEvidence(evidence.toString());
                    }
                    mHospitals.add(hospital);
                }
            }

            cityHospital.setHospitals(mHospitals);

        }

        for (City city1 : citys) {
            Log.e("tag", "parserJson: " + city1.toString());
        }
        mHandler.sendEmptyMessage(0);

    }
}
