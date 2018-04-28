package com.nshane.generalframe.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nshane.generalframe.R;
import com.nshane.generalframe.interfaces.IOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bryan on 2018-4-27.
 */

public class SearchHotAdapter extends RecyclerView.Adapter<SearchHotAdapter.HotVH> {

    private Context context;
    private List<String> hotList = new ArrayList<>();


    public SearchHotAdapter(Context context, List<String> hotList) {
        this.context = context;
        this.hotList = hotList;
    }

    @Override
    public HotVH onCreateViewHolder(ViewGroup parent, int viewType) {
        HotVH vh = new HotVH(
                LayoutInflater.from(context).inflate(R.layout.item_search_hot, parent, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(HotVH holder, int position) {

        if (position == 0) {
            holder.hotRank.setBackground(context.getDrawable(R.drawable.ic_rank_1));
            holder.hotRank.setText(String.valueOf(position + 1));
        } else if (position == 1) {
            holder.hotRank.setBackground(context.getDrawable(R.drawable.ic_rank_2));
            holder.hotRank.setText(String.valueOf(position + 1));
        } else if (position == 2) {
            holder.hotRank.setBackground(context.getDrawable(R.drawable.ic_rank_3));
            holder.hotRank.setText(String.valueOf(position + 1));
        } else {
            holder.hotRank.setBackground(context.getDrawable(R.drawable.ic_rank_4));
            holder.hotRank.setText(String.valueOf(position + 1));
        }

        holder.hotContent.setText(hotList.get(position));

        holder.hotLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                iOnItemClickListener.onItemClick(mSearchHot.get(position));
//
//                if (SearchHistoryUtil.isHistory(mSearchHot.get(position))) {
//                    SearchResultActivity.intentTo(context, mSearchHot.get(position));
//                    LogUtil.d("cg", "热词直跳");
//                } else {
//                    SearchHistoryUtil.insertHistory(mSearchHot.get(position));
//                    LogUtil.d("cg", "热词存跳");
//                    EventBus.getDefault().post(new EventUtil(true));
//                    SearchResultActivity.intentTo(context, mSearchHot.get(position));
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotList.size();
    }

    class HotVH extends RecyclerView.ViewHolder {

        TextView hotRank;
        TextView hotContent;
        LinearLayout hotLL;

        public HotVH(View itemView) {
            super(itemView);
            hotRank = (TextView) itemView.findViewById(R.id.tv_hot_rank);
            hotContent = (TextView) itemView.findViewById(R.id.tv_hot_title);
            hotLL = (LinearLayout) itemView.findViewById(R.id.ll_user_info);

        }
    }

    // interface callback

    private IOnItemClickListener iOnItemClickListener;

    public void setOnItemClickListener(IOnItemClickListener onItemClickListener) {
        this.iOnItemClickListener = iOnItemClickListener;
    }

}
