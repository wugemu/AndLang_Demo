package com.nyso.sudian.ui.widget.pysortlist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


import com.nyso.sudian.R;

import java.util.ArrayList;
import java.util.List;


public class SideBar extends View {

	// 触摸事件
	private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
	// 26个字母
//	public static String[] b = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
//			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
//			"W", "X", "Y", "Z", "#" };
	private List<String> words=new ArrayList<String>();
	private int choose = -1;// 选中
	private Paint paint = new Paint();
	private Context context;
	private TextView mTextDialog;
	private int top,bottom;
	public void setTextView(TextView mTextDialog) {
		this.mTextDialog = mTextDialog;
	}

	public void setWords(List<String> words) {
		if(words!=null){
			this.words = words;
		}
	}



	public SideBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context=context;
		top= (int) context.getResources().getDimension(R.dimen.sidebar_top_margin);
		bottom= (int) context.getResources().getDimension(R.dimen.sidebar_bottom_margin);
	}

	public SideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		top= (int) context.getResources().getDimension(R.dimen.sidebar_top_margin);
		bottom= (int) context.getResources().getDimension(R.dimen.sidebar_bottom_margin);
	}

	public SideBar(Context context) {
		super(context);
		this.context=context;
		top= (int) context.getResources().getDimension(R.dimen.sidebar_top_margin);
		bottom= (int) context.getResources().getDimension(R.dimen.sidebar_bottom_margin);
	}

	/**
	 * 重写这个方法
	 */
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(words.size()>0){
			// 获取焦点改变背景颜色.
			int height = getHeight();// 获取对应高度
			int width = getWidth(); // 获取对应宽度
			int singleHeight = (height-top-bottom) / words.size();// 获取每一个字母的高度
//			int wordHeight= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
//					16, context.getResources().getDisplayMetrics());
//			singleHeight=singleHeight<wordHeight?singleHeight:wordHeight;
			for (int i = 0; i < words.size(); i++) {
				paint.setColor(Color.rgb(33, 65, 98));
				// paint.setColor(Color.WHITE);
//				paint.setTypeface(Typeface.DEFAULT_BOLD);
				paint.setAntiAlias(true);
				paint.setTextSize(context.getResources().getDimension(R.dimen.sidebar_word_size));
				// 选中的状态
				if (i == choose) {
					paint.setColor(Color.parseColor("#333333"));
					paint.setFakeBoldText(true);
				}
				// x坐标等于中间-字符串宽度的一半.
				float xPos = width / 2 - paint.measureText(words.get(i)) / 2;
				float yPos = singleHeight * i + singleHeight+top;
				canvas.drawText(words.get(i), xPos, yPos, paint);
				paint.reset();// 重置画笔
			}
		}


	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		final int action = event.getAction();
		final float y = event.getY()-top;// 点击y坐标
		final int oldChoose = choose;
		final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
		final int c = (int) (y / (getHeight()-top-bottom) * words.size());// 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.
		switch (action) {
		case MotionEvent.ACTION_UP:
			setBackgroundDrawable(new ColorDrawable(0x00000000));
			choose = -1;//
			invalidate();
			if (mTextDialog != null) {
				mTextDialog.setVisibility(View.INVISIBLE);
			}
			break;

		default:
//			setBackgroundResource(R.drawable.sidebar_background);
			if (oldChoose != c) {
				if (c >= 0 && c < words.size()) {
					if (listener != null) {
						listener.onTouchingLetterChanged(words.get(c));
					}
					if (mTextDialog != null) {
						mTextDialog.setText(words.get(c));
						mTextDialog.setVisibility(View.VISIBLE);
					}

					choose = c;
					invalidate();
				}
			}

			break;
		}
		return true;
	}

	/**
	 * 向外公开的方法
	 *
	 * @param onTouchingLetterChangedListener
	 */
	public void setOnTouchingLetterChangedListener(
			OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
		this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}

	/**
	 * 接口
	 * 
	 * @author coder
	 * 
	 */
	public interface OnTouchingLetterChangedListener {
		public void onTouchingLetterChanged(String s);
	}

}