# ----------------------------------------------------------
# Filename: tester.py
# Authors: Nasheb Ismaily [v1], Rob Flowers [v2]
# ----------------------------------------------------------

import tests
from module1_assignment import *

def run_tests(test_cases, function_to_test):
    total_tests = sum(len(test_cases[func]) for func in test_cases)
    test_counter = 0
    passed_tests = 0

    for func in test_cases:
        for case in test_cases[func]:
            test_counter += 1
            args, expected = case[:-1], case[-1]
            try:
                if isinstance(args, tuple):
                    got = function_to_test[func](*args)
                else:
                    got = function_to_test[func](args)

                if got == expected:
                    passed_tests += 1
                    print(f"Test {test_counter}/{total_tests}: Correct.")
                else:
                    print(f"Test {test_counter}/{total_tests}: Incorrect. Expected: {expected}, Got: {got}")

            except Exception as e:
                print(f"Test {test_counter}/{total_tests}: Error occurred: {e}")

    print(f"Passed {passed_tests} of {total_tests} tests.")

if __name__ == "__main__":
    test_cases = {
        "find_max_min": tests.test_find_max_min(),
        "check_symmetry": tests.test_check_symmetry(),
        "merge_sorted_lists": tests.test_merge_sorted_lists(),
		"sum_of_squares": tests.test_sum_of_squares(),
        "string_reversal": tests.test_string_reversal(),
        "find_second_largest": tests.test_find_second_largest()
    }

    functions_to_test = {
        "find_max_min": find_max_min,
        "check_symmetry": check_symmetry,
        "merge_sorted_lists": merge_sorted_lists,
		"sum_of_squares": sum_of_squares,
        "string_reversal": string_reversal,
        "find_second_largest": find_second_largest
    }

    run_tests(test_cases, functions_to_test)
