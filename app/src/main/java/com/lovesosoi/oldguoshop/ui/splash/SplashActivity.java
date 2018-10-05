package com.lovesosoi.oldguoshop.ui.splash;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.lookbi.baselib.base.BaseActivity;
import com.lookbi.baselib.base.BasePresenterImpl;
import com.lovesosoi.oldguoshop.R;
import com.lovesosoi.oldguoshop.ui.main.MainActivity;


/**
 * Created by zzz on 2017/10/10.
 */

public class SplashActivity extends BaseActivity {

    private Handler handler;
    boolean isDestroy = false;

    @Override
    protected BasePresenterImpl createPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected int getFristTopViewId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
//        checkPermission();
        next();
    }


    /**
     * 检查用户设备是否拥有读写SDCard权限
     */
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || this.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义)
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA}, 0x1000);
            } else {
                next();
            }
        } else {
            next();
        }

    }

    private void next() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toNextActivity();
            }
        }, 3000);
    }


    private void toNextActivity() {
        if (isDestroy) {
            return;
        }
        isDestroy = true;
      /*  if (AppContext.getInstance().isLogin(this)) {
            Intent in = new Intent(this, MainActivity.class);
            startActivity(in);
        } else {
            Intent in = new Intent(this, LoginActivity.class);
            startActivity(in);
        }*/
        startActivity(MainActivity.class);
        finish();
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
        handler = null;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 0x1000) {
            next();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

}
