package com.doublechaintech.enterpriselogisticsservice.accesspermission;

import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
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
        return selectId().selectPermissionCode().selectResource().selectAction().selectDescription().selectVersion();
    }

    public AccessPermissionRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public AccessPermissionRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectPermissionCode().selectResource().selectAction().selectDescription().selectVersion();
    }

    public AccessPermissionRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectPermissionCode().selectResource().selectAction().selectDescription().selectVersion();
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
    public AccessPermissionRequest<T> selectPermissionCode(){
       selectProperty(AccessPermission.PERMISSION_CODE_PROPERTY);
       return this;
    }

    /**
     * fill the permissionCode with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  permissionCode) to fetch permissionCode property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public AccessPermissionRequest<T> unselectPermissionCode(){
       unselectProperty(AccessPermission.PERMISSION_CODE_PROPERTY);
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
    public AccessPermissionRequest<T> selectDescription(){
       selectProperty(AccessPermission.DESCRIPTION_PROPERTY);
       return this;
    }

    /**
     * fill the description with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  description) to fetch description property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public AccessPermissionRequest<T> unselectDescription(){
       unselectProperty(AccessPermission.DESCRIPTION_PROPERTY);
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



    public AccessPermissionRequest<T> filterByPermissionCode(String... permissionCode){
      if (permissionCode == null || permissionCode.length == 0) {
        throw new IllegalArgumentException("filterByPermissionCode parameter permissionCode cannot be empty");
      }
      return appendSearchCriteria(createPermissionCodeCriteria(Operator.EQUAL, (Object[])permissionCode));
    }

    public AccessPermissionRequest<T> withPermissionCode(Operator operator, Object... values){
       return appendSearchCriteria(createPermissionCodeCriteria(operator, values));
    }

    public AccessPermissionRequest<T> withPermissionCodeIsUnknown(){
       return withPermissionCode(Operator.IS_NULL);
    }

    public AccessPermissionRequest<T> withPermissionCodeIsKnown(){
       return withPermissionCode(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPermissionCodeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(AccessPermission.PERMISSION_CODE_PROPERTY, operator, values);
    }

    public AccessPermissionRequest<T> withPermissionCodeGreaterThan(String permissionCode){
       return withPermissionCode(Operator.GREATER_THAN, permissionCode);
    }

    public AccessPermissionRequest<T> withPermissionCodeGreaterThanOrEqualTo(String permissionCode){
       return withPermissionCode(Operator.GREATER_THAN_OR_EQUAL, permissionCode);
    }

    public AccessPermissionRequest<T> withPermissionCodeLessThan(String permissionCode){
       return withPermissionCode(Operator.LESS_THAN, permissionCode);
    }

    public AccessPermissionRequest<T> withPermissionCodeLessThanOrEqualTo(String permissionCode){
       return withPermissionCode(Operator.LESS_THAN_OR_EQUAL, permissionCode);
    }

    public AccessPermissionRequest<T> withPermissionCodeBetween(String startOfPermissionCode, String endOfPermissionCode){
       return withPermissionCode(Operator.BETWEEN, startOfPermissionCode, endOfPermissionCode);
    }
    public AccessPermissionRequest<T> withPermissionCodeStartingWith(String permissionCode){
       return withPermissionCode(Operator.BEGIN_WITH, permissionCode);
    }
    public AccessPermissionRequest<T> withPermissionCodeContaining(String permissionCode){
       return withPermissionCode(Operator.CONTAIN, permissionCode);
    }

    public AccessPermissionRequest<T> withPermissionCodeEndingWith(String permissionCode){
       return withPermissionCode(Operator.END_WITH, permissionCode);
    }

    public AccessPermissionRequest<T> withPermissionCodeIs(String permissionCode){
       return withPermissionCode(Operator.EQUAL, permissionCode);
    }

    public AccessPermissionRequest<T> withPermissionCodeSoundingLike(String permissionCode){
       return withPermissionCode(Operator.SOUNDS_LIKE, permissionCode);
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



    public AccessPermissionRequest<T> filterByDescription(String... description){
      if (description == null || description.length == 0) {
        throw new IllegalArgumentException("filterByDescription parameter description cannot be empty");
      }
      return appendSearchCriteria(createDescriptionCriteria(Operator.EQUAL, (Object[])description));
    }

    public AccessPermissionRequest<T> withDescription(Operator operator, Object... values){
       return appendSearchCriteria(createDescriptionCriteria(operator, values));
    }

    public AccessPermissionRequest<T> withDescriptionIsUnknown(){
       return withDescription(Operator.IS_NULL);
    }

    public AccessPermissionRequest<T> withDescriptionIsKnown(){
       return withDescription(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDescriptionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(AccessPermission.DESCRIPTION_PROPERTY, operator, values);
    }

    public AccessPermissionRequest<T> withDescriptionGreaterThan(String description){
       return withDescription(Operator.GREATER_THAN, description);
    }

    public AccessPermissionRequest<T> withDescriptionGreaterThanOrEqualTo(String description){
       return withDescription(Operator.GREATER_THAN_OR_EQUAL, description);
    }

    public AccessPermissionRequest<T> withDescriptionLessThan(String description){
       return withDescription(Operator.LESS_THAN, description);
    }

    public AccessPermissionRequest<T> withDescriptionLessThanOrEqualTo(String description){
       return withDescription(Operator.LESS_THAN_OR_EQUAL, description);
    }

    public AccessPermissionRequest<T> withDescriptionBetween(String startOfDescription, String endOfDescription){
       return withDescription(Operator.BETWEEN, startOfDescription, endOfDescription);
    }
    public AccessPermissionRequest<T> withDescriptionStartingWith(String description){
       return withDescription(Operator.BEGIN_WITH, description);
    }
    public AccessPermissionRequest<T> withDescriptionContaining(String description){
       return withDescription(Operator.CONTAIN, description);
    }

    public AccessPermissionRequest<T> withDescriptionEndingWith(String description){
       return withDescription(Operator.END_WITH, description);
    }

    public AccessPermissionRequest<T> withDescriptionIs(String description){
       return withDescription(Operator.EQUAL, description);
    }

    public AccessPermissionRequest<T> withDescriptionSoundingLike(String description){
       return withDescription(Operator.SOUNDS_LIKE, description);
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

    public AccessPermissionRequest<T> groupByPermissionCode(){
       groupBy(AccessPermission.PERMISSION_CODE_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> groupByPermissionCodeAs(String retName){
       groupBy(retName, AccessPermission.PERMISSION_CODE_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> groupByPermissionCodeWithFunction(String retName, AggrFunction function){
       groupBy(retName, AccessPermission.PERMISSION_CODE_PROPERTY, function);
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

    public AccessPermissionRequest<T> groupByDescription(){
       groupBy(AccessPermission.DESCRIPTION_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> groupByDescriptionAs(String retName){
       groupBy(retName, AccessPermission.DESCRIPTION_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> groupByDescriptionWithFunction(String retName, AggrFunction function){
       groupBy(retName, AccessPermission.DESCRIPTION_PROPERTY, function);
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



    public AccessPermissionRequest<T> orderByIdAscending(){
       addOrderByAscending(AccessPermission.ID_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> orderByIdDescending(){
       addOrderByDescending(AccessPermission.ID_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> orderByPermissionCodeAscending(){
       addOrderByAscending(AccessPermission.PERMISSION_CODE_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> orderByPermissionCodeDescending(){
       addOrderByDescending(AccessPermission.PERMISSION_CODE_PROPERTY);
       return this;
    }
    public AccessPermissionRequest<T> orderByPermissionCodeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(AccessPermission.PERMISSION_CODE_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> orderByPermissionCodeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(AccessPermission.PERMISSION_CODE_PROPERTY);
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
    public AccessPermissionRequest<T> orderByDescriptionAscending(){
       addOrderByAscending(AccessPermission.DESCRIPTION_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> orderByDescriptionDescending(){
       addOrderByDescending(AccessPermission.DESCRIPTION_PROPERTY);
       return this;
    }
    public AccessPermissionRequest<T> orderByDescriptionAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(AccessPermission.DESCRIPTION_PROPERTY);
       return this;
    }

    public AccessPermissionRequest<T> orderByDescriptionDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(AccessPermission.DESCRIPTION_PROPERTY);
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