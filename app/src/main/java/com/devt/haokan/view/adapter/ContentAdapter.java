package com.devt.haokan.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.devt.haokan.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Devt on 16/6/2.
 * Email:devt@foxmail.com
 */
public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder>{

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<String> list;
    private int lastPosition = -1;

    public ContentAdapter(Context context,List<String> list){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         return  new ContentViewHolder(mLayoutInflater.inflate(R.layout.item_imgview,parent,false));
    }

    @Override
    public void onBindViewHolder(ContentViewHolder holder, int position) {
        Glide.with(mContext)
                .load(list.get(position))
                .crossFade()
                .animate(R.anim.zoom_in)
             //   .transform(new CircleTransform(mContext))
                .into(((ContentViewHolder) holder).imageView );


         setAnimation(((ContentViewHolder) holder).imageView, position);
    }



    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class ContentViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.nice_img)
        ImageView imageView;
        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    private void setAnimation(View viewToAnimate, int position)
    {
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.zoom_in);
        viewToAnimate.startAnimation(animation);
        lastPosition = position;

    }

    public static class CircleTransform extends BitmapTransformation {
        public CircleTransform(Context context) {
            super(context);
        }

        @Override protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            // TODO this could be acquired from the pool too
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override public String getId() {
            return getClass().getName();
        }
    }

}
