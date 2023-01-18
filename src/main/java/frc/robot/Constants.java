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

    public static final String CANBUSNAME = "CANivore1";

    public class SwerveModuleConstants{
        public static final double MAX_VOLTAGE = 8.0;
        public static final double NOMINAL_VOLTAGE = 12;
        public static final double TURNMOTORKP = 0.2;
        public static final double TURNMOTORKI = 0.0;
        public static final double TURNMOTORKD = 0.1;
        public static final boolean TURNMOTORINVERTED = true;
        public static final boolean DRIVEMOTORINVERTED = false;
    }

    public class SwerveDriveConstants{
        public static final String FRONTLEFTMODULENAME = "FrontLeft";
        public static final int FRONTLEFTDRIVEMOTORID = 45;
        public static final int FRONTLEFTTURNMOTORID = 40;
        public static final int FRONTLEFTENCID = 50;
        public static final double FRONTLEFTOFFSET = 322.82;

        public static final String FRONTRIGHTMODULENAME = "FrontRight";
        public static final int FRONTRIGHTDRIVEMOTORID = 43;
        public static final int FRONTRIGHTTURNMOTORID = 44;
        public static final int FRONTRIGHTENCID = 51;
        public static final double FRONTRIGHTOFFSET = 339.87;

        public static final String BACKRIGHTMODULENAME = "BackRight";
        public static final int BACKRIGHTDRIVEMOTORID = 23;
        public static final int BACKRIGHTTURNMOTORID = 41;
        public static final int BACKRIGHTENCID = 52;
        public static final double BACKRIGHTOFFSET = 122.52;

        public static final String BACKLEFTMODULENAME = "BackLeft";
        public static final int BACKLEFTDRIVEMOTORID = 20;
        public static final int BACKLEFTTURNMOTORID = 22;
        public static final int BACKLEFTENCID = 53;
        public static final double BACKLEFTOFFSET = 153.10;

        public static final double WHEELBASE = 19.325 * 0.0254;
        public static final double SLEWRATELIMIT = 0.5;
        public static final int PIDGEONID = 13;
    }
}
