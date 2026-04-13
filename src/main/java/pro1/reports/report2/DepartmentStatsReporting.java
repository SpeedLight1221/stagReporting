package pro1.reports.report2;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ActionsList;
import pro1.reports.report2.reportDataModel.DepartmentStats;

public class DepartmentStatsReporting {
    public static DepartmentStats GetReport(DataSource dataSource, String rok, String katedra) {
        var actionsListJson = dataSource.getRozvrhByKatedra(rok, katedra);
        var actionsList = new Gson().fromJson(actionsListJson, ActionsList.class);
        return new DepartmentStats(
                maxActionStudentsCount(actionsList),
                emptyActionsCount(actionsList),
                maxTeacherScore(actionsList)
        );
        // TODO 2.5: Oprav testovací data
    }

    private static long maxActionStudentsCount(ActionsList actionsList) {

        // TODO 2.0: Doplň potřebné atributy do třídy apiDataModel.Action
        // TODO 2.1: Doplň: maximální počet přihlášených studentů na rozvrhové akci
        var res =   actionsList.items.stream()
                .mapToLong(a-> a.studentCount)
                .max()
                .orElse(-1L);
        return res;
    }

    private static long emptyActionsCount(ActionsList actionsList) {
        var res =   actionsList.items.stream()
                .filter(action -> action.studentCount == 0)
                .count();
        return res;
    }


    private static long maxTeacherScore(ActionsList actionsList) {
        // TODO 2.4: Doplň: nejvyšší výsledek dosažený metodou teacherScore mezi všemi učiteli ve vstupních datech
            var teacherIds =   actionsList.items.stream()
                    .mapToLong(action -> action.teacherId)
                    .distinct();
            var scores = teacherIds
                    .map( id-> teacherScore(id, actionsList));
        return scores.max().orElse(-1L);
    }

    private static long teacherScore(long teacherId, ActionsList actionsList) {
        // TODO 2.3: Doplň pomocnou metodu - součet všech přihlášených studentů na akcích daného učitele
        var res =   actionsList.items.stream()
                .filter(a ->a.teacherId == teacherId )
                .mapToLong(a->a.studentCount)
                .sum();
        return res;
    }
}
