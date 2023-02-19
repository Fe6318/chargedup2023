package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Arm extends SubsystemBase { 
  private VictorSPX armMotor;
  public Arm(){
    armMotor = new VictorSPX(10);
  }

  public void Move(double speed){
    armMotor.set(ControlMode.PercentOutput,speed);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
