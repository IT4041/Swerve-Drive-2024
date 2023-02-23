// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final String CANBUS_NAME = "CANivore1";

    public static final class ScoringPoses {

        private static final Pose2d RED1_Pose2d = new Pose2d(Units.inchesToMeters(656.49), Units.inchesToMeters(20.19),
                Rotation2d.fromDegrees(0));
        private static final Pose2d RED2_Pose2d = new Pose2d(Units.inchesToMeters(656.49), Units.inchesToMeters(42.19),
                Rotation2d.fromDegrees(0));
        private static final Pose2d RED3_Pose2d = new Pose2d(Units.inchesToMeters(656.49), Units.inchesToMeters(64.19),
                Rotation2d.fromDegrees(0));
        private static final Pose2d RED4_Pose2d = new Pose2d(Units.inchesToMeters(656.49), Units.inchesToMeters(86.74),
                Rotation2d.fromDegrees(0));
        private static final Pose2d RED5_Pose2d = new Pose2d(Units.inchesToMeters(656.49), Units.inchesToMeters(108.74),
                Rotation2d.fromDegrees(0));
        private static final Pose2d RED6_Pose2d = new Pose2d(Units.inchesToMeters(656.49), Units.inchesToMeters(130.19),
                Rotation2d.fromDegrees(0));
        private static final Pose2d RED7_Pose2d = new Pose2d(Units.inchesToMeters(656.49), Units.inchesToMeters(152.19),
                Rotation2d.fromDegrees(0));
        private static final Pose2d RED8_Pose2d = new Pose2d(Units.inchesToMeters(656.49), Units.inchesToMeters(174.19),
                Rotation2d.fromDegrees(0));
        private static final Pose2d RED9_Pose2d = new Pose2d(Units.inchesToMeters(656.49), Units.inchesToMeters(196.19),
                Rotation2d.fromDegrees(0));

        private static final Pose2d BLUE1_Pose2d = new Pose2d(Units.inchesToMeters(54.73), Units.inchesToMeters(196.19),
                Rotation2d.fromDegrees(180));
        private static final Pose2d BLUE2_Pose2d = new Pose2d(Units.inchesToMeters(54.73), Units.inchesToMeters(174.19),
                Rotation2d.fromDegrees(180));
        private static final Pose2d BLUE3_Pose2d = new Pose2d(Units.inchesToMeters(54.73), Units.inchesToMeters(152.19),
                Rotation2d.fromDegrees(180));
        private static final Pose2d BLUE4_Pose2d = new Pose2d(Units.inchesToMeters(54.73), Units.inchesToMeters(130.74),
                Rotation2d.fromDegrees(180));
        private static final Pose2d BLUE5_Pose2d = new Pose2d(Units.inchesToMeters(54.73), Units.inchesToMeters(108.74),
                Rotation2d.fromDegrees(180));
        private static final Pose2d BLUE6_Pose2d = new Pose2d(Units.inchesToMeters(54.73), Units.inchesToMeters(86.19),
                Rotation2d.fromDegrees(180));
        private static final Pose2d BLUE7_Pose2d = new Pose2d(Units.inchesToMeters(54.73), Units.inchesToMeters(64.19),
                Rotation2d.fromDegrees(180));
        private static final Pose2d BLUE8_Pose2d = new Pose2d(Units.inchesToMeters(54.73), Units.inchesToMeters(42.19),
                Rotation2d.fromDegrees(180));
        private static final Pose2d BLUE9_Pose2d = new Pose2d(Units.inchesToMeters(54.73), Units.inchesToMeters(20.19),
                Rotation2d.fromDegrees(180));
    }

    public static enum ElementType {
        CUBE,
        CONE
    }

    public static final class ScoringLocationDetails {
        public Pose2d pose;
        public ElementType item;

        public ScoringLocationDetails(Pose2d in_pose, ElementType in_item) {

            this.pose = in_pose;
            this.item = in_item;
        }
    }

    public static final class ScoringLocations {

        private static final ScoringLocationDetails[] RedLocations = {
                new ScoringLocationDetails(ScoringPoses.RED1_Pose2d, ElementType.CONE),
                new ScoringLocationDetails(ScoringPoses.RED2_Pose2d, ElementType.CUBE),
                new ScoringLocationDetails(ScoringPoses.RED3_Pose2d, ElementType.CONE),
                new ScoringLocationDetails(ScoringPoses.RED4_Pose2d, ElementType.CONE),
                new ScoringLocationDetails(ScoringPoses.RED5_Pose2d, ElementType.CUBE),
                new ScoringLocationDetails(ScoringPoses.RED6_Pose2d, ElementType.CONE),
                new ScoringLocationDetails(ScoringPoses.RED7_Pose2d, ElementType.CONE),
                new ScoringLocationDetails(ScoringPoses.RED8_Pose2d, ElementType.CUBE),
                new ScoringLocationDetails(ScoringPoses.RED9_Pose2d, ElementType.CONE)
        };

        private static final ScoringLocationDetails[] BlueLocations = {
                new ScoringLocationDetails(ScoringPoses.BLUE1_Pose2d, ElementType.CONE),
                new ScoringLocationDetails(ScoringPoses.BLUE2_Pose2d, ElementType.CUBE),
                new ScoringLocationDetails(ScoringPoses.BLUE3_Pose2d, ElementType.CONE),
                new ScoringLocationDetails(ScoringPoses.BLUE4_Pose2d, ElementType.CONE),
                new ScoringLocationDetails(ScoringPoses.BLUE5_Pose2d, ElementType.CUBE),
                new ScoringLocationDetails(ScoringPoses.BLUE6_Pose2d, ElementType.CONE),
                new ScoringLocationDetails(ScoringPoses.BLUE7_Pose2d, ElementType.CONE),
                new ScoringLocationDetails(ScoringPoses.BLUE8_Pose2d, ElementType.CUBE),
                new ScoringLocationDetails(ScoringPoses.BLUE9_Pose2d, ElementType.CONE)
        };

        public static final ScoringLocationDetails getLocation(int ID, boolean isRed) {
            if (isRed) {
                return RedLocations[ID - 1];
            } else {
                return BlueLocations[ID - 1];
            }

        }
    }

    public static final class AprilTagCoordinates {

        private static final Pose2d ID1 = new Pose2d(Units.inchesToMeters(610.77), Units.inchesToMeters(42.19),
                Rotation2d.fromDegrees(180));
        private static final Pose2d ID2 = new Pose2d(Units.inchesToMeters(610.77), Units.inchesToMeters(108.19),
                Rotation2d.fromDegrees(180));
        private static final Pose2d ID3 = new Pose2d(Units.inchesToMeters(610.77), Units.inchesToMeters(174.19),
                Rotation2d.fromDegrees(180));
        private static final Pose2d ID4 = new Pose2d(Units.inchesToMeters(636.96), Units.inchesToMeters(265.74),
                Rotation2d.fromDegrees(180));
        private static final Pose2d ID5 = new Pose2d(Units.inchesToMeters(14.25), Units.inchesToMeters(265.74),
                Rotation2d.fromDegrees(0));
        private static final Pose2d ID6 = new Pose2d(Units.inchesToMeters(40.45), Units.inchesToMeters(174.19),
                Rotation2d.fromDegrees(0));
        private static final Pose2d ID7 = new Pose2d(Units.inchesToMeters(40.45), Units.inchesToMeters(108.19),
                Rotation2d.fromDegrees(0));
        private static final Pose2d ID8 = new Pose2d(Units.inchesToMeters(40.45), Units.inchesToMeters(42.19),
                Rotation2d.fromDegrees(0));

        private static final Pose2d[] AprilTagsPoses = { ID1, ID2, ID3, ID4, ID5, ID6, ID7, ID8 };

        public static final Pose2d getAprilTagPose(int targetID) {

            return AprilTagsPoses[targetID - 1];
        }
    }

    public static final class SwerveModuleConstants {
        public static final double MAX_VOLTAGE = 8.0;
        public static final double NOMINAL_VOLTAGE = 12;
        public static final double TURN_MOTOR_KP = 0.2;
        public static final double TURN_MOTOR_KI = 0.0;
        public static final double TURN_MOTOR_KD = 0.1;
        public static final boolean TURN_MOTOR_INVERTED = true;
        public static final boolean DRIVE_MOTOR_INVERTED = false;
    }

    public static final class SwerveDriveConstants {
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

    public static final class ArmSubsystemConstants {

        public static final int SparkMaxDeviceID = 32;
        public static final int EncoderID = 0;

        public static final class ArmPositions{
                public static final int top = 70;
                public static final int middle = 35;
                public static final int floor = 15;    
        }
    }

    public static final class WristSubsystemConstants {

        public static final int SparkMaxDeviceID = 4;
        public static final int EncoderID = 1;

        public static final class WristPositions{
                public static final int top = 80;
                public static final int middle = 60;
                public static final int floorCube = 50;
                public static final int floorCone = 35; 
                public static final int tiltedCone = 25;   
        }
    }

    public static final class IntakeSubsystemConstants {

        public static final int SparkMaxPWMChannel = 0;
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

    public static final class AutoPilotController {
        public static final int AUTOPILOT_CONTROLLER_USB_ID = 1;
        public static final int GREEN_LEFT = 1;
        public static final int GREEN_CENTER = 2;
        public static final int GREEN_RIGHT = 3;
        public static final int YELLOW_LEFT = 4;
        public static final int YELLOW_CENTER = 5;
        public static final int YELLOW_RIGHT = 6;
        public static final int BLUE_LEFT = 7;
        public static final int BLUE_CENTER = 8;
        public static final int BLUE_RIGHT = 9;
        public static final int CLEAR_TOP = 10;
        public static final int CLEAR_MIDDLE = 11;
        public static final int CLEAR_BOTTOM = 12; 
    }

}
