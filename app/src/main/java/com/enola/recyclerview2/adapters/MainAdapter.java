package com.enola.recyclerview2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enola.recyclerview2.databinding.ItemRequestBinding;
import com.enola.recyclerview2.model.FriendRequest;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    public void setFriendRequests(List<FriendRequest> friendRequests) {
        this.friendRequests = friendRequests;
        notifyDataSetChanged(); //update
    }

    List<FriendRequest> friendRequests;

    public void setItemClickListener(FriendItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    FriendItemClickListener itemClickListener;
    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        var binding = ItemRequestBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MainViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        FriendRequest friendRequest = friendRequests.get(position);
        holder.bind(friendRequest);
        holder.binding.btConfirm.setOnClickListener(v -> {
            itemClickListener.onConfirm(friendRequest);
        });
        holder.binding.btDelete.setOnClickListener(v -> {
            itemClickListener.onDelete(friendRequest.name());
        });
    }

    @Override
    public int getItemCount() {
        return friendRequests.size();
    }
    public class  MainViewHolder extends RecyclerView.ViewHolder{
        ItemRequestBinding binding;

        public MainViewHolder(ItemRequestBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(FriendRequest friendRequest){
            binding.ivFriend.setImageResource(friendRequest.imageResource());
            binding.tvFriend.setText(friendRequest.name());
        }
    }
}
