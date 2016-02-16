package com.shivandev;

/**
 * Modified by ShIvan on 14.02.2016.
 * Created by duxing on 2015.07.26 23:38.
 */
public class Column {

    private String name;
    private String type;
    private String typeStr;
    private String comment;
    private boolean isPrimaryKey;
    private String typeAndOptions;

    public Column() {
    }

    public Column(String typeAndOptions) {
        this("", "", typeAndOptions);
    }

    public Column(String name, String typeStr, String typeAndOptions) {
        this.name = name;
        this.typeAndOptions = typeAndOptions;
        this.typeStr = TypeChanger.clean(typeStr);
        this.type = TypeChanger.getType(this.typeStr);
    }

    public String getType() {
        return type;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setIsPrimaryKey(boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public String getTypeAndOptions() {
        return typeAndOptions;
    }

    public String toString(){
        String r = "{";
        if(name!=null){
            r+="\"name\"=\""+name+"\"";
        }
        if(typeAndOptions !=null){
            if(!r.equals("{")){r+=",";}
            r+="\"typeAndOptions\"=\""+ typeAndOptions +"\"";
        }
        if(type!=null){
            if(!r.equals("{")){r+=",";}
            r+="\"type\"=\""+type+"\"";
        }
        if(typeStr!=null){
            if(!r.equals("{")){r+=",";}
            r+="\"typeStr\"=\""+typeStr+"\"";
        }
        if(comment!=null){
            if(!r.equals("{")){r+=",";}
            r+="\"comment\"=\""+comment+"\"";
        }
        if(isPrimaryKey){
            if(!r.equals("{")){r+=",";}
            r+="\"isPrimaryKey\"=\""+isPrimaryKey+"\"";
        }
        r+="}";
        return r;
    }
}
