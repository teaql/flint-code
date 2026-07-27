package com.doublechaintech.movingcompanyservice.movingevent;

import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class MovingEventRequest<T extends MovingEvent> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public MovingEventRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public MovingEventRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public MovingEventRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public MovingEventRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public MovingEventRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public MovingEventRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public MovingEventRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (MovingEventRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public MovingEventRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public MovingEventRequest<T> matchingAnyOf(MovingEventRequest movingEvent){
        super.internalMatchAny(movingEvent);
        return this;
    }

    public MovingEventRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public MovingEventRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public MovingEventRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public MovingEventRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectCustomer().selectRoute().selectTimeSlot().selectStatus().selectScheduledDate().selectNotes().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public MovingEventRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public MovingEventRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectCustomer().selectRoute().selectTimeSlot().selectStatus().selectScheduledDate().selectNotes().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public MovingEventRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectCustomer().selectRoute().selectTimeSlot().selectStatus().selectScheduledDate().selectNotes().selectCreateTime().selectUpdateTime().selectVersion();
    }


    public MovingEventRequest<T> selectId(){
       selectProperty(MovingEvent.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MovingEventRequest<T> unselectId(){
       unselectProperty(MovingEvent.ID_PROPERTY);
       return this;
    }
    public MovingEventRequest<T> selectCustomer(){
       selectProperty(MovingEvent.CUSTOMER_PROPERTY);
       return this;
    }

    /**
     * fill the customer with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  customer) to fetch customer property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MovingEventRequest<T> unselectCustomer(){
       unselectProperty(MovingEvent.CUSTOMER_PROPERTY);
       return this;
    }
    public MovingEventRequest<T> selectRoute(){
       selectProperty(MovingEvent.ROUTE_PROPERTY);
       return this;
    }

    /**
     * fill the route with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  route) to fetch route property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MovingEventRequest<T> unselectRoute(){
       unselectProperty(MovingEvent.ROUTE_PROPERTY);
       return this;
    }
    public MovingEventRequest<T> selectTimeSlot(){
       selectProperty(MovingEvent.TIME_SLOT_PROPERTY);
       return this;
    }

    /**
     * fill the timeSlot with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  timeSlot) to fetch timeSlot property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MovingEventRequest<T> unselectTimeSlot(){
       unselectProperty(MovingEvent.TIME_SLOT_PROPERTY);
       return this;
    }
    public MovingEventRequest<T> selectStatus(){
       selectProperty(MovingEvent.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MovingEventRequest<T> unselectStatus(){
       unselectProperty(MovingEvent.STATUS_PROPERTY);
       return this;
    }
    public MovingEventRequest<T> selectScheduledDate(){
       selectProperty(MovingEvent.SCHEDULED_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the scheduledDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  scheduledDate) to fetch scheduledDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MovingEventRequest<T> unselectScheduledDate(){
       unselectProperty(MovingEvent.SCHEDULED_DATE_PROPERTY);
       return this;
    }
    public MovingEventRequest<T> selectNotes(){
       selectProperty(MovingEvent.NOTES_PROPERTY);
       return this;
    }

    /**
     * fill the notes with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  notes) to fetch notes property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MovingEventRequest<T> unselectNotes(){
       unselectProperty(MovingEvent.NOTES_PROPERTY);
       return this;
    }
    public MovingEventRequest<T> selectCreateTime(){
       selectProperty(MovingEvent.CREATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createTime) to fetch createTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MovingEventRequest<T> unselectCreateTime(){
       unselectProperty(MovingEvent.CREATE_TIME_PROPERTY);
       return this;
    }
    public MovingEventRequest<T> selectUpdateTime(){
       selectProperty(MovingEvent.UPDATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updateTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updateTime) to fetch updateTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MovingEventRequest<T> unselectUpdateTime(){
       unselectProperty(MovingEvent.UPDATE_TIME_PROPERTY);
       return this;
    }
    public MovingEventRequest<T> selectVersion(){
       selectProperty(MovingEvent.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MovingEventRequest<T> unselectVersion(){
       unselectProperty(MovingEvent.VERSION_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingEvent.ID_PROPERTY, operator, values);
    }

    public MovingEventRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public MovingEventRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public MovingEventRequest<T> filterByCustomer(String... customer){
      if (customer == null || customer.length == 0) {
        throw new IllegalArgumentException("filterByCustomer parameter customer cannot be empty");
      }
      return appendSearchCriteria(createCustomerCriteria(Operator.EQUAL, (Object[])customer));
    }

    public MovingEventRequest<T> withCustomer(Operator operator, Object... values){
       return appendSearchCriteria(createCustomerCriteria(operator, values));
    }

    public MovingEventRequest<T> withCustomerIsUnknown(){
       return withCustomer(Operator.IS_NULL);
    }

    public MovingEventRequest<T> withCustomerIsKnown(){
       return withCustomer(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCustomerCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingEvent.CUSTOMER_PROPERTY, operator, values);
    }

    public MovingEventRequest<T> withCustomerGreaterThan(String customer){
       return withCustomer(Operator.GREATER_THAN, customer);
    }

    public MovingEventRequest<T> withCustomerGreaterThanOrEqualTo(String customer){
       return withCustomer(Operator.GREATER_THAN_OR_EQUAL, customer);
    }

    public MovingEventRequest<T> withCustomerLessThan(String customer){
       return withCustomer(Operator.LESS_THAN, customer);
    }

    public MovingEventRequest<T> withCustomerLessThanOrEqualTo(String customer){
       return withCustomer(Operator.LESS_THAN_OR_EQUAL, customer);
    }

    public MovingEventRequest<T> withCustomerBetween(String startOfCustomer, String endOfCustomer){
       return withCustomer(Operator.BETWEEN, startOfCustomer, endOfCustomer);
    }
    public MovingEventRequest<T> withCustomerStartingWith(String customer){
       return withCustomer(Operator.BEGIN_WITH, customer);
    }
    public MovingEventRequest<T> withCustomerContaining(String customer){
       return withCustomer(Operator.CONTAIN, customer);
    }

    public MovingEventRequest<T> withCustomerEndingWith(String customer){
       return withCustomer(Operator.END_WITH, customer);
    }

    public MovingEventRequest<T> withCustomerIs(String customer){
       return withCustomer(Operator.EQUAL, customer);
    }

    public MovingEventRequest<T> withCustomerSoundingLike(String customer){
       return withCustomer(Operator.SOUNDS_LIKE, customer);
    }



    public MovingEventRequest<T> filterByRoute(String... route){
      if (route == null || route.length == 0) {
        throw new IllegalArgumentException("filterByRoute parameter route cannot be empty");
      }
      return appendSearchCriteria(createRouteCriteria(Operator.EQUAL, (Object[])route));
    }

    public MovingEventRequest<T> withRoute(Operator operator, Object... values){
       return appendSearchCriteria(createRouteCriteria(operator, values));
    }

    public MovingEventRequest<T> withRouteIsUnknown(){
       return withRoute(Operator.IS_NULL);
    }

    public MovingEventRequest<T> withRouteIsKnown(){
       return withRoute(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createRouteCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingEvent.ROUTE_PROPERTY, operator, values);
    }

    public MovingEventRequest<T> withRouteGreaterThan(String route){
       return withRoute(Operator.GREATER_THAN, route);
    }

    public MovingEventRequest<T> withRouteGreaterThanOrEqualTo(String route){
       return withRoute(Operator.GREATER_THAN_OR_EQUAL, route);
    }

    public MovingEventRequest<T> withRouteLessThan(String route){
       return withRoute(Operator.LESS_THAN, route);
    }

    public MovingEventRequest<T> withRouteLessThanOrEqualTo(String route){
       return withRoute(Operator.LESS_THAN_OR_EQUAL, route);
    }

    public MovingEventRequest<T> withRouteBetween(String startOfRoute, String endOfRoute){
       return withRoute(Operator.BETWEEN, startOfRoute, endOfRoute);
    }
    public MovingEventRequest<T> withRouteStartingWith(String route){
       return withRoute(Operator.BEGIN_WITH, route);
    }
    public MovingEventRequest<T> withRouteContaining(String route){
       return withRoute(Operator.CONTAIN, route);
    }

    public MovingEventRequest<T> withRouteEndingWith(String route){
       return withRoute(Operator.END_WITH, route);
    }

    public MovingEventRequest<T> withRouteIs(String route){
       return withRoute(Operator.EQUAL, route);
    }

    public MovingEventRequest<T> withRouteSoundingLike(String route){
       return withRoute(Operator.SOUNDS_LIKE, route);
    }



    public MovingEventRequest<T> filterByTimeSlot(String... timeSlot){
      if (timeSlot == null || timeSlot.length == 0) {
        throw new IllegalArgumentException("filterByTimeSlot parameter timeSlot cannot be empty");
      }
      return appendSearchCriteria(createTimeSlotCriteria(Operator.EQUAL, (Object[])timeSlot));
    }

    public MovingEventRequest<T> withTimeSlot(Operator operator, Object... values){
       return appendSearchCriteria(createTimeSlotCriteria(operator, values));
    }

    public MovingEventRequest<T> withTimeSlotIsUnknown(){
       return withTimeSlot(Operator.IS_NULL);
    }

    public MovingEventRequest<T> withTimeSlotIsKnown(){
       return withTimeSlot(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createTimeSlotCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingEvent.TIME_SLOT_PROPERTY, operator, values);
    }

    public MovingEventRequest<T> withTimeSlotGreaterThan(String timeSlot){
       return withTimeSlot(Operator.GREATER_THAN, timeSlot);
    }

    public MovingEventRequest<T> withTimeSlotGreaterThanOrEqualTo(String timeSlot){
       return withTimeSlot(Operator.GREATER_THAN_OR_EQUAL, timeSlot);
    }

    public MovingEventRequest<T> withTimeSlotLessThan(String timeSlot){
       return withTimeSlot(Operator.LESS_THAN, timeSlot);
    }

    public MovingEventRequest<T> withTimeSlotLessThanOrEqualTo(String timeSlot){
       return withTimeSlot(Operator.LESS_THAN_OR_EQUAL, timeSlot);
    }

    public MovingEventRequest<T> withTimeSlotBetween(String startOfTimeSlot, String endOfTimeSlot){
       return withTimeSlot(Operator.BETWEEN, startOfTimeSlot, endOfTimeSlot);
    }
    public MovingEventRequest<T> withTimeSlotStartingWith(String timeSlot){
       return withTimeSlot(Operator.BEGIN_WITH, timeSlot);
    }
    public MovingEventRequest<T> withTimeSlotContaining(String timeSlot){
       return withTimeSlot(Operator.CONTAIN, timeSlot);
    }

    public MovingEventRequest<T> withTimeSlotEndingWith(String timeSlot){
       return withTimeSlot(Operator.END_WITH, timeSlot);
    }

    public MovingEventRequest<T> withTimeSlotIs(String timeSlot){
       return withTimeSlot(Operator.EQUAL, timeSlot);
    }

    public MovingEventRequest<T> withTimeSlotSoundingLike(String timeSlot){
       return withTimeSlot(Operator.SOUNDS_LIKE, timeSlot);
    }



    public MovingEventRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public MovingEventRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public MovingEventRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public MovingEventRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingEvent.STATUS_PROPERTY, operator, values);
    }

    public MovingEventRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public MovingEventRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public MovingEventRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public MovingEventRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public MovingEventRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public MovingEventRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public MovingEventRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public MovingEventRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public MovingEventRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public MovingEventRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public MovingEventRequest<T> filterByScheduledDate(LocalDate... scheduledDate){
      if (scheduledDate == null || scheduledDate.length == 0) {
        throw new IllegalArgumentException("filterByScheduledDate parameter scheduledDate cannot be empty");
      }
      return appendSearchCriteria(createScheduledDateCriteria(Operator.EQUAL, (Object[])scheduledDate));
    }

    public MovingEventRequest<T> withScheduledDate(Operator operator, Object... values){
       return appendSearchCriteria(createScheduledDateCriteria(operator, values));
    }

    public MovingEventRequest<T> withScheduledDateIsUnknown(){
       return withScheduledDate(Operator.IS_NULL);
    }

    public MovingEventRequest<T> withScheduledDateIsKnown(){
       return withScheduledDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createScheduledDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingEvent.SCHEDULED_DATE_PROPERTY, operator, values);
    }

    public MovingEventRequest<T> withScheduledDateGreaterThan(LocalDate scheduledDate){
       return withScheduledDate(Operator.GREATER_THAN, scheduledDate);
    }

    public MovingEventRequest<T> withScheduledDateGreaterThanOrEqualTo(LocalDate scheduledDate){
       return withScheduledDate(Operator.GREATER_THAN_OR_EQUAL, scheduledDate);
    }

    public MovingEventRequest<T> withScheduledDateLessThan(LocalDate scheduledDate){
       return withScheduledDate(Operator.LESS_THAN, scheduledDate);
    }

    public MovingEventRequest<T> withScheduledDateLessThanOrEqualTo(LocalDate scheduledDate){
       return withScheduledDate(Operator.LESS_THAN_OR_EQUAL, scheduledDate);
    }

    public MovingEventRequest<T> withScheduledDateBetween(LocalDate startOfScheduledDate, LocalDate endOfScheduledDate){
       return withScheduledDate(Operator.BETWEEN, startOfScheduledDate, endOfScheduledDate);
    }
    public MovingEventRequest<T> withScheduledDateBefore(LocalDate scheduledDate){
       return withScheduledDate(Operator.LESS_THAN, scheduledDate);
    }

    public MovingEventRequest<T> withScheduledDateBefore(Date scheduledDate){
       return withScheduledDate(Operator.LESS_THAN, scheduledDate);
    }

    public MovingEventRequest<T> withScheduledDateAfter(LocalDate scheduledDate){
       return withScheduledDate(Operator.GREATER_THAN, scheduledDate);
    }

    public MovingEventRequest<T> withScheduledDateAfter(Date scheduledDate){
       return withScheduledDate(Operator.GREATER_THAN, scheduledDate);
    }

    public MovingEventRequest<T> withScheduledDateBetween(Date startOfScheduledDate, Date endOfScheduledDate){
       return withScheduledDate(Operator.BETWEEN, startOfScheduledDate, endOfScheduledDate);
    }




    public MovingEventRequest<T> filterByNotes(String... notes){
      if (notes == null || notes.length == 0) {
        throw new IllegalArgumentException("filterByNotes parameter notes cannot be empty");
      }
      return appendSearchCriteria(createNotesCriteria(Operator.EQUAL, (Object[])notes));
    }

    public MovingEventRequest<T> withNotes(Operator operator, Object... values){
       return appendSearchCriteria(createNotesCriteria(operator, values));
    }

    public MovingEventRequest<T> withNotesIsUnknown(){
       return withNotes(Operator.IS_NULL);
    }

    public MovingEventRequest<T> withNotesIsKnown(){
       return withNotes(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNotesCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingEvent.NOTES_PROPERTY, operator, values);
    }

    public MovingEventRequest<T> withNotesGreaterThan(String notes){
       return withNotes(Operator.GREATER_THAN, notes);
    }

    public MovingEventRequest<T> withNotesGreaterThanOrEqualTo(String notes){
       return withNotes(Operator.GREATER_THAN_OR_EQUAL, notes);
    }

    public MovingEventRequest<T> withNotesLessThan(String notes){
       return withNotes(Operator.LESS_THAN, notes);
    }

    public MovingEventRequest<T> withNotesLessThanOrEqualTo(String notes){
       return withNotes(Operator.LESS_THAN_OR_EQUAL, notes);
    }

    public MovingEventRequest<T> withNotesBetween(String startOfNotes, String endOfNotes){
       return withNotes(Operator.BETWEEN, startOfNotes, endOfNotes);
    }
    public MovingEventRequest<T> withNotesStartingWith(String notes){
       return withNotes(Operator.BEGIN_WITH, notes);
    }
    public MovingEventRequest<T> withNotesContaining(String notes){
       return withNotes(Operator.CONTAIN, notes);
    }

    public MovingEventRequest<T> withNotesEndingWith(String notes){
       return withNotes(Operator.END_WITH, notes);
    }

    public MovingEventRequest<T> withNotesIs(String notes){
       return withNotes(Operator.EQUAL, notes);
    }

    public MovingEventRequest<T> withNotesSoundingLike(String notes){
       return withNotes(Operator.SOUNDS_LIKE, notes);
    }



    public MovingEventRequest<T> filterByCreateTime(LocalDateTime... createTime){
      if (createTime == null || createTime.length == 0) {
        throw new IllegalArgumentException("filterByCreateTime parameter createTime cannot be empty");
      }
      return appendSearchCriteria(createCreateTimeCriteria(Operator.EQUAL, (Object[])createTime));
    }

    public MovingEventRequest<T> withCreateTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreateTimeCriteria(operator, values));
    }

    public MovingEventRequest<T> withCreateTimeIsUnknown(){
       return withCreateTime(Operator.IS_NULL);
    }

    public MovingEventRequest<T> withCreateTimeIsKnown(){
       return withCreateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingEvent.CREATE_TIME_PROPERTY, operator, values);
    }

    public MovingEventRequest<T> withCreateTimeGreaterThan(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public MovingEventRequest<T> withCreateTimeGreaterThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN_OR_EQUAL, createTime);
    }

    public MovingEventRequest<T> withCreateTimeLessThan(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public MovingEventRequest<T> withCreateTimeLessThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN_OR_EQUAL, createTime);
    }

    public MovingEventRequest<T> withCreateTimeBetween(LocalDateTime startOfCreateTime, LocalDateTime endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }
    public MovingEventRequest<T> withCreateTimeBefore(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public MovingEventRequest<T> withCreateTimeBefore(Date createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public MovingEventRequest<T> withCreateTimeAfter(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public MovingEventRequest<T> withCreateTimeAfter(Date createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public MovingEventRequest<T> withCreateTimeBetween(Date startOfCreateTime, Date endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }




    public MovingEventRequest<T> filterByUpdateTime(LocalDateTime... updateTime){
      if (updateTime == null || updateTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdateTime parameter updateTime cannot be empty");
      }
      return appendSearchCriteria(createUpdateTimeCriteria(Operator.EQUAL, (Object[])updateTime));
    }

    public MovingEventRequest<T> withUpdateTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdateTimeCriteria(operator, values));
    }

    public MovingEventRequest<T> withUpdateTimeIsUnknown(){
       return withUpdateTime(Operator.IS_NULL);
    }

    public MovingEventRequest<T> withUpdateTimeIsKnown(){
       return withUpdateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingEvent.UPDATE_TIME_PROPERTY, operator, values);
    }

    public MovingEventRequest<T> withUpdateTimeGreaterThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public MovingEventRequest<T> withUpdateTimeGreaterThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN_OR_EQUAL, updateTime);
    }

    public MovingEventRequest<T> withUpdateTimeLessThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public MovingEventRequest<T> withUpdateTimeLessThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN_OR_EQUAL, updateTime);
    }

    public MovingEventRequest<T> withUpdateTimeBetween(LocalDateTime startOfUpdateTime, LocalDateTime endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }
    public MovingEventRequest<T> withUpdateTimeBefore(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public MovingEventRequest<T> withUpdateTimeBefore(Date updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public MovingEventRequest<T> withUpdateTimeAfter(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public MovingEventRequest<T> withUpdateTimeAfter(Date updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public MovingEventRequest<T> withUpdateTimeBetween(Date startOfUpdateTime, Date endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }




    public MovingEventRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public MovingEventRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public MovingEventRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public MovingEventRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingEvent.VERSION_PROPERTY, operator, values);
    }

    public MovingEventRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public MovingEventRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public MovingEventRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public MovingEventRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public MovingEventRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public MovingEventRequest<T> count(){
        super.count();
        return this;
    }
    public MovingEventRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }

    public MovingEventRequest<T> groupById(){
       groupBy(MovingEvent.ID_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> groupByIdAs(String retName){
       groupBy(retName, MovingEvent.ID_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingEvent.ID_PROPERTY, function);
       return this;
    }

    public MovingEventRequest<T> groupByCustomer(){
       groupBy(MovingEvent.CUSTOMER_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> groupByCustomerAs(String retName){
       groupBy(retName, MovingEvent.CUSTOMER_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> groupByCustomerWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingEvent.CUSTOMER_PROPERTY, function);
       return this;
    }

    public MovingEventRequest<T> groupByRoute(){
       groupBy(MovingEvent.ROUTE_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> groupByRouteAs(String retName){
       groupBy(retName, MovingEvent.ROUTE_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> groupByRouteWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingEvent.ROUTE_PROPERTY, function);
       return this;
    }

    public MovingEventRequest<T> groupByTimeSlot(){
       groupBy(MovingEvent.TIME_SLOT_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> groupByTimeSlotAs(String retName){
       groupBy(retName, MovingEvent.TIME_SLOT_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> groupByTimeSlotWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingEvent.TIME_SLOT_PROPERTY, function);
       return this;
    }

    public MovingEventRequest<T> groupByStatus(){
       groupBy(MovingEvent.STATUS_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> groupByStatusAs(String retName){
       groupBy(retName, MovingEvent.STATUS_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingEvent.STATUS_PROPERTY, function);
       return this;
    }

    public MovingEventRequest<T> groupByScheduledDate(){
       groupBy(MovingEvent.SCHEDULED_DATE_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> groupByScheduledDateAs(String retName){
       groupBy(retName, MovingEvent.SCHEDULED_DATE_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> groupByScheduledDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingEvent.SCHEDULED_DATE_PROPERTY, function);
       return this;
    }

    public MovingEventRequest<T> groupByNotes(){
       groupBy(MovingEvent.NOTES_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> groupByNotesAs(String retName){
       groupBy(retName, MovingEvent.NOTES_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> groupByNotesWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingEvent.NOTES_PROPERTY, function);
       return this;
    }

    public MovingEventRequest<T> groupByCreateTime(){
       groupBy(MovingEvent.CREATE_TIME_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> groupByCreateTimeAs(String retName){
       groupBy(retName, MovingEvent.CREATE_TIME_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> groupByCreateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingEvent.CREATE_TIME_PROPERTY, function);
       return this;
    }

    public MovingEventRequest<T> groupByUpdateTime(){
       groupBy(MovingEvent.UPDATE_TIME_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> groupByUpdateTimeAs(String retName){
       groupBy(retName, MovingEvent.UPDATE_TIME_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> groupByUpdateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingEvent.UPDATE_TIME_PROPERTY, function);
       return this;
    }

    public MovingEventRequest<T> groupByVersion(){
       groupBy(MovingEvent.VERSION_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> groupByVersionAs(String retName){
       groupBy(retName, MovingEvent.VERSION_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingEvent.VERSION_PROPERTY, function);
       return this;
    }



    public MovingEventRequest<T> orderByIdAscending(){
       addOrderByAscending(MovingEvent.ID_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> orderByIdDescending(){
       addOrderByDescending(MovingEvent.ID_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> orderByCustomerAscending(){
       addOrderByAscending(MovingEvent.CUSTOMER_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> orderByCustomerDescending(){
       addOrderByDescending(MovingEvent.CUSTOMER_PROPERTY);
       return this;
    }
    public MovingEventRequest<T> orderByCustomerAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(MovingEvent.CUSTOMER_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> orderByCustomerDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(MovingEvent.CUSTOMER_PROPERTY);
       return this;
    }
    public MovingEventRequest<T> orderByRouteAscending(){
       addOrderByAscending(MovingEvent.ROUTE_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> orderByRouteDescending(){
       addOrderByDescending(MovingEvent.ROUTE_PROPERTY);
       return this;
    }
    public MovingEventRequest<T> orderByRouteAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(MovingEvent.ROUTE_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> orderByRouteDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(MovingEvent.ROUTE_PROPERTY);
       return this;
    }
    public MovingEventRequest<T> orderByTimeSlotAscending(){
       addOrderByAscending(MovingEvent.TIME_SLOT_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> orderByTimeSlotDescending(){
       addOrderByDescending(MovingEvent.TIME_SLOT_PROPERTY);
       return this;
    }
    public MovingEventRequest<T> orderByTimeSlotAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(MovingEvent.TIME_SLOT_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> orderByTimeSlotDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(MovingEvent.TIME_SLOT_PROPERTY);
       return this;
    }
    public MovingEventRequest<T> orderByStatusAscending(){
       addOrderByAscending(MovingEvent.STATUS_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> orderByStatusDescending(){
       addOrderByDescending(MovingEvent.STATUS_PROPERTY);
       return this;
    }
    public MovingEventRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(MovingEvent.STATUS_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(MovingEvent.STATUS_PROPERTY);
       return this;
    }
    public MovingEventRequest<T> orderByScheduledDateAscending(){
       addOrderByAscending(MovingEvent.SCHEDULED_DATE_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> orderByScheduledDateDescending(){
       addOrderByDescending(MovingEvent.SCHEDULED_DATE_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> orderByNotesAscending(){
       addOrderByAscending(MovingEvent.NOTES_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> orderByNotesDescending(){
       addOrderByDescending(MovingEvent.NOTES_PROPERTY);
       return this;
    }
    public MovingEventRequest<T> orderByNotesAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(MovingEvent.NOTES_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> orderByNotesDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(MovingEvent.NOTES_PROPERTY);
       return this;
    }
    public MovingEventRequest<T> orderByCreateTimeAscending(){
       addOrderByAscending(MovingEvent.CREATE_TIME_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> orderByCreateTimeDescending(){
       addOrderByDescending(MovingEvent.CREATE_TIME_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> orderByUpdateTimeAscending(){
       addOrderByAscending(MovingEvent.UPDATE_TIME_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> orderByUpdateTimeDescending(){
       addOrderByDescending(MovingEvent.UPDATE_TIME_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> orderByVersionAscending(){
       addOrderByAscending(MovingEvent.VERSION_PROPERTY);
       return this;
    }

    public MovingEventRequest<T> orderByVersionDescending(){
       addOrderByDescending(MovingEvent.VERSION_PROPERTY);
       return this;
    }





    /**
     * get topN records
     * @param topN  records number
     */
    public MovingEventRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public MovingEventRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public MovingEventRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public MovingEventRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public MovingEventRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}