package guopuran.bwie.com.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class Mypager extends PagerAdapter {
    private List<String> list;
    private Context context;

    public Mypager(Context context) {
        this.context = context;
        list=new ArrayList<>();

    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public String getitem(int position){
        return list.get(position);
    }

    @Override
    public int getCount() {
        return issize()==0?list.size():5000;
    }
    public int issize(){
        return list.size();
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView=new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageLoader.getInstance().displayImage(getitem(position%list.size()),imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
