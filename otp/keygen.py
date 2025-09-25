import os
import enum
import argparse
import pathlib

class size(enum.Enum):
    kb = 2**10  # 4096
    mb = 2**20  # 8388608
    gb = 2**30  # 2147483648
    tb = 2**40  # 549755813888

def gen(path,size):
    with open(path, 'wb') as f:
        rnd = os.urandom(size)
        f.write(rnd)

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Generate a key file')
    parser.add_argument('size', type=int, help='size of key file')
    parser.add_argument('unit', type=str.lower, choices=['kb', 'mb', 'gb', 'tb'], help='unit of size')
    parser.add_argument('path', type=pathlib.Path, help='key file')
    parser.add_argument('--discord', action='store_true', help='workaround for discord')
    args = parser.parse_args()
    size = size[args.unit].value * args.size
    if args.discord:
        size = size - 2**9
    gen(args.path, size)
    print(f'Generated {args.path} with size {args.size} {args.unit}')
