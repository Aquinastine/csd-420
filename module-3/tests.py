def test_validate_brackets():
    return [
        ("()", True),                # Test 1
        ("()[]{}", True),            # Test 2
        ("(]", False),               # Test 3
        ("([)]", False),             # Test 4
        ("{[]}", True),              # Test 5
    ]

def test_next_greater_element():
    return [
        ([4, 5, 2, 25], [5, 25, 25, -1]),   # Test 6
        ([13, 7, 6, 12], [-1, 12, 12, -1]),  # Test 7
        ([], []),                            # Test 8
        ([3, 2, 1], [-1, -1, -1]),           # Test 9
        ([2, 7, 3, 5, 4], [7, -1, 5, -1, -1]) # Test 10
    ]

def test_reverse_stack():
    return [
        ([1, 2, 3, 4], [4, 3, 2, 1]),        # Test 11
        ([], []),                             # Test 12 - Empty stack
        ([5], [5]),                           # Test 13 - Single element
        ([3, 1, 4, 2], [2, 4, 1, 3]),         # Test 14
        ([-1, -2, -3], [-3, -2, -1])          # Test 15 - Negative numbers
    ]
