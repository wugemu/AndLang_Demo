package com.nyso.sudian.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.nyso.sudian.R;

/**
 * Created by Bill56 on 2018-1-10.
 */

public class NavRadioButton extends AppCompatRadioButton {

    private int mDrawableSize;// xml文件中设置的大小

    public NavRadioButton(Context context) {
        this(context, null, 0);
    }

    public NavRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        Drawable drawableLeft = null, drawableTop = null, drawableRight = null, drawableBottom = null;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.NavRadioButton);

        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.NavRadioButton_drawableSize:
                    mDrawableSize = a.getDimensionPixelSize(R.styleable.NavRadioButton_drawableSize, 50);
                    break;
                case R.styleable.NavRadioButton_drawableTop:
                    drawableTop = a.getDrawable(attr);
                    break;
                case R.styleable.NavRadioButton_drawableBottom:
                    drawableRight = a.getDrawable(attr);
                    break;
                case R.styleable.NavRadioButton_drawableRight:
                    drawableBottom = a.getDrawable(attr);
                    break;
                case R.styleable.NavRadioButton_drawableLeft:
                    drawableLeft = a.getDrawable(attr);
                    break;
                default :
                    break;
            }
        }
        a.recycle();

        setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);

    }

//	public void setCompoundDrawablesWithIntrinsicBounds(Drawable left,
//			Drawable top, Drawable right, Drawable bottom) {
//
//		if (left != null) {
//			left.setBounds(0, 0, mDrawableSize, mDrawableSize);
//		}
//		if (right != null) {
//			right.setBounds(0, 0, mDrawableSize, mDrawableSize);
//		}
//		if (top != null) {
//			top.setBounds(0, 0, mDrawableSize, mDrawableSize);
//		}
//		if (bottom != null) {
//			bottom.setBounds(0, 0, mDrawableSize, mDrawableSize);
//		}
//		setCompoundDrawables(left, top, right, bottom);
//	}

    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left,
                                                        Drawable top, Drawable right, Drawable bottom) {

        if (left != null) {
            left.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        if (right != null) {
            right.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        if (top != null) {
            top.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        if (bottom != null) {
            bottom.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        setCompoundDrawables(left, top, right, bottom);
    }

}
