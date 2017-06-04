package ${packPrefix}.service;

import ${packPrefix}.${dev}.service.pojo.${class}Dto;
import ${packPrefix}.commons.domain.dto.PageListDto;
import java.util.List;


/**
 * ${class}Service 接口
 * Created: ${Created} on ${nowDate?string("yyyy/MM/dd")}
 * @versionv 1.0
 */
public interface ${class}Service {
	
	/**
	 * 分页查询 数据集
	 * @param queryMap  条件map
	 * @param pageBounds 分页条件
	 * @return
	 * @throws Exception
	 */
	PageListDto<${class}Dto> list${class}(${class}Dto dto, int pageNum, int pageSize);
	
	/**
	 * 通过id查询数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	${class}Dto get${class}(Long id);
	
	/**
	 * 通过id查询数据,多个id用逗号分隔
	 * @param id
	 * @return
	 * @throws Exception
	 */
	 List<${class}Dto> get${class}ByIds(String ids);
	
	/**
	 * 添加数据（作用所有的字段）
	 * @param record
	 * @return
	 * @throws Exception
	 */
	int save${class}(${class}Dto dto);
	
	/**
	 * 通过主键id 更新数据（非空字段）
	 * @param id 主键id
	 * @param record 
	 * @return
	 * @throws Exception
	 */
	void update(${class}Dto dto);
	
	/**
	 * 通过id删除数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	 boolean delete${class}ById(String id);
	
	
	
	

	
}
