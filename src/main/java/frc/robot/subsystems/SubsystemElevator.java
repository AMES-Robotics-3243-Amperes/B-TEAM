// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants.PIDConstants;
public class SubsystemElevator extends SubsystemBase {
  public PIDController pid;
  public XboxController controller;
  /** Creates a new SubsystemElevator. */
  public SubsystemElevator(XboxController xbcontroller) {
    pid = new PIDController(PIDConstants.motorP,PIDConstants.motorI,PIDConstants.motorD);
    controller = xbcontroller;
  }
  public void SetMotor(int id, double speed) {
    
  }
}
