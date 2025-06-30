class TreeNode:
    def __init__(self, value=0, left=None, right=None):
        self.value = value
        self.left = left
        self.right = right
        self.parent = None
        self.root = None

    def insert(self, new_node):
        """
        Insert a TreeNode instance downstream of this node,
        preserving all existing descendants.
        """
        if not isinstance(new_node, TreeNode):
            raise TypeError("insert() requires a TreeNode instance")

        if new_node.value < self.value:
            if self.left is None:
                self.left = new_node
                new_node.parent = self
            else:
                # Descend left subtree
                self.left.insert(new_node)
        else:
            if self.right is None:
                self.right = new_node
                new_node.parent = self
            else:
                # Descend right subtree
                self.right.insert(new_node)
        

def insert_into_bst(root, value):
    """
    Inserts a value into a Binary Search Tree and returns the root of the tree.
    """
    if root is None:
        return TreeNode(value)
    else:
        if not isinstance(root, TreeNode):
            raise TypeError("insert() requires a TreeNode instance")
        newNode = TreeNode(value)
        root.insert(newNode)
    return root

def is_valid_bst(root):
    """
    Determines if a binary tree is a valid Binary Search Tree.
    """
    def helper(node, low, high):
        if not node:
            return True
        if node.value <= low or node.value >= high:
            return False
        return helper(node.left, low, node.value) and helper(node.right, node.value, high)

    return helper(root, float('-inf'), float('inf'))

def inorder_traversal(root):
    """
    Performs in-order traversal on a binary tree and returns a list of values.
    """
    if root is None:
        return []
    return inorder_traversal(root.left) + [root.value] + inorder_traversal(root.right)

