package org.usfirst.frc4909.Bionics2016.commands;

import org.usfirst.frc4909.Bionics2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class autoCrossDefenceAccel extends Command {
	private double startTime = Timer.getFPGATimestamp();

    public autoCrossDefenceAccel() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	while(Robot.drivetrain.accelFlat())
    	{
    		new autoMoveDistance(1);
    	}
    	    	
    	while(!Robot.drivetrain.accelFlat())
    	{
    		new autoMoveDistance(1);
    	}
    	
    	while(Timer.getFPGATimestamp()-startTime<5)
    	{
    		//if(Robot.drivetrain.accelFlat)
    	}
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
