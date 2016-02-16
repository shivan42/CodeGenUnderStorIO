<#assign tableClassName = tableName+"Table">
<#macro ColumnConstName column>COLUMN_${column?upper_case}</#macro>
<#function FieldsNameToConstName line fields>
  <#local str = line>
  <#list fields as field>
    <#local str = str?replace('\\b'+field.name, '\" + COLUMN_${field.name?upper_case} + \"', 'ri')>
  </#list>
  <#return str>
</#function>
package ${package}.tables<#if subject != "">.${subject}</#if>;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.queries.Query;

<#--
<#list importList as import>
import ${import};
</#list>
${column.typeAndOptions}
-->
/**
 * ${tableClassName}
 *
 * Date: ${today?number_to_datetime}
 * Powered by
 */

public class ${tableClassName} {

    public static final String TABLE = "${tableName}";

<#list columnList as column>
    public static final String <@ColumnConstName column = column.name/> = <#if column.name = "id">"_id"<#else>"${column.name}"</#if>;
</#list>

    @NonNull
    public static final Query QUERY_ALL = Query.builder()
            .table(TABLE)
            .build();

    // Этот класс лишь набор мета данных о таблице в БД, его экземпляр нам не нужен
    private  ${tableClassName}() {
        throw new IllegalStateException("No instances please");
    }

    // Вместо static final поля -> позволим VM выгружать неиспользуемую строку
    // Потому что она нам понадобится всего раз, после установки приложения
    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE + "("
<#list columnList as column>
                + <@ColumnConstName column = column.name/> + " ${column.typeAndOptions}<#sep>, "
</#sep></#list><#list optionsColumnList>, "
<#items as column>
                + "${FieldsNameToConstName(column.typeAndOptions, columnList)}<#sep>, </#sep>"
</#items>
<#else>"
</#list>
                + ");";
    }
}