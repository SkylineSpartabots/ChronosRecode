package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Indexer.IndexerStates;

public class SetIndexer extends Command {
    private final Indexer s_Indexer;
    
    private Indexer.IndexerStates state;

    public SetIndexer(Indexer.IndexerStates state) {
        s_Indexer = Indexer.getInstance();
        
        this.state = state;
        addRequirements(s_Indexer);
    }

    @Override
    public void initialize() {
        s_Indexer.setState(state);
    }

    @Override
    public void end(boolean interrupted) {
        s_Indexer.setState(IndexerStates.OFF);
    }
    
    @Override
    public boolean isFinished() {
        return false;
    }
}
