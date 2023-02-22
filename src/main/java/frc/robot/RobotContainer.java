package frc.robot;

import frc.robot.commands.AutoDrive;
import frc.robot.commands.AutoRaiseArm;
import frc.robot.commands.ClampCone;
import frc.robot.commands.ClampCube;
import frc.robot.commands.Drive;
import frc.robot.commands.ManualArm;
import frc.robot.commands.ManualClamp;
import frc.robot.commands.ReleaseCube;
import frc.robot.commands.ReleaseCone;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Clamp;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
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
  private static JoystickButton clampInConeButton;
  private static JoystickButton clampOutConeButton;
  private static JoystickButton clampInCubeButton;
  private static JoystickButton clampOutCubeButton;

  // subsystems
  private static DriveTrain driveTrain;
  private static Clamp clamp;
  private static Arm arm;

  // commands
  private static ClampCone clampCone;
  private static ClampCube clampCube;
  private static ReleaseCone releaseCone;
  private static ReleaseCube releaseCube;
  private static Drive drive;
  private static ManualClamp manualClamp;
  private static ManualArm manualArm;

  // auto commands
  private static AutoDrive autoDrive;
  private static AutoRaiseArm autoRaiseArm;
  private static SequentialCommandGroup autonomous;
  
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
    releaseCone = new ReleaseCone(clamp);
    releaseCube = new ReleaseCube(clamp);
    manualClamp = new ManualClamp(clamp, operator);
    manualArm = new ManualArm(arm, operator);
    
    // non manual clamp buttons
    clampInConeButton = new JoystickButton(operator, Constants.clampConeIn);
    clampOutConeButton = new JoystickButton(operator, Constants.clampConeOut);
    clampInCubeButton = new JoystickButton(operator, Constants.clampCubeIn);
    clampOutCubeButton = new JoystickButton(operator, Constants.clampCubeOut);

    // set default commands
    driveTrain.setDefaultCommand(drive);
    clamp.setDefaultCommand(manualClamp);
    arm.setDefaultCommand(manualArm);

    // auto
    autoDrive = new AutoDrive(driveTrain,2);
    autoRaiseArm = new AutoRaiseArm(arm,3);
    // ex parallelCommand = new ParallelCommandGroup(autoDrive, autoRaiseArm);
    autonomous = new SequentialCommandGroup(autoDrive, autoRaiseArm);

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
    clampInConeButton.onTrue(clampCone);
    clampOutConeButton.onTrue(releaseCone);
    clampInCubeButton.onTrue(clampCube);
    clampOutCubeButton.onTrue(releaseCube);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    /* autonomous idea: push cube in lower shelf, back up while flipping arm, and possibly
    make it to charging station */  
    //return autoDrive;
    return autonomous;
    //return autoRaiseArm;
  }
}
