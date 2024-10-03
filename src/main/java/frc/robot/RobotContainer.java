// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  
  public static final double translationDeadband = 0.1;
  public static final double rotDeadband = 0.1;
  private static RobotContainer container;
  /* Setting up bindings for necessary control of the swerve drive platform */
  public final CommandXboxController driver = new CommandXboxController(0); // Driver joystick

  // private final CommandSwerveDrivetrain drivetrain = CommandSwerveDrivetrain.getInstance(); // Drivetrain
  
  // -----===--Subsystems--===----- 
  // private final Indexer s_Indexer = inde.getInstance();
  // private final Intake s_Intake = Intake.getInstance();
  // private final Pivot s_Pivot = Pivot.getInstance();
  // private final Shooter s_Shooter = Shooter.getInstance();

  // WHY ARE THE INSTANCES NOT IN THE BOILER OF EACH ONE OF THESE!!! -iggy

  public CommandXboxController getDriverController() {
    return driver;
  }

  public static RobotContainer getInstance() { //if we ever need to grab controller values lol idk
    if (container == null) {
        container = new RobotContainer();
    }
    return container;
  }

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() { //theoretical bindings bc SUBSYSTEMS ARENT ON THIS BRANCH
    // driver.a().onTrue(intake);
    // driver.x().onTrue(index or smth idk);
    // driver.b().onTrue();
    // driver.y().whileTrue(new SetIndexer(IndexerStates.SHOOTING));

    // NO SWERVE!

    // drivetrain.setDefaultCommand( // Drivetrain will execute this command periodically
    //     drivetrain.applyRequest(() -> drivetrain.drive(-driver.getLeftY(),-driver.getLeftX(),-driver.getRightX()))
    // );

    // driverBack.onTrue(new InstantCommand(() -> drivetrain.resetOdo(new Pose2d(0, 0, new Rotation2d()))));


  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
