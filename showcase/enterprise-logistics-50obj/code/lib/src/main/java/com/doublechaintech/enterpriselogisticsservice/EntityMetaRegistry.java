package com.doublechaintech.enterpriselogisticsservice;

import io.teaql.core.meta.EntityDescriptor;
import io.teaql.core.meta.EntityMetaAssembler;
import io.teaql.core.meta.EntityMetaFactory;
import io.teaql.core.meta.PropertyDescriptor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EntityMetaRegistry implements EntityMetaAssembler {
  private EntityMetaFactory $factory;

  @Override
  public void assemble(EntityMetaFactory factory) {
    this.$factory = factory;
  }
  private void registerMovingOrder() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder::new);
      entityDescriptor.with("name", "Moving Order")
      .with("module", "Operations")
      .with("module_key", "operations");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor orderNumber = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.ORDER_NUMBER_PROPERTY, String.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor customer = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.CUSTOMER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.MOVING_ORDER_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.class)
      ;
      PropertyDescriptor pickupAddress = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.PICKUP_ADDRESS_PROPERTY, com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.MOVING_ORDER_LIST_AS_PICKUP_ADDRESS_PROPERTY, com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.class)
      ;
      PropertyDescriptor deliveryAddress = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.DELIVERY_ADDRESS_PROPERTY, com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.MOVING_ORDER_LIST_AS_DELIVERY_ADDRESS_PROPERTY, com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.class)
      ;
      PropertyDescriptor totalWeight = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.TOTAL_WEIGHT_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor totalVolume = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.TOTAL_VOLUME_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor estimatedCost = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.ESTIMATED_COST_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor actualCost = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.ACTUAL_COST_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.UPDATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.ORDER_NUMBER_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");




      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.TOTAL_WEIGHT_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.TOTAL_VOLUME_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.ESTIMATED_COST_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.ACTUAL_COST_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.CREATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.UPDATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerField() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.field.Field.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.field.Field.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.field.Field::new);
      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.field.Field.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.field.Field.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.field.Field.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.field.Field.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.field.Field.NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "phone")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.field.Field.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerDispatchPlan() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan::new);
      entityDescriptor.with("name", "Dispatch Plan")
      .with("module", "Operations")
      .with("module_key", "operations");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor planNumber = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.PLAN_NUMBER_PROPERTY, String.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor movingOrder = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.MOVING_ORDER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.DISPATCH_PLAN_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.class)
      ;
      PropertyDescriptor vehicle = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.VEHICLE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.DISPATCH_PLAN_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.class)
      ;
      PropertyDescriptor driver = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.DRIVER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.DISPATCH_PLAN_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.class)
      ;
      PropertyDescriptor scheduledDeparture = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.SCHEDULED_DEPARTURE_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor scheduledArrival = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.SCHEDULED_ARRIVAL_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.UPDATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.PLAN_NUMBER_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");




      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.SCHEDULED_DEPARTURE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.SCHEDULED_ARRIVAL_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.CREATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.UPDATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerTransitRoute() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute::new);
      entityDescriptor.with("name", "Transit Route")
      .with("module", "Operations")
      .with("module_key", "operations");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor routeCode = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.ROUTE_CODE_PROPERTY, String.class)
      ;
      PropertyDescriptor originCity = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.ORIGIN_CITY_PROPERTY, String.class)
      ;
      PropertyDescriptor destinationCity = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.DESTINATION_CITY_PROPERTY, String.class)
      ;
      PropertyDescriptor distanceKm = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.DISTANCE_KM_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor estimatedDurationHours = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.UPDATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.ROUTE_CODE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.ORIGIN_CITY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.DESTINATION_CITY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.DISTANCE_KM_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "1200.00")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "14.00")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.CREATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "createTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.UPDATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "updateTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerTimeSlot() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot::new);
      entityDescriptor.with("name", "Time Slot")
      .with("module", "Operations")
      .with("module_key", "operations");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor slotCode = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.SLOT_CODE_PROPERTY, String.class)
      ;
      PropertyDescriptor startTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.START_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor endTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.END_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor capacity = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.CAPACITY_PROPERTY, Integer.class)
      ;
      PropertyDescriptor availableSpots = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.AVAILABLE_SPOTS_PROPERTY, Integer.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.UPDATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.SLOT_CODE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.START_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "createTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.END_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "createTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.CAPACITY_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.lang.Integer")
      .with("candidates", "10")
      .with("sqlType", "INTEGER")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isInt", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "Int")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.AVAILABLE_SPOTS_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.lang.Integer")
      .with("candidates", "5")
      .with("sqlType", "INTEGER")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isInt", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "Int")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.CREATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "createTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.UPDATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "updateTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerCargoItem() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem::new);
      entityDescriptor.with("name", "Cargo Item")
      .with("module", "Operations")
      .with("module_key", "operations");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor itemCode = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.ITEM_CODE_PROPERTY, String.class)
      ;
      PropertyDescriptor description = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.DESCRIPTION_PROPERTY, String.class)
      ;
      PropertyDescriptor weightKg = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.WEIGHT_KG_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor volumeM3 = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.VOLUME_M3_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor fragile = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.FRAGILE_PROPERTY, Boolean.class)
      ;
      PropertyDescriptor movingOrder = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.MOVING_ORDER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.CARGO_ITEM_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.UPDATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.ITEM_CODE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.DESCRIPTION_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.WEIGHT_KG_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.VOLUME_M3_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.FRAGILE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(1)")
      .with("javaType", "java.lang.Boolean")
      .with("sqlType", "BOOLEAN")
      .with("isId", "false")
      .with("isBool", "true")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "bit")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "Boolean")
      .with("isTime", "false")
      .with("isText", "false");


      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.CREATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.UPDATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerPickupAddress() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress::new);
      entityDescriptor.with("name", "Pickup Address")
      .with("module", "Operations")
      .with("module_key", "operations");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor addressLine1 = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.ADDRESS_LINE1_PROPERTY, String.class)
      ;
      PropertyDescriptor addressLine2 = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.ADDRESS_LINE2_PROPERTY, String.class)
      ;
      PropertyDescriptor city = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.CITY_PROPERTY, String.class)
      ;
      PropertyDescriptor stateProvince = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.STATE_PROVINCE_PROPERTY, String.class)
      ;
      PropertyDescriptor postalCode = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.POSTAL_CODE_PROPERTY, String.class)
      ;
      PropertyDescriptor country = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.COUNTRY_PROPERTY, String.class)
      ;
      PropertyDescriptor latitude = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.LATITUDE_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor longitude = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.LONGITUDE_PROPERTY, String.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.UPDATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.ADDRESS_LINE1_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.ADDRESS_LINE2_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.CITY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.STATE_PROVINCE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.POSTAL_CODE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.COUNTRY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.LATITUDE_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "40.7128")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.LONGITUDE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "-74.0060")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.CREATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "createTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.UPDATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "updateTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerVehicle() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle::new);
      entityDescriptor.with("name", "Vehicle")
      .with("module", "Fleet")
      .with("module_key", "fleet");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor plateNumber = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.PLATE_NUMBER_PROPERTY, String.class)
      ;
      PropertyDescriptor vin = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.VIN_PROPERTY, String.class)
      ;
      PropertyDescriptor make = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.MAKE_PROPERTY, String.class)
      ;
      PropertyDescriptor model = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.MODEL_PROPERTY, String.class)
      ;
      PropertyDescriptor year = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.YEAR_PROPERTY, Integer.class)
      ;
      PropertyDescriptor capacityKg = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.CAPACITY_KG_PROPERTY, Integer.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.UPDATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.PLATE_NUMBER_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.VIN_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.MAKE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.MODEL_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.YEAR_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.lang.Integer")
      .with("candidates", "2023")
      .with("sqlType", "INTEGER")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isInt", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "Int")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.CAPACITY_KG_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.lang.Integer")
      .with("candidates", "5000")
      .with("sqlType", "INTEGER")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isInt", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "Int")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "active")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.CREATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "createTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.UPDATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "updateTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerTelematicsDevice() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice::new);
      entityDescriptor.with("name", "Telematics Device")
      .with("module", "Fleet")
      .with("module_key", "fleet");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor deviceId = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.DEVICE_ID_PROPERTY, String.class)
      ;
      PropertyDescriptor imei = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.IMEI_PROPERTY, String.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor vehicle = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.VEHICLE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.TELEMATICS_DEVICE_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.UPDATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.DEVICE_ID_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.IMEI_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");


      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.CREATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.UPDATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerGpsLog() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog::new);
      entityDescriptor.with("name", "GPS Log")
      .with("module", "Fleet")
      .with("module_key", "fleet");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor latitude = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.LATITUDE_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor longitude = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.LONGITUDE_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor speedKmh = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.SPEED_KMH_PROPERTY, Integer.class)
      ;
      PropertyDescriptor heading = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.HEADING_PROPERTY, Integer.class)
      ;
      PropertyDescriptor timestamp = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.TIMESTAMP_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor device = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.DEVICE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.GPS_LOG_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.LATITUDE_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.LONGITUDE_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.SPEED_KMH_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.lang.Integer")
      .with("sqlType", "INTEGER")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isInt", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "Int")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.HEADING_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.lang.Integer")
      .with("sqlType", "INTEGER")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isInt", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "Int")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.TIMESTAMP_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");


      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerFuelLog() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog::new);
      entityDescriptor.with("name", "Fuel Log")
      .with("module", "Fleet")
      .with("module_key", "fleet");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor liters = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.LITERS_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor cost = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.COST_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor odometerKm = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.ODOMETER_KM_PROPERTY, Integer.class)
      ;
      PropertyDescriptor stationName = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.STATION_NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor date = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor vehicle = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.VEHICLE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.FUEL_LOG_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.LITERS_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.COST_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.ODOMETER_KM_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.lang.Integer")
      .with("sqlType", "INTEGER")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isInt", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "Int")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.STATION_NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");


      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerVehicleMaintenance() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance::new);
      entityDescriptor.with("name", "Vehicle Maintenance")
      .with("module", "Fleet")
      .with("module_key", "fleet");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor serviceType = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.SERVICE_TYPE_PROPERTY, String.class)
      ;
      PropertyDescriptor description = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.DESCRIPTION_PROPERTY, String.class)
      ;
      PropertyDescriptor cost = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.COST_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor scheduledDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.SCHEDULED_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor completedDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.COMPLETED_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor vehicle = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.VEHICLE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.VEHICLE_MAINTENANCE_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.SERVICE_TYPE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.DESCRIPTION_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.COST_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.SCHEDULED_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.COMPLETED_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");


      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerDriverAssignment() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment::new);
      entityDescriptor.with("name", "Driver Assignment")
      .with("module", "Fleet")
      .with("module_key", "fleet");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor startTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.START_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor endTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.END_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor vehicle = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.VEHICLE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.DRIVER_ASSIGNMENT_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.class)
      ;
      PropertyDescriptor driver = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.DRIVER_PROPERTY, String.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.START_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.END_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");


      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.DRIVER_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerWarehouse() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse::new);
      entityDescriptor.with("name", "Warehouse")
      .with("module", "Warehouse")
      .with("module_key", "warehouse");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor code = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.CODE_PROPERTY, String.class)
      ;
      PropertyDescriptor address = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.ADDRESS_PROPERTY, String.class)
      ;
      PropertyDescriptor city = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.CITY_PROPERTY, String.class)
      ;
      PropertyDescriptor country = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.COUNTRY_PROPERTY, String.class)
      ;
      PropertyDescriptor capacity = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.CAPACITY_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor createTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.CREATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.UPDATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "1,2")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Main Hub,West Coast Depot")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.CODE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "MAIN_HUB,WEST_DEPOT")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.ADDRESS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "123 Logistics Way,456 Harbor Blvd")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.CITY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Shanghai,Los Angeles")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.COUNTRY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "China,USA")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.CAPACITY_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "50000.00,30000.00")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "ACTIVE,ACTIVE")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.CREATE_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "createTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.UPDATE_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "updateTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerStorageContainer() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer::new);
      entityDescriptor.with("name", "Storage Container")
      .with("module", "Warehouse")
      .with("module_key", "warehouse");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor containerId = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.CONTAINER_ID_PROPERTY, String.class)
      ;
      PropertyDescriptor warehouse = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.WAREHOUSE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.STORAGE_CONTAINER_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor createTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.CREATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.UPDATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "1,2")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.CONTAINER_ID_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "CONT-001,CONT-002")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.WAREHOUSE_PROPERTY).with("candidates", "");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "AVAILABLE,OCCUPIED")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.CREATE_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.UPDATE_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerContainerUnit() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit::new);
      entityDescriptor.with("name", "Container Unit")
      .with("module", "Warehouse")
      .with("module_key", "warehouse");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor storageContainer = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit.STORAGE_CONTAINER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.CONTAINER_UNIT_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.class)
      ;
      PropertyDescriptor unitNumber = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit.UNIT_NUMBER_PROPERTY, String.class)
      ;
      PropertyDescriptor itemCount = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit.ITEM_COUNT_PROPERTY, Integer.class)
      ;
      PropertyDescriptor createTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit.CREATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit.UPDATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "1,2")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit.STORAGE_CONTAINER_PROPERTY).with("candidates", "");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit.UNIT_NUMBER_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "UNIT-A1,UNIT-A2")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit.ITEM_COUNT_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.lang.Integer")
      .with("candidates", "5,15")
      .with("sqlType", "INTEGER")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isInt", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "Int")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit.CREATE_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit.UPDATE_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerInventoryCheck() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck::new);
      entityDescriptor.with("name", "Inventory Check")
      .with("module", "Warehouse")
      .with("module_key", "warehouse");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor warehouse = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.WAREHOUSE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.INVENTORY_CHECK_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.class)
      ;
      PropertyDescriptor checkDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.CHECK_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor checker = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.CHECKER_PROPERTY, String.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor createTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.CREATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.UPDATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "1,2")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.WAREHOUSE_PROPERTY).with("candidates", "");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.CHECK_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("candidates", "2023-10-01,2023-10-02")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.CHECKER_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "John Doe,Jane Smith")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "COMPLETED,PENDING")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.CREATE_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.UPDATE_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerPallet() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.pallet.Pallet.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.pallet.Pallet.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.pallet.Pallet::new);
      entityDescriptor.with("name", "Pallet")
      .with("module", "Warehouse")
      .with("module_key", "warehouse");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pallet.Pallet.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor warehouse = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.pallet.Pallet.WAREHOUSE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.PALLET_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.class)
      ;
      PropertyDescriptor palletId = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pallet.Pallet.PALLET_ID_PROPERTY, String.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pallet.Pallet.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor createTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pallet.Pallet.CREATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pallet.Pallet.UPDATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pallet.Pallet.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pallet.Pallet.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "1,2")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pallet.Pallet.WAREHOUSE_PROPERTY).with("candidates", "");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pallet.Pallet.PALLET_ID_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "PAL-001,PAL-002")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pallet.Pallet.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "AVAILABLE,IN_USE")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pallet.Pallet.CREATE_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pallet.Pallet.UPDATE_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pallet.Pallet.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerStorageFee() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee::new);
      entityDescriptor.with("name", "Storage Fee")
      .with("module", "Warehouse")
      .with("module_key", "warehouse");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor warehouse = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.WAREHOUSE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.STORAGE_FEE_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.class)
      ;
      PropertyDescriptor container = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.CONTAINER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.STORAGE_FEE_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer.class)
      ;
      PropertyDescriptor amount = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.AMOUNT_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor currency = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.CURRENCY_PROPERTY, String.class)
      ;
      PropertyDescriptor period = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.PERIOD_PROPERTY, String.class)
      ;
      PropertyDescriptor createTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.CREATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.UPDATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "1,2")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.WAREHOUSE_PROPERTY).with("candidates", "");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.CONTAINER_PROPERTY).with("candidates", "");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.AMOUNT_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "50.00,75.00")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.CURRENCY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "USD,USD")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.PERIOD_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "MONTHLY,MONTHLY")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.CREATE_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.UPDATE_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerStaffMember() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember::new);
      entityDescriptor.with("name", "Staff Member")
      .with("module", "HR")
      .with("module_key", "hr");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor email = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.EMAIL_PROPERTY, String.class)
      ;
      PropertyDescriptor phone = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.PHONE_PROPERTY, String.class)
      ;
      PropertyDescriptor hireDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.HIRE_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor department = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.DEPARTMENT_PROPERTY, String.class)
      ;
      PropertyDescriptor jobTitle = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.JOB_TITLE_PROPERTY, String.class)
      ;
      PropertyDescriptor manager = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.MANAGER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.STAFF_MEMBER_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.UPDATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.EMAIL_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.PHONE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.HIRE_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.DEPARTMENT_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.JOB_TITLE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");


      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.CREATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.UPDATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerWorkShift() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift::new);
      entityDescriptor.with("name", "Work Shift")
      .with("module", "HR")
      .with("module_key", "hr");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor startTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.START_TIME_PROPERTY, LocalTime.class)
      ;
      PropertyDescriptor endTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.END_TIME_PROPERTY, LocalTime.class)
      ;
      PropertyDescriptor shiftDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.SHIFT_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.UPDATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "id()")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.START_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalTime")
      .with("candidates", "time()")
      .with("sqlType", "TIME")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "Time")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.END_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalTime")
      .with("candidates", "time()")
      .with("sqlType", "TIME")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "Time")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.SHIFT_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("candidates", "date()")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.CREATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "createTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.UPDATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "updateTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerWorkedHours() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours::new);
      entityDescriptor.with("name", "Worked Hours")
      .with("module", "HR")
      .with("module_key", "hr");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor staff = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours.STAFF_PROPERTY, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.WORKED_HOURS_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.class)
      ;
      PropertyDescriptor shift = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours.SHIFT_PROPERTY, com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.WORKED_HOURS_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.class)
      ;
      PropertyDescriptor hoursWorked = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours.HOURS_WORKED_PROPERTY, String.class)
      ;
      PropertyDescriptor date = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours.DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours.UPDATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");



      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours.HOURS_WORKED_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours.DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours.CREATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours.UPDATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerSalarySlip() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip::new);
      entityDescriptor.with("name", "Salary Slip")
      .with("module", "HR")
      .with("module_key", "hr");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor staff = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.STAFF_PROPERTY, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.SALARY_SLIP_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.class)
      ;
      PropertyDescriptor period = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.PERIOD_PROPERTY, String.class)
      ;
      PropertyDescriptor baseSalary = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.BASE_SALARY_PROPERTY, String.class)
      ;
      PropertyDescriptor bonus = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.BONUS_PROPERTY, String.class)
      ;
      PropertyDescriptor deductions = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.DEDUCTIONS_PROPERTY, String.class)
      ;
      PropertyDescriptor netPay = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.NET_PAY_PROPERTY, String.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.UPDATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");


      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.PERIOD_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.BASE_SALARY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.BONUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.DEDUCTIONS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.NET_PAY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.CREATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.UPDATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerPerformanceReview() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview::new);
      entityDescriptor.with("name", "Performance Review")
      .with("module", "HR")
      .with("module_key", "hr");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor staff = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview.STAFF_PROPERTY, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.PERFORMANCE_REVIEW_LIST_AS_STAFF_PROPERTY, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.class)
      ;
      PropertyDescriptor reviewer = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview.REVIEWER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.PERFORMANCE_REVIEW_LIST_AS_REVIEWER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.class)
      ;
      PropertyDescriptor reviewDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview.REVIEW_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor score = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview.SCORE_PROPERTY, String.class)
      ;
      PropertyDescriptor comments = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview.COMMENTS_PROPERTY, String.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview.UPDATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");



      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview.REVIEW_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview.SCORE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview.COMMENTS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview.CREATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview.UPDATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerSafetyTraining() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining::new);
      entityDescriptor.with("name", "Safety Training")
      .with("module", "HR")
      .with("module_key", "hr");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor title = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.TITLE_PROPERTY, String.class)
      ;
      PropertyDescriptor description = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.DESCRIPTION_PROPERTY, String.class)
      ;
      PropertyDescriptor durationHours = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.DURATION_HOURS_PROPERTY, String.class)
      ;
      PropertyDescriptor completionDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.COMPLETION_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.UPDATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "id()")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.TITLE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.DESCRIPTION_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.DURATION_HOURS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "double()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.COMPLETION_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("candidates", "date()")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.CREATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "createTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.UPDATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "updateTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerPrivateCustomer() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer::new);
      entityDescriptor.with("name", "Private Customer")
      .with("module", "CRM")
      .with("module_key", "crm");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor phone = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.PHONE_PROPERTY, String.class)
      ;
      PropertyDescriptor email = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.EMAIL_PROPERTY, String.class)
      ;
      PropertyDescriptor addressLine1 = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.ADDRESS_LINE1_PROPERTY, String.class)
      ;
      PropertyDescriptor addressLine2 = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.ADDRESS_LINE2_PROPERTY, String.class)
      ;
      PropertyDescriptor city = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.CITY_PROPERTY, String.class)
      ;
      PropertyDescriptor state = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.STATE_PROPERTY, String.class)
      ;
      PropertyDescriptor zipCode = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.ZIP_CODE_PROPERTY, String.class)
      ;
      PropertyDescriptor country = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.COUNTRY_PROPERTY, String.class)
      ;
      PropertyDescriptor customerType = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.CUSTOMER_TYPE_PROPERTY, String.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.UPDATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "id()")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.PHONE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.EMAIL_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.ADDRESS_LINE1_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.ADDRESS_LINE2_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.CITY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.STATE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.ZIP_CODE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.COUNTRY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.CUSTOMER_TYPE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "private")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.CREATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "createTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.UPDATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "updateTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerCorporateCustomer() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer::new);
      entityDescriptor.with("name", "Corporate Customer")
      .with("module", "CRM")
      .with("module_key", "crm");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor registrationNumber = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.REGISTRATION_NUMBER_PROPERTY, String.class)
      ;
      PropertyDescriptor industry = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.INDUSTRY_PROPERTY, String.class)
      ;
      PropertyDescriptor employeeCount = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.EMPLOYEE_COUNT_PROPERTY, Integer.class)
      ;
      PropertyDescriptor billingAddress = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.BILLING_ADDRESS_PROPERTY, String.class)
      ;
      PropertyDescriptor contactEmail = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.CONTACT_EMAIL_PROPERTY, String.class)
      ;
      PropertyDescriptor contactPhone = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.CONTACT_PHONE_PROPERTY, String.class)
      ;
      PropertyDescriptor customerType = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.CUSTOMER_TYPE_PROPERTY, String.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.UPDATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "id()")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.REGISTRATION_NUMBER_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.INDUSTRY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.EMPLOYEE_COUNT_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.lang.Integer")
      .with("candidates", "100")
      .with("sqlType", "INTEGER")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isInt", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "Int")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.BILLING_ADDRESS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.CONTACT_EMAIL_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.CONTACT_PHONE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.CUSTOMER_TYPE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "corporate")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.CREATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "createTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.UPDATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "updateTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerCustomerContact() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact::new);
      entityDescriptor.with("name", "Customer Contact")
      .with("module", "CRM")
      .with("module_key", "crm");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor firstName = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.FIRST_NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor lastName = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.LAST_NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor email = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.EMAIL_PROPERTY, String.class)
      ;
      PropertyDescriptor phone = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.PHONE_PROPERTY, String.class)
      ;
      PropertyDescriptor isPrimary = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.IS_PRIMARY_PROPERTY, Boolean.class)
      ;
      PropertyDescriptor privateCustomer = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.PRIVATE_CUSTOMER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.CUSTOMER_CONTACT_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.class)
      ;
      PropertyDescriptor corporateCustomer = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.CORPORATE_CUSTOMER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.CUSTOMER_CONTACT_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.UPDATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.FIRST_NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.LAST_NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.EMAIL_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.PHONE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.IS_PRIMARY_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(1)")
      .with("javaType", "java.lang.Boolean")
      .with("sqlType", "BOOLEAN")
      .with("isId", "false")
      .with("isBool", "true")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "bit")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "Boolean")
      .with("isTime", "false")
      .with("isText", "false");



      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.CREATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.UPDATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerServiceQuote() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote::new);
      entityDescriptor.with("name", "Service Quote")
      .with("module", "CRM")
      .with("module_key", "crm");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor quoteNumber = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.QUOTE_NUMBER_PROPERTY, String.class)
      ;
      PropertyDescriptor estimatedCost = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.ESTIMATED_COST_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor currency = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.CURRENCY_PROPERTY, String.class)
      ;
      PropertyDescriptor validUntil = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.VALID_UNTIL_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor privateCustomer = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.PRIVATE_CUSTOMER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.SERVICE_QUOTE_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.class)
      ;
      PropertyDescriptor corporateCustomer = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.CORPORATE_CUSTOMER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.SERVICE_QUOTE_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.UPDATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.QUOTE_NUMBER_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.ESTIMATED_COST_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.CURRENCY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.VALID_UNTIL_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");



      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.CREATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.UPDATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerFeedbackReview() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview::new);
      entityDescriptor.with("name", "Feedback Review")
      .with("module", "CRM")
      .with("module_key", "crm");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor rating = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.RATING_PROPERTY, Integer.class)
      ;
      PropertyDescriptor title = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.TITLE_PROPERTY, String.class)
      ;
      PropertyDescriptor comment = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.COMMENT_PROPERTY, String.class)
      ;
      PropertyDescriptor movingOrder = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.MOVING_ORDER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.FEEDBACK_REVIEW_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.UPDATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.RATING_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.lang.Integer")
      .with("sqlType", "INTEGER")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isInt", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "Int")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.TITLE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.COMMENT_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");


      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.CREATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.UPDATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerCustomerLoyalty() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty::new);
      entityDescriptor.with("name", "Customer Loyalty")
      .with("module", "CRM")
      .with("module_key", "crm");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor points = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty.POINTS_PROPERTY, Integer.class)
      ;
      PropertyDescriptor tier = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty.TIER_PROPERTY, String.class)
      ;
      PropertyDescriptor privateCustomer = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.CUSTOMER_LOYALTY_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty.UPDATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty.POINTS_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.lang.Integer")
      .with("sqlType", "INTEGER")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isInt", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "Int")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty.TIER_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");


      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty.CREATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty.UPDATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerPromotionCampaign() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign::new);
      entityDescriptor.with("name", "Promotion Campaign")
      .with("module", "Sales")
      .with("module_key", "sales");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor description = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.DESCRIPTION_PROPERTY, String.class)
      ;
      PropertyDescriptor startDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.START_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor endDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.END_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor budget = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.BUDGET_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.UPDATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.DESCRIPTION_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.START_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("candidates", "2023-01-01")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.END_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("candidates", "2023-12-31")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.BUDGET_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "50000.00")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "ACTIVE")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.CREATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "createTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.UPDATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "updateTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerDiscountCoupon() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon::new);
      entityDescriptor.with("name", "Discount Coupon")
      .with("module", "Sales")
      .with("module_key", "sales");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor code = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.CODE_PROPERTY, String.class)
      ;
      PropertyDescriptor description = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.DESCRIPTION_PROPERTY, String.class)
      ;
      PropertyDescriptor discountPercentage = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor minOrderAmount = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor maxDiscountAmount = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor usageLimit = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.USAGE_LIMIT_PROPERTY, Integer.class)
      ;
      PropertyDescriptor usedCount = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.USED_COUNT_PROPERTY, Integer.class)
      ;
      PropertyDescriptor startDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.START_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor endDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.END_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.UPDATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.CODE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.DESCRIPTION_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "10.00")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "100.00")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "50.00")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.USAGE_LIMIT_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.lang.Integer")
      .with("candidates", "100")
      .with("sqlType", "INTEGER")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isInt", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "Int")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.USED_COUNT_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.lang.Integer")
      .with("candidates", "0")
      .with("sqlType", "INTEGER")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isInt", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "Int")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.START_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("candidates", "2023-01-01")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.END_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("candidates", "2023-12-31")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "ACTIVE")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.CREATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "createTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.UPDATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "updateTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerSalesLead() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead::new);
      entityDescriptor.with("name", "Sales Lead")
      .with("module", "Sales")
      .with("module_key", "sales");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor company = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.COMPANY_PROPERTY, String.class)
      ;
      PropertyDescriptor email = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.EMAIL_PROPERTY, String.class)
      ;
      PropertyDescriptor phone = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.PHONE_PROPERTY, String.class)
      ;
      PropertyDescriptor source = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.SOURCE_PROPERTY, String.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor assignedTo = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.ASSIGNED_TO_PROPERTY, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.SALES_LEAD_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.UPDATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.COMPANY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.EMAIL_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.PHONE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.SOURCE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");


      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.CREATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.UPDATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerSalesChannel() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel::new);
      entityDescriptor.with("name", "Sales Channel")
      .with("module", "Sales")
      .with("module_key", "sales");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor description = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.DESCRIPTION_PROPERTY, String.class)
      ;
      PropertyDescriptor channelType = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.CHANNEL_TYPE_PROPERTY, String.class)
      ;
      PropertyDescriptor isActive = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.IS_ACTIVE_PROPERTY, Boolean.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.UPDATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.DESCRIPTION_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.CHANNEL_TYPE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.IS_ACTIVE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(1)")
      .with("javaType", "java.lang.Boolean")
      .with("candidates", "true")
      .with("sqlType", "BOOLEAN")
      .with("isId", "false")
      .with("isBool", "true")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "bit")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "Boolean")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.CREATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "createTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.UPDATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "updateTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerMarketingRoi() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi::new);
      entityDescriptor.with("name", "Marketing ROI")
      .with("module", "Sales")
      .with("module_key", "sales");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor campaign = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.CAMPAIGN_PROPERTY, com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.MARKETING_ROI_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.class)
      ;
      PropertyDescriptor channel = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.CHANNEL_PROPERTY, com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.MARKETING_ROI_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.class)
      ;
      PropertyDescriptor spend = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.SPEND_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor revenue = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.REVENUE_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor roiPercentage = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.ROI_PERCENTAGE_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor reportDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.REPORT_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.UPDATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");



      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.SPEND_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.REVENUE_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.ROI_PERCENTAGE_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.REPORT_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.CREATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.UPDATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerInvoice() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice::new);
      entityDescriptor.with("name", "Invoice")
      .with("module", "Finance")
      .with("module_key", "finance");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor code = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.CODE_PROPERTY, String.class)
      ;
      PropertyDescriptor amount = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.AMOUNT_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor currency = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.CURRENCY_PROPERTY, String.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor issueDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.ISSUE_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor dueDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.DUE_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor movingOrder = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.MOVING_ORDER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.INVOICE_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.class)
      ;
      PropertyDescriptor customer = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.CUSTOMER_PROPERTY, String.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "1,2,3,4")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Draft,Issued,Paid,Overdue")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.CODE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "DRAFT,ISSUED,PAID,OVERDUE")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.AMOUNT_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.CURRENCY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.ISSUE_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.DUE_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");


      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.CUSTOMER_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerPaymentRecord() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord::new);
      entityDescriptor.with("name", "Payment Record")
      .with("module", "Finance")
      .with("module_key", "finance");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor referenceCode = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.REFERENCE_CODE_PROPERTY, String.class)
      ;
      PropertyDescriptor amount = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.AMOUNT_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor currency = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.CURRENCY_PROPERTY, String.class)
      ;
      PropertyDescriptor paymentMethod = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.PAYMENT_METHOD_PROPERTY, String.class)
      ;
      PropertyDescriptor paymentDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.PAYMENT_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor invoice = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.INVOICE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.PAYMENT_RECORD_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "1,2,3")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Pending,Completed,Failed")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.REFERENCE_CODE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.AMOUNT_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.CURRENCY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.PAYMENT_METHOD_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.PAYMENT_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");


      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerExpenseItem() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem::new);
      entityDescriptor.with("name", "Expense Item")
      .with("module", "Finance")
      .with("module_key", "finance");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor description = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.DESCRIPTION_PROPERTY, String.class)
      ;
      PropertyDescriptor amount = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.AMOUNT_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor currency = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.CURRENCY_PROPERTY, String.class)
      ;
      PropertyDescriptor expenseType = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.EXPENSE_TYPE_PROPERTY, String.class)
      ;
      PropertyDescriptor expenseDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.EXPENSE_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor employee = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.EMPLOYEE_PROPERTY, String.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "1,2,3,4")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Travel,Fuel,Maintenance,Office Supplies")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.DESCRIPTION_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.AMOUNT_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "150.00")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.CURRENCY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "USD")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.EXPENSE_TYPE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.EXPENSE_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("candidates", "2023-10-05")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.EMPLOYEE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "employee()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "status()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerTaxRecord() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord::new);
      entityDescriptor.with("name", "Tax Record")
      .with("module", "Finance")
      .with("module_key", "finance");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor taxCode = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.TAX_CODE_PROPERTY, String.class)
      ;
      PropertyDescriptor taxAmount = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.TAX_AMOUNT_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor currency = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.CURRENCY_PROPERTY, String.class)
      ;
      PropertyDescriptor taxRate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.TAX_RATE_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor taxPeriod = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.TAX_PERIOD_PROPERTY, String.class)
      ;
      PropertyDescriptor filingStatus = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.FILING_STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor invoice = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.INVOICE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.TAX_RECORD_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "1,2,3")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "VAT,Income Tax,Sales Tax")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.TAX_CODE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.TAX_AMOUNT_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.CURRENCY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.TAX_RATE_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.TAX_PERIOD_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.FILING_STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");


      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerServiceContract() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract::new);
      entityDescriptor.with("name", "Service Contract")
      .with("module", "Compliance")
      .with("module_key", "compliance");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor contractNumber = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.CONTRACT_NUMBER_PROPERTY, String.class)
      ;
      PropertyDescriptor title = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.TITLE_PROPERTY, String.class)
      ;
      PropertyDescriptor startDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.START_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor endDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.END_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor totalValue = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.TOTAL_VALUE_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor currency = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.CURRENCY_PROPERTY, String.class)
      ;
      PropertyDescriptor corporateCustomer = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.CORPORATE_CUSTOMER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.SERVICE_CONTRACT_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.UPDATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.CONTRACT_NUMBER_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.TITLE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.START_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.END_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.TOTAL_VALUE_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.CURRENCY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");


      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.CREATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.UPDATE_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerInsurancePolicy() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy::new);
      entityDescriptor.with("name", "Insurance Policy")
      .with("module", "Compliance")
      .with("module_key", "compliance");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor policyNumber = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.POLICY_NUMBER_PROPERTY, String.class)
      ;
      PropertyDescriptor provider = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.PROVIDER_PROPERTY, String.class)
      ;
      PropertyDescriptor coverageAmount = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.COVERAGE_AMOUNT_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor premium = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.PREMIUM_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor startDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.START_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor endDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.END_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.UPDATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.POLICY_NUMBER_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.PROVIDER_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "string()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.COVERAGE_AMOUNT_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "50000.00")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.PREMIUM_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "1000.00")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.START_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("candidates", "2023-01-01")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.END_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("candidates", "2024-01-01")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "active")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.CREATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "createTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.UPDATE_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "updateTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerClaimsRecord() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord::new);
      entityDescriptor.with("name", "Claims Record")
      .with("module", "Compliance")
      .with("module_key", "compliance");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor claimNumber = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.CLAIM_NUMBER_PROPERTY, String.class)
      ;
      PropertyDescriptor description = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.DESCRIPTION_PROPERTY, String.class)
      ;
      PropertyDescriptor claimAmount = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.CLAIM_AMOUNT_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor resolutionDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.RESOLUTION_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor movingOrder = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.MOVING_ORDER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.CLAIMS_RECORD_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.class)
      ;
      PropertyDescriptor insurancePolicy = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.INSURANCE_POLICY_PROPERTY, com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.CLAIMS_RECORD_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.UPDATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.CLAIM_NUMBER_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.DESCRIPTION_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.CLAIM_AMOUNT_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.RESOLUTION_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("sqlType", "DATE")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "Date")
      .with("isTime", "false")
      .with("isText", "false");



      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.CREATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.UPDATE_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerCustomsDeclaration() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration::new);
      entityDescriptor.with("name", "Customs Declaration")
      .with("module", "Compliance")
      .with("module_key", "compliance");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor declarationNumber = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.DECLARATION_NUMBER_PROPERTY, String.class)
      ;
      PropertyDescriptor originCountry = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.ORIGIN_COUNTRY_PROPERTY, String.class)
      ;
      PropertyDescriptor destinationCountry = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.DESTINATION_COUNTRY_PROPERTY, String.class)
      ;
      PropertyDescriptor totalValue = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.TOTAL_VALUE_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor movingOrder = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.MOVING_ORDER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.CUSTOMS_DECLARATION_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.UPDATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.DECLARATION_NUMBER_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.ORIGIN_COUNTRY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.DESTINATION_COUNTRY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.TOTAL_VALUE_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("sqlType", "NUMERIC(19,7)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "true")
      .with("isString", "false")
      .with("isDate", "false")
      .with("graphqlType", "BigDecimal")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");


      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.CREATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.UPDATE_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerAuditLog() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog::new);
      entityDescriptor.with("name", "Audit Log")
      .with("module", "Compliance")
      .with("module_key", "compliance");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor action = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.ACTION_PROPERTY, String.class)
      ;
      PropertyDescriptor entityType = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.ENTITY_TYPE_PROPERTY, String.class)
      ;
      PropertyDescriptor entityId = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.ENTITY_ID_PROPERTY, String.class)
      ;
      PropertyDescriptor userAccount = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.USER_ACCOUNT_PROPERTY, com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.AUDIT_LOG_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.class)
      ;
      PropertyDescriptor ipAddress = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.IP_ADDRESS_PROPERTY, String.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.UPDATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.ACTION_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.ENTITY_TYPE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.ENTITY_ID_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");


      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.IP_ADDRESS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.CREATED_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.UPDATE_TIME_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerUserAccount() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount::new);
      entityDescriptor.with("name", "User Account")
      .with("module", "Platform")
      .with("module_key", "platform")
      .with("audit_mask_fields", "password_hash");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor username = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.USERNAME_PROPERTY, String.class)
      ;
      PropertyDescriptor email = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.EMAIL_PROPERTY, String.class)
      ;
      PropertyDescriptor phone = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.PHONE_PROPERTY, String.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor passwordHash = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.PASSWORD_HASH_PROPERTY, String.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.UPDATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.USERNAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "admin")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.EMAIL_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "admin@logistics.com")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.PHONE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "13800000000")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "ACTIVE")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.PASSWORD_HASH_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "hashed_secret_123")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.CREATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "createTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.UPDATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "updateTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerUserRole() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole::new);
      entityDescriptor.with("name", "User Role")
      .with("module", "Platform")
      .with("module_key", "platform");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor roleName = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.ROLE_NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor description = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.DESCRIPTION_PROPERTY, String.class)
      ;
      PropertyDescriptor isSystem = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.IS_SYSTEM_PROPERTY, String.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.ROLE_NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Administrator,Dispatcher,Driver")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.DESCRIPTION_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Full system access,Manage dispatch plans,View assigned routes")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.IS_SYSTEM_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "true,false,false")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.CREATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "createTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerAccessPermission() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission::new);
      entityDescriptor.with("name", "Access Permission")
      .with("module", "Platform")
      .with("module_key", "platform");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor permissionCode = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.PERMISSION_CODE_PROPERTY, String.class)
      ;
      PropertyDescriptor resource = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.RESOURCE_PROPERTY, String.class)
      ;
      PropertyDescriptor action = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.ACTION_PROPERTY, String.class)
      ;
      PropertyDescriptor description = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.DESCRIPTION_PROPERTY, String.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.PERMISSION_CODE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "VIEW_ORDERS,EDIT_FLEET")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.RESOURCE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "orders,fleet")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.ACTION_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "read,write")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.DESCRIPTION_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "View moving orders,Edit vehicle details")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerSystemNotification() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification::new);
      entityDescriptor.with("name", "System Notification")
      .with("module", "Platform")
      .with("module_key", "platform");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor notificationType = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification.NOTIFICATION_TYPE_PROPERTY, String.class)
      ;
      PropertyDescriptor title = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification.TITLE_PROPERTY, String.class)
      ;
      PropertyDescriptor content = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification.CONTENT_PROPERTY, String.class)
      ;
      PropertyDescriptor isRead = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification.IS_READ_PROPERTY, String.class)
      ;
      PropertyDescriptor recipientId = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification.RECIPIENT_ID_PROPERTY, String.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification.NOTIFICATION_TYPE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "ALERT,INFO")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification.TITLE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Vehicle Maintenance Due,New Order Assigned")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification.CONTENT_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Truck #101 needs oil change.,Order #5005 assigned to you.")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification.IS_READ_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "false,true")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification.RECIPIENT_ID_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "driver_1,driver_1")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification.CREATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "createTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.systemnotification.SystemNotification.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
  private void registerSystemConfiguration() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfiguration.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfiguration.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfiguration::new);
      entityDescriptor.with("name", "System Configuration")
      .with("module", "Platform")
      .with("module_key", "platform");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfiguration.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor configKey = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfiguration.CONFIG_KEY_PROPERTY, String.class)
      ;
      PropertyDescriptor configValue = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfiguration.CONFIG_VALUE_PROPERTY, String.class)
      ;
      PropertyDescriptor description = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfiguration.DESCRIPTION_PROPERTY, String.class)
      ;
      PropertyDescriptor updatedAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfiguration.UPDATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfiguration.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfiguration.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "true")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfiguration.CONFIG_KEY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "max_load_kg,tax_rate")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfiguration.CONFIG_VALUE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "20000,0.08")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfiguration.DESCRIPTION_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Maximum load per truck in kg,Default sales tax rate")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfiguration.UPDATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "updateTime()")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("createFunction", "now")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.systemconfiguration.SystemConfiguration.VERSION_PROPERTY).with("isPassword", "false")
      .with("isVersion", "true")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("sqlType", "BIGINT")
      .with("isId", "false")
      .with("isBaseEntityField", "true")
      .with("isBool", "false")
      .with("isNumber", "false")
      .with("isString", "false")
      .with("isDate", "false")
      .with("snowflake_sqlType", "number")
      .with("graphqlType", "Long")
      .with("isTime", "false")
      .with("isText", "false");

      $factory.register(entityDescriptor);
  }
}