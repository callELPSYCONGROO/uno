package indi.smt.uno.crawler.entity;

import java.util.ArrayList;
import java.util.List;

public class CategoryCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public CategoryCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
    }

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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDomainIsNull() {
            addCriterion("domain is null");
            return (Criteria) this;
        }

        public Criteria andDomainIsNotNull() {
            addCriterion("domain is not null");
            return (Criteria) this;
        }

        public Criteria andDomainEqualTo(String value) {
            addCriterion("domain =", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotEqualTo(String value) {
            addCriterion("domain <>", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainGreaterThan(String value) {
            addCriterion("domain >", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainGreaterThanOrEqualTo(String value) {
            addCriterion("domain >=", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainLessThan(String value) {
            addCriterion("domain <", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainLessThanOrEqualTo(String value) {
            addCriterion("domain <=", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainLike(String value) {
            addCriterion("domain like", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotLike(String value) {
            addCriterion("domain not like", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainIn(List<String> values) {
            addCriterion("domain in", values, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotIn(List<String> values) {
            addCriterion("domain not in", values, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainBetween(String value1, String value2) {
            addCriterion("domain between", value1, value2, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotBetween(String value1, String value2) {
            addCriterion("domain not between", value1, value2, "domain");
            return (Criteria) this;
        }

        public Criteria andCateNameIsNull() {
            addCriterion("cateName is null");
            return (Criteria) this;
        }

        public Criteria andCateNameIsNotNull() {
            addCriterion("cateName is not null");
            return (Criteria) this;
        }

        public Criteria andCateNameEqualTo(String value) {
            addCriterion("cateName =", value, "cateName");
            return (Criteria) this;
        }

        public Criteria andCateNameNotEqualTo(String value) {
            addCriterion("cateName <>", value, "cateName");
            return (Criteria) this;
        }

        public Criteria andCateNameGreaterThan(String value) {
            addCriterion("cateName >", value, "cateName");
            return (Criteria) this;
        }

        public Criteria andCateNameGreaterThanOrEqualTo(String value) {
            addCriterion("cateName >=", value, "cateName");
            return (Criteria) this;
        }

        public Criteria andCateNameLessThan(String value) {
            addCriterion("cateName <", value, "cateName");
            return (Criteria) this;
        }

        public Criteria andCateNameLessThanOrEqualTo(String value) {
            addCriterion("cateName <=", value, "cateName");
            return (Criteria) this;
        }

        public Criteria andCateNameLike(String value) {
            addCriterion("cateName like", value, "cateName");
            return (Criteria) this;
        }

        public Criteria andCateNameNotLike(String value) {
            addCriterion("cateName not like", value, "cateName");
            return (Criteria) this;
        }

        public Criteria andCateNameIn(List<String> values) {
            addCriterion("cateName in", values, "cateName");
            return (Criteria) this;
        }

        public Criteria andCateNameNotIn(List<String> values) {
            addCriterion("cateName not in", values, "cateName");
            return (Criteria) this;
        }

        public Criteria andCateNameBetween(String value1, String value2) {
            addCriterion("cateName between", value1, value2, "cateName");
            return (Criteria) this;
        }

        public Criteria andCateNameNotBetween(String value1, String value2) {
            addCriterion("cateName not between", value1, value2, "cateName");
            return (Criteria) this;
        }

        public Criteria andParentNameIsNull() {
            addCriterion("parentName is null");
            return (Criteria) this;
        }

        public Criteria andParentNameIsNotNull() {
            addCriterion("parentName is not null");
            return (Criteria) this;
        }

        public Criteria andParentNameEqualTo(String value) {
            addCriterion("parentName =", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameNotEqualTo(String value) {
            addCriterion("parentName <>", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameGreaterThan(String value) {
            addCriterion("parentName >", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameGreaterThanOrEqualTo(String value) {
            addCriterion("parentName >=", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameLessThan(String value) {
            addCriterion("parentName <", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameLessThanOrEqualTo(String value) {
            addCriterion("parentName <=", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameLike(String value) {
            addCriterion("parentName like", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameNotLike(String value) {
            addCriterion("parentName not like", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameIn(List<String> values) {
            addCriterion("parentName in", values, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameNotIn(List<String> values) {
            addCriterion("parentName not in", values, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameBetween(String value1, String value2) {
            addCriterion("parentName between", value1, value2, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameNotBetween(String value1, String value2) {
            addCriterion("parentName not between", value1, value2, "parentName");
            return (Criteria) this;
        }

        public Criteria andDomainLikeInsensitive(String value) {
            addCriterion("upper(domain) like", value.toUpperCase(), "domain");
            return (Criteria) this;
        }

        public Criteria andCateNameLikeInsensitive(String value) {
            addCriterion("upper(cateName) like", value.toUpperCase(), "cateName");
            return (Criteria) this;
        }

        public Criteria andParentNameLikeInsensitive(String value) {
            addCriterion("upper(parentName) like", value.toUpperCase(), "parentName");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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