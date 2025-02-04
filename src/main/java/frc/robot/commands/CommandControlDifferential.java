package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.JoyUtil;
import frc.robot.subsystems.SubsystemDifferential;

/**
 *  * @author 0?
 */
public class CommandControlDifferential extends Command{
  private final SubsystemDifferential m_differential;
  private final JoyUtil m_controller;

  public CommandControlDifferential(SubsystemDifferential subsystem, JoyUtil primaryController) {
    this.m_differential = subsystem;
    this.m_controller = primaryController;

    addRequirements(subsystem);
  }

@Override
  public void initialize() {

  }

  @Override
  public void execute() {
    // 0? Pivot up/down on right joystick
    double pivotSpeed = -m_controller.getRightY(); 
    m_differential.pivot(pivotSpeed);

    // 0? check bumpers for intake/outtake
    boolean leftBumperPressed = m_controller.getLeftBumper();
    boolean rightBumperPressed = m_controller.getRightBumper();

    if (leftBumperPressed) {
      m_differential.intake(0.5); 
    } else if (rightBumperPressed) {
      m_differential.outtake(-0.5);
    } else {  
    }
  }

  @Override
  public void end(boolean interrupted) {
    // 0? Optionally stop motors when the command ends
    m_differential.stop();
  }

  @Override
  public boolean isFinished() {
    // 0? Run forever in teleop
    return false;
  }
}
