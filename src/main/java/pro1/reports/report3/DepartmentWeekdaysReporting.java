package pro1.reports.report3;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.Action;
import pro1.apiDataModel.ActionsList;
import pro1.reports.report3.reportDataModel.DepartmentWeekday;

import java.util.ArrayList;
import java.util.List;

public class DepartmentWeekdaysReporting {
    public static List<DepartmentWeekday> GetReport(DataSource dataSource, String rok, String katedra, String[] days)
    {
        var actionsListJson = dataSource.getRozvrhByKatedra(rok, katedra);
        var actionsList = new Gson().fromJson(actionsListJson, ActionsList.class);
        List<DepartmentWeekday> departmentWeekdays = new ArrayList<>();



        for(String d : days){
            DepartmentWeekday current = new DepartmentWeekday(d,0);
            var count = actionsList.items.stream().filter(x->x.day != null && x.shortDay.equals(d)).count();
            current.actionsCount = (int)count;
            departmentWeekdays.add(current);
        }
        return departmentWeekdays;
    }


}
