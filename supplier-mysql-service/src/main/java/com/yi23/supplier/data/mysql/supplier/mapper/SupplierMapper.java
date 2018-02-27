package com.yi23.supplier.data.mysql.supplier.mapper;

import com.yi23.supplier.data.mysql.supplier.po.Supplier;
import com.yi23.supplier.data.mysql.supplier.po.SupplierCriteria;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface SupplierMapper {
    int countByExample(SupplierCriteria example);

    int deleteByExample(SupplierCriteria example);

    int deleteByPrimaryKey(Long supplierId);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    List<Supplier> selectByExampleWithRowbounds(SupplierCriteria example, RowBounds rowBounds);

    List<Supplier> selectByExample(SupplierCriteria example);

    Supplier selectByPrimaryKey(Long supplierId);

    int updateByExampleSelective(@Param("record") Supplier record, @Param("example") SupplierCriteria example);

    int updateByExample(@Param("record") Supplier record, @Param("example") SupplierCriteria example);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);
}