package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Thesis {

    @SerializedName("datumZadani")
    public TDate beginDate;
    @SerializedName("datumOdevzdani")
    public TDate endDate;


}
