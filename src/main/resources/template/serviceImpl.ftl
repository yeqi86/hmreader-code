package ${packPrefix}.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.google.common.base.Splitter;
import ${packPrefix}.${dev}.service.dao.${class}Mapper;
import ${packPrefix}.${dev}.service.pojo.${class}Po;
import ${packPrefix}.${dev}.service.pojo.${class}Dto;
import ${packPrefix}.${dev}.service.service.${class}Service;
import com.iflytek.hmreader.commons.domain.dto.PageListDto;
import com.iflytek.hmreader.commons.utils.bean.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.Date;
import java.util.List;

/**
 * ${class}ServiceImpl 实现类
 * Created: ${Created} on ${nowDate?string("yyyy/MM/dd")}
 * @versionv 1.0
 */
 
@Service
public class ${class}ServiceImpl implements ${class}Service{
	
	@Autowired
	${class}Mapper mapper;

	@Override
    public int save${class}(${class}Dto dto) {
        ${class}Po po = BeanMapper.map(dto, ${class}Po.class);
        Date d = new Date();
        po.setCreateTime(d);
        po.setUpdateTime(d);
        return mapper.insert(po);
    }

    @Override
    public void update(${class}Dto dto) {
        ${class}Po po = BeanMapper.map(dto, ${class}Po.class);
        po.setUpdateTime(new Date());
        mapper.updateByPrimaryKeySelective(po);

    }

    @Override
    public PageListDto<${class}Dto> list${class}(${class}Dto dto, int pageNum, int pageSize) {
        if (pageNum < 1) pageNum = 1;
        // 分页
        Page<Object> page;
        if (pageSize > 0) {
            page = PageHelper.startPage(pageNum, pageSize);
        } else {
            page = new Page<>();
        }
        ${class}Po po = BeanMapper.map(dto,${class}Po.class);
        // 筛选条件
        Example example = new Example(${class}Po.class);
        Example.Criteria criteria = example.createCriteria();
        if (po != null) {
        <#list properties as prop>
  		 if(!StringUtils.isEmpty(po.get${prop.fieldName?cap_first}())){
  		 	criteria.andEqualTo("${prop.fieldName}", po.get${prop.fieldName?cap_first}());
     		}
     		</#list>
     		}
        example.setOrderByClause("create_time desc");
        mapper.selectByExample(example);
        PageListDto<${class}Dto> pageDto = new PageListDto<>();
        pageDto.setList(BeanMapper.mapList(page.getResult(),${class}Dto.class));
        pageDto.setTotalPage(page.getPages());
        pageDto.setTotalSize(page.getTotal());
        return pageDto;
    }


    @Override
    public boolean delete${class}ById(String id) {
        if (StringUtil.isEmpty(id))
            return false;

        List<String> ids = Splitter.on(",").trimResults().splitToList(id);
        Example example = new Example(${class}Po.class);
        example.createCriteria().andIn("id", ids);

        // 删除数据库记录
        return mapper.deleteByExample(example) > 0;
    }

    @Override
    public ${class}Dto get${class}(Long id) {
        return BeanMapper.map(mapper.selectByPrimaryKey(id),${class}Dto.class);
    }

    @Override
    public List<${class}Dto> get${class}ByIds(String id) {
        if (StringUtil.isEmpty(id))
            return null;

        List<String> ids = Splitter.on(",").trimResults().splitToList(id);

        Example example = new Example(${class}Po.class);
        example.createCriteria().andIn("id", ids);
        List<${class}Po> poList = mapper.selectByExample(example);

        return BeanMapper.mapList(poList, ${class}Dto.class);
    }

}
