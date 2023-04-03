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

    //-----BLUE PATHS -----------------------------------------------------------------------------------------
    public Command BLUE_AutoBalancePath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("BLUE_AutoBalancePath", new PathConstraints(.85, 1.0)), true);
    }

    public Command BLUE_CenterPath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("BLUE_CenterPath", new PathConstraints(1.65, 1.25)), true);
    }

    public Command BLUE_CenterFinishPath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("BLUE_CenterFinishPath", new PathConstraints(1.5, 1.5)), false);
    }

    public Command BLUE_SidePath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("BLUE_SidePath", new PathConstraints(1.5, 1.5)), true);
    }

    public Command BLUE_SideFinishPath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("BLUE_SideFinishPath", new PathConstraints(1.5, 1.5)), false);
    }

    //-----RED PATHS -----------------------------------------------------------------------------------------
    public Command RED_AutoBalancePath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("RED_AutoBalancePath", new PathConstraints(.85, 1.0)), true);
    }

    public Command RED_CenterPath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("RED_CenterPath", new PathConstraints(1.65, 1.25)), true);
    }

    public Command RED_CenterFinishPath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("RED_CenterFinishPath", new PathConstraints(1.5, 1.5)), false);
    }

    public Command RED_SidePath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("RED_SidePath", new PathConstraints(1.5, 1.5)), true);
    }

    public Command RED_SideFinishPath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("RED_SideFinishPath", new PathConstraints(1.5, 1.5)), false);
    }

}
