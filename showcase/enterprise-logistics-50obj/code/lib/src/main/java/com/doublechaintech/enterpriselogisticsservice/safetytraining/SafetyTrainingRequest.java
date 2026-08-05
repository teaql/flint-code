package com.doublechaintech.enterpriselogisticsservice.safetytraining;

import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class SafetyTrainingRequest<T extends SafetyTraining> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public SafetyTrainingRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public SafetyTrainingRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public SafetyTrainingRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public SafetyTrainingRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public SafetyTrainingRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public SafetyTrainingRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public SafetyTrainingRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (SafetyTrainingRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public SafetyTrainingRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public SafetyTrainingRequest<T> matchingAnyOf(SafetyTrainingRequest safetyTraining){
        super.internalMatchAny(safetyTraining);
        return this;
    }

    public SafetyTrainingRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public SafetyTrainingRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public SafetyTrainingRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public SafetyTrainingRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectTitle().selectDescription().selectDurationHours().selectCompletionDate().selectStatus().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public SafetyTrainingRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public SafetyTrainingRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectTitle().selectDescription().selectDurationHours().selectCompletionDate().selectStatus().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public SafetyTrainingRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectTitle().selectDescription().selectDurationHours().selectCompletionDate().selectStatus().selectCreatedAt().selectUpdatedAt().selectVersion();
    }


    public SafetyTrainingRequest<T> selectId(){
       selectProperty(SafetyTraining.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SafetyTrainingRequest<T> unselectId(){
       unselectProperty(SafetyTraining.ID_PROPERTY);
       return this;
    }
    public SafetyTrainingRequest<T> selectTitle(){
       selectProperty(SafetyTraining.TITLE_PROPERTY);
       return this;
    }

    /**
     * fill the title with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  title) to fetch title property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SafetyTrainingRequest<T> unselectTitle(){
       unselectProperty(SafetyTraining.TITLE_PROPERTY);
       return this;
    }
    public SafetyTrainingRequest<T> selectDescription(){
       selectProperty(SafetyTraining.DESCRIPTION_PROPERTY);
       return this;
    }

    /**
     * fill the description with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  description) to fetch description property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SafetyTrainingRequest<T> unselectDescription(){
       unselectProperty(SafetyTraining.DESCRIPTION_PROPERTY);
       return this;
    }
    public SafetyTrainingRequest<T> selectDurationHours(){
       selectProperty(SafetyTraining.DURATION_HOURS_PROPERTY);
       return this;
    }

    /**
     * fill the durationHours with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  durationHours) to fetch durationHours property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SafetyTrainingRequest<T> unselectDurationHours(){
       unselectProperty(SafetyTraining.DURATION_HOURS_PROPERTY);
       return this;
    }
    public SafetyTrainingRequest<T> selectCompletionDate(){
       selectProperty(SafetyTraining.COMPLETION_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the completionDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  completionDate) to fetch completionDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SafetyTrainingRequest<T> unselectCompletionDate(){
       unselectProperty(SafetyTraining.COMPLETION_DATE_PROPERTY);
       return this;
    }
    public SafetyTrainingRequest<T> selectStatus(){
       selectProperty(SafetyTraining.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SafetyTrainingRequest<T> unselectStatus(){
       unselectProperty(SafetyTraining.STATUS_PROPERTY);
       return this;
    }
    public SafetyTrainingRequest<T> selectCreatedAt(){
       selectProperty(SafetyTraining.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SafetyTrainingRequest<T> unselectCreatedAt(){
       unselectProperty(SafetyTraining.CREATED_AT_PROPERTY);
       return this;
    }
    public SafetyTrainingRequest<T> selectUpdatedAt(){
       selectProperty(SafetyTraining.UPDATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the updatedAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedAt) to fetch updatedAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SafetyTrainingRequest<T> unselectUpdatedAt(){
       unselectProperty(SafetyTraining.UPDATED_AT_PROPERTY);
       return this;
    }
    public SafetyTrainingRequest<T> selectVersion(){
       selectProperty(SafetyTraining.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SafetyTrainingRequest<T> unselectVersion(){
       unselectProperty(SafetyTraining.VERSION_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SafetyTraining.ID_PROPERTY, operator, values);
    }

    public SafetyTrainingRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public SafetyTrainingRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public SafetyTrainingRequest<T> filterByTitle(String... title){
      if (title == null || title.length == 0) {
        throw new IllegalArgumentException("filterByTitle parameter title cannot be empty");
      }
      return appendSearchCriteria(createTitleCriteria(Operator.EQUAL, (Object[])title));
    }

    public SafetyTrainingRequest<T> withTitle(Operator operator, Object... values){
       return appendSearchCriteria(createTitleCriteria(operator, values));
    }

    public SafetyTrainingRequest<T> withTitleIsUnknown(){
       return withTitle(Operator.IS_NULL);
    }

    public SafetyTrainingRequest<T> withTitleIsKnown(){
       return withTitle(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createTitleCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SafetyTraining.TITLE_PROPERTY, operator, values);
    }

    public SafetyTrainingRequest<T> withTitleGreaterThan(String title){
       return withTitle(Operator.GREATER_THAN, title);
    }

    public SafetyTrainingRequest<T> withTitleGreaterThanOrEqualTo(String title){
       return withTitle(Operator.GREATER_THAN_OR_EQUAL, title);
    }

    public SafetyTrainingRequest<T> withTitleLessThan(String title){
       return withTitle(Operator.LESS_THAN, title);
    }

    public SafetyTrainingRequest<T> withTitleLessThanOrEqualTo(String title){
       return withTitle(Operator.LESS_THAN_OR_EQUAL, title);
    }

    public SafetyTrainingRequest<T> withTitleBetween(String startOfTitle, String endOfTitle){
       return withTitle(Operator.BETWEEN, startOfTitle, endOfTitle);
    }
    public SafetyTrainingRequest<T> withTitleStartingWith(String title){
       return withTitle(Operator.BEGIN_WITH, title);
    }
    public SafetyTrainingRequest<T> withTitleContaining(String title){
       return withTitle(Operator.CONTAIN, title);
    }

    public SafetyTrainingRequest<T> withTitleEndingWith(String title){
       return withTitle(Operator.END_WITH, title);
    }

    public SafetyTrainingRequest<T> withTitleIs(String title){
       return withTitle(Operator.EQUAL, title);
    }

    public SafetyTrainingRequest<T> withTitleSoundingLike(String title){
       return withTitle(Operator.SOUNDS_LIKE, title);
    }



    public SafetyTrainingRequest<T> filterByDescription(String... description){
      if (description == null || description.length == 0) {
        throw new IllegalArgumentException("filterByDescription parameter description cannot be empty");
      }
      return appendSearchCriteria(createDescriptionCriteria(Operator.EQUAL, (Object[])description));
    }

    public SafetyTrainingRequest<T> withDescription(Operator operator, Object... values){
       return appendSearchCriteria(createDescriptionCriteria(operator, values));
    }

    public SafetyTrainingRequest<T> withDescriptionIsUnknown(){
       return withDescription(Operator.IS_NULL);
    }

    public SafetyTrainingRequest<T> withDescriptionIsKnown(){
       return withDescription(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDescriptionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SafetyTraining.DESCRIPTION_PROPERTY, operator, values);
    }

    public SafetyTrainingRequest<T> withDescriptionGreaterThan(String description){
       return withDescription(Operator.GREATER_THAN, description);
    }

    public SafetyTrainingRequest<T> withDescriptionGreaterThanOrEqualTo(String description){
       return withDescription(Operator.GREATER_THAN_OR_EQUAL, description);
    }

    public SafetyTrainingRequest<T> withDescriptionLessThan(String description){
       return withDescription(Operator.LESS_THAN, description);
    }

    public SafetyTrainingRequest<T> withDescriptionLessThanOrEqualTo(String description){
       return withDescription(Operator.LESS_THAN_OR_EQUAL, description);
    }

    public SafetyTrainingRequest<T> withDescriptionBetween(String startOfDescription, String endOfDescription){
       return withDescription(Operator.BETWEEN, startOfDescription, endOfDescription);
    }
    public SafetyTrainingRequest<T> withDescriptionStartingWith(String description){
       return withDescription(Operator.BEGIN_WITH, description);
    }
    public SafetyTrainingRequest<T> withDescriptionContaining(String description){
       return withDescription(Operator.CONTAIN, description);
    }

    public SafetyTrainingRequest<T> withDescriptionEndingWith(String description){
       return withDescription(Operator.END_WITH, description);
    }

    public SafetyTrainingRequest<T> withDescriptionIs(String description){
       return withDescription(Operator.EQUAL, description);
    }

    public SafetyTrainingRequest<T> withDescriptionSoundingLike(String description){
       return withDescription(Operator.SOUNDS_LIKE, description);
    }



    public SafetyTrainingRequest<T> filterByDurationHours(String... durationHours){
      if (durationHours == null || durationHours.length == 0) {
        throw new IllegalArgumentException("filterByDurationHours parameter durationHours cannot be empty");
      }
      return appendSearchCriteria(createDurationHoursCriteria(Operator.EQUAL, (Object[])durationHours));
    }

    public SafetyTrainingRequest<T> withDurationHours(Operator operator, Object... values){
       return appendSearchCriteria(createDurationHoursCriteria(operator, values));
    }

    public SafetyTrainingRequest<T> withDurationHoursIsUnknown(){
       return withDurationHours(Operator.IS_NULL);
    }

    public SafetyTrainingRequest<T> withDurationHoursIsKnown(){
       return withDurationHours(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDurationHoursCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SafetyTraining.DURATION_HOURS_PROPERTY, operator, values);
    }

    public SafetyTrainingRequest<T> withDurationHoursGreaterThan(String durationHours){
       return withDurationHours(Operator.GREATER_THAN, durationHours);
    }

    public SafetyTrainingRequest<T> withDurationHoursGreaterThanOrEqualTo(String durationHours){
       return withDurationHours(Operator.GREATER_THAN_OR_EQUAL, durationHours);
    }

    public SafetyTrainingRequest<T> withDurationHoursLessThan(String durationHours){
       return withDurationHours(Operator.LESS_THAN, durationHours);
    }

    public SafetyTrainingRequest<T> withDurationHoursLessThanOrEqualTo(String durationHours){
       return withDurationHours(Operator.LESS_THAN_OR_EQUAL, durationHours);
    }

    public SafetyTrainingRequest<T> withDurationHoursBetween(String startOfDurationHours, String endOfDurationHours){
       return withDurationHours(Operator.BETWEEN, startOfDurationHours, endOfDurationHours);
    }
    public SafetyTrainingRequest<T> withDurationHoursStartingWith(String durationHours){
       return withDurationHours(Operator.BEGIN_WITH, durationHours);
    }
    public SafetyTrainingRequest<T> withDurationHoursContaining(String durationHours){
       return withDurationHours(Operator.CONTAIN, durationHours);
    }

    public SafetyTrainingRequest<T> withDurationHoursEndingWith(String durationHours){
       return withDurationHours(Operator.END_WITH, durationHours);
    }

    public SafetyTrainingRequest<T> withDurationHoursIs(String durationHours){
       return withDurationHours(Operator.EQUAL, durationHours);
    }

    public SafetyTrainingRequest<T> withDurationHoursSoundingLike(String durationHours){
       return withDurationHours(Operator.SOUNDS_LIKE, durationHours);
    }



    public SafetyTrainingRequest<T> filterByCompletionDate(LocalDate... completionDate){
      if (completionDate == null || completionDate.length == 0) {
        throw new IllegalArgumentException("filterByCompletionDate parameter completionDate cannot be empty");
      }
      return appendSearchCriteria(createCompletionDateCriteria(Operator.EQUAL, (Object[])completionDate));
    }

    public SafetyTrainingRequest<T> withCompletionDate(Operator operator, Object... values){
       return appendSearchCriteria(createCompletionDateCriteria(operator, values));
    }

    public SafetyTrainingRequest<T> withCompletionDateIsUnknown(){
       return withCompletionDate(Operator.IS_NULL);
    }

    public SafetyTrainingRequest<T> withCompletionDateIsKnown(){
       return withCompletionDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCompletionDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SafetyTraining.COMPLETION_DATE_PROPERTY, operator, values);
    }

    public SafetyTrainingRequest<T> withCompletionDateGreaterThan(LocalDate completionDate){
       return withCompletionDate(Operator.GREATER_THAN, completionDate);
    }

    public SafetyTrainingRequest<T> withCompletionDateGreaterThanOrEqualTo(LocalDate completionDate){
       return withCompletionDate(Operator.GREATER_THAN_OR_EQUAL, completionDate);
    }

    public SafetyTrainingRequest<T> withCompletionDateLessThan(LocalDate completionDate){
       return withCompletionDate(Operator.LESS_THAN, completionDate);
    }

    public SafetyTrainingRequest<T> withCompletionDateLessThanOrEqualTo(LocalDate completionDate){
       return withCompletionDate(Operator.LESS_THAN_OR_EQUAL, completionDate);
    }

    public SafetyTrainingRequest<T> withCompletionDateBetween(LocalDate startOfCompletionDate, LocalDate endOfCompletionDate){
       return withCompletionDate(Operator.BETWEEN, startOfCompletionDate, endOfCompletionDate);
    }
    public SafetyTrainingRequest<T> withCompletionDateBefore(LocalDate completionDate){
       return withCompletionDate(Operator.LESS_THAN, completionDate);
    }

    public SafetyTrainingRequest<T> withCompletionDateBefore(Date completionDate){
       return withCompletionDate(Operator.LESS_THAN, completionDate);
    }

    public SafetyTrainingRequest<T> withCompletionDateAfter(LocalDate completionDate){
       return withCompletionDate(Operator.GREATER_THAN, completionDate);
    }

    public SafetyTrainingRequest<T> withCompletionDateAfter(Date completionDate){
       return withCompletionDate(Operator.GREATER_THAN, completionDate);
    }

    public SafetyTrainingRequest<T> withCompletionDateBetween(Date startOfCompletionDate, Date endOfCompletionDate){
       return withCompletionDate(Operator.BETWEEN, startOfCompletionDate, endOfCompletionDate);
    }




    public SafetyTrainingRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public SafetyTrainingRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public SafetyTrainingRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public SafetyTrainingRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SafetyTraining.STATUS_PROPERTY, operator, values);
    }

    public SafetyTrainingRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public SafetyTrainingRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public SafetyTrainingRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public SafetyTrainingRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public SafetyTrainingRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public SafetyTrainingRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public SafetyTrainingRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public SafetyTrainingRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public SafetyTrainingRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public SafetyTrainingRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public SafetyTrainingRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public SafetyTrainingRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public SafetyTrainingRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public SafetyTrainingRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SafetyTraining.CREATED_AT_PROPERTY, operator, values);
    }

    public SafetyTrainingRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public SafetyTrainingRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public SafetyTrainingRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public SafetyTrainingRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public SafetyTrainingRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public SafetyTrainingRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public SafetyTrainingRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public SafetyTrainingRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public SafetyTrainingRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public SafetyTrainingRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }




    public SafetyTrainingRequest<T> filterByUpdatedAt(LocalDateTime... updatedAt){
      if (updatedAt == null || updatedAt.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedAt parameter updatedAt cannot be empty");
      }
      return appendSearchCriteria(createUpdatedAtCriteria(Operator.EQUAL, (Object[])updatedAt));
    }

    public SafetyTrainingRequest<T> withUpdatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedAtCriteria(operator, values));
    }

    public SafetyTrainingRequest<T> withUpdatedAtIsUnknown(){
       return withUpdatedAt(Operator.IS_NULL);
    }

    public SafetyTrainingRequest<T> withUpdatedAtIsKnown(){
       return withUpdatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SafetyTraining.UPDATED_AT_PROPERTY, operator, values);
    }

    public SafetyTrainingRequest<T> withUpdatedAtGreaterThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public SafetyTrainingRequest<T> withUpdatedAtGreaterThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN_OR_EQUAL, updatedAt);
    }

    public SafetyTrainingRequest<T> withUpdatedAtLessThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public SafetyTrainingRequest<T> withUpdatedAtLessThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN_OR_EQUAL, updatedAt);
    }

    public SafetyTrainingRequest<T> withUpdatedAtBetween(LocalDateTime startOfUpdatedAt, LocalDateTime endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }
    public SafetyTrainingRequest<T> withUpdatedAtBefore(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public SafetyTrainingRequest<T> withUpdatedAtBefore(Date updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public SafetyTrainingRequest<T> withUpdatedAtAfter(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public SafetyTrainingRequest<T> withUpdatedAtAfter(Date updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public SafetyTrainingRequest<T> withUpdatedAtBetween(Date startOfUpdatedAt, Date endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }




    public SafetyTrainingRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public SafetyTrainingRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public SafetyTrainingRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public SafetyTrainingRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SafetyTraining.VERSION_PROPERTY, operator, values);
    }

    public SafetyTrainingRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public SafetyTrainingRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public SafetyTrainingRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public SafetyTrainingRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public SafetyTrainingRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public SafetyTrainingRequest<T> count(){
        super.count();
        return this;
    }
    public SafetyTrainingRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }

    public SafetyTrainingRequest<T> groupById(){
       groupBy(SafetyTraining.ID_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByIdAs(String retName){
       groupBy(retName, SafetyTraining.ID_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, SafetyTraining.ID_PROPERTY, function);
       return this;
    }

    public SafetyTrainingRequest<T> groupByTitle(){
       groupBy(SafetyTraining.TITLE_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByTitleAs(String retName){
       groupBy(retName, SafetyTraining.TITLE_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByTitleWithFunction(String retName, AggrFunction function){
       groupBy(retName, SafetyTraining.TITLE_PROPERTY, function);
       return this;
    }

    public SafetyTrainingRequest<T> groupByDescription(){
       groupBy(SafetyTraining.DESCRIPTION_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByDescriptionAs(String retName){
       groupBy(retName, SafetyTraining.DESCRIPTION_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByDescriptionWithFunction(String retName, AggrFunction function){
       groupBy(retName, SafetyTraining.DESCRIPTION_PROPERTY, function);
       return this;
    }

    public SafetyTrainingRequest<T> groupByDurationHours(){
       groupBy(SafetyTraining.DURATION_HOURS_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByDurationHoursAs(String retName){
       groupBy(retName, SafetyTraining.DURATION_HOURS_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByDurationHoursWithFunction(String retName, AggrFunction function){
       groupBy(retName, SafetyTraining.DURATION_HOURS_PROPERTY, function);
       return this;
    }

    public SafetyTrainingRequest<T> groupByCompletionDate(){
       groupBy(SafetyTraining.COMPLETION_DATE_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByCompletionDateAs(String retName){
       groupBy(retName, SafetyTraining.COMPLETION_DATE_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByCompletionDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, SafetyTraining.COMPLETION_DATE_PROPERTY, function);
       return this;
    }

    public SafetyTrainingRequest<T> groupByStatus(){
       groupBy(SafetyTraining.STATUS_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByStatusAs(String retName){
       groupBy(retName, SafetyTraining.STATUS_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, SafetyTraining.STATUS_PROPERTY, function);
       return this;
    }

    public SafetyTrainingRequest<T> groupByCreatedAt(){
       groupBy(SafetyTraining.CREATED_AT_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, SafetyTraining.CREATED_AT_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, SafetyTraining.CREATED_AT_PROPERTY, function);
       return this;
    }

    public SafetyTrainingRequest<T> groupByUpdatedAt(){
       groupBy(SafetyTraining.UPDATED_AT_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByUpdatedAtAs(String retName){
       groupBy(retName, SafetyTraining.UPDATED_AT_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByUpdatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, SafetyTraining.UPDATED_AT_PROPERTY, function);
       return this;
    }

    public SafetyTrainingRequest<T> groupByVersion(){
       groupBy(SafetyTraining.VERSION_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByVersionAs(String retName){
       groupBy(retName, SafetyTraining.VERSION_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, SafetyTraining.VERSION_PROPERTY, function);
       return this;
    }



    public SafetyTrainingRequest<T> orderByIdAscending(){
       addOrderByAscending(SafetyTraining.ID_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByIdDescending(){
       addOrderByDescending(SafetyTraining.ID_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByTitleAscending(){
       addOrderByAscending(SafetyTraining.TITLE_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByTitleDescending(){
       addOrderByDescending(SafetyTraining.TITLE_PROPERTY);
       return this;
    }
    public SafetyTrainingRequest<T> orderByTitleAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SafetyTraining.TITLE_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByTitleDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SafetyTraining.TITLE_PROPERTY);
       return this;
    }
    public SafetyTrainingRequest<T> orderByDescriptionAscending(){
       addOrderByAscending(SafetyTraining.DESCRIPTION_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByDescriptionDescending(){
       addOrderByDescending(SafetyTraining.DESCRIPTION_PROPERTY);
       return this;
    }
    public SafetyTrainingRequest<T> orderByDescriptionAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SafetyTraining.DESCRIPTION_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByDescriptionDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SafetyTraining.DESCRIPTION_PROPERTY);
       return this;
    }
    public SafetyTrainingRequest<T> orderByDurationHoursAscending(){
       addOrderByAscending(SafetyTraining.DURATION_HOURS_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByDurationHoursDescending(){
       addOrderByDescending(SafetyTraining.DURATION_HOURS_PROPERTY);
       return this;
    }
    public SafetyTrainingRequest<T> orderByDurationHoursAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SafetyTraining.DURATION_HOURS_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByDurationHoursDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SafetyTraining.DURATION_HOURS_PROPERTY);
       return this;
    }
    public SafetyTrainingRequest<T> orderByCompletionDateAscending(){
       addOrderByAscending(SafetyTraining.COMPLETION_DATE_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByCompletionDateDescending(){
       addOrderByDescending(SafetyTraining.COMPLETION_DATE_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByStatusAscending(){
       addOrderByAscending(SafetyTraining.STATUS_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByStatusDescending(){
       addOrderByDescending(SafetyTraining.STATUS_PROPERTY);
       return this;
    }
    public SafetyTrainingRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SafetyTraining.STATUS_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SafetyTraining.STATUS_PROPERTY);
       return this;
    }
    public SafetyTrainingRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(SafetyTraining.CREATED_AT_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(SafetyTraining.CREATED_AT_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByUpdatedAtAscending(){
       addOrderByAscending(SafetyTraining.UPDATED_AT_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByUpdatedAtDescending(){
       addOrderByDescending(SafetyTraining.UPDATED_AT_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByVersionAscending(){
       addOrderByAscending(SafetyTraining.VERSION_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByVersionDescending(){
       addOrderByDescending(SafetyTraining.VERSION_PROPERTY);
       return this;
    }





    /**
     * get topN records
     * @param topN  records number
     */
    public SafetyTrainingRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public SafetyTrainingRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public SafetyTrainingRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public SafetyTrainingRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public SafetyTrainingRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}