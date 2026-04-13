package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Action
{
    @SerializedName("obsazeni")
    public long studentCount;

    @SerializedName("ucitIdno")
    public long teacherId;


}
