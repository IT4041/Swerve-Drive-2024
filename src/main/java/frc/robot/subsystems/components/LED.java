// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.components;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import java.util.Timer;
import java.util.TimerTask;

public class LED extends SubsystemBase {

  private static LED m_inst = null;
  private AddressableLED m_led;
  private AddressableLEDBuffer m_ledBuffer;
  private Color indicator = Constants.LEDConstants.orange;
  private Timer timer = new Timer();
  private TimerTask task;
  private int m_animationDelay = 50;

  public static LED getInstance() {
    if (m_inst == null) {
      m_inst = new LED();
    }
    return m_inst;
  }

  /** Creates a new LED. */
  private LED() {
    // PWM port 5
    // Must be a PWM header, not MXP or DIO
    this.m_led = new AddressableLED(Constants.LEDConstants.PWMPort);

    // Reuse buffer
    // Default to a length of 6 start empty output
    // Length is expensive to set, so only set it once, then just update data
    this.m_ledBuffer = new AddressableLEDBuffer(Constants.LEDConstants.StripLength);
    m_led.setLength(m_ledBuffer.getLength());

    // Set the data
    m_led.setData(m_ledBuffer);
    m_led.start();

  }

  @Override
  public void periodic() {

    SmartDashboard.putBoolean("SignalCone", indicator == Constants.LEDConstants.yellow);

    // This method will be called once per scheduler run
    for (var i = 0; i < m_ledBuffer.getLength(); i++) {
      // Sets the specified LED to the RGB values for red
      if (indicator == Constants.LEDConstants.yellow || indicator == Constants.LEDConstants.purple) {
        m_ledBuffer.setLED(i, indicator);
      } else {
        m_ledBuffer.setLED(i, shimmer());
      }
    }

    if (indicator == Constants.LEDConstants.yellow || indicator == Constants.LEDConstants.purple) {
      m_led.setData(m_ledBuffer);
    } else {
      task = new TimerTask() {
        public void run() {
          m_led.setData(m_ledBuffer);
        }
      };
      timer.scheduleAtFixedRate(
          task,
          20, // run first occurrence in 20ms
          m_animationDelay);
    }
  }

  public void signalCone() {
    indicator = Constants.LEDConstants.yellow;

  }

  public void signalCube() {
    indicator = Constants.LEDConstants.purple;

  }

  private Color shimmer() {

    Double red = (Math.random() * 10000) % 256;
    Double green = (Math.random() * 10000) % 256;
    Double blue = (Math.random() * 10000) % 256;

    return new Color(red.intValue(), green.intValue(), blue.intValue());
  }
}
