package com.doublechaintech.enterpriselogisticsservice.safetytraining;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberRequest;
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
        return selectId().selectStaffIdOnly().selectCourseName().selectCompletionDate().selectCertificateNumber().selectStatus().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public SafetyTrainingRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public SafetyTrainingRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectStaff().selectCourseName().selectCompletionDate().selectCertificateNumber().selectStatus().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public SafetyTrainingRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectStaff().selectCourseName().selectCompletionDate().selectCertificateNumber().selectStatus().selectCreatedAt().selectUpdatedAt().selectVersion();
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
    public SafetyTrainingRequest<T> selectStaffIdOnly(){
       selectProperty(SafetyTraining.STAFF_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> selectStaff(){
        return selectStaffWith(Q.staffMembers().unlimited().selectSelf());
    }

    public SafetyTrainingRequest<T> selectStaffWith(StaffMemberRequest staff){
       selectProperty(SafetyTraining.STAFF_PROPERTY);
       enhanceRelation(SafetyTraining.STAFF_PROPERTY, staff);
       return this;
    }

    public SafetyTrainingRequest<T> unselectStaff(){
       unselectProperty(SafetyTraining.STAFF_PROPERTY);
       return this;
    }
    public SafetyTrainingRequest<T> selectCourseName(){
       selectProperty(SafetyTraining.COURSE_NAME_PROPERTY);
       return this;
    }

    /**
     * fill the courseName with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  courseName) to fetch courseName property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SafetyTrainingRequest<T> unselectCourseName(){
       unselectProperty(SafetyTraining.COURSE_NAME_PROPERTY);
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
    public SafetyTrainingRequest<T> selectCertificateNumber(){
       selectProperty(SafetyTraining.CERTIFICATE_NUMBER_PROPERTY);
       return this;
    }

    /**
     * fill the certificateNumber with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  certificateNumber) to fetch certificateNumber property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SafetyTrainingRequest<T> unselectCertificateNumber(){
       unselectProperty(SafetyTraining.CERTIFICATE_NUMBER_PROPERTY);
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



    public SafetyTrainingRequest<T> filterByStaff(StaffMember... staff){
      if (staff == null || staff.length == 0) {
        throw new IllegalArgumentException("filterByStaff parameter staff cannot be empty");
      }
      return appendSearchCriteria(createStaffCriteria(Operator.EQUAL, (Object[])staff));
    }

    public SafetyTrainingRequest<T> withStaff(Operator operator, Object... values){
       return appendSearchCriteria(createStaffCriteria(operator, values));
    }

    public SafetyTrainingRequest<T> withStaffIsUnknown(){
       return withStaff(Operator.IS_NULL);
    }

    public SafetyTrainingRequest<T> withStaffIsKnown(){
       return withStaff(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStaffCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SafetyTraining.STAFF_PROPERTY, operator, values);
    }

    public SafetyTrainingRequest<T> filterByStaff(Long staff){
      if(staff == null){
         return this;
      }
      return withStaff(Operator.EQUAL, staff);
    }
    public SafetyTrainingRequest<T> withStaffMatching(StaffMemberRequest staff){
       return appendSearchCriteria(new SubQuerySearchCriteria(SafetyTraining.STAFF_PROPERTY, staff, StaffMember.ID_PROPERTY));
    }

    public SafetyTrainingRequest<T> filterByCourseName(String... courseName){
      if (courseName == null || courseName.length == 0) {
        throw new IllegalArgumentException("filterByCourseName parameter courseName cannot be empty");
      }
      return appendSearchCriteria(createCourseNameCriteria(Operator.EQUAL, (Object[])courseName));
    }

    public SafetyTrainingRequest<T> withCourseName(Operator operator, Object... values){
       return appendSearchCriteria(createCourseNameCriteria(operator, values));
    }

    public SafetyTrainingRequest<T> withCourseNameIsUnknown(){
       return withCourseName(Operator.IS_NULL);
    }

    public SafetyTrainingRequest<T> withCourseNameIsKnown(){
       return withCourseName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCourseNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SafetyTraining.COURSE_NAME_PROPERTY, operator, values);
    }

    public SafetyTrainingRequest<T> withCourseNameGreaterThan(String courseName){
       return withCourseName(Operator.GREATER_THAN, courseName);
    }

    public SafetyTrainingRequest<T> withCourseNameGreaterThanOrEqualTo(String courseName){
       return withCourseName(Operator.GREATER_THAN_OR_EQUAL, courseName);
    }

    public SafetyTrainingRequest<T> withCourseNameLessThan(String courseName){
       return withCourseName(Operator.LESS_THAN, courseName);
    }

    public SafetyTrainingRequest<T> withCourseNameLessThanOrEqualTo(String courseName){
       return withCourseName(Operator.LESS_THAN_OR_EQUAL, courseName);
    }

    public SafetyTrainingRequest<T> withCourseNameBetween(String startOfCourseName, String endOfCourseName){
       return withCourseName(Operator.BETWEEN, startOfCourseName, endOfCourseName);
    }
    public SafetyTrainingRequest<T> withCourseNameStartingWith(String courseName){
       return withCourseName(Operator.BEGIN_WITH, courseName);
    }
    public SafetyTrainingRequest<T> withCourseNameContaining(String courseName){
       return withCourseName(Operator.CONTAIN, courseName);
    }

    public SafetyTrainingRequest<T> withCourseNameEndingWith(String courseName){
       return withCourseName(Operator.END_WITH, courseName);
    }

    public SafetyTrainingRequest<T> withCourseNameIs(String courseName){
       return withCourseName(Operator.EQUAL, courseName);
    }

    public SafetyTrainingRequest<T> withCourseNameSoundingLike(String courseName){
       return withCourseName(Operator.SOUNDS_LIKE, courseName);
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




    public SafetyTrainingRequest<T> filterByCertificateNumber(String... certificateNumber){
      if (certificateNumber == null || certificateNumber.length == 0) {
        throw new IllegalArgumentException("filterByCertificateNumber parameter certificateNumber cannot be empty");
      }
      return appendSearchCriteria(createCertificateNumberCriteria(Operator.EQUAL, (Object[])certificateNumber));
    }

    public SafetyTrainingRequest<T> withCertificateNumber(Operator operator, Object... values){
       return appendSearchCriteria(createCertificateNumberCriteria(operator, values));
    }

    public SafetyTrainingRequest<T> withCertificateNumberIsUnknown(){
       return withCertificateNumber(Operator.IS_NULL);
    }

    public SafetyTrainingRequest<T> withCertificateNumberIsKnown(){
       return withCertificateNumber(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCertificateNumberCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SafetyTraining.CERTIFICATE_NUMBER_PROPERTY, operator, values);
    }

    public SafetyTrainingRequest<T> withCertificateNumberGreaterThan(String certificateNumber){
       return withCertificateNumber(Operator.GREATER_THAN, certificateNumber);
    }

    public SafetyTrainingRequest<T> withCertificateNumberGreaterThanOrEqualTo(String certificateNumber){
       return withCertificateNumber(Operator.GREATER_THAN_OR_EQUAL, certificateNumber);
    }

    public SafetyTrainingRequest<T> withCertificateNumberLessThan(String certificateNumber){
       return withCertificateNumber(Operator.LESS_THAN, certificateNumber);
    }

    public SafetyTrainingRequest<T> withCertificateNumberLessThanOrEqualTo(String certificateNumber){
       return withCertificateNumber(Operator.LESS_THAN_OR_EQUAL, certificateNumber);
    }

    public SafetyTrainingRequest<T> withCertificateNumberBetween(String startOfCertificateNumber, String endOfCertificateNumber){
       return withCertificateNumber(Operator.BETWEEN, startOfCertificateNumber, endOfCertificateNumber);
    }
    public SafetyTrainingRequest<T> withCertificateNumberStartingWith(String certificateNumber){
       return withCertificateNumber(Operator.BEGIN_WITH, certificateNumber);
    }
    public SafetyTrainingRequest<T> withCertificateNumberContaining(String certificateNumber){
       return withCertificateNumber(Operator.CONTAIN, certificateNumber);
    }

    public SafetyTrainingRequest<T> withCertificateNumberEndingWith(String certificateNumber){
       return withCertificateNumber(Operator.END_WITH, certificateNumber);
    }

    public SafetyTrainingRequest<T> withCertificateNumberIs(String certificateNumber){
       return withCertificateNumber(Operator.EQUAL, certificateNumber);
    }

    public SafetyTrainingRequest<T> withCertificateNumberSoundingLike(String certificateNumber){
       return withCertificateNumber(Operator.SOUNDS_LIKE, certificateNumber);
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
    public SafetyTrainingRequest<T> groupByStaffWithDetails(){
       return groupByStaffWithDetails(Q.staffMembers().unlimited());
    }

    public SafetyTrainingRequest<T> groupByStaffWithDetails(StaffMemberRequest subRequest){
       aggregate(SafetyTraining.STAFF_PROPERTY, subRequest);
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
    public SafetyTrainingRequest<T> groupByStaffWith(StaffMemberRequest subRequest){
       groupBy(SafetyTraining.STAFF_PROPERTY, subRequest);
       return this;
    }
    public SafetyTrainingRequest<T> groupByStaff(){
       groupBy(SafetyTraining.STAFF_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByStaffAs(String retName){
       groupBy(retName, SafetyTraining.STAFF_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByStaffWithFunction(String retName, AggrFunction function){
       groupBy(retName, SafetyTraining.STAFF_PROPERTY, function);
       return this;
    }

    public SafetyTrainingRequest<T> groupByCourseName(){
       groupBy(SafetyTraining.COURSE_NAME_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByCourseNameAs(String retName){
       groupBy(retName, SafetyTraining.COURSE_NAME_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByCourseNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, SafetyTraining.COURSE_NAME_PROPERTY, function);
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

    public SafetyTrainingRequest<T> groupByCertificateNumber(){
       groupBy(SafetyTraining.CERTIFICATE_NUMBER_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByCertificateNumberAs(String retName){
       groupBy(retName, SafetyTraining.CERTIFICATE_NUMBER_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> groupByCertificateNumberWithFunction(String retName, AggrFunction function){
       groupBy(retName, SafetyTraining.CERTIFICATE_NUMBER_PROPERTY, function);
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

    public SafetyTrainingRequest<T> orderByStaffAscending(){
       addOrderByAscending(SafetyTraining.STAFF_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByStaffDescending(){
       addOrderByDescending(SafetyTraining.STAFF_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByCourseNameAscending(){
       addOrderByAscending(SafetyTraining.COURSE_NAME_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByCourseNameDescending(){
       addOrderByDescending(SafetyTraining.COURSE_NAME_PROPERTY);
       return this;
    }
    public SafetyTrainingRequest<T> orderByCourseNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SafetyTraining.COURSE_NAME_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByCourseNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SafetyTraining.COURSE_NAME_PROPERTY);
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

    public SafetyTrainingRequest<T> orderByCertificateNumberAscending(){
       addOrderByAscending(SafetyTraining.CERTIFICATE_NUMBER_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByCertificateNumberDescending(){
       addOrderByDescending(SafetyTraining.CERTIFICATE_NUMBER_PROPERTY);
       return this;
    }
    public SafetyTrainingRequest<T> orderByCertificateNumberAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SafetyTraining.CERTIFICATE_NUMBER_PROPERTY);
       return this;
    }

    public SafetyTrainingRequest<T> orderByCertificateNumberDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SafetyTraining.CERTIFICATE_NUMBER_PROPERTY);
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


    public StaffMemberRequest rollUpToStaff(){
       StaffMemberRequest staff = Q.staffMembers().unlimited();
       this.withStaffMatching(staff)
           .groupByStaffWith(staff);
       return staff;
    }









   public SafetyTrainingRequest<T> facetByStaffAs(String facetName, StaffMemberRequest staff){
       return facetByStaffAs(facetName, staff, true);
   }

   public SafetyTrainingRequest<T> facetByStaffAs(String facetName, StaffMemberRequest staff, boolean includeAllFacets){
       addFacet(facetName, SafetyTraining.STAFF_PROPERTY, staff, includeAllFacets);
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