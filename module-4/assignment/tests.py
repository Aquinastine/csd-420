from module4_assignment import TreeNode
def test_insert_into_bst():
    return [
        ([3, 2, 4, 1], [1, 2, 3, 4]),   # Test 1
        ([5], [5]),                     # Test 2
        ([], []),                       # Test 3
        ([4, 2, 6, 1, 3, 5, 7], [1, 2, 3, 4, 5, 6, 7]), # Test 4
        ([8, 3, 10, 1, 6, 9, 14], [1, 3, 6, 8, 9, 10, 14]), # Test 5
    ]

def test_is_valid_bst():
    return [
        (TreeNode(2, TreeNode(1), TreeNode(3)), True),  # Test 6
        (TreeNode(1, TreeNode(2), TreeNode(3)), False), # Test 7
        (None, True),                                   # Test 8
        (TreeNode(5, TreeNode(3, TreeNode(2), TreeNode(4)), TreeNode(7, TreeNode(6), TreeNode(8))), True),  # Test 9
        (TreeNode(5, TreeNode(3), TreeNode(4)), False), # Test 10
    ]

def test_inorder_traversal():
    return [
        (TreeNode(1, None, TreeNode(2, TreeNode(3))), [1, 3, 2]), # Test 11
        (TreeNode(1, TreeNode(2)), [2, 1]),                       # Test 12
        (None, []),                                               # Test 13
        (TreeNode(5, TreeNode(3, TreeNode(2), TreeNode(4)), TreeNode(7, TreeNode(6), TreeNode(8))), [2, 3, 4, 5, 6, 7, 8]), # Test 14
        (TreeNode(4, TreeNode(2, TreeNode(1), TreeNode(3)), TreeNode(6, TreeNode(5), TreeNode(7))), [1, 2, 3, 4, 5, 6, 7]), # Test 15
    ]
