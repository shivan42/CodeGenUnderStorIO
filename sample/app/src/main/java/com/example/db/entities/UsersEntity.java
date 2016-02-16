package com.example.db.entities;

import com.example.db.tables.UsersTable;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * UsersEntity
 *
 * Date: 03.04.2016 1:30:02
 * Powered by
 */

@StorIOSQLiteType(table = UsersTable.TABLE)
public class UsersEntity {

    @StorIOSQLiteColumn(name = UsersTable.COLUMN_ID, key = true)
    Long id;

    @StorIOSQLiteColumn(name = UsersTable.COLUMN_NICK)
    String nick;


    // оставим дефолтный конструктор для процессора аннотаций
    UsersEntity() {
    }

    public UsersEntity(Long id, String nick) {
        this.id = id;
        this.nick = nick;
    }

    public Long getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }
}