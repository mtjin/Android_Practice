package com.mtjinse.myboxoffice;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BoxOfficeResult {

    @SerializedName("boxofficeType")
    @Expose
    private String boxofficeType;
    @SerializedName("showRange")
    @Expose
    private String showRange;
    @SerializedName("dailyBoxOfficeList")
    @Expose
    private List<DailyBoxOfficeList> dailyBoxOfficeList = null;

    public String getBoxofficeType() {
        return boxofficeType;
    }

    public void setBoxofficeType(String boxofficeType) {
        this.boxofficeType = boxofficeType;
    }

    public String getShowRange() {
        return showRange;
    }

    public void setShowRange(String showRange) {
        this.showRange = showRange;
    }

    public List<DailyBoxOfficeList> getDailyBoxOfficeList() {
        return dailyBoxOfficeList;
    }

    public void setDailyBoxOfficeList(List<DailyBoxOfficeList> dailyBoxOfficeList) {
        this.dailyBoxOfficeList = dailyBoxOfficeList;
    }

}
