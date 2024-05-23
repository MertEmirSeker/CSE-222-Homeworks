/**
 * The AVLTree class represents a balanced binary search tree (AVL tree) for managing Stock objects.
 */
public class AVLTree
{
    private class Node
    {
        Stock stock;
        Node left, right;
        int height;

        Node(Stock stock)
        {
            this.stock = stock;
            this.height = 1;
        }
    }

    private Node root;

    /**
     * Inserts a new stock into the AVL tree.
     *
     * @param stock The stock to be inserted.
     */
    public void insert(Stock stock)
    {
        root = insert(root, stock);
    }

    /**
     * Inserts a new stock into the AVL tree starting from the given node.
     *
     * @param node  The current node in the AVL tree.
     * @param stock The stock to be inserted.
     * @return The updated node after insertion.
     */
    private Node insert(Node node, Stock stock)
    {
        if (node == null)
        {
            return new Node(stock);
        }

        int cmp = stock.getSymbol().compareTo(node.stock.getSymbol());
        if (cmp < 0)
        {
            node.left = insert(node.left, stock);
        }
        else if (cmp > 0)
        {
            node.right = insert(node.right, stock);
        }
        else
        {
            // If stock with same symbol exists, update its attributes
            node.stock.setPrice(stock.getPrice());
            node.stock.setVolume(stock.getVolume());
            node.stock.setMarketCap(stock.getMarketCap());
            return node;
        }

        // Update height and balance the node
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    /**
     * Deletes a stock from the AVL tree.
     *
     * @param symbol The symbol of the stock to be deleted.
     */
    public void delete(String symbol)
    {
        root = delete (root, symbol);
    }

    /**
     * Deletes a stock from the AVL tree starting from the given node.
     *
     * @param node   The current node in the AVL tree.
     * @param symbol The symbol of the stock to be deleted.
     * @return The updated node after deletion.
     */
    private Node delete(Node node, String symbol)
    {
        if (node == null)
        {
            return null;
        }

        int cmp = symbol.compareTo(node.stock.getSymbol());
        if (cmp < 0)
        {
            node.left = delete (node.left, symbol);
        }
        else if (cmp > 0)
        {
            node.right = delete (node.right, symbol);
        }
        else
        {
            // Node with only one child or no child
            if (node.left == null || node.right == null)
            {
                node = (node.left != null) ? node.left : node.right;
            }
            else
            {
                // Node with two children: Get the inorder successor (smallest in the right subtree)
                Node temp = min_value_node(node.right);
                node.stock = temp.stock;
                node.right = delete (node.right, temp.stock.getSymbol());
            }
        }

        // If the tree had only one node then return
        if (node == null)
        {
            return null;
        }

        // Update height and balance the node
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    /**
     * Finds the node with the minimum value in the given subtree.
     *
     * @param node The root of the subtree.
     * @return The node with the minimum value.
     */
    private Node min_value_node(Node node)
    {
        Node current = node;
        while (current.left != null)
        {
            current = current.left;
        }
        return current;
    }

    /**
     * Searches for a stock in the AVL tree.
     *
     * @param symbol The symbol of the stock to search for.
     * @return The Stock object if found, otherwise null.
     */
    public Stock search(String symbol)
    {
        Node result = search(root, symbol);
        return (result != null) ? result.stock : null;
    }

    /**
     * Searches for a stock in the AVL tree starting from the given node.
     *
     * @param node   The current node in the AVL tree.
     * @param symbol The symbol of the stock to search for.
     * @return The node containing the stock if found, otherwise null.
     */
    private Node search(Node node, String symbol)
    {
        if (node == null || node.stock.getSymbol().equals(symbol))
        {
            return node;
        }

        int cmp = symbol.compareTo(node.stock.getSymbol());
        if (cmp < 0)
        {
            return search(node.left, symbol);
        }
        else
        {
            return search(node.right, symbol);
        }
    }

    /**
     * Balances the AVL tree node.
     *
     * @param node The node to balance.
     * @return The balanced node.
     */
    private Node balance(Node node)
    {
        int balanceFactor = getBalanceFactor(node);

        // Left Left Case
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
        {
            return rightRotate(node);
        }

        // Left Right Case
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0)
        {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Right Case
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
        {
            return leftRotate(node);
        }

        // Right Left Case
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0)
        {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /**
     * Performs a right rotation on the subtree rooted at the given node.
     *
     * @param y The root of the subtree to rotate.
     * @return The new root after rotation.
     */
    private Node rightRotate(Node y)
    {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    /**
     * Performs a left rotation on the subtree rooted at the given node.
     *
     * @param x The root of the subtree to rotate.
     * @return The new root after rotation.
     */
    private Node leftRotate(Node x)
    {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    /**
     * Gets the height of the given node.
     *
     * @param node The node to get the height of.
     * @return The height of the node.
     */
    private int height(Node node)
    {
        return (node == null) ? 0 : node.height;
    }

    /**
     * Gets the balance factor of the given node.
     *
     * @param node The node to get the balance factor of.
     * @return The balance factor of the node.
     */
    private int getBalanceFactor(Node node)
    {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    /**
     * Prints the in-order traversal of the AVL tree.
     */
    public void inOrderTraversal()
    {
        inOrderTraversal(root);
    }

    /**
     * Prints the in-order traversal of the AVL tree starting from the given node.
     *
     * @param node The root of the subtree to traverse.
     */
    private void inOrderTraversal(Node node)
    {
        if (node != null)
        {
            inOrderTraversal(node.left);
            System.out.println(node.stock);
            inOrderTraversal(node.right);
        }
    }

    /**
     * Prints the pre-order traversal of the AVL tree.
     */
    public void preOrderTraversal()
    {
        preOrderTraversal(root);
    }

    /**
     * Prints the pre-order traversal of the AVL tree starting from the given node.
     *
     * @param node The root of the subtree to traverse.
     */
    private void preOrderTraversal(Node node)
    {
        if (node != null)
        {
            System.out.println(node.stock);
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    /**
     * Prints the post-order traversal of the AVL tree.
     */
    public void postOrderTraversal()
    {
        postOrderTraversal(root);
    }

    /**
     * Prints the post-order traversal of the AVL tree starting from the given node.
     *
     * @param node The root of the subtree to traverse.
     */
    private void postOrderTraversal(Node node)
    {
        if (node != null)
        {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.println(node.stock);
        }
    }
}
