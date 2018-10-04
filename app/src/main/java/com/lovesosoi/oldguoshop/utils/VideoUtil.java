package com.lovesosoi.oldguoshop.utils;

import android.app.Activity;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;

import com.lookbi.baselib.utils.DialogUtils;
import com.lookbi.baselib.utils.LogUtil;
import com.qiniu.pili.droid.shortvideo.PLShortVideoTranscoder;
import com.qiniu.pili.droid.shortvideo.PLVideoSaveListener;

import static com.qiniu.pili.droid.shortvideo.PLErrorCode.ERROR_LOW_MEMORY;
import static com.qiniu.pili.droid.shortvideo.PLErrorCode.ERROR_NO_VIDEO_TRACK;
import static com.qiniu.pili.droid.shortvideo.PLErrorCode.ERROR_SRC_DST_SAME_FILE_PATH;

public class VideoUtil {

    /**
     * 压缩视频
     *
     * @param mContext
     * @param filepath
     */
    public static void compressVideoResouce(final Activity mContext, String filepath, final
    OnCompressVideoLisenter lisenter) {
        if (TextUtils.isEmpty(filepath)) {
            ToastUtil.show("请先选择转码文件！");
            return;
        }

        String f = filepath.substring(filepath.lastIndexOf(".") + 1);
        if (TextUtils.isEmpty(f)) {
            ToastUtil.show("请上传正确的文件");
            return;
        }

        String p = UtilTools.getSdCardPath() + "/nzjy/video/";
        UtilTools.isFoldersExists(p);
        String vp = p + "upload_nzjy_video." + f;
        UtilTools.deleteFile(vp);
        //PLShortVideoTranscoder初始化，三个参数，第一个context，第二个要压缩文件的路径，第三个视频压缩后输出的路径
        PLShortVideoTranscoder mShortVideoTranscoder = new PLShortVideoTranscoder(mContext,
                filepath, vp);
        MediaMetadataRetriever retr = new MediaMetadataRetriever();
        retr.setDataSource(filepath);
        String height = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);
        // 视频高度
        String width = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH); //
        // 视频宽度
        String rotation = retr.extractMetadata(MediaMetadataRetriever
                .METADATA_KEY_VIDEO_ROTATION); // 视频旋转方向
        int transcodingBitrateLevel = 6;//我这里选择的2500*1000压缩，这里可以自己选择合适的压缩比例
        DialogUtils.show(mContext, "处理中");
        mShortVideoTranscoder.transcode(Integer.parseInt(width), Integer.parseInt(height),
                getEncodingBitrateLevel(transcodingBitrateLevel), false, new PLVideoSaveListener() {
                    @Override
                    public void onSaveVideoSuccess(String s) {
                        LogUtil.e("onSaveVideoSuccess-" + s);
                        DialogUtils.dismiss();
                        if (lisenter != null) {
                            lisenter.onSuccess(s);
                        }
                    }

                    @Override
                    public void onSaveVideoFailed(final int errorCode) {
                        DialogUtils.dismiss();

//                        if (lisenter!=null){
//                            lisenter.onSuccess("该文件没有视频信息");
//                        }
                        LogUtil.e("onSaveVideoFailed-" + errorCode);
//                        mContext.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
                        switch (errorCode) {
                            case ERROR_NO_VIDEO_TRACK:
                                if (lisenter != null) {
                                    lisenter.onSuccess("该文件没有视频信息");
                                }
//                                        ToastUtil.show("该文件没有视频信息！");
                                break;
                            case ERROR_SRC_DST_SAME_FILE_PATH:
                                if (lisenter != null) {
                                    lisenter.onSuccess("源文件路径和目标路径不能相同");
                                }
//                                        ToastUtil.show("源文件路径和目标路径不能相同！");
                                break;
                            case ERROR_LOW_MEMORY:
                                if (lisenter != null) {
                                    lisenter.onSuccess("手机内存不足，无法对该视频进行压缩！");
                                }
//                                        ToastUtil.show("手机内存不足，无法对该视频进行时光倒流！");
                                break;
                            default:
                                if (lisenter != null) {
                                    lisenter.onSuccess("视频压缩失败");
                                }
//                                        ToastUtil.show("transcode failed: " + errorCode);
                        }
//                            }
//                        });
                    }

                    @Override
                    public void onSaveVideoCanceled() {
//                LogUtil.e("onSaveVideoCanceled");
                    }

                    @Override
                    public void onProgressUpdate(float percentage) {
//                LogUtil.e("onProgressUpdate==========" + percentage);
                    }
                });

    }

    /**
     * 设置压缩质量
     *
     * @param position
     * @return
     */
    private static int getEncodingBitrateLevel(int position) {
        return ENCODING_BITRATE_LEVEL_ARRAY[position];
    }

    /**
     * 选的越高文件质量越大，质量越好
     */
    public static final int[] ENCODING_BITRATE_LEVEL_ARRAY = {
            500 * 1000,
            800 * 1000,
            1000 * 1000,
            1200 * 1000,
            1600 * 1000,
            2000 * 1000,
            2500 * 1000,
            4000 * 1000,
            8000 * 1000,
    };


    public interface OnCompressVideoLisenter {
        void onSuccess(String path);

        void onFail(String e);
    }
//    OnCompressVideoLisenter lisenter;
//
//    public void setOnCompressVideoLisenter(OnCompressVideoLisenter lisenter){
//        this.lisenter=lisenter;
//    }


}
