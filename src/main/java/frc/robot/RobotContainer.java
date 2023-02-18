// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.autonomous.AutoPaths;
// import frc.robot.subsystems.ArmSubsystemPID;
import frc.robot.subsystems.ArmSubsystemSimple;
// import frc.robot.subsystems.AutoPilot;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SwerveDriveSubsystem;
// import frc.robot.subsystems.WristSubsystemPID;
import frc.robot.subsystems.WristSubsystemSimple;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final XboxController m_driver = new XboxController(Constants.XboxControllerConstants.DRIVER_CONTROLLER_USB_ID);
  private final XboxController m_assist = new XboxController(Constants.XboxControllerConstants.ASSIST_CONTROLLER_USB_ID);

  private final SwerveDriveSubsystem m_drivetrainSubsystem;
  private final WristSubsystemSimple m_WristSubsystemSimple;
  private final ArmSubsystemSimple m_ArmSubsystemSimple;
  private final IntakeSubsystem m_IntakeSubsystem;

  // private final AutoPilot m_autoPilot;
  private SendableChooser<Command> m_TrajectoryChooser;
  private AutoPaths autoPath;
  
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_drivetrainSubsystem = SwerveDriveSubsystem.getInstance();
    // m_autoPilot = AutoPilot.getInstance();
    m_WristSubsystemSimple = WristSubsystemSimple.getInstance();
    m_ArmSubsystemSimple = ArmSubsystemSimple.getInstance();
    m_IntakeSubsystem = IntakeSubsystem.getInstance();

    //m_drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(m_driver));
    m_drivetrainSubsystem.setDefaultCommand(new RunCommand(() -> m_drivetrainSubsystem.DriveWithJoystick(m_driver),m_drivetrainSubsystem));

    // Set up the default command for the drivetrain.
    // The controls are for field-oriented driving:
    // Left stick Y axis -> forward and backwards movement
    // Left stick X axis -> left and right movement
    // Right stick X axis -> rotation
    configureButtonBindings();
    autoPath = new AutoPaths(m_drivetrainSubsystem);
    setupTrajectoryDashboardChooser();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // JoystickButton A_BUTTON = new JoystickButton(m_driver, Constants.XboxControllerConstants.A_BUTTON);
    // JoystickButton B_BUTTON = new JoystickButton(m_driver, Constants.XboxControllerConstants.B_BUTTON);
    // JoystickButton X_BUTTON = new JoystickButton(m_driver, Constants.XboxControllerConstants.X_BUTTON);
    // JoystickButton Y_BUTTON = new JoystickButton(m_driver, Constants.XboxControllerConstants.Y_BUTTON);
    JoystickButton LEFT_BUMPER = new JoystickButton(m_driver, Constants.XboxControllerConstants.LEFT_BUMPER);
    JoystickButton RIGHT_BUMPER = new JoystickButton(m_driver, Constants.XboxControllerConstants.RIGHT_BUMPER);
    // JoystickButton TWO_SQUARES = new JoystickButton(m_driver, Constants.XboxControllerConstants.TWO_SQUARES);
    // JoystickButton THREE_LINES = new JoystickButton(m_driver, Constants.XboxControllerConstants.THREE_LINES);
    // JoystickButton LEFT_STICK_PRESS = new JoystickButton(m_driver, Constants.XboxControllerConstants.LEFT_STICK_PRESS);
    // JoystickButton RIGHT_STICK_PRESS = new JoystickButton(m_driver, Constants.XboxControllerConstants.RIGHT_STICK_PRESS);

    JoystickButton A_BUTTON_AS = new JoystickButton(m_assist, Constants.XboxControllerConstants.A_BUTTON);
    JoystickButton B_BUTTON_AS = new JoystickButton(m_assist, Constants.XboxControllerConstants.B_BUTTON);
    JoystickButton X_BUTTON_AS = new JoystickButton(m_assist, Constants.XboxControllerConstants.X_BUTTON);
    JoystickButton Y_BUTTON_AS = new JoystickButton(m_assist, Constants.XboxControllerConstants.Y_BUTTON);
    // JoystickButton LEFT_BUMPER_AS = new JoystickButton(m_assist, Constants.XboxControllerConstants.LEFT_BUMPER);
    // JoystickButton RIGHT_BUMPER_AS = new JoystickButton(m_assist, Constants.XboxControllerConstants.RIGHT_BUMPER);
    // JoystickButton TWO_SQUARES_AS = new JoystickButton(m_assist, Constants.XboxControllerConstants.TWO_SQUARES);
    // JoystickButton THREE_LINES_AS = new JoystickButton(m_assist, Constants.XboxControllerConstants.THREE_LINES);
    // JoystickButton LEFT_STICK_PRESS_AS = new JoystickButton(m_assist, Constants.XboxControllerConstants.LEFT_STICK_PRESS);
    // JoystickButton RIGHT_STICK_PRESS_AS = new JoystickButton(m_assist, Constants.XboxControllerConstants.RIGHT_STICK_PRESS);

    //-----------DRIVER
    //----------intake-----------------------------------------------------------
    LEFT_BUMPER.whileTrue(new InstantCommand(m_IntakeSubsystem::in,m_IntakeSubsystem));
    LEFT_BUMPER.onFalse(new InstantCommand(m_IntakeSubsystem::stop,m_IntakeSubsystem));

    RIGHT_BUMPER.whileTrue(new InstantCommand(m_IntakeSubsystem::out,m_IntakeSubsystem));
    RIGHT_BUMPER.onFalse(new InstantCommand(m_IntakeSubsystem::stop,m_IntakeSubsystem));

    //----------ASSISTANT
    //----------arm-----------------------------------------------------------
    Y_BUTTON_AS.whileTrue(new InstantCommand(m_ArmSubsystemSimple::up,m_ArmSubsystemSimple));
    Y_BUTTON_AS.onFalse(new InstantCommand(m_ArmSubsystemSimple::stop,m_ArmSubsystemSimple));

    A_BUTTON_AS.whileTrue(new InstantCommand(m_ArmSubsystemSimple::down,m_ArmSubsystemSimple));
    A_BUTTON_AS.onFalse(new InstantCommand(m_ArmSubsystemSimple::stop,m_ArmSubsystemSimple));

    //----------wrist-----------------------------------------------------------
    B_BUTTON_AS.whileTrue(new InstantCommand(m_WristSubsystemSimple::in,m_WristSubsystemSimple));
    B_BUTTON_AS.onFalse(new InstantCommand(m_WristSubsystemSimple::stop,m_WristSubsystemSimple));

    X_BUTTON_AS.whileTrue(new InstantCommand(m_WristSubsystemSimple::out,m_WristSubsystemSimple));
    X_BUTTON_AS.onFalse(new InstantCommand(m_WristSubsystemSimple::stop,m_WristSubsystemSimple));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {

    return m_TrajectoryChooser.getSelected();
    
  }
 
  private void setupTrajectoryDashboardChooser()
  {
    m_TrajectoryChooser = new SendableChooser<Command>();
    m_TrajectoryChooser.setDefaultOption("CenterConeCubePath", autoPath.CenterConeCubePath());
    m_TrajectoryChooser.addOption("CenterConeCubeTablePath", autoPath.CenterConeCubeTablePath());
    m_TrajectoryChooser.addOption("CubeTablePath", autoPath.CubeTablePath());
    m_TrajectoryChooser.addOption("SideConeCubePath", autoPath.SideConeCubePath());

    SmartDashboard.putData(m_TrajectoryChooser);
  }

  public void teleopInit(){
    //re-orient robot heading to foward heading away from drive station
    m_drivetrainSubsystem.resetHeading();
  }

}
