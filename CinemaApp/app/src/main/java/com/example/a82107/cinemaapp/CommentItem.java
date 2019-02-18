package com.example.a82107.cinemaapp;

import android.os.Parcel;
import android.os.Parcelable;

public class CommentItem implements Parcelable {
    int photo;
    float ratingScore;
    String name;
    String text;

    public CommentItem(){

    }

    public CommentItem(int photo, float ratingScore, String name, String text) {
        this.photo = photo;
        this.ratingScore = ratingScore;
        this.name = name;
        this.text = text;
    }

    //Parcel을 이용하여 보내게 되면 받는 쪽에서도 Parcel을 통해서 데이터를 받기 때문에,
    // 생성자를 통해 Parcel을 전달하게 됩니다. 그래서 Parcel형이 들어오는 생성자를 만들게 되었고,
    // Default 생성자도 함께 추가
    public CommentItem(Parcel src){
        this.photo = src.readInt();
        this.ratingScore = src.readFloat();
        this.name = src.readString();
        this.text = src.readString();
    }

    //이 Creator가 없다면 데이터를 넘기게 되더라고 Creator가 없다는 Exception을
    // 뿌려주기 때문에 필히 작성을 해줘야 하는 부분
    public static final Creator<CommentItem> CREATOR = new Creator<CommentItem>() {
        @Override
        public CommentItem createFromParcel(Parcel in) {
            return new CommentItem(in);
        }

        @Override
        public CommentItem[] newArray(int size) {
            return new CommentItem[size];
        }
    };

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(float ratingScore) {
        this.ratingScore = ratingScore;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(photo);
        dest.writeFloat(ratingScore);
        dest.writeString(name);
        dest.writeString(text);
    }
}
