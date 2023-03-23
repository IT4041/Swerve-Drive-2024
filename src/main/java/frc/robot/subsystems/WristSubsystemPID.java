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


// import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class WristSubsystemPID extends SubsystemBase {

  private static WristSubsystemPID m_inst = null;

  private CANSparkMax m_motor;
  private CANSparkMax m_follower;
  private SparkMaxPIDController m_pidController;
  private SparkMaxAbsoluteEncoder m_AbsoluteEncoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, targetPosition;
  private int currWristPoseIndex = 0;

  public static WristSubsystemPID getInstance() {
    if (m_inst == null) {
      m_inst = new WristSubsystemPID();
    }
    return m_inst;
  }

  /** Creates a new Wrist. */
  private WristSubsystemPID() {

    m_motor = new CANSparkMax(Constants.WristSubsystemConstants.SparkMaxDeviceID, MotorType.kBrushed);
    m_follower = new CANSparkMax(Constants.WristSubsystemConstants.SparkMaxFollowerDeviceID, MotorType.kBrushed);
    m_motor.restoreFactoryDefaults();
    m_follower.restoreFactoryDefaults();

    m_AbsoluteEncoder = m_motor.getAbsoluteEncoder(Type.kDutyCycle);
    m_AbsoluteEncoder.setPositionConversionFactor(360);
    m_AbsoluteEncoder.setVelocityConversionFactor(1);
    m_AbsoluteEncoder.setInverted(true);
    m_AbsoluteEncoder.setZeroOffset(Constants.WristSubsystemConstants.offset);
  
    m_pidController = m_motor.getPIDController();
    m_pidController.setFeedbackDevice(m_AbsoluteEncoder);
    m_pidController.setPositionPIDWrappingEnabled(true);
    m_pidController.setPositionPIDWrappingMaxInput(360);
    m_pidController.setPositionPIDWrappingMinInput(0);

    m_pidController.setP(Constants.WristSubsystemConstants.kP);
    m_pidController.setI(Constants.WristSubsystemConstants.kI);
    m_pidController.setD(Constants.WristSubsystemConstants.kD);
    m_pidController.setFF(Constants.WristSubsystemConstants.kFF);
    m_pidController.setOutputRange(Constants.WristSubsystemConstants.minOutput, Constants.WristSubsystemConstants.maxOutput);

    m_motor.setIdleMode(IdleMode.kBrake);
    m_motor.setSmartCurrentLimit(80);
    m_motor.setClosedLoopRampRate(.5);

    m_follower.setIdleMode(IdleMode.kBrake);
    m_follower.setSmartCurrentLimit(80);
    m_follower.setClosedLoopRampRate(.5);
    m_follower.follow(m_motor);

    // Save the SPARK MAX configurations. If a SPARK MAX browns out during
    // operation, it will maintain the above configurations.
    m_motor.burnFlash();
    m_follower.burnFlash();

    this.targetPosition = 0;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Wrist Encoder", m_AbsoluteEncoder.getPosition());
    SmartDashboard.putNumber("Wrist target position", targetPosition);

    SmartDashboard.putBoolean("Wrist Titled Cone", currWristPoseIndex == 5);
    SmartDashboard.putBoolean("Wrist Floor Cube", currWristPoseIndex == 4);
    SmartDashboard.putBoolean("Wrist Floor Cone", currWristPoseIndex == 3);
    SmartDashboard.putBoolean("Wrist Shelf", currWristPoseIndex == 2);
    SmartDashboard.putBoolean("Wrist Top", currWristPoseIndex == 1);
    SmartDashboard.putBoolean("Wrist Zero", currWristPoseIndex == 0);

  }

  public void in() {
    m_pidController.setReference(.45, CANSparkMax.ControlType.kDutyCycle);
  }

  public void out() {
    m_pidController.setReference(-.45, CANSparkMax.ControlType.kDutyCycle);
  }

  public void stop() {
    m_pidController.setReference(0.0, CANSparkMax.ControlType.kDutyCycle);
  }

  public void setPosition(double position){
    m_pidController.setReference(position, CANSparkMax.ControlType.kPosition,0,Constants.WristSubsystemConstants.arbFeedFoward, ArbFFUnits.kPercentOut);
  }

  public void autoIntake(){
    this.targetPosition = Constants.WristSubsystemConstants.WristPositions.floorCube;
    this.setPosition(Constants.WristSubsystemConstants.WristPositions.floorCube);
  }

  public void zero(){
    this.targetPosition = 0;
    this.setPosition(0);
    currWristPoseIndex = 0;
  }

  public void top(){
    this.targetPosition = 1;
    this.setPosition(Constants.WristSubsystemConstants.WristPositions.top);
    currWristPoseIndex = 1;
  }

  public void stepDown() {

    if (currWristPoseIndex > 0) {
      currWristPoseIndex--;
    }

    this.targetPosition = Constants.WristSubsystemConstants.WristPositions.WristPoses[currWristPoseIndex];
    this.setPosition(Constants.WristSubsystemConstants.WristPositions.WristPoses[currWristPoseIndex]);
  }

  public void stepUp() {

    if (currWristPoseIndex < 5) {
      currWristPoseIndex++;
    }

    this.targetPosition = Constants.WristSubsystemConstants.WristPositions.WristPoses[currWristPoseIndex];
    this.setPosition(Constants.WristSubsystemConstants.WristPositions.WristPoses[currWristPoseIndex]);
  }
}