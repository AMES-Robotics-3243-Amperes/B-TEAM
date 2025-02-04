package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;

/**
 * *@author 0?
 */
public class SubsystemDifferential extends SubsystemBase {
  private final SparkMax leftMotor;
  private final SparkMax rightMotor;

  private final SparkClosedLoopController leftController;
  private final SparkClosedLoopController rightController;

  private double pivotOutput = 0.0;
  private double spinOutput = 0.0;

  public SubsystemDifferential(int leftMotorID, int rightMotorID) {
    // 0? Instantiate motor controllers 
    leftMotor = new SparkMax(11, MotorType.kBrushless);
    rightMotor = new SparkMax(10, MotorType.kBrushless);

    // 0? Create config objects 
    SparkMaxConfig leftConfig = new SparkMaxConfig();
    SparkMaxConfig rightConfig = new SparkMaxConfig();

    // 0? feedback sensor   
    leftConfig.closedLoop.feedbackSensor(FeedbackSensor.kAbsoluteEncoder);
    rightConfig.closedLoop.feedbackSensor(FeedbackSensor.kAbsoluteEncoder);

    // 0? Optional minimal PIDF
    leftConfig.closedLoop.pidf(0.1, 0.0, 0.0, 0.0);
    rightConfig.closedLoop.pidf(0.1, 0.0, 0.0, 0.0);

    // 0? Set idle mode and current limit  
    leftConfig.idleMode(IdleMode.kBrake);
    rightConfig.idleMode(IdleMode.kBrake);

    leftConfig.smartCurrentLimit(30);
    rightConfig.smartCurrentLimit(30);

    // 0? Apply config
    leftMotor.configure(leftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    rightMotor.configure(rightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // 0? Retrieve the ClosedLoop controllers 
    leftController = leftMotor.getClosedLoopController();
    rightController = rightMotor.getClosedLoopController();
  }

  /**
   * 0? Pivot command: runs both motors in the same direction 
   *  
   * @param speed  
   */
  public void pivot(double speed) {
    pivotOutput = -speed * 0.5;
  }

  /**
   * 0? Intake command: runs motors in opposite directions 
   * 
   * @param speed  
   */
  public void intake(double speed) {
    spinOutput = speed * 0.5;
  }

  /**
   * 0? Outtake command: opposite of intake
   * 
   * @param speed  
   */
  public void outtake(double speed) {
    spinOutput = -speed * 0.5;
  }

  /**
   * 0? Full stop both motors 
   */
  public void stop() {
    pivotOutput = 0.0;
    spinOutput = 0.0;
  }

  @Override
  public void periodic() {
    double left = pivotOutput + spinOutput;
    double right = pivotOutput - spinOutput;

    leftController.setReference(left, ControlType.kDutyCycle);
    rightController.setReference(right, ControlType.kDutyCycle);
  }
}
