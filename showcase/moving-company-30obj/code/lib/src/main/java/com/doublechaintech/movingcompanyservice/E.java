package com.doublechaintech.movingcompanyservice;

import com.doublechaintech.movingcompanyservice.movingevent.MovingEvent;
import com.doublechaintech.movingcompanyservice.movingevent.MovingEventExpression;
import com.doublechaintech.movingcompanyservice.payment.Payment;
import com.doublechaintech.movingcompanyservice.payment.PaymentExpression;
import com.doublechaintech.movingcompanyservice.platform.Platform;
import com.doublechaintech.movingcompanyservice.platform.PlatformExpression;
import com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomerExpression;
import com.doublechaintech.movingcompanyservice.vehicle.Vehicle;
import com.doublechaintech.movingcompanyservice.vehicle.VehicleExpression;
import io.teaql.core.value.ValueExpression;

public class E  {
  public static MovingEventExpression<MovingEvent, MovingEvent, MovingEvent> movingEvent(MovingEvent movingEvent){
      return new MovingEventExpression(new ValueExpression(movingEvent));
  }
  public static PrivateCustomerExpression<PrivateCustomer, PrivateCustomer, PrivateCustomer> privateCustomer(PrivateCustomer privateCustomer){
      return new PrivateCustomerExpression(new ValueExpression(privateCustomer));
  }
  public static PaymentExpression<Payment, Payment, Payment> payment(Payment payment){
      return new PaymentExpression(new ValueExpression(payment));
  }
  public static VehicleExpression<Vehicle, Vehicle, Vehicle> vehicle(Vehicle vehicle){
      return new VehicleExpression(new ValueExpression(vehicle));
  }
  public static PlatformExpression<Platform, Platform, Platform> platform(Platform platform){
      return new PlatformExpression(new ValueExpression(platform));
  }
}