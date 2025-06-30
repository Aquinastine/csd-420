class Tree:
    """Abstract base class representing a tree structure."""
    #------------------------------- nested Postion class --------------------
    class Positon:
        """An abstraction representing the location of a single element."""
        
        def element(self):
            """Return the element stored at this Position."""
            raise NotImplementedError(must be implemented by subclass)
        
        def __eq__(self,other):
            """Return True if other Position represents the same location."""
            raise NotImplementedError(must be implemented by subclass)
        
        def __ne__(self,other):
            """Return True if other does not represent the same location."""
            return not(self== other)#oppositeofeq
        
    # ---------- abstract methods that concrete subclass must support ----------
    def root(self):
        """Return Position representing the trees root (or None if empty)."""        
        raise NotImplementedError(must be implemented by subclass)

    def parent(self,p):
        """Return Position representing ps parent (or None if p is root)."""
        raise NotImplementedError(must be implemented by subclass)

    def numchildren(self,p):
        """Return the number of children that Position p has."""
        raise NotImplementedError(must be implemented by subclass)

    def children(self,p):
        """Generate an iteration of Positions representing ps children."""
        raise NotImplementedError(must be implemented by subclass)

    def len(self):
        """Return the total number of elements in the tree."""
        raise NotImplementedError(must be implemented by subclass)

    # ---------- concrete methods implemented in this class ----------
    def isroot(self,p):
        """Return True if Position p represents the root of the tree."""
        return self.root()==p

    def isleaf(self,p):
        """Return True if Position p does not have any children."""
        return self.numchildren(p) == 0

    def isempty(self):
        """Return True if the tree is empty."""
        return len(self)==0

class BinaryTree(Tree):
    """Abstract base class representing a binary tree structure."""
    # --------------------- additional abstract methods ---------------------
    def left(self,p):
        """Return a Position representing ps left child.
        Return None if p does not have a left child."""
        raise NotImplementedError(must be implemented by subclass)
    
    def right(self,p):
        """Return a Position representing ps right child.
        Return None if p does not have a right child.16"""
        raise NotImplementedError(must be implemented by subclass)

    # ---------- concrete methods implemented in this class ----------
    def sibling(self,p):
        """Return a Position representing ps sibling (or None if no sibling)."""
        parent =self.parent(p)
        if parent is None:      # p must be the root
            return None         # root has no sibling
        else:
            if p==self.left(parent):
                return self.right(parent)   # possibly None
            else:
                return self.left(parent)    # possibly None
            
    def children(self,p):
        """Generate an iteration of Positions representing ps children."""
        if self.left(p)is not None:
            yield self.left(p)
        if self.right(p)is not None:
            yield self.right(p)

classLinkedBinaryTree(BinaryTree):2
"""Linked representation of a binary tree structure."""
    class _Node:    # Lightweight, nonpublic class for storing a node.
        slots=_element,_parent,_left,_right
        def init(self,element,parent=None,left=None,right=None):
            self._element = element
            self._parent = parent
            self._left = left
            self._right = right
            
    class Position(BinaryTree.Position):
        """An abstraction representing the location of a single element."""
        
        def init(self,container,node):
            """Constructor should not be invoked by user."""
            self._container = container
            self._node = node
        
        def element(self):
            """Return the element stored at this Position."""
            return self._node._element
        
        def __eq__(self,other):
            """Return True if other is a Position representing the same location."""
            return type(other) is type(self) and other.node is self.node
        
        def _validate(self,p):
            """Return associated node, if position is valid."""
            if not is instance(p,self.Position):
                raise TypeError(p must be proper Position type)
            if p.container is not self:
                raiseValueError(p does not belong to this container)
            if p.node.parent is p.node: # convention for deprecated nodes
                raiseValueError(p is no longer valid)
            return p.node
        
        def makeposition(self,node):
            """Return Position instance for given node (or None if no node)."""
            return self.Position(self,node) if node is not None else None