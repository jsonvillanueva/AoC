"""
The newly-improved calibration document consists of lines of text; each line originally contained a specific calibration value that the Elves now need to recover. On each line, the calibration value can be found by combining the first digit and the last digit (in that order) to form a single two-digit number.

For example:

1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet

In this example, the calibration values of these four lines are 12, 38, 15, and 77. Adding these together produces 142.

Consider your entire calibration document. What is the sum of all of the calibration values?
"""
from pathlib import Path


def caluclate(numbers):
    return sum(numbers)


def get_value(line):
    first = None
    last = None
    for char in line:
        try:
            first = int(char)
            first = char
            break
        except:
            continue
    for char in line[::-1]:
        try:
            last = int(char)
            last = char
            break
        except:
            continue
    return int(first + last)


with open(Path("inputs") / "1-1.txt") as lines:
    numbers = []
    for line in lines:
        numbers.append(get_value(line))

print(caluclate(numbers))
