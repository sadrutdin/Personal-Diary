package com.zaynukov.dev.angeldiary.service;

@SuppressWarnings("SqlNoDataSourceInspection")
public final class DiaryConstants {
    private DiaryConstants() {
    }

    public final static String INIT_NEW_DIARY =
            "create table notes(" +
                    "  id             int auto_increment  primary key not null," +
                    "  title          varchar(45)                           not null," +
                    "  note_text      varchar(32768)                        not null," +
                    "  create_date    timestamp                             not null," +
                    "  change_history varchar(240)" +
                    ")";

    public final static String TEST_AUTH_DB_QUERY = "select 1";


    public final class TableColumnName {
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String TEXT = "note_text";
        public static final String CREATE_DATE = "create_date";
        public static final String CHANGE_HISTORY = "change_history";

        private TableColumnName() {
        }
    }

}
