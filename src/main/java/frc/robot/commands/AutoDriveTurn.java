package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AutoDriveTurn extends CommandBase{
  private DriveTrain driveTrain;
  private double angle;
  private Gyro gyro;
  private double kP = 0.0125;
  private double kI = 0;
  private double kD = 0.0025;
  private double error;
  private PIDController turnPID;
  
  public AutoDriveTurn(DriveTrain driveTrain, double angle, Gyro gyro){
    this.driveTrain = driveTrain;
    this.angle = angle;
    this.gyro = gyro;
    addRequirements(driveTrain);
    error = 0;
    turnPID = new PIDController(kP, kI, kD);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize(){
    gyro.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute(){
    turnPID.enableContinuousInput(180,180);
    turnPID.setTolerance(3,5);

    SmartDashboard.putNumber("turn kp", kP);
    kP = SmartDashboard.getNumber("turn kP", 0);
    
    error = angle - gyro.getAngle();
    driveTrain.Drive(0, turnPID.calculate(gyro.getAngle(), angle));
    SmartDashboard.putNumber("auto drive error", error);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted){
    driveTrain.Drive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return turnPID.atSetpoint();
  }
}
