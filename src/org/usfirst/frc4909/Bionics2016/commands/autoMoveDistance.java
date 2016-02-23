package org.usfirst.frc4909.Bionics2016.commands;

import org.usfirst.frc4909.Bionics2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class autoMoveDistance extends Command {
	private double distance=5;
	private double Kp = 0.06;
	
    public autoMoveDistance(double dist) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	distance=dist;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetGyro();
    	Robot.drivetrain.resetEncoders();
        SmartDashboard.putNumber("Auto Stage",3);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double angle = Robot.drivetrain.getGyroAngle(); // get current heading
        Robot.drivetrain.autoDrive(0.25, angle*Kp); // drive towards heading 0
        //Timer.delay(0.004);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.getDistTraveledLeft() > distance || Robot.drivetrain.getDistTraveledRight() > distance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//int i=0;
    	//while(i<20)
    	//{
    		Robot.drivetrain.stopMotors();
    		//i++;
    	//}
    	//SmartDashboard.putNumber("i", i);

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//Robot.drivetrain.moveRobot(-0.5, 0);
    	//int i=0;
    	//while(i<20)
    	//{
    		Robot.drivetrain.stopMotors();
    	//	i++;
    	//}
    	//SmartDashboard.putNumber("j", i);
    }
}
