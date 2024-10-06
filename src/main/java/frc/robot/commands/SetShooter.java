package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class SetShooter extends Command {
    private final Shooter s_Shooter;

    private double velocity;

    public SetShooter(double velocity) {
        s_Shooter = Shooter.getInstance();

        this.velocity = velocity;
        addRequirements(s_Shooter);
    }

    @Override
    public void initialize() {
        s_Shooter.setVelocity(velocity);
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return Math.abs(s_Shooter.getVelocity() - velocity) < 5;
    }
}
