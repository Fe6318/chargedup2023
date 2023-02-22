package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Arm extends SubsystemBase { 
  //private VictorSPX armMotor;
  private WPI_VictorSPX armMotor;
  public Arm(){
    //armMotor = new VictorSPX(10);
    armMotor = new WPI_VictorSPX(10);
  }

  public void Move(double speed){
    //armMotor.set(ControlMode.PercentOutput,speed);
    armMotor.set(speed);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
