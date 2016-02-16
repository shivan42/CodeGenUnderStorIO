package com.example.db.entities;

import com.example.db.tables.TweetsTable;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * TweetsEntity
 *
 * Date: 03.04.2016 1:30:02
 * Powered by
 */

@StorIOSQLiteType(table = TweetsTable.TABLE)
public class TweetsEntity {

    @StorIOSQLiteColumn(name = TweetsTable.COLUMN_ID, key = true)
    Long id;

    @StorIOSQLiteColumn(name = TweetsTable.COLUMN_AUTHOR)
    String author;

    @StorIOSQLiteColumn(name = TweetsTable.COLUMN_CONTENT)
    String content;


    // оставим дефолтный конструктор для процессора аннотаций
    TweetsEntity() {
    }

    public TweetsEntity(Long id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}