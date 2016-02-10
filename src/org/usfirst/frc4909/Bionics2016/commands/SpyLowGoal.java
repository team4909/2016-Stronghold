package org.usfirst.frc4909.Bionics2016.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SpyLowGoal extends CommandGroup {
    
    public  SpyLowGoal() {
        // Add Commands here:
    	
    	addParallel(new autoSpyToTower()); //make it have right time
    	addParallel(new autoSetShooterAngle(85)); //MAKE CORRECT ANGLE!!!
    	addParallel(new StartShooter(1000)); //Check RPM
    	addSequential(new Shoot());
    	
    	//Possibility of using the line up methods (ultrasonics and vision) in auto... 
    	//for now assuming that we are not as those are not yet written
    	
    	
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
