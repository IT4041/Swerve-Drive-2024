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

    public Command AutoBalancePath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("AutoBalancePath", new PathConstraints(.85, 1.0)), true);
    }

    public Command CenterFinishPath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("CenterFinishPath", new PathConstraints(1.5, 1.5)), false);
    }
    
    public Command CenterPath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("CenterPath", new PathConstraints(1.65, 1.25)), true);
    }

    public Command CenterToStationPath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("CenterToStationPath", new PathConstraints(1.65, 1.25)), true);
    }

    public Command SideFinishPath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("SideFinishPath", new PathConstraints(1.5, 1.5)), false);
    }

    public Command SidePath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("SidePath", new PathConstraints(1.5, 1.5)), true);
    }

    public Command SideToStationPath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("SideToStationPath", new PathConstraints(1, 1)), true);
    }






}
