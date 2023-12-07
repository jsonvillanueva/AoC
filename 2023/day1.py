from util import parse_file_line_by_line


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


def get_value_2(line):
    first = get_first_number_as_char(line)
    last = get_last_number_as_char(line[::-1])
    return int(first + last)


if __name__ == "__main__":
    print(sum(parse_file_line_by_line(1, 1, get_value)))
    print(sum(parse_file_line_by_line(1, 2, get_value_2)))
