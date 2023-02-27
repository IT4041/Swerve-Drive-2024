// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ArmSubsystemPID;
import frc.robot.subsystems.MasterController;
import frc.robot.subsystems.SwerveDriveSubsystem;
import frc.robot.subsystems.WristSubsystemPID;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoSequences extends SequentialCommandGroup {

  private final AutoPaths autoPaths;
  private final SwerveDriveSubsystem m_drivetrainSubsystem;
  private final MasterController m_MasterController;

  /** Creates a new AutoSequences. */
  public AutoSequences(SwerveDriveSubsystem in_drivetrainSubsystem, MasterController in_MasterController) {

    m_drivetrainSubsystem = in_drivetrainSubsystem;
    m_MasterController = in_MasterController;

    autoPaths = new AutoPaths(m_drivetrainSubsystem);
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands();
  }

  public Command ConeTablePath(){
    return new SequentialCommandGroup(
      new InstantCommand(m_MasterController::buttonTop,m_MasterController),
      new WaitCommand(4),
      new InstantCommand(m_MasterController::cubeInConeOut,m_MasterController),
      new WaitCommand(1),
      new InstantCommand(m_MasterController::zero,m_MasterController),
      new InstantCommand(m_MasterController::intakeStop,m_MasterController),
      new WaitCommand(3),
      autoPaths.TablePath()
    );
  }

  public Command CubeTablePath(){
    return new SequentialCommandGroup(
      new InstantCommand(m_MasterController::buttonTop,m_MasterController),
      new WaitCommand(4),
      new InstantCommand(m_MasterController::cubeOutConeIn,m_MasterController),
      new WaitCommand(1),
      new InstantCommand(m_MasterController::zero,m_MasterController),
      new InstantCommand(m_MasterController::intakeStop,m_MasterController),
      new WaitCommand(3),
      autoPaths.TablePath()
    );
  }

  public Command CenterPath(){
    return new SequentialCommandGroup(
      new InstantCommand(m_MasterController::buttonTop,m_MasterController),
      new WaitCommand(3.5),
      new InstantCommand(m_MasterController::cubeInConeOut,m_MasterController),
      new WaitCommand(.5),
      new InstantCommand(m_MasterController::zero,m_MasterController),
      new InstantCommand(m_MasterController::intakeStop,m_MasterController),
      new WaitCommand(2.5),
      new ParallelCommandGroup(autoPaths.CenterPath(),new InstantCommand(m_MasterController::autoCubefloorPickUp,m_MasterController)),
      new InstantCommand(m_MasterController::intakeStop,m_MasterController),
      new InstantCommand(m_MasterController::buttonTop,m_MasterController),
      new InstantCommand(m_MasterController::wristZero,m_MasterController),
      new WaitCommand(2.5),
      autoPaths.FinishPath(),
      new InstantCommand(m_MasterController::cubeOutConeIn,m_MasterController)
    );
  }


}
