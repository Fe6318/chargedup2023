package frc.robot;

import frc.robot.commands.AutoDrive;
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
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
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
  private static AutoDrive R1driveBackward1;
  private static AutoDrive R1driveForward1;
  private static AutoDrive R1driveBackward2;
  private static AutoDrive R1rightTurn1;
  private static AutoDrive R1leftTurn1;
  private static AutoDrive R1driveBackward3;
  private static AutoDrive R1driveBackward4;
  private static AutoDrive R1rightTurn2;
  private static AutoDrive R1driveForward2;
  // drive auto 2 
  private static AutoDrive R2driveBackward1;
  private static AutoDrive R2driveForward1;
  private static AutoDrive R2driveBackward2;
  // drive auto 3 
  private static AutoDrive R3driveBackward1;
  private static AutoDrive R3driveForward1;
  private static AutoDrive R3driveBackward2;
  private static AutoDrive R3driveForward2;
  //drive auto 4 
  private static AutoDrive R4driveBackward1;
  private static AutoDrive R4driveForward1;
  private static AutoDrive R4driveBackward2;
  private static AutoDrive R4rightTurn1;
  private static AutoDrive R4leftTurn1;
  private static AutoDrive R4driveBackward3;
  private static AutoDrive R4driveBackward4;
  private static AutoDrive R4leftTurn2;
  private static AutoDrive R4driveForward2;
  // arm auto 1
  private static AutoRaiseArm R1raiseArm1;
  private static AutoLowerArm R1lowerArm1;
  private static AutoRaiseArm R1raiseArm2;
  private static AutoLowerArm R1lowerArm2;
  // arm auto 2
  private static AutoRaiseArm R2raiseArm1;
  private static AutoLowerArm R2lowerArm1;
  private static AutoRaiseArm R2raiseArm2;
  private static AutoLowerArm R2lowerArm2;
  // arm auto 3
  private static AutoRaiseArm R3raiseArm1;
  private static AutoLowerArm R3lowerArm1;
  private static AutoRaiseArm R3raiseArm2;
  private static AutoLowerArm R3lowerArm2;
  // arm auto 4
  private static AutoRaiseArm R4raiseArm1;
  private static AutoLowerArm R4lowerArm1;
  private static AutoRaiseArm R4raiseArm2;
  private static AutoLowerArm R4lowerArm2;
  
  // grouped auto
  private static SequentialCommandGroup R1armMovement1;
  private static SequentialCommandGroup R2armMovement1;
  private static SequentialCommandGroup R3armMovement1;
  private static SequentialCommandGroup R4armMovement1;
  // auto 1 
  private final SequentialCommandGroup autonomous1;
  private static ParallelCommandGroup R1backAndArm1;
  private static ParallelCommandGroup R1backAndArm2;
  // auto 2 
  private final SequentialCommandGroup autonomous2;
  private static ParallelCommandGroup R2backAndArm1;
  private static ParallelCommandGroup R2backAndArm2;
  // auto 3 
  private final SequentialCommandGroup autonomous3;
  private static ParallelCommandGroup R3backAndArm1;
  private static ParallelCommandGroup R3backAndArm2;
  // auto 4 
  private final SequentialCommandGroup autonomous4;
  private static ParallelCommandGroup R4backAndArm1;
  private static ParallelCommandGroup R4backAndArm2;

  // a chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();
 
  
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
    /* lift arm to release cube and drive back, push cube into space, retreat back, rotate right, 
    move back, rotate left, drive back and lower clamp, rotate right, drive onto charging station */
    R1raiseArm1 = new AutoRaiseArm(arm, 3);
    R1lowerArm1 = new AutoLowerArm(arm, 3);
    // up and down arm movement to lower clamp
    R1armMovement1 = new SequentialCommandGroup(R1raiseArm1, R1lowerArm1); 

    R1raiseArm2 = new AutoRaiseArm(arm, 3);
    R1lowerArm2 = new AutoLowerArm(arm,2);
    R1driveBackward1 = new AutoDrive(driveTrain, 5, -.5,0);
    //raise arm while backing up
    R1backAndArm1 = new ParallelCommandGroup(R1driveBackward1, R1raiseArm2);

    R1driveForward1 = new AutoDrive(driveTrain, 5, .5, 0);
    R1driveBackward2 = new AutoDrive(driveTrain, 1, -.5, 0);
    R1rightTurn1 = new AutoDrive(driveTrain, 1,0,.5);
    R1driveBackward3 = new AutoDrive(driveTrain,1,-.5,0);
    R1leftTurn1 = new AutoDrive(driveTrain,1,0,-.5);
    R1driveBackward4 = new AutoDrive(driveTrain, 4, -.75, 0);
    R1rightTurn2 = new AutoDrive(driveTrain, 2, 0, .5);
    R1driveForward2 = new AutoDrive(driveTrain, 4, .5,-.25);

    // move arm while backing up for long stretch
    R1backAndArm2 = new ParallelCommandGroup(R1driveBackward4, R1armMovement1);

    // final auto command
    autonomous1 = new SequentialCommandGroup(R1backAndArm1, R1lowerArm2, R1driveForward1, 
    R1driveBackward2, R1rightTurn1, R1driveBackward3, R1leftTurn1, R1backAndArm2, R1rightTurn2, 
    R1driveForward2);
    //////////////////////////////////////////////////////////////////////////////////////////////////
    // route #2
    /* raise arm to drop cube and drive back, drop arm, push cube into space, retreat back to charging 
    station attempting to dock and not balance and also lowering the clamp at the same time */
    R2raiseArm1 = new AutoRaiseArm(arm, 3);
    R2lowerArm1 = new AutoLowerArm(arm, 3);
    // up and down arm movement to lower clamp
    R2armMovement1 = new SequentialCommandGroup(R2raiseArm1, R2lowerArm1); 

    R2raiseArm2 = new AutoRaiseArm(arm, 3);
    R2lowerArm2 = new AutoLowerArm(arm,2);
    R2driveBackward1 = new AutoDrive(driveTrain, 5, -.5, 0);
    //raise arm while backing up
    R2backAndArm1 = new ParallelCommandGroup(R2driveBackward1, R2raiseArm2);

    R2driveForward1 = new AutoDrive(driveTrain, 4, .5, 0);
    R2driveBackward2 = new AutoDrive(driveTrain, 4, -.5, 0);
    R2backAndArm2 = new ParallelCommandGroup(R2driveBackward2, R2armMovement1);

    // final auto command
    autonomous2 = new SequentialCommandGroup(R2backAndArm1, R2lowerArm2, 
    R2driveForward1, R2backAndArm2);
    //////////////////////////////////////////////////////////////////////////////////////////////////
    // route #3
    /* raise arm to release cube and drive back, push cube into space, drive back over charging 
    station while lowering clamp, then drive forward to dock on station and not balance */
    R3raiseArm1 = new AutoRaiseArm(arm, 3);
    R3lowerArm1 = new AutoLowerArm(arm, 3);
    // up and down arm movement to lower clamp
    R3armMovement1 = new SequentialCommandGroup(R3raiseArm1, R3lowerArm1); 

    R3raiseArm2 = new AutoRaiseArm(arm, 3);
    R3lowerArm2 = new AutoLowerArm(arm,2);
    R3driveBackward1 = new AutoDrive(driveTrain, 5, -.5, 0);

    // raise arm while backing up
    R3backAndArm1 = new ParallelCommandGroup(R3driveBackward1, R3raiseArm2);

    R3raiseArm1 = new AutoRaiseArm(arm, 4);
    R3lowerArm1 = new AutoLowerArm(arm,2);
    R3driveForward1 = new AutoDrive(driveTrain, 4, .5, 0);
    R3driveBackward2 = new AutoDrive(driveTrain, 8, -.5, 0);
    // raise arm while backing up
    R3backAndArm2 = new ParallelCommandGroup(R3driveBackward2, R3armMovement1);
    R3driveForward2 = new AutoDrive(driveTrain, 4, .5,0);

    // final auto command
    autonomous3 = new SequentialCommandGroup(R3backAndArm1, R3lowerArm2, 
    R3driveForward1, R3backAndArm2, R3driveForward2);
    //////////////////////////////////////////////////////////////////////////////////////////////////
    // route #4
    /* raise arm to release cube and back up, lower arm, push cube into space, retreat back, rotate left, move back, rotate right, 
    drive back and lower clamp, rotate left, drive onto charging station */
    //R4raiseArm1 = new AutoRaiseArm(arm, 3);
    //R4lowerArm1 = new AutoLowerArm(arm, 3);
    // up and down arm movement to lower clamp
    //R4armMovement1 = new SequentialCommandGroup(R4raiseArm1, R4lowerArm1); 

    R4raiseArm1 = new AutoRaiseArm(arm, 3);
    R4lowerArm1 = new AutoLowerArm(arm,2);
    R4driveBackward1 = new AutoDrive(driveTrain, 2, -.5, 0);

    //raise arm while backing up
    R4backAndArm1 = new ParallelCommandGroup(R4driveBackward1, R4raiseArm1);

    R4raiseArm2 = new AutoRaiseArm(arm, 3);
    R4lowerArm2 = new AutoLowerArm(arm,2);
    R4driveForward1 = new AutoDrive(driveTrain, 3, .5, 0);
    R4driveBackward2 = new AutoDrive(driveTrain, 3, -.5, 0);
    //R4leftTurn1 = new AutoDrive(driveTrain,1,0,-.5);
    R4driveBackward3 = new AutoDrive(driveTrain,1,-.5,0);
    //R4rightTurn1 = new AutoDrive(driveTrain, 1,0,.5);
    R4driveBackward4 = new AutoDrive(driveTrain, 3, -.75, 0);
    //R4leftTurn2 = new AutoDrive(driveTrain, 2, 0, -.5);
    R4driveForward2 = new AutoDrive(driveTrain, 4, .5,.25);

    // move arm while backing up for long stretch
    //R4backAndArm2 = new ParallelCommandGroup(R4driveBackward4, R4armMovement1);

    // final auto command
    /*autonomous4 = new SequentialCommandGroup(R4backAndArm1, R4lowerArm2, R4driveForward1, 
    R4driveBackward2, R4rightTurn1, R4driveBackward3, R4leftTurn1, R4driveBackward4/*R4backAndArm2, R4leftTurn2, 
    R4driveForward2);*/
    autonomous4 = new SequentialCommandGroup(R4backAndArm1, R4lowerArm2, R4driveForward1,
    R4driveBackward2);


    //////////////////////////////////////////////////////////////////////////////////////////////////
    // add commands to the autonomous command chooser
    m_chooser.setDefaultOption("route #1", autonomous1);
    m_chooser.addOption("route #2", autonomous2);
    m_chooser.addOption("route #3", autonomous3);
    m_chooser.addOption("route #4", autonomous4);

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
