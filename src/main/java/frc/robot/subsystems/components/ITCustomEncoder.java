// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.components;

import com.revrobotics.MotorFeedbackSensor;
import com.revrobotics.REVLibError;

import edu.wpi.first.wpilibj.DutyCycleEncoder;

/** Add your docs here. */
public class ITCustomEncoder extends DutyCycleEncoder implements MotorFeedbackSensor {

    public ITCustomEncoder(int channel){
        super(channel);
    }

    @Override
    public REVLibError setInverted(boolean inverted) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean getInverted() {
        // TODO Auto-generated method stub
        return false;
    }}
