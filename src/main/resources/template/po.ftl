package ${packPrefix}.po; 

import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
/**
 * ${class} 实体类
 * Created: ${Created} on ${nowDate?string("yyyy/MM/dd")}
 * @versionv 1.0
 */
 
 @Data
 @Table(name = "${tableName}")
public class ${class}Po implements Serializable { 

  <#list properties as prop>
  <#if (prop.fieldName == "id")>
   @Id
  </#if>
    private ${prop.fieldType} ${prop.fieldName};
  </#list>

}
