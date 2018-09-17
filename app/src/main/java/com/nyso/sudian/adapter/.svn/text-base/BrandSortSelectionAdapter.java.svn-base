package com.nyso.sudian.adapter;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test.andlang.util.ActivityUtil;
import com.example.test.andlang.util.imageload.ImageLoadUtils;
import com.nyso.sudian.R;
import com.nyso.sudian.model.local.SortModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2017/3/20.
 */

/**
 * listview适配器,设置特殊item(本例中为蓝色背景的首字母栏)
 */
public class BrandSortSelectionAdapter extends BaseAdapter {

    private Activity context;
    private List<SortModel> datas;
    private ListView listView;
    private int type;   // 0-默认的，不显示销售数量，1-显示销售数量
//		public Map<String, Integer> maps;

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public BrandSortSelectionAdapter(Activity context,List<SortModel> datas) {
        this(context,datas,0);
    }

    public BrandSortSelectionAdapter(Activity context, List<SortModel> datas,int type) {
        this.context = context;
        this.type = type;
        if(datas!=null){
            this.datas=datas;
        }else{
            this.datas=new ArrayList<SortModel>();
        }

//
//			sortLetter(datas);
    }

    public void setData(List<SortModel> datas){
        this.datas=datas;
        notifyDataSetChanged();
    }

    /**
     * 获取需要顶部悬浮显示的view
     */
    public View getPinnedSectionView(int position) {
        ViewGroup view = (ViewGroup) getView(position, null, listView);
        View vAlpha = view.getChildAt(0);
        return vAlpha;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public SortModel getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ViewHolder holder;
        if (convertView == null) {
            view = View.inflate(context, R.layout.listview_brand, null);
            holder = new ViewHolder();
            holder.tvName = (TextView) view.findViewById(R.id.tv_brand_name);
            holder.tvClass = (TextView) view.findViewById(R.id.catalog);
            holder.llClass = (LinearLayout) view.findViewById(R.id.ll_catalog);
            holder.logo= (ImageView) view.findViewById(R.id.iv_brand_logo);
            holder.line=view.findViewById(R.id.view_line);
            holder.llBrand= (LinearLayout) view.findViewById(R.id.ll_brand);
            holder.llBrandSale = view.findViewById(R.id.ll_brand_sale);
            holder.tvBrandSale = view.findViewById(R.id.tv_brand_sale);
            view.setTag(holder);
        }else{
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        final SortModel model=getItem(position);
        if("1".equals(model.getSortLetters())){
            //热门品牌数据
            if(position==0){
                //显示热门品牌
                holder.llClass.setVisibility(View.VISIBLE);
            }else{
                holder.llClass.setVisibility(View.GONE);
                holder.line.setVisibility(View.VISIBLE);
            }
            holder.tvClass.setText("热门品牌");
        }else{

            //普通品牌
            //根据position获取分类的首字母的Char ascii值
            int section = getSectionForPosition(position);
            //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
            if(position == getPositionForSection(section)){
                holder.llClass.setVisibility(View.VISIBLE);
                holder.tvClass.setText(model.getSortLetters());
            }else{
                holder.line.setVisibility(View.VISIBLE);
                holder.llClass.setVisibility(View.GONE);
            }
        }
        ImageLoadUtils.doLoadCircleImageUrl(holder.logo,model.getBrandBean().getBrandLogo());
        holder.tvName.setText(model.getBrandBean().getBrandName());
        if(type == 1) {
            // 显示销售数量
            holder.llBrandSale.setVisibility(View.VISIBLE);
            holder.tvBrandSale.setText("99");   // 默认写死的，后面根据后台来
        } else {
            holder.llBrandSale.setVisibility(View.GONE);
        }
        /*holder.llBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, BrandProductActivity.class);
                intent.putExtra("brand",model.getBrandBean());
                intent.putExtra("type","1");
                ActivityUtil.getInstance().start(context,intent);
            }
        });*/
        return view;
    }
    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        if(!TextUtils.isEmpty(datas.get(position).getSortLetters())){
            return datas.get(position).getSortLetters().charAt(0);
        }
        return -1;
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = datas.get(i).getSortLetters();
            if(!TextUtils.isEmpty(sortStr)){
                char firstChar= sortStr.toUpperCase().charAt(0);
                if (firstChar == section) {
                    return i;
                }
            }else{
                if(section==-1){
                    return 0;
                }
            }
        }
        return -1;
    }
//		private void sortLetter(ArrayList<MyData> datas) {
//			Collections.sort(datas, new Comparator<MyData>() {
//				@Override
//				public int compare(MyData lhs, MyData rhs) {
//					return lhs.firstLetter.compareTo(rhs.firstLetter);
//				}
//			});
//
//			maps = new HashMap<String, Integer>();
//			for (int i = 0; i < datas.size(); i++) {
//				if (!maps.containsKey(datas.get(i).firstLetter)) {
//					maps.put(datas.get(i).firstLetter, i);
//				}
//			}
//		}

    class ViewHolder{
        TextView tvName,tvClass,tvBrandSale;
        ImageView logo;
        LinearLayout llClass,llBrand,llBrandSale;
        View line;
    }

}


