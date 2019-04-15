package Program04;

import java.util.ArrayList;

public class Red_Black_Tree {


    public static class Node {

        Node left;
        Node right;
        Node parent;
        boolean color;
        int data;

        public Node(int data) {

            this.data = data;
            left = null;
            right = null;
            parent = null;
            color = true;
        }

        private Node getGrandparent() {
            return (this.getParent() == null) ? null : this.getParent().getParent();
        }

        public Node getLeft() {
            return left == null ? null : left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right == null ? null : right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getParent() {
            return parent == null ? null : parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node sibbling() {

            Node parent = this.getParent();

            if (parent == null) {

                return null;
            } else if (this == parent.getLeft()) {

                return parent.getRight();
            } else {

                return parent.getLeft();
            }
        }



        @Override
        public String toString() {

            return data + "." + (color ? "r" : "b");
        }
    }

    public static class RedBlackTree {

        Node root;
        final boolean RED = true;
        final boolean BLACK = false;

        public RedBlackTree() {
            root = null;
        }

        private boolean hasColor(Node node) {

            return node != null;
        }

        private Node mostRight(Node node){

            Node temp = node.getLeft();

            while (temp != null){

                temp = temp.getRight();
            }
            return temp;
        }

        public void remove(int data) {

            Node node = find(root, data);

            if (node == null){
                return;
            }

            if (node.getRight() != null && node.getLeft() != null){

                Node temp = mostRight(node);
                node.setData(temp.getData());
                node = temp;
            }

            Node temp = node.getLeft() == null ? node.getRight() : node.getLeft();

            if (temp != null){

                if (node == root){

                    if (isLeft(temp)) temp.getParent().setLeft(null);
                    else if (isRight(temp)) temp.getParent().setRight(null);
                    temp.setParent(null);
                    root = temp;
                }else if (isLeft(node)){
                    node.getParent().setLeft(temp);
                }else {
                    node.getParent().setRight(temp);
                }
                if (node.isColor() == BLACK){
                    fixRm(temp);
                }
            } else if (node == root){

                root = null;
            }else {

                if (isBLACK(node)){

                    fixRm(node);
                }
                if (isLeft(node)) node.getParent().setLeft(null);
                else if (isRight(node)) node.getParent().setRight(null);
                node.setParent(null);

            }





        }

        private void fixRm(Node node){


            while (node != root && isBLACK(node)){

                if (isLeft(node)){

                    Node sib = node.getParent().getRight();

                    if (isRED(sib)){

                        sib.setColor(BLACK);
                        node.getParent().setColor(RED);
                        rotation(node.getParent());
                        sib = node.getParent().getRight();
                    }
                    if (isBLACK(sib.getRight()) && isBLACK(sib.getLeft())){

                        sib.setColor(RED);
                        node = node.getParent();
                    }else {

                        if (isBLACK(sib.getRight())){

                            sib.getLeft().setColor(BLACK);
                            sib.setColor(RED);
                            rotation(sib);
                            sib = node.getParent().getRight();
                        }
                        sib.setColor(node.getParent().isColor());
                        node.getParent().setColor(BLACK);
                        if (sib.getRight() != null) sib.getRight().setColor(BLACK);
                        rotation(node.getParent());
                        node = root;
                    }




                }else {

                    Node sib = getSib(node);

                    if (isRED(sib)){

                        sib.setColor(BLACK);
                        node.getParent().setColor(RED);
                        rotation(node.getParent());
                        sib = node.getParent().getLeft();
                    }
                    if (sib != null && isBLACK(sib.getLeft()) && isBLACK(sib.getRight())){

                        sib.setColor(RED);
                        node = node.getParent();
                    }else {

                        if (isBLACK(sib.getLeft())){

                            sib.getRight().setColor(BLACK);
                            sib.setColor(RED);
                            rotation(sib);
                            sib = node.getParent().getLeft();
                        }
                        sib.setColor(node.getParent().isColor());
                        node.getParent().setColor(BLACK);
                        if (sib.getLeft() != null) sib.getLeft().setColor(BLACK);
                        rotation(node.getParent());
                        node = root;
                    }
                }
            }
            node.setColor(BLACK);
        }

        private Node find(Node node, int data){

            if (node == null) return null;
            if (node.getData() == data){
                return node;
            }
            if (data < node.getData()) return find(node.getLeft(), data);
            if (data > node.getData()) return find(node.getRight(), data);
            return null;

        }




        public void rotateleft(Node node) {

            if (node.getRight() == null) {
                return;
            }

            Node oldRight = node.getRight();
            node.setRight(oldRight.getLeft());

            if (node.getParent() == null) {

                root = oldRight;

            } else if (isLeft(node)) {

                if (oldRight.getParent().getLeft() == oldRight){
                    oldRight.getParent().setLeft(null);
                }else {
                    oldRight.getParent().setRight(null);
                }
                node.getParent().getLeft().setParent(null);
                node.getParent().setLeft(oldRight);
                oldRight.setParent(node.getParent());

            } else {

                node.getParent().setRight(oldRight);
            }
            oldRight.setLeft(node);
        }

        public void rotateright(Node node) {

            if (node.getLeft() == null) {
                return;
            }

            Node oldLeft = node.getLeft();
            node.setLeft(oldLeft.getRight());

            if (node.getParent() == null) {

                root = oldLeft;

            } else if (isLeft(node)) {

                node.getParent().setLeft(oldLeft);

            } else {

                node.getParent().setRight(oldLeft);
            }
            oldLeft.setRight(node);
        }

        public void inOrder(Node node) {

            if (node == null) {
                return;
            }

            inOrder(node.getLeft());
            System.out.print(node + " ");
            inOrder(node.getRight());

        }

        private Node search(Node node, int key) {

            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }

            if (node.getData() == key) {
                return node;
            } else if (node.getLeft() != null && node.getLeft().getData() < key) {
                return search(node.getLeft(), key);
            } else if (node.getRight() != null) {
                return search(node.getRight(), key);
            }

            return null;
        }

        public void add(int data) {

            if (root == null) {
                root = new Node(data);
                root.setColor(BLACK);
            } else if (search(root, data) == null) {
                add(root, data);
            }
        }

        private void add(Node node, int data) {


            if (node.getData() < data) {
                if (node.getRight() == null) {
                    Node temp = new Node(data);
                    temp.setParent(node);
                    node.setRight(temp);
                    if (node != root) fixAdd(node.getRight());
                } else {
                    add(node.getRight(), data);
                }
            } else {      // node.getdata > data
                if (node.getLeft() == null) {
                    Node temp = new Node(data);
                    temp.setParent(node);
                    node.setLeft(temp);
                    if (node != root) fixAdd(node.getLeft());
                } else {
                    add(node.getLeft(), data);
                }
            }

            root.setColor(BLACK);
            if (node == root) return;


        }

        private void fixAdd(Node node) {

            if (node == null) return;
            if (isRED(node) && isRED(node.getParent())) {
                Node gran = getGran(node);
                if (gran != null) {
                    Node un = getUncle(node);
                    if (isRED(un)) {

                        if (gran.getLeft() != null) gran.getLeft().setColor(BLACK);
                        if (gran.getRight() != null) gran.getRight().setColor(BLACK);
                        gran.setColor(RED);
                        fixAdd(gran);

                    } else if (un == null) {
                        rotation(node);

                    } else if (isBLACK(un)) {

                        rotation(node);
                    }
                }
            }
        }


        private void rotation(Node node) {

            if (isLeft(node.getParent())) {

                if (isLeft(node)) {
                    Node pivot = node.getParent();
                    Node up = pivot.getParent();

                    up.setRight(pivot.getLeft());
                    pivot.setRight(up);
                    pivot.setParent(up.getParent());
                    up.setParent(pivot);

                    pivot.setColor(BLACK);
                    up.setColor(RED);

                    if (up == root) {
                        root = pivot;
                    }
                    
                } else {     // isRight(node)

                    Node pivot = node.getParent();
                    Node up = pivot.getParent();
                    Node gran = getGran(pivot);

                    pivot.setRight(up);
                    up.setLeft(null);
                    pivot.setParent(gran);
                    up.setParent(pivot);
                    if (gran != null) gran.setLeft(pivot);

                    pivot.setColor(BLACK);
                    up.setColor(RED);

                    if (up == root) {
                        root = pivot;
                    }

                }

            } else {      // isRight(node.getParent()



                if (isLeft(node)) {

                    Node par = node.getParent();
                    Node gran = getGran(node);

                    node.setRight(par);
                    par.setParent(node);
                    if (gran != null) gran.setRight(node);
                    node.setParent(gran);
                    par.setRight(null);
                    par.setLeft(null);
                    fixAdd(par);


                } else {     // isRight(node)

                    Node pivot = node.getParent();
                    Node up = pivot.getParent();
                    Node gran = getGran(pivot);

                    up.setRight(pivot.getLeft());
                    pivot.setLeft(up);
                    pivot.setParent(gran);
                    up.setParent(pivot);
                    if (gran != null) gran.setRight(getSib(node));

                    pivot.setColor(BLACK);
                    up.setColor(RED);

                    if (up == root) {
                        root = pivot;
                    }

                }
            }
        }


        private boolean isLeft(Node node) {

            if (node == null) return false;
            Node parent = node.getParent();
            return parent == null ? false : parent.getLeft() == node;
        }

        private boolean isRight(Node node) {

            Node parent = node.getParent();
            return parent == null ? false : parent.getRight() == node;
        }

        public boolean isBLACK(Node node) {
            return (node != null && BLACK == node.isColor());
        }

        public boolean isRED(Node node) {
            return (node != null && RED == node.isColor());


        }

        public Node getSib(Node node) {

            if (node == root) {
                return null;
            }
            return isLeft(node) ? node.getParent().getRight() : node.getParent().getLeft();
        }

        private Node getGran(Node node) {
            return node.getParent() == null && node.getParent().getParent() == null
                    ? null : node.getParent().getParent();
        }

        private Node getUncle(Node node) {

            Node gran = getGran(node);
            Node par = node.getParent();

            if (gran != null && par != null) {

                return (isLeft(par) ? gran.getRight() : gran.getLeft());
            } else {
                return null;
            }
        }
    }

    public static void main(String[] args) {

        RedBlackTree redBlackTree = new RedBlackTree();
        String input = "4.in 7.in 12.in 15.in 3.in 5.in 14.in 18.in 16.in 17.in 3.del 12.del 17.del 18.del 15.del 16.del ";
        String[] list = input.split(" ");

        ArrayList<String[]> fulllist = new ArrayList<>();


        for (String string3 : list) {
            fulllist.add(string3.split("\\."));
        }


        for (String[] thing : fulllist) {
            for (int i = 0; i < thing.length; i++) {
                if (thing[i].equalsIgnoreCase("in")) {
                    redBlackTree.add(Integer.parseInt(thing[i - 1]));
                } else if (thing[i].equalsIgnoreCase("del")) {
                    redBlackTree.remove(Integer.parseInt(thing[i - 1]));
                }
            }
        }
        redBlackTree.inOrder(redBlackTree.root);
    }
}
