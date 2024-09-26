#!/usr/bin/env pybricks-micropython
from pybricks.hubs import EV3Brick
from pybricks.ev3devices import Motor, ColorSensor, UltrasonicSensor
from pybricks.parameters import Port

# @authors: Jens Christian Aanestad, Malin H. Høli, Halvor Kirkhus, 
#           Kristiane S. Kolshus & Sander R. Skofsrud
# @version: Python 3.10.6

class GarbageTruck:
    def __init__(self):
        # Initializes the garbage truck.
        self.SPEED = 100  # Default speed - 100 degrees per second. Approximately 40 mm per second.
        self.ev3 = EV3Brick()
        self.color_sensor = ColorSensor(Port.S2)
        self.distance_sensor = UltrasonicSensor(Port.S1)
        self.arm_side = Motor(Port.A)
        self.arm = Motor(Port.B)
        self.forward_reverse = Motor(Port.C)

    def measure_distance(self):
        # Measures the distance from the trashcan and returns the distance in mm.
        return self.distance_sensor.distance()

    def reverse(self, speed, seconds):
        # Drives reverse with "speed" degrees per second /dps for "seconds" seconds.
        self.forward_reverse.run_time(speed, seconds * 1000)

    def forward(self, speed, seconds):
        # Drives forward with "speed" degrees per second /dps for "seconds" seconds.
        self.forward_reverse.run_time(-speed, seconds * 1000)

    def arm_up(self, speed, seconds):
        # Raises arm up with "speed" degrees per second /dps for "seconds" seconds.
        self.arm.run_time(-speed, seconds * 1000)

    def arm_down(self, speed, seconds):
        # Lowers arm down with "speed" degrees per second /dps for "seconds" seconds.
        self.arm.run_time(speed, seconds * 1000)

    def arm_right(self, speed, seconds):
        # Moves the arm horizontally right with "speed" degrees per second /dps for "seconds" seconds.
        self.arm_side.run_time(speed, seconds * 1000)

    def arm_left(self, speed, seconds):
        # Moves the arm horizontally left with "speed" degrees per second /dps for "seconds" seconds.
        self.arm_side.run_time(-speed, seconds * 1000)

    def is_red(self):
        # Measures the amount of red, green and blue and checks 
        # if there is more red than green. Returns a boolean.
        (red, green, blue) = self.color_sensor.rgb()
        return red > green

    def calculate_seconds_to_drive(self):
        # Calculates the amount of seconds which is needed to have 
        # just enough space to lower the "arm" right before connection.
        
        driving_distance = self.measure_distance()  # Measures the distance from the trashcan
        driving_distance -= 184.5  # Subtracting 184.5 mm from the driving distance in order to lower the arm in safe distance from the bins
        seconds = (driving_distance/self.SPEED) * 2.5  # Divides the drivng distance with default speed.
        # Since the speed is 100 degrees per second, which is around 40 mm per second, it must be multiplicated with the constant 2.5 to be to work.
        return seconds

    # Sequenses:
    def initialize_position(self):
        # Drives forward from the bin so that the robot is lined up with it. 
        # This requires that the arm is on the left side, connected with the left bin.

        self.forward(self.SPEED, 5)

    def connection(self):
        # Raises the "arm" before it reverses until it reaches a position where it can lower the "arm" and then back into
        # the bin, so it's connected

        self.arm_up(50, 2.2)
        self.reverse(self.SPEED, self.calculate_seconds_to_drive())
        self.arm_down(50, 2.2)
        self.reverse(self.SPEED, 1.2)

    def sort(self):
        # Raises the bin slightly, before it checks if it's red og green, and then moves it to the correct side,
        # empties it and puts it down in the same location. Then moves the arm over to the other side, before it does
        # exactly the same thing, and drives forwards when it's done. 

        self.arm_up(50, 1.5)  # Lifts arm 50 dps for 1500 ms
        if self.is_red(): 
            self.arm_right(self.SPEED, 5)  # Moves arm horizontally right (from trucks perspective) 100 dps for 5 seconds
            self.arm_up(50, 1.4)  # Lifts arm 50 dps for 1400 ms
            self.arm_left(self.SPEED, 5)  # Moves arm horizontally left 100 dps for 5 seconds
        else:
            self.arm_up(50, 1.4)  # Lifts arm 50 dps for 1400 ms

        self.arm_down(50, 2.8)  # Lowers arm 50 dps for 2800 ms
        self.forward(self.SPEED, 1)  # Drives forward 100 dps for 1 second

        self.arm_right(self.SPEED, 5)  # Moves arm right 100 dps for 5 seconds
        self.reverse(self.SPEED, 1)  # Reversing 100 dps for 1 second
        self.arm_up(50, 1.5)  # Lifts arm 50 dps for 1500 ms

        if not self.is_red():
            self.arm_left(self.SPEED, 5)  # Moves arm left 100 dps for 5 seconds
            self.arm_up(50, 1.4)  # Lifts arm 50 dps for 1400 ms
            self.arm_right(self.SPEED, 5)  # Moves arm right 100 dps for 5 seconds
        else:
            self.arm_up(50, 1.4)  # Lifts arm 50 dps for 1400 ms

        self.arm_down(50, 2.8)  # Lowers arm 50 dps for 2800 ms
        self.forward(self.SPEED, 5)  # Drives forward 100 dps for 5 seconds


class Sequenses:
    # Represents the garbage trucks action sequence
    def __init__(self):
        self.garbage_truck = GarbageTruck()

    def run(self):
        # Performs the garbage trucks action sequence. This requires
        # that the trucks arm starts in the left bin, and the bins stands 
        # parallell to each other with approximately distance 3.7 cm 
        
        self.garbage_truck.initialize_position()
        self.garbage_truck.reverse()
        self.garbage_truck.sort()
        
Sequenses().run()