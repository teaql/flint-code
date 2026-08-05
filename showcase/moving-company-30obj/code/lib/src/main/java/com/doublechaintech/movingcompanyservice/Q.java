package com.doublechaintech.movingcompanyservice;

import io.teaql.core.criteria.Operator;

public class Q  {
  public static com.doublechaintech.movingcompanyservice.movingevent.MovingEventRequest<com.doublechaintech.movingcompanyservice.movingevent.MovingEvent> movingEvents(){
      return new com.doublechaintech.movingcompanyservice.movingevent.MovingEventRequest(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.movingcompanyservice.movingevent.MovingEventRequest<com.doublechaintech.movingcompanyservice.movingevent.MovingEvent> movingEventsWithMinimalFields(){
      return new com.doublechaintech.movingcompanyservice.movingevent.MovingEventRequest(com.doublechaintech.movingcompanyservice.movingevent.MovingEvent.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomerRequest<com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer> privateCustomers(){
      return new com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomerRequest(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomerRequest<com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer> privateCustomersWithMinimalFields(){
      return new com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomerRequest(com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.movingcompanyservice.payment.PaymentRequest<com.doublechaintech.movingcompanyservice.payment.Payment> payments(){
      return new com.doublechaintech.movingcompanyservice.payment.PaymentRequest(com.doublechaintech.movingcompanyservice.payment.Payment.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.movingcompanyservice.payment.PaymentRequest<com.doublechaintech.movingcompanyservice.payment.Payment> paymentsWithMinimalFields(){
      return new com.doublechaintech.movingcompanyservice.payment.PaymentRequest(com.doublechaintech.movingcompanyservice.payment.Payment.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.movingcompanyservice.vehicle.VehicleRequest<com.doublechaintech.movingcompanyservice.vehicle.Vehicle> vehicles(){
      return new com.doublechaintech.movingcompanyservice.vehicle.VehicleRequest(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.class).selectSelf().withVersion(Operator.GREATER_THAN, 0l);
  }
  public static com.doublechaintech.movingcompanyservice.vehicle.VehicleRequest<com.doublechaintech.movingcompanyservice.vehicle.Vehicle> vehiclesWithMinimalFields(){
      return new com.doublechaintech.movingcompanyservice.vehicle.VehicleRequest(com.doublechaintech.movingcompanyservice.vehicle.Vehicle.class).withVersion(Operator.GREATER_THAN, 0l);
  }


  public static com.doublechaintech.movingcompanyservice.platform.PlatformRequest<com.doublechaintech.movingcompanyservice.platform.Platform> platforms(){
      return new com.doublechaintech.movingcompanyservice.platform.PlatformRequest(com.doublechaintech.movingcompanyservice.platform.Platform.class).selectSelf().
}