def test_find_max_min():
    return [
        ([1, 2, 3, 4, 5], (5, 1)), # Test 1
        ([5, 4, 3, 2, 1], (5, 1)), # Test 2
        ([-2, -5, -4], (-2, -5)),  # Test 3
        ([10], (10, 10)),          # Test 4
        ([], (None, None))         # Test 5
    ]

def test_check_symmetry():
    return [
        ("racecar", True), # Test 6
        ("python", False), # Test 7
        ("", True), # Test 8
        ("radar", True), # Test 9
        ("data", False) # Test 10
    ]

def test_merge_sorted_lists():
    return [
        ([1, 3, 5], [2, 4, 6], [1, 2, 3, 4, 5, 6]), # Test 11
        ([], [1, 2, 3], [1, 2, 3]),                 # Test 12
        ([1, 2, 3], [], [1, 2, 3]),                 # Test 13
        ([1, 1, 1], [1, 1, 1], [1, 1, 1, 1, 1, 1]), # Test 14
        ([-1, 1], [-2, 2], [-2, -1, 1, 2])          # Test 15
    ]

def test_sum_of_squares():
    return [
        ([1, 2, 3], 14),          # Test 16
        ([], 0),                  # Test 17
        ([-1, -2, -3], 14),       # Test 18
        ([4, 5], 41),             # Test 19
        ([0], 0)                  # Test 20
    ]

def test_string_reversal():
    return [
        ("hello", "olleh"),       # Test 21
        ("", ""),                 # Test 22
        ("a", "a"),               # Test 23
        ("Python", "nohtyP"),     # Test 24
        ("data", "atad")          # Test 25
    ]

def test_find_second_largest():
    return [
        ([1, 3, 4, 5], 4),        # Test 26
        ([5, 4, 3, 2, 1], 4),     # Test 27
        ([1], None),              # Test 28
        ([], None),               # Test 29
        ([2, 3], 2),              # Test 30
        ([-2, -1, -3], -2)        # Test 31
    ]
