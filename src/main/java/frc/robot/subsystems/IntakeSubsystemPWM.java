// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Spark; 

public class IntakeSubsystemPWM extends SubsystemBase {

  private static IntakeSubsystemPWM m_inst = null;
  private double speed = 0;

  //private PWMSparkMax m_motor;
  private Spark m_motor;

  public static IntakeSubsystemPWM getInstance() {
    if (m_inst == null) {
      m_inst = new IntakeSubsystemPWM();
    }
    return m_inst;
  }

  /** Creates a new Arm. */
  private IntakeSubsystemPWM() {

    m_motor = new Spark(Constants.IntakeSubsystemConstants.SparkMaxPWMChannel);
    //m_motor = new PWMSparkMax(Constants.IntakeSubsystemConstants.SparkMaxPWMChannel);
    // m_motor.enableDeadbandElimination(true);
    // m_motor.setSafetyEnabled(true);
    // m_motor.setExpiration(10);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //m_motor.setVoltage(12);
    //m_motor.feed();
    m_motor.set(this.speed);
  }
  
  public void in() {
    this.speed = .5;
    //m_motor.set(1);
    System.out.println("in");
  }
  public void in_hold() {
    this.speed = 0.02;
    //m_motor.set(0.1);
    System.out.println("in_hold");
  }

  public void out() {
    this.speed = -0.5;
    //m_motor.set(-1);
    System.out.println("out");
  }

  public void out_hold() {
    this.speed = -0.02;
    //m_motor.set(-0.1);
    System.out.println("out_hold");
  }

  public void stop() {
    this.speed = 0.0;
    //m_motor.stopMotor();
  }
}
