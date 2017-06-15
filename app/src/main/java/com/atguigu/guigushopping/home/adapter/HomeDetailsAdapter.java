package com.atguigu.guigushopping.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.guigushopping.R;
import com.atguigu.guigushopping.home.activity.GoodsInfoActivity;
import com.atguigu.guigushopping.home.activity.WebViewActivity;
import com.atguigu.guigushopping.home.bean.CommodityDetailsBean;
import com.atguigu.guigushopping.home.bean.GoodsBean;
import com.atguigu.guigushopping.home.bean.WebViewBean;
import com.atguigu.guigushopping.home.view.NoScrollGridView;
import com.atguigu.guigushopping.utils.GlideImageLoader;
import com.atguigu.guigushopping.utils.NetConstants;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.transformer.TabletTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;


/**
 * Created by Administrator on 2017/6/12.
 */

public class HomeDetailsAdapter extends RecyclerView.Adapter {

    /**
     * 六种类型
     */
    /**
     * 横幅广告
     */
    public static final int BANNER = 0;
    /**
     * 频道
     */
    public static final int CHANNEL = 1;

    /**
     * 活动
     */
    public static final int ACT = 2;

    /**
     * 秒杀
     */
    public static final int SECKILL = 3;
    /**
     * 推荐
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;


    private static Context context;
    public CommodityDetailsBean.ResultBean beanDatas;
    private final LayoutInflater inflater;


    public static final String GOODS_BEAN = "goods_bean";


    public static final String WEBVIEW_BEAN = "webView";

    public HomeDetailsAdapter(Context context, CommodityDetailsBean.ResultBean beanDatas) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.beanDatas = beanDatas;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHodler(inflater.inflate(R.layout.home_banner_layout, null));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHodler(inflater.inflate(R.layout.home_channel_layout, null));
        } else if (viewType == ACT) {
            return new ACTViewHodler(inflater.inflate(R.layout.home_act_layout, null));
        } else if (viewType == SECKILL) {
            return new SeckillViewHodler(inflater.inflate(R.layout.home_seckill_layout, null));
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHodler(inflater.inflate(R.layout.home_recommend_layout, null));
        } else if (viewType == HOT) {
            return new HotViewHodler(inflater.inflate(R.layout.home_hot_layout, null));
        }

        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (beanDatas != null) {
            if (getItemViewType(position) == BANNER) {
                BannerViewHodler bannerViewHodler = (BannerViewHodler) viewHolder;

                bannerViewHodler.setData(beanDatas.getBanner_info());


            } else if (getItemViewType(position) == CHANNEL) {
                ChannelViewHodler channelViewHodler = (ChannelViewHodler) viewHolder;

                channelViewHodler.setData(beanDatas.getChannel_info());

            } else if (getItemViewType(position) == ACT) {
                ACTViewHodler actViewHodler = (ACTViewHodler) viewHolder;
                actViewHodler.setData(beanDatas.getAct_info());
            } else if (getItemViewType(position) == SECKILL) {
                SeckillViewHodler seckillViewHodler = (SeckillViewHodler) viewHolder;
                seckillViewHodler.setData(beanDatas.getSeckill_info());
            } else if (getItemViewType(position) == RECOMMEND) {
                RecommendViewHodler recommendViewHodler = (RecommendViewHodler) viewHolder;
                recommendViewHodler.setData(beanDatas.getRecommend_info());
            } else if (getItemViewType(position) == HOT) {
                HotViewHodler hotViewHodler = (HotViewHodler) viewHolder;
                hotViewHodler.setData(beanDatas.getHot_info());

            }


        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {

        int currViewType = 0;
        if (position == 0) {
            currViewType = BANNER;
        } else if (position == 1) {
            currViewType = CHANNEL;
        } else if (position == 2) {
            currViewType = ACT;
        } else if (position == 3) {
            currViewType = SECKILL;
        } else if (position == 4) {
            currViewType = RECOMMEND;
        } else if (position == 5) {
            currViewType = HOT;
        }

        return currViewType;

    }

//    //向adapter传递数据
//    public void setAdapterDatas(CommodityDetailsBean.ResultBean beanDatas) {
//        this.beanDatas = beanDatas;
//        LogUtils.e("TAG", "beanDatas" + beanDatas);
//        notifyDataSetChanged();
//    }


    public static class BannerViewHodler extends RecyclerView.ViewHolder {

        private Banner banner;

        public BannerViewHodler(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(final List<CommodityDetailsBean.ResultBean.BannerInfoBean> banerDatas) {

            ArrayList<String> images = new ArrayList<>();
            for (int i = 0; i < banerDatas.size(); i++) {
                images.add(NetConstants.BASE_URL_IMAGE + banerDatas.get(i).getImage());
            }
            //简单使用

            banner.setImages(images)
                    .setImageLoader(new GlideImageLoader())
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
//                            Toast.makeText(context, "position==" + position, Toast.LENGTH_SHORT).show();
                            if (position < banerDatas.size()) {
                                String product_id = "";
                                String name = "";
                                String cover_price = "";
                                if (position == 0) {
                                    product_id = "627";
                                    cover_price = "32.00";
                                    name = "剑三T恤批发";
                                } else if (position == 1) {
                                    product_id = "21";
                                    cover_price = "8.00";
                                    name = "同人原创】剑网3 剑侠情缘叁 Q版成男 口袋胸针";
                                } else {
                                    product_id = "1341";
                                    cover_price = "50.00";
                                    name = "【蓝诺】《天下吾双》 剑网3同人本";
                                }
                                String image = banerDatas.get(position).getImage();
                                GoodsBean goodsBean = new GoodsBean();
                                goodsBean.setName(name);
                                goodsBean.setCover_price(cover_price);
                                goodsBean.setFigure(image);
                                goodsBean.setProduct_id(product_id);

                                Intent intent = new Intent(context, GoodsInfoActivity.class);
                                intent.putExtra(GOODS_BEAN, goodsBean);
                                context.startActivity(intent);
                            }
                        }

                    }).start();

        }

    }

    static class ChannelViewHodler extends RecyclerView.ViewHolder {

        private List<CommodityDetailsBean.ResultBean.ChannelInfoBean> channelData;

        private GridView gv;

        public ChannelViewHodler(View itemView) {
            super(itemView);
            gv = (GridView) itemView.findViewById(R.id.gv);
        }

        public void setData(final List<CommodityDetailsBean.ResultBean.ChannelInfoBean> channelData) {
            this.channelData = channelData;

            //装配数据
            ChannelAdapter channelAdapter = new ChannelAdapter(context, channelData);
            gv.setAdapter(channelAdapter);

            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CommodityDetailsBean.ResultBean.ChannelInfoBean channelInfoBean = channelData.get(position);
                    Toast.makeText(context, channelInfoBean.getChannel_name().toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }


    }

    static class ACTViewHodler extends RecyclerView.ViewHolder {

        private List<CommodityDetailsBean.ResultBean.ActInfoBean> actDatas;
        private ViewPager act_viewpager;

        public ACTViewHodler(View itemView) {
            super(itemView);
            act_viewpager = (ViewPager) itemView.findViewById(R.id.act_viewpager);
        }

        public void setData(final List<CommodityDetailsBean.ResultBean.ActInfoBean> actDatas) {
            this.actDatas = actDatas;

            MyPagerAdapter myPagerAdapter = new MyPagerAdapter(context, actDatas);
            act_viewpager.setAdapter(myPagerAdapter);

            act_viewpager.setPageMargin(20);
            act_viewpager.setPageTransformer(true, new TabletTransformer());

            myPagerAdapter.setOnPagerItemClickListener(new MyPagerAdapter.OnPagerItemClickListener() {


                @Override
                public void onPagerItemClick(int position) {
//                    String name = actDatas.get(position).getName();
//                    Toast.makeText(context, name.toString(), Toast.LENGTH_SHORT).show();

                    WebViewBean webViewBean = new WebViewBean();
                    webViewBean.setName(actDatas.get(position).getName());
                    webViewBean.setIcon_url(actDatas.get(position).getIcon_url());
                    webViewBean.setUrl(NetConstants.BASE_URL_IMAGE + actDatas.get(position).getUrl());

                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra(WEBVIEW_BEAN, webViewBean);
                    context.startActivity(intent);

                }
            });
        }
    }

    static class SeckillViewHodler extends RecyclerView.ViewHolder {

        @BindView(R.id.countdownview)
        CountdownView countdownview;
        @BindView(R.id.tv_more_seckill)
        TextView tvMoreSeckill;
        @BindView(R.id.recyclerview)
        RecyclerView recyclerview;

        private long time;
        private CommodityDetailsBean.ResultBean.SeckillInfoBean seckillData;

        private boolean isFrist = false;

        Handler handler = new Handler();

        public SeckillViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void startRefreshTime() {

            handler.postDelayed(myRunnable, 10);

        }

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                long currentTime = System.currentTimeMillis();
                if (currentTime >= Long.parseLong(seckillData.getEnd_time())) {
                    handler.removeCallbacksAndMessages(null);
                } else {
                    countdownview.updateShow(Long.parseLong(seckillData.getEnd_time()) - currentTime);
                    handler.postDelayed(myRunnable, 1000);
                }

            }
        };


        public void setData(final CommodityDetailsBean.ResultBean.SeckillInfoBean seckillData) {
            this.seckillData = seckillData;

            time = Long.parseLong(seckillData.getEnd_time()) - Long.parseLong(seckillData.getStart_time());
            countdownview.start(time);

            //设置布局管理器
            recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayout.HORIZONTAL, false));

            SeckillRecyclerViewAdapter seckillRecyclerViewAdapter = new SeckillRecyclerViewAdapter(context, seckillData);

            recyclerview.setAdapter(seckillRecyclerViewAdapter);


            if (!isFrist) {

                long totalTime = Long.parseLong(seckillData.getEnd_time()) - Long.parseLong(seckillData.getStart_time());

                // 校对倒计时
                long curTime = System.currentTimeMillis();

                //重新设置结束数据时间
                seckillData.setEnd_time((curTime + totalTime + ""));


                //开始刷新
                startRefreshTime();


            }


            seckillRecyclerViewAdapter.setOnPagerItemChangerListener(new SeckillRecyclerViewAdapter.onPagerItemChangerListener() {
                @Override
                public void onPagerChangerListener(int position) {
//                    String name = seckillData.getList().get(position).getName();
//                    Toast.makeText(context, name.toString(), Toast.LENGTH_SHORT).show();

                    String name = seckillData.getList().get(position).getName();
                    String figure = seckillData.getList().get(position).getFigure();
                    String cover_price = seckillData.getList().get(position).getCover_price();
                    String product_id = seckillData.getList().get(position).getProduct_id();
                    GoodsBean goodsBean = new GoodsBean(name, cover_price, figure, product_id);


                    Intent intent = new Intent(context, GoodsInfoActivity.class);
                    intent.putExtra(GOODS_BEAN, goodsBean);
                    context.startActivity(intent);


                }
            });

        }


    }

    public static class RecommendViewHodler extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_more_recommend)
        TextView tvMoreRecommend;
        @BindView(R.id.gv_recommend)
        GridView gvRecommend;
        private List<CommodityDetailsBean.ResultBean.RecommendInfoBean> recommendDatas;

        public RecommendViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(final List<CommodityDetailsBean.ResultBean.RecommendInfoBean> recommendDatas) {
            this.recommendDatas = recommendDatas;

            final RecommendGridViewAdapter adapter = new RecommendGridViewAdapter(context, recommendDatas);
            gvRecommend.setAdapter(adapter);


            //设置点击事件
            gvRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CommodityDetailsBean.ResultBean.RecommendInfoBean recommendInfoBean = recommendDatas.get(position);
//                    String name = recommendInfoBean.getName();
//                    Toast.makeText(context, name.toString(), Toast.LENGTH_SHORT).show();

                    String name = recommendDatas.get(position).getName();
                    String figure = recommendDatas.get(position).getFigure();
                    String product_id = recommendDatas.get(position).getProduct_id();
                    String cover_price = recommendDatas.get(position).getCover_price();
                    GoodsBean goodsBean = new GoodsBean(name, cover_price, figure, product_id);


                    Intent intent = new Intent(context, GoodsInfoActivity.class);
                    intent.putExtra(GOODS_BEAN, goodsBean);
                    context.startActivity(intent);


                }
            });

        }
    }


    static class HotViewHodler extends RecyclerView.ViewHolder {

        private List<CommodityDetailsBean.ResultBean.HotInfoBean> hotData;

        @BindView(R.id.tv_more_hot)
        TextView tvMoreHot;
        @BindView(R.id.gv_hot)
        NoScrollGridView gvHot;

        public HotViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(final List<CommodityDetailsBean.ResultBean.HotInfoBean> hotData) {
            this.hotData = hotData;

            HotGridViewAdapter adapter = new HotGridViewAdapter(context, this.hotData);
            gvHot.setAdapter(adapter);

            gvHot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    String cover_price = hotData.get(position).getCover_price();
                    String name = hotData.get(position).getName();
                    String figure = hotData.get(position).getFigure();
                    String product_id = hotData.get(position).getProduct_id();
                    GoodsBean goodsBean = new GoodsBean(name, cover_price, figure, product_id);


                    Intent intent = new Intent(context, GoodsInfoActivity.class);
                    intent.putExtra(GOODS_BEAN, goodsBean);
                    context.startActivity(intent);


                }
            });


        }
    }

}
