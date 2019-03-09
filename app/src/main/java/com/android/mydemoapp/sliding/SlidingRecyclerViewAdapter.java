package com.android.mydemoapp.sliding;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mydemoapp.R;
import com.android.mydemoapp.util.ScreenUtils;

import java.util.List;

public class SlidingRecyclerViewAdapter extends RecyclerView.Adapter<SlidingRecyclerViewAdapter.ViewHolder> implements SlidingButtonView.SlidingButtonListener {

    protected List<String> mList;  //数据源
    private Context mContext;
    private SlidingButtonView mMenu = null;
    private SlidingViewClickListener slidingViewClickListener;

    /**
     * 事件接口
     */
    public interface SlidingViewClickListener {
        void onItemClick(View view, int position);

        void onLongItemClick(View view, int position);

        void onDeleteBtnCilck(View view, int position);
    }

    public void setSlidingViewClickListener(SlidingViewClickListener slidingViewClickListener) {
        this.slidingViewClickListener = slidingViewClickListener;
    }

    public SlidingRecyclerViewAdapter(Context context, List<String> datas) {
        this.mList = datas;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_sliding_delelte, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        ((SlidingButtonView) v).setSlidingButtonListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTextView.setText(mList.get(position));
        //设置内容布局的宽为屏幕宽度
        holder.mViewGroup.getLayoutParams().width = ScreenUtils.getScreenWidth();
        //item点击事件
        holder.mViewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuIsOpen()) { //判断是否有删除菜单打开
                    closeMenu(); //关闭菜单
                } else {
                    int n = holder.getLayoutPosition();
                    if (null != slidingViewClickListener) {
                        slidingViewClickListener.onItemClick(v, n);
                    }
                }
            }
        });
        // 长点击
        holder.mViewGroup.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (menuIsOpen()) { //判断是否有删除菜单打开
                    closeMenu(); //关闭菜单
                } else {
                    int n = holder.getLayoutPosition();
                    if (null != slidingViewClickListener) {
                        slidingViewClickListener.onLongItemClick(v, n);
                    }
                }
                return true;
            }
        });
        // 删除
        holder.mDeleteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenuquick();
                notifyItemRemoved(position);
                notifyItemRangeRemoved(position, mList.size() - position);
                if (null != slidingViewClickListener) {
                    int n = holder.getLayoutPosition();
                    slidingViewClickListener.onDeleteBtnCilck(v, n);
                }
            }
        });
        // 免打扰
        holder.mNoDisturb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击免打扰", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mNoDisturb;
        TextView mTextView, mDeleteText;
        ViewGroup mViewGroup;

        public ViewHolder(View itemView) {
            super(itemView);
            mNoDisturb = itemView.findViewById(R.id.item_sliding_no_disturb);
            mTextView = itemView.findViewById(R.id.item_sliding_text);
            mViewGroup = itemView.findViewById(R.id.item_sliding_lay);
            mDeleteText = itemView.findViewById(R.id.item_sliding_delete);
        }
    }

    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (SlidingButtonView) view;
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        mMenu.closeMenu();
        mMenu = null;
    }

    public void closeMenuquick() {
        mMenu.closeMenuQuick();
        mMenu = null;
    }

    /**
     * 判断是否有菜单打开
     */
    public Boolean menuIsOpen() {
        if (mMenu != null) {
            return true;
        }
        return false;
    }

    @Override
    public void onDownOrMove(SlidingButtonView slidingButtonView) {
        if (menuIsOpen()) {
            if (mMenu != slidingButtonView) {
                closeMenu();
            }
        }
    }
}
