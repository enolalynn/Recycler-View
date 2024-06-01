package com.enola.recyclerview2.model;

import androidx.annotation.DrawableRes;

public record FriendRequest(
        String name,
        @DrawableRes int imageResource
) {
}
