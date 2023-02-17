package com.zzk.order.entity;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderDetailExample() {
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

        public Criteria andOrderCodeIsNull() {
            addCriterion("order_code is null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNotNull() {
            addCriterion("order_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeEqualTo(String value) {
            addCriterion("order_code =", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotEqualTo(String value) {
            addCriterion("order_code <>", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThan(String value) {
            addCriterion("order_code >", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("order_code >=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThan(String value) {
            addCriterion("order_code <", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("order_code <=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLike(String value) {
            addCriterion("order_code like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotLike(String value) {
            addCriterion("order_code not like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIn(List<String> values) {
            addCriterion("order_code in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotIn(List<String> values) {
            addCriterion("order_code not in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeBetween(String value1, String value2) {
            addCriterion("order_code between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotBetween(String value1, String value2) {
            addCriterion("order_code not between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeIsNull() {
            addCriterion("product_code is null");
            return (Criteria) this;
        }

        public Criteria andProductCodeIsNotNull() {
            addCriterion("product_code is not null");
            return (Criteria) this;
        }

        public Criteria andProductCodeEqualTo(String value) {
            addCriterion("product_code =", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotEqualTo(String value) {
            addCriterion("product_code <>", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeGreaterThan(String value) {
            addCriterion("product_code >", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeGreaterThanOrEqualTo(String value) {
            addCriterion("product_code >=", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLessThan(String value) {
            addCriterion("product_code <", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLessThanOrEqualTo(String value) {
            addCriterion("product_code <=", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLike(String value) {
            addCriterion("product_code like", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotLike(String value) {
            addCriterion("product_code not like", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeIn(List<String> values) {
            addCriterion("product_code in", values, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotIn(List<String> values) {
            addCriterion("product_code not in", values, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeBetween(String value1, String value2) {
            addCriterion("product_code between", value1, value2, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotBetween(String value1, String value2) {
            addCriterion("product_code not between", value1, value2, "productCode");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderedIsNull() {
            addCriterion("quantity_ordered is null");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderedIsNotNull() {
            addCriterion("quantity_ordered is not null");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderedEqualTo(Integer value) {
            addCriterion("quantity_ordered =", value, "quantityOrdered");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderedNotEqualTo(Integer value) {
            addCriterion("quantity_ordered <>", value, "quantityOrdered");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderedGreaterThan(Integer value) {
            addCriterion("quantity_ordered >", value, "quantityOrdered");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderedGreaterThanOrEqualTo(Integer value) {
            addCriterion("quantity_ordered >=", value, "quantityOrdered");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderedLessThan(Integer value) {
            addCriterion("quantity_ordered <", value, "quantityOrdered");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderedLessThanOrEqualTo(Integer value) {
            addCriterion("quantity_ordered <=", value, "quantityOrdered");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderedIn(List<Integer> values) {
            addCriterion("quantity_ordered in", values, "quantityOrdered");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderedNotIn(List<Integer> values) {
            addCriterion("quantity_ordered not in", values, "quantityOrdered");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderedBetween(Integer value1, Integer value2) {
            addCriterion("quantity_ordered between", value1, value2, "quantityOrdered");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderedNotBetween(Integer value1, Integer value2) {
            addCriterion("quantity_ordered not between", value1, value2, "quantityOrdered");
            return (Criteria) this;
        }

        public Criteria andPriceEachIsNull() {
            addCriterion("price_each is null");
            return (Criteria) this;
        }

        public Criteria andPriceEachIsNotNull() {
            addCriterion("price_each is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEachEqualTo(Double value) {
            addCriterion("price_each =", value, "priceEach");
            return (Criteria) this;
        }

        public Criteria andPriceEachNotEqualTo(Double value) {
            addCriterion("price_each <>", value, "priceEach");
            return (Criteria) this;
        }

        public Criteria andPriceEachGreaterThan(Double value) {
            addCriterion("price_each >", value, "priceEach");
            return (Criteria) this;
        }

        public Criteria andPriceEachGreaterThanOrEqualTo(Double value) {
            addCriterion("price_each >=", value, "priceEach");
            return (Criteria) this;
        }

        public Criteria andPriceEachLessThan(Double value) {
            addCriterion("price_each <", value, "priceEach");
            return (Criteria) this;
        }

        public Criteria andPriceEachLessThanOrEqualTo(Double value) {
            addCriterion("price_each <=", value, "priceEach");
            return (Criteria) this;
        }

        public Criteria andPriceEachIn(List<Double> values) {
            addCriterion("price_each in", values, "priceEach");
            return (Criteria) this;
        }

        public Criteria andPriceEachNotIn(List<Double> values) {
            addCriterion("price_each not in", values, "priceEach");
            return (Criteria) this;
        }

        public Criteria andPriceEachBetween(Double value1, Double value2) {
            addCriterion("price_each between", value1, value2, "priceEach");
            return (Criteria) this;
        }

        public Criteria andPriceEachNotBetween(Double value1, Double value2) {
            addCriterion("price_each not between", value1, value2, "priceEach");
            return (Criteria) this;
        }

        public Criteria andOrderLineNumberIsNull() {
            addCriterion("order_line_number is null");
            return (Criteria) this;
        }

        public Criteria andOrderLineNumberIsNotNull() {
            addCriterion("order_line_number is not null");
            return (Criteria) this;
        }

        public Criteria andOrderLineNumberEqualTo(Integer value) {
            addCriterion("order_line_number =", value, "orderLineNumber");
            return (Criteria) this;
        }

        public Criteria andOrderLineNumberNotEqualTo(Integer value) {
            addCriterion("order_line_number <>", value, "orderLineNumber");
            return (Criteria) this;
        }

        public Criteria andOrderLineNumberGreaterThan(Integer value) {
            addCriterion("order_line_number >", value, "orderLineNumber");
            return (Criteria) this;
        }

        public Criteria andOrderLineNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_line_number >=", value, "orderLineNumber");
            return (Criteria) this;
        }

        public Criteria andOrderLineNumberLessThan(Integer value) {
            addCriterion("order_line_number <", value, "orderLineNumber");
            return (Criteria) this;
        }

        public Criteria andOrderLineNumberLessThanOrEqualTo(Integer value) {
            addCriterion("order_line_number <=", value, "orderLineNumber");
            return (Criteria) this;
        }

        public Criteria andOrderLineNumberIn(List<Integer> values) {
            addCriterion("order_line_number in", values, "orderLineNumber");
            return (Criteria) this;
        }

        public Criteria andOrderLineNumberNotIn(List<Integer> values) {
            addCriterion("order_line_number not in", values, "orderLineNumber");
            return (Criteria) this;
        }

        public Criteria andOrderLineNumberBetween(Integer value1, Integer value2) {
            addCriterion("order_line_number between", value1, value2, "orderLineNumber");
            return (Criteria) this;
        }

        public Criteria andOrderLineNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("order_line_number not between", value1, value2, "orderLineNumber");
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