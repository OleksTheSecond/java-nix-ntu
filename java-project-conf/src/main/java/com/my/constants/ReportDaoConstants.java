package com.my.constants;

public interface ReportDaoConstants {
    String REPORT_ID = "report_id";
    String REPORT_TITLE = "report_title";
    String USER_ID = "user_id";
    String CONF_ID = "conf_id";

    String INSERT_REPORT = "INSERT INTO Reports VALUES(?,?,?,?);";
    String DELETE_REPORT = "DELETE FROM Reports WHERE report_id = ?";
    String UPDATE_REPORT =
            "UPDATE Reports SET report_title = ?, user_id = ?, conf_id = ? WHERE report_id = ?;";
    String FIND_ALL = "SELECT * FROM REPORTS;";
    String FIND_REPORT = "SELECT * FROM REPORTS WHERE report_id = ?";

}
