package com.yjkj.ks_user.mapper;

import com.yjkj.ks_user.entity.AisleName;
import com.yjkj.ks_user.entity.AisleNameExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AisleNameMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aisle_compare_name
     *
     * @mbggenerated
     */
    int countByExample(AisleNameExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aisle_compare_name
     *
     * @mbggenerated
     */
    int deleteByExample(AisleNameExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aisle_compare_name
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aisle_compare_name
     *
     * @mbggenerated
     */
    int insert(AisleName record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aisle_compare_name
     *
     * @mbggenerated
     */
    int insertSelective(AisleName record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aisle_compare_name
     *
     * @mbggenerated
     */
    List<AisleName> selectByExample(AisleNameExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aisle_compare_name
     *
     * @mbggenerated
     */
    AisleName selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aisle_compare_name
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") AisleName record, @Param("example") AisleNameExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aisle_compare_name
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") AisleName record, @Param("example") AisleNameExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aisle_compare_name
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AisleName record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aisle_compare_name
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AisleName record);
}