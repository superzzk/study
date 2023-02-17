package com.think.entity;

import java.util.ArrayList;
import java.util.List;

public class ProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductExample() {
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

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductLineIsNull() {
            addCriterion("product_line is null");
            return (Criteria) this;
        }

        public Criteria andProductLineIsNotNull() {
            addCriterion("product_line is not null");
            return (Criteria) this;
        }

        public Criteria andProductLineEqualTo(String value) {
            addCriterion("product_line =", value, "productLine");
            return (Criteria) this;
        }

        public Criteria andProductLineNotEqualTo(String value) {
            addCriterion("product_line <>", value, "productLine");
            return (Criteria) this;
        }

        public Criteria andProductLineGreaterThan(String value) {
            addCriterion("product_line >", value, "productLine");
            return (Criteria) this;
        }

        public Criteria andProductLineGreaterThanOrEqualTo(String value) {
            addCriterion("product_line >=", value, "productLine");
            return (Criteria) this;
        }

        public Criteria andProductLineLessThan(String value) {
            addCriterion("product_line <", value, "productLine");
            return (Criteria) this;
        }

        public Criteria andProductLineLessThanOrEqualTo(String value) {
            addCriterion("product_line <=", value, "productLine");
            return (Criteria) this;
        }

        public Criteria andProductLineLike(String value) {
            addCriterion("product_line like", value, "productLine");
            return (Criteria) this;
        }

        public Criteria andProductLineNotLike(String value) {
            addCriterion("product_line not like", value, "productLine");
            return (Criteria) this;
        }

        public Criteria andProductLineIn(List<String> values) {
            addCriterion("product_line in", values, "productLine");
            return (Criteria) this;
        }

        public Criteria andProductLineNotIn(List<String> values) {
            addCriterion("product_line not in", values, "productLine");
            return (Criteria) this;
        }

        public Criteria andProductLineBetween(String value1, String value2) {
            addCriterion("product_line between", value1, value2, "productLine");
            return (Criteria) this;
        }

        public Criteria andProductLineNotBetween(String value1, String value2) {
            addCriterion("product_line not between", value1, value2, "productLine");
            return (Criteria) this;
        }

        public Criteria andProductScaleIsNull() {
            addCriterion("product_scale is null");
            return (Criteria) this;
        }

        public Criteria andProductScaleIsNotNull() {
            addCriterion("product_scale is not null");
            return (Criteria) this;
        }

        public Criteria andProductScaleEqualTo(String value) {
            addCriterion("product_scale =", value, "productScale");
            return (Criteria) this;
        }

        public Criteria andProductScaleNotEqualTo(String value) {
            addCriterion("product_scale <>", value, "productScale");
            return (Criteria) this;
        }

        public Criteria andProductScaleGreaterThan(String value) {
            addCriterion("product_scale >", value, "productScale");
            return (Criteria) this;
        }

        public Criteria andProductScaleGreaterThanOrEqualTo(String value) {
            addCriterion("product_scale >=", value, "productScale");
            return (Criteria) this;
        }

        public Criteria andProductScaleLessThan(String value) {
            addCriterion("product_scale <", value, "productScale");
            return (Criteria) this;
        }

        public Criteria andProductScaleLessThanOrEqualTo(String value) {
            addCriterion("product_scale <=", value, "productScale");
            return (Criteria) this;
        }

        public Criteria andProductScaleLike(String value) {
            addCriterion("product_scale like", value, "productScale");
            return (Criteria) this;
        }

        public Criteria andProductScaleNotLike(String value) {
            addCriterion("product_scale not like", value, "productScale");
            return (Criteria) this;
        }

        public Criteria andProductScaleIn(List<String> values) {
            addCriterion("product_scale in", values, "productScale");
            return (Criteria) this;
        }

        public Criteria andProductScaleNotIn(List<String> values) {
            addCriterion("product_scale not in", values, "productScale");
            return (Criteria) this;
        }

        public Criteria andProductScaleBetween(String value1, String value2) {
            addCriterion("product_scale between", value1, value2, "productScale");
            return (Criteria) this;
        }

        public Criteria andProductScaleNotBetween(String value1, String value2) {
            addCriterion("product_scale not between", value1, value2, "productScale");
            return (Criteria) this;
        }

        public Criteria andProductVendorIsNull() {
            addCriterion("product_vendor is null");
            return (Criteria) this;
        }

        public Criteria andProductVendorIsNotNull() {
            addCriterion("product_vendor is not null");
            return (Criteria) this;
        }

        public Criteria andProductVendorEqualTo(String value) {
            addCriterion("product_vendor =", value, "productVendor");
            return (Criteria) this;
        }

        public Criteria andProductVendorNotEqualTo(String value) {
            addCriterion("product_vendor <>", value, "productVendor");
            return (Criteria) this;
        }

        public Criteria andProductVendorGreaterThan(String value) {
            addCriterion("product_vendor >", value, "productVendor");
            return (Criteria) this;
        }

        public Criteria andProductVendorGreaterThanOrEqualTo(String value) {
            addCriterion("product_vendor >=", value, "productVendor");
            return (Criteria) this;
        }

        public Criteria andProductVendorLessThan(String value) {
            addCriterion("product_vendor <", value, "productVendor");
            return (Criteria) this;
        }

        public Criteria andProductVendorLessThanOrEqualTo(String value) {
            addCriterion("product_vendor <=", value, "productVendor");
            return (Criteria) this;
        }

        public Criteria andProductVendorLike(String value) {
            addCriterion("product_vendor like", value, "productVendor");
            return (Criteria) this;
        }

        public Criteria andProductVendorNotLike(String value) {
            addCriterion("product_vendor not like", value, "productVendor");
            return (Criteria) this;
        }

        public Criteria andProductVendorIn(List<String> values) {
            addCriterion("product_vendor in", values, "productVendor");
            return (Criteria) this;
        }

        public Criteria andProductVendorNotIn(List<String> values) {
            addCriterion("product_vendor not in", values, "productVendor");
            return (Criteria) this;
        }

        public Criteria andProductVendorBetween(String value1, String value2) {
            addCriterion("product_vendor between", value1, value2, "productVendor");
            return (Criteria) this;
        }

        public Criteria andProductVendorNotBetween(String value1, String value2) {
            addCriterion("product_vendor not between", value1, value2, "productVendor");
            return (Criteria) this;
        }

        public Criteria andQuantityInStockIsNull() {
            addCriterion("quantity_in_stock is null");
            return (Criteria) this;
        }

        public Criteria andQuantityInStockIsNotNull() {
            addCriterion("quantity_in_stock is not null");
            return (Criteria) this;
        }

        public Criteria andQuantityInStockEqualTo(String value) {
            addCriterion("quantity_in_stock =", value, "quantityInStock");
            return (Criteria) this;
        }

        public Criteria andQuantityInStockNotEqualTo(String value) {
            addCriterion("quantity_in_stock <>", value, "quantityInStock");
            return (Criteria) this;
        }

        public Criteria andQuantityInStockGreaterThan(String value) {
            addCriterion("quantity_in_stock >", value, "quantityInStock");
            return (Criteria) this;
        }

        public Criteria andQuantityInStockGreaterThanOrEqualTo(String value) {
            addCriterion("quantity_in_stock >=", value, "quantityInStock");
            return (Criteria) this;
        }

        public Criteria andQuantityInStockLessThan(String value) {
            addCriterion("quantity_in_stock <", value, "quantityInStock");
            return (Criteria) this;
        }

        public Criteria andQuantityInStockLessThanOrEqualTo(String value) {
            addCriterion("quantity_in_stock <=", value, "quantityInStock");
            return (Criteria) this;
        }

        public Criteria andQuantityInStockLike(String value) {
            addCriterion("quantity_in_stock like", value, "quantityInStock");
            return (Criteria) this;
        }

        public Criteria andQuantityInStockNotLike(String value) {
            addCriterion("quantity_in_stock not like", value, "quantityInStock");
            return (Criteria) this;
        }

        public Criteria andQuantityInStockIn(List<String> values) {
            addCriterion("quantity_in_stock in", values, "quantityInStock");
            return (Criteria) this;
        }

        public Criteria andQuantityInStockNotIn(List<String> values) {
            addCriterion("quantity_in_stock not in", values, "quantityInStock");
            return (Criteria) this;
        }

        public Criteria andQuantityInStockBetween(String value1, String value2) {
            addCriterion("quantity_in_stock between", value1, value2, "quantityInStock");
            return (Criteria) this;
        }

        public Criteria andQuantityInStockNotBetween(String value1, String value2) {
            addCriterion("quantity_in_stock not between", value1, value2, "quantityInStock");
            return (Criteria) this;
        }

        public Criteria andBuyPriceIsNull() {
            addCriterion("buy_price is null");
            return (Criteria) this;
        }

        public Criteria andBuyPriceIsNotNull() {
            addCriterion("buy_price is not null");
            return (Criteria) this;
        }

        public Criteria andBuyPriceEqualTo(Long value) {
            addCriterion("buy_price =", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceNotEqualTo(Long value) {
            addCriterion("buy_price <>", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceGreaterThan(Long value) {
            addCriterion("buy_price >", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("buy_price >=", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceLessThan(Long value) {
            addCriterion("buy_price <", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceLessThanOrEqualTo(Long value) {
            addCriterion("buy_price <=", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceIn(List<Long> values) {
            addCriterion("buy_price in", values, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceNotIn(List<Long> values) {
            addCriterion("buy_price not in", values, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceBetween(Long value1, Long value2) {
            addCriterion("buy_price between", value1, value2, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceNotBetween(Long value1, Long value2) {
            addCriterion("buy_price not between", value1, value2, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andMsrpIsNull() {
            addCriterion("MSRP is null");
            return (Criteria) this;
        }

        public Criteria andMsrpIsNotNull() {
            addCriterion("MSRP is not null");
            return (Criteria) this;
        }

        public Criteria andMsrpEqualTo(String value) {
            addCriterion("MSRP =", value, "msrp");
            return (Criteria) this;
        }

        public Criteria andMsrpNotEqualTo(String value) {
            addCriterion("MSRP <>", value, "msrp");
            return (Criteria) this;
        }

        public Criteria andMsrpGreaterThan(String value) {
            addCriterion("MSRP >", value, "msrp");
            return (Criteria) this;
        }

        public Criteria andMsrpGreaterThanOrEqualTo(String value) {
            addCriterion("MSRP >=", value, "msrp");
            return (Criteria) this;
        }

        public Criteria andMsrpLessThan(String value) {
            addCriterion("MSRP <", value, "msrp");
            return (Criteria) this;
        }

        public Criteria andMsrpLessThanOrEqualTo(String value) {
            addCriterion("MSRP <=", value, "msrp");
            return (Criteria) this;
        }

        public Criteria andMsrpLike(String value) {
            addCriterion("MSRP like", value, "msrp");
            return (Criteria) this;
        }

        public Criteria andMsrpNotLike(String value) {
            addCriterion("MSRP not like", value, "msrp");
            return (Criteria) this;
        }

        public Criteria andMsrpIn(List<String> values) {
            addCriterion("MSRP in", values, "msrp");
            return (Criteria) this;
        }

        public Criteria andMsrpNotIn(List<String> values) {
            addCriterion("MSRP not in", values, "msrp");
            return (Criteria) this;
        }

        public Criteria andMsrpBetween(String value1, String value2) {
            addCriterion("MSRP between", value1, value2, "msrp");
            return (Criteria) this;
        }

        public Criteria andMsrpNotBetween(String value1, String value2) {
            addCriterion("MSRP not between", value1, value2, "msrp");
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