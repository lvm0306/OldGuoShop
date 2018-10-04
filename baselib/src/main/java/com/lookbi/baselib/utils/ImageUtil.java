package com.lookbi.baselib.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lookbi.baselib.views.CircleImageView;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by zhangyisheng on 2018/2/5.
 */

public class ImageUtil {

    private static final String TAG = "ImageUtil";


    //图片显示
    public static Load with(Context context) {
        return new Load(context);
    }

    public static class Load {
        private Context context;

        public Load(Context context) {
            this.context = context;
        }

        public Into load(String url) {
            return new Into(context, url);
        }

        public Into load(int resId) {
            return new Into(context, resId);
        }

        public Into load(File url) {
            return new Into(context, url);
        }

        public class Into {
            private int placeResid;
            private int errResid;
            private String url;
            private File file;
            private int resId;
            private Context context;

            public Into(Context context, String url) {
                this.context = context;
                this.url = url;
            }

            public Into(Context context, File file) {
                this.context = context;
                this.file = file;
            }

            public Into(Context context, int resId) {
                this.context = context;
                this.resId = resId;
            }

            public Into placeholder(int placeResid) {
                this.placeResid = placeResid;
                return this;
            }

            public Into error(int errResid) {
                this.errResid = errResid;
                return this;
            }

            public void into(ImageView imageView) {
                if (context == null) {
                    LogUtil.e("ImageUtil-context =null");
                    return;
                }
                if (url == null) {
                    if (file == null) {
                        if (resId==0){
                            LogUtil.e("ImageUtil - url or file or resId is null");
                            return;
                        }
                    }
                }
                if (TextUtils.isEmpty(url)) {
                    if (file == null) {
                        if (resId==0){
                            LogUtil.e("ImageUtil - url or file or resId is isEmpty");
                            return;
                        }
                    }
                }
                if (imageView == null) {
                    LogUtil.e("ImageUtil-imageView =null");
                    return;
                }
                try {
                    if (imageView instanceof CircleImageView) {
                        if (TextUtils.isEmpty(url)) {
                            if (resId != 0) {
                                Glide.with(context)
                                        .load(resId)
                                        .dontAnimate()
                                        .centerCrop()
                                        .placeholder(placeResid)
                                        .error(errResid)
                                        .into(imageView);
                            } else {
                                Glide.with(context)
                                        .load(file)
                                        .dontAnimate()
                                        .centerCrop()
                                        .placeholder(placeResid)
                                        .error(errResid)
                                        .into(imageView);
                            }

                        } else {
                            Glide.with(context)
                                    .load(url)
                                    .dontAnimate()
                                    .centerCrop()
                                    .placeholder(placeResid)
                                    .error(errResid)
                                    .into(imageView);
                        }


                    } else {

                        if (TextUtils.isEmpty(url)) {
                            if (resId != 0) {
                                Glide.with(context)
                                        .load(resId)
                                        .placeholder(placeResid)
                                        .error(errResid)
                                        .into(imageView);
                            } else {
                                Glide.with(context)
                                        .load(file)
                                        .placeholder(placeResid)
                                        .error(errResid)
                                        .into(imageView);
                            }

                        } else {
                            Glide.with(context)
                                    .load(url)
                                    .placeholder(placeResid)
                                    .error(errResid)
                                    .into(imageView);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    if (TextUtils.isEmpty(url)) {
                        if (resId != 0) {
                            Picasso.with(context)
                                    .load(resId)
                                    .placeholder(placeResid)
                                    .error(errResid)
                                    .into(imageView);
                        } else {
                            Picasso.with(context)
                                    .load(file)
                                    .placeholder(placeResid)
                                    .error(errResid)
                                    .into(imageView);
                        }

                    } else {
                        Picasso.with(context)
                                .load(url)
                                .placeholder(placeResid)
                                .error(errResid)
                                .into(imageView);
                    }
                }


                placeResid = 0;
                errResid = 0;
                url = "";
                file = null;
                resId = 0;
                context = null;
            }


        }

    }

}
