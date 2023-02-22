// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class IntakeSubsystem extends SubsystemBase {

  private static IntakeSubsystem m_inst = null;

  private CANSparkMax m_motor;
  // private SparkMaxPIDController m_pidController;
  // public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

  public static IntakeSubsystem getInstance() {
    if (m_inst == null) {
      m_inst = new IntakeSubsystem();
    }
    return m_inst;
  }

  /** Creates a new Arm. */
  private IntakeSubsystem() {

    m_motor = new CANSparkMax(Constants.IntakeSubsystemConstants.SparkMaxDeviceID, MotorType.kBrushless);
    m_motor.restoreFactoryDefaults();
    m_motor.setIdleMode(IdleMode.kCoast);
    m_motor.setSmartCurrentLimit(12);
    m_motor.burnFlash();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
  public void in() {
    m_motor.set(0.5);
  }

  public void in_hold() {
    m_motor.set(0.1);
    System.out.println("in_hold");
  }

  public void out() {
    m_motor.set(-0.5);
  }

  public void out_hold() {
    m_motor.set(-0.1);
    System.out.println("out_hold");
  }

  public void stop() {
    m_motor.stopMotor();
  }
}
