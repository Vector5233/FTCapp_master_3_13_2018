package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by CCA on 1/23/2018.
 */
@Autonomous(name="CheckGyro")
public class CheckGyro extends LinearOpMode {
ModernRoboticsI2cGyro gyro;

    public void initialization () {
        gyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");
        gyro.calibrate();

        while (gyro.isCalibrating() && opModeIsActive()) {
            idle();
        }
    }

    public void runOpMode(){
            double g;
            initialization();
            waitForStart();
            while (opModeIsActive()) {
                g = gyro.getHeading();
                telemetry.addData("Gyro", g);
                telemetry.update();
            }
    }

}
