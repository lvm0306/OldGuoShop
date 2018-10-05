package com.lovesosoi.oldguoshop.ui.contract_us;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lookbi.baselib.base.BaseActivity;
import com.lookbi.baselib.base.BasePresenterImpl;
import com.lovesosoi.oldguoshop.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContractUsActivity extends BaseActivity {

    @BindView(R.id.view_top)
    View viewTop;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;

    @Override
    protected BasePresenterImpl createPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_contract_us;
    }

    @Override
    protected int getFristTopViewId() {
        return R.id.view_top;
    }

    @Override
    protected void initView() {
        initBlackToolbar(toolbar);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

    }

    @OnClick({R.id.tv_phone, R.id.tv_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_phone:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:13946084875"));
                startActivity(intent);
                break;
            case R.id.tv_address:
                //根据地名打开地图应用显示，字符串要记得编码！！
                String encodedName = Uri.encode("黑龙江省测绘局");
                Uri locationUri = Uri.parse("geo:0,0?q=" + encodedName);
                //根据经纬度打开地图显示，?z=11表示缩放级别，范围为1-23
                Intent intent2 = new Intent(Intent.ACTION_VIEW);
                Intent it2 = Intent.createChooser(intent2, "请选择地图软件");
                intent2.setData(locationUri);
                if (intent2.resolveActivity(getPackageManager()) != null) {
                    startActivity(it2);
                }

                break;
        }
    }
}
