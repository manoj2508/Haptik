package com.example.manoj.haptiktest.chatconverstion.fav;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manoj.haptiktest.R;
import com.example.manoj.haptiktest.models.FavMessageModel;

import java.util.List;

/**
 * Created by manoj on 02/06/16.
 */
public class FavChatAdapter extends RecyclerView.Adapter<FavChatAdapter.ViewHolder> {


    private List<FavMessageModel> dataList;
    private Context context;

    public FavChatAdapter(Context context, List<FavMessageModel> chatMessageList) {
        this.context = context;
        this.dataList = chatMessageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_fav_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FavMessageModel chatMessageModel = dataList.get(position);
        holder.userNameView.setText(chatMessageModel.getName());
        holder.totalCountView.setText("Total Msg Count : " + chatMessageModel.getTotalCount());
        holder.favCountView.setText("Fav Msg Count : " + chatMessageModel.getFavCount());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void updateMessageList(List<FavMessageModel> chatMessageList) {
        dataList = chatMessageList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userNameView;
        private TextView totalCountView;
        private TextView favCountView;

        public ViewHolder(View itemView) {
            super(itemView);
            totalCountView = (TextView) itemView.findViewById(R.id.total_count);
            favCountView = (TextView) itemView.findViewById(R.id.fav_count);
            userNameView = (TextView) itemView.findViewById(R.id.user_name);
        }
    }
}
