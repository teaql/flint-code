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
      PropertyDescriptor orderId = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.ORDER_ID_PROPERTY, String.class)
      ;
      PropertyDescriptor customer = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.CUSTOMER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.MOVING_ORDER_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.STATUS_PROPERTY, String.class)
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
      PropertyDescriptor pickupDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.PICKUP_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor deliveryDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.DELIVERY_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor specialInstructions = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.SPECIAL_INSTRUCTIONS_PROPERTY, String.class)
      ;
      PropertyDescriptor createTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.CREATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.UPDATE_TIME_PROPERTY, LocalDateTime.class)
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.ORDER_ID_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.PICKUP_DATE_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.DELIVERY_DATE_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.SPECIAL_INSTRUCTIONS_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.CREATE_TIME_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.UPDATE_TIME_PROPERTY).with("isPassword", "false")
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
      .with("candidates", "client_signature_data")
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
      PropertyDescriptor planId = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.PLAN_ID_PROPERTY, String.class)
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
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor scheduledDeparture = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.SCHEDULED_DEPARTURE_PROPERTY, String.class)
      ;
      PropertyDescriptor scheduledArrival = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.SCHEDULED_ARRIVAL_PROPERTY, String.class)
      ;
      PropertyDescriptor createTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.CREATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.UPDATE_TIME_PROPERTY, LocalDateTime.class)
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.PLAN_ID_PROPERTY).with("isPassword", "false")
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
      .with("zh_CN", "2023-10-01T08")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.SCHEDULED_ARRIVAL_PROPERTY).with("isPassword", "false")
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
      .with("zh_CN", "2023-10-01T18")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.CREATE_TIME_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan.UPDATE_TIME_PROPERTY).with("isPassword", "false")
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
      PropertyDescriptor routeId = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.ROUTE_ID_PROPERTY, String.class)
      ;
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor originWarehouse = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.ORIGIN_WAREHOUSE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.TRANSIT_ROUTE_LIST_AS_ORIGIN_WAREHOUSE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.class)
      ;
      PropertyDescriptor destinationWarehouse = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.DESTINATION_WAREHOUSE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.TRANSIT_ROUTE_LIST_AS_DESTINATION_WAREHOUSE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse.class)
      ;
      PropertyDescriptor distanceKm = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.DISTANCE_KM_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor estimatedDurationHours = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor createTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.CREATE_TIME_PROPERTY, LocalDateTime.class)
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.ROUTE_ID_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.NAME_PROPERTY).with("isPassword", "false")
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



      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.DISTANCE_KM_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.STATUS_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute.CREATE_TIME_PROPERTY).with("isPassword", "false")
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
      PropertyDescriptor slotId = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.SLOT_ID_PROPERTY, String.class)
      ;
      PropertyDescriptor movingOrder = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.MOVING_ORDER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.TIME_SLOT_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.class)
      ;
      PropertyDescriptor startTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.START_TIME_PROPERTY, String.class)
      ;
      PropertyDescriptor endTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.END_TIME_PROPERTY, String.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor createTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.CREATE_TIME_PROPERTY, LocalDateTime.class)
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.SLOT_ID_PROPERTY).with("isPassword", "false")
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


      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.START_TIME_PROPERTY).with("isPassword", "false")
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
      .with("zh_CN", "2023-10-01T09")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.END_TIME_PROPERTY).with("isPassword", "false")
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
      .with("zh_CN", "2023-10-01T10")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.STATUS_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot.CREATE_TIME_PROPERTY).with("isPassword", "false")
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
      PropertyDescriptor itemId = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.ITEM_ID_PROPERTY, String.class)
      ;
      PropertyDescriptor movingOrder = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.MOVING_ORDER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.CARGO_ITEM_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.class)
      ;
      PropertyDescriptor description = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.DESCRIPTION_PROPERTY, String.class)
      ;
      PropertyDescriptor category = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.CATEGORY_PROPERTY, String.class)
      ;
      PropertyDescriptor weightKg = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.WEIGHT_KG_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor volumeM3 = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.VOLUME_M3_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor value = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.VALUE_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor fragile = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.FRAGILE_PROPERTY, Boolean.class)
      ;
      PropertyDescriptor createTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.CREATE_TIME_PROPERTY, LocalDateTime.class)
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.ITEM_ID_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.CATEGORY_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.VALUE_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem.CREATE_TIME_PROPERTY).with("isPassword", "false")
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
      PropertyDescriptor addressId = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.ADDRESS_ID_PROPERTY, String.class)
      ;
      PropertyDescriptor movingOrder = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.MOVING_ORDER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.PICKUP_ADDRESS_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.class)
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
      PropertyDescriptor state = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.STATE_PROPERTY, String.class)
      ;
      PropertyDescriptor zipCode = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.ZIP_CODE_PROPERTY, String.class)
      ;
      PropertyDescriptor country = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.COUNTRY_PROPERTY, String.class)
      ;
      PropertyDescriptor contactName = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.CONTACT_NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor contactPhone = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.CONTACT_PHONE_PROPERTY, String.class)
      ;
      PropertyDescriptor createTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.CREATE_TIME_PROPERTY, LocalDateTime.class)
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.ADDRESS_ID_PROPERTY).with("isPassword", "false")
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


      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.ADDRESS_LINE1_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.ADDRESS_LINE2_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.CITY_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.STATE_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.ZIP_CODE_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.COUNTRY_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.CONTACT_NAME_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.CONTACT_PHONE_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress.CREATE_TIME_PROPERTY).with("isPassword", "false")
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
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor licensePlate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.LICENSE_PLATE_PROPERTY, String.class)
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
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.CAPACITY_KG_PROPERTY, BigDecimal.class)
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Truck Alpha,Van Beta")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.LICENSE_PLATE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "TRK-001,VAN-002")
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
      .with("candidates", "Volvo,Mercedes")
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
      .with("candidates", "FH16,Sprinter")
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
      .with("candidates", "2023,2022")
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
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "5000.00,2000.00")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Active,Active")
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
      PropertyDescriptor vehicle = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.VEHICLE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.DRIVER_ASSIGNMENT_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.class)
      ;
      PropertyDescriptor driver = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.DRIVER_PROPERTY, String.class)
      ;
      PropertyDescriptor startDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.START_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor endDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.END_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "1")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.VEHICLE_PROPERTY).with("candidates", "");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.DRIVER_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.START_DATE_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.END_DATE_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Active")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment.CREATED_AT_PROPERTY).with("isPassword", "false")
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
      PropertyDescriptor vehicle = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.VEHICLE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.GPS_LOG_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.class)
      ;
      PropertyDescriptor latitude = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.LATITUDE_PROPERTY, String.class)
      ;
      PropertyDescriptor longitude = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.LONGITUDE_PROPERTY, String.class)
      ;
      PropertyDescriptor timestamp = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.TIMESTAMP_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor speedKmh = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.SPEED_KMH_PROPERTY, String.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "1")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.VEHICLE_PROPERTY).with("candidates", "");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.LATITUDE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "40.7128")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.LONGITUDE_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.TIMESTAMP_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "2023-10-01T10:00:00")
      .with("sqlType", "TIMESTAMP")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("mssql_sqlType", "dateTime")
      .with("isDateTime", "true")
      .with("isDate", "true")
      .with("isString", "false")
      .with("graphqlType", "LocalTime")
      .with("isTime", "true")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.SPEED_KMH_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "60.5")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog.CREATED_AT_PROPERTY).with("isPassword", "false")
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
      PropertyDescriptor vehicle = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.VEHICLE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.FUEL_LOG_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.class)
      ;
      PropertyDescriptor fuelAmountLiters = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.FUEL_AMOUNT_LITERS_PROPERTY, String.class)
      ;
      PropertyDescriptor cost = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.COST_PROPERTY, String.class)
      ;
      PropertyDescriptor date = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "1")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.VEHICLE_PROPERTY).with("candidates", "");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.FUEL_AMOUNT_LITERS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "50.0")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.COST_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "75.00")
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
      .with("candidates", "2023-10-01")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog.CREATED_AT_PROPERTY).with("isPassword", "false")
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
      PropertyDescriptor vehicle = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.VEHICLE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.VEHICLE_MAINTENANCE_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.class)
      ;
      PropertyDescriptor serviceType = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.SERVICE_TYPE_PROPERTY, String.class)
      ;
      PropertyDescriptor serviceDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.SERVICE_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor cost = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.COST_PROPERTY, String.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "1")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.VEHICLE_PROPERTY).with("candidates", "");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.SERVICE_TYPE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Oil Change")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.SERVICE_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("candidates", "2023-09-15")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.COST_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "150.00")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Completed")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance.CREATED_AT_PROPERTY).with("isPassword", "false")
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
      PropertyDescriptor vehicle = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.VEHICLE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.TELEMATICS_DEVICE_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle.class)
      ;
      PropertyDescriptor firmwareVersion = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.FIRMWARE_VERSION_PROPERTY, String.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "1")
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
      .with("candidates", "TEL-001")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.VEHICLE_PROPERTY).with("candidates", "");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice.FIRMWARE_VERSION_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "1.2.0")
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
      .with("candidates", "Active")
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
      .with("candidates", "MAIN_HUB,WC_DEPOT")
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
      PropertyDescriptor unitType = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit.UNIT_TYPE_PROPERTY, String.class)
      ;
      PropertyDescriptor quantity = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit.QUANTITY_PROPERTY, Integer.class)
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit.UNIT_TYPE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "BOX,CRATE")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit.QUANTITY_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.lang.Integer")
      .with("candidates", "50,50")
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
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.CHECK_DATE_PROPERTY, String.class)
      ;
      PropertyDescriptor totalItems = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.TOTAL_ITEMS_PROPERTY, Integer.class)
      ;
      PropertyDescriptor discrepancies = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.DISCREPANCIES_PROPERTY, Integer.class)
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
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "2023-10-01,2023-10-15")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.TOTAL_ITEMS_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.lang.Integer")
      .with("candidates", "1000,1000")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck.DISCREPANCIES_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.lang.Integer")
      .with("candidates", "0,2")
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
      PropertyDescriptor loadWeight = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.pallet.Pallet.LOAD_WEIGHT_PROPERTY, BigDecimal.class)
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pallet.Pallet.LOAD_WEIGHT_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "500.00,0.00")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.pallet.Pallet.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "LOADED,EMPTY")
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
      PropertyDescriptor invoice = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.INVOICE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.STORAGE_FEE_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.class)
      ;
      PropertyDescriptor feeAmount = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.FEE_AMOUNT_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor currency = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.CURRENCY_PROPERTY, String.class)
      ;
      PropertyDescriptor periodStart = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.PERIOD_START_PROPERTY, String.class)
      ;
      PropertyDescriptor periodEnd = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.PERIOD_END_PROPERTY, String.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.STATUS_PROPERTY, String.class)
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.INVOICE_PROPERTY).with("candidates", "");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.FEE_AMOUNT_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "100.00,150.00")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.PERIOD_START_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "2023-10-01,2023-11-01")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.PERIOD_END_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "2023-10-31,2023-11-30")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "PAID,UNPAID")
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
      PropertyDescriptor shiftType = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.SHIFT_TYPE_PROPERTY, String.class)
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift.SHIFT_TYPE_PROPERTY).with("isPassword", "false")
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
      PropertyDescriptor date = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours.DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor hoursWorked = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours.HOURS_WORKED_PROPERTY, String.class)
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
      PropertyDescriptor staff = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.STAFF_PROPERTY, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.SAFETY_TRAINING_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.class)
      ;
      PropertyDescriptor courseName = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.COURSE_NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor completionDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.COMPLETION_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor certificateNumber = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.CERTIFICATE_NUMBER_PROPERTY, String.class)
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


      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.COURSE_NAME_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.COMPLETION_DATE_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.CERTIFICATE_NUMBER_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.STATUS_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.CREATED_AT_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining.UPDATED_AT_PROPERTY).with("isPassword", "false")
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
      .with("module_key", "crm")
      .with("constant", "false");

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
      PropertyDescriptor address = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.ADDRESS_PROPERTY, String.class)
      ;
      PropertyDescriptor city = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.CITY_PROPERTY, String.class)
      ;
      PropertyDescriptor country = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.COUNTRY_PROPERTY, String.class)
      ;
      PropertyDescriptor customerType = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.CUSTOMER_TYPE_PROPERTY, String.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "1001,1002")
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
      .with("candidates", "John Smith,Jane Doe")
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
      .with("candidates", "13800138001,13800138002")
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
      .with("candidates", "john@example.com,jane@example.com")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.ADDRESS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "123 Main St,456 Oak Ave")
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
      .with("candidates", "New York,London")
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
      .with("candidates", "USA,UK")
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
      .with("module_key", "crm")
      .with("constant", "false");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor contactPerson = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.CONTACT_PERSON_PROPERTY, String.class)
      ;
      PropertyDescriptor phone = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.PHONE_PROPERTY, String.class)
      ;
      PropertyDescriptor email = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.EMAIL_PROPERTY, String.class)
      ;
      PropertyDescriptor address = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.ADDRESS_PROPERTY, String.class)
      ;
      PropertyDescriptor city = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.CITY_PROPERTY, String.class)
      ;
      PropertyDescriptor country = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.COUNTRY_PROPERTY, String.class)
      ;
      PropertyDescriptor taxId = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.TAX_ID_PROPERTY, String.class)
      ;
      PropertyDescriptor customerType = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.CUSTOMER_TYPE_PROPERTY, String.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "2001,2002")
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
      .with("candidates", "Acme Corp,Global Tech")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.CONTACT_PERSON_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Bob Brown,Alice White")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.PHONE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "13800138003,13800138004")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.EMAIL_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "info@acme.com,contact@globaltech.com")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.ADDRESS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "789 Business Park,321 Tech Blvd")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.CITY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Chicago,Berlin")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.COUNTRY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "USA,Germany")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.TAX_ID_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "US123456,DE987654")
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
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor phone = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.PHONE_PROPERTY, String.class)
      ;
      PropertyDescriptor email = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.EMAIL_PROPERTY, String.class)
      ;
      PropertyDescriptor relationship = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.RELATIONSHIP_PROPERTY, String.class)
      ;
      PropertyDescriptor privateCustomer = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.PRIVATE_CUSTOMER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.CUSTOMER_CONTACT_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.class)
      ;
      PropertyDescriptor corporateCustomer = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.CORPORATE_CUSTOMER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.CUSTOMER_CONTACT_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "3001,3002")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "John Smith Jr,Acme Assistant")
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
      .with("candidates", "13800138005,13800138006")
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
      .with("candidates", "johnjr@example.com,assistant@acme.com")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.RELATIONSHIP_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Son,Admin")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.PRIVATE_CUSTOMER_PROPERTY).with("candidates", "");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact.CORPORATE_CUSTOMER_PROPERTY).with("candidates", "");

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
      PropertyDescriptor description = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.DESCRIPTION_PROPERTY, String.class)
      ;
      PropertyDescriptor estimatedCost = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.ESTIMATED_COST_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor currency = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.CURRENCY_PROPERTY, String.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor validUntil = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.VALID_UNTIL_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor privateCustomer = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.PRIVATE_CUSTOMER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.SERVICE_QUOTE_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.class)
      ;
      PropertyDescriptor corporateCustomer = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.CORPORATE_CUSTOMER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.SERVICE_QUOTE_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "4001,4002")
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
      .with("candidates", "Q-2023-001,Q-2023-002")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.DESCRIPTION_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Local Move,International Shipment")
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
      .with("candidates", "1500.00,12000.00")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "accepted,pending")
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
      .with("candidates", "2023-12-31,2024-06-30")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.PRIVATE_CUSTOMER_PROPERTY).with("candidates", "");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote.CORPORATE_CUSTOMER_PROPERTY).with("candidates", "");

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
      PropertyDescriptor comment = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.COMMENT_PROPERTY, String.class)
      ;
      PropertyDescriptor reviewDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.REVIEW_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor privateCustomer = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.PRIVATE_CUSTOMER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.FEEDBACK_REVIEW_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.class)
      ;
      PropertyDescriptor corporateCustomer = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.CORPORATE_CUSTOMER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.FEEDBACK_REVIEW_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "5001,5002")
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
      .with("candidates", "5,4")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.COMMENT_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Excellent service!,Good,but slightly delayed.")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.REVIEW_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("candidates", "2023-10-01,2023-10-15")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.PRIVATE_CUSTOMER_PROPERTY).with("candidates", "");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview.CORPORATE_CUSTOMER_PROPERTY).with("candidates", "");

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
      PropertyDescriptor corporateCustomer = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty.CORPORATE_CUSTOMER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.CUSTOMER_LOYALTY_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "6001,6002")
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
      .with("candidates", "1500,500")
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
      .with("candidates", "platinum,silver")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY).with("candidates", "");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty.CORPORATE_CUSTOMER_PROPERTY).with("candidates", "");

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
      PropertyDescriptor description = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.DESCRIPTION_PROPERTY, String.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.UPDATE_TIME_PROPERTY, LocalDateTime.class)
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
      .with("candidates", "Summer Move Sale")
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
      .with("candidates", "2023-06-01")
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
      .with("candidates", "2023-08-31")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.DESCRIPTION_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Discounts for summer relocations")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign.UPDATE_TIME_PROPERTY).with("isPassword", "false")
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
      PropertyDescriptor discountPercentage = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor maxUses = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.MAX_USES_PROPERTY, Integer.class)
      ;
      PropertyDescriptor currentUses = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.CURRENT_USES_PROPERTY, Integer.class)
      ;
      PropertyDescriptor expiryDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.EXPIRY_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.UPDATE_TIME_PROPERTY, LocalDateTime.class)
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
      .with("candidates", "SUMMER2023")
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
      .with("candidates", "15.00")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.MAX_USES_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.lang.Integer")
      .with("candidates", "1000")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.CURRENT_USES_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.lang.Integer")
      .with("candidates", "450")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.EXPIRY_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("candidates", "2023-08-31")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.discountcoupon.DiscountCoupon.UPDATE_TIME_PROPERTY).with("isPassword", "false")
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
      PropertyDescriptor email = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.EMAIL_PROPERTY, String.class)
      ;
      PropertyDescriptor phone = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.PHONE_PROPERTY, Integer.class)
      ;
      PropertyDescriptor source = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.SOURCE_PROPERTY, String.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor estimatedValue = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.ESTIMATED_VALUE_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.UPDATE_TIME_PROPERTY, LocalDateTime.class)
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
      .with("candidates", "John Doe Inquiry")
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
      .with("candidates", "john.doe@example.com")
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
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.lang.Integer")
      .with("candidates", "13800138000")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.SOURCE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "website")
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
      .with("candidates", "new")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.ESTIMATED_VALUE_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "5000.00")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.CREATED_TIME_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead.UPDATE_TIME_PROPERTY).with("isPassword", "false")
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
      PropertyDescriptor channelType = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.CHANNEL_TYPE_PROPERTY, String.class)
      ;
      PropertyDescriptor url = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.URL_PROPERTY, String.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.UPDATE_TIME_PROPERTY, LocalDateTime.class)
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
      .with("candidates", "Online Portal")
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
      .with("candidates", "digital")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.URL_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "//logistics.example.com")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("zh_CN", "https")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.STATUS_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel.UPDATE_TIME_PROPERTY).with("isPassword", "false")
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
      PropertyDescriptor totalSpend = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.TOTAL_SPEND_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor totalRevenue = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.TOTAL_REVENUE_PROPERTY, BigDecimal.class)
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
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.UPDATE_TIME_PROPERTY, LocalDateTime.class)
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


      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.TOTAL_SPEND_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.TOTAL_REVENUE_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi.UPDATE_TIME_PROPERTY).with("isPassword", "false")
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
      .with("module_key", "finance")
      .with("audit_mask_fields", "id");

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
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.UPDATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor movingOrder = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.MOVING_ORDER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.INVOICE_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder.class)
      ;
      PropertyDescriptor customer = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.CUSTOMER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.INVOICE_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "1001,1002")
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
      .with("candidates", "Outstanding Invoice,Paid Invoice")
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
      .with("candidates", "INV-2023-001,INV-2023-002")
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
      .with("candidates", "1500.00,2500.00")
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
      .with("candidates", "USD,EUR")
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
      .with("candidates", "OUTSTANDING,PAID")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.CREATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.UPDATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.MOVING_ORDER_PROPERTY).with("candidates", "");

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.invoice.Invoice.CUSTOMER_PROPERTY).with("candidates", "corporate_customer()");

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
      .with("module_key", "finance")
      .with("audit_mask_fields", "id");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor code = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.CODE_PROPERTY, String.class)
      ;
      PropertyDescriptor amount = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.AMOUNT_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor currency = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.CURRENCY_PROPERTY, String.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.UPDATED_AT_PROPERTY, LocalDateTime.class)
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
      .with("candidates", "2001,2002")
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
      .with("candidates", "Wire Transfer,Credit Card")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.CODE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "PAY-2023-001,PAY-2023-002")
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
      .with("candidates", "1500.00,2500.00")
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
      .with("candidates", "USD,EUR")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.STATUS_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.CREATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.UPDATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord.INVOICE_PROPERTY).with("candidates", "");

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
      .with("module_key", "finance")
      .with("audit_mask_fields", "id");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor code = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.CODE_PROPERTY, String.class)
      ;
      PropertyDescriptor amount = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.AMOUNT_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor currency = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.CURRENCY_PROPERTY, String.class)
      ;
      PropertyDescriptor category = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.CATEGORY_PROPERTY, String.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.UPDATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor staffMember = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.STAFF_MEMBER_PROPERTY, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.EXPENSE_ITEM_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "3001,3002")
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
      .with("candidates", "Fuel Expense,Toll Fee")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.CODE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "EXP-2023-001,EXP-2023-002")
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
      .with("candidates", "150.00,50.00")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.CATEGORY_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "FUEL,TOLL")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.CREATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.UPDATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem.STAFF_MEMBER_PROPERTY).with("candidates", "");

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
      .with("module_key", "finance")
      .with("audit_mask_fields", "id");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor code = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.CODE_PROPERTY, String.class)
      ;
      PropertyDescriptor amount = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.AMOUNT_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor currency = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.CURRENCY_PROPERTY, String.class)
      ;
      PropertyDescriptor taxRate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.TAX_RATE_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor taxType = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.TAX_TYPE_PROPERTY, String.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.UPDATED_AT_PROPERTY, LocalDateTime.class)
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
      .with("candidates", "4001,4002")
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
      .with("candidates", "VAT Record,Sales Tax")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.CODE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "TAX-2023-001,TAX-2023-002")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.AMOUNT_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "150.00,50.00")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.TAX_RATE_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "10.00,5.00")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.TAX_TYPE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "VAT,SALES")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.CREATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.UPDATED_AT_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("updateFunction", "now")
      .with("javaType", "java.time.LocalDateTime")
      .with("candidates", "")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord.INVOICE_PROPERTY).with("candidates", "");

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
  private void registerFinancialReport() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport::new);
      entityDescriptor.with("name", "Financial Report")
      .with("module", "Finance")
      .with("module_key", "finance")
      .with("audit_mask_fields", "id");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor code = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport.CODE_PROPERTY, String.class)
      ;
      PropertyDescriptor totalRevenue = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport.TOTAL_REVENUE_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor totalExpenses = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport.TOTAL_EXPENSES_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor periodStart = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport.PERIOD_START_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor periodEnd = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport.PERIOD_END_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor createdAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport.CREATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedAt = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport.UPDATED_AT_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport.ID_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport.NAME_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport.CODE_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport.TOTAL_REVENUE_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport.TOTAL_EXPENSES_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "30000.00")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport.PERIOD_START_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport.PERIOD_END_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("candidates", "2023-01-31")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport.CREATED_AT_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport.UPDATED_AT_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.financialreport.FinancialReport.VERSION_PROPERTY).with("isPassword", "false")
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
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.UPDATED_TIME_PROPERTY, LocalDateTime.class)
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.TITLE_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.START_DATE_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.END_DATE_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.STATUS_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.TOTAL_VALUE_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "10000.00")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.CREATED_TIME_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract.UPDATED_TIME_PROPERTY).with("isPassword", "false")
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
      PropertyDescriptor updatedTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.UPDATED_TIME_PROPERTY, LocalDateTime.class)
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy.UPDATED_TIME_PROPERTY).with("isPassword", "false")
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
      PropertyDescriptor claimAmount = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.CLAIM_AMOUNT_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor description = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.DESCRIPTION_PROPERTY, String.class)
      ;
      PropertyDescriptor resolutionDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.RESOLUTION_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.UPDATED_TIME_PROPERTY, LocalDateTime.class)
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.CLAIM_AMOUNT_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "5000.00")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.DESCRIPTION_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.RESOLUTION_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("candidates", "2023-06-15")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord.UPDATED_TIME_PROPERTY).with("isPassword", "false")
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
      PropertyDescriptor portOfEntry = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.PORT_OF_ENTRY_PROPERTY, String.class)
      ;
      PropertyDescriptor countryOfOrigin = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.COUNTRY_OF_ORIGIN_PROPERTY, String.class)
      ;
      PropertyDescriptor declaredValue = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.DECLARED_VALUE_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor clearanceDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.CLEARANCE_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.CREATED_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updatedTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.UPDATED_TIME_PROPERTY, LocalDateTime.class)
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.PORT_OF_ENTRY_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.COUNTRY_OF_ORIGIN_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.DECLARED_VALUE_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "15000.00")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.CLEARANCE_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("candidates", "2023-05-20")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.CREATED_TIME_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration.UPDATED_TIME_PROPERTY).with("isPassword", "false")
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
      PropertyDescriptor userId = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.USER_ID_PROPERTY, String.class)
      ;
      PropertyDescriptor ipAddress = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.IP_ADDRESS_PROPERTY, String.class)
      ;
      PropertyDescriptor details = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.DETAILS_PROPERTY, String.class)
      ;
      PropertyDescriptor createdTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.CREATED_TIME_PROPERTY, LocalDateTime.class)
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.ENTITY_TYPE_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.ENTITY_ID_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.USER_ID_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.IP_ADDRESS_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.DETAILS_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog.CREATED_TIME_PROPERTY).with("isPassword", "false")
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
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor email = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.EMAIL_PROPERTY, String.class)
      ;
      PropertyDescriptor phone = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.PHONE_PROPERTY, String.class)
      ;
      PropertyDescriptor passwordHash = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.PASSWORD_HASH_PROPERTY, String.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor createTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.CREATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.UPDATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.ID_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Admin User,Dispatcher,Driver")
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
      .with("candidates", "admin@logistics.com,dispatch@logistics.com,driver@logistics.com")
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
      .with("candidates", "13800000001,13800000002,13800000003")
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
      .with("candidates", "hashed_secret_123,hashed_secret_456,hashed_secret_789")
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
      .with("candidates", "ACTIVE,ACTIVE,ACTIVE")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.CREATE_TIME_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount.UPDATE_TIME_PROPERTY).with("isPassword", "false")
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
      .with("module_key", "platform")
      .with("constant", "true")
      .with("identifier", "code");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor code = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.CODE_PROPERTY, String.class)
      ;
      PropertyDescriptor description = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.DESCRIPTION_PROPERTY, String.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "1001,1002,1003,1004")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Administrator,Dispatcher,Driver,Customer Service")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.CODE_PROPERTY).with("identifier", "true")
      .with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "ADMIN,DISPATCHER,DRIVER,CS")
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
      .with("candidates", "Full system access,Manage orders and fleet,View assignments and logs,Handle inquiries")
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
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor resource = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.RESOURCE_PROPERTY, String.class)
      ;
      PropertyDescriptor action = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.ACTION_PROPERTY, String.class)
      ;
      PropertyDescriptor role = 
      entityDescriptor.addObjectProperty($factory, com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.ROLE_PROPERTY, com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.INTERNAL_TYPE, com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.ACCESS_PERMISSION_LIST_PROPERTY, com.doublechaintech.enterpriselogisticsservice.userrole.UserRole.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.ID_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(11)")
      .with("javaType", "java.lang.Long")
      .with("candidates", "2001,2002,2003,2004")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Read Orders,Write Orders,View Fleet,Manage Users")
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
      .with("candidates", "orders,orders,fleet,users")
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
      .with("candidates", "read,write,read,manage")
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

      entityDescriptor.findProperty(com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission.ROLE_PROPERTY).with("candidates", "");

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
}