package com.lovesosoi.oldguoshop.ui.news;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lookbi.baselib.base.BaseActivity;
import com.lookbi.baselib.base.BasePresenterImpl;
import com.lovesosoi.oldguoshop.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListActivity extends BaseActivity {

    @BindView(R.id.view_top)
    View viewTop;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_info)
    RecyclerView rvInfo;
    @BindView(R.id.srl_info)
    SmartRefreshLayout srlInfo;
    NewsListAdapter mAdapter;
    Context mContext;

    @Override
    protected BasePresenterImpl createPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_news_list;
    }

    @Override
    protected int getFristTopViewId() {
        return R.id.view_top;
    }

    @Override
    protected void initView() {
        mContext = this;
        initBlackToolbar(toolbar);
        mAdapter = new NewsListAdapter();
        rvInfo.setLayoutManager(new LinearLayoutManager(mContext, OrientationHelper.VERTICAL, false));
        rvInfo.setAdapter(mAdapter);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

    }

    class NewsListAdapter extends RecyclerView.Adapter {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_news_list, parent, false);
            return new ViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(NewsInfoActivity.class);
                }
            });

        }

        @Override
        public int getItemCount() {
            return 4;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_info)
            TextView tvInfo;
            @BindView(R.id.tv_time)
            TextView tvTime;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
