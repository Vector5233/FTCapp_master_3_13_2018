package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by CCA on 11/3/2017.
 */
@Disabled

@Autonomous(name = "LiftAuto", group = "myGroup")
public class LiftAuto extends LinearOpMode {

    DcMotor liftMotor = null;
    Servo leftGrab, rightGrab = null;

    float Lt, Rt;
    final double RIGHTKnocker_OPEN = 1.0;
    final double RIGHTKnocker_CLOSE = 0.4;
    final double LEFTKnocker_OPEN = 0;
    final double LEFTKnocker_CLOSE = 0.6;

    @Override
    public void runOpMode() throws InterruptedException {

        rightGrab = hardwareMap.servo.get("rightGrab");
        leftGrab = hardwareMap.servo.get("leftGrab");
        rightGrab.setPosition(RIGHTKnocker_OPEN);
        leftGrab.setPosition(LEFTKnocker_OPEN);

        liftMotor.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();

        sleep(2000);
        rightGrab.setPosition(RIGHTKnocker_OPEN);
        leftGrab.setPosition(LEFTKnocker_OPEN);
        while (opModeIsActive()) {

            telemetry.update();
            idle();
        }

    }

}