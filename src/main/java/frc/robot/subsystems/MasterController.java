// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MasterController extends SubsystemBase {

  private static MasterController m_inst = null;

  private WristSubsystemPID m_WristSubsystemPID;
  private ArmSubsystemPID m_ArmSubsystemPID;
  private IntakeSubsystemPWM m_IntakeSubsystemPWM;

  public static MasterController getInstance(WristSubsystemPID in_WristSubsystemPID,
      ArmSubsystemPID in_ArmSubsystemPID, IntakeSubsystemPWM in_IntakeSubsystemPWM) {
    if (m_inst == null) {
      m_inst = new MasterController(in_WristSubsystemPID, in_ArmSubsystemPID, in_IntakeSubsystemPWM);
    }
    return m_inst;
  }

  /** Creates a new MasterController. */
  private MasterController(WristSubsystemPID in_WristSubsystemPID, ArmSubsystemPID in_ArmSubsystemPID, IntakeSubsystemPWM in_IntakeSubsystemPWM) {
    m_WristSubsystemPID = in_WristSubsystemPID;
    m_ArmSubsystemPID = in_ArmSubsystemPID;
    m_IntakeSubsystemPWM = in_IntakeSubsystemPWM;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void gotoTop() {
    // top
    m_ArmSubsystemPID.top();
  }

  public void gotoMiddle() {
    // top
    m_ArmSubsystemPID.middle();
  }

  // --- return to arm wrist to zero position
  // --------------------------------------
  public void zero() {
    m_ArmSubsystemPID.zero();
    m_WristSubsystemPID.zero();
  }

  public void zeroCube() {
    m_ArmSubsystemPID.zero();
    m_WristSubsystemPID.autoIntake();
  }

  // ---intake control----------------------------
  public void cubeInConeOut() {
    m_IntakeSubsystemPWM.in();
  }

  public void cubeOutConeIn() {
    m_IntakeSubsystemPWM.out();
  }

  public void intakeStop() {
    m_IntakeSubsystemPWM.stop();
  }
  // ----------------------------------------------

  public void wristZero() {
    m_WristSubsystemPID.zero();
  }

  public void wristTop() {
    m_WristSubsystemPID.top();
  }

  public void autoCubefloorPickUp() {
    m_ArmSubsystemPID.floor();
    m_IntakeSubsystemPWM.in();
    m_WristSubsystemPID.autoIntake();
  }

}
