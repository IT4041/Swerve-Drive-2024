package frc.robot.commands.autonomous;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class FirstPath extends SequentialCommandGroup {

    private SwerveDriveSubsystem m_drivetrainSubsystem;

    public FirstPath(SwerveDriveSubsystem drivetrainSubsystem) {
        this.m_drivetrainSubsystem = drivetrainSubsystem;
    }
    
    public Command getCommand(){
        return m_drivetrainSubsystem.traj(PathPlanner.loadPath("Our Path", new PathConstraints(1, 1)), true);
     
    }
}
