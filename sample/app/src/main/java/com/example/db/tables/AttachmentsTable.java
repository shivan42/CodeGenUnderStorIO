package com.example.db.tables;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.queries.Query;

/**
 * AttachmentsTable
 *
 * Date: 03.04.2016 1:30:02
 * Powered by
 */

public class AttachmentsTable {

    public static final String TABLE = "Attachments";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TWEETS_ID = "tweets_id";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_DESCRIPTION = "description";

    @NonNull
    public static final Query QUERY_ALL = Query.builder()
            .table(TABLE)
            .build();

    // Этот класс лишь набор мета данных о таблице в БД, его экземпляр нам не нужен
    private  AttachmentsTable() {
        throw new IllegalStateException("No instances please");
    }

    // Вместо static final поля -> позволим VM выгружать неиспользуемую строку
    // Потому что она нам понадобится всего раз, после установки приложения
    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE + "("
                + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TWEETS_ID + " integer(10) NOT NULL, "
                + COLUMN_URL + " text NOT NULL, "
                + COLUMN_DESCRIPTION + " text, "
                + "FOREIGN KEY(" + COLUMN_TWEETS_ID + ") REFERENCES Tweets(" + COLUMN_ID + ")"
                + ");";
    }
}