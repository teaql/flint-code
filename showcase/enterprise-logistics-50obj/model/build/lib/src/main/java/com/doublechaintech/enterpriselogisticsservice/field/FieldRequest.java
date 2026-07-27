package com.doublechaintech.enterpriselogisticsservice.field;

import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;

public class FieldRequest<T extends Field> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public FieldRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public FieldRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public FieldRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public FieldRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public FieldRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public FieldRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public FieldRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (FieldRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public FieldRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public FieldRequest<T> matchingAnyOf(FieldRequest field){
        super.internalMatchAny(field);
        return this;
    }

    public FieldRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public FieldRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public FieldRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public FieldRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectName().selectVersion();
    }

    public FieldRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public FieldRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectVersion();
    }

    public FieldRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectName().selectVersion();
    }


    public FieldRequest<T> selectId(){
       selectProperty(Field.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FieldRequest<T> unselectId(){
       unselectProperty(Field.ID_PROPERTY);
       return this;
    }
    public FieldRequest<T> selectName(){
       selectProperty(Field.NAME_PROPERTY);
       return this;
    }

    /**
     * fill the name with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  name) to fetch name property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FieldRequest<T> unselectName(){
       unselectProperty(Field.NAME_PROPERTY);
       return this;
    }
    public FieldRequest<T> selectVersion(){
       selectProperty(Field.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FieldRequest<T> unselectVersion(){
       unselectProperty(Field.VERSION_PROPERTY);
       return this;
    }

    public FieldRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Field.ID_PROPERTY, operator, values);
    }

    public FieldRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public FieldRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public FieldRequest<T> filterByName(String... name){
      if (name == null || name.length == 0) {
        throw new IllegalArgumentException("filterByName parameter name cannot be empty");
      }
      return appendSearchCriteria(createNameCriteria(Operator.EQUAL, (Object[])name));
    }

    public FieldRequest<T> withName(Operator operator, Object... values){
       return appendSearchCriteria(createNameCriteria(operator, values));
    }

    public FieldRequest<T> withNameIsUnknown(){
       return withName(Operator.IS_NULL);
    }

    public FieldRequest<T> withNameIsKnown(){
       return withName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Field.NAME_PROPERTY, operator, values);
    }

    public FieldRequest<T> withNameGreaterThan(String name){
       return withName(Operator.GREATER_THAN, name);
    }

    public FieldRequest<T> withNameGreaterThanOrEqualTo(String name){
       return withName(Operator.GREATER_THAN_OR_EQUAL, name);
    }

    public FieldRequest<T> withNameLessThan(String name){
       return withName(Operator.LESS_THAN, name);
    }

    public FieldRequest<T> withNameLessThanOrEqualTo(String name){
       return withName(Operator.LESS_THAN_OR_EQUAL, name);
    }

    public FieldRequest<T> withNameBetween(String startOfName, String endOfName){
       return withName(Operator.BETWEEN, startOfName, endOfName);
    }
    public FieldRequest<T> withNameStartingWith(String name){
       return withName(Operator.BEGIN_WITH, name);
    }
    public FieldRequest<T> withNameContaining(String name){
       return withName(Operator.CONTAIN, name);
    }

    public FieldRequest<T> withNameEndingWith(String name){
       return withName(Operator.END_WITH, name);
    }

    public FieldRequest<T> withNameIs(String name){
       return withName(Operator.EQUAL, name);
    }

    public FieldRequest<T> withNameSoundingLike(String name){
       return withName(Operator.SOUNDS_LIKE, name);
    }



    public FieldRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public FieldRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public FieldRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public FieldRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Field.VERSION_PROPERTY, operator, values);
    }

    public FieldRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public FieldRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public FieldRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public FieldRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public FieldRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public FieldRequest<T> count(){
        super.count();
        return this;
    }
    public FieldRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }

    public FieldRequest<T> groupById(){
       groupBy(Field.ID_PROPERTY);
       return this;
    }

    public FieldRequest<T> groupByIdAs(String retName){
       groupBy(retName, Field.ID_PROPERTY);
       return this;
    }

    public FieldRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, Field.ID_PROPERTY, function);
       return this;
    }

    public FieldRequest<T> groupByName(){
       groupBy(Field.NAME_PROPERTY);
       return this;
    }

    public FieldRequest<T> groupByNameAs(String retName){
       groupBy(retName, Field.NAME_PROPERTY);
       return this;
    }

    public FieldRequest<T> groupByNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, Field.NAME_PROPERTY, function);
       return this;
    }

    public FieldRequest<T> groupByVersion(){
       groupBy(Field.VERSION_PROPERTY);
       return this;
    }

    public FieldRequest<T> groupByVersionAs(String retName){
       groupBy(retName, Field.VERSION_PROPERTY);
       return this;
    }

    public FieldRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, Field.VERSION_PROPERTY, function);
       return this;
    }



    public FieldRequest<T> orderByIdAscending(){
       addOrderByAscending(Field.ID_PROPERTY);
       return this;
    }

    public FieldRequest<T> orderByIdDescending(){
       addOrderByDescending(Field.ID_PROPERTY);
       return this;
    }

    public FieldRequest<T> orderByNameAscending(){
       addOrderByAscending(Field.NAME_PROPERTY);
       return this;
    }

    public FieldRequest<T> orderByNameDescending(){
       addOrderByDescending(Field.NAME_PROPERTY);
       return this;
    }
    public FieldRequest<T> orderByNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Field.NAME_PROPERTY);
       return this;
    }

    public FieldRequest<T> orderByNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Field.NAME_PROPERTY);
       return this;
    }
    public FieldRequest<T> orderByVersionAscending(){
       addOrderByAscending(Field.VERSION_PROPERTY);
       return this;
    }

    public FieldRequest<T> orderByVersionDescending(){
       addOrderByDescending(Field.VERSION_PROPERTY);
       return this;
    }





    /**
     * get topN records
     * @param topN  records number
     */
    public FieldRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public FieldRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public FieldRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public FieldRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public FieldRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}