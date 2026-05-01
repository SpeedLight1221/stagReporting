package pro1.reports.report4;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.Thesis;
import pro1.apiDataModel.ThesisList;
import pro1.reports.report4.reportDataModel.ThesisDuration;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ThesisDurationReporting {
    public static List<ThesisDuration> GetReport(DataSource dataSource, String katedra, String[] years)
    {
        List<ThesisDuration> list = new ArrayList<>();

        for(int i=0;i<years.length;i++) {

            var ThesisListJson = dataSource.getKvalifikacniPrace(years[i],katedra);
            var ThesisList = new Gson().fromJson(ThesisListJson, ThesisList.class);

            long addedDurations = 0;

            int skipped = 0;

            for (Thesis t : ThesisList.ListThesis) {
                if (!t.beginDate.isDateValid() || !t.endDate.isDateValid()) {
                    skipped++;
                    continue;
                }


                long duration = ChronoUnit.DAYS.between(t.beginDate.toLocalDate(), t.endDate.toLocalDate());

                if (duration < 0) {

                    skipped++;
                    continue;
                }

                addedDurations += duration;
            }

            double avg = (double)addedDurations / (ThesisList.ListThesis.size() - skipped);

            ThesisDuration result = new ThesisDuration(years[i], Math.round(avg));
            list.add(result);

        }

        return list;
    }
}
