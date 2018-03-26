package org.firstinspires.ftc.teamcode; /**
 * Created by CCA on 10/26/2017.
 */


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


public class RaymondAutonomousOpMode extends Object {
    Servo jewelKnocker, leftGrab, rightGrab, jewelRaiser;
    DcMotor frontLeft, frontRight, backLeft, backRight;
    ColorSensor colorSensor;
    Drive drive;
    LinearOpMode opmode;
    float red, green, blue;

    // Right, left, and center are facing the back of the bot
    final double JEWEL_UP = 0.94;
    final double JEWEL_DOWN = 0.38;
    final double JEWEL_RIGHT = 0.25;
    final double JEWEL_CENTER = 0.15;
    final double JEWEL_LEFT = 0.05;
    final double JEWEL_RETRY = 0.12;


    public RaymondAutonomousOpMode (Drive D, Servo JK, Servo JR, ColorSensor CS, LinearOpMode L) {
        drive = D;
        jewelKnocker = JK;
        jewelRaiser = JR;
        colorSensor = CS;
        opmode = L;
    }
    public void RedKnocker(){
        LowerJewelKnocker();
        opmode.sleep(3000);

        opmode.telemetry.addData("Red: ", colorSensor.red());
        opmode.telemetry.addData("Blue: ", colorSensor.blue());
        opmode.telemetry.update();
        if (colorSensor.red() > colorSensor.blue()) {
            jewelKnocker.setPosition(JEWEL_RIGHT);
            opmode.sleep(500);
            jewelKnocker.setPosition(JEWEL_CENTER);
            opmode.sleep(500);
            RaiseJewelKnocker();
            opmode.sleep(500);
        } else if (colorSensor.red() == 0 && colorSensor.blue() == 0){
            jewelKnocker.setPosition(JEWEL_RETRY);
            opmode.sleep(3000);
            opmode.telemetry.addData("Red: ", colorSensor.red());
            opmode.telemetry.addData("Blue: ", colorSensor.blue());
            opmode.telemetry.update();
            if (colorSensor.red() > colorSensor.blue()) {
                jewelKnocker.setPosition(JEWEL_RIGHT);
                opmode.sleep(500);
                jewelKnocker.setPosition(JEWEL_CENTER);
                opmode.sleep(500);
                RaiseJewelKnocker();
                opmode.sleep(500);
            }
            else if (colorSensor.red() == 0 && colorSensor.blue() == 0){
                RaiseJewelKnocker();
                opmode.sleep(500);
            }
            else {
                jewelKnocker.setPosition(JEWEL_LEFT);
                opmode.sleep(500);
                jewelKnocker.setPosition(JEWEL_CENTER);
                opmode.sleep(500);
                RaiseJewelKnocker();
                opmode.sleep(500);
            }
        } else {
            jewelKnocker.setPosition(JEWEL_LEFT);
            opmode.sleep(500);
            jewelKnocker.setPosition(JEWEL_CENTER);
            opmode.sleep(500);
            RaiseJewelKnocker();
            opmode.sleep(500);
        }

    }

    public void BlueKnocker() {
        LowerJewelKnocker();
        opmode.sleep(3000);
        opmode.telemetry.addData("Red: ", colorSensor.red());
        opmode.telemetry.addData("Blue: ", colorSensor.blue());
        opmode.telemetry.update();
        if (colorSensor.blue() > colorSensor.red()) {
            jewelKnocker.setPosition(JEWEL_RIGHT);
            opmode.sleep(500);
            jewelKnocker.setPosition(JEWEL_CENTER);
            opmode.sleep(500);
            RaiseJewelKnocker();
            opmode.sleep(500);
        } else if (colorSensor.red() == 0 && colorSensor.blue() == 0){
            jewelKnocker.setPosition(JEWEL_RETRY);
            opmode.sleep(3000);
            opmode.telemetry.addData("Red: ", colorSensor.red());
            opmode.telemetry.addData("Blue: ", colorSensor.blue());
            opmode.telemetry.update();
            if (colorSensor.blue() > colorSensor.red()) {
                jewelKnocker.setPosition(JEWEL_RIGHT);
                opmode.sleep(500);
                jewelKnocker.setPosition(JEWEL_CENTER);
                opmode.sleep(500);
                RaiseJewelKnocker();
                opmode.sleep(500);
            }
            else if (colorSensor.red() == 0 && colorSensor.blue() == 0) {
                RaiseJewelKnocker();
                opmode.sleep(500);
            }
            else {
                jewelKnocker.setPosition(JEWEL_LEFT);
                opmode.sleep(500);
                jewelKnocker.setPosition(JEWEL_CENTER);
                opmode.sleep(500);
                RaiseJewelKnocker();
                opmode.sleep(500);

            }
        }
        else {
            jewelKnocker.setPosition(JEWEL_LEFT);
            opmode.sleep(500);
            jewelKnocker.setPosition(JEWEL_CENTER);
            opmode.sleep(500);
            RaiseJewelKnocker();
            opmode.sleep(500);
        }
    }



    public void LowerJewelKnocker(){
        jewelRaiser.setPosition(JEWEL_DOWN);
    }

    public void RaiseJewelKnocker(){
        jewelRaiser.setPosition(JEWEL_UP);
    }
}




