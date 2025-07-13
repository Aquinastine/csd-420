import tests
from module6_assignment import HashTable, is_anagram

def run_tests():
    total_tests = passed_tests = 0

    for operation in tests.test_hash_table_operations():
        total_tests += 1
        result, expected = operation
        try:
            assert result == expected
            print(f"Test {total_tests}: Correct.")
            passed_tests += 1
        except AssertionError:
            print(f"Test {total_tests}: Incorrect. Expected: {expected}, Got: {result}")

    for args, expected in tests.test_is_anagram():
        total_tests += 1
        result = is_anagram(*args)
        try:
            assert result == expected
            print(f"Test {total_tests}: Correct.")
            passed_tests += 1
        except AssertionError:
            print(f"Test {total_tests}: Incorrect. Expected: {expected}, Got: {result}")

    print(f"Passed {passed_tests} of {total_tests} tests.")

if __name__ == "__main__":
    run_tests()
