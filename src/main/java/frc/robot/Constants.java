// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final String CANBUS_NAME = "CANivore1";

    public class SwerveModuleConstants{
        public static final double MAX_VOLTAGE = 8.0;
        public static final double NOMINAL_VOLTAGE = 12;
        public static final double TURN_MOTOR_KP = 0.2;
        public static final double TURN_MOTOR_KI = 0.0;
        public static final double TURN_MOTOR_KD = 0.1;
        public static final boolean TURN_MOTOR_INVERTED = true;
        public static final boolean DRIVE_MOTOR_INVERTED = false;
    }

    public class SwerveDriveConstants{
        public static final String FRONT_LEFT_MODULE_NAME = "FrontLeft";
        public static final int FRONT_LEFT_DRIVE_MOTOR_ID = 45;
        public static final int FRONT_LEFT_TURN_MOTOR_ID = 40;
        public static final int FRONT_LEFT_ENC_ID = 50;
        public static final double FRONT_LEFT_OFFSET = 322.82;

        public static final String FRONT_RIGHT_MODULE_NAME = "FrontRight";
        public static final int FRONT_RIGHT_DRIVE_MOTOR_ID = 43;
        public static final int FRONT_RIGHT_TURN_MOTOR_ID = 44;
        public static final int FRONT_RIGHT_ENC_ID = 51;
        public static final double FRONT_RIGHT_OFFSET = 339.87;

        public static final String BACK_RIGHT_MODULE_NAME = "BackRight";
        public static final int BACK_RIGHT_DRIVE_MOTOR_ID = 23;
        public static final int BACK_RIGHT_TURN_MOTOR_ID = 41;
        public static final int BACK_RIGHT_ENC_ID = 52;
        public static final double BACK_RIGHT_OFFSET = 122.52;

        public static final String BACK_LEFT_MODULE_NAME = "BackLeft";
        public static final int BACK_LEFT_DRIVE_MOTOR_ID = 20;
        public static final int BACK_LEFT_TURN_MOTOR_ID = 22;
        public static final int BACK_LEFT_ENC_ID = 53;
        public static final double BACK_LEFT_OFFSET = 153.10;

        public static final double WHEEL_BASE = 19.325 * 0.0254;
        public static final double SLEW_RATE_LIMIT = 0.5;
        public static final int PIDGEON_ID = 13;
        public static final double MAX_VELOCITY_METERS_PER_SECOND = 4;
        public static final double MAX_OMEGA_RADIANS_PER_SECOND = 2.5;
    }

    public class XboxControllerConstants{
        public static final int DRIVER_CONTROLLER = 0;
        public static final int ASSISTANT_CONTROLLER = 1;
        public static final int A_BUTTON = 1;
        public static final int B_BUTTON = 2;
        public static final int X_BUTTON = 3;
        public static final int Y_BUTTON = 4;
        public static final int LEFT_BUMPER = 5;
        public static final int RIGHT_BUMPER = 6;
        public static final int TWO_SQUARES = 7;
        public static final int THREE_LINES = 8;
        public static final int LEFT_STICK_PRESS = 9;
        public static final int RIGHT_STICK_PRESS = 10;
    }
}
