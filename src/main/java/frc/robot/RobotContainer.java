package frc.robot;

import frc.robot.commands.AutoDrive;
import frc.robot.commands.AutoDriveTurn;
import frc.robot.commands.AutoLowerArm;
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

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

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

  // manual commands
  private static Drive drive;
  private static ManualClamp manualClamp;
  private static ManualArm manualArm;

  // auto commands
  // clamp
  private static ClampCone clampCone;
  private static ClampCube clampCube;
  private static ReleaseCone releaseCone;
  private static ReleaseCube releaseCube;
  // drive auto 1 
  private static AutoDrive R1driveBack1;
  private static AutoDrive R1driveForward1;
  private static AutoDrive R1driveBack2;
  private static AutoDriveTurn R1turnLeft1;
  private static AutoDrive R1driveForward2;
  private static AutoDriveTurn R1turnRight1;
  private static AutoDrive R1driveForward3;
  
  // arm auto 1
  private static AutoRaiseArm R1armUp1;
  private static AutoLowerArm R1armDown1;
  
  
  // grouped commands for auto
  private final SequentialCommandGroup autonomous1;
  //private final SequentialCommandGroup autonomous2;
  //private final SequentialCommandGroup autonomous3;
  //private final SequentialCommandGroup autonomous4;
  private static ParallelCommandGroup R1backAndArm1;

  

  // to test gyro auto
  private final AutoDriveTurn autonomous5;

  // navX MXP using SPI
  private static AHRS gyro;

  // a chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();
 
  public RobotContainer() {
    // gyro
    gyro = new AHRS(SPI.Port.kMXP);
    gyro.reset();
    Shuffleboard.getTab("gyro").add(gyro);
    
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
    
    // auto clamp buttons
    clampInConeButton = new JoystickButton(operator, Constants.clampConeIn);
    clampOutConeButton = new JoystickButton(operator, Constants.clampConeOut);
    clampInCubeButton = new JoystickButton(operator, Constants.clampCubeIn);
    clampOutCubeButton = new JoystickButton(operator, Constants.clampCubeOut);

    // set default commands
    driveTrain.setDefaultCommand(drive);
    clamp.setDefaultCommand(manualClamp);
    arm.setDefaultCommand(manualArm);

    // auto
    /////////////////////////////////////////////////////////////////////////////////////////////////
    // route #1
    /* lift arm to release cube and drive back, push cube into space, retreat back, turn 90 degrees, 
    drive onto charging station */

    // drive back command 
    R1driveBack1 = new AutoDrive(driveTrain, 2, -.5, 0, gyro);
    // raise arm command
    R1armUp1 = new AutoRaiseArm(arm, 2);
    // back and arm up at same time
    R1backAndArm1 = new ParallelCommandGroup(R1driveBack1, R1armUp1);
    // arm lower slowly
    R1armDown1 = new AutoLowerArm(arm, 1);
    // drive forward 
    R1driveForward1 = new AutoDrive(driveTrain, 2, .5, 0, gyro);
    // straight back
    R1driveBack2 = new AutoDrive(driveTrain, 3, -.5, 0, gyro);
    // turn 90 degrees
    R1turnLeft1 = new AutoDriveTurn(driveTrain, -90, gyro);
    // drive forward
    R1driveForward2 = new AutoDrive(driveTrain, 2, .5, 0, gyro);
    // turn right
    R1turnRight1 = new AutoDriveTurn(driveTrain, 90, gyro);
    // drive forward
    R1driveForward3 = new AutoDrive(driveTrain, 3, .5, 0, gyro);

    // final auto command
    autonomous1 = new SequentialCommandGroup(R1backAndArm1, R1armDown1, R1driveForward1, R1driveBack2,
    R1turnLeft1, R1driveForward2, R1turnRight1, R1driveForward3);
    //////////////////////////////////////////////////////////////////////////////////////////////////
    // route #2
    /* raise arm to drop cube and drive back, drop arm, push cube into space, retreat back to charging 
    station attempting to dock and not balance and also lowering the clamp at the same time */

    // final auto command
    //autonomous2 = new SequentialCommandGroup(null);
    //////////////////////////////////////////////////////////////////////////////////////////////////
    // route #3
    /* raise arm to release cube and drive back, push cube into space, drive back over charging 
    station while lowering clamp, then drive forward to dock on station and not balance */

    // final auto command
    //autonomous3 = new SequentialCommandGroup(null);
    //////////////////////////////////////////////////////////////////////////////////////////////////
    // route #4
    /* raise arm to release cube and back up, lower arm, push cube into space, retreat back, rotate left, move back, rotate right, 
    drive back and lower clamp, rotate left, drive onto charging station */

    // final auto command
    //autonomous4 = new SequentialCommandGroup(null);
    //////////////////////////////////////////////////////////////////////////////////////////////////
    // route 5
    autonomous5 = new AutoDriveTurn(driveTrain, 90, gyro);
    //////////////////////////////////////////////////////////////////////////////////////////////////
    // add commands to the autonomous command chooser
    m_chooser.setDefaultOption("route #1", autonomous1);
    //m_chooser.addOption("route #2", autonomous2);
    //m_chooser.addOption("route #3", autonomous3);
    //m_chooser.addOption("route #4", autonomous4);
    m_chooser.addOption("route #5", autonomous5);

    // put the chooser on the dashboard
    SmartDashboard.putData(m_chooser);

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
    // possible points: 14 (up to 18 if charging station engaged)
    // positions: Red 1, Blue 3
    //return autonomous1;

    // possible points: 11 (up to 15 if charging station engaged)
    // positions: Red 2, Blue 2
    //return autonomous2;

    // possible points: 14 (up to 18 if charging station engaged)
    // positions: Red 2, Blue 2 
    //return autonomous3;
    
    // possible points: 14 (up to 18 if charging station engaged)
    // positions: Red 3, Blue 1
    //return autonomous4;

    return m_chooser.getSelected();

  }
}
