package com.limprove.tinygithub.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Owner {
    @SerializedName("login")
    public String login;

    @SerializedName("avatar_url")
    public String avatarUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return login.equals(owner.login) &&
                avatarUrl.equals(owner.avatarUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, avatarUrl);
    }
}
