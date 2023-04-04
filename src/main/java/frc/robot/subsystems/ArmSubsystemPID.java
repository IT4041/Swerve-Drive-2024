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
import com.revrobotics.CANSparkMax.ExternalFollower;
import com.revrobotics.CANSparkMax.IdleMode;
//import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ArmSubsystemPID extends SubsystemBase {

  private static ArmSubsystemPID m_inst = null;

  private CANSparkMax m_motor;
  private CANSparkMax m_follower;

  private SparkMaxPIDController m_pidController;
  private SparkMaxAbsoluteEncoder m_AbsoluteEncoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, targetPosition;
  private int currArmPoseIndex = 0;

  public static ArmSubsystemPID getInstance() {
    if (m_inst == null) {
      m_inst = new ArmSubsystemPID();
    }
    return m_inst;
  }

  /** Creates a new Arm. */
  private ArmSubsystemPID() {

    m_motor = new CANSparkMax(Constants.ArmSubsystemConstants.SparkMaxDeviceID, MotorType.kBrushless);
    m_follower = new CANSparkMax(Constants.ArmSubsystemConstants.SparkMaxFollowerDeviceID, MotorType.kBrushless);

    m_motor.restoreFactoryDefaults();
    m_follower.restoreFactoryDefaults();

    m_AbsoluteEncoder = m_motor.getAbsoluteEncoder(Type.kDutyCycle);
    m_AbsoluteEncoder.setPositionConversionFactor(360);
    m_AbsoluteEncoder.setVelocityConversionFactor(1);
    m_AbsoluteEncoder.setInverted(false);
    m_AbsoluteEncoder.setZeroOffset(Constants.ArmSubsystemConstants.offset);

    m_pidController = m_motor.getPIDController();
    m_pidController.setFeedbackDevice(m_AbsoluteEncoder);
    m_pidController.setPositionPIDWrappingEnabled(true);
    m_pidController.setPositionPIDWrappingMaxInput(360);
    m_pidController.setPositionPIDWrappingMinInput(0);

    m_pidController.setP(Constants.ArmSubsystemConstants.kP);
    m_pidController.setI(Constants.ArmSubsystemConstants.kI);
    m_pidController.setD(Constants.ArmSubsystemConstants.kD);
    m_pidController.setFF(Constants.ArmSubsystemConstants.kFF);
    m_pidController.setOutputRange(Constants.ArmSubsystemConstants.minOutput, Constants.ArmSubsystemConstants.maxOutput);

    m_motor.setIdleMode(IdleMode.kBrake);
    m_motor.setSmartCurrentLimit(20, 80, 100);
    m_motor.setClosedLoopRampRate(1);

    // m_motor.setSoftLimit(SoftLimitDirection.kForward, Constants.ArmSubsystemConstants.ArmPositions.shelf);
    // m_motor.setSoftLimit(SoftLimitDirection.kReverse, Constants.ArmSubsystemConstants.offset_offset);
    // m_motor.enableSoftLimit(SoftLimitDirection.kForward, true);
    // m_motor.enableSoftLimit(SoftLimitDirection.kReverse, true);
    
    m_follower.setIdleMode(IdleMode.kBrake);
    m_follower.setSmartCurrentLimit(20, 80, 100);
    m_follower.setClosedLoopRampRate(1);

    m_motor.follow(ExternalFollower.kFollowerDisabled, 0);
    m_follower.follow(m_motor);

    // Save the SPARK MAX configurations. If a SPARK MAX browns out during
    // operation, it will maintain the above configurations.
    m_motor.burnFlash();
    m_follower.burnFlash();

    this.targetPosition = Constants.ArmSubsystemConstants.offset_offset;
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Arm Encoder", m_AbsoluteEncoder.getPosition());
    SmartDashboard.putNumber("Arm target position", targetPosition);
    
    SmartDashboard.putBoolean("Arm shelf", currArmPoseIndex == 4);
    SmartDashboard.putBoolean("Arm Top", currArmPoseIndex == 3);
    SmartDashboard.putBoolean("Arm Middle", currArmPoseIndex == 2);
    SmartDashboard.putBoolean("Arm Low", currArmPoseIndex == 1);
    SmartDashboard.putBoolean("Arm Zero", currArmPoseIndex == 0);
  }
  
  public void up() {
    m_pidController.setReference(1, CANSparkMax.ControlType.kDutyCycle);
  }

  public void down() {
    m_pidController.setReference(-1, CANSparkMax.ControlType.kDutyCycle);
  }

  public void stop() {
    m_pidController.setReference(0.0, CANSparkMax.ControlType.kDutyCycle);
  }

  private void setPosition(double position) {
    m_pidController.setReference(position, CANSparkMax.ControlType.kPosition, 0, 0, ArbFFUnits.kPercentOut);
  }

  public void shelf() {
    this.targetPosition = Constants.ArmSubsystemConstants.ArmPositions.shelf;
    this.setPosition(Constants.ArmSubsystemConstants.ArmPositions.shelf);
    currArmPoseIndex = 4;
  }

  public void top() {
    this.targetPosition = Constants.ArmSubsystemConstants.ArmPositions.top;
    this.setPosition(Constants.ArmSubsystemConstants.ArmPositions.top);
    currArmPoseIndex = 3;
  }

  public void middle() {
    this.targetPosition = Constants.ArmSubsystemConstants.ArmPositions.middle;
    this.setPosition(Constants.ArmSubsystemConstants.ArmPositions.middle);
    currArmPoseIndex = 2;
  }

  public void floor() {
    this.targetPosition = Constants.ArmSubsystemConstants.ArmPositions.floor;
    this.setPosition(Constants.ArmSubsystemConstants.ArmPositions.floor);
    currArmPoseIndex = 1;
  }

  public void zero() {
    this.targetPosition = Constants.ArmSubsystemConstants.offset_offset;
    this.setPosition(Constants.ArmSubsystemConstants.offset_offset);
    currArmPoseIndex = 0;
  }

  public void stepDown() {

    if (currArmPoseIndex > 0) {
      currArmPoseIndex--;
    }

    this.targetPosition = Constants.ArmSubsystemConstants.ArmPositions.ArmPoses[currArmPoseIndex];
    this.setPosition(Constants.ArmSubsystemConstants.ArmPositions.ArmPoses[currArmPoseIndex]);
  }

  public void stepUp() {

    if (currArmPoseIndex < 4) {
      currArmPoseIndex++;
    }

    this.targetPosition = Constants.ArmSubsystemConstants.ArmPositions.ArmPoses[currArmPoseIndex];
    this.setPosition(Constants.ArmSubsystemConstants.ArmPositions.ArmPoses[currArmPoseIndex]);
  }

}
