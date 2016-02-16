package com.example.db.tables;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.queries.Query;

/**
 * TweetsTable
 *
 * Date: 03.04.2016 1:30:02
 * Powered by
 */

public class TweetsTable {

    public static final String TABLE = "Tweets";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_CONTENT = "content";

    @NonNull
    public static final Query QUERY_ALL = Query.builder()
            .table(TABLE)
            .build();

    // Этот класс лишь набор мета данных о таблице в БД, его экземпляр нам не нужен
    private  TweetsTable() {
        throw new IllegalStateException("No instances please");
    }

    // Вместо static final поля -> позволим VM выгружать неиспользуемую строку
    // Потому что она нам понадобится всего раз, после установки приложения
    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE + "("
                + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
                + COLUMN_AUTHOR + " text NOT NULL, "
                + COLUMN_CONTENT + " text NOT NULL"
                + ");";
    }
}