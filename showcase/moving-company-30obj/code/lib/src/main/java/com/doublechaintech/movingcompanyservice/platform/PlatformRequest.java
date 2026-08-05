package com.doublechaintech.movingcompanyservice.platform;

import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDateTime;
import java.util.Date;

public class PlatformRequest<T extends Platform> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public PlatformRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public PlatformRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public PlatformRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public PlatformRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public PlatformRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public PlatformRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public PlatformRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (PlatformRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public PlatformRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public PlatformRequest<T> matchingAnyOf(PlatformRequest platform){
        super.internalMatchAny(platform);
        return this;
    }

    public PlatformRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public PlatformRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public PlatformRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public PlatformRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectVersion().selectApiVersion().selectMaintenanceMode().selectCreateTime().selectUpdateTime();
    }

    public PlatformRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public PlatformRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectVersion().selectApiVersion().selectMaintenanceMode().selectCreateTime().selectUpdateTime();
    }

    public PlatformRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectVersion().selectApiVersion().selectMaintenanceMode().selectCreateTime().selectUpdateTime();
    }


    public PlatformRequest<T> selectId(){
       selectProperty(Platform.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PlatformRequest<T> unselectId(){
       unselectProperty(Platform.ID_PROPERTY);
       return this;
    }
    public PlatformRequest<T> selectVersion(){
       selectProperty(Platform.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PlatformRequest<T> unselectVersion(){
       unselectProperty(Platform.VERSION_PROPERTY);
       return this;
    }
    public PlatformRequest<T> selectApiVersion(){
       selectProperty(Platform.API_VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the apiVersion with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  apiVersion) to fetch apiVersion property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PlatformRequest<T> unselectApiVersion(){
       unselectProperty(Platform.API_VERSION_PROPERTY);
       return this;
    }
    public PlatformRequest<T> selectMaintenanceMode(){
       selectProperty(Platform.MAINTENANCE_MODE_PROPERTY);
       return this;
    }

    /**
     * fill the maintenanceMode with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  maintenanceMode) to fetch maintenanceMode property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PlatformRequest<T> unselectMaintenanceMode(){
       unselectProperty(Platform.MAINTENANCE_MODE_PROPERTY);
       return this;
    }
    public PlatformRequest<T> selectCreateTime(){
       selectProperty(Platform.CREATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createTime) to fetch createTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PlatformRequest<T> unselectCreateTime(){
       unselectProperty(Platform.CREATE_TIME_PROPERTY);
       return this;
    }
    public PlatformRequest<T> selectUpdateTime(){
       selectProperty(Platform.UPDATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updateTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updateTime) to fetch updateTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PlatformRequest<T> unselectUpdateTime(){
       unselectProperty(Platform.UPDATE_TIME_PROPERTY);
       return this;
    }

    public PlatformRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Platform.ID_PROPERTY, operator, values);
    }

    public PlatformRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public PlatformRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public PlatformRequest<T> filterByVersion(String... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public PlatformRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public PlatformRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public PlatformRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Platform.VERSION_PROPERTY, operator, values);
    }

    public PlatformRequest<T> withVersionGreaterThan(String version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public PlatformRequest<T> withVersionGreaterThanOrEqualTo(String version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public PlatformRequest<T> withVersionLessThan(String version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public PlatformRequest<T> withVersionLessThanOrEqualTo(String version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public PlatformRequest<T> withVersionBetween(String startOfVersion, String endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }
    public PlatformRequest<T> withVersionStartingWith(String version){
       return withVersion(Operator.BEGIN_WITH, version);
    }
    public PlatformRequest<T> withVersionContaining(String version){
       return withVersion(Operator.CONTAIN, version);
    }

    public PlatformRequest<T> withVersionEndingWith(String version){
       return withVersion(Operator.END_WITH, version);
    }

    public PlatformRequest<T> withVersionIs(String version){
       return withVersion(Operator.EQUAL, version);
    }

    public PlatformRequest<T> withVersionSoundingLike(String version){
       return withVersion(Operator.SOUNDS_LIKE, version);
    }



    public PlatformRequest<T> filterByApiVersion(String... apiVersion){
      if (apiVersion == null || apiVersion.length == 0) {
        throw new IllegalArgumentException("filterByApiVersion parameter apiVersion cannot be empty");
      }
      return appendSearchCriteria(createApiVersionCriteria(Operator.EQUAL, (Object[])apiVersion));
    }

    public PlatformRequest<T> withApiVersion(Operator operator, Object... values){
       return appendSearchCriteria(createApiVersionCriteria(operator, values));
    }

    public PlatformRequest<T> withApiVersionIsUnknown(){
       return withApiVersion(Operator.IS_NULL);
    }

    public PlatformRequest<T> withApiVersionIsKnown(){
       return withApiVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createApiVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Platform.API_VERSION_PROPERTY, operator, values);
    }

    public PlatformRequest<T> withApiVersionGreaterThan(String apiVersion){
       return withApiVersion(Operator.GREATER_THAN, apiVersion);
    }

    public PlatformRequest<T> withApiVersionGreaterThanOrEqualTo(String apiVersion){
       return withApiVersion(Operator.GREATER_THAN_OR_EQUAL, apiVersion);
    }

    public PlatformRequest<T> withApiVersionLessThan(String apiVersion){
       return withApiVersion(Operator.LESS_THAN, apiVersion);
    }

    public PlatformRequest<T> withApiVersionLessThanOrEqualTo(String apiVersion){
       return withApiVersion(Operator.LESS_THAN_OR_EQUAL, apiVersion);
    }

    public PlatformRequest<T> withApiVersionBetween(String startOfApiVersion, String endOfApiVersion){
       return withApiVersion(Operator.BETWEEN, startOfApiVersion, endOfApiVersion);
    }
    public PlatformRequest<T> withApiVersionStartingWith(String apiVersion){
       return withApiVersion(Operator.BEGIN_WITH, apiVersion);
    }
    public PlatformRequest<T> withApiVersionContaining(String apiVersion){
       return withApiVersion(Operator.CONTAIN, apiVersion);
    }

    public PlatformRequest<T> withApiVersionEndingWith(String apiVersion){
       return withApiVersion(Operator.END_WITH, apiVersion);
    }

    public PlatformRequest<T> withApiVersionIs(String apiVersion){
       return withApiVersion(Operator.EQUAL, apiVersion);
    }

    public PlatformRequest<T> withApiVersionSoundingLike(String apiVersion){
       return withApiVersion(Operator.SOUNDS_LIKE, apiVersion);
    }



    public PlatformRequest<T> filterByMaintenanceMode(Boolean... maintenanceMode){
      if (maintenanceMode == null || maintenanceMode.length == 0) {
        throw new IllegalArgumentException("filterByMaintenanceMode parameter maintenanceMode cannot be empty");
      }
      return appendSearchCriteria(createMaintenanceModeCriteria(Operator.EQUAL, (Object[])maintenanceMode));
    }

    public PlatformRequest<T> withMaintenanceMode(Operator operator, Object... values){
       return appendSearchCriteria(createMaintenanceModeCriteria(operator, values));
    }

    public PlatformRequest<T> withMaintenanceModeIsUnknown(){
       return withMaintenanceMode(Operator.IS_NULL);
    }

    public PlatformRequest<T> withMaintenanceModeIsKnown(){
       return withMaintenanceMode(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createMaintenanceModeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Platform.MAINTENANCE_MODE_PROPERTY, operator, values);
    }

    public PlatformRequest<T> whichIsMaintenanceMode(){
       return withMaintenanceMode(Operator.EQUAL, true);
    }

    public PlatformRequest<T> whichIsNotMaintenanceMode(){
       return withMaintenanceMode(Operator.EQUAL, false);
    }


    public PlatformRequest<T> filterByCreateTime(LocalDateTime... createTime){
      if (createTime == null || createTime.length == 0) {
        throw new IllegalArgumentException("filterByCreateTime parameter createTime cannot be empty");
      }
      return appendSearchCriteria(createCreateTimeCriteria(Operator.EQUAL, (Object[])createTime));
    }

    public PlatformRequest<T> withCreateTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreateTimeCriteria(operator, values));
    }

    public PlatformRequest<T> withCreateTimeIsUnknown(){
       return withCreateTime(Operator.IS_NULL);
    }

    public PlatformRequest<T> withCreateTimeIsKnown(){
       return withCreateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Platform.CREATE_TIME_PROPERTY, operator, values);
    }

    public PlatformRequest<T> withCreateTimeGreaterThan(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public PlatformRequest<T> withCreateTimeGreaterThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN_OR_EQUAL, createTime);
    }

    public PlatformRequest<T> withCreateTimeLessThan(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public PlatformRequest<T> withCreateTimeLessThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN_OR_EQUAL, createTime);
    }

    public PlatformRequest<T> withCreateTimeBetween(LocalDateTime startOfCreateTime, LocalDateTime endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }
    public PlatformRequest<T> withCreateTimeBefore(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public PlatformRequest<T> withCreateTimeBefore(Date createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public PlatformRequest<T> withCreateTimeAfter(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public PlatformRequest<T> withCreateTimeAfter(Date createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public PlatformRequest<T> withCreateTimeBetween(Date startOfCreateTime, Date endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }




    public PlatformRequest<T> filterByUpdateTime(LocalDateTime... updateTime){
      if (updateTime == null || updateTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdateTime parameter updateTime cannot be empty");
      }
      return appendSearchCriteria(createUpdateTimeCriteria(Operator.EQUAL, (Object[])updateTime));
    }

    public PlatformRequest<T> withUpdateTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdateTimeCriteria(operator, values));
    }

    public PlatformRequest<T> withUpdateTimeIsUnknown(){
       return withUpdateTime(Operator.IS_NULL);
    }

    public PlatformRequest<T> withUpdateTimeIsKnown(){
       return withUpdateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Platform.UPDATE_TIME_PROPERTY, operator, values);
    }

    public PlatformRequest<T> withUpdateTimeGreaterThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public PlatformRequest<T> withUpdateTimeGreaterThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN_OR_EQUAL, updateTime);
    }

    public PlatformRequest<T> withUpdateTimeLessThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public PlatformRequest<T> withUpdateTimeLessThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN_OR_EQUAL, updateTime);
    }

    public PlatformRequest<T> withUpdateTimeBetween(LocalDateTime startOfUpdateTime, LocalDateTime endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }
    public PlatformRequest<T> withUpdateTimeBefore(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public PlatformRequest<T> withUpdateTimeBefore(Date updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public PlatformRequest<T> withUpdateTimeAfter(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public PlatformRequest<T> withUpdateTimeAfter(Date updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public PlatformRequest<T> withUpdateTimeBetween(Date startOfUpdateTime, Date endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }




    public PlatformRequest<T> count(){
        super.count();
        return this;
    }
    public PlatformRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }

    public PlatformRequest<T> groupById(){
       groupBy(Platform.ID_PROPERTY);
       return this;
    }

    public PlatformRequest<T> groupByIdAs(String retName){
       groupBy(retName, Platform.ID_PROPERTY);
       return this;
    }

    public PlatformRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, Platform.ID_PROPERTY, function);
       return this;
    }

    public PlatformRequest<T> groupByVersion(){
       groupBy(Platform.VERSION_PROPERTY);
       return this;
    }

    public PlatformRequest<T> groupByVersionAs(String retName){
       groupBy(retName, Platform.VERSION_PROPERTY);
       return this;
    }

    public PlatformRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, Platform.VERSION_PROPERTY, function);
       return this;
    }

    public PlatformRequest<T> groupByApiVersion(){
       groupBy(Platform.API_VERSION_PROPERTY);
       return this;
    }

    public PlatformRequest<T> groupByApiVersionAs(String retName){
       groupBy(retName, Platform.API_VERSION_PROPERTY);
       return this;
    }

    public PlatformRequest<T> groupByApiVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, Platform.API_VERSION_PROPERTY, function);
       return this;
    }

    public PlatformRequest<T> groupByMaintenanceMode(){
       groupBy(Platform.MAINTENANCE_MODE_PROPERTY);
       return this;
    }

    public PlatformRequest<T> groupByMaintenanceModeAs(String retName){
       groupBy(retName, Platform.MAINTENANCE_MODE_PROPERTY);
       return this;
    }

    public PlatformRequest<T> groupByMaintenanceModeWithFunction(String retName, AggrFunction function){
       groupBy(retName, Platform.MAINTENANCE_MODE_PROPERTY, function);
       return this;
    }

    public PlatformRequest<T> groupByCreateTime(){
       groupBy(Platform.CREATE_TIME_PROPERTY);
       return this;
    }

    public PlatformRequest<T> groupByCreateTimeAs(String retName){
       groupBy(retName, Platform.CREATE_TIME_PROPERTY);
       return this;
    }

    public PlatformRequest<T> groupByCreateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, Platform.CREATE_TIME_PROPERTY, function);
       return this;
    }

    public PlatformRequest<T> groupByUpdateTime(){
       groupBy(Platform.UPDATE_TIME_PROPERTY);
       return this;
    }

    public PlatformRequest<T> groupByUpdateTimeAs(String retName){
       groupBy(retName, Platform.UPDATE_TIME_PROPERTY);
       return this;
    }

    public PlatformRequest<T> groupByUpdateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, Platform.UPDATE_TIME_PROPERTY, function);
       return this;
    }



    public PlatformRequest<T> orderByIdAscending(){
       addOrderByAscending(Platform.ID_PROPERTY);
       return this;
    }

    public PlatformRequest<T> orderByIdDescending(){
       addOrderByDescending(Platform.ID_PROPERTY);
       return this;
    }

    public PlatformRequest<T> orderByVersionAscending(){
       addOrderByAscending(Platform.VERSION_PROPERTY);
       return this;
    }

    public PlatformRequest<T> orderByVersionDescending(){
       addOrderByDescending(Platform.VERSION_PROPERTY);
       return this;
    }
    public PlatformRequest<T> orderByVersionAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Platform.VERSION_PROPERTY);
       return this;
    }

    public PlatformRequest<T> orderByVersionDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Platform.VERSION_PROPERTY);
       return this;
    }
    public PlatformRequest<T> orderByApiVersionAscending(){
       addOrderByAscending(Platform.API_VERSION_PROPERTY);
       return this;
    }

    public PlatformRequest<T> orderByApiVersionDescending(){
       addOrderByDescending(Platform.API_VERSION_PROPERTY);
       return this;
    }
    public PlatformRequest<T> orderByApiVersionAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Platform.API_VERSION_PROPERTY);
       return this;
    }

    public PlatformRequest<T> orderByApiVersionDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Platform.API_VERSION_PROPERTY);
       return this;
    }
    public PlatformRequest<T> orderByMaintenanceModeAscending(){
       addOrderByAscending(Platform.MAINTENANCE_MODE_PROPERTY);
       return this;
    }

    public PlatformRequest<T> orderByMaintenanceModeDescending(){
       addOrderByDescending(Platform.MAINTENANCE_MODE_PROPERTY);
       return this;
    }

    public PlatformRequest<T> orderByCreateTimeAscending(){
       addOrderByAscending(Platform.CREATE_TIME_PROPERTY);
       return this;
    }

    public PlatformRequest<T> orderByCreateTimeDescending(){
       addOrderByDescending(Platform.CREATE_TIME_PROPERTY);
       return this;
    }

    public PlatformRequest<T> orderByUpdateTimeAscending(){
       addOrderByAscending(Platform.UPDATE_TIME_PROPERTY);
       return this;
    }

    public PlatformRequest<T> orderByUpdateTimeDescending(){
       addOrderByDescending(Platform.UPDATE_TIME_PROPERTY);
       return this;
    }





    /**
     * get topN records
     * @param topN  records number
     */
    public PlatformRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public PlatformRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public PlatformRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public PlatformRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public PlatformRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}