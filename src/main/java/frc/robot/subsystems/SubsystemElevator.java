// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkFlexConfig;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants.PIDConstants;
import frc.robot.Constants.ElevatorConstants.MotorIDConstants;;
public class SubsystemElevator extends SubsystemBase {
  public PIDController pid;
  public XboxController controller;
  public final SparkBase motor1;
  public final SparkBase motor2;
  public SubsystemElevator(XboxController xbcontroller) {
    pid = new PIDController(PIDConstants.motorP,PIDConstants.motorI,PIDConstants.motorD);
    controller = xbcontroller;
    motor1 = new SparkFlex(MotorIDConstants.motor1ID, MotorType.kBrushless);
    motor2 = new SparkFlex(MotorIDConstants.motor2ID, MotorType.kBrushless);
    SparkBaseConfig motor1Config = new SparkFlexConfig();
    SparkBaseConfig motor2Config = new SparkFlexConfig();
    motor2Config.follow(motor1);
    motor1.configure(motor1Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    motor2.configure(motor2Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }
  public void SetMotor(double speed) {
    motor1.set(speed);
  }
  public void SetPosition(double position) {
    //Used for getting motor position
    RelativeEncoder encoder = motor1.getEncoder();
    motor1.set(pid.calculate(encoder.getPosition(), position));
  }
}
