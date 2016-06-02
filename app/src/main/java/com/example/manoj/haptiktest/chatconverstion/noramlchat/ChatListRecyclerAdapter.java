package com.example.manoj.haptiktest.chatconverstion.noramlchat;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manoj.haptiktest.R;
import com.example.manoj.haptiktest.models.ChatMessageModel;
import com.example.manoj.haptiktest.models.MessageType;

import java.util.List;

/**
 * Created by manoj on 02/06/16.
 */
public class ChatListRecyclerAdapter extends RecyclerView.Adapter<ChatListRecyclerAdapter.BaseViewHolder> {

    private Context context;
    private List<ChatMessageModel> dataList;

    public ChatListRecyclerAdapter(Context context, List<ChatMessageModel> dataList) {
        this.context = context;
        this.dataList = dataList;
        for (ChatMessageModel chatMessageModel : dataList) {
            Log.d("manoj", "data : " + chatMessageModel.getMessageType());
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MessageType messageType = MessageType.values()[viewType];
        View view = null;
        BaseViewHolder baseViewHolder = null;
        if (messageType == MessageType.FRIEND) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_friend_message, parent, false);
            baseViewHolder = new FriendViewHolder(view);
        } else if (messageType == MessageType.SELF) {
            Log.d("manoj", "self message row created");
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_self_message, parent, false);
            baseViewHolder = new BaseViewHolder(view);
        }
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        ChatMessageModel chatMessageModel = getItem(position);
        holder.messageText.setText(chatMessageModel.getBody());
        if (chatMessageModel.getMessageType() == MessageType.FRIEND) {
            FriendViewHolder friendViewHolder = (FriendViewHolder) holder;
            friendViewHolder.userNameView.setText(chatMessageModel.getName());

            int resourceId = R.drawable.ic_star_border_black_24dp;
            if (chatMessageModel.isFavourite()) {
                resourceId = R.drawable.ic_star_black_24dp;
            }
            friendViewHolder.favIcon.setImageDrawable(ContextCompat.getDrawable(context, resourceId));
        }
    }

    private ChatMessageModel getItem(int positon) {
        return dataList.get(positon);
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getMessageType().ordinal();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {
        private TextView messageText;

        public BaseViewHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.message);
        }
    }

    public class FriendViewHolder extends BaseViewHolder implements View.OnClickListener {

        private TextView userNameView;
        private ImageView favIcon;

        public FriendViewHolder(View itemView) {
            super(itemView);
            userNameView = (TextView) itemView.findViewById(R.id.user_name);
            favIcon = (ImageView) itemView.findViewById(R.id.fav_icon);
            favIcon.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ChatMessageModel chatMessageModel = getItem(position);
            chatMessageModel.setIsFavourite(!chatMessageModel.isFavourite());
            notifyDataSetChanged();
        }
    }


}
