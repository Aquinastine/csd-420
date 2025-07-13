from module6_assignment import HashTable
def test_hash_table_operations():
    hash_table = HashTable()
    operations_results = [
        (hash_table.put("key1", "value1"), None),  # Test 1
        (hash_table.get("key1"), "value1"),        # Test 2
        (hash_table.put("key2", 10), None),        # Test 3
        (hash_table.get("key2"), 10),              # Test 4
        (hash_table.remove("key1"), None),         # Test 5
        (hash_table.get("key1"), None)             # Test 6
    ]
    return operations_results

def test_is_anagram():
    return [
        (("anagram", "nagaram"), True),            # Test 7
        (("rat", "car"), False),                   # Test 8
        (("listen", "silent"), True),              # Test 9
        (("flow", "wolf"), True)                # Test 10
    ]
