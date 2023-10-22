
import java.util.Date;
import java.util.Iterator;

class HashMap{
    class Entity{
        int key;
        int value;
    }
    class Basket{
        Node head;
        class Node{
            Entity entity;
            Node next;
        }

        public Integer find(int key){
            Node node = head;
            while(node != null){
                if(node.entity.key == key){
                    return node.entity.value;
                }
                node = node.next;
            }
            return null;
        }

        public boolean insert(Entity entity){
            Node node = new Node();
            node.entity = entity;

            if(head == null){
                head = node;
                return true;
            }else {
                Node cur = head;
                while (cur != null){
                    if(cur.entity.key == entity.key){
                        return false;
                    }
                    if(cur.next == null){
                        cur.next = node;
                        return true;
                    }
                    cur = cur.next;
                }
                return false;
            }
        }
    }

    public static int INIT_SIZE = 16;
    Basket[] baskets;

    public HashMap(){
        this(INIT_SIZE);
    }
    public HashMap(int size){
        baskets = new Basket[size];
    }

    public int getIndex(int key){
        //key.HashCode()
        return key % baskets.length;
    }

    public Integer find(int key){
        int index = getIndex(key);
        Basket basket = baskets[index];
        if(basket != null){
            return basket.find(key);
        }
        return null;
    }

    public boolean insert(int key, int value){
        Entity entity = new Entity();
        entity.key = key;
        entity.value = value;

        int index = getIndex(key);
        Basket basket = baskets[index];

        if(basket == null){
            basket = new Basket();
            baskets[index] = basket;
        }

        return basket.insert(entity);

    }
}

class BinaryTree{
    Node root;
    class Node{
        int value;
        Node left;
        Node right;
    }

    public boolean find(int value){
        return find(root, value);
    }

    public boolean find(Node node, int value){
        if(node == null){
            return false;
        }
        if(node.value == value){
            return true;
        }
        if(node.value < value){
            return find(node.right, value);
        }else{
            return find(node.left, value);
        }
    }

    public boolean insert(int value){
        if(root == null){
            root = new Node();
            root.value = value;
            return true;
        }
        return insert(root, value);
        //root.color = BLACK;
    }

    public boolean insert(Node node, int value) {
        if(node.value == value){
            return false;
        }

        if(node.value < value){
            if(node.right == null){
                node.right = new Node();
                node.right.value = value;
                // node.right.color = RED;
                //rebalance();
                return true;
            }
            return insert(node.right, value);
            //rebalance();
        }else{
            if(node.left == null){
                node.left = new Node();
                node.left.value = value;
                // node.left.color = RED;
                //rebalance();
                return true;
            }
            return insert(node.left, value);
            //rebalance();
        }
    }
}

class RedBlackTree {
    Node root;
    static final boolean RED = true;
    static final boolean BLACK = false;

    class Node {
        int value;
        Node left;
        Node right;
        boolean color;

        Node(int value, boolean color) {
            this.value = value;
            this.color = color;
        }
    }

    public void insert(int value) {
        root = insert(root, value);
        root.color = BLACK;
    }

    private Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value, RED);
        }

        if (value < node.value) {
            node.left = insert(node.left, value);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        } else {
            return node;
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public boolean contains(int value) {
        return contains(root, value);
    }

    private boolean contains(Node node, int value) {
        if (node == null) {
            return false;
        }

        if (value == node.value) {
            return true;
        } else if (value < node.value) {
            return contains(node.left, value);
        } else {
            return contains(node.right, value);
        }
    }
}


public class Main {
    public static void main(String[] args) {
//        HashMap map = new HashMap();
//
//        map.insert(1, 2);
//        map.insert(17, 4);
//        map.insert(5, 6);
//
//        System.out.println(map.find(1));
//        System.out.println(map.find(17));

        RedBlackTree tree = new RedBlackTree();

        tree.insert(5);
        tree.insert(3);
        tree.insert(1);
        tree.insert(2);
        tree.insert(4);
        tree.insert(7);
        tree.insert(6);
        tree.insert(8);

        int esfe=0;
    }
}