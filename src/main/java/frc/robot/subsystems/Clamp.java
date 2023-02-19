package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Clamp extends SubsystemBase {
  private TalonSRX clampMotor;
  public Clamp(){
    clampMotor = new TalonSRX(0);
  }

  public void Move(double speed){
    clampMotor.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

