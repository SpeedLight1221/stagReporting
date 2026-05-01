package pro1.reports.report5;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ExamList;
import pro1.reports.report5.reportDataModel.DepartmentExamStats;

import java.util.HashSet;
import java.util.Set;

public class DepartmentExamsStatsReporting {

    public static DepartmentExamStats GetReport(DataSource dataSource, String katedra)
    {
        var ExamListJson = dataSource.getTerminyZkousek2(katedra);
        var examList = new Gson().fromJson(ExamListJson, ExamList.class);

        int realizedCount = 0;
        Set<String> examRooms = new HashSet<>();

        for(var exam : examList.exams){
            if(Integer.parseInt(exam.studentCount) > 0){
                realizedCount++;
            }

            if(exam.room != null){
                examRooms.add(exam.room);
            }

        }

        return new DepartmentExamStats(realizedCount, examRooms.stream().sorted().toList());

    }
}
