package frc.robot.subsystems.components;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoder; 
import com.ctre.phoenix.sensors.SensorInitializationStrategy; 

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotMath;

public class SwerveDriveModule {
    private TalonFX driveMotor;
    private TalonFX turnMotor;
    private double m_offset ; 
    private CANCoder enc;
    private String m_name;

    public SwerveDriveModule(String name, int driveMotorCanId, int turnMotorCanId, int encoderCanId, double encOffset) {
        m_name = name;
        m_offset = encOffset;

        driveMotor = new TalonFX(driveMotorCanId, Constants.CANBUS_NAME); 
        turnMotor = new TalonFX(turnMotorCanId, Constants.CANBUS_NAME);
        enc = new CANCoder(encoderCanId, Constants.CANBUS_NAME);

        enc.configSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);

        turnMotor.configFactoryDefault();
        driveMotor.configFactoryDefault();

        // set motor encoder to 0 when robot code starts
        var currentHeadingTicks = -1 * RobotMath.convertDegreesToTicks(getHeading());
        turnMotor.setSelectedSensorPosition(currentHeadingTicks);
        turnMotor.set(ControlMode.Position, currentHeadingTicks);

        SmartDashboard.putNumber(m_name + " initial heading", getHeading());

        turnMotor.setInverted(Constants.SwerveModuleConstants.TURN_MOTOR_INVERTED);
        turnMotor.config_kP(0, Constants.SwerveModuleConstants.TURN_MOTOR_KP);
        turnMotor.config_kI(0, Constants.SwerveModuleConstants.TURN_MOTOR_KI);
        turnMotor.config_kD(0, Constants.SwerveModuleConstants.TURN_MOTOR_KD);
        turnMotor.configIntegratedSensorAbsoluteRange(AbsoluteSensorRange.Unsigned_0_to_360);

        driveMotor.setInverted(Constants.SwerveModuleConstants.DRIVE_MOTOR_INVERTED);
        driveMotor.setNeutralMode(NeutralMode.Brake);
        
    }
  
    public void periodic() {
        SmartDashboard.putNumber(m_name + " Cancoder",  enc.getPosition());
        SmartDashboard.putNumber(m_name + " Cancoder with offset",  getHeading());
        SmartDashboard.putNumber(m_name + " error", turnMotor.getClosedLoopError());
        SmartDashboard.putNumber(m_name + " error deg", RobotMath.convertTicksToDegrees(turnMotor.getClosedLoopError()));
        SmartDashboard.putNumber(m_name + " turnMotor", RobotMath.convertTicksToDegrees(turnMotor.getSelectedSensorPosition()));
    }

    public double getHeading() {
        double v = Math.floorMod((int)enc.getPosition(), 360);
        
       return v - m_offset;
    }
 
    public void setModuleState(SwerveModuleState desiredState) {

        desiredState = SwerveModuleState.optimize(desiredState, Rotation2d.fromDegrees(getHeading()));

        SmartDashboard.putNumber(m_name + " Heading", desiredState.angle.getDegrees());

        double desiredPercentOutput = (desiredState.speedMetersPerSecond / Constants.SwerveDriveConstants.MAX_VELOCITY_METERS_PER_SECOND) * (Constants.SwerveModuleConstants.MAX_VOLTAGE / Constants.SwerveModuleConstants.NOMINAL_VOLTAGE);
        SmartDashboard.putNumber(m_name + " desiredVoltage", desiredPercentOutput);

        driveMotor.set(ControlMode.PercentOutput, desiredPercentOutput);
        turnMotor.set(ControlMode.Position, RobotMath.convertDegreesToTicks(desiredState.angle.getDegrees()));
    }

    public SwerveModuleState getState() {

        double speedTicksPer100miliSeconds = driveMotor.getSelectedSensorVelocity();

        double speedMetersPerSecond = RobotMath.calculateSpeedMetersPer100ms(speedTicksPer100miliSeconds);
         

        SmartDashboard.putNumber(m_name + "St", speedTicksPer100miliSeconds);
        SmartDashboard.putNumber(m_name + "Sm", speedMetersPerSecond);

        return new SwerveModuleState(speedMetersPerSecond, Rotation2d.fromDegrees( getHeading()));

    }
    
    public SwerveModulePosition getPosition () {
        // 2048 ticks per rotation
        // 6.75:1 gear ratio
        // 4" wheels
        double distanceMeters = RobotMath.distanceMeters(driveMotor.getSelectedSensorPosition());
        Rotation2d angle = Rotation2d.fromDegrees( getHeading());
        
        return new SwerveModulePosition(distanceMeters, angle);
    }
    
    public void resetTurnEncoders() {

        turnMotor.set(ControlMode.Position, 0);
    }

    public void setClosedLoopRampRate(double rate){
        driveMotor.configClosedloopRamp(rate);
    }
}
