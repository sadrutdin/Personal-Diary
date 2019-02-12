package com.zaynukov.dev.angeldiary.model.sql;

import org.intellij.lang.annotations.Language;

public final class TableNotes {


    private TableNotes() {
    }

    @Language("H2")
    public final static String INIT_TABLE =
            "create table NOTES(" +
                    "ID INTEGER auto_increment primary key, " +
                    "TITLE VARCHAR(45) not null, " +
                    "NOTE_TEXT VARCHAR(32768) not null, " +
                    "CREATE_DATE TIMESTAMP not null, " +
                    "ACTIVE boolean default true not null" +
                    ")";

    public enum Column {
        ID, TITLE, NOTE_TEXT, CREATE_DATE, ACTIVE
    }

//    @Language("H2")
//    public static final String LAST_ID = "select n.id from NOTES as n order by id desc limit 1";

    @Language("H2")
    public static final String SELECT_ONLY = "select * from NOTES where ID=? and ACTIVE is true limit 1";

    @Language("H2")
    public static final String SELECT_ALL_DESC = "select n.ID,n.TITLE,n.CREATE_DATE,(select DATE from CHANGES as c where c.NOTE_ID=n.ID order by c.id desc limit 1) from NOTES as n where n.ACTIVE is true order by n.CREATE_DATE desc";

    @Language("H2")
    public static final String SELECT_ALL_DESC_WITH_SEARCH = "select n.ID,n.TITLE,n.CREATE_DATE,(select DATE from CHANGES as c where c.NOTE_ID=n.ID order by c.id desc limit 1) from NOTES as n where n.ACTIVE is true and (lower(TITLE) like ? or lower(NOTE_TEXT) like ?) order by n.CREATE_DATE desc";
    @Language("H2")
    public static final String SELECT_ALL_DESC_WITH_DATE_RANGE = "select n.ID,n.TITLE,n.CREATE_DATE,(select DATE from CHANGES as c where c.NOTE_ID=n.ID order by c.id desc limit 1) from NOTES as n where n.ACTIVE is true and (CREATE_DATE between ? and ?) order by n.CREATE_DATE desc";
    @Language("H2")
    public static final String SELECT_ALL_DESC_WITH_SEARCH_DATE_RANGE = "select n.ID,n.TITLE,n.CREATE_DATE,(select DATE from CHANGES as c where c.NOTE_ID=n.ID order by c.id desc limit 1) from NOTES as n where n.ACTIVE is true and (CREATE_DATE between ? and ?) and (lower(TITLE) like ? or lower(NOTE_TEXT) like ?) order by n.CREATE_DATE desc";

    @Language("H2")
    public static final String INSERT = "insert into NOTES (TITLE,NOTE_TEXT,CREATE_DATE,ACTIVE) values (?,?,?,TRUE)";

    @Language("H2")
    public static final String UPDATE = "update NOTES set TITLE = ?, NOTE_TEXT = ? where ID=?";

    @Language("H2")
    public static final String DELETE = "update NOTES set ACTIVE=false where ID=?";

}
