// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4909.Bionics2016;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc4909.Bionics2016.commands.*;
import org.usfirst.frc4909.Bionics2016.subsystems.*;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    SendableChooser autoChooser;
    
    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Drivetrain drivetrain;
    public static Shooter shooter;
    public static Feeder feeder;
    public static Pivot pivot;
    public static Climber climber;
    int session;
    Image frame;
    NIVision.Rect rect;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        drivetrain = new Drivetrain();
        shooter = new Shooter();
        feeder = new Feeder();
        pivot = new Pivot("Pivot", .1, 0.002, 0);
        climber = new Climber();

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        autoChooser = new SendableChooser();
        autoChooser.addDefault("Spy Low Goal", new SpyLowGoal());
        autoChooser.addObject("Spy High Goal", new SpyHighGoal());
        autoChooser.addObject("Defence Cross", new CrossDefence());
        //autoChooser.addObject("Experimental: Cross Defence, Score Low Goal", new CrossLowGoal());
        //autoChooser.addObject("Experimental: Cross Defence,, Score High Goal", new ());
        //autoChooser.addObject("Experimental!!: Start as Spy, Score High Goal, Cross a Defence", new ());
        
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        
        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	autonomousCommand = (Command)autoChooser.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null)
        	autonomousCommand.cancel();
        NIVision.IMAQdxStartAcquisition(session);
        rect = new NIVision.Rect(10, 10, 100, 100);

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("encoderVal", Robot.pivot.returnPIDInput());
        NIVision.IMAQdxGrab(session, frame, 1);
        NIVision.imaqDrawShapeOnImage(frame, frame, rect,
                DrawMode.PAINT_VALUE, ShapeMode.SHAPE_RECT, 0.0f);
        
        CameraServer.getInstance().setImage(frame);

        /** robot code here! **/
        //Timer.delay(0.005);		// wait for a motor update time
        
        NIVision.IMAQdxStopAcquisition(session);    
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
