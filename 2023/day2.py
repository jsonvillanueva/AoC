import math
import re
from collections import Counter

from util import parse_file_line_by_line


def parse_line(line: str):
    if captures := re.match(r"Game (\d+): (.+)", line):
        id_, game = captures.groups()
        colors: Counter[str] = Counter()
        for amount, color in re.findall(r"(\d+) (\w+)", game):
            colors[color] = max(colors[color], int(amount))
        return int(id_), colors

def part_one(line: str) -> int:
    max_colors = Counter(red=12, green=13, blue=14)
    id_, colors = parse_line(line)
    return id_ if colors <= max_colors else 0


def part_two(line: str) -> int:
    _, colors = parse_line(line)
    return math.prod(colors.values())

if __name__ == "__main__":
    print(sum(parse_file_line_by_line(2, 1, part_one)))
    print(sum(parse_file_line_by_line(2, 2, part_two)))