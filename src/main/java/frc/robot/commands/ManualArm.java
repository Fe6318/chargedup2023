// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class ManualArm extends CommandBase {
  private Arm arm;
  private Joystick operator;

  public ManualArm(Arm arm, Joystick operator) {
    this.arm = arm;
    addRequirements(arm);
    this.operator = operator;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize(){}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute(){
    double x = operator.getRawAxis(0);
    double x2 = operator.getRawAxis(0);
    arm.Move(x-x2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
