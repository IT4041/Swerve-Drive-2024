// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.components.ITCustomEncoder;

import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ArmSubsystem extends SubsystemBase {

  private static final int deviceID = 1;
  private CANSparkMax m_motor;
  private SparkMaxPIDController m_pidController;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

  private ITCustomEncoder armDutyCycleEncoder = new ITCustomEncoder(0);

  /** Creates a new Arm. */
  public ArmSubsystem() {

    armDutyCycleEncoder.getAbsolutePosition();

    m_motor = new CANSparkMax(deviceID, MotorType.kBrushless);
    m_motor.restoreFactoryDefaults();
    m_pidController = m_motor.getPIDController();
    m_pidController.setFeedbackDevice(armDutyCycleEncoder);
    m_pidController.setPositionPIDWrappingEnabled(false);

    m_pidController.setP(1);
    m_pidController.setI(0);
    m_pidController.setD(0);
    m_pidController.setFF(0);
    m_pidController.setOutputRange(0,0);

    m_motor.setIdleMode(IdleMode.kBrake);
    m_motor.setSmartCurrentLimit(12);
    m_motor.setClosedLoopRampRate(5);
    m_motor.setOpenLoopRampRate(5);
    m_motor.setSoftLimit(SoftLimitDirection.kForward, 1);
    m_motor.setSoftLimit(SoftLimitDirection.kReverse, -1);

    m_motor.burnFlash();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void up(){
    m_motor.set(0.25);
  }

  public void down(){
    m_motor.set(-0.25);
  }

}

