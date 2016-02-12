package org.usfirst.frc4909.Bionics2016.commands;

import org.usfirst.frc4909.Bionics2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class autoTowerFromDefence extends Command {

	private double distanceToTower=105; //in inches
	
    public autoTowerFromDefence() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.autoDrive(0.5, -0.025);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.getDistTraveledLeft() >= distanceToTower || Robot.drivetrain.getDistTraveledRight() >= distanceToTower;
    }

    // Called once after isFinished returns true
    protected void end(){
    	Robot.drivetrain.moveRobot(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
