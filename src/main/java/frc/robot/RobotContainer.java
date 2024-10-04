// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
<<<<<<< HEAD
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.CommandFactory;
import frc.robot.commands.SetIndexer;
import frc.robot.commands.SetIntake;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Indexer.IndexerStates;
import frc.robot.subsystems.Intake.IntakeState;
=======
>>>>>>> origin/Robot-Container

public class RobotContainer {
  
  public static final double translationDeadband = 0.1;
  public static final double rotDeadband = 0.1;
  private static RobotContainer container;
  /* Setting up bindings for necessary control of the swerve drive platform */
  public final CommandXboxController driver = new CommandXboxController(0); // Driver joystick

  
      private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
            .withDeadband(Constants.MaxSpeed * translationDeadband).withRotationalDeadband(Constants.MaxAngularRate * rotDeadband)
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // I want field-centric
    // driving in open loop
    /* Driver Buttons */
    private final Trigger driverBack = driver.back();
    private final Trigger driverStart = driver.start();
    private final Trigger driverA = driver.a();
    private final Trigger driverB = driver.b();
    private final Trigger driverX = driver.x();
    private final Trigger driverY = driver.y();
    private final Trigger driverRightBumper = driver.rightBumper();
    private final Trigger driverLeftBumper = driver.rightBumper();
    private final Trigger driverLeftTrigger = driver.leftTrigger();
    private final Trigger driverRightTrigger = driver.rightTrigger();
    private final Trigger driverDpadUp = driver.povUp();
    private final Trigger driverDpadDown = driver.povDown();
    private final Trigger driverDpadLeft = driver.povLeft();
    private final Trigger driverDpadRight = driver.povRight();


  // -----===--Subsystems--===----- 
  private final Indexer s_Indexer = Indexer.getInstance();
  private final Intake s_Intake = Intake.getInstance();
  private final Shooter s_Shooter = Shooter.getInstance();

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
<<<<<<< HEAD
    driver.a().onTrue(new SetIntake(IntakeState.ON));
    driver.x().onTrue(CommandFactory.Index());
    driver.b().onTrue(CommandFactory.AllOff());

    driver.rightTrigger().onTrue(CommandFactory.Shoot());
=======
    // driver.a().onTrue(intake);
    // driver.x().onTrue(index or smth idk);
    // driver.b().onTrue();
>>>>>>> origin/Robot-Container
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
