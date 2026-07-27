package com.doublechaintech.enterpriselogisticsservice.userrole;

import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDateTime;
import java.util.Date;

public class UserRoleRequest<T extends UserRole> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public UserRoleRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public UserRoleRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public UserRoleRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public UserRoleRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public UserRoleRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public UserRoleRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public UserRoleRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (UserRoleRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public UserRoleRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public UserRoleRequest<T> matchingAnyOf(UserRoleRequest userRole){
        super.internalMatchAny(userRole);
        return this;
    }

    public UserRoleRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public UserRoleRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public UserRoleRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public UserRoleRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectRoleName().selectDescription().selectIsSystem().selectCreatedAt().selectVersion();
    }

    public UserRoleRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public UserRoleRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectRoleName().selectDescription().selectIsSystem().selectCreatedAt().selectVersion();
    }

    public UserRoleRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectRoleName().selectDescription().selectIsSystem().selectCreatedAt().selectVersion();
    }


    public UserRoleRequest<T> selectId(){
       selectProperty(UserRole.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public UserRoleRequest<T> unselectId(){
       unselectProperty(UserRole.ID_PROPERTY);
       return this;
    }
    public UserRoleRequest<T> selectRoleName(){
       selectProperty(UserRole.ROLE_NAME_PROPERTY);
       return this;
    }

    /**
     * fill the roleName with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  roleName) to fetch roleName property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public UserRoleRequest<T> unselectRoleName(){
       unselectProperty(UserRole.ROLE_NAME_PROPERTY);
       return this;
    }
    public UserRoleRequest<T> selectDescription(){
       selectProperty(UserRole.DESCRIPTION_PROPERTY);
       return this;
    }

    /**
     * fill the description with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  description) to fetch description property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public UserRoleRequest<T> unselectDescription(){
       unselectProperty(UserRole.DESCRIPTION_PROPERTY);
       return this;
    }
    public UserRoleRequest<T> selectIsSystem(){
       selectProperty(UserRole.IS_SYSTEM_PROPERTY);
       return this;
    }

    /**
     * fill the isSystem with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  isSystem) to fetch isSystem property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public UserRoleRequest<T> unselectIsSystem(){
       unselectProperty(UserRole.IS_SYSTEM_PROPERTY);
       return this;
    }
    public UserRoleRequest<T> selectCreatedAt(){
       selectProperty(UserRole.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public UserRoleRequest<T> unselectCreatedAt(){
       unselectProperty(UserRole.CREATED_AT_PROPERTY);
       return this;
    }
    public UserRoleRequest<T> selectVersion(){
       selectProperty(UserRole.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public UserRoleRequest<T> unselectVersion(){
       unselectProperty(UserRole.VERSION_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(UserRole.ID_PROPERTY, operator, values);
    }

    public UserRoleRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public UserRoleRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public UserRoleRequest<T> filterByRoleName(String... roleName){
      if (roleName == null || roleName.length == 0) {
        throw new IllegalArgumentException("filterByRoleName parameter roleName cannot be empty");
      }
      return appendSearchCriteria(createRoleNameCriteria(Operator.EQUAL, (Object[])roleName));
    }

    public UserRoleRequest<T> withRoleName(Operator operator, Object... values){
       return appendSearchCriteria(createRoleNameCriteria(operator, values));
    }

    public UserRoleRequest<T> withRoleNameIsUnknown(){
       return withRoleName(Operator.IS_NULL);
    }

    public UserRoleRequest<T> withRoleNameIsKnown(){
       return withRoleName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createRoleNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(UserRole.ROLE_NAME_PROPERTY, operator, values);
    }

    public UserRoleRequest<T> withRoleNameGreaterThan(String roleName){
       return withRoleName(Operator.GREATER_THAN, roleName);
    }

    public UserRoleRequest<T> withRoleNameGreaterThanOrEqualTo(String roleName){
       return withRoleName(Operator.GREATER_THAN_OR_EQUAL, roleName);
    }

    public UserRoleRequest<T> withRoleNameLessThan(String roleName){
       return withRoleName(Operator.LESS_THAN, roleName);
    }

    public UserRoleRequest<T> withRoleNameLessThanOrEqualTo(String roleName){
       return withRoleName(Operator.LESS_THAN_OR_EQUAL, roleName);
    }

    public UserRoleRequest<T> withRoleNameBetween(String startOfRoleName, String endOfRoleName){
       return withRoleName(Operator.BETWEEN, startOfRoleName, endOfRoleName);
    }
    public UserRoleRequest<T> withRoleNameStartingWith(String roleName){
       return withRoleName(Operator.BEGIN_WITH, roleName);
    }
    public UserRoleRequest<T> withRoleNameContaining(String roleName){
       return withRoleName(Operator.CONTAIN, roleName);
    }

    public UserRoleRequest<T> withRoleNameEndingWith(String roleName){
       return withRoleName(Operator.END_WITH, roleName);
    }

    public UserRoleRequest<T> withRoleNameIs(String roleName){
       return withRoleName(Operator.EQUAL, roleName);
    }

    public UserRoleRequest<T> withRoleNameSoundingLike(String roleName){
       return withRoleName(Operator.SOUNDS_LIKE, roleName);
    }



    public UserRoleRequest<T> filterByDescription(String... description){
      if (description == null || description.length == 0) {
        throw new IllegalArgumentException("filterByDescription parameter description cannot be empty");
      }
      return appendSearchCriteria(createDescriptionCriteria(Operator.EQUAL, (Object[])description));
    }

    public UserRoleRequest<T> withDescription(Operator operator, Object... values){
       return appendSearchCriteria(createDescriptionCriteria(operator, values));
    }

    public UserRoleRequest<T> withDescriptionIsUnknown(){
       return withDescription(Operator.IS_NULL);
    }

    public UserRoleRequest<T> withDescriptionIsKnown(){
       return withDescription(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDescriptionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(UserRole.DESCRIPTION_PROPERTY, operator, values);
    }

    public UserRoleRequest<T> withDescriptionGreaterThan(String description){
       return withDescription(Operator.GREATER_THAN, description);
    }

    public UserRoleRequest<T> withDescriptionGreaterThanOrEqualTo(String description){
       return withDescription(Operator.GREATER_THAN_OR_EQUAL, description);
    }

    public UserRoleRequest<T> withDescriptionLessThan(String description){
       return withDescription(Operator.LESS_THAN, description);
    }

    public UserRoleRequest<T> withDescriptionLessThanOrEqualTo(String description){
       return withDescription(Operator.LESS_THAN_OR_EQUAL, description);
    }

    public UserRoleRequest<T> withDescriptionBetween(String startOfDescription, String endOfDescription){
       return withDescription(Operator.BETWEEN, startOfDescription, endOfDescription);
    }
    public UserRoleRequest<T> withDescriptionStartingWith(String description){
       return withDescription(Operator.BEGIN_WITH, description);
    }
    public UserRoleRequest<T> withDescriptionContaining(String description){
       return withDescription(Operator.CONTAIN, description);
    }

    public UserRoleRequest<T> withDescriptionEndingWith(String description){
       return withDescription(Operator.END_WITH, description);
    }

    public UserRoleRequest<T> withDescriptionIs(String description){
       return withDescription(Operator.EQUAL, description);
    }

    public UserRoleRequest<T> withDescriptionSoundingLike(String description){
       return withDescription(Operator.SOUNDS_LIKE, description);
    }



    public UserRoleRequest<T> filterByIsSystem(String... isSystem){
      if (isSystem == null || isSystem.length == 0) {
        throw new IllegalArgumentException("filterByIsSystem parameter isSystem cannot be empty");
      }
      return appendSearchCriteria(createIsSystemCriteria(Operator.EQUAL, (Object[])isSystem));
    }

    public UserRoleRequest<T> withIsSystem(Operator operator, Object... values){
       return appendSearchCriteria(createIsSystemCriteria(operator, values));
    }

    public UserRoleRequest<T> withIsSystemIsUnknown(){
       return withIsSystem(Operator.IS_NULL);
    }

    public UserRoleRequest<T> withIsSystemIsKnown(){
       return withIsSystem(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createIsSystemCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(UserRole.IS_SYSTEM_PROPERTY, operator, values);
    }

    public UserRoleRequest<T> withIsSystemGreaterThan(String isSystem){
       return withIsSystem(Operator.GREATER_THAN, isSystem);
    }

    public UserRoleRequest<T> withIsSystemGreaterThanOrEqualTo(String isSystem){
       return withIsSystem(Operator.GREATER_THAN_OR_EQUAL, isSystem);
    }

    public UserRoleRequest<T> withIsSystemLessThan(String isSystem){
       return withIsSystem(Operator.LESS_THAN, isSystem);
    }

    public UserRoleRequest<T> withIsSystemLessThanOrEqualTo(String isSystem){
       return withIsSystem(Operator.LESS_THAN_OR_EQUAL, isSystem);
    }

    public UserRoleRequest<T> withIsSystemBetween(String startOfIsSystem, String endOfIsSystem){
       return withIsSystem(Operator.BETWEEN, startOfIsSystem, endOfIsSystem);
    }
    public UserRoleRequest<T> withIsSystemStartingWith(String isSystem){
       return withIsSystem(Operator.BEGIN_WITH, isSystem);
    }
    public UserRoleRequest<T> withIsSystemContaining(String isSystem){
       return withIsSystem(Operator.CONTAIN, isSystem);
    }

    public UserRoleRequest<T> withIsSystemEndingWith(String isSystem){
       return withIsSystem(Operator.END_WITH, isSystem);
    }

    public UserRoleRequest<T> withIsSystemIs(String isSystem){
       return withIsSystem(Operator.EQUAL, isSystem);
    }

    public UserRoleRequest<T> withIsSystemSoundingLike(String isSystem){
       return withIsSystem(Operator.SOUNDS_LIKE, isSystem);
    }



    public UserRoleRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public UserRoleRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public UserRoleRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public UserRoleRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(UserRole.CREATED_AT_PROPERTY, operator, values);
    }

    public UserRoleRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public UserRoleRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public UserRoleRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public UserRoleRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public UserRoleRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public UserRoleRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public UserRoleRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public UserRoleRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public UserRoleRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public UserRoleRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }




    public UserRoleRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public UserRoleRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public UserRoleRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public UserRoleRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(UserRole.VERSION_PROPERTY, operator, values);
    }

    public UserRoleRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public UserRoleRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public UserRoleRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public UserRoleRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public UserRoleRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public UserRoleRequest<T> count(){
        super.count();
        return this;
    }
    public UserRoleRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }

    public UserRoleRequest<T> groupById(){
       groupBy(UserRole.ID_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> groupByIdAs(String retName){
       groupBy(retName, UserRole.ID_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, UserRole.ID_PROPERTY, function);
       return this;
    }

    public UserRoleRequest<T> groupByRoleName(){
       groupBy(UserRole.ROLE_NAME_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> groupByRoleNameAs(String retName){
       groupBy(retName, UserRole.ROLE_NAME_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> groupByRoleNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, UserRole.ROLE_NAME_PROPERTY, function);
       return this;
    }

    public UserRoleRequest<T> groupByDescription(){
       groupBy(UserRole.DESCRIPTION_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> groupByDescriptionAs(String retName){
       groupBy(retName, UserRole.DESCRIPTION_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> groupByDescriptionWithFunction(String retName, AggrFunction function){
       groupBy(retName, UserRole.DESCRIPTION_PROPERTY, function);
       return this;
    }

    public UserRoleRequest<T> groupByIsSystem(){
       groupBy(UserRole.IS_SYSTEM_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> groupByIsSystemAs(String retName){
       groupBy(retName, UserRole.IS_SYSTEM_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> groupByIsSystemWithFunction(String retName, AggrFunction function){
       groupBy(retName, UserRole.IS_SYSTEM_PROPERTY, function);
       return this;
    }

    public UserRoleRequest<T> groupByCreatedAt(){
       groupBy(UserRole.CREATED_AT_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, UserRole.CREATED_AT_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, UserRole.CREATED_AT_PROPERTY, function);
       return this;
    }

    public UserRoleRequest<T> groupByVersion(){
       groupBy(UserRole.VERSION_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> groupByVersionAs(String retName){
       groupBy(retName, UserRole.VERSION_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, UserRole.VERSION_PROPERTY, function);
       return this;
    }



    public UserRoleRequest<T> orderByIdAscending(){
       addOrderByAscending(UserRole.ID_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> orderByIdDescending(){
       addOrderByDescending(UserRole.ID_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> orderByRoleNameAscending(){
       addOrderByAscending(UserRole.ROLE_NAME_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> orderByRoleNameDescending(){
       addOrderByDescending(UserRole.ROLE_NAME_PROPERTY);
       return this;
    }
    public UserRoleRequest<T> orderByRoleNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(UserRole.ROLE_NAME_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> orderByRoleNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(UserRole.ROLE_NAME_PROPERTY);
       return this;
    }
    public UserRoleRequest<T> orderByDescriptionAscending(){
       addOrderByAscending(UserRole.DESCRIPTION_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> orderByDescriptionDescending(){
       addOrderByDescending(UserRole.DESCRIPTION_PROPERTY);
       return this;
    }
    public UserRoleRequest<T> orderByDescriptionAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(UserRole.DESCRIPTION_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> orderByDescriptionDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(UserRole.DESCRIPTION_PROPERTY);
       return this;
    }
    public UserRoleRequest<T> orderByIsSystemAscending(){
       addOrderByAscending(UserRole.IS_SYSTEM_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> orderByIsSystemDescending(){
       addOrderByDescending(UserRole.IS_SYSTEM_PROPERTY);
       return this;
    }
    public UserRoleRequest<T> orderByIsSystemAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(UserRole.IS_SYSTEM_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> orderByIsSystemDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(UserRole.IS_SYSTEM_PROPERTY);
       return this;
    }
    public UserRoleRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(UserRole.CREATED_AT_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(UserRole.CREATED_AT_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> orderByVersionAscending(){
       addOrderByAscending(UserRole.VERSION_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> orderByVersionDescending(){
       addOrderByDescending(UserRole.VERSION_PROPERTY);
       return this;
    }





    /**
     * get topN records
     * @param topN  records number
     */
    public UserRoleRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public UserRoleRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public UserRoleRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public UserRoleRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public UserRoleRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}