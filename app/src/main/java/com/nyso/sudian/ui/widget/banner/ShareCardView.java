package com.nyso.sudian.ui.widget.banner;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.test.andlang.util.imageload.ImageLoadUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nyso.sudian.R;
import com.nyso.sudian.model.api.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * @author SoBan
 * @create 2017/5/19 15:33.
 */
public class ShareCardView extends FrameLayout implements ViewPager.OnPageChangeListener {
    private static final int MSG_NEXT = 1;
    private Context mContext;
    private ViewPager mViewPager; //自定义的无限循环ViewPager
    private ViewGroup mViewGroup;
    private CardAdapter mAdapter;
    private int mFocusImageId;
    private int mUnfocusImageId;
    private Handler mHandler;
    private TimerTask mTimerTask;
    private Timer mTimer;
    private int centerPos; //中间卡片位置
    private int pageCount; //所有卡片的个数
    /**
     * 是否自动滚动
     */
    private boolean mIsAutoScrollEnable = true;

    public ShareCardView(Context context) {
        this(context, null);
    }

    public ShareCardView(Context context, AttributeSet set) {
        super(context, set);
        init(context);
    }


    private void init(Context context) {
        mContext = context;
        View container = LayoutInflater.from(mContext).inflate(R.layout.layout_share_cardview, null);
        addView(container);
        mViewPager = (ViewPager) (container.findViewById(R.id.slide_viewPager));
        mViewGroup = (ViewGroup) (container.findViewById(R.id.slide_viewGroup));
        mFocusImageId = R.drawable.focus_banner;
        mUnfocusImageId = R.drawable.unfocus_banner;

        mViewPager.setPageTransformer(false, new ScaleTransformer());//设置卡片切换动画
        mViewPager.setPageMargin(32);//卡片与卡片间的距离
        mViewPager.setOnPageChangeListener(this);
//        int width = (int) (BBCUtil.getDisplayWidth((Activity) getContext())-getContext().getResources().getDimension(R.dimen.viewpage_margintop2));
//        int height = (int) ((width*350.0/610));
//        RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(width,height);
//        mViewPager.setLayoutParams(params);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_NEXT:
                        next();
                        break;
                }
                super.handleMessage(msg);
            }
        };

        mViewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        stopTimer();
                        break;
                    case MotionEvent.ACTION_UP:
                        startTimer();
                        break;
                }
                return false;
            }
        });
    }

    //设置数据
    public void setCardData(ShareCardItem cardItem) {
        pageCount = cardItem.getDataList().size();
        centerPos = pageCount / 2;//中间卡片的位置
        mAdapter = new CardAdapter(cardItem);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(pageCount);//预加载所有卡片
        mAdapter.select(0);//默认从中间卡片开始
        mViewPager.setCurrentItem(0);
        startTimer();
    }

    //启动动画
    public void startTimer() {
        stopTimer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(MSG_NEXT);
            }
        };
        mTimer = new Timer(true);
        mTimer.schedule(mTimerTask, 3000, 3000);
    }

    //停止动画
    public void stopTimer() {
        mHandler.removeMessages(MSG_NEXT);
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }

        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }

    //选择下一个卡片
    public void next() {
        int pos = mViewPager.getCurrentItem();
        pos += 1;
        mViewPager.setCurrentItem(pos);
    }

    //判断是否显隐控件
    public void refresh() {
        if (getCount() <= 0) {
            this.setVisibility(View.GONE);
        } else {
            this.setVisibility(View.VISIBLE);
        }
    }

    public int getCount() {
        if (mAdapter != null) {
            return mAdapter.getCount();
        }
        return 0;
    }

    public class CardAdapter extends PagerAdapter {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.image_def) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.image_def) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.image_def) // 设置图片加载或解码过程中发生错误显示的图片
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                .displayer(new RoundedBitmapDisplayer(8))//是否设置为圆角，弧度为多少
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .build();//构建完成

        private ArrayList<ImageView> mPoints;
        private List<Subject> lrCardItems = new ArrayList<>();

        public CardAdapter(ShareCardItem cardItem) {
            mPoints = new ArrayList<>();
            lrCardItems = cardItem.getDataList();
            setItems();
        }

        @Override
        public int getCount() {
            return pageCount;
        }

        private ImageView newPoint() {
            ImageView imageView = new ImageView(mContext);
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin = 20;
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(mUnfocusImageId);
            return imageView;
        }

        //其他卡片
        public void setLRCard(final ImageView view, int lrCardItemPos) {
            final Subject item = lrCardItems.size() > lrCardItemPos ?
                    lrCardItems.get(lrCardItemPos) : new Subject();
            ImageLoadUtils.doLoadImageUrl(view,item.getUrl2());
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickL != null) {
                        mOnItemClickL.onItemClick(item);
                    }
                }
            });

        }

        public void setItems() {
            while (mPoints.size() < pageCount) mPoints.add(newPoint());
            while (mPoints.size() > pageCount) mPoints.remove(0);
            mViewGroup.removeAllViews();
            for (ImageView view : mPoints) {
                mViewGroup.addView(view);
            }

        }

        public void select(int pos) {
            if (mPoints.size() > 0) {
                pos = pos % mPoints.size();
                for (int i = 0; i < mPoints.size(); i++) {
                    if (i == pos) {
                        mPoints.get(i).setBackgroundResource(mFocusImageId);
                    } else {
                        mPoints.get(i).setBackgroundResource(mUnfocusImageId);
                    }
                }
            }
            refresh();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

           final ImageView view = new ImageView(getContext());
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            view.setLayoutParams(params);
            setLRCard(view, position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mAdapter.select(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private int downX;
    private int downY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 父控件不要拦截
                getParent().requestDisallowInterceptTouchEvent(true);
                downX = (int) ev.getX();
                downY = (int) ev.getY();
                if (mIsAutoScrollEnable) {
                    stopTimer();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) ev.getX();
                int moveY = (int) ev.getY();
                // 下滑
                if (Math.abs(moveY - downY) > Math.abs(moveX - downX)) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                if (mIsAutoScrollEnable) {
                    startTimer();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mIsAutoScrollEnable) {
                    startTimer();
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private OnItemClickL mOnItemClickL;

    public void setOnItemClickL(OnItemClickL onItemClickL) {
        this.mOnItemClickL = onItemClickL;
    }

    public interface OnItemClickL {
        void onItemClick(Object object);
    }
}
