package com.nyso.sudian.ui.brand;

import android.widget.FrameLayout;

import com.example.test.andlang.andlangutil.BaseLangFragment;
import com.nyso.sudian.R;
import com.nyso.sudian.adapter.BrandSortSelectionAdapter;
import com.nyso.sudian.model.api.Brand;
import com.nyso.sudian.model.local.BrandListModel;
import com.nyso.sudian.model.local.SortModel;
import com.nyso.sudian.presenter.BrandListPresenter;
import com.nyso.sudian.ui.widget.PinnedSectionListView;
import com.nyso.sudian.ui.widget.pysortlist.SideBar;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Observable;

import butterknife.BindView;

/**
 * Created by Bill56 on 2018-9-10.
 */

public class BrandListFragment extends BaseLangFragment<BrandListPresenter> {

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

    @Override
    public int getLayoutId() {
        return R.layout.fragment_brand_list;
    }

    @Override
    public void initView() {
        presenter = new BrandListPresenter(this,activity,BrandListModel.class);
    }

    @Override
    public void initPresenter() {
        presenter = new BrandListPresenter(this,activity, BrandListModel.class);
    }

    @Override
    public void initData() {

    }

    @Override
    public void update(Observable o, Object arg) {
        if(BrandListPresenter.TAG_BRAND_LIST_SUCCESS.equals(arg) && o instanceof BrandListModel) {
            BrandListModel model = (BrandListModel) o;
            LinkedHashMap<String, List<Brand>> map = model.getBrandMap();
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
            brandSortAdapter = new BrandSortSelectionAdapter(activity, sortModels,1);// 这里需要显示销售数量
            brandSortAdapter.setListView(pinnedSectionListView);
            pinnedSectionListView.setAdapter(brandSortAdapter);
            sidrbar.invalidate();
        }
    }
}
