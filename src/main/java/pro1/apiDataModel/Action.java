package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Action
{
    @SerializedName("obsazeni")
    public long studentCount;

    @SerializedName("ucitIdno")
    public long teacherId;

    @SerializedName("rok")
    public String year;

    @SerializedName("den")
    public String day;

    @SerializedName("denZkr")
    public String shortDay;

    public int roakIdno;


}
