package org.usfirst.frc4909.Bionics2016.commands;

import org.usfirst.frc4909.Bionics2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class CrossDefence extends Command {
	private double Kp = 0.03;
	private double startTime; //SET TO REAL TIME!!!!!
	
	public CrossDefence() {
		// TODO Auto-generated constructor stub
        requires(Robot.drivetrain);
	}


	@Override
	protected void initialize() {
		startTime=Timer.getFPGATimestamp();
		// TODO Auto-generated method stub
		Robot.drivetrain.resetGyro();
        double angle; // get current heading

        while (Timer.getFPGATimestamp()-startTime<2) {
            angle = Robot.drivetrain.getGyroAngle(); // get current heading
            Robot.drivetrain.autoDrive(-.5, -angle*Kp); // drive towards heading 0
            Timer.delay(0.004);
        }
        while(Math.abs(Robot.drivetrain.getGyroAngle())>5){
        	angle = Robot.drivetrain.getGyroAngle();
            Robot.drivetrain.autoDrive(0, -angle*Kp); // drive towards heading 0
        }
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void end() {
        Robot.drivetrain.autoDrive(0, 0); // drive towards heading 0

		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
