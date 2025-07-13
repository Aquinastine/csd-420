from module5_assignment import MinHeap

def test_min_heap():
    min_heap = MinHeap()
    test_results = [
        (min_heap.insert(3), "insert", None, "insert 3"),  # Test 1
        (min_heap.get_min(), "get_min", 3, "get_min after inserting 3"),  # Test 2
        (min_heap.insert(2), "insert", None, "insert 2"),  # Test 3
        (min_heap.get_min(), "get_min", 2, "get_min after inserting 2"),  # Test 4
        (min_heap.insert(1), "insert", None, "insert 1"),  # Test 5
        (min_heap.get_min(), "get_min", 1, "get_min after inserting 1"),  # Test 6
        (min_heap.extract_min(), "extract_min", 1, "extract_min, should be 1"),  # Test 7
        (min_heap.get_min(), "get_min", 2, "get_min, should be 2 after extraction"),  # Test 8
        (min_heap.extract_min(), "extract_min", 2, "extract_min, should be 2"),  # Test 9
        (min_heap.get_min(), "get_min", 3, "get_min, should be 3 after extraction"),  # Test 10
        (min_heap.extract_min(), "extract_min", 3, "extract_min, should be 3"),  # Test 11
        (min_heap.extract_min(), "extract_min", None, "extract_min from empty heap"),  # Test 12
        (min_heap.get_min(), "get_min", None, "get_min from empty heap"),  # Test 13
    ]
    return test_results
