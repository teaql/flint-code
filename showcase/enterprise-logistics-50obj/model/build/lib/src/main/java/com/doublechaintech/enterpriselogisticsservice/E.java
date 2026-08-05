package com.doublechaintech.enterpriselogisticsservice;

import com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission;
import com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermissionExpression;
import com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog;
import com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLogExpression;
import com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem;
import com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItemExpression;
import com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord;
import com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecordExpression;
import com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit;
import com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnitExpression;
import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer;
import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomerExpression;
import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact;
import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContactExpression;
import com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty;
import com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyaltyExpression;
import com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration;
import com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclarationExpression;
import com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon;
import com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCouponExpression;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlanExpression;
import com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment;
import com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignmentExpression;
import com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem;
import com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItemExpression;
import com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview;
import com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReviewExpression;
import com.doublechaintech.enterpriselogisticsservice.field.Field;
import com.doublechaintech.enterpriselogisticsservice.field.FieldExpression;
import com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport;
import com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReportExpression;
import com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog;
import com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLogExpression;
import com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog;
import com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLogExpression;
import com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy;
import com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicyExpression;
import com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck;
import com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheckExpression;
import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import com.doublechaintech.enterpriselogisticsservice.invoice.InvoiceExpression;
import com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi;
import com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoiExpression;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderExpression;
import com.doublechaintech.enterpriselogisticsservice.pallet.Pallet;
import com.doublechaintech.enterpriselogisticsservice.pallet.PalletExpression;
import com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord;
import com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecordExpression;
import com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview;
import com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReviewExpression;
import com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress;
import com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddressExpression;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerExpression;
import com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign;
import com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaignExpression;
import com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining;
import com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTrainingExpression;
import com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip;
import com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlipExpression;
import com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel;
import com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannelExpression;
import com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead;
import com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLeadExpression;
import com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract;
import com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContractExpression;
import com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote;
import com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuoteExpression;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberExpression;
import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer;
import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainerExpression;
import com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee;
import com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFeeExpression;
import com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord;
import com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecordExpression;
import com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice;
import com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDeviceExpression;
import com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot;
import com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlotExpression;
import com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute;
import com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRouteExpression;
import com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount;
import com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccountExpression;
import com.doublechaintech.enterpriselogisticsservice.userrole.UserRole;
import com.doublechaintech.enterpriselogisticsservice.userrole.UserRoleExpression;
import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
import com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleExpression;
import com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance;
import com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenanceExpression;
import com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse;
import com.doublechaintech.enterpriselogisticsservice.warehouse.WarehouseExpression;
import com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours;
import com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHoursExpression;
import com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift;
import com.doublechaintech.enterpriselogisticsservice.workshift.WorkShiftExpression;
import io.teaql.core.value.ValueExpression;

public class E  {
  public static MovingOrderExpression<MovingOrder, MovingOrder, MovingOrder> movingOrder(MovingOrder movingOrder){
      return new MovingOrderExpression(new ValueExpression(movingOrder));
  }
  public static FieldExpression<Field, Field, Field> field(Field field){
      return new FieldExpression(new ValueExpression(field));
  }
  public static DispatchPlanExpression<DispatchPlan, DispatchPlan, DispatchPlan> dispatchPlan(DispatchPlan dispatchPlan){
      return new DispatchPlanExpression(new ValueExpression(dispatchPlan));
  }
  public static TransitRouteExpression<TransitRoute, TransitRoute, TransitRoute> transitRoute(TransitRoute transitRoute){
      return new TransitRouteExpression(new ValueExpression(transitRoute));
  }
  public static TimeSlotExpression<TimeSlot, TimeSlot, TimeSlot> timeSlot(TimeSlot timeSlot){
      return new TimeSlotExpression(new ValueExpression(timeSlot));
  }
  public static CargoItemExpression<CargoItem, CargoItem, CargoItem> cargoItem(CargoItem cargoItem){
      return new CargoItemExpression(new ValueExpression(cargoItem));
  }
  public static PickupAddressExpression<PickupAddress, PickupAddress, PickupAddress> pickupAddress(PickupAddress pickupAddress){
      return new PickupAddressExpression(new ValueExpression(pickupAddress));
  }
  public static VehicleExpression<Vehicle, Vehicle, Vehicle> vehicle(Vehicle vehicle){
      return new VehicleExpression(new ValueExpression(vehicle));
  }
  public static DriverAssignmentExpression<DriverAssignment, DriverAssignment, DriverAssignment> driverAssignment(DriverAssignment driverAssignment){
      return new DriverAssignmentExpression(new ValueExpression(driverAssignment));
  }
  public static GpsLogExpression<GpsLog, GpsLog, GpsLog> gpsLog(GpsLog gpsLog){
      return new GpsLogExpression(new ValueExpression(gpsLog));
  }
  public static FuelLogExpression<FuelLog, FuelLog, FuelLog> fuelLog(FuelLog fuelLog){
      return new FuelLogExpression(new ValueExpression(fuelLog));
  }
  public static VehicleMaintenanceExpression<VehicleMaintenance, VehicleMaintenance, VehicleMaintenance> vehicleMaintenance(VehicleMaintenance vehicleMaintenance){
      return new VehicleMaintenanceExpression(new ValueExpression(vehicleMaintenance));
  }
  public static TelematicsDeviceExpression<TelematicsDevice, TelematicsDevice, TelematicsDevice> telematicsDevice(TelematicsDevice telematicsDevice){
      return new TelematicsDeviceExpression(new ValueExpression(telematicsDevice));
  }
  public static WarehouseExpression<Warehouse, Warehouse, Warehouse> warehouse(Warehouse warehouse){
      return new WarehouseExpression(new ValueExpression(warehouse));
  }
  public static StorageContainerExpression<StorageContainer, StorageContainer, StorageContainer> storageContainer(StorageContainer storageContainer){
      return new StorageContainerExpression(new ValueExpression(storageContainer));
  }
  public static ContainerUnitExpression<ContainerUnit, ContainerUnit, ContainerUnit> containerUnit(ContainerUnit containerUnit){
      return new ContainerUnitExpression(new ValueExpression(containerUnit));
  }
  public static InventoryCheckExpression<InventoryCheck, InventoryCheck, InventoryCheck> inventoryCheck(InventoryCheck inventoryCheck){
      return new InventoryCheckExpression(new ValueExpression(inventoryCheck));
  }
  public static PalletExpression<Pallet, Pallet, Pallet> pallet(Pallet pallet){
      return new PalletExpression(new ValueExpression(pallet));
  }
  public static StorageFeeExpression<StorageFee, StorageFee, StorageFee> storageFee(StorageFee storageFee){
      return new StorageFeeExpression(new ValueExpression(storageFee));
  }
  public static StaffMemberExpression<StaffMember, StaffMember, StaffMember> staffMember(StaffMember staffMember){
      return new StaffMemberExpression(new ValueExpression(staffMember));
  }
  public static WorkShiftExpression<WorkShift, WorkShift, WorkShift> workShift(WorkShift workShift){
      return new WorkShiftExpression(new ValueExpression(workShift));
  }
  public static WorkedHoursExpression<WorkedHours, WorkedHours, WorkedHours> workedHours(WorkedHours workedHours){
      return new WorkedHoursExpression(new ValueExpression(workedHours));
  }
  public static SalarySlipExpression<SalarySlip, SalarySlip, SalarySlip> salarySlip(SalarySlip salarySlip){
      return new SalarySlipExpression(new ValueExpression(salarySlip));
  }
  public static PerformanceReviewExpression<PerformanceReview, PerformanceReview, PerformanceReview> performanceReview(PerformanceReview performanceReview){
      return new PerformanceReviewExpression(new ValueExpression(performanceReview));
  }
  public static SafetyTrainingExpression<SafetyTraining, SafetyTraining, SafetyTraining> safetyTraining(SafetyTraining safetyTraining){
      return new SafetyTrainingExpression(new ValueExpression(safetyTraining));
  }
  public static PrivateCustomerExpression<PrivateCustomer, PrivateCustomer, PrivateCustomer> privateCustomer(PrivateCustomer privateCustomer){
      return new PrivateCustomerExpression(new ValueExpression(privateCustomer));
  }
  public static CorporateCustomerExpression<CorporateCustomer, CorporateCustomer, CorporateCustomer> corporateCustomer(CorporateCustomer corporateCustomer){
      return new CorporateCustomerExpression(new ValueExpression(corporateCustomer));
  }
  public static CustomerContactExpression<CustomerContact, CustomerContact, CustomerContact> customerContact(CustomerContact customerContact){
      return new CustomerContactExpression(new ValueExpression(customerContact));
  }
  public static ServiceQuoteExpression<ServiceQuote, ServiceQuote, ServiceQuote> serviceQuote(ServiceQuote serviceQuote){
      return new ServiceQuoteExpression(new ValueExpression(serviceQuote));
  }
  public static FeedbackReviewExpression<FeedbackReview, FeedbackReview, FeedbackReview> feedbackReview(FeedbackReview feedbackReview){
      return new FeedbackReviewExpression(new ValueExpression(feedbackReview));
  }
  public static CustomerLoyaltyExpression<CustomerLoyalty, CustomerLoyalty, CustomerLoyalty> customerLoyalty(CustomerLoyalty customerLoyalty){
      return new CustomerLoyaltyExpression(new ValueExpression(customerLoyalty));
  }
  public static PromotionCampaignExpression<PromotionCampaign, PromotionCampaign, PromotionCampaign> promotionCampaign(PromotionCampaign promotionCampaign){
      return new PromotionCampaignExpression(new ValueExpression(promotionCampaign));
  }
  public static DiscountCouponExpression<DiscountCoupon, DiscountCoupon, DiscountCoupon> discountCoupon(DiscountCoupon discountCoupon){
      return new DiscountCouponExpression(new ValueExpression(discountCoupon));
  }
  public static SalesLeadExpression<SalesLead, SalesLead, SalesLead> salesLead(SalesLead salesLead){
      return new SalesLeadExpression(new ValueExpression(salesLead));
  }
  public static SalesChannelExpression<SalesChannel, SalesChannel, SalesChannel> salesChannel(SalesChannel salesChannel){
      return new SalesChannelExpression(new ValueExpression(salesChannel));
  }
  public static MarketingRoiExpression<MarketingRoi, MarketingRoi, MarketingRoi> marketingRoi(MarketingRoi marketingRoi){
      return new MarketingRoiExpression(new ValueExpression(marketingRoi));
  }
  public static InvoiceExpression<Invoice, Invoice, Invoice> invoice(Invoice invoice){
      return new InvoiceExpression(new ValueExpression(invoice));
  }
  public static PaymentRecordExpression<PaymentRecord, PaymentRecord, PaymentRecord> paymentRecord(PaymentRecord paymentRecord){
      return new PaymentRecordExpression(new ValueExpression(paymentRecord));
  }
  public static ExpenseItemExpression<ExpenseItem, ExpenseItem, ExpenseItem> expenseItem(ExpenseItem expenseItem){
      return new ExpenseItemExpression(new ValueExpression(expenseItem));
  }
  public static TaxRecordExpression<TaxRecord, TaxRecord, TaxRecord> taxRecord(TaxRecord taxRecord){
      return new TaxRecordExpression(new ValueExpression(taxRecord));
  }
  public static FinancialReportExpression<FinancialReport, FinancialReport, FinancialReport> financialReport(FinancialReport financialReport){
      return new FinancialReportExpression(new ValueExpression(financialReport));
  }
  public static ServiceContractExpression<ServiceContract, ServiceContract, ServiceContract> serviceContract(ServiceContract serviceContract){
      return new ServiceContractExpression(new ValueExpression(serviceContract));
  }
  public static InsurancePolicyExpression<InsurancePolicy, InsurancePolicy, InsurancePolicy> insurancePolicy(InsurancePolicy insurancePolicy){
      return new InsurancePolicyExpression(new ValueExpression(insurancePolicy));
  }
  public static ClaimsRecordExpression<ClaimsRecord, ClaimsRecord, ClaimsRecord> claimsRecord(ClaimsRecord claimsRecord){
      return new ClaimsRecordExpression(new ValueExpression(claimsRecord));
  }
  public static CustomsDeclarationExpression<CustomsDeclaration, CustomsDeclaration, CustomsDeclaration> customsDeclaration(CustomsDeclaration customsDeclaration){
      return new CustomsDeclarationExpression(new ValueExpression(customsDeclaration));
  }
  public static AuditLogExpression<AuditLog, AuditLog, AuditLog> auditLog(AuditLog auditLog){
      return new AuditLogExpression(new ValueExpression(auditLog));
  }
  public static UserAccountExpression<UserAccount, UserAccount, UserAccount> userAccount(UserAccount userAccount){
      return new UserAccountExpression(new ValueExpression(userAccount));
  }
  public static UserRoleExpression<UserRole, UserRole, UserRole> userRole(UserRole userRole){
      return new UserRoleExpression(new ValueExpression(userRole));
  }
  public static AccessPermissionExpression<AccessPermission, AccessPermission, AccessPermission> accessPermission(AccessPermission accessPermission){
      return new AccessPermissionExpression(new ValueExpression(accessPermission));
  }
}