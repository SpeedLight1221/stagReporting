package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TDate {
    @SerializedName("value")
    public String dateString;



    public boolean isDateValid(){
        if(dateString==null || dateString.isEmpty())
            return false;

        String[] split = dateString.split("\\.");

        if(split.length != 3) return false;

        if(Integer.parseInt(split[0]) <1 || Integer.parseInt(split[0]) > 31 ) return false;

        if(Integer.parseInt(split[1]) <1 || Integer.parseInt(split[1]) > 12 ) return false;

        if(split[2].length() != 4 ) return false;



        return true;
    }

    public LocalDate toLocalDate(){
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d.M.yyyy"));
    }
}
