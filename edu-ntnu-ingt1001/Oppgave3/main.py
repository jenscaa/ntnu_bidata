#!/usr/bin/env pybricks-micropython
from pybricks.hubs import EV3Brick
from pybricks.ev3devices import Motor, ColorSensor, TouchSensor, UltrasonicSensor
from pybricks.parameters import Port
from pybricks.tools import wait, StopWatch
from pybricks.parameters import Color
from pybricks.media.ev3dev import SoundFile
from pybricks.robotics import DriveBase
import random

ev3 = EV3Brick()
left_motor = Motor(Port.A)
right_motor = Motor(Port.D)
obstacle_sensor = UltrasonicSensor(Port.S3)
button = TouchSensor(Port.S2)
line_sensor1 = ColorSensor(Port.S1)
line_sensor2 = ColorSensor(Port.S4)

robot = DriveBase(left_motor, right_motor, wheel_diameter=55.5, axle_track=104)

BLACK = 8
WHITE = 30
threshold = (BLACK + WHITE) / 2


def piratesOfTheCaribbean():
    ev3.speaker.play_notes(['D4/4', 'D4/8', 'D4/4', 'D4/8', 'D4/4', 'A3/8', 'C4/8', 'D4/4', 'D4/4', 'D4/8', 
                            'E4/8', 'F4/4', 'F4/4', 'F4/8', 'G4/8', 'E4/8', 'E4/4', 'D4/8', 'C4/8', 'C4/8', 'D4/2'])

def despacito():
    ev3.speaker.play_notes(['C5/4', 'B4/4', 'A4/2', 'E4/16', 'A4/16', 'B4/16', 'C5/8', 'D5/8', 'C5/16', 
                            'B4/8', 'A4/8', 'G4/8', 'F4/4' , 'C5/4' , 'C5/2' , 'C5/8', 'G4/8', 'C5/8' ,
                            'G4/8', 'C5/8', 'G4/8', 'C5/8' , 'D5/16', 'B4/2'])

def gameOfThronesEntry():
    ev3.speaker.play_notes(['E5/4', 'A4/4' , 'C5/16', 'D5/16', 'E5/4' , 'A4/4' , 'C5/16', 'D5/16', 'B4/8' , 'E4/8',
                           'G4/16', 'A4/16', 'B4/8' , 'E4/8' , 'G4/16', 'A4/16', 'B4/8' , 'E4/8' , 'G4/16', 'A4/16'
                           'B4/8' , 'E4/8' , 'G4/8' , 'D5/4' , 'G4/4' , 'B4/4' , 'C5/4' , 'B4/4' , 'G4/4' , 'A4/2'])

def haalandHaaland():
    ev3.speaker.play_notes(['F4/6' , 'C5/6' , 'F4/6'  , 'C5/6', 'F5/8' , 'F5/16', 'D#5/8', 'C#5/8', 'D#5/8', 'C5/8' , 
                            'G#4/8', 'C#5/8', 'C#5/16', 'C5/8', 'A#4/8', 'C5/8' , 'G#4/8', 'F4/4' , 'A#4/8', 'G#4/8',
                            'G4/8' , 'F4/8' , 'E4/4'])

def animeWow():
    ev3.speaker.play_file("PewDiePie.wav")


def underholdning(tilfeldigTall):
    if tilfeldigTall == 1:
        piratesOfTheCaribbean()
    elif tilfeldigTall == 2:
        despacito()
    elif tilfeldigTall == 3:
        gameOfThronesEntry()
    elif tilfeldigTall == 4:
        haalandHaaland()
    else:
        animeWow()

def fram():
    while line_sensor1.reflection() < threshold and line_sensor2.reflection() > threshold:
        robot.drive(150, 0)

def venstre():
    while line_sensor1.reflection() > threshold and line_sensor2.reflection() < threshold:
        robot.drive(100, -50)

def høyre():
    while line_sensor1.reflection() > threshold and line_sensor2.reflection() > threshold:
        robot.drive(100, 50)



sekunder = 1
while not button.pressed():
    ev3.screen.print("sek: " + str(sekunder) + " V: " + str(line_sensor2.reflection()) + " H:" + str(line_sensor1.reflection()))
    sekunder += 1
    wait(1000)

time = StopWatch()
run = True

while run:

    fram()
    venstre()
    høyre()


    if time.time() > 10000: # 10000 millisekunder == 10 sekunder 
        robot.stop()
        ev3.light.on(Color.YELLOW)
        tilfeldigTall = random.randint(1, 5)
        underholdning(tilfeldigTall)
        ev3.light.off()
        time.reset()

    if obstacle_sensor.distance() < 100:
        robot.stop()
        ev3.speaker.play_file(SoundFile.CHEERING)
        break