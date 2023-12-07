from pathlib import Path

str_to_int_map = {
    "one": "1",
    "two": "2",
    "three": "3",
    "four": "4",
    "five": "5",
    "six": "6",
    "seven": "7",
    "eight": "8",
    "nine": "9",
}


def get_first_number_as_char(chars):
    number = None
    for i, char in enumerate(chars):
        try:
            number = int(char)
            number = char
            break
        except:
            pass
        for word_len in range(3, 6):
            if chars[i : i + word_len] in str_to_int_map:
                number = str_to_int_map[chars[i : i + word_len]]
                break
        if number:
            break
    return number


def get_last_number_as_char(chars):
    number = None
    for i, char in enumerate(chars):
        try:
            number = int(char)
            number = char
            break
        except:
            pass
        for word_len in range(3, 6):
            if i + word_len >= len(chars):
                continue
            if "".join(reversed(chars[i : i + word_len])) in str_to_int_map:
                number = str_to_int_map["".join(reversed(chars[i : i + word_len]))]
                break
        if number:
            break
    return number


def get_value(line):
    first = get_first_number_as_char(line)
    last = get_last_number_as_char(line[::-1])
    return int(first + last)


with open(Path("inputs") / "1-2.txt") as lines:
    numbers = []
    for line in lines:
        numbers.append(get_value(line))
print(sum(numbers))
