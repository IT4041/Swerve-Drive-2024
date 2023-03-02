// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.autonomous.AutoPaths;
import frc.robot.commands.autonomous.AutoSequences;
import frc.robot.subsystems.ArmSubsystemPID;
import frc.robot.subsystems.AutoPilot;
import frc.robot.subsystems.IntakeSubsystemPWM;
import frc.robot.subsystems.MasterController;
import frc.robot.subsystems.SwerveDriveSubsystem;
import frc.robot.subsystems.WristSubsystemPID;
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
  //private final Joystick autoPilotController = new Joystick(Constants.XboxControllerConstants.ASSIST_CONTROLLER_USB_ID);

  private final SwerveDriveSubsystem m_drivetrainSubsystem;
  private final WristSubsystemPID m_WristSubsystemPID;
  private final ArmSubsystemPID m_ArmSubsystemPID;
  private final IntakeSubsystemPWM m_IntakeSubsystem;
  private final MasterController m_MasterController;

  private final AutoPilot m_autoPilot;
  private SendableChooser<Command> m_TrajectoryChooser;
  //private AutoPaths autoPath;
  private AutoSequences autoSequences;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_drivetrainSubsystem = SwerveDriveSubsystem.getInstance();
    m_autoPilot = AutoPilot.getInstance();
    m_WristSubsystemPID = WristSubsystemPID.getInstance();
    m_IntakeSubsystem = IntakeSubsystemPWM.getInstance();
    m_ArmSubsystemPID = ArmSubsystemPID.getInstance();
    m_MasterController = MasterController.getInstance(m_WristSubsystemPID, m_ArmSubsystemPID, m_autoPilot, m_IntakeSubsystem);

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
    // JoystickButton TWO_SQUARES = new JoystickButton(m_driver,Constants.XboxControllerConstants.TWO_SQUARES);
    JoystickButton THREE_LINES = new JoystickButton(m_driver, Constants.XboxControllerConstants.THREE_LINES);
    // JoystickButton LEFT_STICK_PRESS = new JoystickButton(m_driver, Constants.XboxControllerConstants.LEFT_STICK_PRESS);
    // JoystickButton RIGHT_STICK_PRESS = new JoystickButton(m_driver, Constants.XboxControllerConstants.RIGHT_STICK_PRESS);

    JoystickButton A_BUTTON_AS = new JoystickButton(m_assist,Constants.XboxControllerConstants.A_BUTTON);
    JoystickButton B_BUTTON_AS = new JoystickButton(m_assist,Constants.XboxControllerConstants.B_BUTTON);
    JoystickButton X_BUTTON_AS = new JoystickButton(m_assist,Constants.XboxControllerConstants.X_BUTTON);
    JoystickButton Y_BUTTON_AS = new JoystickButton(m_assist,Constants.XboxControllerConstants.Y_BUTTON);
    // JoystickButton LEFT_BUMPER_AS = new JoystickButton(m_assist, Constants.XboxControllerConstants.LEFT_BUMPER);
    // JoystickButton RIGHT_BUMPER_AS = new JoystickButton(m_assist, Constants.XboxControllerConstants.RIGHT_BUMPER);
    // JoystickButton TWO_SQUARES_AS = new JoystickButton(m_assist, Constants.XboxControllerConstants.TWO_SQUARES);
    JoystickButton THREE_LINES_AS = new JoystickButton(m_assist, Constants.XboxControllerConstants.THREE_LINES);
    // JoystickButton LEFT_STICK_PRESS_AS = new JoystickButton(m_assist, Constants.XboxControllerConstants.LEFT_STICK_PRESS);
    // JoystickButton RIGHT_STICK_PRESS_AS = new JoystickButton(m_assist, Constants.XboxControllerConstants.RIGHT_STICK_PRESS);

    // JoystickButton GREEN_LEFT = new JoystickButton(autoPilotController, Constants.AutoPilotController.GREEN_LEFT);
    // JoystickButton GREEN_CENTER = new JoystickButton(autoPilotController, Constants.AutoPilotController.GREEN_CENTER);
    // JoystickButton GREEN_RIGHT = new JoystickButton(autoPilotController, Constants.AutoPilotController.GREEN_RIGHT);
    // JoystickButton YELLOW_LEFT = new JoystickButton(autoPilotController, Constants.AutoPilotController.YELLOW_LEFT);
    // JoystickButton YELLOW_CENTER = new JoystickButton(autoPilotController, Constants.AutoPilotController.YELLOW_CENTER);
    // JoystickButton YELLOW_RIGHT = new JoystickButton(autoPilotController, Constants.AutoPilotController.YELLOW_RIGHT);
    // JoystickButton BLUE_LEFT = new JoystickButton(autoPilotController, Constants.AutoPilotController.BLUE_LEFT);
    // JoystickButton BLUE_CENTER = new JoystickButton(autoPilotController, Constants.AutoPilotController.BLUE_CENTER);
    // JoystickButton BLUE_RIGHT = new JoystickButton(autoPilotController, Constants.AutoPilotController.BLUE_RIGHT);
    // JoystickButton CLEAR_BOTTOM = new JoystickButton(autoPilotController, Constants.AutoPilotController.CLEAR_BOTTOM);
    // JoystickButton CLEAR_MIDDLE = new JoystickButton(autoPilotController, Constants.AutoPilotController.CLEAR_MIDDLE);
    // JoystickButton CLEAR_TOP = new JoystickButton(autoPilotController, Constants.AutoPilotController.CLEAR_TOP);

    // -----------DRIVER
    // ----------intake-----------------------------------------------------------
    // LEFT_BUMPER.whileTrue(new InstantCommand(m_IntakeSubsystem::in, m_IntakeSubsystem));
    // LEFT_BUMPER.onFalse(new InstantCommand(m_IntakeSubsystem::in_hold, m_IntakeSubsystem));

    // RIGHT_BUMPER.whileTrue(new InstantCommand(m_IntakeSubsystem::out, m_IntakeSubsystem));
    // RIGHT_BUMPER.onFalse(new InstantCommand(m_IntakeSubsystem::out_hold, m_IntakeSubsystem));

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

    // //----------ASSISTANT--------------------------------------------------------------
    // ----------arm position-----------------------------------------------------------
    Y_BUTTON_AS.onTrue(new InstantCommand(m_ArmSubsystemPID::stepUp, m_ArmSubsystemPID));
    A_BUTTON_AS.onTrue(new InstantCommand(m_ArmSubsystemPID::stepDown, m_ArmSubsystemPID));

    // ----------wrist position-----------------------------------------------------------
    X_BUTTON_AS.onTrue(new InstantCommand(m_WristSubsystemPID::stepUp, m_WristSubsystemPID));
    B_BUTTON_AS.onTrue(new InstantCommand(m_WristSubsystemPID::stepDown, m_WristSubsystemPID));

    // ----RETURN TO ZERO---------------------------------------------------------------
    THREE_LINES_AS.onTrue(new InstantCommand(m_MasterController::zero, m_MasterController));

    // GREEN_LEFT.onTrue(new InstantCommand(m_MasterController::button1, m_MasterController));// ID:1
    // GREEN_CENTER.onTrue(new InstantCommand(m_MasterController::button2, m_MasterController));// ID:2
    // GREEN_RIGHT.onTrue(new InstantCommand(m_MasterController::button3, m_MasterController));// ID:3

    // YELLOW_LEFT.onTrue(new InstantCommand(m_MasterController::button4, m_MasterController));// ID:4
    // YELLOW_CENTER.onTrue(new InstantCommand(m_MasterController::button5, m_MasterController));// ID:5
    // YELLOW_RIGHT.onTrue(new InstantCommand(m_MasterController::button6, m_MasterController));// ID:6

    // BLUE_LEFT.onTrue(new InstantCommand(m_MasterController::button7, m_MasterController));// ID:7
    // BLUE_CENTER.onTrue(new InstantCommand(m_MasterController::button8, m_MasterController));// ID:8
    // BLUE_RIGHT.onTrue(new InstantCommand(m_MasterController::button9, m_MasterController));// ID:9

    // CLEAR_TOP.onTrue(new InstantCommand(m_MasterController::buttonTop, m_MasterController));// ID:10
    // CLEAR_MIDDLE.onTrue(new InstantCommand(m_MasterController::buttonMiddle, m_MasterController));// ID:11
    // CLEAR_BOTTOM.onTrue(new InstantCommand(m_MasterController::buttonBottom, m_MasterController));// ID:12
    // ------------------------------------------------------------------------------------------
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_TrajectoryChooser.getSelected();
  }

  private void setupTrajectoryDashboardChooser() {
    m_TrajectoryChooser = new SendableChooser<Command>();
    m_TrajectoryChooser.setDefaultOption("ConeTable", autoSequences.ConeTablePath());
    m_TrajectoryChooser.addOption("CubeTable", autoSequences.CubeTablePath());
    m_TrajectoryChooser.addOption("Center", autoSequences.CenterPath());
    m_TrajectoryChooser.addOption("Side", autoSequences.SidePath());

    SmartDashboard.putData(m_TrajectoryChooser);
  }

  public void teleopInit() {
    // re-orient robot heading to foward heading away from drive station
    m_drivetrainSubsystem.resetHeading();
  }
}