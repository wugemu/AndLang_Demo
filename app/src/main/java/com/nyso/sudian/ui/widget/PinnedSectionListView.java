package com.nyso.sudian.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.nyso.sudian.R;
import com.nyso.sudian.adapter.BrandSortSelectionAdapter;
import com.nyso.sudian.model.local.SortModel;


/**
 * 
 * TODO 
 * @ClassName: PinnedSectionListView  
 * @author ChenYuanming
 * @date 2015年1月5日 下午3:20:38 
 * @version V1.0
 */
public class PinnedSectionListView extends ListView {
	String Tag = getClass().getSimpleName();

	private Context context;
	private BrandSortSelectionAdapter adapter;
	private View currentPinnedHeader;
	private int mTranslateY;
	private boolean isPinnedHeaderShown;

	public PinnedSectionListView(Context context) {
		super(context);
		this.context = context;
		initView();
	}

	public PinnedSectionListView(Context context, AttributeSet attrs) {
		super(context,attrs);
		this.context = context;
		initView();
	}

	public PinnedSectionListView(Context context, AttributeSet attrs, int defStyle) {
		super(context,attrs,defStyle);
		this.context = context;
		initView();
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		super.setAdapter(adapter);
		this.adapter= (BrandSortSelectionAdapter) adapter;
		this.setOnScrollListener(mOnScrollListener);
	}

	private void initView() {
		isPinnedHeaderShown = false;
//		adapter = new BrandSortSelectionAdapter((Activity) context);
//		adapter.setListView(this);
//		this.setAdapter(adapter);

	}

	/**
	 * 显示顶部悬浮框
	 */
	private synchronized void createPinnedHeader(int position) {

		View pinnedView =  adapter.getPinnedSectionView(position);

		LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) pinnedView.getLayoutParams();
		if (layoutParams == null) {
			layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		}

		int heightMode = View.MeasureSpec.getMode(layoutParams.height);
		int heightSize = View.MeasureSpec.getSize(layoutParams.height);

		if (heightMode == View.MeasureSpec.UNSPECIFIED)
			heightMode = View.MeasureSpec.EXACTLY;

		int maxHeight = getHeight() - this.getListPaddingTop() - this.getListPaddingBottom();
		if (heightSize > maxHeight)
			heightSize = maxHeight;

		int ws = View.MeasureSpec.makeMeasureSpec(getWidth() - this.getListPaddingLeft() - this.getListPaddingRight(), View.MeasureSpec.EXACTLY);
		int hs = View.MeasureSpec.makeMeasureSpec(heightSize, heightMode);
		pinnedView.measure(ws, hs);
		pinnedView.layout(0, 0, pinnedView.getMeasuredWidth(), pinnedView.getMeasuredHeight());

		currentPinnedHeader = pinnedView;
	}

	private String lastGroupName = "";
	private OnScrollListener mOnScrollListener = new OnScrollListener() {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
// 				createPinnedHeader(view.getFirstVisiblePosition());
				Log.d(Tag, "stop at "+view.getFirstVisiblePosition());
//				invalidate();
			}
		}

		/**
		 * 滚动时动态监测是否需要利用新顶部悬浮框顶替当前顶部悬浮框
		 */
		@Override
		public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
			if(firstVisibleItem>0){
				firstVisibleItem--;
				// 屏幕中可以看到的顶部第一条
				SortModel myData = adapter.getItem(firstVisibleItem);
				// 屏幕中可以看到的顶部第二条
				SortModel nextData = adapter.getItem(firstVisibleItem + 1);

				// 对比第一二条数据
				if (!myData.getSortLetters().equals(nextData.getSortLetters())) {
					// 不同时即出现两个悬浮框互相顶替效果,
					// 则需要动态获取y轴偏移量,让顶部悬浮框在y轴上根据偏移量显示
					// 从而最终造成一种被顶出去顶回来(顶来顶去可欢乐了)滴效果
					View childView = view.getChildAt(0);
					if (childView != null) {
						if(childView.getTop()+currentPinnedHeader.getHeight()<=0){
							mTranslateY = childView.getTop()+currentPinnedHeader.getHeight();
						}
						createPinnedHeader(firstVisibleItem); // 创建当前显示的悬浮框
						postInvalidate();
						System.out.println("ding ... " + mTranslateY);
					}
				} else {
					if ((currentPinnedHeader != null && isPinnedHeaderShown)) {
						if (!myData.getSortLetters().equals(lastGroupName)) {
							createPinnedHeader(firstVisibleItem);
							Log.d(Tag, "create    merge " + firstVisibleItem);
						} else {
							Log.d(Tag, "recycle  " + firstVisibleItem);
						}
						mTranslateY = 0;
					} else {
//					if (!isPullRefreshing()) {
						createPinnedHeader(firstVisibleItem);
//						Log.d(Tag, "create " + firstVisibleItem);
//					}
					}
				}
				lastGroupName = myData.getSortLetters();
			}else{
				currentPinnedHeader=null;
			}

		}

	};
//	private ListView listView;

//	@Override
//	protected void preparePullDownRefresh() {
//		super.preparePullDownRefresh();
//		currentPinnedHeader = null;
//		invalidate();
//	}

	/**
	 * 核心方法 绘制顶部悬浮框
	 */
	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);

		if (currentPinnedHeader != null) {
			View pinnedView = currentPinnedHeader;
			TextView textView= (TextView) pinnedView.findViewById(R.id.catalog);
			if("1".equals(lastGroupName)){
				textView.setText("热门品牌");
			}else{
				textView.setText(lastGroupName);
			}

			int pLeft = this.getListPaddingLeft();
			int pTop = this.getListPaddingTop();

			canvas.save();
			canvas.clipRect(pLeft, pTop, pLeft + pinnedView.getWidth(), pTop + pinnedView.getHeight());
			canvas.translate(pLeft, pTop + mTranslateY);
			drawChild(canvas, pinnedView, getDrawingTime());
			canvas.restore();

			isPinnedHeaderShown = true;
		}
	}

	class PinnedHeader {
		public View view;
		public int position;

		@Override
		public String toString() {
			return "PinnedHeader [view=" + view + ", position=" + position + "]";
		}
	}


}
