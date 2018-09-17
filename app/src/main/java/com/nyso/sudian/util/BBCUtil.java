package com.nyso.sudian.util;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.test.andlang.http.Des3;
import com.example.test.andlang.util.DateUtil;
import com.nyso.sudian.SuDianApp;
import com.nyso.sudian.model.api.SysVer;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Bill56 on 2018-1-11.
 */

public class BBCUtil {
    private static StringBuilder sb = new StringBuilder();

    /**
     * 实现文本复制功能
     * add by wangqianzhou
     *
     * @param content
     */
    public static void copy(String content, Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData myClip = ClipData.newPlainText("text", content);//text是内容
        cmb.setPrimaryClip(myClip);
    }

    //获取屏幕宽度
    public static int getDisplayWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getDisplayHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    // android获取一个用于打开浏览器的intent
    public static void openWebBrowser(Activity activity, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        activity.startActivity(intent);
    }

    public static boolean isEmpty(String str) {
        if (str != null && !"".equals(str.trim())) {
            return false;
        }
        return true;
    }

    /**
     * Gets the corresponding path to a file from the given content:// URI
     *
     * @param selectedVideoUri The content:// URI to find the file path from
     * @param contentResolver  The content resolver to use to perform the query.
     * @return the file path as a string
     */
    public static String getFilePathFromContentUri(Uri selectedVideoUri,
                                                   ContentResolver contentResolver) {
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};
        Cursor cursor = contentResolver.query(selectedVideoUri, filePathColumn, null, null, null);
//      也可用下面的方法拿到cursor
//      Cursor cursor = this.context.managedQuery(selectedVideoUri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

    public static Map<String, String> getClientHeader() {
        Map<String, String> header = new HashMap<String, String>();
        header.put("Client-Type", "MihuiAndroid");
        return header;
    }

    public static String getUrlParma(String url, String key) {

        if (url != null && key != null && url.contains(key)) {
            String paramUrl=url.substring(url.indexOf("?")+1);
            if (paramUrl != null && paramUrl.indexOf("&") > -1 && paramUrl.indexOf("=") > -1) {
                String[] arrTemp = paramUrl.split("&");
                for (String str : arrTemp) {
                    String[] qs = str.split("=");
                    if(key.equals(qs[0])){
                        return qs[1];
                    }
                }
            }
//            int start = url.indexOf(key + "=");
//            url.indexOf("&", start);
//            int end = url.indexOf("&", start);
//            if (end == -1) {
//                end = url.length();
//            }
//            return url.substring(start + key.length() + 1, end);
        }
        return "";
    }

    public static void syncCookie(Context context) {
        String cookie = SuDianApp.getInstance().getSpUtil().getString(context, Constants.COOKIE);
        if (cookie != null && !"".equals(cookie)) {
            try {
                cookie = Des3.decode(cookie);
            } catch (Exception e) {
                e.printStackTrace();
                cookie = "";
            }
        }
        cookie += ";path=/;";
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setCookie(Constants.HOST, cookie);
        cookieManager.setCookie("http://bbc.nysomall.com/", cookie);
        CookieSyncManager.getInstance().sync();
    }

    public static String getHideMobile(String mobile) {
        if (mobile.length() > 4) {
            return mobile.substring(0, 3) + "****" + mobile.substring(mobile.length() - 4);
        }
        return mobile;
    }

    public static boolean isLogin(Context context) {
        return SuDianApp.getInstance().getSpUtil().getBoolean(context, Constants.ISLOGIN, false);
    }
    public static boolean isLoginFirst(Context context) {
        return SuDianApp.getInstance().getSpUtil().getBoolean(context, Constants.ISLOGIN_FIRST, false);
    }

    public static boolean isRead(Context context) {
        return SuDianApp.getInstance().getSpUtil().getBoolean(context,  Constants.ISREAD, false);
    }
    public static boolean isWXAppInstalledAndSupported(Context context) {
        IWXAPI msgApi = WXAPIFactory.createWXAPI(context, null);
        msgApi.registerApp(NativeHelper.getWXAPPID());
        boolean sIsWXAppInstalledAndSupported = msgApi.isWXAppInstalled()
                && msgApi.isWXAppSupportAPI();
        return sIsWXAppInstalledAndSupported;
    }

    public static byte[] getAssertsFile(Context context, String fileName) {
        InputStream inputStream = null;
        AssetManager assetManager = context.getAssets();
        try {
            inputStream = assetManager.open(fileName);
            if (inputStream == null) {
                return null;
            }

            BufferedInputStream bis = null;
            int length;
            try {
                bis = new BufferedInputStream(inputStream);
                length = bis.available();
                byte[] data = new byte[length];
                bis.read(data);

                return data;
            } catch (IOException e) {

            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (Exception e) {

                    }
                }
            }

            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 获取版本名称
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        PackageInfo packInfo = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            packInfo = packageManager.getPackageInfo(context.getPackageName(),
                    0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo.versionName;
    }

    /**
     * 获取版本姓名
     *
     * @param context
     * @return
     */
    public static Integer getVersionCode(Context context) {
        PackageInfo packInfo = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            packInfo = packageManager.getPackageInfo(context.getPackageName(),
                    0);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo.versionCode;
    }

    /**
     * 手机号码验证
     *
     * @param str
     * @return
     */
    public static boolean isMobile(String str) {
        if (isEmpty(str) || str.length() != 11) {
            return false;
        }
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static List<String> getSearchHistory(Context context) {
        List<String> history = new ArrayList<String>();
        String historyStr = SuDianApp.getInstance().getSpUtil().getString(context, Constants.SEARCH_HISTORY);
        if (!BBCUtil.isEmpty(historyStr)) {
            for (String str : historyStr.split(",")) {
                history.add(str);
            }
        }
        return history;
    }

    public static void deleteRecord(Context context, String record) {
        sb.delete(0, sb.length());
        String history = SuDianApp.getInstance().getSpUtil().getString(context, Constants.SEARCH_HISTORY);
        sb.append(history).append(",");
        String newHistory = sb.toString().replace(record + ",", "");
        if(newHistory.length()>0){
            sb.delete(0, sb.length());
            sb.append(newHistory).deleteCharAt(sb.length()-1);
        }
        SuDianApp.getInstance().getSpUtil().putString(context, Constants.SEARCH_HISTORY, sb.toString());
    }

    public static void addSearchRecord(Context context, String record) {
        sb.delete(0, sb.length());

        String history = SuDianApp.getInstance().getSpUtil().getString(context, Constants.SEARCH_HISTORY);
        String tmp = record + ",";
        if ("".equals(history)) {
            SuDianApp.getInstance().getSpUtil().putString(context, Constants.SEARCH_HISTORY, history.concat(record));
        } else if ((history + ",").contains(tmp)) {
            sb.append(history).append(",");
            int start = sb.indexOf(tmp);
            int end = start + tmp.length();
            sb.delete(start, end);
            sb.append(record);
            SuDianApp.getInstance().getSpUtil().putString(context, Constants.SEARCH_HISTORY, sb.toString());
        } else if (calculateCharCount(history, ',') >= 9) {//历史记录已经有50条，需要删除最早的记录
            sb.append(history);
            sb.delete(0, history.indexOf(",") + 1);
            sb.append(",");
            sb.append(record);
            SuDianApp.getInstance().getSpUtil().putString(context, Constants.SEARCH_HISTORY, sb.toString());
        } else {
            sb.append(history);
            sb.append(",");
            sb.append(record);
            SuDianApp.getInstance().getSpUtil().putString(context, Constants.SEARCH_HISTORY, sb.toString());
        }
    }

    public static void clearAllRecoder(Context context) {
        SuDianApp.getInstance().getSpUtil().putString(context, Constants.SEARCH_HISTORY, "");
    }

    public static int calculateCharCount(String str, char target) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == target) {
                count++;
            }
        }
        return count;

    }


    /**
     * 动态设置ListView的高度
     *
     * @param listView
     */
    public static int setListViewHeightBasedOnChildren(ListView listView) {
        if (listView == null) return 0;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return 0;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        return params.height;
    }

    /**
     * 动态设置ListView的高度
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView,int size) {
        if (listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        int showCount = listAdapter.getCount();
        if(showCount > size) {
            showCount = size;  // 展示的位置只显示size个
        }
        for (int i = 0; i < showCount; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (showCount - 1));
        listView.setLayoutParams(params);
    }

    public static int sp2px(Context context, int defaultSize) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return (int) (defaultSize * dm.density);
    }

    /**
     * 将double类型数据转换2位小数的税费价格
     *
     * @param d
     * @return
     */
    public static String getTaxFormat(double d) {

        return String.format("%.2f", getDoubleRoundOf(d));
//        DecimalFormat df = new DecimalFormat("##.#%");
    }

    /**
     * 四舍五入
     *
     * @param d
     * @return //先保留三位小数，再保留两位小数
     */
    public static double getDoubleRoundOf(Double d) {
        BigDecimal bg = new BigDecimal(String.valueOf(d));
        d = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        d = new BigDecimal(String.valueOf(d)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return d;
    }

    /**
     * 获取百分比
     */
    public static String getBFB(double d) {
        d*=100;
        d=getDoubleRoundOf(d,2);
        return String.format("%.2f", d)+"%";
    }

    /**
     * 四舍五入
     *
     * @param d
     * @return //先保留三位小数，再保留两位小数
     */
    public static double getDoubleRoundOf(Double d,int round) {
        BigDecimal bg = new BigDecimal(String.valueOf(d));
        d = bg.setScale(round+1, BigDecimal.ROUND_HALF_UP).doubleValue();
        d = new BigDecimal(String.valueOf(d)).setScale(round, BigDecimal.ROUND_HALF_UP).doubleValue();
        return d;
    }
    public static String getString(String str) {
        if (isEmpty(str)) {
            return "";
        }
        return str;
    }

    public static boolean isPhoneNumber(String str) {
        if (isEmpty(str)) {
            return false;
        }
        if (str.length() != 11) {
            return false;
        }
        if (!str.startsWith("1")) {
            return false;
        }
        return true;
    }

    public static String getDoubleFormat(Double price) {
        String s = price.toString();
        if (s.indexOf(".") > 0) {
            //正则表达
            s = s.replaceAll("0+?$", "");//去掉后面无用的零
            s = s.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
        }
        return s;
    }

    /**
     * 检测是否有emoji表情
     *
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { // 如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }
    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    public static String urlDecode(String url){
        url = url.replaceAll("%(?![0-9a-fA-F]{2})", "%");
        try {
            return   URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static boolean isAvilible(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        List<String> pName = new ArrayList<String>();// 用于存储所有已安装程序的包名
        // 从pinfo中将包名字逐一取出，压入pName list中
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);// 判断pName中是否有目标程序的包名，有TRUE，没有FALSE
    }
    /**
     * 启动到app详情界面
     *
     * @param appPkg    App的包名
     * @param marketPkg 应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public static void launchAppDetail(Context context, String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg))
                return;
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg))
                intent.setPackage(marketPkg);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 版本检查
     *
     * @param version
     * @return
     */
    public static boolean checkUpdate(Context context, SysVer version) {
        int verNo = BBCUtil.getVersionCode(context);
        if (version != null && version.getVerValue() > verNo) {
            return true;
        }
        return false;
    }
    public static String getSpValue(Context context,String key) {
        return SuDianApp.getInstance().getSpUtil().getString(context,  key);
    }


    public static void saveShowDialogTime(Context context){
        Date date=new Date(SuDianApp.NOW_TIME);
        SuDianApp.getInstance().getSpUtil().putString(context,
                Constants.SERVICE_TIME, DateUtil.getDateTime(date,"yyyy-MM-dd"));
    }

    public static boolean isDialogShow(Context context){
        String time=getSpValue(context, Constants.SERVICE_TIME);
        Date date=new Date(SuDianApp.NOW_TIME);
        if(isEmpty(time)){
            return true;
        }else if(!time.equals(DateUtil.getDateTime(date,"yyyy-MM-dd"))){
            return true;
        }
        return false;
    }

    /**
     *
     * @param arr 选择列表数组
     * @param str 当前选择的字符串
     * @param offset 返回type与数组下标的偏移量
     * @return
     */
    public static String getRefundValue(String[] arr, String str,int offset) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(str)) {
                return i+offset+"";
            }
        }
        return "";
    }
}
