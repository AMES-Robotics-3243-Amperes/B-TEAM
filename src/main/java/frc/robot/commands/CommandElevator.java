// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SubsystemElevator;
import frc.robot.utility.shuffleboard_suppliers.*;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class CommandElevator extends Command {


  private final SubsystemElevator m_subsystem;
  private final XboxController m_controller;
  private boolean manualControl = false;
  //Note that this variable is obsolete during manual control
  private int elevatorLevel;
  //Note that the elevatorLevels variable is currently using placeholder values. Remove this message when values are changed to not be placeholders.
  private final double[] elevatorLevels = {1,2,3,4};
  private SbBoolSupplier mcSupplier = new SbBoolSupplier(manualControl);
  private SbIntSupplier lvlSupplier = new SbIntSupplier(elevatorLevel);
  /** Creates a new CommandElevator. */
  public CommandElevator(SubsystemElevator subsystem, XboxController controller) {
    m_subsystem = subsystem;
    m_controller = controller;
    Shuffleboard.getTab("Elevator Status").addBoolean("Manual Control", mcSupplier);
    Shuffleboard.getTab("Elevator Status").addInteger("Elevator Level", lvlSupplier);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mcSupplier.setBoolean(manualControl);
    lvlSupplier.setInt(elevatorLevel);
    if (m_controller.getStartButtonPressed()) {
      manualControl = !manualControl;
    }
    if (manualControl) {
      if (m_controller.getLeftBumperButtonPressed()){
        m_subsystem.SetMotor(-0.25);
      }
      if (m_controller.getLeftBumperButtonReleased()){
        m_subsystem.SetMotor(0);
      }
      if (m_controller.getRightBumperButtonPressed()){
        m_subsystem.SetMotor(0.25);
      }
      if (m_controller.getRightBumperButtonReleased()){
        m_subsystem.SetMotor(0);
      }
    } else {
      if (m_controller.getLeftBumperButtonPressed() && elevatorLevel > 0) {
        elevatorLevel--;
      }
      if (m_controller.getRightBumperButtonPressed() && elevatorLevel < elevatorLevels.length - 1) {
        elevatorLevel++;
      }
      m_subsystem.SetPosition(elevatorLevels[elevatorLevel]);
    }
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

