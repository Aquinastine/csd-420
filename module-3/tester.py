# ----------------------------------------------------------
# Filename: tester_stacks_queues.py
# Author: Nasheb Ismaily
# ----------------------------------------------------------

import  tests
from module3_assignment import *

def run_tests(test_cases, function_to_test):
    total_tests = sum(len(test_cases[func]) for func in test_cases)
    test_counter = 0
    passed_tests = 0

    for func in test_cases:
        for case in test_cases[func]:
            test_counter += 1
            args, expected = case[:-1], case[-1]

            try:
                got = function_to_test[func](*args)

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
        "validate_brackets": tests.test_validate_brackets(),
        "next_greater_element": tests.test_next_greater_element(),
        "reverse_stack": tests.test_reverse_stack()
    }

    functions_to_test = {
        "validate_brackets": validate_brackets,
        "next_greater_element": next_greater_element,
        "reverse_stack": reverse_stack
    }

    run_tests(test_cases, functions_to_test)
