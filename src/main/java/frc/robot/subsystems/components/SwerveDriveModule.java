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
import frc.robot.subsystems.SwerveDriveSubsystem;

public class SwerveDriveModule {
    private TalonFX driveMotor;
    private TalonFX turnMotor;
    private double m_offset ; 
    private CANCoder enc;
    private String m_name;

    public SwerveDriveModule(String name, int driveMotorCanId, int turnMotorCanId, int encoderCanId, double encOffset) {
        this.m_name = name;
        m_offset = encOffset;

        driveMotor = new TalonFX(driveMotorCanId, Constants.CANBUSNAME); 
        turnMotor = new TalonFX(turnMotorCanId, Constants.CANBUSNAME);
        enc = new CANCoder(encoderCanId, Constants.CANBUSNAME);

        enc.configSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);

        turnMotor.configFactoryDefault();
        driveMotor.configFactoryDefault();

        // set motor encoder to 0 when robot code starts
        var currentHeadingTicks = -1 * SwerveDriveSubsystem.convertDegreesToTicks(getHeading());
        turnMotor.setSelectedSensorPosition(currentHeadingTicks);
        turnMotor.set(ControlMode.Position, currentHeadingTicks);

        SmartDashboard.putNumber(m_name + " initial heading", getHeading());

        turnMotor.setInverted(Constants.SwerveModuleConstants.TURNMOTORINVERTED);
        turnMotor.config_kP(0, Constants.SwerveModuleConstants.TURNMOTORKP);
        turnMotor.config_kI(0, Constants.SwerveModuleConstants.TURNMOTORKI);
        turnMotor.config_kD(0, Constants.SwerveModuleConstants.TURNMOTORKD);
        turnMotor.configIntegratedSensorAbsoluteRange(AbsoluteSensorRange.Unsigned_0_to_360);

        driveMotor.setInverted(Constants.SwerveModuleConstants.DRIVEMOTORINVERTED);
        driveMotor.setNeutralMode(NeutralMode.Brake);
    }
  
    public void periodic() {
        SmartDashboard.putNumber(m_name + " Cancoder",  enc.getPosition());
        SmartDashboard.putNumber(m_name + " Cancoder with offset",  getHeading());
        SmartDashboard.putNumber(m_name + " error", turnMotor.getClosedLoopError());
        SmartDashboard.putNumber(m_name + " error deg", SwerveDriveSubsystem.convertTicksToDegrees(turnMotor.getClosedLoopError()));
        SmartDashboard.putNumber(m_name + " turnMotor", SwerveDriveSubsystem.convertTicksToDegrees(turnMotor.getSelectedSensorPosition()));
    }

    public double getHeading() {
        double v = Math.floorMod((int)enc.getPosition(), 360);
        
       return v - m_offset;
    }
 
    public void setModuleState(SwerveModuleState desiredState) {

        desiredState = SwerveModuleState.optimize(desiredState, Rotation2d.fromDegrees(getHeading()));

        SmartDashboard.putNumber(m_name + " Heading", desiredState.angle.getDegrees());

        double desiredPercentOutput = (desiredState.speedMetersPerSecond / SwerveDriveSubsystem.MAX_VELOCITY_METERS_PER_SECOND) * (Constants.SwerveModuleConstants.MAX_VOLTAGE / Constants.SwerveModuleConstants.NOMINAL_VOLTAGE);
        SmartDashboard.putNumber(m_name + " desiredVoltage", desiredPercentOutput);

        driveMotor.set(ControlMode.PercentOutput, desiredPercentOutput);
        turnMotor.set(ControlMode.Position, SwerveDriveSubsystem.convertDegreesToTicks(desiredState.angle.getDegrees()));
    }

    public SwerveModuleState getState() {

        double speedTicksPer100miliSeconds = driveMotor.getSelectedSensorVelocity();

        double speedMetersPerSecond = speedTicksPer100miliSeconds * (1 / 100d) * (1_000 / 1d) * (1 / 2048d) * (1 / 6.75)
                * ((4.0 * Math.PI) / 1d) * (.0254 / 1);

        SmartDashboard.putNumber(m_name + "St", speedTicksPer100miliSeconds);
        SmartDashboard.putNumber(m_name + "Sm", speedMetersPerSecond);

        return new SwerveModuleState(speedMetersPerSecond, Rotation2d.fromDegrees( getHeading()));

    }
    
    public SwerveModulePosition getPosition () {
        // 2048 ticks per rotation
        // 6.75:1 gear ratio
        // 4" wheels
        double distanceMeters = (1 / 2048d) * (1 / 6.75) * ((4.0 * Math.PI) / 1d) * (.0254 / 1) * driveMotor.getSelectedSensorPosition();
        Rotation2d angle = Rotation2d.fromDegrees( getHeading());
        
        return new SwerveModulePosition(distanceMeters, angle);
    }
    
    public void resetTurnEncoders() {

        turnMotor.set(ControlMode.Position, 0);
    }
}
