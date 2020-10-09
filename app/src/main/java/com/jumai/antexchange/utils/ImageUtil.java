package com.jumai.antexchange.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.base.GlideApp;
import com.jumai.antexchange.utils.net.util.RxUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class ImageUtil {

    public static final long SIZE_200_KB = 209715200l;
    public static final long SIZE_300_KB = 314572800l;
    public static final long SIZE_1_5_MB = 1572864000l;


    //centerCrop
    public static void loadCenterCrop(Context context, String path, ImageView imageView) {
        GlideApp.with(context).asBitmap().load(path).placeholder(R.drawable.icon_placeholder).error(R.drawable.icon_placeholder).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    //centerCrop
    public static void loadCenterCrop(Context context, int drawable, ImageView imageView) {
        GlideApp.with(context).asBitmap().load(drawable).placeholder(R.drawable.icon_placeholder).error(R.drawable.icon_placeholder).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    //fitCenter
    public static void loadFitCenter(Context context, String path, ImageView imageView) {
        GlideApp.with(context).asBitmap().load(path).placeholder(R.drawable.icon_placeholder).error(R.drawable.icon_placeholder).fitCenter().diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    //fitCenter
    public static void loadFitCenter(Context context, int drawable, ImageView imageView) {
        GlideApp.with(context).asBitmap().load(drawable).placeholder(R.drawable.icon_placeholder).error(R.drawable.icon_placeholder).fitCenter().diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    //根据imageView的scaleType决定
    public static void load(Context context, String path, ImageView imageView) {
        GlideApp.with(context).asBitmap().load(path).placeholder(R.drawable.icon_placeholder).error(R.drawable.icon_placeholder).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    //根据imageView的scaleType决定
    public static void load(Context context, int drawable, ImageView imageView) {
        GlideApp.with(context).asBitmap().load(drawable).placeholder(R.drawable.icon_placeholder).error(R.drawable.icon_placeholder).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    public static void loadHolder(Context context, String path, ImageView imageView, int placeholder) {
        GlideApp.with(context).asBitmap().load(path).placeholder(placeholder).error(placeholder).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    public static void loadHolderCenterCrop(Context context, String path, ImageView imageView, int placeholder) {
        GlideApp.with(context).asBitmap().load(path).centerCrop().placeholder(placeholder).error(placeholder).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    public static void loadHolderFitCenter(Context context, String path, ImageView imageView, int lodingImage) {
        GlideApp.with(context).asBitmap().load(path).fitCenter().placeholder(lodingImage).error(lodingImage).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    //加载GIF
    public static void loadGif(Context context, String path, ImageView imageView) {
        GlideApp.with(context).asGif().load(path).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView);
    }

    //加载GIF
    public static void loadGif(Context context, int drawable, ImageView imageView) {
        GlideApp.with(context).asGif().load(drawable).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView);
    }

    /**
     * 带监听的图片加载
     */
    public static void loadWithListener(Context context, String path, ImageView imageView, int placeholder, ImageListener listener) {
        GlideApp.with(context).asBitmap().load(path).listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                listener.onFailed();
                //{@code true} to prevent {@link Target#onLoadFailed(Drawable)} from being called on
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                listener.onSucceed();
                //{@code true} to prevent {@link Target#onLoadFailed(Drawable)} from being called on
                return false;
            }
        }).fitCenter().dontAnimate().error(placeholder).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    public static void load(Context context, String path, CustomViewTarget<View, Bitmap> target) {
        GlideApp.with(context)
                .asBitmap()
                .load(path)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(target);
    }


    // should called in io thread
    public static String getShareImagePath(Context context, String url) {
        FutureTarget<File> futureTarget = GlideApp.with(context).load(url).downloadOnly(150, 150);
        try {
            File cacheFile = futureTarget.get();
            return cacheFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    private static final int MAX_DECODE_PICTURE_SIZE = 1920 * 1440;


    /**
     * 按质量压缩
     *
     * @return 质量压缩压缩过的图片
     */
    public static byte[] compressByQuality(String filePath) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        String qualityAttr = getImageQualityAttr(filePath);

        int quality = TextUtils.isEmpty(qualityAttr) ? 80 : Integer.valueOf(qualityAttr);

        if (bitmap != null) {

            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);

            int size;
            while ((size = baos.size()) > SIZE_200_KB) {
                baos.reset();
                if (size > SIZE_1_5_MB) {
                    quality -= 20;

                } else if (size > SIZE_300_KB) {
                    quality -= 10;
                } else {
                    quality -= 5;
                }

//                if (quality < 0) return null;

                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            }

            byte[] bytes = baos.toByteArray();
        }
        return baos.toByteArray();
    }


    public static String getImageQualityAttr(String filePath) {
        String qualityAttr = null;
        try {
            ExifInterface exifInterface = new ExifInterface(filePath);
            qualityAttr = exifInterface.getAttribute("quality");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return qualityAttr;
    }


    /**
     * 计算采样大小
     *
     * @param options   选项
     * @param maxWidth  最大宽度
     * @param maxHeight 最大高度
     * @return 采样大小
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int maxWidth, int maxHeight) {
        if (maxWidth == 0 || maxHeight == 0) return 1;
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        while ((height >>= 1) >= maxHeight && (width >>= 1) >= maxWidth) {
            inSampleSize <<= 1;
        }
        return inSampleSize;
    }


    /**
     * 根据计算的inSampleSize，得到压缩后图片
     */
    private Bitmap decodeSampledBitmapFromResource(String pathName,
                                                   int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(pathName, options);
    }

    public static void downloadImage(Context context, String imgUrl, String fileName, ImageListener listener) {
        if (TextUtils.isEmpty(imgUrl)) {
            return;
        }

        String finalImageName = fileName + ".jpg";

        GlideApp.with(context)
                .asFile()
                .load(imgUrl)
                .into(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                        try {
                            RxUtil.io(() -> {
                                File appSDImageFile = FileUtil.getAppSDImageFile(finalImageName);
                                FileUtil.copyFile(resource, appSDImageFile);
                                notifyPhotoPicture(context, appSDImageFile.getAbsolutePath());
                            });
                            ToastUtils.showShort(R.string.tips_save_succeed);
                            if (listener != null) {
                                listener.onSucceed();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            ToastUtils.showShort(R.string.tips_save_failed);
                            if (listener != null) {
                                listener.onFailed();
                            }
                        }
                    }
                });
    }


    public static void notifyPhotoPicture(Context ctx, String imgFileName) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File file = new File(imgFileName);
        Uri contentUri = Uri.fromFile(file);
        mediaScanIntent.setData(contentUri);
        ctx.sendBroadcast(mediaScanIntent);
    }

    public static String saveBitmap(Bitmap bitmap) throws IOException {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = FileUtil.getAppSDImagePath(System.currentTimeMillis() + ".png");
            if (path != null) {
                FileOutputStream fos = new FileOutputStream(path);
                bitmap.compress(Bitmap.CompressFormat.PNG, 80, fos);
                fos.flush();
                fos.close();
            }
            return path;
        } else {
            return null;
        }
    }

    @SuppressLint("CheckResult")
    public static void saveImage(BaseActivity activity, String imgUrl, String fileName, ImageListener listener) {
        new RxPermissions(activity)
                .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(permission -> {
                    if (permission.granted) {
                        downloadImage(activity, imgUrl, fileName, listener);
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        if (listener != null) {
                            listener.onFailed();
                        }
                    } else {
                        if (listener != null) {
                            listener.onFailed();
                        }
                    }
                });
    }

    public static void setDrawableRight(Context context, TextView view , int drawableRes){
        view.setVisibility(View.VISIBLE);
        Drawable drawable = context.getResources().getDrawable(drawableRes);
        if(drawable != null){
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            view.setCompoundDrawables(null, null, drawable, null);
        }
    }


    public interface ImageListener {
        void onSucceed();

        void onFailed();
    }
}
