package com.example.db.entities;

import com.example.db.tables.AttachmentsTable;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * AttachmentsEntity
 *
 * Date: 03.04.2016 1:30:02
 * Powered by
 */

@StorIOSQLiteType(table = AttachmentsTable.TABLE)
public class AttachmentsEntity {

    @StorIOSQLiteColumn(name = AttachmentsTable.COLUMN_ID, key = true)
    Long id;

    @StorIOSQLiteColumn(name = AttachmentsTable.COLUMN_TWEETS_ID)
    Long tweetsId;

    @StorIOSQLiteColumn(name = AttachmentsTable.COLUMN_URL)
    String url;

    @StorIOSQLiteColumn(name = AttachmentsTable.COLUMN_DESCRIPTION)
    String description;


    // оставим дефолтный конструктор для процессора аннотаций
    AttachmentsEntity() {
    }

    public AttachmentsEntity(Long id, Long tweetsId, String url, String description) {
        this.id = id;
        this.tweetsId = tweetsId;
        this.url = url;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Long getTweetsId() {
        return tweetsId;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }
}