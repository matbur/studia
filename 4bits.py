#!/usr/bin/python

import random
import sys


def random_sequence(n=4):
    return ''.join([random.choice('01') for _ in xrange(n)])


def u2(s):
    r = -int(s[0]) * 2 ** len(s) / 2
    return r + int(s[1:], 2)


def prepare_dict(s):
    return dict(
        bin=s,
        dec=int(s, 2),
        u2=u2(s),
        hex=hex(int(s, 2))[2:]
    )


def guess_numbers(d):
    print ' ' * 5, d.pop('bin')
    for k, v in d.items():
        a = raw_input('{:>4}: '.format(k))
        if a != str(v):
            print ' ' * 5, v
    print


def main():
    try:
        n = int(sys.argv[1])
    except (IndexError, ValueError):
        n = 4
    try:
        while True:
            s = random_sequence(n)
            d = prepare_dict(s)
            guess_numbers(d)
    except (KeyboardInterrupt, EOFError):
        print '\nI hope you learned a lot :)'


if __name__ == '__main__':
    main()
