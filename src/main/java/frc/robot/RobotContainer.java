// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.AutoDrive;
import frc.robot.commands.ClampCone;
import frc.robot.commands.ClampCube;
import frc.robot.commands.Drive;
import frc.robot.commands.ManualClamp;
import frc.robot.subsystems.Clamp;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  // joysticks
  private static Joystick driver;
  private static Joystick operator;
  private static JoystickButton clampInButton;
  private static JoystickButton clampOutButton;

  // subsystems
  private static Clamp clamp;
  private static DriveTrain driveTrain;

  // commands
  private static ClampCone clampCone;
  private static ClampCube clampCube;
  private static Drive drive;
  private static ManualClamp manualClamp;

  // auto commands
  private static AutoDrive autoDrive;
  
  public RobotContainer() {
    // initialize variables
    driver = new Joystick(0);
    operator = new Joystick(1);
    clamp = new Clamp();
    driveTrain = new DriveTrain();
    clampCone = new ClampCone(clamp);
    clampCube = new ClampCube(clamp);
    drive = new Drive(driveTrain, driver);
    manualClamp = new ManualClamp(clamp, operator);
    
    // clamp buttons
    clampInButton = new JoystickButton(operator, Constants.clampIn);
    clampOutButton = new JoystickButton(operator, Constants.clampOut);

    // set default commands
    driveTrain.setDefaultCommand(drive);
    clamp.setDefaultCommand(clampCone);

    // auto
    autoDrive = new AutoDrive(driveTrain,4);

    // configure button bindings
    configureBindings();
  }
  
  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    clampInButton.whileTrue(new ClampCone(clamp));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autoDrive;
  }
}
