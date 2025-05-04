import json
import random
import requests
from math import sqrt

ids = ['214986523', '214683914']


def plus_minus(lst):
    """
    Returns the sum of a list of numbers, with alternating signs.
    First element is with +
    lst: A list of numbers
    """
    sum1 = 0
    for i in range(len(lst)):
        sum1 += ((-1) ** i) * lst[i]
    return sum1



    # TODOD: Implement this function


def median(lst):
    """
    Returns the median of a list of numbers.
    lst: A list of numbers
    """
    lst_sort = sorted(lst)
    middle = len(lst_sort) // 2

    if len(lst_sort) % 2 == 0:
        return (lst_sort[middle] + lst_sort[middle - 1]) / 2
    else:
        return lst_sort[middle]

    # TODOD: Implement this function


def std(lst):
    """
    Returns the sample standard deviation of a list of numbers.
    lst: A list of numbers
    """
    if len(lst)<2:
        return 0
    total = 0
    average = sum(lst) / len(lst)

    for i in range(len(lst)):
        total +=   ((lst[i] - average) ** 2)

    return (total / (len(lst) - 1)) ** 0.5
    # TODOD: Implement this function
    

def apply_func_shuffle(lst, f1, f2):
    """
    Applies f1 and f2 on a shuffled version of lst (not on lst itself)
    lst: A list of numbers
    Returns two values: the result from f1, and the result from f2    
    """
    shuffled_lst = list(lst)  # do not change
    random.shuffle(shuffled_lst)  # do not change
    return f1(shuffled_lst), f2(shuffled_lst)

    # TODO: Implement the rest of this function
    

if __name__ == '__main__':
    # DO NOT CHANGE THE FOLLOWING CODE
    random.seed(sum([int(i) for i in ids]))
    lst = [random.randint(0, 100) for _ in range(100)]
    
    res = {"median": round(median(lst), 3),
           "std": round(std(lst), 3),
           "plus_minus": round(plus_minus(lst), 3),
           "req_ver": requests.__version__,
           }
    med_shuf, plus_minus_shuf = apply_func_shuffle(lst, median, plus_minus)
    res.update({"median_shuffle": round(med_shuf, 3),
                "plus_minus_shuffle": round(plus_minus_shuf, 3)})
    with open("output.json", "w") as f:
        json.dump(res, f)
        
