// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.util.Color;

public final class Constants {

    public static final String CANBUS_NAME = "CANivore1";

    public static final class SwerveModuleConstants {
        //change this voltage to make the robot move faster
        public static final double MAX_VOLTAGE = 12.0;
        
        public static final double NOMINAL_VOLTAGE = 12;
        public static final double TURN_MOTOR_KP = 0.2;
        public static final double TURN_MOTOR_KI = 0.0;
        public static final double TURN_MOTOR_KD = 0.1;
        public static final boolean TURN_MOTOR_INVERTED = true;
        public static final boolean DRIVE_MOTOR_INVERTED = false;
    }

    public static final class SwerveDriveConstants {

        public static final String FRONT_LEFT_MODULE_NAME = "FrontLeft";
        public static final int FRONT_LEFT_DRIVE_MOTOR_ID = 20;
        public static final int FRONT_LEFT_TURN_MOTOR_ID = 22;
        public static final int FRONT_LEFT_ENC_ID = 53;
        public static final double FRONT_LEFT_OFFSET = 242.490;

        public static final String FRONT_RIGHT_MODULE_NAME = "FrontRight";
        public static final int FRONT_RIGHT_DRIVE_MOTOR_ID = 23;
        public static final int FRONT_RIGHT_TURN_MOTOR_ID = 41;
        public static final int FRONT_RIGHT_ENC_ID = 52;
        public static final double FRONT_RIGHT_OFFSET = 31.904;

        public static final String BACK_RIGHT_MODULE_NAME = "BackRight";
        public static final int BACK_RIGHT_DRIVE_MOTOR_ID = 45;
        public static final int BACK_RIGHT_TURN_MOTOR_ID = 40;
        public static final int BACK_RIGHT_ENC_ID = 50;
        public static final double BACK_RIGHT_OFFSET = 142.207;

        public static final String BACK_LEFT_MODULE_NAME = "BackLeft";
        public static final int BACK_LEFT_DRIVE_MOTOR_ID = 43;
        public static final int BACK_LEFT_TURN_MOTOR_ID = 44;
        public static final int BACK_LEFT_ENC_ID = 52;
        public static final double BACK_LEFT_OFFSET = 160.400;

        public static final double WHEEL_BASE = 19.325 * 0.0254;
        public static final double SLEW_RATE_LIMIT = 0.5;
        public static final int PIDGEON_ID = 13;
        public static final double MAX_VELOCITY_METERS_PER_SECOND = 5;
        public static final double MAX_OMEGA_RADIANS_PER_SECOND = 2.5;

        public static final double balanceSpeedMultipier = 0.0625;
        public static final double balanceRollThreshold = 4.5;
        public static final double balanceSpeed = 0.55;
        public static final double balanceHold = 0.05;
    }

    public static final class ArmSubsystemConstants {

        //----OFFSET-------------------------------
        public static final int offset_offset = 0;
        public static final double offset = 252.8590107 - offset_offset;
        //-----------------------------------------

        public static final int SparkMaxDeviceID = 32;
        public static final int SparkMaxFollowerDeviceID = 2;
        public static final int EncoderID = 0;

        public static final class ArmPositions{
                //public static final int shelf = 86 + offset_offset;
                public static final int top = 74 + offset_offset;
                public static final int middle = 52 + offset_offset;
                public static final int floor = 15 + offset_offset;
                //public static final int[] ArmPoses = {offset_offset,floor,middle,top,shelf};
                public static final int[] ArmPoses = {offset_offset,floor,middle,top};        
        }

        public static final double kP = 0.035;
        public static final double kI = 0.0;
        public static final double kD = 0.6;
        public static final double kFF = 0.0;
        public static final double maxOutput = 1;
        public static final double minOutput = -1;
        public static final double arbFeedFoward = 0;
        
    }

    public static final class WristSubsystemConstants {

        //----OFFSET-------------------------------
        public static final int offset_offset = 0;
        public static final double offset = 312.9485393 - offset_offset;
        //-----------------------------------------

        public static final int SparkMaxDeviceID = 4;
        public static final int SparkMaxFollowerDeviceID = 5;
        public static final int EncoderID = 1;

        public static final class WristPositions{
                public static final int top = 12 + offset_offset;
                public static final int middle = 29 + offset_offset;
                //public static final int shelf = 60 + offset_offset;
                public static final int floorCone = 90 + offset_offset;
                public static final int floorCube = 97 + offset_offset; 
                public static final int tiltedCone = 100 + offset_offset;
                //public static final int[] WristPoses = {offset_offset,top,middle,shelf,floorCone,floorCube,tiltedCone};    
                public static final int[] WristPoses = {offset_offset,top,middle,floorCone,floorCube,tiltedCone};    

                public static final int autoWrist = 91 + offset_offset;
        }

        public static final double kP = 0.15;
        public static final double kI = 0.0;
        public static final double kD = -1;
        public static final double kFF = 0.0;
        public static final double maxOutput = 1;
        public static final double minOutput = -1;
        public static final double arbFeedFoward = 0;
    }

    public static final class IntakeSubsystemConstants {

        public static final int SparkMaxPWMChannel = 1;
        public static final int SparkMaxDeviceID = 8;
    }

    public static final class XboxControllerConstants {
        public static final int DRIVER_CONTROLLER_USB_ID = 0;
        public static final int ASSIST_CONTROLLER_USB_ID = 1;
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

    public static final class LEDConstants{
        public static final int PWMPort = 5;
        public static final int StripLength = 16;

        public static final Color purple = new Color(255, 1, 255);
        public static final Color yellow = new Color(255, 128, 1);
        public static final Color orange = new Color(1, 255, 255);
    }

}
