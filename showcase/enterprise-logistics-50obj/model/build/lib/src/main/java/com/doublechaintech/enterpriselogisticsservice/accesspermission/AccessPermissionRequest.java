package com.doublechaintech.enterpriselogisticsservice.accesspermission;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.userrole.UserRole;
import com.doublechaintech.enterpriselogisticsservice.userrole.UserRoleRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;

public class AccessPermissionRequest<T extends AccessPermission> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public AccessPermissionRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public AccessPermissionRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public AccessPermissionRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public AccessPermissionRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public AccessPermissionRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public AccessPermissionRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public AccessPermissionRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (AccessPermissionRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public AccessPermissionRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public AccessPermissionRequest<T> matchingAnyOf(AccessPermissionRequest accessPermission){
        super.internalMatchAny(accessPermission);
        return this;
    }

    public AccessPermissionRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public AccessPermissionRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public AccessPermissionRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public AccessPermissionRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectName().selectResource().selectAction().selectRoleIdOnly().selectVersion();
    }

    public AccessPermissionRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public AccessPermissionRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectResource().selectAction().selectRole().selectVersion();
    }

    public AccessPermissionRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectName().selectResource().selectAction().selectRole().selectVersion();
    }


    public AccessPermissionRequest<T> selectId(){
       selectProperty(AccessPermission.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public AccessPermissionRequest<T> unselectId(){
       unselectProperty(AccessPermission.ID_PROPERTY);
       return this;
    }
    public AccessPermissionRequest<T> selectName(){
       selectProperty(AccessPermission.NAME_PROPERTY);
       return this;
    }

    /**
     * fill the name with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  name) to fetch name property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public AccessPermissionRequest<T> unselectName(){
       unselectProperty(AccessPermission.NAME_PROPERTY);
       return this;
    }
    public AccessPermissionRequest<T> selectResource(){
       selectProperty(AccessPermission.RESOURCE_PROPERTY);
       return this;
    }

    /**
     * fill the resource with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  resource) to fetch resource property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public AccessPermissionRequest<T> unselectResource(){
       unselectProperty(AccessPermission.RESOURCE_PROPERTY);
       return this;
    }
    public AccessPermissionRequest<T> selectAction(){
       selectProperty(AccessPermission.ACTION_PROPERTY);
       return this;
    }

    /**
     * fill the action with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  action) to fetch action property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public AccessPermissionRequest<T> unselectAction(){
       unselectProperty(AccessPermission.ACTION_PROPERTY);
       return this;
    }
    public AccessPermissionRequest<T> selectRoleIdOnly(){
       selectProperty(AccessPermission.ROLE_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> selectRole(){
        return selectRoleWith(Q.userRoles().unlimited().selectSelf());
    }

    public AccessPermissionRequest<T> selectRoleWith(UserRoleRequest role){
       selectProperty(AccessPermission.ROLE_PROPERTY);
       enhanceRelation(AccessPermission.ROLE_PROPERTY, role);
       return this;
    }

    public AccessPermissionRequest<T> unselectRole(){
       unselectProperty(AccessPermission.ROLE_PROPERTY);
       return this;
    }
    public AccessPermissionRequest<T> selectVersion(){
       selectProperty(AccessPermission.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public AccessPermissionRequest<T> unselectVersion(){
       unselectProperty(AccessPermission.VERSION_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(AccessPermission.ID_PROPERTY, operator, values);
    }

    public AccessPermissionRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public AccessPermissionRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public AccessPermissionRequest<T> filterByName(String... name){
      if (name == null || name.length == 0) {
        throw new IllegalArgumentException("filterByName parameter name cannot be empty");
      }
      return appendSearchCriteria(createNameCriteria(Operator.EQUAL, (Object[])name));
    }

    public AccessPermissionRequest<T> withName(Operator operator, Object... values){
       return appendSearchCriteria(createNameCriteria(operator, values));
    }

    public AccessPermissionRequest<T> withNameIsUnknown(){
       return withName(Operator.IS_NULL);
    }

    public AccessPermissionRequest<T> withNameIsKnown(){
       return withName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(AccessPermission.NAME_PROPERTY, operator, values);
    }

    public AccessPermissionRequest<T> withNameGreaterThan(String name){
       return withName(Operator.GREATER_THAN, name);
    }

    public AccessPermissionRequest<T> withNameGreaterThanOrEqualTo(String name){
       return withName(Operator.GREATER_THAN_OR_EQUAL, name);
    }

    public AccessPermissionRequest<T> withNameLessThan(String name){
       return withName(Operator.LESS_THAN, name);
    }

    public AccessPermissionRequest<T> withNameLessThanOrEqualTo(String name){
       return withName(Operator.LESS_THAN_OR_EQUAL, name);
    }

    public AccessPermissionRequest<T> withNameBetween(String startOfName, String endOfName){
       return withName(Operator.BETWEEN, startOfName, endOfName);
    }
    public AccessPermissionRequest<T> withNameStartingWith(String name){
       return withName(Operator.BEGIN_WITH, name);
    }
    public AccessPermissionRequest<T> withNameContaining(String name){
       return withName(Operator.CONTAIN, name);
    }

    public AccessPermissionRequest<T> withNameEndingWith(String name){
       return withName(Operator.END_WITH, name);
    }

    public AccessPermissionRequest<T> withNameIs(String name){
       return withName(Operator.EQUAL, name);
    }

    public AccessPermissionRequest<T> withNameSoundingLike(String name){
       return withName(Operator.SOUNDS_LIKE, name);
    }



    public AccessPermissionRequest<T> filterByResource(String... resource){
      if (resource == null || resource.length == 0) {
        throw new IllegalArgumentException("filterByResource parameter resource cannot be empty");
      }
      return appendSearchCriteria(createResourceCriteria(Operator.EQUAL, (Object[])resource));
    }

    public AccessPermissionRequest<T> withResource(Operator operator, Object... values){
       return appendSearchCriteria(createResourceCriteria(operator, values));
    }

    public AccessPermissionRequest<T> withResourceIsUnknown(){
       return withResource(Operator.IS_NULL);
    }

    public AccessPermissionRequest<T> withResourceIsKnown(){
       return withResource(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createResourceCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(AccessPermission.RESOURCE_PROPERTY, operator, values);
    }

    public AccessPermissionRequest<T> withResourceGreaterThan(String resource){
       return withResource(Operator.GREATER_THAN, resource);
    }

    public AccessPermissionRequest<T> withResourceGreaterThanOrEqualTo(String resource){
       return withResource(Operator.GREATER_THAN_OR_EQUAL, resource);
    }

    public AccessPermissionRequest<T> withResourceLessThan(String resource){
       return withResource(Operator.LESS_THAN, resource);
    }

    public AccessPermissionRequest<T> withResourceLessThanOrEqualTo(String resource){
       return withResource(Operator.LESS_THAN_OR_EQUAL, resource);
    }

    public AccessPermissionRequest<T> withResourceBetween(String startOfResource, String endOfResource){
       return withResource(Operator.BETWEEN, startOfResource, endOfResource);
    }
    public AccessPermissionRequest<T> withResourceStartingWith(String resource){
       return withResource(Operator.BEGIN_WITH, resource);
    }
    public AccessPermissionRequest<T> withResourceContaining(String resource){
       return withResource(Operator.CONTAIN, resource);
    }

    public AccessPermissionRequest<T> withResourceEndingWith(String resource){
       return withResource(Operator.END_WITH, resource);
    }

    public AccessPermissionRequest<T> withResourceIs(String resource){
       return withResource(Operator.EQUAL, resource);
    }

    public AccessPermissionRequest<T> withResourceSoundingLike(String resource){
       return withResource(Operator.SOUNDS_LIKE, resource);
    }



    public AccessPermissionRequest<T> filterByAction(String... action){
      if (action == null || action.length == 0) {
        throw new IllegalArgumentException("filterByAction parameter action cannot be empty");
      }
      return appendSearchCriteria(createActionCriteria(Operator.EQUAL, (Object[])action));
    }

    public AccessPermissionRequest<T> withAction(Operator operator, Object... values){
       return appendSearchCriteria(createActionCriteria(operator, values));
    }

    public AccessPermissionRequest<T> withActionIsUnknown(){
       return withAction(Operator.IS_NULL);
    }

    public AccessPermissionRequest<T> withActionIsKnown(){
       return withAction(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createActionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(AccessPermission.ACTION_PROPERTY, operator, values);
    }

    public AccessPermissionRequest<T> withActionGreaterThan(String action){
       return withAction(Operator.GREATER_THAN, action);
    }

    public AccessPermissionRequest<T> withActionGreaterThanOrEqualTo(String action){
       return withAction(Operator.GREATER_THAN_OR_EQUAL, action);
    }

    public AccessPermissionRequest<T> withActionLessThan(String action){
       return withAction(Operator.LESS_THAN, action);
    }

    public AccessPermissionRequest<T> withActionLessThanOrEqualTo(String action){
       return withAction(Operator.LESS_THAN_OR_EQUAL, action);
    }

    public AccessPermissionRequest<T> withActionBetween(String startOfAction, String endOfAction){
       return withAction(Operator.BETWEEN, startOfAction, endOfAction);
    }
    public AccessPermissionRequest<T> withActionStartingWith(String action){
       return withAction(Operator.BEGIN_WITH, action);
    }
    public AccessPermissionRequest<T> withActionContaining(String action){
       return withAction(Operator.CONTAIN, action);
    }

    public AccessPermissionRequest<T> withActionEndingWith(String action){
       return withAction(Operator.END_WITH, action);
    }

    public AccessPermissionRequest<T> withActionIs(String action){
       return withAction(Operator.EQUAL, action);
    }

    public AccessPermissionRequest<T> withActionSoundingLike(String action){
       return withAction(Operator.SOUNDS_LIKE, action);
    }



    public AccessPermissionRequest<T> filterByRole(UserRole... role){
      if (role == null || role.length == 0) {
        throw new IllegalArgumentException("filterByRole parameter role cannot be empty");
      }
      return appendSearchCriteria(createRoleCriteria(Operator.EQUAL, (Object[])role));
    }

    public AccessPermissionRequest<T> withRole(Operator operator, Object... values){
       return appendSearchCriteria(createRoleCriteria(operator, values));
    }

    public AccessPermissionRequest<T> withRoleIsUnknown(){
       return withRole(Operator.IS_NULL);
    }

    public AccessPermissionRequest<T> withRoleIsKnown(){
       return withRole(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createRoleCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(AccessPermission.ROLE_PROPERTY, operator, values);
    }

    public AccessPermissionRequest<T> filterByRole(Long role){
      if(role == null){
         return this;
      }
      return withRole(Operator.EQUAL, role);
    }
    public AccessPermissionRequest<T> withRoleMatching(UserRoleRequest role){
       return appendSearchCriteria(new SubQuerySearchCriteria(AccessPermission.ROLE_PROPERTY, role, UserRole.ID_PROPERTY));
    }

    public AccessPermissionRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public AccessPermissionRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public AccessPermissionRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public AccessPermissionRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(AccessPermission.VERSION_PROPERTY, operator, values);
    }

    public AccessPermissionRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public AccessPermissionRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public AccessPermissionRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public AccessPermissionRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public AccessPermissionRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public AccessPermissionRequest<T> count(){
        super.count();
        return this;
    }
    public AccessPermissionRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public AccessPermissionRequest<T> groupByRoleWithDetails(){
       return groupByRoleWithDetails(Q.userRoles().unlimited());
    }

    public AccessPermissionRequest<T> groupByRoleWithDetails(UserRoleRequest subRequest){
       aggregate(AccessPermission.ROLE_PROPERTY, subRequest);
       return this;
    }



    public AccessPermissionRequest<T> groupById(){
       groupBy(AccessPermission.ID_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> groupByIdAs(String retName){
       groupBy(retName, AccessPermission.ID_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, AccessPermission.ID_PROPERTY, function);
       return this;
    }

    public AccessPermissionRequest<T> groupByName(){
       groupBy(AccessPermission.NAME_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> groupByNameAs(String retName){
       groupBy(retName, AccessPermission.NAME_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> groupByNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, AccessPermission.NAME_PROPERTY, function);
       return this;
    }

    public AccessPermissionRequest<T> groupByResource(){
       groupBy(AccessPermission.RESOURCE_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> groupByResourceAs(String retName){
       groupBy(retName, AccessPermission.RESOURCE_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> groupByResourceWithFunction(String retName, AggrFunction function){
       groupBy(retName, AccessPermission.RESOURCE_PROPERTY, function);
       return this;
    }

    public AccessPermissionRequest<T> groupByAction(){
       groupBy(AccessPermission.ACTION_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> groupByActionAs(String retName){
       groupBy(retName, AccessPermission.ACTION_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> groupByActionWithFunction(String retName, AggrFunction function){
       groupBy(retName, AccessPermission.ACTION_PROPERTY, function);
       return this;
    }
    public AccessPermissionRequest<T> groupByRoleWith(UserRoleRequest subRequest){
       groupBy(AccessPermission.ROLE_PROPERTY, subRequest);
       return this;
    }
    public AccessPermissionRequest<T> groupByRole(){
       groupBy(AccessPermission.ROLE_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> groupByRoleAs(String retName){
       groupBy(retName, AccessPermission.ROLE_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> groupByRoleWithFunction(String retName, AggrFunction function){
       groupBy(retName, AccessPermission.ROLE_PROPERTY, function);
       return this;
    }

    public AccessPermissionRequest<T> groupByVersion(){
       groupBy(AccessPermission.VERSION_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> groupByVersionAs(String retName){
       groupBy(retName, AccessPermission.VERSION_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, AccessPermission.VERSION_PROPERTY, function);
       return this;
    }

    public AccessPermissionRequest<T> withRoleIsAdmin(){
       filterByRole(com.doublechaintech.enterpriselogisticsservice.Constants.USER_ROLE_ADMIN);
       return this;
    }


    public AccessPermissionRequest<T> withRoleIsDispatcher(){
       filterByRole(com.doublechaintech.enterpriselogisticsservice.Constants.USER_ROLE_DISPATCHER);
       return this;
    }


    public AccessPermissionRequest<T> withRoleIsDriver(){
       filterByRole(com.doublechaintech.enterpriselogisticsservice.Constants.USER_ROLE_DRIVER);
       return this;
    }


    public AccessPermissionRequest<T> withRoleIsCs(){
       filterByRole(com.doublechaintech.enterpriselogisticsservice.Constants.USER_ROLE_CS);
       return this;
    }




    public AccessPermissionRequest<T> orderByIdAscending(){
       addOrderByAscending(AccessPermission.ID_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> orderByIdDescending(){
       addOrderByDescending(AccessPermission.ID_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> orderByNameAscending(){
       addOrderByAscending(AccessPermission.NAME_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> orderByNameDescending(){
       addOrderByDescending(AccessPermission.NAME_PROPERTY);
       return this;
    }
    public AccessPermissionRequest<T> orderByNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(AccessPermission.NAME_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> orderByNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(AccessPermission.NAME_PROPERTY);
       return this;
    }
    public AccessPermissionRequest<T> orderByResourceAscending(){
       addOrderByAscending(AccessPermission.RESOURCE_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> orderByResourceDescending(){
       addOrderByDescending(AccessPermission.RESOURCE_PROPERTY);
       return this;
    }
    public AccessPermissionRequest<T> orderByResourceAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(AccessPermission.RESOURCE_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> orderByResourceDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(AccessPermission.RESOURCE_PROPERTY);
       return this;
    }
    public AccessPermissionRequest<T> orderByActionAscending(){
       addOrderByAscending(AccessPermission.ACTION_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> orderByActionDescending(){
       addOrderByDescending(AccessPermission.ACTION_PROPERTY);
       return this;
    }
    public AccessPermissionRequest<T> orderByActionAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(AccessPermission.ACTION_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> orderByActionDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(AccessPermission.ACTION_PROPERTY);
       return this;
    }
    public AccessPermissionRequest<T> orderByRoleAscending(){
       addOrderByAscending(AccessPermission.ROLE_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> orderByRoleDescending(){
       addOrderByDescending(AccessPermission.ROLE_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> orderByVersionAscending(){
       addOrderByAscending(AccessPermission.VERSION_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> orderByVersionDescending(){
       addOrderByDescending(AccessPermission.VERSION_PROPERTY);
       return this;
    }


    public UserRoleRequest rollUpToRole(){
       UserRoleRequest role = Q.userRoles().unlimited();
       this.withRoleMatching(role)
           .groupByRoleWith(role);
       return role;
    }



   public AccessPermissionRequest<T> facetByRoleAs(String facetName, UserRoleRequest role){
       return facetByRoleAs(facetName, role, true);
   }

   public AccessPermissionRequest<T> facetByRoleAs(String facetName, UserRoleRequest role, boolean includeAllFacets){
       addFacet(facetName, AccessPermission.ROLE_PROPERTY, role, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public AccessPermissionRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public AccessPermissionRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public AccessPermissionRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public AccessPermissionRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public AccessPermissionRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}