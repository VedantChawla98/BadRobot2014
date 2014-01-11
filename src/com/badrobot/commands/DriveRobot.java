/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.OI;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Isaac
 */
public class DriveRobot extends BadCommand
{
    private double BUMPER_SPEED = .5;
    
    public DriveRobot()
    {
        requires((Subsystem) driveTrain);
    }
    
    protected void initialize() 
    {
        driveTrain.tankDrive(0, 0);
    }

    public String getConsoleIdentity() 
    {
        return "DriveRobot";
    }

    protected void execute() 
    {
        driveTrain.tankDrive(OI.primaryController.getLeftStickY(), OI.secondaryController.getRightStickY());
        
        if (OI.primaryController.getRightTrigger() > 0)
        {
            log("shift right");
            driveTrain.shift(true);
        }  
        else if (OI.primaryController.getLeftTrigger() > 0)
        {
            log("shift left");
            driveTrain.shift(false);
        }
        
        if (driveTrain.getCompressorLimit())
        {
            driveTrain.compressorEnabled(true);
        }
        else
        {
            driveTrain.compressorEnabled(false);
        }
    }

    protected boolean isFinished() 
    {
        return false;
    }

    protected void end() 
    {
        driveTrain.tankDrive(0, 0);
    }

    protected void interrupted() 
    {
        log("I've been interrupted and am deffering to the new Command");
    }
}