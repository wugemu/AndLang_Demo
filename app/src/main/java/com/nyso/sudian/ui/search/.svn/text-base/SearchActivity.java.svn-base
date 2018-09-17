package com.nyso.sudian.ui.search;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.util.ActivityUtil;
import com.example.test.andlang.util.ToastUtil;
import com.example.test.andlang.widget.editview.CleanableEditText;
import com.nyso.sudian.R;
import com.nyso.sudian.adapter.HistoryAdapter;
import com.nyso.sudian.ui.widget.PredicateLayout;
import com.nyso.sudian.util.BBCUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseLangActivity {

    @BindView(R.id.et_search)
    CleanableEditText etSearch;
    @BindView(R.id.pl_hot_search)
    PredicateLayout plHotSearch;
    @BindView(R.id.lv_list)
    ListView lvList;
    @BindView(R.id.btn_clear_history)
    Button btnClearHistory;
    @BindView(R.id.lv_key_list)
    ListView lvKeyList;
    private String key;
    private List<String> history;
    private List<TextView> tvList;
    private HistoryAdapter historyAdapter;
    private boolean isVisable;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        key = getIntent().getStringExtra("key");
        if (!BBCUtil.isEmpty(key)) {
            etSearch.setText(key);
            isVisable = true;
        }
        etSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSearch.requestFocus();
            }
        });
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (!"".equals(etSearch.getText().toString().trim())) {
                        if (etSearch.getText().toString().trim().contains(",")) {
                            ToastUtil.show(SearchActivity.this, "不能包含特殊字符");
                        } else {
                            //搜索操作
                            keySearch(etSearch.getText().toString().trim());
                        }

                    } else {
                        ToastUtil.show(SearchActivity.this, "搜索关键词不能为空");
                    }
                    return true;
                }
                return false;
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isVisable && !"".equals(String.valueOf(s).trim())) {
                    key = s.toString();
//                    searchP.getRelationProductList();

                    //动态显示关联商品列表
                } else {
                    isVisable = true;
                    goneAdapter();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        tvList = new ArrayList<TextView>();
        plHotSearch.setDividerCol(30);
//        searchP.initHotKey();
        initHotKey(new String[]{"花王","护手霜","面膜","大麦若耶","水光真","洗面奶"});
        //获取热搜词
        showKeyboard();
        key="花王";
        search();
    }


    private void showKeyboard(){

        etSearch.setFocusable(true);
        etSearch.setFocusableInTouchMode(true);
        etSearch.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           public void run() {
                               InputMethodManager inputManager =
                                       (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                               inputManager.showSoftInput(etSearch, 0);
                           }
                       },
                200);
    }
    private void search(){
        history= BBCUtil.getSearchHistory(this);
        Collections.reverse(history);
        historyAdapter = new HistoryAdapter(this, history, handler);
        if(historyAdapter.getCount()>0){
            btnClearHistory.setVisibility(View.VISIBLE);
        }else{
            btnClearHistory.setVisibility(View.GONE);
        }
        lvList.setAdapter(historyAdapter);

    }


    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void update(Observable o, Object arg) {

    }


    public void notifySearchAdapterUpdate(BaseAdapter adapter) {
        if (lvKeyList != null) {
            lvKeyList.setAdapter(adapter);
            lvKeyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String key = (String) parent.getAdapter().getItem(position);
                    keySearch(key);
                }
            });
            lvKeyList.setVisibility(View.VISIBLE);
        }
    }


    private View.OnClickListener hotClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            for (int i = 0; i < tvList.size(); i++) {
                TextView btn = tvList.get(i);
                if ((TextView) v == btn) {
                    btn.setSelected(true);
                    keySearch(((TextView) v).getText().toString());
                }
            }

        }
    };

    private void keySearch(String key) {
        btnClearHistory.setVisibility(View.VISIBLE);
        BBCUtil.addSearchRecord(this, key);
        history = BBCUtil.getSearchHistory(this);
        Collections.reverse(history);
        historyAdapter.setHistory(history);
        isVisable = false;
        etSearch.setText(key);
        etSearch.clearFocus();
        etSearch.setFocusable(false);
        goneAdapter();
        Intent i = new Intent(SearchActivity.this, SearchResultActivity.class);
        i.putExtra("searchKey", key);
        ActivityUtil.getInstance().start(SearchActivity.this, i);
        ActivityUtil.getInstance().finishActivity(SearchResultActivity.class);
        ActivityUtil.getInstance().exit(this);
    }

    public void goneAdapter() {
        lvKeyList.setVisibility(View.GONE);
    }

    public void initHotKey(String[] hotKey) {
        if (hotKey != null) {
            int padding1 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    4, SearchActivity.this.getResources().getDisplayMetrics());
            int padding2 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    8, SearchActivity.this.getResources().getDisplayMetrics());
            for (int i = 0; i < hotKey.length; i++) {
                String key = hotKey[i];
                TextView txt = new TextView(SearchActivity.this);
                txt.setBackgroundResource(R.drawable.tv_key_normal_bg);
                txt.setText(key);
                txt.setClickable(true);
                txt.setTextSize(TypedValue.COMPLEX_UNIT_PX, BBCUtil.sp2px(SearchActivity.this, 14));
                txt.setPadding(padding2, padding1, padding2, padding1);
                txt.setTextColor(Color.parseColor("#292929"));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                txt.setLayoutParams(params);
                txt.setTag(i);
                txt.setOnClickListener(hotClick);
                tvList.add(txt);
                plHotSearch.addView(txt);
            }
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    keySearch((String) msg.obj);
                    break;

            }
        }
    };

    public void setRelationProductList(List<String> productList) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.adapter_array_keys, R.id.tv_key, productList);
        notifySearchAdapterUpdate(adapter);
    }



    @OnClick({R.id.tv_cancel, R.id.btn_clear_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                onBackPressed();
                break;
            case R.id.btn_clear_history:
                BBCUtil.clearAllRecoder(this);
                historyAdapter.clearHistory();
                BBCUtil.setListViewHeightBasedOnChildren(lvList);
                btnClearHistory.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (etSearch != null) {
            etSearch.setFocusable(true);
            etSearch.setFocusableInTouchMode(true);
            etSearch.requestFocus();
            etSearch.findFocus();
        }
    }
}
