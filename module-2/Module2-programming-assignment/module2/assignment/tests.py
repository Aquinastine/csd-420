# Helper function to create a linked list from a list of values
def create_linked_list(values):
    if not values:
        return None
    head = ListNode(values[0])
    current = head
    for value in values[1:]:
        current.next = ListNode(value)
        current = current.next
    return head

def test_array_sum():
    return [
        ([1, 2, 3, 4, 5], 15),         # Test 1
        ([], 0),                        # Test 2
        ([-1, -2, -3], -6),             # Test 3
        ([10, 20, 30], 60)              # Test 4
    ]

def test_find_middle_node():
    return [
        ([1, 2, 3, 4, 5], 3),           # Test 5
        ([1, 2, 3, 4], 3),              # Test 6
        ([1], 1),                       # Test 7
        ([], None),                    # Test 8
        ([1, 2, 3, 4, 5, 6, 7], 4)      # Test 9
    ]

def test_remove_duplicates_from_sorted_array():
    return [
        ([1, 1, 2], 2),                 # Test 10
        ([1, 2, 2, 3, 3, 4], 4),        # Test 11
        ([], 0),                        # Test 12
        ([1, 1, 1, 1, 1], 1),           # Test 13
        ([1, 2, 3, 4, 5], 5)            # Test 14
    ]
