package ${packPrefix}.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import ${packPrefix}.${dev}.ControllerBase;
import ${packPrefix}.${dev}.vo.${class}Vo;
import ${packPrefix}.${dev}.service.pojo.${class}Dto;
import ${packPrefix}.${dev}.service.service.${class}Service;
import ${packPrefix}.commons.domain.dto.PageListDto;
import ${packPrefix}.commons.domain.vo.PageListVo;
import ${packPrefix}.commons.utils.bean.BeanMapper;
import ${packPrefix}.commons.utils.string.StringUtil;
import com.loy.e.common.util.Assert;
import com.loy.e.common.vo.SuccessResponse;
import com.loy.e.core.annotation.ControllerLogExeTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ${class}Controller 控制器
 * Created: ${Created} on ${nowDate?string("yyyy/MM/dd")}
 * @versionv 1.0
 */

@Api(value = "${details}页面", description = "${details}页面")
@Slf4j
@RestController
@RequestMapping("**/${class?uncap_first}")
public class ${class}Controller extends ControllerBase {

    
	@Resource
	private ${class}Service ${class?uncap_first}Service;
   
   
    @RequestMapping(value = "/page", method = {RequestMethod.GET})
    @ApiOperation(value = "${details}页面查询", httpMethod = "GET")
    @ApiImplicitParams({
     <#list properties as prop>
  		 <#if prop_has_next>
          		 @ApiImplicitParam(name = "${prop.fieldName?cap_first}", value = "${prop.fieldName?cap_first}", paramType = "query", dataType = "${prop.fieldType}"),
          		 <#else>
          		  @ApiImplicitParam(name = "${prop.fieldName?cap_first}", value = "${prop.fieldName?cap_first}", paramType = "query", dataType = "${prop.fieldType}")
          		 </#if>
 	 </#list>     
    })
    public PageListVo page(@ApiIgnore ${class}Vo reqVo, @ApiIgnore Pageable pageable) {
        ${class}Dto reqDto = BeanMapper.map(reqVo, ${class}Dto.class);
        PageListDto<${class}Dto> respDto = ${class?uncap_first}Service.list${class}(reqDto, pageable.getPageNumber(), pageable.getPageSize());
        return convertPageListVo(pageable.getPageNumber(),respDto,${class}Vo.class);
    }
	
	
	@RequestMapping(value = "/add", method = {RequestMethod.POST})
    @ControllerLogExeTime(description = "${details}添加")
    @ApiOperation(value = "${details}添加", notes = "${details}添加", httpMethod = "POST")
      @ApiImplicitParams({
     <#list properties as prop>
     <#if prop_has_next>
  		 @ApiImplicitParam(name = "${prop.fieldName?cap_first}", value = "${prop.fieldName?cap_first}", paramType = "query", dataType = "${prop.fieldType}"),
  		 <#else>
  		  @ApiImplicitParam(name = "${prop.fieldName?cap_first}", value = "${prop.fieldName?cap_first}", paramType = "query", dataType = "${prop.fieldType}")
  		 </#if>
 	 </#list>     
    })
    public SuccessResponse add(@ApiIgnore ${class}Vo vo) throws IOException {
        if (isNull(vo)) {
            Assert.throwException("字段不能为空");
        }
       
        ${class?uncap_first}Service.save${class}(BeanMapper.map(vo, ${class}Dto.class));
        return SuccessResponse.newInstance();
    }
	
	 @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ControllerLogExeTime(description = "${details}修改")
    @ApiOperation(value = "${details}修改", notes = "${details}修改", httpMethod = "POST")
     @ApiImplicitParams({
     <#list properties as prop>
  		 <#if prop_has_next>
          		 @ApiImplicitParam(name = "${prop.fieldName?cap_first}", value = "${prop.fieldName?cap_first}", paramType = "query", dataType = "${prop.fieldType}"),
          		 <#else>
          		  @ApiImplicitParam(name = "${prop.fieldName?cap_first}", value = "${prop.fieldName?cap_first}", paramType = "query", dataType = "${prop.fieldType}")
          		 </#if>
         	 </#list>

    })
    public SuccessResponse update(@ApiIgnore ${class}Vo vo) throws IOException {
        if (isNull(vo)) {
            Assert.throwException("字段不能为空");
        }
        ${class}Dto dto = ${class?uncap_first}Service.get${class}(vo.getId());
        if (dto != null) {
            ${class}Dto dvo = BeanMapper.map(vo, ${class}Dto.class);
            ${class?uncap_first}Service.update(dvo);
        } else {
            Assert.throwException("${details}记录不存在");
        }
        return SuccessResponse.newInstance();
    }
	
	@RequestMapping(value = "/del", method = {RequestMethod.POST})
    @ControllerLogExeTime(description = "${details}删除")
    @ApiOperation(value = "${details}删除", notes = "${details}删除", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", paramType = "form", required = true, dataType = "String")
    })
    public SuccessResponse del(String id) throws IOException {
        List<${class}Dto> dtoList = ${class?uncap_first}Service.get${class}ByIds(id);
        if (dtoList != null && !dtoList.isEmpty()) {
            ${class?uncap_first}Service.delete${class}ById(id);
        } else {
            Assert.throwException("${details}不存在");
        }
        return SuccessResponse.newInstance();
    }
	
	
	@RequestMapping(value = "/get/{id}", method = {RequestMethod.GET})
    @ControllerLogExeTime(description = "获取${details}", log = false)
    @ApiOperation(value = "获取${details}", notes = "获取${details}", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "${details}ID", paramType = "path", required = true, dataType = "Long")
    })
    public ${class}Vo get(@PathVariable("id") Long id) {
        if (id == null) {
            Assert.throwException("id不能为空");
            return null;
        }
        ${class}Dto dto = ${class?uncap_first}Service.get${class}(id);
        if (dto == null) {
            Assert.throwException("${details}不存在");
        }
        
      return BeanMapper.map(dto, ${class}Vo.class);
        
    }		
}
