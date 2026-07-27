package com.doublechaintech.enterpriselogisticsservice.userrole;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission;
import com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermissionRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;

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
        return selectId().selectName().selectCode().selectDescription().selectVersion();
    }

    public UserRoleRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public UserRoleRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectCode().selectDescription().selectVersion();
    }

    public UserRoleRequest<T> selectChildren(){
        super.selectAny();
        selectAccessPermissionList();
        return selectId().selectName().selectCode().selectDescription().selectVersion();
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
    public UserRoleRequest<T> selectName(){
       selectProperty(UserRole.NAME_PROPERTY);
       return this;
    }

    /**
     * fill the name with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  name) to fetch name property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public UserRoleRequest<T> unselectName(){
       unselectProperty(UserRole.NAME_PROPERTY);
       return this;
    }
    public UserRoleRequest<T> selectCode(){
       selectProperty(UserRole.CODE_PROPERTY);
       return this;
    }

    /**
     * fill the code with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  code) to fetch code property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public UserRoleRequest<T> unselectCode(){
       unselectProperty(UserRole.CODE_PROPERTY);
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
    public UserRoleRequest<T> selectAccessPermissionList(){
       return selectAccessPermissionListWith(Q.accessPermissions().selectSelf());
    }

    public UserRoleRequest<T> selectAccessPermissionListWith(AccessPermissionRequest accessPermissionList){
       enhanceRelation(UserRole.ACCESS_PERMISSION_LIST_PROPERTY, accessPermissionList);
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



    public UserRoleRequest<T> filterByName(String... name){
      if (name == null || name.length == 0) {
        throw new IllegalArgumentException("filterByName parameter name cannot be empty");
      }
      return appendSearchCriteria(createNameCriteria(Operator.EQUAL, (Object[])name));
    }

    public UserRoleRequest<T> withName(Operator operator, Object... values){
       return appendSearchCriteria(createNameCriteria(operator, values));
    }

    public UserRoleRequest<T> withNameIsUnknown(){
       return withName(Operator.IS_NULL);
    }

    public UserRoleRequest<T> withNameIsKnown(){
       return withName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(UserRole.NAME_PROPERTY, operator, values);
    }

    public UserRoleRequest<T> withNameGreaterThan(String name){
       return withName(Operator.GREATER_THAN, name);
    }

    public UserRoleRequest<T> withNameGreaterThanOrEqualTo(String name){
       return withName(Operator.GREATER_THAN_OR_EQUAL, name);
    }

    public UserRoleRequest<T> withNameLessThan(String name){
       return withName(Operator.LESS_THAN, name);
    }

    public UserRoleRequest<T> withNameLessThanOrEqualTo(String name){
       return withName(Operator.LESS_THAN_OR_EQUAL, name);
    }

    public UserRoleRequest<T> withNameBetween(String startOfName, String endOfName){
       return withName(Operator.BETWEEN, startOfName, endOfName);
    }
    public UserRoleRequest<T> withNameStartingWith(String name){
       return withName(Operator.BEGIN_WITH, name);
    }
    public UserRoleRequest<T> withNameContaining(String name){
       return withName(Operator.CONTAIN, name);
    }

    public UserRoleRequest<T> withNameEndingWith(String name){
       return withName(Operator.END_WITH, name);
    }

    public UserRoleRequest<T> withNameIs(String name){
       return withName(Operator.EQUAL, name);
    }

    public UserRoleRequest<T> withNameSoundingLike(String name){
       return withName(Operator.SOUNDS_LIKE, name);
    }



    public UserRoleRequest<T> filterByCode(String... code){
      if (code == null || code.length == 0) {
        throw new IllegalArgumentException("filterByCode parameter code cannot be empty");
      }
      return appendSearchCriteria(createCodeCriteria(Operator.EQUAL, (Object[])code));
    }

    public UserRoleRequest<T> withCode(Operator operator, Object... values){
       return appendSearchCriteria(createCodeCriteria(operator, values));
    }

    public UserRoleRequest<T> withCodeIsUnknown(){
       return withCode(Operator.IS_NULL);
    }

    public UserRoleRequest<T> withCodeIsKnown(){
       return withCode(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCodeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(UserRole.CODE_PROPERTY, operator, values);
    }

    public UserRoleRequest<T> withCodeGreaterThan(String code){
       return withCode(Operator.GREATER_THAN, code);
    }

    public UserRoleRequest<T> withCodeGreaterThanOrEqualTo(String code){
       return withCode(Operator.GREATER_THAN_OR_EQUAL, code);
    }

    public UserRoleRequest<T> withCodeLessThan(String code){
       return withCode(Operator.LESS_THAN, code);
    }

    public UserRoleRequest<T> withCodeLessThanOrEqualTo(String code){
       return withCode(Operator.LESS_THAN_OR_EQUAL, code);
    }

    public UserRoleRequest<T> withCodeBetween(String startOfCode, String endOfCode){
       return withCode(Operator.BETWEEN, startOfCode, endOfCode);
    }
    public UserRoleRequest<T> withCodeStartingWith(String code){
       return withCode(Operator.BEGIN_WITH, code);
    }
    public UserRoleRequest<T> withCodeContaining(String code){
       return withCode(Operator.CONTAIN, code);
    }

    public UserRoleRequest<T> withCodeEndingWith(String code){
       return withCode(Operator.END_WITH, code);
    }

    public UserRoleRequest<T> withCodeIs(String code){
       return withCode(Operator.EQUAL, code);
    }

    public UserRoleRequest<T> withCodeSoundingLike(String code){
       return withCode(Operator.SOUNDS_LIKE, code);
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

    public UserRoleRequest<T> withAccessPermissionListMatching(AccessPermissionRequest accessPermissionRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(UserRole.ID_PROPERTY, accessPermissionRequest, AccessPermission.ROLE_PROPERTY));
    }

    public UserRoleRequest<T> withoutAccessPermissionListMatching(AccessPermissionRequest accessPermissionRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(UserRole.ID_PROPERTY, accessPermissionRequest, AccessPermission.ROLE_PROPERTY)));
    }

    public UserRoleRequest<T> haveAccessPermissions(){
        return withAccessPermissionListMatching(Q.accessPermissions().unlimited());
    }

    public UserRoleRequest<T> haveNoAccessPermissions(){
        return withoutAccessPermissionListMatching(Q.accessPermissions().unlimited());
    }

    public UserRoleRequest<T> count(){
        super.count();
        return this;
    }
    public UserRoleRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public UserRoleRequest<T> groupByAccessPermissionsWithDetails(AccessPermissionRequest subRequest){
       aggregate(UserRole.ACCESS_PERMISSION_LIST_PROPERTY, subRequest);
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

    public UserRoleRequest<T> groupByName(){
       groupBy(UserRole.NAME_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> groupByNameAs(String retName){
       groupBy(retName, UserRole.NAME_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> groupByNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, UserRole.NAME_PROPERTY, function);
       return this;
    }

    public UserRoleRequest<T> groupByCode(){
       groupBy(UserRole.CODE_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> groupByCodeAs(String retName){
       groupBy(retName, UserRole.CODE_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> groupByCodeWithFunction(String retName, AggrFunction function){
       groupBy(retName, UserRole.CODE_PROPERTY, function);
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

    public UserRoleRequest<T> orderByNameAscending(){
       addOrderByAscending(UserRole.NAME_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> orderByNameDescending(){
       addOrderByDescending(UserRole.NAME_PROPERTY);
       return this;
    }
    public UserRoleRequest<T> orderByNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(UserRole.NAME_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> orderByNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(UserRole.NAME_PROPERTY);
       return this;
    }
    public UserRoleRequest<T> orderByCodeAscending(){
       addOrderByAscending(UserRole.CODE_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> orderByCodeDescending(){
       addOrderByDescending(UserRole.CODE_PROPERTY);
       return this;
    }
    public UserRoleRequest<T> orderByCodeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(UserRole.CODE_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> orderByCodeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(UserRole.CODE_PROPERTY);
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
    public UserRoleRequest<T> orderByVersionAscending(){
       addOrderByAscending(UserRole.VERSION_PROPERTY);
       return this;
    }

    public UserRoleRequest<T> orderByVersionDescending(){
       addOrderByDescending(UserRole.VERSION_PROPERTY);
       return this;
    }


    public UserRoleRequest<T> statsFromAccessPermissionsAs(String name, AccessPermissionRequest subRequest){
       return statsFromAccessPermissionsAs(name, subRequest, false);
    }

    public UserRoleRequest<T> statsFromAccessPermissionsAs(String name, AccessPermissionRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(AccessPermission.ROLE_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public UserRoleRequest<T> statsFromAccessPermissions(AccessPermissionRequest subRequest){
       return statsFromAccessPermissionsAs(REFINEMENTS, subRequest);
    }
    public UserRoleRequest<T> countAccessPermissions(){
        return countAccessPermissionsAs("Count");
    }

    public UserRoleRequest<T> countAccessPermissionsAs(String name){
        return countAccessPermissionsWith(name, Q.accessPermissions().unlimited());
    }

    public UserRoleRequest<T> countAccessPermissionsWith(String name, AccessPermissionRequest subRequest){
        return statsFromAccessPermissionsAs(name, subRequest.count(), true);
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