package com.nyso.sudian.ui.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.test.andlang.andlangutil.BaseLangFragment;
import com.nyso.sudian.R;
import com.nyso.sudian.adapter.AHBrandAdapter;
import com.nyso.sudian.adapter.BrandSortSelectionAdapter;
import com.nyso.sudian.model.api.Brand;
import com.nyso.sudian.model.local.BrandModel;
import com.nyso.sudian.model.local.SortModel;
import com.nyso.sudian.presenter.BrandPresenter;
import com.nyso.sudian.ui.widget.CustomGridView;
import com.nyso.sudian.ui.widget.PinnedSectionListView;
import com.nyso.sudian.ui.widget.pysortlist.SideBar;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Observable;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class BrandFragment extends BaseLangFragment<BrandPresenter> {

    @BindView(R.id.fl_content)
    FrameLayout frameLayout;
    @BindView(R.id.sidrbar)
    SideBar sidrbar;
    @BindView(R.id.lv_brand_list)
    PinnedSectionListView pinnedSectionListView;
    private BrandSortSelectionAdapter brandSortAdapter;
    private List<SortModel> sortModels = new ArrayList<SortModel>();
    public static final String[] keys = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S"
            , "T", "U", "V", "W", "X", "Y", "Z", "#"};
    private View headView;
    private CustomGridView gvAuth,gvHot;
    private AHBrandAdapter authAdapter,hotAdapter;
    private boolean isMore;//是否展开更多授权商品
    private ImageView ivArrow;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_brand;
    }

    @Override
    public void initView() {
        headView= LayoutInflater.from(activity).inflate(R.layout.head_brand_list,null);
        gvAuth= (CustomGridView) headView.findViewById(R.id.gv_auth_brand);
        gvHot= (CustomGridView) headView.findViewById(R.id.gv_hot_brand);
        ivArrow= (ImageView) headView.findViewById(R.id.iv_arrow);
        headView.findViewById(R.id.ll_auth_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isMore){
                    authAdapter.setCount(0);
                    isMore=true;
                    ivArrow.setImageResource(R.mipmap.arrow_up);
                }else {
                    authAdapter.setCount(9);
                    isMore=false;
                    ivArrow.setImageResource(R.mipmap.arrow_down);
                }
            }
        });
        pinnedSectionListView.addHeaderView(headView);
        sidrbar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                int position = brandSortAdapter.getPositionForSection(s.charAt(0));
                if (position != 0) {
                    pinnedSectionListView.setSelection(position);
                }
            }
        });
    }

    @Override
    public void initPresenter() {
        presenter = new BrandPresenter(this,activity, BrandModel.class);
    }

    @Override
    public void initData() {

    }

    @Override
    public void update(Observable o, Object arg) {
        if(BrandPresenter.TAG_BRAND_SUCCESS.equals(arg) && o instanceof BrandModel) {
            BrandModel brandModel = (BrandModel) o;
            authAdapter=new AHBrandAdapter(activity,brandModel.getAuthBrandList());
            gvAuth.setAdapter(authAdapter);
            hotAdapter=new AHBrandAdapter(activity,brandModel.getHotBrandList());
            gvHot.setAdapter(hotAdapter);
            hotAdapter.setCount(0);
            LinkedHashMap<String, List<Brand>> map = brandModel.getBrandMap();
            List<String> words = new ArrayList<String>();
            for (String key : keys) {
                List<Brand> brands = map.get(key);
                if (brands != null && brands.size() > 0) {
                    words.add(key);
                    for (Brand brand : brands) {
                        SortModel sortModel = new SortModel();
                        sortModel.setBrandBean(brand);
                        sortModel.setSortLetters(key);
                        sortModels.add(sortModel);
                    }
                }
            }
            sidrbar.setWords(words);
            brandSortAdapter = new BrandSortSelectionAdapter(activity, sortModels);
            brandSortAdapter.setListView(pinnedSectionListView);
            pinnedSectionListView.setAdapter(brandSortAdapter);
            sidrbar.invalidate();
        }
    }

}
