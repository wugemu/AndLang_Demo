package com.example.test.andlang.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * Created by Bill56 on 2018-1-31.
 */

public class FileUtil {

    /**
     * 根据文件转换成对应的Uri
     *
     * @param ctx
     * @param file
     * @return
     */
    public static Uri file2Uri(Context ctx, File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(ctx, ctx.getApplicationInfo().processName + ".fileProvider", file);
        } else {
            return Uri.fromFile(file);
        }
    }

    public static File getFileFromUri(Context ctx,Uri uri) throws Exception{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Bitmap bitmap = BitmapFactory.decodeStream(ctx.getContentResolver().openInputStream(uri));
            if(bitmap != null && ImageUtil.saveBitmap(bitmap)) {
                return new File(PicSelUtil.getImgFileDir(), PicSelUtil.CACHE_FILE_NAME[0]);
            } else {
                return null;
            }
        } else {
            return new File(uri.getPath());
        }
    }

}
