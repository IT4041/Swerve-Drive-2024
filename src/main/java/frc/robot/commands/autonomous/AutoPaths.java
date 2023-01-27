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
    
    public Command CenterConeCubePath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("CenterConeCubePath", new PathConstraints(1, 1)), true);
     
    }
    public Command CenterConeCubeTablePath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("CenterConeCubeTablePath", new PathConstraints(1, 1)), true);
     
    }
    public Command CubeTablePath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("CubeTablePath", new PathConstraints(1, 1)), true);
     
    }
    public Command SideConeCubePath(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("SideConeCubePath", new PathConstraints(1, 1)), true);
     
    }
}
