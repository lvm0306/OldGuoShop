package com.lovesosoi.oldguoshop.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.lookbi.baselib.base.BaseFragment;
import com.lookbi.baselib.base.BasePresenterImpl;
import com.lookbi.baselib.views.CircleImageView;
import com.lovesosoi.oldguoshop.R;
import com.lovesosoi.oldguoshop.ui.news.NewsListActivity;
import com.lovesosoi.oldguoshop.ui.notice.NoticeInfoActivity;
import com.lovesosoi.oldguoshop.ui.order.OrderActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuFragment extends BaseFragment {

    @BindView(R.id.iv_pic)
    CircleImageView ivPic;
    @BindView(R.id.rv_title)
    RecyclerView rvTitle;
    @BindView(R.id.tv_notice_info)
    TextView tvNoticeInfo;
    @BindView(R.id.tv_notice_time)
    TextView tvNoticeTime;
    @BindView(R.id.rl_news)
    RelativeLayout rlNews;
    @BindView(R.id.rv_news)
    RecyclerView rvNews;
    TitleAdapter mTitleAdapter;
    NewsAdapter mNewsAdapter;
    int[] mPics = new int[]{R.mipmap.ic_shitang, R.mipmap.ic_bbq, R.mipmap.ic_mlt, R.mipmap.ic_baozi, R.mipmap.ic_other};
    String[] mTitles = new String[]{"食堂", "烧烤", "麻辣烫", "包子", "其他"};

    public MenuFragment() {
    }

    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected BasePresenterImpl createPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_menu;
    }

    @Override
    protected void immersionInit() {
        super.immersionInit();
        ImmersionBar.with(this).statusBarColor(R.color.main_color).statusBarDarkFont(true, 0.2f).init();
    }

    @Override
    protected int getFristTopViewId() {
        return 0;
    }

    @Override
    public void initView() {
        mTitleAdapter = new TitleAdapter();
        GridLayoutManager titleManager = new GridLayoutManager(getContext(), 5);
        rvTitle.setLayoutManager(titleManager);
        rvTitle.setAdapter(mTitleAdapter);

        mNewsAdapter = new NewsAdapter();
        LinearLayoutManager newManager = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        rvNews.setLayoutManager(newManager);
        rvNews.setAdapter(mNewsAdapter);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.rv_title, R.id.tv_notice_info, R.id.tv_notice_time, R.id.ll_notice,R.id.rl_news})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rv_title:
                break;
            case R.id.tv_notice_info:

                startActivity(NoticeInfoActivity.class);
                break;
            case R.id.tv_notice_time:

                startActivity(NoticeInfoActivity.class);
                break;
            case R.id.ll_notice:
                startActivity(NoticeInfoActivity.class);
                break;
            case R.id.rl_news:
                startActivity(NewsListActivity.class);
                break;
        }
    }


    class TitleAdapter extends RecyclerView.Adapter {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_main_title, parent, false);
            return new TitleViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            TitleViewHolder viewHolder = (TitleViewHolder) holder;
            viewHolder.ivPic.setImageResource(mPics[position]);
            viewHolder.tvName.setText(mTitles[position]);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(OrderActivity.class);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mPics.length;
        }

        class TitleViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.iv_pic)
            ImageView ivPic;
            @BindView(R.id.tv_name)
            TextView tvName;

            TitleViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    class NewsAdapter extends RecyclerView.Adapter {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_main_news, parent, false);

            return new NewsViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 4;
        }

        class NewsViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_info)
            TextView tvInfo;
            @BindView(R.id.tv_time)
            TextView tvTime;

            NewsViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

}
