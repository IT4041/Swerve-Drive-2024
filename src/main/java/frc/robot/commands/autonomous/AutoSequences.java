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
    
    SequentialCommandGroup BLUE_SideToStationPath = new SequentialCommandGroup(
        // new InstantCommand(m_MasterController::gotoTop, m_MasterController),
        // new WaitCommand(2.5),
        // new InstantCommand(m_MasterController::cubeInConeOut, m_MasterController),
        // new WaitCommand(0.35),
        // new InstantCommand(m_MasterController::zero, m_MasterController),
        // new InstantCommand(m_MasterController::intakeStop, m_MasterController),
        // new WaitCommand(0.25),
        autoPaths.BLUE_SideToStationPath(),
        m_drivetrainSubsystem.autoBalance());

        BLUE_SideToStationPath.setName("BLUE_SIDE_TO_STATION_PATH");
        return BLUE_SideToStationPath;
  }

  public Command BLUE_CenterToStationPath() {
    
    SequentialCommandGroup BLUE_CenterToStationPath = new SequentialCommandGroup(
        // new InstantCommand(m_MasterController::gotoTop, m_MasterController),
        // new WaitCommand(2.5),
        // new InstantCommand(m_MasterController::cubeInConeOut, m_MasterController),
        // new WaitCommand(0.35),
        // new InstantCommand(m_MasterController::zero, m_MasterController),
        // new InstantCommand(m_MasterController::intakeStop, m_MasterController),
        // new WaitCommand(0.25),
        autoPaths.BLUE_CenterToStationPath(),
        m_drivetrainSubsystem.autoBalance());
        
        BLUE_CenterToStationPath.setName("BLUE_CENTER_TO_STATION_PATH");
        return BLUE_CenterToStationPath;
  }

  public Command BLUE_CenterPath() {

    // SequentialCommandGroup BLUE_CenterPath = new SequentialCommandGroup(
    //     new InstantCommand(m_MasterController::gotoTop, m_MasterController),
    //     new WaitCommand(2.75),
    //     new InstantCommand(m_MasterController::cubeInConeOut, m_MasterController),
    //     new WaitCommand(0.35),
    //     new InstantCommand(m_MasterController::zero, m_MasterController),
    //     new InstantCommand(m_MasterController::intakeStop, m_MasterController),
    //     new WaitCommand(0.25),
    //     new ParallelCommandGroup(autoPaths.BLUE_CenterPath(),
    //         new InstantCommand(m_MasterController::autoCubefloorPickUp, m_MasterController)),
    //     new InstantCommand(m_MasterController::intakeStop, m_MasterController),
    //     new InstantCommand(m_MasterController::gotoTop, m_MasterController),
    //     new InstantCommand(m_MasterController::wristZero, m_MasterController),
    //     new ParallelCommandGroup(
    //         new SequentialCommandGroup(new WaitCommand(0.825), autoPaths.BLUE_CenterFinishPath()),
    //         new SequentialCommandGroup(new WaitCommand(1.65),
    //             new InstantCommand(m_MasterController::cubeOutConeIn, m_MasterController))));

    SequentialCommandGroup BLUE_CenterPath = new SequentialCommandGroup(autoPaths.BLUE_CenterPath(),autoPaths.BLUE_CenterFinishPath());

    BLUE_CenterPath.setName("BLUE_CENTER_PATH");
    return BLUE_CenterPath;
  }

  public Command BLUE_SidePath() {

    // SequentialCommandGroup BLUE_SidePath = new SequentialCommandGroup(
    //     new InstantCommand(m_MasterController::gotoTop, m_MasterController),
    //     new WaitCommand(2.5),
    //     new InstantCommand(m_MasterController::cubeInConeOut, m_MasterController),
    //     new WaitCommand(0.35),
    //     new InstantCommand(m_MasterController::zero, m_MasterController),
    //     new InstantCommand(m_MasterController::intakeStop, m_MasterController),
    //     new WaitCommand(0.25),
    //     new ParallelCommandGroup(autoPaths.BLUE_SidePath(),
    //         new InstantCommand(m_MasterController::autoCubefloorPickUp, m_MasterController)),
    //     new InstantCommand(m_MasterController::intakeStop, m_MasterController),
    //     new InstantCommand(m_MasterController::gotoTop, m_MasterController),
    //     new InstantCommand(m_MasterController::wristZero, m_MasterController),
    //     new ParallelCommandGroup(
    //         new SequentialCommandGroup(new WaitCommand(0.825), autoPaths.BLUE_SideFinishPath()),
    //         new SequentialCommandGroup(new WaitCommand(1.65),
    //             new InstantCommand(m_MasterController::cubeOutConeIn, m_MasterController))));

    SequentialCommandGroup BLUE_SidePath = new SequentialCommandGroup(autoPaths.BLUE_SidePath(),autoPaths.BLUE_SideFinishPath());

    BLUE_SidePath.setName("BLUE_SIDE_PATH");
    return BLUE_SidePath;
  }

  public Command BLUE_AutoBalance() {

    SequentialCommandGroup BLUE_AutoBalance = new SequentialCommandGroup(
           
        autoPaths.BLUE_AutoBalancePath(),
        m_drivetrainSubsystem.autoBalance());

        BLUE_AutoBalance.setName("BLUE_AUTO_BALANCE");
        return BLUE_AutoBalance;
  }

  public Command RED_AutoBalance() {

    SequentialCommandGroup RED_AutoBalance = new SequentialCommandGroup(
      // new InstantCommand(m_MasterController::gotoTop, m_MasterController),
      // new WaitCommand(2.5),
      // new InstantCommand(m_MasterController::cubeInConeOut, m_MasterController),
      // new WaitCommand(0.35),
      // new InstantCommand(m_MasterController::zero, m_MasterController),
      // new InstantCommand(m_MasterController::intakeStop, m_MasterController),
      // new WaitCommand(0.25),
      autoPaths.RED_AutoBalancePath(),
      m_drivetrainSubsystem.autoBalance());

      RED_AutoBalance.setName("RED_AUTO_BALANCE");
      return RED_AutoBalance;
  }

  public Command RED_SideToStationPath() {

    SequentialCommandGroup RED_SideToStationPath = new SequentialCommandGroup(
      // new InstantCommand(m_MasterController::gotoTop, m_MasterController),
      // new WaitCommand(2.5),
      // new InstantCommand(m_MasterController::cubeInConeOut, m_MasterController),
      // new WaitCommand(0.35),
      // new InstantCommand(m_MasterController::zero, m_MasterController),
      // new InstantCommand(m_MasterController::intakeStop, m_MasterController),
      // new WaitCommand(0.25),
      autoPaths.RED_SideToStationPath(),
      m_drivetrainSubsystem.autoBalance());

      RED_SideToStationPath.setName("RED_SIDE_TO_STATION_PATH");
      return RED_SideToStationPath;
  }

  public Command RED_CenterToStationPath() {

    SequentialCommandGroup RED_CenterToStationPath = new SequentialCommandGroup(
      // new InstantCommand(m_MasterController::gotoTop, m_MasterController),
      // new WaitCommand(2.5),
      // new InstantCommand(m_MasterController::cubeInConeOut, m_MasterController),
      // new WaitCommand(0.35),
      // new InstantCommand(m_MasterController::zero, m_MasterController),
      // new InstantCommand(m_MasterController::intakeStop, m_MasterController),
      // new WaitCommand(0.25),
      autoPaths.RED_CenterToStationPath(),
      m_drivetrainSubsystem.autoBalance());

      RED_CenterToStationPath.setName("RED_CENTER_TO_STATION_PATH");
      return RED_CenterToStationPath;

  }

  public Command RED_CenterPath() {

    // SequentialCommandGroup RED_CenterPath = new SequentialCommandGroup(
    //   new InstantCommand(m_MasterController::gotoTop, m_MasterController),
    //   new WaitCommand(2.5),
    //   new InstantCommand(m_MasterController::cubeInConeOut, m_MasterController),
    //   new WaitCommand(0.35),
    //   new InstantCommand(m_MasterController::zero, m_MasterController),
    //   new InstantCommand(m_MasterController::intakeStop, m_MasterController),
    //   new WaitCommand(0.25),
    //   new ParallelCommandGroup(autoPaths.RED_CenterPath(),
    //     new InstantCommand(m_MasterController::autoCubefloorPickUp, m_MasterController)),
    //   new InstantCommand(m_MasterController::gotoTop, m_MasterController),
    //   new InstantCommand(m_MasterController::wristZero, m_MasterController),
    //   new ParallelCommandGroup(
    //       new SequentialCommandGroup(new WaitCommand(0.825), autoPaths.RED_CenterFinishPath()),
    //       new SequentialCommandGroup(new WaitCommand(1.65),
    //           new InstantCommand(m_MasterController::cubeOutConeIn, m_MasterController))));

      SequentialCommandGroup RED_CenterPath = new SequentialCommandGroup(autoPaths.RED_CenterPath(),autoPaths.RED_CenterFinishPath());

      RED_CenterPath.setName("RED_CENTER_PATH");
      return RED_CenterPath;
  } 

  public Command RED_SidePath() {

    // SequentialCommandGroup RED_SidePath = new SequentialCommandGroup(
    //   new InstantCommand(m_MasterController::gotoTop, m_MasterController),
    //   new WaitCommand(2.5),
    //   new InstantCommand(m_MasterController::cubeInConeOut, m_MasterController),
    //   new WaitCommand(0.35),
    //   new InstantCommand(m_MasterController::zero, m_MasterController),
    //   new InstantCommand(m_MasterController::intakeStop, m_MasterController),
    //   new WaitCommand(0.25),
    //   new ParallelCommandGroup(autoPaths.RED_SidePath(),
    //       new InstantCommand(m_MasterController::autoCubefloorPickUp, m_MasterController)),
    //   new InstantCommand(m_MasterController::intakeStop, m_MasterController),
    //   new InstantCommand(m_MasterController::gotoTop, m_MasterController),
    //   new InstantCommand(m_MasterController::wristZero, m_MasterController),
    //   new ParallelCommandGroup(
    //       new SequentialCommandGroup(new WaitCommand(0.825), autoPaths.RED_SideFinishPath()),
    //       new SequentialCommandGroup(new WaitCommand(1.65),
    //           new InstantCommand(m_MasterController::cubeOutConeIn, m_MasterController))));

    SequentialCommandGroup RED_SidePath = new SequentialCommandGroup(autoPaths.RED_SidePath(),autoPaths.RED_SideFinishPath());

    RED_SidePath.setName("RED_SIDE_PATH");
    return RED_SidePath;
  }

}
