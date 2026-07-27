package com.doublechaintech.enterpriselogisticsservice.workshift;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours;
import com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHoursRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class WorkShiftRequest<T extends WorkShift> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public WorkShiftRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public WorkShiftRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public WorkShiftRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public WorkShiftRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public WorkShiftRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public WorkShiftRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public WorkShiftRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (WorkShiftRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public WorkShiftRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public WorkShiftRequest<T> matchingAnyOf(WorkShiftRequest workShift){
        super.internalMatchAny(workShift);
        return this;
    }

    public WorkShiftRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public WorkShiftRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public WorkShiftRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public WorkShiftRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectName().selectStartTime().selectEndTime().selectShiftDate().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public WorkShiftRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public WorkShiftRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectStartTime().selectEndTime().selectShiftDate().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public WorkShiftRequest<T> selectChildren(){
        super.selectAny();
        selectWorkedHoursList();
        return selectId().selectName().selectStartTime().selectEndTime().selectShiftDate().selectCreatedAt().selectUpdatedAt().selectVersion();
    }


    public WorkShiftRequest<T> selectId(){
       selectProperty(WorkShift.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WorkShiftRequest<T> unselectId(){
       unselectProperty(WorkShift.ID_PROPERTY);
       return this;
    }
    public WorkShiftRequest<T> selectName(){
       selectProperty(WorkShift.NAME_PROPERTY);
       return this;
    }

    /**
     * fill the name with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  name) to fetch name property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WorkShiftRequest<T> unselectName(){
       unselectProperty(WorkShift.NAME_PROPERTY);
       return this;
    }
    public WorkShiftRequest<T> selectStartTime(){
       selectProperty(WorkShift.START_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the startTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  startTime) to fetch startTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WorkShiftRequest<T> unselectStartTime(){
       unselectProperty(WorkShift.START_TIME_PROPERTY);
       return this;
    }
    public WorkShiftRequest<T> selectEndTime(){
       selectProperty(WorkShift.END_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the endTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  endTime) to fetch endTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WorkShiftRequest<T> unselectEndTime(){
       unselectProperty(WorkShift.END_TIME_PROPERTY);
       return this;
    }
    public WorkShiftRequest<T> selectShiftDate(){
       selectProperty(WorkShift.SHIFT_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the shiftDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  shiftDate) to fetch shiftDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WorkShiftRequest<T> unselectShiftDate(){
       unselectProperty(WorkShift.SHIFT_DATE_PROPERTY);
       return this;
    }
    public WorkShiftRequest<T> selectCreatedAt(){
       selectProperty(WorkShift.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WorkShiftRequest<T> unselectCreatedAt(){
       unselectProperty(WorkShift.CREATED_AT_PROPERTY);
       return this;
    }
    public WorkShiftRequest<T> selectUpdatedAt(){
       selectProperty(WorkShift.UPDATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the updatedAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedAt) to fetch updatedAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WorkShiftRequest<T> unselectUpdatedAt(){
       unselectProperty(WorkShift.UPDATED_AT_PROPERTY);
       return this;
    }
    public WorkShiftRequest<T> selectVersion(){
       selectProperty(WorkShift.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WorkShiftRequest<T> unselectVersion(){
       unselectProperty(WorkShift.VERSION_PROPERTY);
       return this;
    }
    public WorkShiftRequest<T> selectWorkedHoursList(){
       return selectWorkedHoursListWith(Q.workedHourses().selectSelf());
    }

    public WorkShiftRequest<T> selectWorkedHoursListWith(WorkedHoursRequest workedHoursList){
       enhanceRelation(WorkShift.WORKED_HOURS_LIST_PROPERTY, workedHoursList);
       return this;
    }

    public WorkShiftRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(WorkShift.ID_PROPERTY, operator, values);
    }

    public WorkShiftRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public WorkShiftRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public WorkShiftRequest<T> filterByName(String... name){
      if (name == null || name.length == 0) {
        throw new IllegalArgumentException("filterByName parameter name cannot be empty");
      }
      return appendSearchCriteria(createNameCriteria(Operator.EQUAL, (Object[])name));
    }

    public WorkShiftRequest<T> withName(Operator operator, Object... values){
       return appendSearchCriteria(createNameCriteria(operator, values));
    }

    public WorkShiftRequest<T> withNameIsUnknown(){
       return withName(Operator.IS_NULL);
    }

    public WorkShiftRequest<T> withNameIsKnown(){
       return withName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(WorkShift.NAME_PROPERTY, operator, values);
    }

    public WorkShiftRequest<T> withNameGreaterThan(String name){
       return withName(Operator.GREATER_THAN, name);
    }

    public WorkShiftRequest<T> withNameGreaterThanOrEqualTo(String name){
       return withName(Operator.GREATER_THAN_OR_EQUAL, name);
    }

    public WorkShiftRequest<T> withNameLessThan(String name){
       return withName(Operator.LESS_THAN, name);
    }

    public WorkShiftRequest<T> withNameLessThanOrEqualTo(String name){
       return withName(Operator.LESS_THAN_OR_EQUAL, name);
    }

    public WorkShiftRequest<T> withNameBetween(String startOfName, String endOfName){
       return withName(Operator.BETWEEN, startOfName, endOfName);
    }
    public WorkShiftRequest<T> withNameStartingWith(String name){
       return withName(Operator.BEGIN_WITH, name);
    }
    public WorkShiftRequest<T> withNameContaining(String name){
       return withName(Operator.CONTAIN, name);
    }

    public WorkShiftRequest<T> withNameEndingWith(String name){
       return withName(Operator.END_WITH, name);
    }

    public WorkShiftRequest<T> withNameIs(String name){
       return withName(Operator.EQUAL, name);
    }

    public WorkShiftRequest<T> withNameSoundingLike(String name){
       return withName(Operator.SOUNDS_LIKE, name);
    }



    public WorkShiftRequest<T> filterByStartTime(LocalTime... startTime){
      if (startTime == null || startTime.length == 0) {
        throw new IllegalArgumentException("filterByStartTime parameter startTime cannot be empty");
      }
      return appendSearchCriteria(createStartTimeCriteria(Operator.EQUAL, (Object[])startTime));
    }

    public WorkShiftRequest<T> withStartTime(Operator operator, Object... values){
       return appendSearchCriteria(createStartTimeCriteria(operator, values));
    }

    public WorkShiftRequest<T> withStartTimeIsUnknown(){
       return withStartTime(Operator.IS_NULL);
    }

    public WorkShiftRequest<T> withStartTimeIsKnown(){
       return withStartTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStartTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(WorkShift.START_TIME_PROPERTY, operator, values);
    }

    public WorkShiftRequest<T> withStartTimeGreaterThan(LocalTime startTime){
       return withStartTime(Operator.GREATER_THAN, startTime);
    }

    public WorkShiftRequest<T> withStartTimeGreaterThanOrEqualTo(LocalTime startTime){
       return withStartTime(Operator.GREATER_THAN_OR_EQUAL, startTime);
    }

    public WorkShiftRequest<T> withStartTimeLessThan(LocalTime startTime){
       return withStartTime(Operator.LESS_THAN, startTime);
    }

    public WorkShiftRequest<T> withStartTimeLessThanOrEqualTo(LocalTime startTime){
       return withStartTime(Operator.LESS_THAN_OR_EQUAL, startTime);
    }

    public WorkShiftRequest<T> withStartTimeBetween(LocalTime startOfStartTime, LocalTime endOfStartTime){
       return withStartTime(Operator.BETWEEN, startOfStartTime, endOfStartTime);
    }


    public WorkShiftRequest<T> filterByEndTime(LocalTime... endTime){
      if (endTime == null || endTime.length == 0) {
        throw new IllegalArgumentException("filterByEndTime parameter endTime cannot be empty");
      }
      return appendSearchCriteria(createEndTimeCriteria(Operator.EQUAL, (Object[])endTime));
    }

    public WorkShiftRequest<T> withEndTime(Operator operator, Object... values){
       return appendSearchCriteria(createEndTimeCriteria(operator, values));
    }

    public WorkShiftRequest<T> withEndTimeIsUnknown(){
       return withEndTime(Operator.IS_NULL);
    }

    public WorkShiftRequest<T> withEndTimeIsKnown(){
       return withEndTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createEndTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(WorkShift.END_TIME_PROPERTY, operator, values);
    }

    public WorkShiftRequest<T> withEndTimeGreaterThan(LocalTime endTime){
       return withEndTime(Operator.GREATER_THAN, endTime);
    }

    public WorkShiftRequest<T> withEndTimeGreaterThanOrEqualTo(LocalTime endTime){
       return withEndTime(Operator.GREATER_THAN_OR_EQUAL, endTime);
    }

    public WorkShiftRequest<T> withEndTimeLessThan(LocalTime endTime){
       return withEndTime(Operator.LESS_THAN, endTime);
    }

    public WorkShiftRequest<T> withEndTimeLessThanOrEqualTo(LocalTime endTime){
       return withEndTime(Operator.LESS_THAN_OR_EQUAL, endTime);
    }

    public WorkShiftRequest<T> withEndTimeBetween(LocalTime startOfEndTime, LocalTime endOfEndTime){
       return withEndTime(Operator.BETWEEN, startOfEndTime, endOfEndTime);
    }


    public WorkShiftRequest<T> filterByShiftDate(LocalDate... shiftDate){
      if (shiftDate == null || shiftDate.length == 0) {
        throw new IllegalArgumentException("filterByShiftDate parameter shiftDate cannot be empty");
      }
      return appendSearchCriteria(createShiftDateCriteria(Operator.EQUAL, (Object[])shiftDate));
    }

    public WorkShiftRequest<T> withShiftDate(Operator operator, Object... values){
       return appendSearchCriteria(createShiftDateCriteria(operator, values));
    }

    public WorkShiftRequest<T> withShiftDateIsUnknown(){
       return withShiftDate(Operator.IS_NULL);
    }

    public WorkShiftRequest<T> withShiftDateIsKnown(){
       return withShiftDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createShiftDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(WorkShift.SHIFT_DATE_PROPERTY, operator, values);
    }

    public WorkShiftRequest<T> withShiftDateGreaterThan(LocalDate shiftDate){
       return withShiftDate(Operator.GREATER_THAN, shiftDate);
    }

    public WorkShiftRequest<T> withShiftDateGreaterThanOrEqualTo(LocalDate shiftDate){
       return withShiftDate(Operator.GREATER_THAN_OR_EQUAL, shiftDate);
    }

    public WorkShiftRequest<T> withShiftDateLessThan(LocalDate shiftDate){
       return withShiftDate(Operator.LESS_THAN, shiftDate);
    }

    public WorkShiftRequest<T> withShiftDateLessThanOrEqualTo(LocalDate shiftDate){
       return withShiftDate(Operator.LESS_THAN_OR_EQUAL, shiftDate);
    }

    public WorkShiftRequest<T> withShiftDateBetween(LocalDate startOfShiftDate, LocalDate endOfShiftDate){
       return withShiftDate(Operator.BETWEEN, startOfShiftDate, endOfShiftDate);
    }
    public WorkShiftRequest<T> withShiftDateBefore(LocalDate shiftDate){
       return withShiftDate(Operator.LESS_THAN, shiftDate);
    }

    public WorkShiftRequest<T> withShiftDateBefore(Date shiftDate){
       return withShiftDate(Operator.LESS_THAN, shiftDate);
    }

    public WorkShiftRequest<T> withShiftDateAfter(LocalDate shiftDate){
       return withShiftDate(Operator.GREATER_THAN, shiftDate);
    }

    public WorkShiftRequest<T> withShiftDateAfter(Date shiftDate){
       return withShiftDate(Operator.GREATER_THAN, shiftDate);
    }

    public WorkShiftRequest<T> withShiftDateBetween(Date startOfShiftDate, Date endOfShiftDate){
       return withShiftDate(Operator.BETWEEN, startOfShiftDate, endOfShiftDate);
    }




    public WorkShiftRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public WorkShiftRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public WorkShiftRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public WorkShiftRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(WorkShift.CREATED_AT_PROPERTY, operator, values);
    }

    public WorkShiftRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public WorkShiftRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public WorkShiftRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public WorkShiftRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public WorkShiftRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public WorkShiftRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public WorkShiftRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public WorkShiftRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public WorkShiftRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public WorkShiftRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }




    public WorkShiftRequest<T> filterByUpdatedAt(LocalDateTime... updatedAt){
      if (updatedAt == null || updatedAt.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedAt parameter updatedAt cannot be empty");
      }
      return appendSearchCriteria(createUpdatedAtCriteria(Operator.EQUAL, (Object[])updatedAt));
    }

    public WorkShiftRequest<T> withUpdatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedAtCriteria(operator, values));
    }

    public WorkShiftRequest<T> withUpdatedAtIsUnknown(){
       return withUpdatedAt(Operator.IS_NULL);
    }

    public WorkShiftRequest<T> withUpdatedAtIsKnown(){
       return withUpdatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(WorkShift.UPDATED_AT_PROPERTY, operator, values);
    }

    public WorkShiftRequest<T> withUpdatedAtGreaterThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public WorkShiftRequest<T> withUpdatedAtGreaterThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN_OR_EQUAL, updatedAt);
    }

    public WorkShiftRequest<T> withUpdatedAtLessThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public WorkShiftRequest<T> withUpdatedAtLessThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN_OR_EQUAL, updatedAt);
    }

    public WorkShiftRequest<T> withUpdatedAtBetween(LocalDateTime startOfUpdatedAt, LocalDateTime endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }
    public WorkShiftRequest<T> withUpdatedAtBefore(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public WorkShiftRequest<T> withUpdatedAtBefore(Date updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public WorkShiftRequest<T> withUpdatedAtAfter(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public WorkShiftRequest<T> withUpdatedAtAfter(Date updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public WorkShiftRequest<T> withUpdatedAtBetween(Date startOfUpdatedAt, Date endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }




    public WorkShiftRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public WorkShiftRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public WorkShiftRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public WorkShiftRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(WorkShift.VERSION_PROPERTY, operator, values);
    }

    public WorkShiftRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public WorkShiftRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public WorkShiftRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public WorkShiftRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public WorkShiftRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }

    public WorkShiftRequest<T> withWorkedHoursListMatching(WorkedHoursRequest workedHoursRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(WorkShift.ID_PROPERTY, workedHoursRequest, WorkedHours.SHIFT_PROPERTY));
    }

    public WorkShiftRequest<T> withoutWorkedHoursListMatching(WorkedHoursRequest workedHoursRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(WorkShift.ID_PROPERTY, workedHoursRequest, WorkedHours.SHIFT_PROPERTY)));
    }

    public WorkShiftRequest<T> haveWorkedHourses(){
        return withWorkedHoursListMatching(Q.workedHourses().unlimited());
    }

    public WorkShiftRequest<T> haveNoWorkedHourses(){
        return withoutWorkedHoursListMatching(Q.workedHourses().unlimited());
    }

    public WorkShiftRequest<T> count(){
        super.count();
        return this;
    }
    public WorkShiftRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public WorkShiftRequest<T> groupByWorkedHoursesWithDetails(WorkedHoursRequest subRequest){
       aggregate(WorkShift.WORKED_HOURS_LIST_PROPERTY, subRequest);
       return this;
    }

    public WorkShiftRequest<T> groupById(){
       groupBy(WorkShift.ID_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> groupByIdAs(String retName){
       groupBy(retName, WorkShift.ID_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, WorkShift.ID_PROPERTY, function);
       return this;
    }

    public WorkShiftRequest<T> groupByName(){
       groupBy(WorkShift.NAME_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> groupByNameAs(String retName){
       groupBy(retName, WorkShift.NAME_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> groupByNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, WorkShift.NAME_PROPERTY, function);
       return this;
    }

    public WorkShiftRequest<T> groupByStartTime(){
       groupBy(WorkShift.START_TIME_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> groupByStartTimeAs(String retName){
       groupBy(retName, WorkShift.START_TIME_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> groupByStartTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, WorkShift.START_TIME_PROPERTY, function);
       return this;
    }

    public WorkShiftRequest<T> groupByEndTime(){
       groupBy(WorkShift.END_TIME_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> groupByEndTimeAs(String retName){
       groupBy(retName, WorkShift.END_TIME_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> groupByEndTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, WorkShift.END_TIME_PROPERTY, function);
       return this;
    }

    public WorkShiftRequest<T> groupByShiftDate(){
       groupBy(WorkShift.SHIFT_DATE_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> groupByShiftDateAs(String retName){
       groupBy(retName, WorkShift.SHIFT_DATE_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> groupByShiftDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, WorkShift.SHIFT_DATE_PROPERTY, function);
       return this;
    }

    public WorkShiftRequest<T> groupByCreatedAt(){
       groupBy(WorkShift.CREATED_AT_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, WorkShift.CREATED_AT_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, WorkShift.CREATED_AT_PROPERTY, function);
       return this;
    }

    public WorkShiftRequest<T> groupByUpdatedAt(){
       groupBy(WorkShift.UPDATED_AT_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> groupByUpdatedAtAs(String retName){
       groupBy(retName, WorkShift.UPDATED_AT_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> groupByUpdatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, WorkShift.UPDATED_AT_PROPERTY, function);
       return this;
    }

    public WorkShiftRequest<T> groupByVersion(){
       groupBy(WorkShift.VERSION_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> groupByVersionAs(String retName){
       groupBy(retName, WorkShift.VERSION_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, WorkShift.VERSION_PROPERTY, function);
       return this;
    }



    public WorkShiftRequest<T> orderByIdAscending(){
       addOrderByAscending(WorkShift.ID_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> orderByIdDescending(){
       addOrderByDescending(WorkShift.ID_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> orderByNameAscending(){
       addOrderByAscending(WorkShift.NAME_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> orderByNameDescending(){
       addOrderByDescending(WorkShift.NAME_PROPERTY);
       return this;
    }
    public WorkShiftRequest<T> orderByNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(WorkShift.NAME_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> orderByNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(WorkShift.NAME_PROPERTY);
       return this;
    }
    public WorkShiftRequest<T> orderByStartTimeAscending(){
       addOrderByAscending(WorkShift.START_TIME_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> orderByStartTimeDescending(){
       addOrderByDescending(WorkShift.START_TIME_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> orderByEndTimeAscending(){
       addOrderByAscending(WorkShift.END_TIME_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> orderByEndTimeDescending(){
       addOrderByDescending(WorkShift.END_TIME_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> orderByShiftDateAscending(){
       addOrderByAscending(WorkShift.SHIFT_DATE_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> orderByShiftDateDescending(){
       addOrderByDescending(WorkShift.SHIFT_DATE_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(WorkShift.CREATED_AT_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(WorkShift.CREATED_AT_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> orderByUpdatedAtAscending(){
       addOrderByAscending(WorkShift.UPDATED_AT_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> orderByUpdatedAtDescending(){
       addOrderByDescending(WorkShift.UPDATED_AT_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> orderByVersionAscending(){
       addOrderByAscending(WorkShift.VERSION_PROPERTY);
       return this;
    }

    public WorkShiftRequest<T> orderByVersionDescending(){
       addOrderByDescending(WorkShift.VERSION_PROPERTY);
       return this;
    }


    public WorkShiftRequest<T> statsFromWorkedHoursesAs(String name, WorkedHoursRequest subRequest){
       return statsFromWorkedHoursesAs(name, subRequest, false);
    }

    public WorkShiftRequest<T> statsFromWorkedHoursesAs(String name, WorkedHoursRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(WorkedHours.SHIFT_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public WorkShiftRequest<T> statsFromWorkedHourses(WorkedHoursRequest subRequest){
       return statsFromWorkedHoursesAs(REFINEMENTS, subRequest);
    }
    public WorkShiftRequest<T> countWorkedHourses(){
        return countWorkedHoursesAs("Count");
    }

    public WorkShiftRequest<T> countWorkedHoursesAs(String name){
        return countWorkedHoursesWith(name, Q.workedHourses().unlimited());
    }

    public WorkShiftRequest<T> countWorkedHoursesWith(String name, WorkedHoursRequest subRequest){
        return statsFromWorkedHoursesAs(name, subRequest.count(), true);
    }



    /**
     * get topN records
     * @param topN  records number
     */
    public WorkShiftRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public WorkShiftRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public WorkShiftRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public WorkShiftRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public WorkShiftRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}