// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.SparkMaxAbsoluteEncoder.Type;
import com.revrobotics.SparkMaxPIDController.ArbFFUnits;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ArmSubsystemPID extends SubsystemBase {

  private static ArmSubsystemPID m_inst = null;

  private CANSparkMax m_motor;
  private SparkMaxPIDController m_pidController;
  private SparkMaxAbsoluteEncoder m_AbsoluteEncoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;;

  public static ArmSubsystemPID getInstance() {
    if (m_inst == null) {
      m_inst = new ArmSubsystemPID();
    }
    return m_inst;
  }

  /** Creates a new Arm. */
  private ArmSubsystemPID() {

    m_motor = new CANSparkMax(Constants.ArmSubsystemConstants.SparkMaxDeviceID, MotorType.kBrushless);
    m_motor.restoreFactoryDefaults();

    m_AbsoluteEncoder = m_motor.getAbsoluteEncoder(Type.kDutyCycle);
  
    m_pidController = m_motor.getPIDController();
    m_pidController.setFeedbackDevice(m_AbsoluteEncoder);

    // Apply position and velocity conversion factors for the turning encoder. We
    // want these in radians and radians per second to use with WPILib's swerve
    // APIs.
    m_AbsoluteEncoder.setPositionConversionFactor(360);
    m_AbsoluteEncoder.setVelocityConversionFactor(1);

    // Invert the turning encoder, since the output shaft rotates in the opposite
    // direction of
    // the steering motor in the MAXSwerve Module.
    m_AbsoluteEncoder.setInverted(true);

    // Enable PID wrap around for the turning motor. This will allow the PID
    // controller to go through 0 to get to the setpoint i.e. going from 350 degrees
    // to 10 degrees will go through 0 rather than the other direction which is a
    // longer route.
    m_pidController.setPositionPIDWrappingEnabled(false);

    // Set the PID gains for the turning motor. Note these are example gains, and
    // you
    // may need to tune them for your own robot!
    m_pidController.setP(1);
    m_pidController.setI(0);
    m_pidController.setD(0);
    m_pidController.setFF(0);
    m_pidController.setOutputRange(-1, 1);

    m_motor.setIdleMode(IdleMode.kBrake);
    m_motor.setSmartCurrentLimit(12);
    m_motor.setClosedLoopRampRate(5);
    m_motor.setSoftLimit(SoftLimitDirection.kForward, 360);
    m_motor.setSoftLimit(SoftLimitDirection.kReverse, 0);

    // Save the SPARK MAX configurations. If a SPARK MAX browns out during
    // operation, it will maintain the above configurations.
    m_motor.burnFlash();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Arm Encoder", m_AbsoluteEncoder.getPosition());
  }

  public void up() {
    m_motor.set(0.25);
  }

  public void down() {
    m_motor.set(-0.25);
  }
  
  public void stop() {
    m_motor.set(0.0);
  }

  public void setPosition(double position){
    m_pidController.setReference(position, CANSparkMax.ControlType.kPosition,0,.1, ArbFFUnits.kPercentOut);
  }
}
