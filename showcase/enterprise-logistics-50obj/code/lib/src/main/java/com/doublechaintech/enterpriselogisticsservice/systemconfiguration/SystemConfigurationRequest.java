package com.doublechaintech.enterpriselogisticsservice.systemconfiguration;

import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDateTime;
import java.util.Date;

public class SystemConfigurationRequest<T extends SystemConfiguration> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public SystemConfigurationRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public SystemConfigurationRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public SystemConfigurationRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public SystemConfigurationRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public SystemConfigurationRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public SystemConfigurationRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public SystemConfigurationRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (SystemConfigurationRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public SystemConfigurationRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public SystemConfigurationRequest<T> matchingAnyOf(SystemConfigurationRequest systemConfiguration){
        super.internalMatchAny(systemConfiguration);
        return this;
    }

    public SystemConfigurationRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public SystemConfigurationRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public SystemConfigurationRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public SystemConfigurationRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectConfigKey().selectConfigValue().selectDescription().selectUpdatedAt().selectVersion();
    }

    public SystemConfigurationRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public SystemConfigurationRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectConfigKey().selectConfigValue().selectDescription().selectUpdatedAt().selectVersion();
    }

    public SystemConfigurationRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectConfigKey().selectConfigValue().selectDescription().selectUpdatedAt().selectVersion();
    }


    public SystemConfigurationRequest<T> selectId(){
       selectProperty(SystemConfiguration.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SystemConfigurationRequest<T> unselectId(){
       unselectProperty(SystemConfiguration.ID_PROPERTY);
       return this;
    }
    public SystemConfigurationRequest<T> selectConfigKey(){
       selectProperty(SystemConfiguration.CONFIG_KEY_PROPERTY);
       return this;
    }

    /**
     * fill the configKey with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  configKey) to fetch configKey property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SystemConfigurationRequest<T> unselectConfigKey(){
       unselectProperty(SystemConfiguration.CONFIG_KEY_PROPERTY);
       return this;
    }
    public SystemConfigurationRequest<T> selectConfigValue(){
       selectProperty(SystemConfiguration.CONFIG_VALUE_PROPERTY);
       return this;
    }

    /**
     * fill the configValue with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  configValue) to fetch configValue property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SystemConfigurationRequest<T> unselectConfigValue(){
       unselectProperty(SystemConfiguration.CONFIG_VALUE_PROPERTY);
       return this;
    }
    public SystemConfigurationRequest<T> selectDescription(){
       selectProperty(SystemConfiguration.DESCRIPTION_PROPERTY);
       return this;
    }

    /**
     * fill the description with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  description) to fetch description property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SystemConfigurationRequest<T> unselectDescription(){
       unselectProperty(SystemConfiguration.DESCRIPTION_PROPERTY);
       return this;
    }
    public SystemConfigurationRequest<T> selectUpdatedAt(){
       selectProperty(SystemConfiguration.UPDATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the updatedAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedAt) to fetch updatedAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SystemConfigurationRequest<T> unselectUpdatedAt(){
       unselectProperty(SystemConfiguration.UPDATED_AT_PROPERTY);
       return this;
    }
    public SystemConfigurationRequest<T> selectVersion(){
       selectProperty(SystemConfiguration.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SystemConfigurationRequest<T> unselectVersion(){
       unselectProperty(SystemConfiguration.VERSION_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SystemConfiguration.ID_PROPERTY, operator, values);
    }

    public SystemConfigurationRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public SystemConfigurationRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public SystemConfigurationRequest<T> filterByConfigKey(String... configKey){
      if (configKey == null || configKey.length == 0) {
        throw new IllegalArgumentException("filterByConfigKey parameter configKey cannot be empty");
      }
      return appendSearchCriteria(createConfigKeyCriteria(Operator.EQUAL, (Object[])configKey));
    }

    public SystemConfigurationRequest<T> withConfigKey(Operator operator, Object... values){
       return appendSearchCriteria(createConfigKeyCriteria(operator, values));
    }

    public SystemConfigurationRequest<T> withConfigKeyIsUnknown(){
       return withConfigKey(Operator.IS_NULL);
    }

    public SystemConfigurationRequest<T> withConfigKeyIsKnown(){
       return withConfigKey(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createConfigKeyCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SystemConfiguration.CONFIG_KEY_PROPERTY, operator, values);
    }

    public SystemConfigurationRequest<T> withConfigKeyGreaterThan(String configKey){
       return withConfigKey(Operator.GREATER_THAN, configKey);
    }

    public SystemConfigurationRequest<T> withConfigKeyGreaterThanOrEqualTo(String configKey){
       return withConfigKey(Operator.GREATER_THAN_OR_EQUAL, configKey);
    }

    public SystemConfigurationRequest<T> withConfigKeyLessThan(String configKey){
       return withConfigKey(Operator.LESS_THAN, configKey);
    }

    public SystemConfigurationRequest<T> withConfigKeyLessThanOrEqualTo(String configKey){
       return withConfigKey(Operator.LESS_THAN_OR_EQUAL, configKey);
    }

    public SystemConfigurationRequest<T> withConfigKeyBetween(String startOfConfigKey, String endOfConfigKey){
       return withConfigKey(Operator.BETWEEN, startOfConfigKey, endOfConfigKey);
    }
    public SystemConfigurationRequest<T> withConfigKeyStartingWith(String configKey){
       return withConfigKey(Operator.BEGIN_WITH, configKey);
    }
    public SystemConfigurationRequest<T> withConfigKeyContaining(String configKey){
       return withConfigKey(Operator.CONTAIN, configKey);
    }

    public SystemConfigurationRequest<T> withConfigKeyEndingWith(String configKey){
       return withConfigKey(Operator.END_WITH, configKey);
    }

    public SystemConfigurationRequest<T> withConfigKeyIs(String configKey){
       return withConfigKey(Operator.EQUAL, configKey);
    }

    public SystemConfigurationRequest<T> withConfigKeySoundingLike(String configKey){
       return withConfigKey(Operator.SOUNDS_LIKE, configKey);
    }



    public SystemConfigurationRequest<T> filterByConfigValue(String... configValue){
      if (configValue == null || configValue.length == 0) {
        throw new IllegalArgumentException("filterByConfigValue parameter configValue cannot be empty");
      }
      return appendSearchCriteria(createConfigValueCriteria(Operator.EQUAL, (Object[])configValue));
    }

    public SystemConfigurationRequest<T> withConfigValue(Operator operator, Object... values){
       return appendSearchCriteria(createConfigValueCriteria(operator, values));
    }

    public SystemConfigurationRequest<T> withConfigValueIsUnknown(){
       return withConfigValue(Operator.IS_NULL);
    }

    public SystemConfigurationRequest<T> withConfigValueIsKnown(){
       return withConfigValue(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createConfigValueCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SystemConfiguration.CONFIG_VALUE_PROPERTY, operator, values);
    }

    public SystemConfigurationRequest<T> withConfigValueGreaterThan(String configValue){
       return withConfigValue(Operator.GREATER_THAN, configValue);
    }

    public SystemConfigurationRequest<T> withConfigValueGreaterThanOrEqualTo(String configValue){
       return withConfigValue(Operator.GREATER_THAN_OR_EQUAL, configValue);
    }

    public SystemConfigurationRequest<T> withConfigValueLessThan(String configValue){
       return withConfigValue(Operator.LESS_THAN, configValue);
    }

    public SystemConfigurationRequest<T> withConfigValueLessThanOrEqualTo(String configValue){
       return withConfigValue(Operator.LESS_THAN_OR_EQUAL, configValue);
    }

    public SystemConfigurationRequest<T> withConfigValueBetween(String startOfConfigValue, String endOfConfigValue){
       return withConfigValue(Operator.BETWEEN, startOfConfigValue, endOfConfigValue);
    }
    public SystemConfigurationRequest<T> withConfigValueStartingWith(String configValue){
       return withConfigValue(Operator.BEGIN_WITH, configValue);
    }
    public SystemConfigurationRequest<T> withConfigValueContaining(String configValue){
       return withConfigValue(Operator.CONTAIN, configValue);
    }

    public SystemConfigurationRequest<T> withConfigValueEndingWith(String configValue){
       return withConfigValue(Operator.END_WITH, configValue);
    }

    public SystemConfigurationRequest<T> withConfigValueIs(String configValue){
       return withConfigValue(Operator.EQUAL, configValue);
    }

    public SystemConfigurationRequest<T> withConfigValueSoundingLike(String configValue){
       return withConfigValue(Operator.SOUNDS_LIKE, configValue);
    }



    public SystemConfigurationRequest<T> filterByDescription(String... description){
      if (description == null || description.length == 0) {
        throw new IllegalArgumentException("filterByDescription parameter description cannot be empty");
      }
      return appendSearchCriteria(createDescriptionCriteria(Operator.EQUAL, (Object[])description));
    }

    public SystemConfigurationRequest<T> withDescription(Operator operator, Object... values){
       return appendSearchCriteria(createDescriptionCriteria(operator, values));
    }

    public SystemConfigurationRequest<T> withDescriptionIsUnknown(){
       return withDescription(Operator.IS_NULL);
    }

    public SystemConfigurationRequest<T> withDescriptionIsKnown(){
       return withDescription(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDescriptionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SystemConfiguration.DESCRIPTION_PROPERTY, operator, values);
    }

    public SystemConfigurationRequest<T> withDescriptionGreaterThan(String description){
       return withDescription(Operator.GREATER_THAN, description);
    }

    public SystemConfigurationRequest<T> withDescriptionGreaterThanOrEqualTo(String description){
       return withDescription(Operator.GREATER_THAN_OR_EQUAL, description);
    }

    public SystemConfigurationRequest<T> withDescriptionLessThan(String description){
       return withDescription(Operator.LESS_THAN, description);
    }

    public SystemConfigurationRequest<T> withDescriptionLessThanOrEqualTo(String description){
       return withDescription(Operator.LESS_THAN_OR_EQUAL, description);
    }

    public SystemConfigurationRequest<T> withDescriptionBetween(String startOfDescription, String endOfDescription){
       return withDescription(Operator.BETWEEN, startOfDescription, endOfDescription);
    }
    public SystemConfigurationRequest<T> withDescriptionStartingWith(String description){
       return withDescription(Operator.BEGIN_WITH, description);
    }
    public SystemConfigurationRequest<T> withDescriptionContaining(String description){
       return withDescription(Operator.CONTAIN, description);
    }

    public SystemConfigurationRequest<T> withDescriptionEndingWith(String description){
       return withDescription(Operator.END_WITH, description);
    }

    public SystemConfigurationRequest<T> withDescriptionIs(String description){
       return withDescription(Operator.EQUAL, description);
    }

    public SystemConfigurationRequest<T> withDescriptionSoundingLike(String description){
       return withDescription(Operator.SOUNDS_LIKE, description);
    }



    public SystemConfigurationRequest<T> filterByUpdatedAt(LocalDateTime... updatedAt){
      if (updatedAt == null || updatedAt.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedAt parameter updatedAt cannot be empty");
      }
      return appendSearchCriteria(createUpdatedAtCriteria(Operator.EQUAL, (Object[])updatedAt));
    }

    public SystemConfigurationRequest<T> withUpdatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedAtCriteria(operator, values));
    }

    public SystemConfigurationRequest<T> withUpdatedAtIsUnknown(){
       return withUpdatedAt(Operator.IS_NULL);
    }

    public SystemConfigurationRequest<T> withUpdatedAtIsKnown(){
       return withUpdatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SystemConfiguration.UPDATED_AT_PROPERTY, operator, values);
    }

    public SystemConfigurationRequest<T> withUpdatedAtGreaterThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public SystemConfigurationRequest<T> withUpdatedAtGreaterThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN_OR_EQUAL, updatedAt);
    }

    public SystemConfigurationRequest<T> withUpdatedAtLessThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public SystemConfigurationRequest<T> withUpdatedAtLessThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN_OR_EQUAL, updatedAt);
    }

    public SystemConfigurationRequest<T> withUpdatedAtBetween(LocalDateTime startOfUpdatedAt, LocalDateTime endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }
    public SystemConfigurationRequest<T> withUpdatedAtBefore(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public SystemConfigurationRequest<T> withUpdatedAtBefore(Date updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public SystemConfigurationRequest<T> withUpdatedAtAfter(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public SystemConfigurationRequest<T> withUpdatedAtAfter(Date updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public SystemConfigurationRequest<T> withUpdatedAtBetween(Date startOfUpdatedAt, Date endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }




    public SystemConfigurationRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public SystemConfigurationRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public SystemConfigurationRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public SystemConfigurationRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SystemConfiguration.VERSION_PROPERTY, operator, values);
    }

    public SystemConfigurationRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public SystemConfigurationRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public SystemConfigurationRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public SystemConfigurationRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public SystemConfigurationRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public SystemConfigurationRequest<T> count(){
        super.count();
        return this;
    }
    public SystemConfigurationRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }

    public SystemConfigurationRequest<T> groupById(){
       groupBy(SystemConfiguration.ID_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> groupByIdAs(String retName){
       groupBy(retName, SystemConfiguration.ID_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, SystemConfiguration.ID_PROPERTY, function);
       return this;
    }

    public SystemConfigurationRequest<T> groupByConfigKey(){
       groupBy(SystemConfiguration.CONFIG_KEY_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> groupByConfigKeyAs(String retName){
       groupBy(retName, SystemConfiguration.CONFIG_KEY_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> groupByConfigKeyWithFunction(String retName, AggrFunction function){
       groupBy(retName, SystemConfiguration.CONFIG_KEY_PROPERTY, function);
       return this;
    }

    public SystemConfigurationRequest<T> groupByConfigValue(){
       groupBy(SystemConfiguration.CONFIG_VALUE_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> groupByConfigValueAs(String retName){
       groupBy(retName, SystemConfiguration.CONFIG_VALUE_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> groupByConfigValueWithFunction(String retName, AggrFunction function){
       groupBy(retName, SystemConfiguration.CONFIG_VALUE_PROPERTY, function);
       return this;
    }

    public SystemConfigurationRequest<T> groupByDescription(){
       groupBy(SystemConfiguration.DESCRIPTION_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> groupByDescriptionAs(String retName){
       groupBy(retName, SystemConfiguration.DESCRIPTION_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> groupByDescriptionWithFunction(String retName, AggrFunction function){
       groupBy(retName, SystemConfiguration.DESCRIPTION_PROPERTY, function);
       return this;
    }

    public SystemConfigurationRequest<T> groupByUpdatedAt(){
       groupBy(SystemConfiguration.UPDATED_AT_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> groupByUpdatedAtAs(String retName){
       groupBy(retName, SystemConfiguration.UPDATED_AT_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> groupByUpdatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, SystemConfiguration.UPDATED_AT_PROPERTY, function);
       return this;
    }

    public SystemConfigurationRequest<T> groupByVersion(){
       groupBy(SystemConfiguration.VERSION_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> groupByVersionAs(String retName){
       groupBy(retName, SystemConfiguration.VERSION_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, SystemConfiguration.VERSION_PROPERTY, function);
       return this;
    }



    public SystemConfigurationRequest<T> orderByIdAscending(){
       addOrderByAscending(SystemConfiguration.ID_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> orderByIdDescending(){
       addOrderByDescending(SystemConfiguration.ID_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> orderByConfigKeyAscending(){
       addOrderByAscending(SystemConfiguration.CONFIG_KEY_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> orderByConfigKeyDescending(){
       addOrderByDescending(SystemConfiguration.CONFIG_KEY_PROPERTY);
       return this;
    }
    public SystemConfigurationRequest<T> orderByConfigKeyAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SystemConfiguration.CONFIG_KEY_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> orderByConfigKeyDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SystemConfiguration.CONFIG_KEY_PROPERTY);
       return this;
    }
    public SystemConfigurationRequest<T> orderByConfigValueAscending(){
       addOrderByAscending(SystemConfiguration.CONFIG_VALUE_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> orderByConfigValueDescending(){
       addOrderByDescending(SystemConfiguration.CONFIG_VALUE_PROPERTY);
       return this;
    }
    public SystemConfigurationRequest<T> orderByConfigValueAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SystemConfiguration.CONFIG_VALUE_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> orderByConfigValueDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SystemConfiguration.CONFIG_VALUE_PROPERTY);
       return this;
    }
    public SystemConfigurationRequest<T> orderByDescriptionAscending(){
       addOrderByAscending(SystemConfiguration.DESCRIPTION_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> orderByDescriptionDescending(){
       addOrderByDescending(SystemConfiguration.DESCRIPTION_PROPERTY);
       return this;
    }
    public SystemConfigurationRequest<T> orderByDescriptionAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SystemConfiguration.DESCRIPTION_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> orderByDescriptionDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SystemConfiguration.DESCRIPTION_PROPERTY);
       return this;
    }
    public SystemConfigurationRequest<T> orderByUpdatedAtAscending(){
       addOrderByAscending(SystemConfiguration.UPDATED_AT_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> orderByUpdatedAtDescending(){
       addOrderByDescending(SystemConfiguration.UPDATED_AT_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> orderByVersionAscending(){
       addOrderByAscending(SystemConfiguration.VERSION_PROPERTY);
       return this;
    }

    public SystemConfigurationRequest<T> orderByVersionDescending(){
       addOrderByDescending(SystemConfiguration.VERSION_PROPERTY);
       return this;
    }





    /**
     * get topN records
     * @param topN  records number
     */
    public SystemConfigurationRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public SystemConfigurationRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public SystemConfigurationRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public SystemConfigurationRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public SystemConfigurationRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}