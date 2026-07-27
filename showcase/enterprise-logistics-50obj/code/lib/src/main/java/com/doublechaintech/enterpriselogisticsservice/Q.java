package com.doublechaintech.enterpriselogisticsservice;

import io.teaql.core.criteria.Operator;

public class Q  {
  public static com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderRequest<com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder> movingOrders(){
      return new com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderRequest(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderRequest<com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder> movingOrdersWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderRequest(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.field.FieldRequest<com.doublechaintech.enterpriselogisticsservice.field.Field> fields(){
      return new com.doublechaintech.enterpriselogisticsservice.field.FieldRequest(com.doublechaintech.enterpriselogisticsservice.field.Field.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.field.FieldRequest<com.doublechaintech.enterpriselogisticsservice.field.Field> fieldsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.field.FieldRequest(com.doublechaintech.enterpriselogisticsservice.field.Field.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlanRequest<com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan> dispatchPlans(){
      return new com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlanRequest(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlanRequest<com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan> dispatchPlansWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlanRequest(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRouteRequest<com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute> transitRoutes(){
      return new com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRouteRequest(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRouteRequest<com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute> transitRoutesWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRouteRequest(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlotRequest<com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot> timeSlots(){
      return new com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlotRequest(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlotRequest<com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot> timeSlotsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlotRequest(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItemRequest<com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem> cargoItems(){
      return new com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItemRequest(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItemRequest<com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem> cargoItemsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItemRequest(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddressRequest<com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress> pickupAddresses(){
      return new com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddressRequest(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddressRequest<com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress> pickupAddressesWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddressRequest(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleRequest<com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle> vehicles(){
      return new com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleRequest(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleRequest<com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle> vehiclesWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleRequest(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDeviceRequest<com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice> telematicsDevices(){
      return new com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDeviceRequest(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDeviceRequest<com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice> telematicsDevicesWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDeviceRequest(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLogRequest<com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog> gpsLogs(){
      return new com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLogRequest(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLogRequest<com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog> gpsLogsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLogRequest(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLogRequest<com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog> fuelLogs(){
      return new com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLogRequest(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLogRequest<com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog> fuelLogsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLogRequest(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenanceRequest<com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance> vehicleMaintenances(){
      return new com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenanceRequest(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenanceRequest<com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance> vehicleMaintenancesWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenanceRequest(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignmentRequest<com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment> driverAssignments(){
      return new com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignmentRequest(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignmentRequest<com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment> driverAssignmentsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignmentRequest(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.warehouse.WarehouseRequest<com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse> warehouses(){
      return new com.doublechaintech.enterpriselogisticsservice.warehouse.WarehouseRequest(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.warehouse.WarehouseRequest<com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse> warehousesWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.warehouse.WarehouseRequest(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainerRequest<com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer> storageContainers(){
      return new com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainerRequest(com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainerRequest<com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer> storageContainersWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainerRequest(com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnitRequest<com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit> containerUnits(){
      return new com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnitRequest(com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnitRequest<com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit> containerUnitsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnitRequest(com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheckRequest<com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck> inventoryChecks(){
      return new com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheckRequest(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheckRequest<com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck> inventoryChecksWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheckRequest(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.pallet.PalletRequest<com.doublechaintech.enterpriselogisticsservice.pallet.Pallet> pallets(){
      return new com.doublechaintech.enterpriselogisticsservice.pallet.PalletRequest(com.doublechaintech.enterpriselogisticsservice.pallet.Pallet.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.pallet.PalletRequest<com.doublechaintech.enterpriselogisticsservice.pallet.Pallet> palletsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.pallet.PalletRequest(com.doublechaintech.enterpriselogisticsservice.pallet.Pallet.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFeeRequest<com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee> storageFees(){
      return new com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFeeRequest(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFeeRequest<com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee> storageFeesWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFeeRequest(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberRequest<com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember> staffMembers(){
      return new com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberRequest(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberRequest<com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember> staffMembersWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberRequest(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.workshift.WorkShiftRequest<com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift> workShifts(){
      return new com.doublechaintech.enterpriselogisticsservice.workshift.WorkShiftRequest(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.workshift.WorkShiftRequest<com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift> workShiftsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.workshift.WorkShiftRequest(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHoursRequest<com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours> workedHourses(){
      return new com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHoursRequest(com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHoursRequest<com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours> workedHoursesWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHoursRequest(com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlipRequest<com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip> salarySlips(){
      return new com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlipRequest(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlipRequest<com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip> salarySlipsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlipRequest(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReviewRequest<com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview> performanceReviews(){
      return new com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReviewRequest(com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReviewRequest<com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview> performanceReviewsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReviewRequest(com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTrainingRequest<com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining> safetyTrainings(){
      return new com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTrainingRequest(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTrainingRequest<com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining> safetyTrainingsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTrainingRequest(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerRequest<com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer> privateCustomers(){
      return new com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerRequest(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerRequest<com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer> privateCustomersWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerRequest(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomerRequest<com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer> corporateCustomers(){
      return new com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomerRequest(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomerRequest<com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer> corporateCustomersWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomerRequest(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContactRequest<com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact> customerContacts(){
      return new com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContactRequest(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContactRequest<com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact> customerContactsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContactRequest(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuoteRequest<com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote> serviceQuotes(){
      return new com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuoteRequest(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuoteRequest<com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote> serviceQuotesWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuoteRequest(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReviewRequest<com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview> feedbackReviews(){
      return new com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReviewRequest(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReviewRequest<com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview> feedbackReviewsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReviewRequest(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyaltyRequest<com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty> customerLoyalties(){
      return new com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyaltyRequest(com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyaltyRequest<com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty> customerLoyaltiesWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyaltyRequest(com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaignRequest<com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign> promotionCampaigns(){
      return new com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaignRequest(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaignRequest<com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign> promotionCampaignsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaignRequest(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCouponRequest<com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon> discountCoupons(){
      return new com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCouponRequest(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCouponRequest<com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon> discountCouponsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCouponRequest(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLeadRequest<com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead> salesLeads(){
      return new com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLeadRequest(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLeadRequest<com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead> salesLeadsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLeadRequest(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannelRequest<com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel> salesChannels(){
      return new com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannelRequest(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannelRequest<com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel> salesChannelsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannelRequest(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoiRequest<com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi> marketingRois(){
      return new com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoiRequest(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoiRequest<com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi> marketingRoisWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoiRequest(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.invoice.InvoiceRequest<com.doublechaintech.enterpriselogisticsservice.invoice.Invoice> invoices(){
      return new com.doublechaintech.enterpriselogisticsservice.invoice.InvoiceRequest(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.invoice.InvoiceRequest<com.doublechaintech.enterpriselogisticsservice.invoice.Invoice> invoicesWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.invoice.InvoiceRequest(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecordRequest<com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord> paymentRecords(){
      return new com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecordRequest(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecordRequest<com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord> paymentRecordsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecordRequest(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItemRequest<com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem> expenseItems(){
      return new com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItemRequest(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItemRequest<com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem> expenseItemsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItemRequest(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecordRequest<com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord> taxRecords(){
      return new com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecordRequest(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecordRequest<com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord> taxRecordsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecordRequest(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContractRequest<com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract> serviceContracts(){
      return new com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContractRequest(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContractRequest<com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract> serviceContractsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContractRequest(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicyRequest<com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy> insurancePolicies(){
      return new com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicyRequest(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicyRequest<com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy> insurancePoliciesWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicyRequest(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecordRequest<com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord> claimsRecords(){
      return new com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecordRequest(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecordRequest<com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord> claimsRecordsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecordRequest(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclarationRequest<com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration> customsDeclarations(){
      return new com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclarationRequest(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclarationRequest<com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration> customsDeclarationsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclarationRequest(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLogRequest<com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog> auditLogs(){
      return new com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLogRequest(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLogRequest<com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog> auditLogsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLogRequest(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccountRequest<com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount> userAccounts(){
      return new com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccountRequest(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccountRequest<com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount> userAccountsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccountRequest(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.userrole.UserRoleRequest<com.doublechaintech.enterpriselogisticsservice.userrole.UserRole> userRoles(){
      return new com.doublechaintech.enterpriselogisticsservice.userrole.UserRoleRequest(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.userrole.UserRoleRequest<com.doublechaintech.enterpriselogisticsservice.userrole.UserRole> userRolesWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.userrole.UserRoleRequest(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermissionRequest<com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission> accessPermissions(){
      return new com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermissionRequest(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermissionRequest<com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission> accessPermissionsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermissionRequest(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotificationRequest<com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification> systemNotifications(){
      return new com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotificationRequest(com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotificationRequest<com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification> systemNotificationsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotificationRequest(com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfigurationRequest<com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfiguration> systemConfigurations(){
      return new com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfigurationRequest(com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfiguration.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfigurationRequest<com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfiguration> systemConfigurationsWithMinimalFields(){
      return new com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfigurationRequest(com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfiguration.class).withVersion(Operator.GREATER_THAN, 0l);
  }


}