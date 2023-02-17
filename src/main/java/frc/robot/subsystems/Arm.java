package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

public class Arm extends SubsystemBase { 
  private CANSparkMax armMotor;
  public Arm(){
    armMotor = new CANSparkMax(0, CANSparkMaxLowLevel.MotorType.kBrushed);
  }

  public void Move(double speed){
    armMotor.set(speed);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
