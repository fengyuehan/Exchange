package com.jumai.antexchange.utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import com.jumai.antexchange.base.AntApplication;
import com.zhy.base.fileprovider.FileProvider7;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 存储分两类：
 * - 紧要数据: /storage/emulated/0/Android/data/包名/file;    /data/data/包名/file
 * - getAppFilePath()
 * - 缓存数据: /storage/emulated/0/Android/data/包名/cache;   /data/data/包名/cache
 * --  getCacheImageDir()缓存 /images
 * --  getCacheFileDir() 缓存 /file, 存储pdf等
 */
public class FileUtil {

    private static final String APP_CACHE_IMG_PATH = "/image/";
    private static final String APP_CACHE_FILE_PATH = "/file/";
    private static final String APP_CACHE_RECORDER_PATH = "/recorder/";
    private static final String APP_SAVE_FILE_PATH = "/file/";
    private static final String APP_SAVE_ANT_PATH = "/ant/";
    private static final String APP_NAME = "AntExchange";
    private static final String APP_CACHE = "cache";
    private static final String APP_FILE = "files";

    /**
     * 判断外部存储是否存在并且是可写的
     */
    public static boolean isSDCardExistAndCanWrite(File file) {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && file.canWrite();
    }

    /**
     * SD卡可用的时候返回的是:storage/emulated/0/AntExchange
     * SD卡不可用返回的是内存的路径data/data/AntExchange/files
     */
    public static String getAppPublicPath(Context context, String pathName) {
        String dirPath = "";
        // 判断SDCard是否存在并且是可用的getExternalStorageDirectory
        if (isSDCardExistAndCanWrite(Environment.getExternalStorageDirectory())) {
            dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + APP_NAME;
        } else {
            dirPath = context.getFilesDir().getAbsolutePath();
        }

        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }


    /**
     * SD卡可用的时候返回的是:storage/emulated/0/Android/data/AntExchange/cache
     * SD卡不可用返回的是内存的路径:data/data/AntExchange/cache
     */
    public static String getAppCachePath(Context context, String pathName) {
        String dirPath = "";
        // 判断SDCard是否存在并且是可用的
        if (isSDCardExistAndCanWrite(context.getExternalCacheDir())) {
            dirPath = context.getExternalCacheDir().getAbsolutePath() + File.separator + APP_CACHE;
        } else {
            dirPath = context.getCacheDir().getAbsolutePath();
        }
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    /**
     * SD卡可用的时候返回的是:storage/emulated/0/Android/data/AntExchange/files
     * SD卡不可用返回的是内存的路径data/data/AntExchange/files
     */
    public static String getAppFilePath(Context context) {
        String dirPath = "";
        // 判断SDCard是否存在并且是可用的
        if (isSDCardExistAndCanWrite(context.getExternalFilesDir(Environment.DIRECTORY_PODCASTS))) {
            dirPath = context.getExternalFilesDir(Environment.DIRECTORY_PODCASTS).getAbsolutePath() + File.separator + APP_FILE;
        } else {
            dirPath = context.getFilesDir().getAbsolutePath();
        }
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    /**
     * SD卡可用的时候返回的是:storage/emulated/0/Android/data/AntExchange/files/xxx
     * SD卡不可用返回的是内存的路径data/data/AntExchange/files/xxx
     */
    public static String getAppFilePath(Context context, String fileName) {
        String dirPath = "";
        // 判断SDCard是否存在并且是可用的
        if (isSDCardExistAndCanWrite(context.getExternalFilesDir(Environment.DIRECTORY_PODCASTS))) {
            dirPath = context.getExternalFilesDir(Environment.DIRECTORY_PODCASTS).getAbsolutePath() + File.separator + APP_FILE + File.separator + fileName;
        } else {
            dirPath = context.getFilesDir().getAbsolutePath() + File.separator + fileName;
        }
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    /**
     * 获取SD卡路径
     * storage/emulated/0/Android/data/AntExchange
     */
    public static String getAppSDPath() {
        String dirPath = null;
        // 判断SDCard是否存在并且是可用的getExternalStorageDirectory
        if (isSDCardExistAndCanWrite(Environment.getExternalStorageDirectory())) {
            dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + APP_NAME;
        }
        return dirPath;
    }

    /**
     * 获取SD卡保存图片目录
     */
    public static String getAppSDImagePath(String fileName) {
        String appSDPath = getAppSDPath();
        if (appSDPath != null) {
            String path = appSDPath + "/image/" + fileName;
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            return path;
        } else {
            return null;
        }
    }

    /**
     * 获取SD卡保存图片File
     */
    public static File getAppSDImageFile(String fileName) {
        String appSDPath = getAppSDPath();
        if (appSDPath != null) {
            String path = appSDPath + "/image/" + fileName;
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
        } else {
            return null;
        }
    }

    /**
     * 获取SD卡保存文件目录
     */
    public static String getAppSDFilePath(String fileName) {
        String appSDPath = getAppSDPath();
        if (appSDPath != null) {
            String path = appSDPath + "/files/" + fileName;
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            return path;
        } else {
            return null;
        }
    }

    /**
     * 获取SD卡保存文件File
     */
    public static File getAppSDFileFile(String fileName) {
        String appSDPath = getAppSDPath();
        if (appSDPath != null) {
            String path = appSDPath + "/files/" + fileName;
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
        } else {
            return null;
        }
    }

    /**
     * 获取SD卡保存缓存目录
     */
    public static String getAppSDCachePath(String fileName) {
        String appSDPath = getAppSDPath();
        if (appSDPath != null) {
            String path = appSDPath + "/files/" + fileName;
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            return path;
        } else {
            return null;
        }
    }

    /**
     * 获取SD卡保存缓存File
     */
    public static File getAppSDCacheFile(String fileName) {
        String appSDPath = getAppSDPath();
        if (appSDPath != null) {
            String path = appSDPath + "/files/" + fileName;
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
        } else {
            return null;
        }
    }

    public static String getPublicPocketDir() {
        return getAppPublicPath(AntApplication.getInstance(), APP_SAVE_ANT_PATH);
    }

    public static void writeFile(Context context, Object obj, String fileName) {
        File file;
        FileOutputStream fos = null;
        ObjectOutputStream os = null;
        try {
            file = new File(getAppFilePath(context, fileName));
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            os = new ObjectOutputStream(fos);
            os.writeObject(obj);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Object readFile(Context context, String fileName) {
        FileInputStream fos = null;
        ObjectInputStream os = null;
        try {
            File file = new File(getAppFilePath(context, fileName));
            if (!file.exists()) {
                return null;
            }
            fos = new FileInputStream(file);
            os = new ObjectInputStream(fos);
            return os.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param assetFileName 必须是"path/filename"或"filename"
     */
    public static Object readAsset(Context context, String assetFileName) {
        ObjectInputStream ois = null;
        InputStream is = null;
        try {
            is = context.getResources().getAssets().open(assetFileName);
            ois = new ObjectInputStream(is);
            return ois.readObject();
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 通过文件名获取文件类型
     */
    public static int getFileType(String fileName) {
        int type;
        int index = fileName.lastIndexOf(".");
        String extendName = fileName.substring(index + 1);
        if (extendName.equalsIgnoreCase("mp3") || extendName.equalsIgnoreCase("wav") || extendName.equalsIgnoreCase("m4a")
                || extendName.equalsIgnoreCase("arm") || extendName.equalsIgnoreCase("caf")) {
            type = 1;
        } else if (extendName.equalsIgnoreCase("txt")) {
            type = 2;
        } else if (extendName.equalsIgnoreCase("png") || extendName.equalsIgnoreCase("jpg") || extendName.equalsIgnoreCase("gif") || extendName.equalsIgnoreCase("jpeg")) {
            type = 3;
        } else if (extendName.equalsIgnoreCase("pdf")) {
            type = 5;
        } else if (extendName.equalsIgnoreCase("doc") || extendName.equalsIgnoreCase("docx")) {
            type = 6;
        } else if (extendName.equalsIgnoreCase("ppt") || extendName.equalsIgnoreCase("pptx") || extendName.equalsIgnoreCase("pptx-ppt")) {
            type = 7;
        } else if (extendName.equalsIgnoreCase("xls") || extendName.equalsIgnoreCase("xlsx-excel") || extendName.equalsIgnoreCase("xlsx")) {
            type = 8;
        } else {
            return -1;
        }

        return type;
    }

    /**
     * 重命名文件
     *
     * @param path    文件目录
     * @param oldName 原来的文件名
     * @param newName 新文件名
     */
    public static int renameFile(String path, String oldName, String newName) {
        if (!oldName.equals(newName)) {
            File oldfile = new File(path + "/" + oldName);
            File newfile = new File(path + "/" + newName);
            if (!oldfile.exists()) {//重命名文件不存在
                return 2;
            }
            if (newfile.exists()) {//若在该目录下已经有一个文件和新文件名相同，则不允许重命名
                return 3;
            } else {
                oldfile.renameTo(newfile);
                return 1;
            }
        } else {
            return 1;
        }
    }

    /**
     * 文件复制
     *
     * @param sourceFile 源文件
     * @param targetFile 目标文件
     */
    public static boolean copyFile(File sourceFile, File targetFile) {
        boolean result = true;
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(sourceFile).getChannel();
            outputChannel = new FileOutputStream(targetFile).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        } finally {
            try {
                if (inputChannel != null) {
                    inputChannel.close();
                }

                if (outputChannel != null) {
                    outputChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 删除文件或目录
     */
    public static void deleteDir(File file) {
        if (file == null) {
            return;
        }
        if (file.isFile()) {
            if (file.exists()) {
                file.delete();
            }
        } else {
            if (file.exists()) {
                for (File tFile : file.listFiles()) {
                    if (tFile.isDirectory())
                        deleteDir(tFile);
                    else {
                        tFile.delete();
                    }
                }
                file.delete();
            }
        }
    }


    public static int getFileSize(File file) {
        if (file.listFiles() == null)
            return 0;
        int size = 0;
        for (File file1 : file.listFiles()) {
            if (file1.isDirectory()) {
                size += getFileSize(file1);
            } else {
                size += file1.length();
            }
        }
        return size;
    }

    public static String formatFileSize(long size) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (size < 1024) {
            fileSizeString = df.format((double) size) + "B";
        } else if (size < 1048576) {
            fileSizeString = df.format((double) size / 1024) + "K";
        } else if (size < 1073741824) {
            fileSizeString = df.format((double) size / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) size / 1073741824) + "G";
        }
        return fileSizeString;
    }

    public static String formatFileSize2(long size) {
        DecimalFormat df = new DecimalFormat("#");
        String fileSizeString = "";
        if (size < 1024) {
            fileSizeString = df.format((double) size) + "B";
        } else if (size < 1048576) {
            fileSizeString = df.format((double) size / 1024) + "K";
        } else if (size < 1073741824) {
            fileSizeString = df.format((double) size / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) size / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 指定要扫描的文件路径与文件扩展名列表, 返回指定扩展名的文件名的列表, 调用方不能是主线程
     *
     * @param root 文件路径,可以是文件目录或文件文件扩展名列表
     * @return 特殊情况, 如果文件扩展 名列表为null则返回所有文件
     */
    public static List<String> scanFile(String root, List<String> suffixList) throws IOException, OutOfMemoryError {
        if (TextUtils.isEmpty(root)) {
            return null;
        }

        File rootDir = new File(root);
        if (rootDir != null && rootDir.exists()) {
            List<String> scanResultList = new ArrayList<String>();
            File[] files = rootDir.listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    File file = files[i];
                    String path = file.getAbsolutePath(); //file.getCanonicalPath()改为getAbsolutePath(), 要gengeng确认
                    if (file.isFile()) {
                        if (isSpecfiedSuffixExist(path, suffixList)) {
                            scanResultList.add(path);
                        }
                    } else if (file.isDirectory() && path.indexOf("/.") == -1) {
                        // 忽略点文件（隐藏文件/文件夹）
                        List<String> childList = scanFile(path, suffixList);
                        if (childList != null && !childList.isEmpty()) {
                            scanResultList.addAll(childList);
                        }
                    }
                }
            }

            return scanResultList;
        } else {
            return null;
        }
    }

    /**
     * 判断一个文件是否是指定的后缀类型的文件
     *
     * @param path       文件路径
     * @param suffixList 扩展名列表
     * @return
     */
    public static boolean isSpecfiedSuffixExist(String path, List<String> suffixList) {
        if (TextUtils.isEmpty(path) || suffixList == null || suffixList.isEmpty()) {
            return true;
        }

        for (String suffix : suffixList) {
            try {
                if (path.endsWith(suffix)) {
                    return true;
                }
            } catch (NullPointerException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * 文件复制
     *
     * @param from
     * @param dest
     * @return
     * @throws IOException
     */
    public static boolean copy(String from, String dest) throws IOException {
        if (TextUtils.isEmpty(from) || TextUtils.isEmpty(dest)) {
            return false;
        }
        File file = new File(from);
        if (!file.exists()) {
            return false;
        }
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            inBuff = new BufferedInputStream(new FileInputStream(file));
            outBuff = new BufferedOutputStream(new FileOutputStream(new File(dest)));

            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
            return true;
        } finally {
            // 关闭流
            if (inBuff != null)
                try {
                    inBuff.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (outBuff != null)
                try {
                    outBuff.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }


    /**
     * 指定路径创建目录, 并提供指定momedia的接口
     *
     * @param nomedia : 是否需要nomedia文件，只有存图片的目录需要，有nomedia图片在相册不可见
     * @return 完整路径
     */
    public static String getPath(String path, boolean nomedia) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
            if (nomedia) {
                File nomediaFile = new File(path + File.separator + ".nomedia");
                try {
                    nomediaFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file.getAbsolutePath();
    }


    public static Intent openFile(Context context, String filePath) {
        File file = new File(filePath);
        if (!file.exists()) return null;
        /* 取得扩展名 */
        String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase();
        /* 依扩展名的类型决定MimeType */
        if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") ||
                end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
            return getAudioFileIntent(context, filePath);
        } else if (end.equals("3gp") || end.equals("mp4")) {
            return getAudioFileIntent(context, filePath);
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") ||
                end.equals("jpeg") || end.equals("bmp")) {
            return getImageFileIntent(context, filePath);
        } else if (end.equals("apk")) {
            return getApkFileIntent(context, filePath);
        } else if (end.equals("ppt")) {
            return getPptFileIntent(context, filePath);
        } else if (end.equals("xls")) {
            return getExcelFileIntent(context, filePath);
        } else if (end.equals("doc")) {
            return getWordFileIntent(context, filePath);
        } else if (end.equals("pdf")) {
            return getPdfFileIntent(context, filePath);
        } else if (end.equals("chm")) {
            return getChmFileIntent(context, filePath);
        } else if (end.equals("txt")) {
            return getTextFileIntent(context, filePath, false);
        } else {
            return getAllIntent(context, filePath);
        }
    }

    /**
     * Android获取一个用于打开APK文件的intent
     */
    public static Intent getAllIntent(Context context, String param) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
//        Uri uri = Uri.fromFile(new File(param));
        Uri uri = FileProvider7.getUriForFile(context, new File(param));

        intent.setDataAndType(uri, "*/*");
        return intent;
    }

    /**
     * Android获取一个用于打开APK文件的intent
     */
    public static Intent getApkFileIntent(Context context, String param) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
//        Uri uri = Uri.fromFile(new File(param));
        Uri uri = FileProvider7.getUriForFile(context, new File(param));

        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        return intent;
    }

    /**
     * Android获取一个用于打开Video文件的intent
     */
    public static Intent getVideoFileIntent(Context context, String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = FileProvider7.getUriForFile(context, new File(param));
        intent.setDataAndType(uri, "video/*");
        return intent;
    }

    /**
     * Android获取一个用于打开Audio文件的intent
     */
    public static Intent getAudioFileIntent(Context context, String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = FileProvider7.getUriForFile(context, new File(param));
        intent.setDataAndType(uri, "audio/*");
        return intent;
    }

    /**
     * Android获取一个用于打开Html文件的intent
     */
    public static Intent getHtmlFileIntent(String param) {
        Uri uri = Uri.parse(param).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(param).build();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, "text/html");
        return intent;
    }

    /**
     * Android获取一个用于打开Image文件的intent
     */
    public static Intent getImageFileIntent(Context context, String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = FileProvider7.getUriForFile(context, new File(param));
        intent.setDataAndType(uri, "image/*");
        return intent;
    }

    /**
     * Android获取一个用于打开PPT文件的intent
     */
    public static Intent getPptFileIntent(Context context, String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = FileProvider7.getUriForFile(context, new File(param));
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        return intent;
    }

    /**
     * Android获取一个用于打开Excel文件的intent
     */
    public static Intent getExcelFileIntent(Context context, String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = FileProvider7.getUriForFile(context, new File(param));
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        return intent;
    }

    /**
     * Android获取一个用于打开Word文件的intent
     */
    public static Intent getWordFileIntent(Context context, String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri uri = Uri.fromFile(new File(param));
        Uri uri = FileProvider7.getUriForFile(context, new File(param));

        intent.setDataAndType(uri, "application/msword");
        return intent;
    }

    /**
     * Android获取一个用于打开CHM文件的intent
     */
    public static Intent getChmFileIntent(Context context, String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri uri = Uri.fromFile(new File(param));
        Uri uri = FileProvider7.getUriForFile(context, new File(param));
        intent.setDataAndType(uri, "application/x-chm");
        return intent;
    }

    /**
     * Android获取一个用于打开文本文件的intent
     */
    public static Intent getTextFileIntent(Context context, String param, boolean paramBoolean) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (paramBoolean) {
            Uri uri1 = Uri.parse(param);
            intent.setDataAndType(uri1, "text/plain");
        } else {
//            Uri uri2 = Uri.fromFile(new File(param));
            Uri uri2 = FileProvider7.getUriForFile(context, new File(param));

            intent.setDataAndType(uri2, "text/plain");
        }
        return intent;
    }

    /**
     * Android获取一个用于打开PDF文件的intent
     */
    public static Intent getPdfFileIntent(Context context, String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri uri = Uri.fromFile(new File(param));
        Uri uri = FileProvider7.getUriForFile(context, new File(param));
        intent.setDataAndType(uri, "application/pdf");
        return intent;
    }

    /**
     * Android发送邮件
     */
    public static void sendMail(Context context, String mail) {
        if (TextUtils.isEmpty(mail)) {
            return;
        }
        Uri uri = Uri.parse("mailto:" + mail);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
//        intent.putExtra(Intent.EXTRA_CC, email); // 抄送人
        intent.putExtra(Intent.EXTRA_SUBJECT, "这是邮件的主题部分"); // 主题
        intent.putExtra(Intent.EXTRA_TEXT, "这是邮件的正文部分"); // 正文
        context.startActivity(Intent.createChooser(intent, "请选择邮件类应用"));
    }

    /**
     * 通过uri获取文件路径
     *
     * @param mUri
     * @return
     */
    public static String getFilePath(Uri mUri) {
        try {
            if (mUri.getScheme().equals("file")) {
                return mUri.getPath();
            } else {
                return getFilePathByUri(mUri);
            }
        } catch (FileNotFoundException ex) {
            return null;
        }
    }

    // 获取文件路径通过url
    private static String getFilePathByUri(Uri mUri) throws FileNotFoundException {
        String authenPicPath = "";
        if (mUri != null) {
            Cursor cursor = AntApplication.getInstance().getContentResolver().query(mUri, null, null, null, null);
            cursor.moveToFirst();
            authenPicPath = cursor.getString(1);

            cursor.close();
        }
        return authenPicPath;
    }

}