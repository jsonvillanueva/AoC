from pathlib import Path
from typing import Callable


def parse_file_line_by_line(day: int, part: int, func: Callable):
    with open(Path("inputs") / f"{day}-{part}.txt") as file:
        for line in file:
            yield func(line)
