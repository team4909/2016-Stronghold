package org.usfirst.frc4909.Bionics2016.commands;

import org.usfirst.frc4909.Bionics2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class autoSpyToTower extends Command {
	private double time=0; //SET TO REAL TIME!!!!!
	private double Kp = 0.03;
	
    public autoSpyToTower() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

	// Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetGyro();
        while (Timer.getMatchTime()<time) {
            double angle = Robot.drivetrain.getGyroAngle(); // get current heading
            Robot.drivetrain.autoDrive(1.0, angle*Kp); // drive towards heading 0
            Timer.delay(0.004);
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
