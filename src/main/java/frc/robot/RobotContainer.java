package frc.robot;

import frc.robot.commands.AutoDrive;
import frc.robot.commands.ClampCone;
import frc.robot.commands.ClampCube;
import frc.robot.commands.Drive;
import frc.robot.commands.ManualArm;
import frc.robot.commands.ManualClamp;
import frc.robot.subsystems.Arm;
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

  // joystick buttons
  private static JoystickButton clampInButton;
  private static JoystickButton clampOutButton;

  // subsystems
  private static DriveTrain driveTrain;
  private static Clamp clamp;
  private static Arm arm;

  // commands
  private static ClampCone clampCone;
  private static ClampCube clampCube;
  private static Drive drive;
  private static ManualClamp manualClamp;
  private static ManualArm manualArm;

  // auto commands
  private static AutoDrive autoDrive;
  
  public RobotContainer() {
    // controllers
    driver = new Joystick(0);
    operator = new Joystick(1);

    // subsytems
    clamp = new Clamp();
    driveTrain = new DriveTrain();
    arm = new Arm();

    // commands
    drive = new Drive(driveTrain, driver);
    clampCone = new ClampCone(clamp);
    clampCube = new ClampCube(clamp);
    manualClamp = new ManualClamp(clamp, operator);
    manualArm = new ManualArm(arm, operator);
    //manualArm = new ManualArm(arm);
    
    // clamp buttons
    clampInButton = new JoystickButton(operator, Constants.clampIn);
    clampOutButton = new JoystickButton(operator, Constants.clampOut);

    // set default commands
    driveTrain.setDefaultCommand(drive);
    clamp.setDefaultCommand(manualClamp);
    arm.setDefaultCommand(manualArm);

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
    //clampInButton.whileTrue(new ClampCone(clamp));
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
