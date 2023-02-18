// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ArmSubsystemSimple extends SubsystemBase {

  private static ArmSubsystemSimple m_inst = null;

  private CANSparkMax m_motor;

  public static ArmSubsystemSimple getInstance() {
    if (m_inst == null) {
      m_inst = new ArmSubsystemSimple();
    }
    return m_inst;
  }

  /** Creates a new Arm. */
  private ArmSubsystemSimple() {

    m_motor = new CANSparkMax(Constants.ArmSubsystemConstants.SparkMaxDeviceID, MotorType.kBrushless);
    m_motor.restoreFactoryDefaults();
    m_motor.setIdleMode(IdleMode.kBrake);
    m_motor.setSmartCurrentLimit(12);
    m_motor.setOpenLoopRampRate(1);

    m_motor.burnFlash();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void up() {
    m_motor.set(1);
  }

  public void down() {
    m_motor.set(-0.65);
  }
  
  public void stop() {
    m_motor.set(0.0);
  }

}
