package com.example.db.tables;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.queries.Query;

/**
 * FavoriteUsersTable
 *
 * Date: 03.04.2016 1:30:02
 * Powered by
 */

public class FavoriteUsersTable {

    public static final String TABLE = "FavoriteUsers";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_GROUP_COLOR = "group_color";
    public static final String COLUMN_RATING = "rating";

    @NonNull
    public static final Query QUERY_ALL = Query.builder()
            .table(TABLE)
            .build();

    // Этот класс лишь набор мета данных о таблице в БД, его экземпляр нам не нужен
    private  FavoriteUsersTable() {
        throw new IllegalStateException("No instances please");
    }

    // Вместо static final поля -> позволим VM выгружать неиспользуемую строку
    // Потому что она нам понадобится всего раз, после установки приложения
    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE + "("
                + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
                + COLUMN_GROUP_COLOR + " smallint(5) DEFAULT 0 NOT NULL, "
                + COLUMN_RATING + " real(10) DEFAULT -1 NOT NULL, "
                + "UNIQUE (" + COLUMN_ID + ") ON CONFLICT replace , "
                + "FOREIGN KEY(" + COLUMN_ID + ") REFERENCES Users(" + COLUMN_ID + ")"
                + ");";
    }
}