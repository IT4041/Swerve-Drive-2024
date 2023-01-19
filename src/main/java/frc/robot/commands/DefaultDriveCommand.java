package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class DefaultDriveCommand extends CommandBase {

    private final SwerveDriveSubsystem m_drivetrainSubsystem;

    private XboxController m_driver;

    public DefaultDriveCommand(XboxController m_driver2) {
        m_drivetrainSubsystem = SwerveDriveSubsystem.getInstance();
        m_driver = m_driver2;

        this.addRequirements(m_drivetrainSubsystem);
    }
    
    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {
        m_drivetrainSubsystem.DriveWithJoystick(m_driver);
    }

    
    @Override
    public void end(boolean interrupted) {
        
    }
    
    @Override
    public boolean isFinished() {
        return false;
    }

}
