/*
 * Copyright (C) 2010-2013 The SINA WEIBO Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.maotong.weibo.openapi;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.maotong.weibo.R;


/**
 * 该类是所有 OpenAPI Demo 的入口 Activity。
 * 
 * @author SINA
 * @since 2013-11-05
 */
public class WBOpenAPIActivity extends Activity implements OnItemClickListener {
    /** OpenAPI DEMO 的包名（请区分应用程序包名）*/
    private static final String DEST_ACTIVITY_PACKAGE_NAME = "com.maotong.weibo.openapi";
    /** 该 MAP 用于存放 OpenAPI 名称以及对应的 DEMO Activity 名 */
    private static final LinkedHashMap<String, String> sAPIList = 
            new LinkedHashMap<String, String>();
    
    /**
     * 初始化用于存放 OpenAPI 名称以及对应的 DEMO Activity 名的 MAP。
     */
    static {
        sAPIList.put("InviteAPI", "WBInviteAPIActivity");
        sAPIList.put("LogoutAPI", "WBLogoutAPIActivity");
        sAPIList.put("PublicTimeLine", "WBPublicAPIActivity");
    }
    
    /** UI 元素：ListView */
    private ListView mApiListView;
    
    /**
     * @see {@link Activity#onCreate}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_api);
        
        mApiListView = (ListView)findViewById(R.id.api_list);
        mApiListView.setAdapter(new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, getAPINameList()));
        mApiListView.setOnItemClickListener(this);
    }
    
    /**
     * @see {@link OnItemClickListener#onItemClick}
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (view instanceof TextView) {
            String className = sAPIList.get(((TextView)view).getText().toString());
            
            Intent intent = new Intent();
            intent.setClassName(getPackageName(), DEST_ACTIVITY_PACKAGE_NAME + "." + className);
            try {
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取 OpenAPI 名称列表。
     * 
     * @return OpenAPI 名称列表
     */
    private ArrayList<String> getAPINameList() {
        ArrayList<String> nameList = new ArrayList<String>();
        Set<String> nameSet = sAPIList.keySet();
        for (String name : nameSet) {
            nameList.add(name);
        }
        
        return nameList;
    }
}
