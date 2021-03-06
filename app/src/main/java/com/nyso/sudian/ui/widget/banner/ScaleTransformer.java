package com.nyso.sudian.ui.widget.banner;

import android.support.v4.view.ViewPager;
import android.view.View;


/**
 * @author SoBan
 * @create 2017/5/19 15:33.
 */
public class ScaleTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.95f;
    private static final float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage(View page, float position) {
        if (position < -1 || position > 1) {
//            page.setAlpha(MIN_ALPHA);
            page.setScaleX(1f);
            page.setScaleY(MIN_SCALE);
        } else if (position <= 1) { // [-1,1]
//            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            if (position < 0) {
                float scaleX = 1 + 0.05f * position;
//                Log.d("google_lenve_fb", "transformPage: scaleX:" + scaleX);
//                page.setScaleX(scaleX);
                page.setScaleX(1f);
                page.setScaleY(scaleX);
            } else {
                float scaleX = 1 - 0.05f * position;
//                page.setScaleX(scaleX);
                page.setScaleY(scaleX);
                page.setScaleX(1f);
            }
//            page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
        }
    }
}
