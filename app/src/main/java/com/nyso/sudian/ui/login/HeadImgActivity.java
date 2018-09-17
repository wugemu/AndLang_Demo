package com.nyso.sudian.ui.login;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.http.ExecutorServiceUtil;
import com.example.test.andlang.util.ActivityUtil;
import com.example.test.andlang.util.BitmapUtil;
import com.example.test.andlang.util.FileUtil;
import com.example.test.andlang.util.ImageUtil;
import com.example.test.andlang.util.PermissionsCheckerUtil;
import com.example.test.andlang.util.PicSelUtil;
import com.example.test.andlang.util.StorageUtils;
import com.example.test.andlang.util.ToastUtil;
import com.example.test.andlang.util.imageload.GlideUtil;
import com.example.test.andlang.util.imageload.ImageLoadUtils;
import com.nyso.sudian.R;
import com.nyso.sudian.SuDianApp;
import com.nyso.sudian.model.local.LoginModel;
import com.nyso.sudian.presenter.LoginPresenter;
import com.nyso.sudian.ui.home.HomeActivity;
import com.nyso.sudian.ui.widget.CleanableEditText;
import com.nyso.sudian.ui.widget.SelectPicPopupWindow;
import com.nyso.sudian.util.BBCHttpUtil;
import com.nyso.sudian.util.BBCUtil;
import com.nyso.sudian.util.Constants;

import java.io.File;
import java.util.Observable;

import butterknife.BindView;
import butterknife.OnClick;

public class HeadImgActivity extends BaseLangActivity<LoginPresenter> {

    private  final int REQUESTCODE_TAKE=100;//拍照
    private  final int REQUESTCODE_PICK=101;//本地
    private final int CROP_PHOTO = 103;// 返回码：剪裁

    @BindView(R.id.iv_head_url)
    ImageView iv_head_url;
    @BindView(R.id.ce_bind_name)
    CleanableEditText ce_bind_name;
    @BindView(R.id.btn_next)
    Button btn_next;

    // 图片格式的URI对象
    private Uri imageUri;
    // 临时保存头像的缓存文件
    private File imageCache;
    private String headurl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_head_img;
    }

    @Override
    public void initView() {
        ce_bind_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeButtonState();
            }
        });
        headurl= SuDianApp.otherHeadImg;
        String nickname=SuDianApp.otherNickName;
        if(!BBCUtil.isEmpty(headurl)){
            ImageLoadUtils.doLoadCircleImageUrl(iv_head_url,headurl);
        }
        if(!BBCUtil.isEmpty(nickname)){
            ce_bind_name.setText(nickname);
        }
    }

    @Override
    public void initPresenter() {
        presenter=new LoginPresenter(HeadImgActivity.this, LoginModel.class);
    }

    @Override
    public void initData() {
        initLoading();
    }

    @OnClick(R.id.tv_edit)
    public void goJump(){
        //跳过
        ActivityUtil.getInstance().start(HeadImgActivity.this,new Intent(HeadImgActivity.this,HomeActivity.class));
    }

    @OnClick(R.id.iv_head_url)
    public void chooseIcon(){
        //实例化SelectPicPopupWindow
        SelectPicPopupWindow menuWindow = new SelectPicPopupWindow(this,handler,REQUESTCODE_TAKE,REQUESTCODE_PICK);
        //显示窗口
        menuWindow.showAtLocation(iv_head_url, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
    }

    @OnClick(R.id.btn_next)
    public void goNext(){
        //下一步
        showWaitDialog();
        presenter.updateNickName(headurl,ce_bind_name.getText().toString().trim());
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Intent intent=null;
            switch (msg.what) {
                case REQUESTCODE_TAKE:
                    // 系统相机

                    // 缺少权限时, 进入权限配置页面
                    if (PermissionsCheckerUtil.lacksPermissions(HeadImgActivity.this,PicSelUtil.TAKE_PHOTO_PERMISSIONS)) {
                        //申请WRITE_EXTERNAL_STORAGE权限
                        ActivityCompat.requestPermissions(HeadImgActivity.this, PicSelUtil.TAKE_PHOTO_PERMISSIONS,
                                PicSelUtil.TAKE_PHOTO_CODE);
                    } else {
                        startCemara();
                    }
                    break;

                case REQUESTCODE_PICK:

                    // 缺少权限时, 进入权限配置页面
                    if (PermissionsCheckerUtil.lacksPermissions(HeadImgActivity.this,PicSelUtil.WRITE_EXTERNAL_STORAGE_PERMISSIONS)) {
                        //申请WRITE_EXTERNAL_STORAGE权限
                        ActivityCompat.requestPermissions(HeadImgActivity.this, PicSelUtil.WRITE_EXTERNAL_STORAGE_PERMISSIONS,
                                PicSelUtil.SELECT_PHOTO_CODE);
                    } else {
                        selectPhoto();
                    }

                    break;
                case CROP_PHOTO:
                    // 调用剪裁程序活动
                    intent = new Intent("com.android.camera.action.CROP");
                    //添加这一句表示对目标应用临时授权该Uri所代表的文件
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    intent.setDataAndType(imageUri, "image/*");
//                    intent.putExtra("scale", true);
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                    intent.putExtra("outputX", 300);
                    intent.putExtra("outputY", 300);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CROP_PHOTO);
                    break;

            }
        }
    };

    private void selectPhoto(){
        // 本地图册
        Intent intent = new Intent(
                Intent.ACTION_PICK, null);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, REQUESTCODE_PICK);
    }

    private void startCemara() {
        // 系统相机
        Intent intent2 = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        intent2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        imageUri = PicSelUtil.getImageCacheUri(this,PicSelUtil.CACHE_FILE_NAME[0]);
        intent2.putExtra(
                MediaStore.EXTRA_OUTPUT,
                imageUri);
        startActivityForResult(intent2, REQUESTCODE_TAKE);
    }

    public void changeButtonState(){
        if(!BBCUtil.isEmpty(ce_bind_name.getText().toString().trim())&&!BBCUtil.isEmpty(headurl)){
            btn_next.setBackgroundResource(R.drawable.bg_rect_new);
            btn_next.setEnabled(true);
        }else {
            btn_next.setBackgroundResource(R.drawable.bg_rect_new3);
            btn_next.setEnabled(false);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUESTCODE_PICK:// 直接从相册获取
                // 相册
                try {
                    if (resultCode == RESULT_OK) {
                        Uri uri = ImageUtil.getUri(data, HeadImgActivity.this.getContentResolver());//解决小米手机上获取图片路径为null的情况\
                        if(uri != null) {
                            // 取得SDCard图片路径做显示
                            String path = BBCUtil.getFilePathFromContentUri(uri, this.getContentResolver());
                            File imgSrc = new File(path);
                            // 获得uri
                            imageUri = FileUtil.file2Uri(this, imgSrc);
                            // 打开剪裁程序
                            Message message = new Message();
                            message.what = CROP_PHOTO;
                            message.obj = path;
                            handler.sendMessage(message);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();// 用户点击取消操作，其他错误
                }
                break;
            case REQUESTCODE_TAKE:
                // 相机
                if (resultCode == RESULT_OK) {
                    handler.sendEmptyMessage(CROP_PHOTO);
                }
                break;
            case CROP_PHOTO:
                // 剪裁程序返回
                // 当剪裁程序返回的结果是正确的时候
                if (resultCode == RESULT_OK) {
                    // 获取图片
                    try {
                        imageCache = FileUtil.getFileFromUri(this,imageUri);
                        if (imageCache != null) {
                            showWaitDialog();
                            ExecutorServiceUtil.getInstence().execute(uploadImageRunnable);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }

    }

    Runnable uploadImageRunnable = new Runnable() {
        public void run() {
            // 要上传的图片文件
            uploadFileInThreadByOkHttp(Constants.HOST + Constants.UP_MINE_HEADIMG,imageCache,"1");
        }
    };

    // 压缩图片并上传
    private  void uploadFileInThreadByOkHttp(final String actionUrl, final File tempPic,String type) {
        String pic_path = tempPic.getAbsolutePath();
        File targetFile=new File(StorageUtils.getOwnCacheDirectory(
                getApplicationContext(), Constants.imageDir),tempPic.getName());
        final String compressImage = BitmapUtil.compressImage(pic_path, targetFile.getAbsolutePath(), 100);
        final File compressedPic = new File(compressImage);
        //调用压缩图片的方法，返回压缩后的图片path
        presenter.uploadImage(HeadImgActivity.this, compressedPic,actionUrl,true,type);
    }

    //用户选择允许或拒绝后，会回调onRequestPermissionsResult方法
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    //根据requestCode和grantResults(授权结果)做相应的后续处理
    private void doNext(int requestCode, int[] grantResults) {

        if (requestCode == PicSelUtil.SELECT_PHOTO_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 开启读写权限
                selectPhoto();
            } else {
                ToastUtil.show(this, "请开启SD卡读写权限");
            }
        }
        if (requestCode == PicSelUtil.TAKE_PHOTO_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // 开启拍照
                startCemara();
            } else {
                if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    ToastUtil.show(this, "请开启拍照权限");
                } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ToastUtil.show(this, "请开启SD卡读写权限");
                } else {
                    ToastUtil.show(this, "请开启拍照和SD卡读写权限");
                }
            }
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        if("uploadImage".equals(arg)){
            //图片上传成功
            headurl= SuDianApp.getInstance().getSpUtil().getString(HeadImgActivity.this,
                     Constants.USER_HEADIMG);
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //此时已在主线程中，可以更新UI了
                    ImageLoadUtils.doLoadCircleImageUrl(iv_head_url,headurl);
                }
            });
            changeButtonState();
        }else if("updateNickName".equals(arg)){
            //更新成功
            ActivityUtil.getInstance().start(HeadImgActivity.this,new Intent(HeadImgActivity.this,HomeActivity.class));
        }
    }
}
