package com.example.db.entities;

import com.example.db.tables.FavoriteUsersTable;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * FavoriteUsersEntity
 *
 * Date: 03.04.2016 1:30:02
 * Powered by
 */

@StorIOSQLiteType(table = FavoriteUsersTable.TABLE)
public class FavoriteUsersEntity {

    @StorIOSQLiteColumn(name = FavoriteUsersTable.COLUMN_ID, key = true)
    Long id;

    @StorIOSQLiteColumn(name = FavoriteUsersTable.COLUMN_GROUP_COLOR)
    Integer groupColor;

    @StorIOSQLiteColumn(name = FavoriteUsersTable.COLUMN_RATING)
    Float rating;


    // оставим дефолтный конструктор для процессора аннотаций
    FavoriteUsersEntity() {
    }

    public FavoriteUsersEntity(Long id, Integer groupColor, Float rating) {
        this.id = id;
        this.groupColor = groupColor;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public Integer getGroupColor() {
        return groupColor;
    }

    public Float getRating() {
        return rating;
    }
}