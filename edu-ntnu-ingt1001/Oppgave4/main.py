#!/usr/bin/env pybricks-micropython
from pybricks.hubs import EV3Brick
from pybricks.ev3devices import Motor, ColorSensor, TouchSensor
from pybricks.parameters import Port
from pybricks.tools import wait, StopWatch
from pybricks.parameters import Color
from pybricks.media.ev3dev import SoundFile
from pybricks.robotics import DriveBase
import random

ev3 = EV3Brick()
left_motor = Motor(Port.A)
right_motor = Motor(Port.D)

button = TouchSensor(Port.S2)
line_sensor_høyre = ColorSensor(Port.S1)
line_sensor_venstre = ColorSensor(Port.S4)
robot = DriveBase(left_motor, right_motor, wheel_diameter=55.5, axle_track=104)

def kjør():
    rotasjon = (line_sensor_venstre.reflection() - line_sensor_høyre.reflection()) * 1.45
     #1.8 med 290 funket 10/10 med avstand 1 cm
    fart = 280
    #while (line_sensor_venstre.reflection() < 10 or line_sensor_høyre.reflection() < 10):
    #    robot.drive(fart - 20, rotasjon)
    robot.drive(fart, rotasjon)

    #Minimal avstand med 1.4 ratio med fart på 300 mm/s funker! ikke lenger

    #Minimal avstand med 1.5 ratio med fart på 270 mm/s funker nice cnice

    #Minimal avstand med 1.44 ratio med fart på 280 mm/s funker krem

    #Minimal avstand med 1.37 ratio med fart 290 funket i racet, men ikke konsistent!




def nyKjør():
    rotasjon = (line_sensor_venstre.reflection() - line_sensor_høyre.reflection()) * 1.37 #1.8 med 290 funket 
    fart = 350 - abs(line_sensor_venstre.reflection() - line_sensor_høyre.reflection())
    robot.drive(fart, rotasjon)



    


sekunder = 1
while not button.pressed():
    ev3.screen.print("sek: " + str(sekunder) + " V: " + str(line_sensor_venstre.reflection()) + " H:" + str(line_sensor_høyre.reflection()))
    sekunder += 1
    wait(1000)


run = True
while run:
    robot.drive(280, (line_sensor_venstre.reflection() - line_sensor_høyre.reflection()) * 1.45)