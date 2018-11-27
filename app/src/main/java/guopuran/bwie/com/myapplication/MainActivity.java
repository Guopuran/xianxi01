package guopuran.bwie.com.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private String lujing="http://www.zhaoapi.cn/product/getProductDetail?pid=1";
    private TextView textView;
    private List<String> list;
    private ViewPager viewPager;
    private Mypager mypager;
    private LinearLayout lin;
    private int index=-1;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            sendEmptyMessageDelayed(0,2000);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取资源ID
        lin = findViewById(R.id.lin);
        viewPager = findViewById(R.id.viewpager);
        textView = findViewById(R.id.textview);
        //设置适配器
        mypager = new Mypager(this);
        viewPager.setAdapter(mypager);
        list = new ArrayList<>();
        initdata();
        //指示器的选择
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                //改变当前的点
                lin.getChildAt(i%lin.getChildCount()).setSelected(true);
                //还原原来的点
                if (index>=0){
                    lin.getChildAt(index%lin.getChildCount()).setSelected(false);
                }
                index=i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }
    //设置小圆点
    public void initiew(int size) {
        lin.removeAllViews();
        for (int i=0;i<size;i++){
            ImageView imageView=new ImageView(this);
            imageView.setBackgroundResource(R.drawable.select01);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
            );
            int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
            params.leftMargin=margin;
            params.rightMargin=margin;
            lin.addView(imageView,params);
        }
    }

    private void initdata() {
        NetUtil.yibu(lujing, Bean.class, new NetUtil.CallBack<Bean>() {



            @Override
            public void getdata(Bean o) {
                cc(o.getData().getImages());
                mypager.setList(list);
                //设置标题
                textView.setText(o.getData().getTitle());
                initiew(list.size());
                int center = mypager.getCount()/2;
                center=center-center%list.size();
                viewPager.setCurrentItem(center);
                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0,2000);
        }
        });
    }
    //递归截取字符串
    public void cc(String images){
        //找到 | 的位置
        int index=images.indexOf("|");
        if (index>=0){
            String pian=images.substring(0,index);
            list.add(pian);
            cc(images.substring(index+1,images.length()));
        }else{
            list.add(images);
        }
    }
}
