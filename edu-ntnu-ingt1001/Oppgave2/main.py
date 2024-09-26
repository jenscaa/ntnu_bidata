from pybricks.hubs import EV3Brick
from pybricks.ev3devices import (Motor, TouchSensor, ColorSensor,
                                 InfraredSensor, UltrasonicSensor, GyroSensor)
from pybricks.parameters import Port, Stop, Direction, Button, Color
from pybricks.tools import wait, StopWatch, DataLog
from pybricks.robotics import DriveBase
from pybricks.media.ev3dev import SoundFile, ImageFile
#from ev3dev2.sound import Sound

# Initialize the EV3 Brick.
ev3 = EV3Brick()

left_motor = Motor(Port.A)
right_motor = Motor(Port.D)
obstacle_sensor = UltrasonicSensor(Port.S2)
button = TouchSensor(Port.S1)

robot = DriveBase(left_motor, right_motor, wheel_diameter=48.5, axle_track=104)


def kjørFram(milisekunder):
    robot.straight(milisekunder)


def sving(venstreHøyre):
    if venstreHøyre == 0:
        robot.turn(-90)
    else:
        robot.turn(90)


while button.pressed():
    if obstacle_sensor > 200:
        kjørFram(1000)
        sving(1)
        kjørFram(100)
        sving(1)
        kjørFram(1000)
        sving(0)
        kjørFram(100)
        sving(0)
        kjørFram(1000)
        


ev3.speaker.say("Have a nice day")