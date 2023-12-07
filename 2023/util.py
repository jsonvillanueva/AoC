from pathlib import Path
from typing import Callable


def parse_file_line_by_line(day: int, func: Callable):
    with open(Path("inputs") / f"{day}.txt") as file:
        for line in file:
            yield func(line)
