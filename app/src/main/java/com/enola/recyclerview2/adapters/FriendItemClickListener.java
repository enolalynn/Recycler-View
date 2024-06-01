package com.enola.recyclerview2.adapters;

import com.enola.recyclerview2.model.FriendRequest;

public interface FriendItemClickListener {
    void onConfirm(FriendRequest friendRequest);
    void onDelete(String friendName);
}
