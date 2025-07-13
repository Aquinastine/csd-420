import tests
from module5_assignment import MinHeap

def run_tests():
    total_tests = passed_tests = 0

    for result, operation_type, expected, message in tests.test_min_heap():
        total_tests += 1
        try:
            # Check if the operation type is 'insert', which should not have a result to assert
            if operation_type == "insert":
                assert result is expected  # expected should be None for 'insert' operations
            else:
                assert result == expected  # For other operations, check the result
            print(f"Test {total_tests}: Correct. ({message})")
            passed_tests += 1
        except AssertionError:
            print(f"Test {total_tests}: Incorrect. Expected: {expected}, Got: {result} ({message})")
        except Exception as e:
            print(f"Test {total_tests}: Error occurred: {e} ({message})")

    print(f"Passed {passed_tests} of {total_tests} tests.")

if __name__ == "__main__":
    run_tests()
