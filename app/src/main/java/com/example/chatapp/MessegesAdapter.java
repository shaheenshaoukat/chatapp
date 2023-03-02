package com.example.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.databinding.ItemReceiveBinding;
import com.example.chatapp.databinding.ItemSentBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessegesAdapter extends RecyclerView.Adapter{

    Context context;
    ArrayList<Message> messages;
    final int ITEM_SENT=1;
    final int ITEM_RECEIVE=2;

    public MessegesAdapter(Context context, ArrayList<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType==ITEM_SENT){
            View view= LayoutInflater.from(context).inflate(R.layout.item_sent,parent,false);
            return new SentViewHolder(view);
        }
        else {
            View view= LayoutInflater.from(context).inflate(R.layout.item_receive,parent,false);
            return new ReceiveviewHolder(view);
        }

    }

    @Override
    public int getItemViewType(int position) {
        Message message=messages.get(position);
        if (FirebaseAuth.getInstance().getUid().equals(message.getSenderId())){
            return ITEM_SENT;
        }
        else {
          return   ITEM_RECEIVE;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message=messages.get(position);
        if (holder.getClass()==SentViewHolder.class){
            SentViewHolder viewHolder=(SentViewHolder) holder;
            viewHolder.binding.message.setText(message.getMessage());
        }
        else {

            ReceiveviewHolder viewHolder=(ReceiveviewHolder) holder;
            viewHolder.binding.message.setText(message.getMessage());
        }

    }


    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class SentViewHolder extends RecyclerView.ViewHolder{

        ItemSentBinding binding;
        public SentViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemSentBinding.bind(itemView);
        }

    }
    public class ReceiveviewHolder extends RecyclerView.ViewHolder{

        ItemReceiveBinding binding;
        public ReceiveviewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemReceiveBinding.bind(itemView);
        }
    }
}
