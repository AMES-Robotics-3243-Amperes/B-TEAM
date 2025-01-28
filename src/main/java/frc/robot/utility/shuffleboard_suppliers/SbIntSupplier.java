// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utility.shuffleboard_suppliers;

import java.util.function.LongSupplier;
public class SbIntSupplier implements LongSupplier {
  private int internalInt;
  @Override
  public long getAsLong() {
    return internalInt;
  }
  public void setInt(int integer) {
    internalInt = integer;
  }
  public SbIntSupplier(int integer) {
    setInt(integer);
  }
}