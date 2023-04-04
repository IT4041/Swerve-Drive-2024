// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard; 

public class IntakeSubsystemPWM extends SubsystemBase {

  private static IntakeSubsystemPWM m_inst = null;
  private double speed = 0;
  private Spark m_motor;
  private boolean in_on = false;
  private boolean out_on = false;

  public static IntakeSubsystemPWM getInstance() {
    if (m_inst == null) {
      m_inst = new IntakeSubsystemPWM();
    }
    return m_inst;
  }

  /** Creates a new Arm. */
  private IntakeSubsystemPWM() {
    m_motor = new Spark(Constants.IntakeSubsystemConstants.SparkMaxPWMChannel);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_motor.set(this.speed);

    SmartDashboard.putBoolean("Intake-On",  Math.abs(this.speed) > 0.1);
    SmartDashboard.putBoolean("Intake-Direction",  this.speed > 0.0);
  }
  
  public void in() {
    this.speed = 0.6;
  }

  public void out() {
    this.speed = -0.6;
  }

  public void stop() {
    this.speed = 0.0;
  }

  public void in_persist(){
    if(in_on){
      this.speed = 0.05;
    }else{
      this.speed = 0.6;
    }
    in_on = !in_on;

  }
  
  public void out_persist(){
    if(out_on){ 
      this.speed = -0.05;
    }else{
      this.speed = -0.6;
    }
    out_on = !out_on;
  }
}
