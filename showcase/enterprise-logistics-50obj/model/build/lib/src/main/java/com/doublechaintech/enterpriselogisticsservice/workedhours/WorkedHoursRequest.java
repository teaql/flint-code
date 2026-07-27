package com.doublechaintech.enterpriselogisticsservice.workedhours;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberRequest;
import com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift;
import com.doublechaintech.enterpriselogisticsservice.workshift.WorkShiftRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class WorkedHoursRequest<T extends WorkedHours> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public WorkedHoursRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public WorkedHoursRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public WorkedHoursRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public WorkedHoursRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public WorkedHoursRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public WorkedHoursRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public WorkedHoursRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (WorkedHoursRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public WorkedHoursRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public WorkedHoursRequest<T> matchingAnyOf(WorkedHoursRequest workedHours){
        super.internalMatchAny(workedHours);
        return this;
    }

    public WorkedHoursRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public WorkedHoursRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public WorkedHoursRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public WorkedHoursRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectStaffIdOnly().selectShiftIdOnly().selectDate().selectHoursWorked().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public WorkedHoursRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public WorkedHoursRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectStaff().selectShift().selectDate().selectHoursWorked().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public WorkedHoursRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectStaff().selectShift().selectDate().selectHoursWorked().selectCreatedAt().selectUpdatedAt().selectVersion();
    }


    public WorkedHoursRequest<T> selectId(){
       selectProperty(WorkedHours.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WorkedHoursRequest<T> unselectId(){
       unselectProperty(WorkedHours.ID_PROPERTY);
       return this;
    }
    public WorkedHoursRequest<T> selectStaffIdOnly(){
       selectProperty(WorkedHours.STAFF_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> selectStaff(){
        return selectStaffWith(Q.staffMembers().unlimited().selectSelf());
    }

    public WorkedHoursRequest<T> selectStaffWith(StaffMemberRequest staff){
       selectProperty(WorkedHours.STAFF_PROPERTY);
       enhanceRelation(WorkedHours.STAFF_PROPERTY, staff);
       return this;
    }

    public WorkedHoursRequest<T> unselectStaff(){
       unselectProperty(WorkedHours.STAFF_PROPERTY);
       return this;
    }
    public WorkedHoursRequest<T> selectShiftIdOnly(){
       selectProperty(WorkedHours.SHIFT_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> selectShift(){
        return selectShiftWith(Q.workShifts().unlimited().selectSelf());
    }

    public WorkedHoursRequest<T> selectShiftWith(WorkShiftRequest shift){
       selectProperty(WorkedHours.SHIFT_PROPERTY);
       enhanceRelation(WorkedHours.SHIFT_PROPERTY, shift);
       return this;
    }

    public WorkedHoursRequest<T> unselectShift(){
       unselectProperty(WorkedHours.SHIFT_PROPERTY);
       return this;
    }
    public WorkedHoursRequest<T> selectDate(){
       selectProperty(WorkedHours.DATE_PROPERTY);
       return this;
    }

    /**
     * fill the date with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  date) to fetch date property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WorkedHoursRequest<T> unselectDate(){
       unselectProperty(WorkedHours.DATE_PROPERTY);
       return this;
    }
    public WorkedHoursRequest<T> selectHoursWorked(){
       selectProperty(WorkedHours.HOURS_WORKED_PROPERTY);
       return this;
    }

    /**
     * fill the hoursWorked with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  hoursWorked) to fetch hoursWorked property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WorkedHoursRequest<T> unselectHoursWorked(){
       unselectProperty(WorkedHours.HOURS_WORKED_PROPERTY);
       return this;
    }
    public WorkedHoursRequest<T> selectCreatedAt(){
       selectProperty(WorkedHours.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WorkedHoursRequest<T> unselectCreatedAt(){
       unselectProperty(WorkedHours.CREATED_AT_PROPERTY);
       return this;
    }
    public WorkedHoursRequest<T> selectUpdatedAt(){
       selectProperty(WorkedHours.UPDATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the updatedAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedAt) to fetch updatedAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WorkedHoursRequest<T> unselectUpdatedAt(){
       unselectProperty(WorkedHours.UPDATED_AT_PROPERTY);
       return this;
    }
    public WorkedHoursRequest<T> selectVersion(){
       selectProperty(WorkedHours.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WorkedHoursRequest<T> unselectVersion(){
       unselectProperty(WorkedHours.VERSION_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(WorkedHours.ID_PROPERTY, operator, values);
    }

    public WorkedHoursRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public WorkedHoursRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public WorkedHoursRequest<T> filterByStaff(StaffMember... staff){
      if (staff == null || staff.length == 0) {
        throw new IllegalArgumentException("filterByStaff parameter staff cannot be empty");
      }
      return appendSearchCriteria(createStaffCriteria(Operator.EQUAL, (Object[])staff));
    }

    public WorkedHoursRequest<T> withStaff(Operator operator, Object... values){
       return appendSearchCriteria(createStaffCriteria(operator, values));
    }

    public WorkedHoursRequest<T> withStaffIsUnknown(){
       return withStaff(Operator.IS_NULL);
    }

    public WorkedHoursRequest<T> withStaffIsKnown(){
       return withStaff(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStaffCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(WorkedHours.STAFF_PROPERTY, operator, values);
    }

    public WorkedHoursRequest<T> filterByStaff(Long staff){
      if(staff == null){
         return this;
      }
      return withStaff(Operator.EQUAL, staff);
    }
    public WorkedHoursRequest<T> withStaffMatching(StaffMemberRequest staff){
       return appendSearchCriteria(new SubQuerySearchCriteria(WorkedHours.STAFF_PROPERTY, staff, StaffMember.ID_PROPERTY));
    }

    public WorkedHoursRequest<T> filterByShift(WorkShift... shift){
      if (shift == null || shift.length == 0) {
        throw new IllegalArgumentException("filterByShift parameter shift cannot be empty");
      }
      return appendSearchCriteria(createShiftCriteria(Operator.EQUAL, (Object[])shift));
    }

    public WorkedHoursRequest<T> withShift(Operator operator, Object... values){
       return appendSearchCriteria(createShiftCriteria(operator, values));
    }

    public WorkedHoursRequest<T> withShiftIsUnknown(){
       return withShift(Operator.IS_NULL);
    }

    public WorkedHoursRequest<T> withShiftIsKnown(){
       return withShift(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createShiftCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(WorkedHours.SHIFT_PROPERTY, operator, values);
    }

    public WorkedHoursRequest<T> filterByShift(Long shift){
      if(shift == null){
         return this;
      }
      return withShift(Operator.EQUAL, shift);
    }
    public WorkedHoursRequest<T> withShiftMatching(WorkShiftRequest shift){
       return appendSearchCriteria(new SubQuerySearchCriteria(WorkedHours.SHIFT_PROPERTY, shift, WorkShift.ID_PROPERTY));
    }

    public WorkedHoursRequest<T> filterByDate(LocalDate... date){
      if (date == null || date.length == 0) {
        throw new IllegalArgumentException("filterByDate parameter date cannot be empty");
      }
      return appendSearchCriteria(createDateCriteria(Operator.EQUAL, (Object[])date));
    }

    public WorkedHoursRequest<T> withDate(Operator operator, Object... values){
       return appendSearchCriteria(createDateCriteria(operator, values));
    }

    public WorkedHoursRequest<T> withDateIsUnknown(){
       return withDate(Operator.IS_NULL);
    }

    public WorkedHoursRequest<T> withDateIsKnown(){
       return withDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(WorkedHours.DATE_PROPERTY, operator, values);
    }

    public WorkedHoursRequest<T> withDateGreaterThan(LocalDate date){
       return withDate(Operator.GREATER_THAN, date);
    }

    public WorkedHoursRequest<T> withDateGreaterThanOrEqualTo(LocalDate date){
       return withDate(Operator.GREATER_THAN_OR_EQUAL, date);
    }

    public WorkedHoursRequest<T> withDateLessThan(LocalDate date){
       return withDate(Operator.LESS_THAN, date);
    }

    public WorkedHoursRequest<T> withDateLessThanOrEqualTo(LocalDate date){
       return withDate(Operator.LESS_THAN_OR_EQUAL, date);
    }

    public WorkedHoursRequest<T> withDateBetween(LocalDate startOfDate, LocalDate endOfDate){
       return withDate(Operator.BETWEEN, startOfDate, endOfDate);
    }
    public WorkedHoursRequest<T> withDateBefore(LocalDate date){
       return withDate(Operator.LESS_THAN, date);
    }

    public WorkedHoursRequest<T> withDateBefore(Date date){
       return withDate(Operator.LESS_THAN, date);
    }

    public WorkedHoursRequest<T> withDateAfter(LocalDate date){
       return withDate(Operator.GREATER_THAN, date);
    }

    public WorkedHoursRequest<T> withDateAfter(Date date){
       return withDate(Operator.GREATER_THAN, date);
    }

    public WorkedHoursRequest<T> withDateBetween(Date startOfDate, Date endOfDate){
       return withDate(Operator.BETWEEN, startOfDate, endOfDate);
    }




    public WorkedHoursRequest<T> filterByHoursWorked(String... hoursWorked){
      if (hoursWorked == null || hoursWorked.length == 0) {
        throw new IllegalArgumentException("filterByHoursWorked parameter hoursWorked cannot be empty");
      }
      return appendSearchCriteria(createHoursWorkedCriteria(Operator.EQUAL, (Object[])hoursWorked));
    }

    public WorkedHoursRequest<T> withHoursWorked(Operator operator, Object... values){
       return appendSearchCriteria(createHoursWorkedCriteria(operator, values));
    }

    public WorkedHoursRequest<T> withHoursWorkedIsUnknown(){
       return withHoursWorked(Operator.IS_NULL);
    }

    public WorkedHoursRequest<T> withHoursWorkedIsKnown(){
       return withHoursWorked(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createHoursWorkedCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(WorkedHours.HOURS_WORKED_PROPERTY, operator, values);
    }

    public WorkedHoursRequest<T> withHoursWorkedGreaterThan(String hoursWorked){
       return withHoursWorked(Operator.GREATER_THAN, hoursWorked);
    }

    public WorkedHoursRequest<T> withHoursWorkedGreaterThanOrEqualTo(String hoursWorked){
       return withHoursWorked(Operator.GREATER_THAN_OR_EQUAL, hoursWorked);
    }

    public WorkedHoursRequest<T> withHoursWorkedLessThan(String hoursWorked){
       return withHoursWorked(Operator.LESS_THAN, hoursWorked);
    }

    public WorkedHoursRequest<T> withHoursWorkedLessThanOrEqualTo(String hoursWorked){
       return withHoursWorked(Operator.LESS_THAN_OR_EQUAL, hoursWorked);
    }

    public WorkedHoursRequest<T> withHoursWorkedBetween(String startOfHoursWorked, String endOfHoursWorked){
       return withHoursWorked(Operator.BETWEEN, startOfHoursWorked, endOfHoursWorked);
    }
    public WorkedHoursRequest<T> withHoursWorkedStartingWith(String hoursWorked){
       return withHoursWorked(Operator.BEGIN_WITH, hoursWorked);
    }
    public WorkedHoursRequest<T> withHoursWorkedContaining(String hoursWorked){
       return withHoursWorked(Operator.CONTAIN, hoursWorked);
    }

    public WorkedHoursRequest<T> withHoursWorkedEndingWith(String hoursWorked){
       return withHoursWorked(Operator.END_WITH, hoursWorked);
    }

    public WorkedHoursRequest<T> withHoursWorkedIs(String hoursWorked){
       return withHoursWorked(Operator.EQUAL, hoursWorked);
    }

    public WorkedHoursRequest<T> withHoursWorkedSoundingLike(String hoursWorked){
       return withHoursWorked(Operator.SOUNDS_LIKE, hoursWorked);
    }



    public WorkedHoursRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public WorkedHoursRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public WorkedHoursRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public WorkedHoursRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(WorkedHours.CREATED_AT_PROPERTY, operator, values);
    }

    public WorkedHoursRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public WorkedHoursRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public WorkedHoursRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public WorkedHoursRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public WorkedHoursRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public WorkedHoursRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public WorkedHoursRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public WorkedHoursRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public WorkedHoursRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public WorkedHoursRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }




    public WorkedHoursRequest<T> filterByUpdatedAt(LocalDateTime... updatedAt){
      if (updatedAt == null || updatedAt.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedAt parameter updatedAt cannot be empty");
      }
      return appendSearchCriteria(createUpdatedAtCriteria(Operator.EQUAL, (Object[])updatedAt));
    }

    public WorkedHoursRequest<T> withUpdatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedAtCriteria(operator, values));
    }

    public WorkedHoursRequest<T> withUpdatedAtIsUnknown(){
       return withUpdatedAt(Operator.IS_NULL);
    }

    public WorkedHoursRequest<T> withUpdatedAtIsKnown(){
       return withUpdatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(WorkedHours.UPDATED_AT_PROPERTY, operator, values);
    }

    public WorkedHoursRequest<T> withUpdatedAtGreaterThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public WorkedHoursRequest<T> withUpdatedAtGreaterThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN_OR_EQUAL, updatedAt);
    }

    public WorkedHoursRequest<T> withUpdatedAtLessThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public WorkedHoursRequest<T> withUpdatedAtLessThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN_OR_EQUAL, updatedAt);
    }

    public WorkedHoursRequest<T> withUpdatedAtBetween(LocalDateTime startOfUpdatedAt, LocalDateTime endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }
    public WorkedHoursRequest<T> withUpdatedAtBefore(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public WorkedHoursRequest<T> withUpdatedAtBefore(Date updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public WorkedHoursRequest<T> withUpdatedAtAfter(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public WorkedHoursRequest<T> withUpdatedAtAfter(Date updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public WorkedHoursRequest<T> withUpdatedAtBetween(Date startOfUpdatedAt, Date endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }




    public WorkedHoursRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public WorkedHoursRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public WorkedHoursRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public WorkedHoursRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(WorkedHours.VERSION_PROPERTY, operator, values);
    }

    public WorkedHoursRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public WorkedHoursRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public WorkedHoursRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public WorkedHoursRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public WorkedHoursRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public WorkedHoursRequest<T> count(){
        super.count();
        return this;
    }
    public WorkedHoursRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public WorkedHoursRequest<T> groupByStaffWithDetails(){
       return groupByStaffWithDetails(Q.staffMembers().unlimited());
    }

    public WorkedHoursRequest<T> groupByStaffWithDetails(StaffMemberRequest subRequest){
       aggregate(WorkedHours.STAFF_PROPERTY, subRequest);
       return this;
    }

    public WorkedHoursRequest<T> groupByShiftWithDetails(){
       return groupByShiftWithDetails(Q.workShifts().unlimited());
    }

    public WorkedHoursRequest<T> groupByShiftWithDetails(WorkShiftRequest subRequest){
       aggregate(WorkedHours.SHIFT_PROPERTY, subRequest);
       return this;
    }







    public WorkedHoursRequest<T> groupById(){
       groupBy(WorkedHours.ID_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> groupByIdAs(String retName){
       groupBy(retName, WorkedHours.ID_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, WorkedHours.ID_PROPERTY, function);
       return this;
    }
    public WorkedHoursRequest<T> groupByStaffWith(StaffMemberRequest subRequest){
       groupBy(WorkedHours.STAFF_PROPERTY, subRequest);
       return this;
    }
    public WorkedHoursRequest<T> groupByStaff(){
       groupBy(WorkedHours.STAFF_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> groupByStaffAs(String retName){
       groupBy(retName, WorkedHours.STAFF_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> groupByStaffWithFunction(String retName, AggrFunction function){
       groupBy(retName, WorkedHours.STAFF_PROPERTY, function);
       return this;
    }
    public WorkedHoursRequest<T> groupByShiftWith(WorkShiftRequest subRequest){
       groupBy(WorkedHours.SHIFT_PROPERTY, subRequest);
       return this;
    }
    public WorkedHoursRequest<T> groupByShift(){
       groupBy(WorkedHours.SHIFT_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> groupByShiftAs(String retName){
       groupBy(retName, WorkedHours.SHIFT_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> groupByShiftWithFunction(String retName, AggrFunction function){
       groupBy(retName, WorkedHours.SHIFT_PROPERTY, function);
       return this;
    }

    public WorkedHoursRequest<T> groupByDate(){
       groupBy(WorkedHours.DATE_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> groupByDateAs(String retName){
       groupBy(retName, WorkedHours.DATE_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> groupByDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, WorkedHours.DATE_PROPERTY, function);
       return this;
    }

    public WorkedHoursRequest<T> groupByHoursWorked(){
       groupBy(WorkedHours.HOURS_WORKED_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> groupByHoursWorkedAs(String retName){
       groupBy(retName, WorkedHours.HOURS_WORKED_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> groupByHoursWorkedWithFunction(String retName, AggrFunction function){
       groupBy(retName, WorkedHours.HOURS_WORKED_PROPERTY, function);
       return this;
    }

    public WorkedHoursRequest<T> groupByCreatedAt(){
       groupBy(WorkedHours.CREATED_AT_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, WorkedHours.CREATED_AT_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, WorkedHours.CREATED_AT_PROPERTY, function);
       return this;
    }

    public WorkedHoursRequest<T> groupByUpdatedAt(){
       groupBy(WorkedHours.UPDATED_AT_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> groupByUpdatedAtAs(String retName){
       groupBy(retName, WorkedHours.UPDATED_AT_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> groupByUpdatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, WorkedHours.UPDATED_AT_PROPERTY, function);
       return this;
    }

    public WorkedHoursRequest<T> groupByVersion(){
       groupBy(WorkedHours.VERSION_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> groupByVersionAs(String retName){
       groupBy(retName, WorkedHours.VERSION_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, WorkedHours.VERSION_PROPERTY, function);
       return this;
    }



    public WorkedHoursRequest<T> orderByIdAscending(){
       addOrderByAscending(WorkedHours.ID_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> orderByIdDescending(){
       addOrderByDescending(WorkedHours.ID_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> orderByStaffAscending(){
       addOrderByAscending(WorkedHours.STAFF_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> orderByStaffDescending(){
       addOrderByDescending(WorkedHours.STAFF_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> orderByShiftAscending(){
       addOrderByAscending(WorkedHours.SHIFT_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> orderByShiftDescending(){
       addOrderByDescending(WorkedHours.SHIFT_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> orderByDateAscending(){
       addOrderByAscending(WorkedHours.DATE_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> orderByDateDescending(){
       addOrderByDescending(WorkedHours.DATE_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> orderByHoursWorkedAscending(){
       addOrderByAscending(WorkedHours.HOURS_WORKED_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> orderByHoursWorkedDescending(){
       addOrderByDescending(WorkedHours.HOURS_WORKED_PROPERTY);
       return this;
    }
    public WorkedHoursRequest<T> orderByHoursWorkedAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(WorkedHours.HOURS_WORKED_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> orderByHoursWorkedDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(WorkedHours.HOURS_WORKED_PROPERTY);
       return this;
    }
    public WorkedHoursRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(WorkedHours.CREATED_AT_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(WorkedHours.CREATED_AT_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> orderByUpdatedAtAscending(){
       addOrderByAscending(WorkedHours.UPDATED_AT_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> orderByUpdatedAtDescending(){
       addOrderByDescending(WorkedHours.UPDATED_AT_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> orderByVersionAscending(){
       addOrderByAscending(WorkedHours.VERSION_PROPERTY);
       return this;
    }

    public WorkedHoursRequest<T> orderByVersionDescending(){
       addOrderByDescending(WorkedHours.VERSION_PROPERTY);
       return this;
    }


    public StaffMemberRequest rollUpToStaff(){
       StaffMemberRequest staff = Q.staffMembers().unlimited();
       this.withStaffMatching(staff)
           .groupByStaffWith(staff);
       return staff;
    }

    public WorkShiftRequest rollUpToShift(){
       WorkShiftRequest shift = Q.workShifts().unlimited();
       this.withShiftMatching(shift)
           .groupByShiftWith(shift);
       return shift;
    }







   public WorkedHoursRequest<T> facetByStaffAs(String facetName, StaffMemberRequest staff){
       return facetByStaffAs(facetName, staff, true);
   }

   public WorkedHoursRequest<T> facetByStaffAs(String facetName, StaffMemberRequest staff, boolean includeAllFacets){
       addFacet(facetName, WorkedHours.STAFF_PROPERTY, staff, includeAllFacets);
       return this;
   }
   public WorkedHoursRequest<T> facetByShiftAs(String facetName, WorkShiftRequest shift){
       return facetByShiftAs(facetName, shift, true);
   }

   public WorkedHoursRequest<T> facetByShiftAs(String facetName, WorkShiftRequest shift, boolean includeAllFacets){
       addFacet(facetName, WorkedHours.SHIFT_PROPERTY, shift, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public WorkedHoursRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public WorkedHoursRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public WorkedHoursRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public WorkedHoursRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public WorkedHoursRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}