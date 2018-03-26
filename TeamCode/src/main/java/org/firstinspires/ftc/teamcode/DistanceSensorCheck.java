package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by CCA on 2/6/2018.
 */

@Autonomous(name="DistanceSensorCheck")

public class DistanceSensorCheck extends LinearOpMode {
    ModernRoboticsI2cRangeSensor rangeRight, rangeLeft;

    public void initialization() {
        rangeRight = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "rangeRight");
        rangeLeft = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "rangeLeft");
    }

    public void runOpMode() {
        initialization();
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Right Distance", "%.2f in", rangeRight.getDistance(DistanceUnit.INCH));
            telemetry.addData("Left Distance", "%.2f in", rangeLeft.getDistance(DistanceUnit.INCH));
            telemetry.update();
        }
    }
}
