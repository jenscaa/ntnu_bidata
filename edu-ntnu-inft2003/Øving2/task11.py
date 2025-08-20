import math


def euclidean_distance(point1, point2):
    if len(point1) != len(point2):
        raise ValueError("Points does not have the same dimensions.")
    return math.sqrt(sum((point1[i] - point2[i]) ** 2 for i in range(len(point1))))


# a)
point1 = (2, 5)
point2 = (8, 4)
distance_a = euclidean_distance(point1, point2)
print(f"{point1} and {point2} distance: {distance_a}")

# b)
point1 = (2, -1, 3, 4)
point2 = (8, 15, -5, 0)
distance_b = euclidean_distance(point1, point2)
print(f"{point1} and {point2} distance: {distance_b}")

# c)
point1 = (2, -1, 3, 4)
point2 = (7, 10, 0, -5)
distance_c = euclidean_distance(point1, point2)
print(f"{point1} and {point2} distance: {distance_c}")
