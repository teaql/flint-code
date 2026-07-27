# TeaQL Java CRUD Guide

Generated for `com.doublechaintech.enterpriselogisticsservice`. Use this guide when adding controllers, services, jobs, or integration code in this workspace.

## Setup

```java
import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.EnterpriseLogisticsServiceUserContext;
import io.teaql.core.web.WebResponse;
```

Most workspace code receives a TeaQL context from Spring:

```java
public WebResponse handle(@TQLContext UserContext userContext) {
    // use Q, WebResponse, and entity.auditAs("comment").save(userContext)
}
```

## Non-Negotiable Database Rule

Never use SQL to operate on the database from workspace business code. This includes select, insert, update, delete, schema changes, JDBC templates, native queries, direct repositories, and ad hoc SQL helpers.

If the generated TeaQL API does not provide a path for the requested change, stop and report the missing API. Do not implement a SQL workaround.

## Mandatory Update Method Rule

Use generated `updateXxx(...)` methods for all entity field changes. Do not use `setXxx(...)` in new code. Setters are deprecated even when the IDE or generated class makes them visible.

```java
entity.updateName("new value");
```

Do not write:

```java
entity.setName("new value");
```

## Read One Entity

Prefer typed generated query entry points. Controller methods that return one object should wrap the result in `WebResponse.of(...)`:

```java
public WebResponse getOne(@TQLContext UserContext userContext, Long id) {
    try {
        var entity = Q.staffMembers()
            .filterById(id)
            .selectSelf()
            .comment("load detail by id")
            .purpose("get task detail")
            .executeForOne(userContext);
        return WebResponse.of(entity);
    } catch (Exception e) {
        return WebResponse.fail(e.getMessage());
    }
}
```

## Read A Page

Controller methods that return multiple objects should wrap the list in `WebResponse.of(...)`:

```java
public WebResponse list(@TQLContext UserContext userContext) {
    try {
        var list = Q.staffMembers()
            .selectSelf()
            .page(1, 20)
            .comment("list tasks")
            .purpose("render dashboard list")
            .executeForList(userContext);
        return WebResponse.of(list);
    } catch (Exception e) {
        return WebResponse.fail(e.getMessage());
    }
}
```

## Create

```java
var entity = new StaffMember();
// Fill fields with generated updateXxx(...) methods, not setXxx(...).
entity.auditAs("Create new item").save(userContext);
```

## Update

```java
var entity = Q.staffMembers()
    .filterById(id)
    .selectSelf()
    .comment("load for update")
    .purpose("edit entity")
    .executeForOne(userContext);

// Use updateXxx(...) methods for state changes.
// entity.updateName("new value");
entity.auditAs("Update entity properties").save(userContext);
```

## Load Relations

Use generated selectors. Do not write a loop that queries children one row at a time. The examples below focus on the entities with the highest reverse relation counts because they are stronger aggregate-root candidates.

```java
var list = Q.staffMembers()
    .selectManagerWith(Q.staffMembers().selectSelf())
    .comment("load with manager")
    .purpose("fetch associated manager")
    .executeForList(userContext);
```



```java
var list = Q.staffMembers()
    .selectDispatchPlanListWith(Q.dispatchPlans().selectSelf())
    .comment("load with dispatchPlanList")
    .purpose("fetch child dispatchPlanList")
    .executeForList(userContext);
```

```java
var list = Q.staffMembers()
    .selectStaffMemberListWith(Q.staffMembers().selectSelf())
    .comment("load with staffMemberList")
    .purpose("fetch child staffMemberList")
    .executeForList(userContext);
```

```java
var list = Q.staffMembers()
    .selectWorkedHoursListWith(Q.workedHourses().selectSelf())
    .comment("load with workedHoursList")
    .purpose("fetch child workedHoursList")
    .executeForList(userContext);
```

```java
var list = Q.staffMembers()
    .selectSalarySlipListWith(Q.salarySlips().selectSelf())
    .comment("load with salarySlipList")
    .purpose("fetch child salarySlipList")
    .executeForList(userContext);
```

```java
var list = Q.staffMembers()
    .selectPerformanceReviewListAsStaffWith(Q.performanceReviews().selectSelf())
    .comment("load with performanceReviewListAsStaff")
    .purpose("fetch child performanceReviewListAsStaff")
    .executeForList(userContext);
```

```java
var list = Q.staffMembers()
    .selectPerformanceReviewListAsReviewerWith(Q.performanceReviews().selectSelf())
    .comment("load with performanceReviewListAsReviewer")
    .purpose("fetch child performanceReviewListAsReviewer")
    .executeForList(userContext);
```

```java
var list = Q.staffMembers()
    .selectSalesLeadListWith(Q.salesLeads().selectSelf())
    .comment("load with salesLeadList")
    .purpose("fetch child salesLeadList")
    .executeForList(userContext);
```

```java
var list = Q.movingOrders()
    .selectCustomerWith(Q.privateCustomers().selectSelf())
    .comment("load with customer")
    .purpose("fetch associated customer")
    .executeForList(userContext);
```
```java
var list = Q.movingOrders()
    .selectPickupAddressWith(Q.pickupAddresses().selectSelf())
    .comment("load with pickupAddress")
    .purpose("fetch associated pickupAddress")
    .executeForList(userContext);
```
```java
var list = Q.movingOrders()
    .selectDeliveryAddressWith(Q.pickupAddresses().selectSelf())
    .comment("load with deliveryAddress")
    .purpose("fetch associated deliveryAddress")
    .executeForList(userContext);
```







```java
var list = Q.movingOrders()
    .selectDispatchPlanListWith(Q.dispatchPlans().selectSelf())
    .comment("load with dispatchPlanList")
    .purpose("fetch child dispatchPlanList")
    .executeForList(userContext);
```

```java
var list = Q.movingOrders()
    .selectCargoItemListWith(Q.cargoItems().selectSelf())
    .comment("load with cargoItemList")
    .purpose("fetch child cargoItemList")
    .executeForList(userContext);
```

```java
var list = Q.movingOrders()
    .selectFeedbackReviewListWith(Q.feedbackReviews().selectSelf())
    .comment("load with feedbackReviewList")
    .purpose("fetch child feedbackReviewList")
    .executeForList(userContext);
```

```java
var list = Q.movingOrders()
    .selectInvoiceListWith(Q.invoices().selectSelf())
    .comment("load with invoiceList")
    .purpose("fetch child invoiceList")
    .executeForList(userContext);
```

```java
var list = Q.movingOrders()
    .selectClaimsRecordListWith(Q.claimsRecords().selectSelf())
    .comment("load with claimsRecordList")
    .purpose("fetch child claimsRecordList")
    .executeForList(userContext);
```

```java
var list = Q.movingOrders()
    .selectCustomsDeclarationListWith(Q.customsDeclarations().selectSelf())
    .comment("load with customsDeclarationList")
    .purpose("fetch child customsDeclarationList")
    .executeForList(userContext);
```


```java
var list = Q.vehicles()
    .selectDispatchPlanListWith(Q.dispatchPlans().selectSelf())
    .comment("load with dispatchPlanList")
    .purpose("fetch child dispatchPlanList")
    .executeForList(userContext);
```

```java
var list = Q.vehicles()
    .selectTelematicsDeviceListWith(Q.telematicsDevices().selectSelf())
    .comment("load with telematicsDeviceList")
    .purpose("fetch child telematicsDeviceList")
    .executeForList(userContext);
```

```java
var list = Q.vehicles()
    .selectFuelLogListWith(Q.fuelLogs().selectSelf())
    .comment("load with fuelLogList")
    .purpose("fetch child fuelLogList")
    .executeForList(userContext);
```

```java
var list = Q.vehicles()
    .selectVehicleMaintenanceListWith(Q.vehicleMaintenances().selectSelf())
    .comment("load with vehicleMaintenanceList")
    .purpose("fetch child vehicleMaintenanceList")
    .executeForList(userContext);
```

```java
var list = Q.vehicles()
    .selectDriverAssignmentListWith(Q.driverAssignments().selectSelf())
    .comment("load with driverAssignmentList")
    .purpose("fetch child driverAssignmentList")
    .executeForList(userContext);
```


## Delete

Use the generated TeaQL soft-delete API available on the entity/request class in this domain. If unsure, inspect the generated request class for the target entity and use the soft-delete operation it exposes.

Do not hard-delete rows. Do not write SQL `DELETE` or `UPDATE` statements. If the soft-delete API is not visible, stop and report that blocker instead of changing data through SQL.

## Common Mistakes

- Do not instantiate repositories directly in workspace business code.
- Do not use SQL for any database operation.
- Do not use deprecated `setXxx(...)` methods for updates. Use `updateXxx(...)`.
- Do not return raw entities or lists from controller query methods. Return `WebResponse.of(entity)` or `WebResponse.of(list)`.
- Do not forget `.executeForOne(userContext)` or `.executeForList(userContext)`.
- Do not forget `.comment("...")` and `.purpose("...")` on queries.
- Do not forget `.auditAs("...")` before calling `.save(ctx)` on entities.
- Do not assume relations are loaded unless the query selected them.
- Do not bypass `UserContext`; context carries logging, tenant, security, and repository resolution behavior.

## Entity Cheat Sheet

These entities are selected by reverse relation count, not by model declaration order.

- `StaffMember`: reverse relations `7`, query `Q.staffMembers()`, save `new StaffMember().auditAs("comment").save(userContext)`, request `com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberRequest`
- `MovingOrder`: reverse relations `6`, query `Q.movingOrders()`, save `new MovingOrder().auditAs("comment").save(userContext)`, request `com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderRequest`
- `Vehicle`: reverse relations `5`, query `Q.vehicles()`, save `new Vehicle().auditAs("comment").save(userContext)`, request `com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleRequest`