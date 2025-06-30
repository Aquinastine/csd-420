import tests
from module4_assignment import *

def run_tests(test_cases, function_to_test):
    total_tests = sum(len(test_cases[func]) for func in test_cases)
    test_counter = 0
    passed_tests = 0

    for func in test_cases:
        for case in test_cases[func]:
            test_counter += 1
            args, expected = case[:-1], case[-1]

            try:
                if func == "insert_into_bst":
                    root = None
                    for value in args[0]:
                        root = insert_into_bst(root, value)
                    got = inorder_traversal(root)
                else:
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
        "insert_into_bst": tests.test_insert_into_bst(),
        "is_valid_bst": tests.test_is_valid_bst(),
        "inorder_traversal": tests.test_inorder_traversal()
    }

    functions_to_test = {
        "insert_into_bst": insert_into_bst,  # Note: this function is tested indirectly through inorder_traversal
        "is_valid_bst": is_valid_bst,
        "inorder_traversal": inorder_traversal
    }

    run_tests(test_cases, functions_to_test)
