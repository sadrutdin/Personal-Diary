package com.zaynukov.dev.angeldiary.model.sql;

import org.intellij.lang.annotations.Language;

public final class TableChanges {
    private TableChanges() {
    }

    @Language("H2")
    public static final String INIT_TABLE = "create table CHANGES( " +
            " ID INTEGER auto_increment not null, " +
            " NOTE_ID INTEGER not null, " +
            " DATE TIMESTAMP not null, " +
            " constraint CHANGES_PK " +
            " primary key (ID), " +
            " constraint CHANGES_NOTES_ID_FK " +
            " foreign key (NOTE_ID) references NOTES " +
            ")";


    public enum Column {
        ID, NOTE_ID, DATE
    }

    @Language("H2")
    public static final String INSERT = "insert into CHANGES (NOTE_ID, DATE) values (?,?)";

    @Language("H2")
    public static final String GET_LAST = "select DATE from CHANGES where NOTE_ID=? order by id desc limit 1";

    @Language("H2")
    public static final String GET_ALL = "select DATE from CHANGES where NOTE_ID=? order by id asc";
}
