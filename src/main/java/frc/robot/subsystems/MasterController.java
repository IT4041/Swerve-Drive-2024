// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.BooleanSubscriber;
import edu.wpi.first.networktables.BooleanTopic;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.PubSubOption;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ScoringLocationDetails;
import frc.robot.Constants.ScoringLocations;

public class MasterController extends SubsystemBase {

  private static MasterController m_inst = null;

  private WristSubsystemPID m_WristSubsystemPID;
  private ArmSubsystemPID m_ArmSubsystemPID;
  private IntakeSubsystemPWM m_IntakeSubsystemPWM;
  private AutoPilot m_AutoPilot;
  private ScoringLocationDetails selectedLocation = null;

  private NetworkTableInstance inst = NetworkTableInstance.getDefault();
  private NetworkTable table = inst.getTable("FMSInfo");
  private BooleanTopic isRedAlliance = table.getBooleanTopic("IsRedAlliance");
  private BooleanSubscriber IsRed = isRedAlliance.subscribe(false, PubSubOption.keepDuplicates(false),
      PubSubOption.pollStorage(6));
  private boolean isRed;
  private int locationID = 0;
  private int height = 0;

  public static MasterController getInstance(WristSubsystemPID in_WristSubsystemPID,
      ArmSubsystemPID in_ArmSubsystemPID, AutoPilot in_AutoPilot, IntakeSubsystemPWM in_IntakeSubsystemPWM) {
    if (m_inst == null) {
      m_inst = new MasterController(in_WristSubsystemPID, in_ArmSubsystemPID, in_AutoPilot, in_IntakeSubsystemPWM);
    }
    return m_inst;
  }

  /** Creates a new MasterController. */
  private MasterController(WristSubsystemPID in_WristSubsystemPID, ArmSubsystemPID in_ArmSubsystemPID,
      AutoPilot in_AutoPilot, IntakeSubsystemPWM in_IntakeSubsystemPWM) {
    m_WristSubsystemPID = in_WristSubsystemPID;
    m_ArmSubsystemPID = in_ArmSubsystemPID;
    m_AutoPilot = in_AutoPilot;
    m_IntakeSubsystemPWM = in_IntakeSubsystemPWM;
    this.isRed = IsRed.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    if (this.locationID > 0) {
      this.selectedLocation = this.getLocationDetails(this.locationID, this.isRed);
    }
  }

  // ------Buttons for setting location -------------
  public void button1() {
    this.locationID = 1;
  }

  public void button2() {
    this.locationID = 2;
  }

  public void button3() {
    this.locationID = 3;
  }

  public void button4() {
    this.locationID = 4;
  }

  public void button5() {
    this.locationID = 5;
  }

  public void button6() {
    this.locationID = 6;
  }

  public void button7() {
    this.locationID = 7;
  }

  public void button8() {
    this.locationID = 8;
  }

  public void button9() {
    this.locationID = 9;
  }

  // ------Buttons for setting height -------------
  public void buttonTop() {
    // top
    this.height = 3;
    m_ArmSubsystemPID.top();
  }

  public void buttonMiddle() {
    // middle
    this.height = 2;
    m_ArmSubsystemPID.middle();
  }

  public void buttonBottom() {
    // floor/bottom
    this.height = 1;
    m_ArmSubsystemPID.floor();
  }

  // --- return to arm wrist to zero position
  // --------------------------------------
  public void zero() {
    this.height = 0;
    m_ArmSubsystemPID.zero();
    m_WristSubsystemPID.zero();
  }

  public void zeroCube() {
    this.height = 0;
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

  public void autoCubefloorPickUp() {
    m_ArmSubsystemPID.floor();
    m_IntakeSubsystemPWM.in();
    m_WristSubsystemPID.autoIntake();
  }

  private ScoringLocationDetails getLocationDetails(int LocationID, boolean isRed) {
    return ScoringLocations.getLocation(LocationID, isRed);
  }

}
