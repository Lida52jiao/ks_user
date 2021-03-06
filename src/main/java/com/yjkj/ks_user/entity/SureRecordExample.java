package com.yjkj.ks_user.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SureRecordExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_mp_surerecord
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_mp_surerecord
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_mp_surerecord
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_surerecord
     *
     * @mbggenerated
     */
    public SureRecordExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_surerecord
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_surerecord
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_surerecord
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_surerecord
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_surerecord
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_surerecord
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_surerecord
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_surerecord
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_surerecord
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_surerecord
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_mp_surerecord
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUseuserIsNull() {
            addCriterion("useUser is null");
            return (Criteria) this;
        }

        public Criteria andUseuserIsNotNull() {
            addCriterion("useUser is not null");
            return (Criteria) this;
        }

        public Criteria andUseuserEqualTo(String value) {
            addCriterion("useUser =", value, "useuser");
            return (Criteria) this;
        }

        public Criteria andUseuserNotEqualTo(String value) {
            addCriterion("useUser <>", value, "useuser");
            return (Criteria) this;
        }

        public Criteria andUseuserGreaterThan(String value) {
            addCriterion("useUser >", value, "useuser");
            return (Criteria) this;
        }

        public Criteria andUseuserGreaterThanOrEqualTo(String value) {
            addCriterion("useUser >=", value, "useuser");
            return (Criteria) this;
        }

        public Criteria andUseuserLessThan(String value) {
            addCriterion("useUser <", value, "useuser");
            return (Criteria) this;
        }

        public Criteria andUseuserLessThanOrEqualTo(String value) {
            addCriterion("useUser <=", value, "useuser");
            return (Criteria) this;
        }

        public Criteria andUseuserLike(String value) {
            addCriterion("useUser like", value, "useuser");
            return (Criteria) this;
        }

        public Criteria andUseuserNotLike(String value) {
            addCriterion("useUser not like", value, "useuser");
            return (Criteria) this;
        }

        public Criteria andUseuserIn(List<String> values) {
            addCriterion("useUser in", values, "useuser");
            return (Criteria) this;
        }

        public Criteria andUseuserNotIn(List<String> values) {
            addCriterion("useUser not in", values, "useuser");
            return (Criteria) this;
        }

        public Criteria andUseuserBetween(String value1, String value2) {
            addCriterion("useUser between", value1, value2, "useuser");
            return (Criteria) this;
        }

        public Criteria andUseuserNotBetween(String value1, String value2) {
            addCriterion("useUser not between", value1, value2, "useuser");
            return (Criteria) this;
        }

        public Criteria andUndoagentidIsNull() {
            addCriterion("undoAgentId is null");
            return (Criteria) this;
        }

        public Criteria andUndoagentidIsNotNull() {
            addCriterion("undoAgentId is not null");
            return (Criteria) this;
        }

        public Criteria andUndoagentidEqualTo(String value) {
            addCriterion("undoAgentId =", value, "undoagentid");
            return (Criteria) this;
        }

        public Criteria andUndoagentidNotEqualTo(String value) {
            addCriterion("undoAgentId <>", value, "undoagentid");
            return (Criteria) this;
        }

        public Criteria andUndoagentidGreaterThan(String value) {
            addCriterion("undoAgentId >", value, "undoagentid");
            return (Criteria) this;
        }

        public Criteria andUndoagentidGreaterThanOrEqualTo(String value) {
            addCriterion("undoAgentId >=", value, "undoagentid");
            return (Criteria) this;
        }

        public Criteria andUndoagentidLessThan(String value) {
            addCriterion("undoAgentId <", value, "undoagentid");
            return (Criteria) this;
        }

        public Criteria andUndoagentidLessThanOrEqualTo(String value) {
            addCriterion("undoAgentId <=", value, "undoagentid");
            return (Criteria) this;
        }

        public Criteria andUndoagentidLike(String value) {
            addCriterion("undoAgentId like", value, "undoagentid");
            return (Criteria) this;
        }

        public Criteria andUndoagentidNotLike(String value) {
            addCriterion("undoAgentId not like", value, "undoagentid");
            return (Criteria) this;
        }

        public Criteria andUndoagentidIn(List<String> values) {
            addCriterion("undoAgentId in", values, "undoagentid");
            return (Criteria) this;
        }

        public Criteria andUndoagentidNotIn(List<String> values) {
            addCriterion("undoAgentId not in", values, "undoagentid");
            return (Criteria) this;
        }

        public Criteria andUndoagentidBetween(String value1, String value2) {
            addCriterion("undoAgentId between", value1, value2, "undoagentid");
            return (Criteria) this;
        }

        public Criteria andUndoagentidNotBetween(String value1, String value2) {
            addCriterion("undoAgentId not between", value1, value2, "undoagentid");
            return (Criteria) this;
        }

        public Criteria andBindagentidIsNull() {
            addCriterion("bindAgentId is null");
            return (Criteria) this;
        }

        public Criteria andBindagentidIsNotNull() {
            addCriterion("bindAgentId is not null");
            return (Criteria) this;
        }

        public Criteria andBindagentidEqualTo(String value) {
            addCriterion("bindAgentId =", value, "bindagentid");
            return (Criteria) this;
        }

        public Criteria andBindagentidNotEqualTo(String value) {
            addCriterion("bindAgentId <>", value, "bindagentid");
            return (Criteria) this;
        }

        public Criteria andBindagentidGreaterThan(String value) {
            addCriterion("bindAgentId >", value, "bindagentid");
            return (Criteria) this;
        }

        public Criteria andBindagentidGreaterThanOrEqualTo(String value) {
            addCriterion("bindAgentId >=", value, "bindagentid");
            return (Criteria) this;
        }

        public Criteria andBindagentidLessThan(String value) {
            addCriterion("bindAgentId <", value, "bindagentid");
            return (Criteria) this;
        }

        public Criteria andBindagentidLessThanOrEqualTo(String value) {
            addCriterion("bindAgentId <=", value, "bindagentid");
            return (Criteria) this;
        }

        public Criteria andBindagentidLike(String value) {
            addCriterion("bindAgentId like", value, "bindagentid");
            return (Criteria) this;
        }

        public Criteria andBindagentidNotLike(String value) {
            addCriterion("bindAgentId not like", value, "bindagentid");
            return (Criteria) this;
        }

        public Criteria andBindagentidIn(List<String> values) {
            addCriterion("bindAgentId in", values, "bindagentid");
            return (Criteria) this;
        }

        public Criteria andBindagentidNotIn(List<String> values) {
            addCriterion("bindAgentId not in", values, "bindagentid");
            return (Criteria) this;
        }

        public Criteria andBindagentidBetween(String value1, String value2) {
            addCriterion("bindAgentId between", value1, value2, "bindagentid");
            return (Criteria) this;
        }

        public Criteria andBindagentidNotBetween(String value1, String value2) {
            addCriterion("bindAgentId not between", value1, value2, "bindagentid");
            return (Criteria) this;
        }

        public Criteria andBindmerchantidIsNull() {
            addCriterion("bindMerchantId is null");
            return (Criteria) this;
        }

        public Criteria andBindmerchantidIsNotNull() {
            addCriterion("bindMerchantId is not null");
            return (Criteria) this;
        }

        public Criteria andBindmerchantidEqualTo(String value) {
            addCriterion("bindMerchantId =", value, "bindmerchantid");
            return (Criteria) this;
        }

        public Criteria andBindmerchantidNotEqualTo(String value) {
            addCriterion("bindMerchantId <>", value, "bindmerchantid");
            return (Criteria) this;
        }

        public Criteria andBindmerchantidGreaterThan(String value) {
            addCriterion("bindMerchantId >", value, "bindmerchantid");
            return (Criteria) this;
        }

        public Criteria andBindmerchantidGreaterThanOrEqualTo(String value) {
            addCriterion("bindMerchantId >=", value, "bindmerchantid");
            return (Criteria) this;
        }

        public Criteria andBindmerchantidLessThan(String value) {
            addCriterion("bindMerchantId <", value, "bindmerchantid");
            return (Criteria) this;
        }

        public Criteria andBindmerchantidLessThanOrEqualTo(String value) {
            addCriterion("bindMerchantId <=", value, "bindmerchantid");
            return (Criteria) this;
        }

        public Criteria andBindmerchantidLike(String value) {
            addCriterion("bindMerchantId like", value, "bindmerchantid");
            return (Criteria) this;
        }

        public Criteria andBindmerchantidNotLike(String value) {
            addCriterion("bindMerchantId not like", value, "bindmerchantid");
            return (Criteria) this;
        }

        public Criteria andBindmerchantidIn(List<String> values) {
            addCriterion("bindMerchantId in", values, "bindmerchantid");
            return (Criteria) this;
        }

        public Criteria andBindmerchantidNotIn(List<String> values) {
            addCriterion("bindMerchantId not in", values, "bindmerchantid");
            return (Criteria) this;
        }

        public Criteria andBindmerchantidBetween(String value1, String value2) {
            addCriterion("bindMerchantId between", value1, value2, "bindmerchantid");
            return (Criteria) this;
        }

        public Criteria andBindmerchantidNotBetween(String value1, String value2) {
            addCriterion("bindMerchantId not between", value1, value2, "bindmerchantid");
            return (Criteria) this;
        }

        public Criteria andBindtimeIsNull() {
            addCriterion("bindTime is null");
            return (Criteria) this;
        }

        public Criteria andBindtimeIsNotNull() {
            addCriterion("bindTime is not null");
            return (Criteria) this;
        }

        public Criteria andBindtimeEqualTo(Date value) {
            addCriterion("bindTime =", value, "bindtime");
            return (Criteria) this;
        }

        public Criteria andBindtimeNotEqualTo(Date value) {
            addCriterion("bindTime <>", value, "bindtime");
            return (Criteria) this;
        }

        public Criteria andBindtimeGreaterThan(Date value) {
            addCriterion("bindTime >", value, "bindtime");
            return (Criteria) this;
        }

        public Criteria andBindtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("bindTime >=", value, "bindtime");
            return (Criteria) this;
        }

        public Criteria andBindtimeLessThan(Date value) {
            addCriterion("bindTime <", value, "bindtime");
            return (Criteria) this;
        }

        public Criteria andBindtimeLessThanOrEqualTo(Date value) {
            addCriterion("bindTime <=", value, "bindtime");
            return (Criteria) this;
        }

        public Criteria andBindtimeIn(List<Date> values) {
            addCriterion("bindTime in", values, "bindtime");
            return (Criteria) this;
        }

        public Criteria andBindtimeNotIn(List<Date> values) {
            addCriterion("bindTime not in", values, "bindtime");
            return (Criteria) this;
        }

        public Criteria andBindtimeBetween(Date value1, Date value2) {
            addCriterion("bindTime between", value1, value2, "bindtime");
            return (Criteria) this;
        }

        public Criteria andBindtimeNotBetween(Date value1, Date value2) {
            addCriterion("bindTime not between", value1, value2, "bindtime");
            return (Criteria) this;
        }

        public Criteria andAgentstatusIsNull() {
            addCriterion("agentStatus is null");
            return (Criteria) this;
        }

        public Criteria andAgentstatusIsNotNull() {
            addCriterion("agentStatus is not null");
            return (Criteria) this;
        }

        public Criteria andAgentstatusEqualTo(String value) {
            addCriterion("agentStatus =", value, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentstatusNotEqualTo(String value) {
            addCriterion("agentStatus <>", value, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentstatusGreaterThan(String value) {
            addCriterion("agentStatus >", value, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentstatusGreaterThanOrEqualTo(String value) {
            addCriterion("agentStatus >=", value, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentstatusLessThan(String value) {
            addCriterion("agentStatus <", value, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentstatusLessThanOrEqualTo(String value) {
            addCriterion("agentStatus <=", value, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentstatusLike(String value) {
            addCriterion("agentStatus like", value, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentstatusNotLike(String value) {
            addCriterion("agentStatus not like", value, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentstatusIn(List<String> values) {
            addCriterion("agentStatus in", values, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentstatusNotIn(List<String> values) {
            addCriterion("agentStatus not in", values, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentstatusBetween(String value1, String value2) {
            addCriterion("agentStatus between", value1, value2, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentstatusNotBetween(String value1, String value2) {
            addCriterion("agentStatus not between", value1, value2, "agentstatus");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_mp_surerecord
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_mp_surerecord
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}