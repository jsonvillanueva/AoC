import math
import pathlib
import re
from collections import Counter
from collections.abc import Iterator

type AoCData = Iterator[tuple[int, Counter[str]]]


def parse_data(path: pathlib.Path) -> AoCData:
    for line in path.read_text(encoding="utf-8").splitlines():
        if captures := re.match(r"Game (\d+): (.+)", line):
            id_, game = captures.groups()
            colours: Counter[str] = Counter()
            for amount, colour in re.findall(r"(\d+) (\w+)", game):
                colours[colour] = max(colours[colour], int(amount))
            yield int(id_), colours


def part_one(data: AoCData) -> int:
    max_colours = Counter(red=12, green=13, blue=14)
    return sum(id_ for id_, colours in data if colours <= max_colours)


def part_two(data: AoCData) -> int:
    return sum(math.prod(colours.values()) for id_, colours in data)

data = parse_data(pathlib.Path("inputs") / f"{pathlib.Path(__file__).stem}.txt")
print(data)
print(part_one(data))
print(part_two(data))