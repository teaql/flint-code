package com.doublechaintech.movingcompanyservice;

import io.teaql.core.meta.EntityDescriptor;
import io.teaql.core.meta.EntityMetaAssembler;
import io.teaql.core.meta.EntityMetaFactory;
import io.teaql.core.meta.PropertyDescriptor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EntityMetaRegistry implements EntityMetaAssembler {
  private EntityMetaFactory $factory;

  @Override
  public void assemble(EntityMetaFactory factory) {
    this.$factory = factory;
    registerMovingEvent();
    registerPrivateCustomer();
    registerPayment();
    registerVehicle();
    registerPlatform();
  }
  private void registerMovingEvent() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent::new);
      entityDescriptor.with("name", "Moving Event")
      .with("module", "Operations")
      .with("module_key", "operations");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor customer = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.CUSTOMER_PROPERTY, String.class)
      ;
      PropertyDescriptor route = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.ROUTE_PROPERTY, String.class)
      ;
      PropertyDescriptor timeSlot = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.TIME_SLOT_PROPERTY, String.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor scheduledDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.SCHEDULED_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor notes = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.NOTES_PROPERTY, String.class)
      ;
      PropertyDescriptor createTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.CREATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.UPDATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.ID_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.CUSTOMER_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "customer()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.ROUTE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "route()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.TIME_SLOT_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "time_slot()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.STATUS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "PENDING")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.SCHEDULED_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("candidates", "2024-06-15")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.NOTES_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Long distance household move")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.CREATE_TIME_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.UPDATE_TIME_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.VERSION_PROPERTY).with("isPassword", "false")
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
      entityDescriptor.setType(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer::new);
      entityDescriptor.with("name", "Private Customer")
      .with("module", "Customer Management")
      .with("module_key", "customer_management");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor name = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer.NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor email = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer.EMAIL_PROPERTY, String.class)
      ;
      PropertyDescriptor phone = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer.PHONE_PROPERTY, Integer.class)
      ;
      PropertyDescriptor address = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer.ADDRESS_PROPERTY, String.class)
      ;
      PropertyDescriptor idNumber = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer.ID_NUMBER_PROPERTY, String.class)
      ;
      PropertyDescriptor createTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer.CREATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer.UPDATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer.ID_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer.NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "John Smith")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer.EMAIL_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "john.smith@email.com")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer.PHONE_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer.ADDRESS_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "123 Main Street, City")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer.ID_NUMBER_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "G12345678")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer.CREATE_TIME_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer.UPDATE_TIME_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer.VERSION_PROPERTY).with("isPassword", "false")
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
  private void registerPayment() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.movingcompanyservice.payment.Payment.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.movingcompanyservice.payment.Payment.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.movingcompanyservice.payment.Payment::new);
      entityDescriptor.with("name", "Payment")
      .with("module", "Finance")
      .with("module_key", "finance")
      .with("display_name", "Payment");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.payment.Payment.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor amount = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.payment.Payment.AMOUNT_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor paymentMethod = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.payment.Payment.PAYMENT_METHOD_PROPERTY, String.class)
      ;
      PropertyDescriptor transactionRef = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.payment.Payment.TRANSACTION_REF_PROPERTY, String.class)
      ;
      PropertyDescriptor paymentDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.payment.Payment.PAYMENT_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.payment.Payment.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor invoice = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.payment.Payment.INVOICE_PROPERTY, String.class)
      ;
      PropertyDescriptor customer = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.payment.Payment.CUSTOMER_PROPERTY, String.class)
      ;
      PropertyDescriptor createTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.payment.Payment.CREATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.payment.Payment.UPDATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.payment.Payment.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.payment.Payment.ID_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.payment.Payment.AMOUNT_PROPERTY).with("isPassword", "false")
      .with("db2_sqlType", "decimal(19,7)")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(19,7)")
      .with("javaType", "java.math.BigDecimal")
      .with("candidates", "1500.00")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.payment.Payment.PAYMENT_METHOD_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Credit Card")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.payment.Payment.TRANSACTION_REF_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "TXN-2024-001")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.payment.Payment.PAYMENT_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("candidates", "2024-01-15")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.payment.Payment.STATUS_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.payment.Payment.INVOICE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "invoice()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.payment.Payment.CUSTOMER_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "customer()")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.payment.Payment.CREATE_TIME_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.payment.Payment.UPDATE_TIME_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.payment.Payment.VERSION_PROPERTY).with("isPassword", "false")
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
      entityDescriptor.setType(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.movingcompanyservice.vehicle.Vehicle::new);
      entityDescriptor.with("name", "Vehicle")
      .with("module", "AssetManagement")
      .with("module_key", "asset_management");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor internalType = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.INTERNAL_TYPE_PROPERTY, String.class)
      ;
      PropertyDescriptor displayName = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.DISPLAY_NAME_PROPERTY, String.class)
      ;
      PropertyDescriptor vehicleType = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.VEHICLE_TYPE_PROPERTY, String.class)
      ;
      PropertyDescriptor licensePlate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.LICENSE_PLATE_PROPERTY, String.class)
      ;
      PropertyDescriptor capacityCubicMeters = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.CAPACITY_CUBIC_METERS_PROPERTY, BigDecimal.class)
      ;
      PropertyDescriptor purchaseDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.PURCHASE_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor status = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.STATUS_PROPERTY, String.class)
      ;
      PropertyDescriptor lastMaintenanceDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.LAST_MAINTENANCE_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor nextMaintenanceDate = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.NEXT_MAINTENANCE_DATE_PROPERTY, LocalDate.class)
      ;
      PropertyDescriptor createTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.CREATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.UPDATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.VERSION_PROPERTY, Long.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.ID_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.INTERNAL_TYPE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "com.doublechaintech.movingcompany.vehicle.Vehicle")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.DISPLAY_NAME_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Vehicle")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.VEHICLE_TYPE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "Truck")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.LICENSE_PLATE_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "AB123CD")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.CAPACITY_CUBIC_METERS_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.PURCHASE_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("candidates", "2020-01-15")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.STATUS_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.LAST_MAINTENANCE_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("candidates", "2023-06-20")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.NEXT_MAINTENANCE_DATE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("javaType", "java.time.LocalDate")
      .with("candidates", "2024-06-20")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.CREATE_TIME_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.UPDATE_TIME_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.VERSION_PROPERTY).with("isPassword", "false")
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
  private void registerPlatform() {
      EntityDescriptor entityDescriptor = new EntityDescriptor();
      entityDescriptor.setType(com.doublechaintech.movingcompanyservice.platform.Platform.INTERNAL_TYPE);
      entityDescriptor.setTargetType(com.doublechaintech.movingcompanyservice.platform.Platform.class);
      entityDescriptor.setEntitySupplier(com.doublechaintech.movingcompanyservice.platform.Platform::new);
      entityDescriptor.with("name", "Platform")
      .with("module", "Platform")
      .with("module_key", "platform");

      PropertyDescriptor id = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.platform.Platform.ID_PROPERTY, Long.class)
      ;
      PropertyDescriptor version = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.platform.Platform.VERSION_PROPERTY, String.class)
      ;
      PropertyDescriptor apiVersion = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.platform.Platform.API_VERSION_PROPERTY, String.class)
      ;
      PropertyDescriptor maintenanceMode = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.platform.Platform.MAINTENANCE_MODE_PROPERTY, Boolean.class)
      ;
      PropertyDescriptor createTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.platform.Platform.CREATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      PropertyDescriptor updateTime = 
      entityDescriptor.addSimpleProperty(com.doublechaintech.movingcompanyservice.platform.Platform.UPDATE_TIME_PROPERTY, LocalDateTime.class)
      ;
      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.platform.Platform.ID_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.platform.Platform.VERSION_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "1.0.0")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.platform.Platform.API_VERSION_PROPERTY).with("isPassword", "false")
      .with("max", "100")
      .with("isVersion", "false")
      .with("javaType", "java.lang.String")
      .with("candidates", "v2")
      .with("sqlType", "VARCHAR(<max>)")
      .with("isId", "false")
      .with("isBool", "false")
      .with("isBaseEntityField", "false")
      .with("isNumber", "false")
      .with("isString", "true")
      .with("isDate", "false")
      .with("graphqlType", "String")
      .with("isTime", "false")
      .with("isText", "false");

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.platform.Platform.MAINTENANCE_MODE_PROPERTY).with("isPassword", "false")
      .with("isVersion", "false")
      .with("oracle_sqlType", "number(1)")
      .with("javaType", "java.lang.Boolean")
      .with("candidates", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.platform.Platform.CREATE_TIME_PROPERTY).with("isPassword", "false")
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

      entityDescriptor.findProperty(com.doublechaintech.movingcompanyservice.platform.Platform.UPDATE_TIME_PROPERTY).with("isPassword", "false")
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

      $factory.register(entityDescriptor);
  }
}