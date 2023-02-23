// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.BooleanSubscriber;
import edu.wpi.first.networktables.BooleanTopic;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.PubSubOption;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ScoringLocationDetails;
import frc.robot.Constants.ScoringLocations;

public class MasterController extends SubsystemBase {

  private static MasterController m_inst = null;

  private WristSubsystemPID m_WristSubsystemPID;
  private ArmSubsystemPID m_ArmSubsystemPID;
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
      ArmSubsystemPID in_ArmSubsystemPID) {
    if (m_inst == null) {
      m_inst = new MasterController(in_WristSubsystemPID, in_ArmSubsystemPID);
    }
    return m_inst;
  }

  /** Creates a new MasterController. */
  private MasterController(WristSubsystemPID in_WristSubsystemPID, ArmSubsystemPID in_ArmSubsystemPID) {
    m_WristSubsystemPID = in_WristSubsystemPID;
    m_ArmSubsystemPID = in_ArmSubsystemPID;
    this.isRed = IsRed.get();

    SmartDashboard.putString("target item", "");
    SmartDashboard.putNumber("target pose x",0);
    SmartDashboard.putNumber("target pose y",0);
    SmartDashboard.putNumber("target degrees",0);
    SmartDashboard.putNumber("target trans x",0);
    SmartDashboard.putNumber("target trans y",0);
    SmartDashboard.putNumber("target height", this.height);
    SmartDashboard.putNumber("location ID", this.locationID);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    if (this.locationID > 0) {
      this.selectedLocation = this.getLocationDetails(this.locationID, this.isRed);
    }

    if (this.selectedLocation != null) {
      SmartDashboard.putString("target item", this.selectedLocation.item.toString());
      SmartDashboard.putNumber("target pose x",this.selectedLocation.pose.getX());
      SmartDashboard.putNumber("target pose y",this.selectedLocation.pose.getY());
      SmartDashboard.putNumber("target degrees",this.selectedLocation.pose.getRotation().getDegrees());
      SmartDashboard.putNumber("target trans x",this.selectedLocation.pose.getTranslation().getX());
      SmartDashboard.putNumber("target trans y",this.selectedLocation.pose.getTranslation().getY());
    }

    SmartDashboard.putNumber("target height", this.height);
    SmartDashboard.putNumber("location ID", this.locationID);

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
  public void buttonT() {
    this.height = 3;
  }

  public void buttonM() {
    this.height = 2;
  }

  public void buttonB() {
    this.height = 1;
  }

  private ScoringLocationDetails getLocationDetails(int LocationID, boolean isRed) {
    return ScoringLocations.getLocation(LocationID, isRed);
  }

}
