package org.sciborgs1155.robot;

public final class Ports {
  public static final class OI {
    public static final int OPERATOR = 0;
    public static final int DRIVER = 1;
  }

  public static final class Drive {
    public static final int FRONT_LEFT_DRIVE = 11;
    public static final int REAR_LEFT_DRIVE = 10;
    public static final int FRONT_RIGHT_DRIVE = 12;
    public static final int REAR_RIGHT_DRIVE = 13;

    public static final int FRONT_LEFT_TURNING = 15;
    public static final int REAR_LEFT_TURNING = 14;
    public static final int FRONT_RIGHT_TURNING = 16;
    public static final int REAR_RIGHT_TURNING = 17;
  }

  public static final class Elevator {
    public static final int MOTOR_LEFT_TOP = 21;
    public static final int MOTOR_LEFT_BOTTOM = 22;
    public static final int MOTOR_RIGHT_TOP = 23;
    public static final int MOTOR_RIGHT_BOTTOM = 24;
    public static final int SOLENOID = 25;
  }

  public static final class Forklift {
    public static final int SOLENOID_L = 31;
    public static final int SOLENOID_R = 32;
  }

  public static final class Intake {
    public static final int LEFT_INTAKE = 41;
    public static final int RIGHT_INTAKE = 42;
  }

  public static final class Wrist {
    public static final int LIMIT_SWITCH = 3;
    public static final int BACK_MOTOR = 43;
  }

  public static final class Leds {
    public static final int PORT = 5;
  }
}
