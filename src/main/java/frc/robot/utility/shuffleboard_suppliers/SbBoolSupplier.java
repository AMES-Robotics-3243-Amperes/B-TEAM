// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utility.shuffleboard_suppliers;

import java.util.function.BooleanSupplier;

/** Add your docs here. */
public class SbBoolSupplier implements BooleanSupplier {
    private boolean internalBool;
    @Override
    public boolean getAsBoolean() {
      return internalBool;
    }
    public void setBoolean(boolean bool) {
      internalBool = bool;
    }
    public SbBoolSupplier(boolean bool) {
      setBoolean(bool);
    }
  }
