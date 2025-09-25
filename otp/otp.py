from quantiphy import Quantity
import argparse
import pathlib

class NotEnoughKey(Exception):
    pass

def xor_bytes(a, b):
    if not len(a) == len(b): raise NotEnoughKey()
    return bytes(x ^ y for x, y in zip(a, b))

def encdec(input_file, output_file, safe=False):
    with open('key', 'rb+') as key_f:
        with open(input_file, 'rb') as pln_f:
            with open(output_file, 'wb') as cph_f:
                key = key_f.read()
                pln_bytes = pln_f.read()
                bytes_len = len(pln_bytes)
                key_bytes = key[:bytes_len]
                cph_f.write(xor_bytes(pln_bytes, key_bytes))
                if safe:
                    key_f.seek(0)
                    key_f.truncate()
                    key_f.write(key[bytes_len:])
                    print_data(bytes_len, len(key), len(key) - bytes_len)
                else:
                    print_data(bytes_len, len(key), len(key))

def print_data(used, total, left):
    used = Quantity(used)
    total = Quantity(total)
    left = Quantity(left)
    print(f"{used.binary()}B used from {total.binary()}B" + (f" with {left.binary()}B left" if total != left else ""))


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='OTP Encryption')
    parser.add_argument('key', help='key file', metavar='key', type=pathlib.Path)
    parser.add_argument('input', help='input file', metavar='input', type=pathlib.Path)
    parser.add_argument('output', help='output file', metavar='output', type=pathlib.Path)
    parser.add_argument('-s', '--secure', help='secure mode', action=argparse.BooleanOptionalAction)
    args = parser.parse_args()
    try:
        encdec(args.input, args.output, args.secure)
    except NotEnoughKey:
        print("Key is too short")
