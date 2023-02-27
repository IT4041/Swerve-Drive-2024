package frc.robot.commands.autonomous;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class AutoPaths extends SequentialCommandGroup {

    private SwerveDriveSubsystem m_drivetrainSubsystem;

    public AutoPaths(SwerveDriveSubsystem drivetrainSubsystem) {
        this.m_drivetrainSubsystem = drivetrainSubsystem;
    }
    
    public Command CenterPath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("CenterPath", new PathConstraints(1.5, 1.5)), true);
    }

    public Command CenterTablePath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("CenterTablePath", new PathConstraints(1, 1)), true);
    }

    public Command TablePath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("TablePath", new PathConstraints(1, 1)), true);
    }

    public Command SidePath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("SidePath", new PathConstraints(1, 1)), true);
    }

    public Command FinishPath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("FinishPath", new PathConstraints(1.5, 1.5)), false);
    }
}
