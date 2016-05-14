package org.usfirst.frc4909.Bionics2016.commands;

import org.usfirst.frc4909.Bionics2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SpyHighGoal extends CommandGroup {
   
    public  SpyHighGoal() {
    	
        requires(Robot.drivetrain);
        requires(Robot.pivot);
        requires(Robot.shooter);
        requires(Robot.feeder);
        
        // Add Commands here:
    	addSequential(new autoMoveDistance(12)); //MAKE IT WORK!!!
    	//addParallel(new autoPivotTime(.05)); //MAKE CORRECT ANGLE!!!!
    	addParallel(new autoTurnRobot(3));
    	addParallel(new autoSetShooterAngle(42.6));
    	addSequential(new StartShooter(4000)); //Check RPM
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
