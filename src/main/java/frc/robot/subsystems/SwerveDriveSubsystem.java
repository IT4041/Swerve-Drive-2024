package frc.robot.subsystems;

import com.ctre.phoenix.sensors.Pigeon2;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.SlewRateLimiter; 
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.components.SwerveDriveModule;

public class SwerveDriveSubsystem extends SubsystemBase {
    
    private static SwerveDriveSubsystem m_inst = null;

    double wheelBase = 19.325 * 0.0254;
    // Locations for the swerve drive modules relative to the robot center.
    Translation2d m_frontLeftLocation = new Translation2d(wheelBase, wheelBase);
    Translation2d m_frontRightLocation = new Translation2d(wheelBase, -wheelBase);
    Translation2d m_backLeftLocation = new Translation2d(-wheelBase, wheelBase);
    Translation2d m_backRightLocation = new Translation2d(-wheelBase, -wheelBase);

    // Creating my kinematics object using the module locations
    SwerveDriveKinematics m_kinematics = new SwerveDriveKinematics(
            m_frontLeftLocation,
            m_frontRightLocation,
            m_backLeftLocation,
            m_backRightLocation);

    // Creating my odometry object from the kinematics object. Here,
    // our starting pose is 5 meters along the long end of the field and in the
    // center of the field along the short end, facing forward.
    SwerveDriveOdometry m_odometry;
    Pose2d m_pose;
    Joystick driveController = new Joystick(Constants.XboxControllerConstants.DRIVER_CONTROLLER);

    private final double FRONT_LEFT_ENC_OFFSET = 322.82;
    private final double FRONT_RIGHT_ENC_OFFSET = 339.87;
    private final double BACK_RIGHT_ENC_OFFSET = 122.52;
    private final double BACK_LEFT_ENC_OFFSET = 153.10;   

    SwerveDriveModule frontLeftModule = new SwerveDriveModule("FrontLeft", 45, 40, 50, FRONT_LEFT_ENC_OFFSET);
    SwerveDriveModule frontRightModule = new SwerveDriveModule("FrontRight", 43, 44, 51, FRONT_RIGHT_ENC_OFFSET);
    SwerveDriveModule backRightModule = new SwerveDriveModule("BackRight", 23, 41, 52, BACK_RIGHT_ENC_OFFSET);
    SwerveDriveModule backLeftModule = new SwerveDriveModule("BackLeft", 20, 22, 53, BACK_LEFT_ENC_OFFSET);

    ShuffleboardTab drivetrainTab;

    SlewRateLimiter fwdBakRateLimiter = new SlewRateLimiter(0.5);
    SlewRateLimiter leftRightRateLimiter = new SlewRateLimiter(0.5);
    SlewRateLimiter turnRateLimiter = new SlewRateLimiter(0.5);

    Pigeon2 pigeon = new Pigeon2(13, "CANivore1");

    //Global variable for drive rate speed
    double g_driveRate;

    public static final double MAX_VELOCITY_METERS_PER_SECOND = 4;
    public static final double MAX_OMEGA_RADIANS_PER_SECOND = 2.5;

    private SwerveDriveSubsystem() {
        pigeon.setYaw(0);

        m_odometry = new SwerveDriveOdometry(
                m_kinematics,
                getGyroHeading(),
             getModulePositions(),
                
                new Pose2d(0, 0, new Rotation2d()));
        
        SmartDashboard.putBoolean("Done", false);
    }

    public static SwerveDriveSubsystem getInstance() {
        if (m_inst == null) {
            m_inst = new SwerveDriveSubsystem();
        }
        return m_inst;
    }

    // this is alled every loop of the scheduler (~20ms)
    @Override
    public void periodic() {
        frontLeftModule.periodic();
        frontRightModule.periodic();
        backLeftModule.periodic();
        backRightModule.periodic();
       
        SmartDashboard.putNumber("Pidgeon yaw", pigeon.getYaw());

        // Update the pose
        // button 8 on xbox is three lines button
        if (driveController.getRawButton(Constants.XboxControllerConstants.THREE_LINES)) {
            frontLeftModule.resetTurnEncoders();
            frontRightModule.resetTurnEncoders();
            backRightModule.resetTurnEncoders();
            backLeftModule.resetTurnEncoders();
        }
        // button 7 on xbox it two squares
        if (driveController.getRawButton(Constants.XboxControllerConstants.TWO_SQUARES)) {
            pigeon.setYaw(0);
        }
        //Button 6 is right bumper/slow button
        //Speed is multiplied by 0.3 when held down
        if (driveController.getRawButton(Constants.XboxControllerConstants.RIGHT_BUMPER)) {
            setDriveRate(0.3);
        }
        else {
            setDriveRate(1);
        }

        SmartDashboard.putString("odo", m_odometry.getPoseMeters().toString());
        SmartDashboard.putString("odo", m_odometry.getPoseMeters().toString());

        m_odometry.update(getGyroHeading(), getModulePositions());
    }

    public Rotation2d getGyroHeading() {
        // // Get my gyro angle. We are negating the value because gyros return positive
        // // values as the robot turns clockwise. This is not standard convention that
        // is
        // // used by the WPILib classes.
        // var gyroAngle = Rotation2d.fromDegrees(-m_gyro.getAngle());
        return Rotation2d.fromDegrees(pigeon.getYaw());
    }

    private Pose2d getPose() {
        return m_odometry.getPoseMeters();
    }

    private SwerveModulePosition[] getModulePositions () {
        SwerveModulePosition[] positions = new SwerveModulePosition[4];
        positions[0] = frontLeftModule.getPosition();  
        positions[1] = frontRightModule.getPosition();  
        positions[2] = backRightModule.getPosition();
        positions[3] = backLeftModule.getPosition();  
       return positions;
        
    }

    public static double convertTicksToDegrees(double ticks) {
        // Cancoder: 2048 ticks per rotation
        // Steering gear ratio: 150/7:1
        double degrees = ticks * (1.0 / 2048.0) * (1.0 / (150 / 7)) * (360.0 / 1.0);
        return degrees;
    }

    public static double convertDegreesToTicks(double degrees) {

        double ticks = degrees * 1 / ((1.0 / 2048.0) * (1.0 / (150 / 7)) * (360.0 / 1.0));
        return ticks;
    }

    public void DriveWithJoystick(XboxController m_driver) {

        double leftRightDir = -1 * getDriveRate() * m_driver.getRawAxis(0); // positive number means left
        double fwdBackDir = -1 * getDriveRate() * m_driver.getRawAxis(1); // positive number means fwd
        double turn = -1 * getDriveRate() * m_driver.getRawAxis(4); // positive number means clockwise

        // fwdBackDir = fwdBakRateLimiter.calculate(fwdBackDir);
        // leftRightDir = leftRightRateLimiter.calculate(leftRightDir);
        // turn = turnRateLimiter.calculate(turn);

        double deadband = .05;

        if (-deadband < leftRightDir && leftRightDir < deadband) {
            leftRightDir = 0;
        }

        if (-deadband < fwdBackDir && fwdBackDir < deadband) {
            fwdBackDir = 0;
        }

        if (-deadband < turn && turn < deadband) {
            turn = 0;
        }

        // Example chassis speeds: 1 meter per second forward, 3 meters
        // per second to the left, and rotation at 1.5 radians per second
        // counteclockwise.
        // ChassisSpeeds speeds = new ChassisSpeeds(fwdBackDir, leftRightDir, turn);

        

        ChassisSpeeds speeds = ChassisSpeeds.fromFieldRelativeSpeeds(
                fwdBackDir * SwerveDriveSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
                leftRightDir * SwerveDriveSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
                turn * SwerveDriveSubsystem.MAX_OMEGA_RADIANS_PER_SECOND,
                Rotation2d.fromDegrees(pigeon.getYaw()));

        // Convert to module states
        SwerveModuleState[] moduleStates = m_kinematics.toSwerveModuleStates(speeds);

        setModuleStates(moduleStates);

    }

    public void setModuleStates(SwerveModuleState[] moduleStates) {

        SwerveDriveKinematics.desaturateWheelSpeeds(moduleStates, MAX_VELOCITY_METERS_PER_SECOND);

        SwerveModuleState frontLeft = moduleStates[0];
        SwerveModuleState frontRight = moduleStates[1];
        SwerveModuleState backRight = moduleStates[3];
        SwerveModuleState backLeft = moduleStates[2];

        frontLeftModule.setModuleState(frontLeft);
        frontRightModule.setModuleState(frontRight);
        backRightModule.setModuleState(backRight);
        backLeftModule.setModuleState(backLeft);
    }

    public Command traj(PathPlannerTrajectory traj, boolean isFirstPath) {

        PIDController xyController = new PIDController(5, 0, 0);

        return new SequentialCommandGroup(
                new InstantCommand(() -> {
                    if (isFirstPath)
                        this.m_odometry.resetPosition(getGyroHeading(),getModulePositions(),traj.getInitialHolonomicPose());
            
                }),
                new PPSwerveControllerCommand(
                        traj,
                        this::getPose,
                        this.m_kinematics,
                        xyController,
                        xyController,
                        new PIDController(5, 0, 0),
                        this::setModuleStates,
                        this),
                new InstantCommand(() -> {
                    SmartDashboard.putBoolean("Done", true);
                }));
    }

    public void setDriveRate(double driveRate) {
        g_driveRate = driveRate;
    }
    
    public double getDriveRate() {
        return g_driveRate;
    }

}