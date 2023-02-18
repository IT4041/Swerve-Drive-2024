// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class WristSubsystemSimple extends SubsystemBase {

  private static WristSubsystemSimple m_inst = null;

  private CANSparkMax m_motor;

  public static WristSubsystemSimple getInstance() {
    if (m_inst == null) {
      m_inst = new WristSubsystemSimple();
    }
    return m_inst;
  }

  /** Creates a new Arm. */
  private WristSubsystemSimple() {

    m_motor = new CANSparkMax(Constants.WristSubsystemConstants.SparkMaxDeviceID, MotorType.kBrushed);
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

  public void in() {
    m_motor.set(1);
  }

  public void out() {
    m_motor.set(-1);
  }
  
  public void stop() {
    m_motor.set(0.0);
  }

}
