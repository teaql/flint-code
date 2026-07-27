package com.doublechaintech.movingcompanyservice;

import com.doublechaintech.movingcompanyservice.movingevent.MovingEvent;
import com.doublechaintech.movingcompanyservice.payment.Payment;
import com.doublechaintech.movingcompanyservice.platform.Platform;
import com.doublechaintech.movingcompanyservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.movingcompanyservice.vehicle.Vehicle;

public interface Constants  {
  public static final long MOVING_EVENT_ID = 1l;
  public static final MovingEvent MOVING_EVENT = MovingEvent.refer(MOVING_EVENT_ID);
  public static final long PRIVATE_CUSTOMER_ID = 1l;
  public static final PrivateCustomer PRIVATE_CUSTOMER = PrivateCustomer.refer(PRIVATE_CUSTOMER_ID);
  public static final long PAYMENT_ID = 1l;
  public static final Payment PAYMENT = Payment.refer(PAYMENT_ID);
  public static final long VEHICLE_ID = 1l;
  public static final Vehicle VEHICLE = Vehicle.refer(VEHICLE_ID);
  public static final long PLATFORM_ID = 1l;
  public static final Platform PLATFORM = Platform.refer(PLATFORM_ID);
}