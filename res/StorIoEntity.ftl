<#assign tableClassName = tableName+"Table">
<#assign entityClassName= tableName+"Entity">
<#macro ColumnConstName column>COLUMN_${column?upper_case}</#macro>
<#macro JavaName column>${column?replace("_", " ")?capitalize?replace(" ", "")?uncap_first}</#macro>
package ${package}.entities<#if subject != "">.${subject}</#if>;

import ${package}.tables<#if subject != "">.${subject}</#if>.${tableClassName};
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

<#--
<#list importList as import>
import ${import};
</#list>
-->
/**
 * ${entityClassName}
 *
 * Date: ${today?number_to_datetime}
 * Powered by
 */

@StorIOSQLiteType(table = ${tableClassName}.TABLE)
public class ${entityClassName} {

<#list columnList as column>
    @StorIOSQLiteColumn(name = ${tableClassName}.<@ColumnConstName column = column.name/><#if column.typeAndOptions?contains("PRIMARY KEY")>, key = true</#if>)
    ${column.type} <@JavaName column = column.name/>;

</#list>

    // оставим дефолтный конструктор для процессора аннотаций
    ${entityClassName}() {
    }

    public ${entityClassName}(<#list columnList as column>${column.type} <@JavaName column = column.name/><#sep>, </#list>) {
<#list columnList as column>
        this.<@JavaName column = column.name/> = <@JavaName column = column.name/>;
</#list>
    }
<#list columnList as column>

    public ${column.type} get${column.name?replace("_", " ")?capitalize?replace(" ", "")}() {
        return <@JavaName column = column.name/>;
    }
</#list>
}