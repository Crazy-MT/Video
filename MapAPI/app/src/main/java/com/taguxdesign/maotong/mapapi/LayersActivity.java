package com.taguxdesign.maotong.mapapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyTrafficStyle;


public class LayersActivity extends AppCompatActivity implements View.OnClickListener,  AdapterView.OnItemSelectedListener {

    private AMap aMap;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layers);

        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        init();

    }

    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }

        CheckBox traffic = (CheckBox) findViewById(R.id.traffic);
        traffic.setOnClickListener(this);

        Spinner spinner = (Spinner) findViewById(R.id.layers_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.layers_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        MyTrafficStyle myTrafficStyle = new MyTrafficStyle();
        myTrafficStyle.setSeriousCongestedColor(0xff92000a);
        myTrafficStyle.setCongestedColor(0xffea0312);
        myTrafficStyle.setSlowColor(0xffff7508);
        myTrafficStyle.setSmoothColor(0xff00a209);
        aMap.setMyTrafficStyle(myTrafficStyle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.traffic) {
            aMap.setTrafficEnabled(((CheckBox) v).isChecked());// 显示实时交通状况
        }
    }



    /**
     * 选择矢量地图/卫星地图/夜景地图事件的响应
     */
    private void setLayer(String layerName) {
        if (layerName.equals(getString(R.string.normal))) {
            aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 矢量地图模式
        } else if (layerName.equals(getString(R.string.satellite))) {
            aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 卫星地图模式
        } else if (layerName.equals(getString(R.string.night_mode))) {
            aMap.setMapType(AMap.MAP_TYPE_NIGHT);//夜景地图模式
        } else if (layerName.equals(getString(R.string.navi_mode))) {
            //aMap.setMapType(AMap.MAP_TYPE_NAVI);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (aMap != null) {
            setLayer((String) parent.getItemAtPosition(position));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
