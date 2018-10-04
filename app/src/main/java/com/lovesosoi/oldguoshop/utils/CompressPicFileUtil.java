package com.lovesosoi.oldguoshop.utils;





import com.lookbi.baselib.lisenter.OnLubanLisenter;
import com.lookbi.baselib.utils.LogUtil;
import com.lovesosoi.oldguoshop.AppContext;

import java.io.File;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by zhangyisheng on 2017/10/11.
 * 图片压缩工具
 */

public class CompressPicFileUtil {

    /**
     * 使用Luban进行图片异步压缩
     * @param file
     * @param lisenter
     */
    public static void compressFile(File file, final OnLubanLisenter lisenter) {
        if (!UtilTools.isExitsSdcard()) {
            ToastUtil.show("SD卡不存在");
            return;
        }
        LogUtil.e("压缩前file大小="+file.length());
        Luban.with(AppContext.getInstance().getContext())
                .load(file)                                   // 传人要压缩的图片列表
                .ignoreBy(80)                                  // 忽略不压缩图片的大小
                .setTargetDir(UtilTools.getSdCardPath())                        // 设置压缩后文件存储位置
                .setCompressListener(new OnCompressListener() { //设置回调
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }

                    @Override
                    public void onSuccess(File file) {
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                        LogUtil.e("压缩后file大小="+file.length());
                        if (lisenter != null) {
                            lisenter.success(file);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                        LogUtil.e(e.getMessage());
                        if (lisenter != null) {
                            lisenter.fail();
                        }
                    }
                }).launch();    //启动压缩

    }
}
