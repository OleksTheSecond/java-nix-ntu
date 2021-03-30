package com.my.constants;

public interface ConfDaoConstants {
    String CONF_ID = "conf_id";
    String START_TIME = "start_time";
    String END_TIME = "end_time";
    String PLACE = "place";
    String RPEORTS_COUNT = "reports_count";

    String INSERT_CONF = "INSERT INTO Conferences VALUES(?,?,?,?,?);";
    String DELETE_CONF = "DELETE FROM Conferences WHERE conf_id = ?";
    String UPDATE_CONF =
            "UPDATE Conferences SET start_time = ?, end_time = ?, place = ? WHERE conf_id = ?";
    String FIND_ALL = "SELECT * FROM Conferences";
    String FIND_CONF = "SELECT * FROM Conferences WHERE conf_id = ?";
}
