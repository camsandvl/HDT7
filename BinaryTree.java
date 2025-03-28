
public class BinaryTree<K extends Comparable<K>, V> {
    private Node<K, V> root;

    public BinaryTree() {
        this.root = null;
    }

    public void insert(K key, V value) {
        root = insertRec(root, key, value);
    }

    private Node<K, V> insertRec(Node<K, V> root, K key, V value) {
        if (root == null) return new Node<>(key, value);
        if (key.compareTo(root.key) < 0) root.left = insertRec(root.left, key, value);
        else if (key.compareTo(root.key) > 0) root.right = insertRec(root.right, key, value);
        return root;
    }

    public V search(K key) {
        return searchRec(root, key);
    }

    private V searchRec(Node<K, V> root, K key) {
        if (root == null) return null;
        if (key.compareTo(root.key) == 0) return root.value;
        return key.compareTo(root.key) < 0 ? searchRec(root.left, key) : searchRec(root.right, key);
    }

    public void inOrderTraversal() {
        inOrderRec(root);
    }

    private void inOrderRec(Node<K, V> root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.println(root.value);
            inOrderRec(root.right);
        }
    }
}

