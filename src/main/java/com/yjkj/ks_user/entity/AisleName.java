package com.yjkj.ks_user.entity;

public class AisleName {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column aisle_compare_name.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column aisle_compare_name.aisle_code
     *
     * @mbggenerated
     */
    private String aisleCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column aisle_compare_name.aisle_name
     *
     * @mbggenerated
     */
    private String aisleName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column aisle_compare_name.id
     *
     * @return the value of aisle_compare_name.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column aisle_compare_name.id
     *
     * @param id the value for aisle_compare_name.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column aisle_compare_name.aisle_code
     *
     * @return the value of aisle_compare_name.aisle_code
     *
     * @mbggenerated
     */
    public String getAisleCode() {
        return aisleCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column aisle_compare_name.aisle_code
     *
     * @param aisleCode the value for aisle_compare_name.aisle_code
     *
     * @mbggenerated
     */
    public void setAisleCode(String aisleCode) {
        this.aisleCode = aisleCode == null ? null : aisleCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column aisle_compare_name.aisle_name
     *
     * @return the value of aisle_compare_name.aisle_name
     *
     * @mbggenerated
     */
    public String getAisleName() {
        return aisleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column aisle_compare_name.aisle_name
     *
     * @param aisleName the value for aisle_compare_name.aisle_name
     *
     * @mbggenerated
     */
    public void setAisleName(String aisleName) {
        this.aisleName = aisleName == null ? null : aisleName.trim();
    }
}