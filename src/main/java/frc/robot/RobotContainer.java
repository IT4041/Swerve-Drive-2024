// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.autonomous.AutoSequences;
import frc.robot.subsystems.ArmSubsystemPID;
import frc.robot.subsystems.IntakeSubsystemPWM;
import frc.robot.subsystems.MasterController;
import frc.robot.subsystems.SwerveDriveSubsystem;
import frc.robot.subsystems.WristSubsystemPID;
import frc.robot.subsystems.components.LED;
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
  private final WristSubsystemPID m_WristSubsystemPID;
  private final ArmSubsystemPID m_ArmSubsystemPID;
  private final IntakeSubsystemPWM m_IntakeSubsystem;
  private final MasterController m_MasterController;
  private final LED m_LED;

  private SendableChooser<Command> m_TrajectoryChooser;
  private AutoSequences autoSequences;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_drivetrainSubsystem = SwerveDriveSubsystem.getInstance();
    m_WristSubsystemPID = WristSubsystemPID.getInstance();
    m_IntakeSubsystem = IntakeSubsystemPWM.getInstance();
    m_ArmSubsystemPID = ArmSubsystemPID.getInstance();
    m_MasterController = MasterController.getInstance(m_WristSubsystemPID, m_ArmSubsystemPID, m_IntakeSubsystem);
    m_LED = LED.getInstance();

    m_drivetrainSubsystem.setDefaultCommand(new RunCommand(() -> m_drivetrainSubsystem.DriveWithJoystick(m_driver), m_drivetrainSubsystem));
  
    // Set up the default command for the drivetrain.
    // The controls are for field-oriented driving:
    // Left stick Y axis -> forward and backwards movement
    // Left stick X axis -> left and right movement
    // Right stick X axis -> rotation
    configureButtonBindings();
    //autoPath = new AutoPaths(m_drivetrainSubsystem);
    autoSequences = new AutoSequences(m_drivetrainSubsystem, m_MasterController);
    setupTrajectoryDashboardChooser();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    JoystickButton A_BUTTON = new JoystickButton(m_driver, Constants.XboxControllerConstants.A_BUTTON);
    JoystickButton B_BUTTON = new JoystickButton(m_driver, Constants.XboxControllerConstants.B_BUTTON);
    JoystickButton X_BUTTON = new JoystickButton(m_driver, Constants.XboxControllerConstants.X_BUTTON);
    JoystickButton Y_BUTTON = new JoystickButton(m_driver, Constants.XboxControllerConstants.Y_BUTTON);
    JoystickButton LEFT_BUMPER = new JoystickButton(m_driver, Constants.XboxControllerConstants.LEFT_BUMPER);
    JoystickButton RIGHT_BUMPER = new JoystickButton(m_driver, Constants.XboxControllerConstants.RIGHT_BUMPER);
    JoystickButton THREE_LINES = new JoystickButton(m_driver, Constants.XboxControllerConstants.THREE_LINES);
    JoystickButton TWO_SQUARES = new JoystickButton(m_driver, Constants.XboxControllerConstants.TWO_SQUARES);

    JoystickButton A_BUTTON_AS = new JoystickButton(m_assist,Constants.XboxControllerConstants.A_BUTTON);
    JoystickButton B_BUTTON_AS = new JoystickButton(m_assist,Constants.XboxControllerConstants.B_BUTTON);
    JoystickButton X_BUTTON_AS = new JoystickButton(m_assist,Constants.XboxControllerConstants.X_BUTTON);
    JoystickButton Y_BUTTON_AS = new JoystickButton(m_assist,Constants.XboxControllerConstants.Y_BUTTON);
    JoystickButton THREE_LINES_AS = new JoystickButton(m_assist, Constants.XboxControllerConstants.THREE_LINES);
    JoystickButton LEFT_BUMPER_AS = new JoystickButton(m_assist, Constants.XboxControllerConstants.LEFT_BUMPER);
    JoystickButton RIGHT_BUMPER_AS = new JoystickButton(m_assist, Constants.XboxControllerConstants.RIGHT_BUMPER);


    // -----------DRIVER
    // ----------intake-----------------------------------------------------------
    RIGHT_BUMPER.onTrue(new InstantCommand(m_IntakeSubsystem::out_persist, m_IntakeSubsystem));
    LEFT_BUMPER.onTrue(new InstantCommand(m_IntakeSubsystem::in_persist, m_IntakeSubsystem));
    // ----------arm
    // manual-----------------------------------------------------------
    Y_BUTTON.whileTrue(new InstantCommand(m_ArmSubsystemPID::up, m_ArmSubsystemPID));
    Y_BUTTON.onFalse(new InstantCommand(m_ArmSubsystemPID::stop, m_ArmSubsystemPID));

    A_BUTTON.whileTrue(new InstantCommand(m_ArmSubsystemPID::down, m_ArmSubsystemPID));
    A_BUTTON.onFalse(new InstantCommand(m_ArmSubsystemPID::stop, m_ArmSubsystemPID));

    // ----------wrist
    // manual-----------------------------------------------------------
    X_BUTTON.whileTrue(new InstantCommand(m_WristSubsystemPID::in, m_WristSubsystemPID));
    X_BUTTON.onFalse(new InstantCommand(m_WristSubsystemPID::stop, m_WristSubsystemPID));

    B_BUTTON.whileTrue(new InstantCommand(m_WristSubsystemPID::out, m_WristSubsystemPID));
    B_BUTTON.onFalse(new InstantCommand(m_WristSubsystemPID::stop, m_WristSubsystemPID));

    // ----RETURN TO ZERO---------------------------------------------------------------
    THREE_LINES.onTrue(new InstantCommand(m_MasterController::zero, m_MasterController));

    // ----RESET POSE---------------------------------------------------------------
    TWO_SQUARES.onTrue(new InstantCommand(m_drivetrainSubsystem::updatePose, m_drivetrainSubsystem));

    // //----------ASSISTANT--------------------------------------------------------------
    // ----------arm position-----------------------------------------------------------
    Y_BUTTON_AS.onTrue(new InstantCommand(m_ArmSubsystemPID::stepUp, m_ArmSubsystemPID));
    A_BUTTON_AS.onTrue(new InstantCommand(m_ArmSubsystemPID::stepDown, m_ArmSubsystemPID));

    // ----------wrist position-----------------------------------------------------------
    X_BUTTON_AS.onTrue(new InstantCommand(m_WristSubsystemPID::stepUp, m_WristSubsystemPID));
    B_BUTTON_AS.onTrue(new InstantCommand(m_WristSubsystemPID::stepDown, m_WristSubsystemPID));

    // ----RETURN TO ZERO---------------------------------------------------------------
    THREE_LINES_AS.onTrue(new InstantCommand(m_MasterController::zero, m_MasterController));

    // ----------SIgnal Light-----------------------------------------------------------
    RIGHT_BUMPER_AS.onTrue(new InstantCommand(m_LED::signalCone, m_LED));
    LEFT_BUMPER_AS.onTrue(new InstantCommand(m_LED::signalCube, m_LED));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {

    System.out.println("*****************************");
    System.out.println("*****************************");
    System.out.println(m_TrajectoryChooser.getSelected().getName());
    System.out.println("*****************************");
    System.out.println("*****************************");

    return m_TrajectoryChooser.getSelected();
  }

  private void setupTrajectoryDashboardChooser() {

    m_TrajectoryChooser = new SendableChooser<Command>();

      m_TrajectoryChooser.addOption("RED-Auto-Balance", autoSequences.RED_AutoBalance());
      m_TrajectoryChooser.addOption("RED-Center", autoSequences.RED_CenterPath());
      m_TrajectoryChooser.addOption("RED-Side", autoSequences.RED_SidePath());
      m_TrajectoryChooser.addOption("-----------------------------------------", null);
      m_TrajectoryChooser.addOption("BLUE-Auto-Balance", autoSequences.BLUE_AutoBalance());
      m_TrajectoryChooser.addOption("BLUE-Center", autoSequences.BLUE_CenterPath());
      m_TrajectoryChooser.addOption("BLUE-Side", autoSequences.BLUE_SidePath());
  
    SmartDashboard.putData(m_TrajectoryChooser);
  }

  public void teleopInit() {
    // re-orient robot heading to foward heading away from drive station
    m_drivetrainSubsystem.resetHeading();

  }

}