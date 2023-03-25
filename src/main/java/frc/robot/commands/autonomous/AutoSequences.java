// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.MasterController;
import frc.robot.subsystems.SwerveDriveSubsystem;

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

  public Command BLUE_SideToStationPath() {
    return new SequentialCommandGroup(
        new InstantCommand(m_MasterController::gotoTop, m_MasterController),
        new WaitCommand(2.5),
        new InstantCommand(m_MasterController::cubeInConeOut, m_MasterController),
        new WaitCommand(0.35),
        new InstantCommand(m_MasterController::zero, m_MasterController),
        new InstantCommand(m_MasterController::intakeStop, m_MasterController),
        new WaitCommand(0.25),
        autoPaths.BLUE_SideToStationPath(),
        m_drivetrainSubsystem.autoBalance());
  }

  public Command BLUE_CenterToStationPath() {
    return new SequentialCommandGroup(
        new InstantCommand(m_MasterController::gotoTop, m_MasterController),
        new WaitCommand(2.5),
        new InstantCommand(m_MasterController::cubeInConeOut, m_MasterController),
        new WaitCommand(0.35),
        new InstantCommand(m_MasterController::zero, m_MasterController),
        new InstantCommand(m_MasterController::intakeStop, m_MasterController),
        new WaitCommand(0.25),
        autoPaths.BLUE_CenterToStationPath(),
        m_drivetrainSubsystem.autoBalance());
  }

  public Command BLUE_CenterPath() {
    return new SequentialCommandGroup(
        new InstantCommand(m_MasterController::gotoTop, m_MasterController),
        new WaitCommand(2.5),
        new InstantCommand(m_MasterController::cubeInConeOut, m_MasterController),
        new WaitCommand(0.35),
        new InstantCommand(m_MasterController::zero, m_MasterController),
        new InstantCommand(m_MasterController::intakeStop, m_MasterController),
        new WaitCommand(0.25),
        new ParallelCommandGroup(autoPaths.BLUE_CenterPath(),
            new InstantCommand(m_MasterController::autoCubefloorPickUp, m_MasterController)),
        new InstantCommand(m_MasterController::intakeStop, m_MasterController),
        new InstantCommand(m_MasterController::gotoTop, m_MasterController),
        new InstantCommand(m_MasterController::wristZero, m_MasterController),
        new ParallelCommandGroup(
            new SequentialCommandGroup(new WaitCommand(0.825), autoPaths.BLUE_CenterFinishPath()),
            new SequentialCommandGroup(new WaitCommand(1.65),
                new InstantCommand(m_MasterController::cubeOutConeIn, m_MasterController))));
  }

  public Command BLUE_SidePath() {
    return new SequentialCommandGroup(
        new InstantCommand(m_MasterController::gotoTop, m_MasterController),
        new WaitCommand(2.5),
        new InstantCommand(m_MasterController::cubeInConeOut, m_MasterController),
        new WaitCommand(0.35),
        new InstantCommand(m_MasterController::zero, m_MasterController),
        new InstantCommand(m_MasterController::intakeStop, m_MasterController),
        new WaitCommand(0.25),
        new ParallelCommandGroup(autoPaths.BLUE_SidePath(),
            new InstantCommand(m_MasterController::autoCubefloorPickUp, m_MasterController)),
        new InstantCommand(m_MasterController::intakeStop, m_MasterController),
        new InstantCommand(m_MasterController::gotoTop, m_MasterController),
        new InstantCommand(m_MasterController::wristZero, m_MasterController),
        new ParallelCommandGroup(
            new SequentialCommandGroup(new WaitCommand(0.825), autoPaths.BLUE_SideFinishPath()),
            new SequentialCommandGroup(new WaitCommand(1.65),
                new InstantCommand(m_MasterController::cubeOutConeIn, m_MasterController))));
  }

  public Command BLUE_AutoBalance() {
    return new SequentialCommandGroup(
        new InstantCommand(m_MasterController::gotoTop, m_MasterController),
        new WaitCommand(2.5),
        new InstantCommand(m_MasterController::cubeInConeOut, m_MasterController),
        new WaitCommand(0.35),
        new InstantCommand(m_MasterController::zero, m_MasterController),
        new InstantCommand(m_MasterController::intakeStop, m_MasterController),
        new WaitCommand(0.25),
        autoPaths.BLUE_AutoBalancePath(),
        m_drivetrainSubsystem.autoBalance());
  }

  public Command RED_AutoBalance() {
    return new SequentialCommandGroup(
      new InstantCommand(m_MasterController::gotoTop, m_MasterController),
      new WaitCommand(2.5),
      new InstantCommand(m_MasterController::cubeInConeOut, m_MasterController),
      new WaitCommand(0.35),
      new InstantCommand(m_MasterController::zero, m_MasterController),
      new InstantCommand(m_MasterController::intakeStop, m_MasterController),
      new WaitCommand(0.25),
      autoPaths.RED_AutoBalancePath(),
      m_drivetrainSubsystem.autoBalance());
  }

  public Command RED_SideToStationPath() {
    return new SequentialCommandGroup(
      new InstantCommand(m_MasterController::gotoTop, m_MasterController),
      new WaitCommand(2.5),
      new InstantCommand(m_MasterController::cubeInConeOut, m_MasterController),
      new WaitCommand(0.35),
      new InstantCommand(m_MasterController::zero, m_MasterController),
      new InstantCommand(m_MasterController::intakeStop, m_MasterController),
      new WaitCommand(0.25),
      autoPaths.RED_SideToStationPath(),
      m_drivetrainSubsystem.autoBalance());
  }

  public Command RED_CenterToStationPath() {
    return new SequentialCommandGroup(
      new InstantCommand(m_MasterController::gotoTop, m_MasterController),
      new WaitCommand(2.5),
      new InstantCommand(m_MasterController::cubeInConeOut, m_MasterController),
      new WaitCommand(0.35),
      new InstantCommand(m_MasterController::zero, m_MasterController),
      new InstantCommand(m_MasterController::intakeStop, m_MasterController),
      new WaitCommand(0.25),
      autoPaths.RED_CenterToStationPath(),
      m_drivetrainSubsystem.autoBalance());
  }

  public Command RED_CenterPath() {
    return new SequentialCommandGroup(
      new InstantCommand(m_MasterController::gotoTop, m_MasterController),
      new WaitCommand(2.5),
      new InstantCommand(m_MasterController::cubeInConeOut, m_MasterController),
      new WaitCommand(0.35),
      new InstantCommand(m_MasterController::zero, m_MasterController),
      new InstantCommand(m_MasterController::intakeStop, m_MasterController),
      new WaitCommand(0.25),
      new ParallelCommandGroup(autoPaths.RED_CenterPath(),
        new InstantCommand(m_MasterController::autoCubefloorPickUp, m_MasterController)),
      new InstantCommand(m_MasterController::gotoTop, m_MasterController),
      new InstantCommand(m_MasterController::wristZero, m_MasterController),
      new ParallelCommandGroup(
          new SequentialCommandGroup(new WaitCommand(0.825), autoPaths.RED_CenterFinishPath()),
          new SequentialCommandGroup(new WaitCommand(1.65),
              new InstantCommand(m_MasterController::cubeOutConeIn, m_MasterController))));
  }

  public Command RED_SidePath() {
    return new SequentialCommandGroup(
      new InstantCommand(m_MasterController::gotoTop, m_MasterController),
      new WaitCommand(2.5),
      new InstantCommand(m_MasterController::cubeInConeOut, m_MasterController),
      new WaitCommand(0.35),
      new InstantCommand(m_MasterController::zero, m_MasterController),
      new InstantCommand(m_MasterController::intakeStop, m_MasterController),
      new WaitCommand(0.25),
      new ParallelCommandGroup(autoPaths.RED_SidePath(),
          new InstantCommand(m_MasterController::autoCubefloorPickUp, m_MasterController)),
      new InstantCommand(m_MasterController::intakeStop, m_MasterController),
      new InstantCommand(m_MasterController::gotoTop, m_MasterController),
      new InstantCommand(m_MasterController::wristZero, m_MasterController),
      new ParallelCommandGroup(
          new SequentialCommandGroup(new WaitCommand(0.825), autoPaths.RED_SideFinishPath()),
          new SequentialCommandGroup(new WaitCommand(1.65),
              new InstantCommand(m_MasterController::cubeOutConeIn, m_MasterController))));
  }

}
