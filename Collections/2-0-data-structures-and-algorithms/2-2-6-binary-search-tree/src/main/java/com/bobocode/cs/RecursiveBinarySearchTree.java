package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.Comparator;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * {@link RecursiveBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is based on a linked nodes
 * and recursion. A tree node is represented as a nested class {@link Node}. It holds an element (a value) and
 * two references to the left and right child nodes.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Maksym Stasiuk
 */
public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

      static class Node<T>  {
          T element;
        Node left;
        Node right;


        public Node(T value){
            this.element=value;
            this.left=null;
            this.right=null;
        }
        public static <T> Node<T>  getValue(T item){
            return new Node<>(item);
        }



    }

      Node mainNode;

    int size=0;

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {

        RecursiveBinarySearchTree tree=new RecursiveBinarySearchTree();

        if(tree.mainNode==null){
            tree.mainNode=new Node(elements[0]);
            tree.size++;
        }

        for(int i=1;i<elements.length;i++){

            tree.insert(elements[i]);
        }

        return  tree;




        // throw new ExerciseNotCompletedException();
    }

    @Override
    public boolean insert(T element) {



        if(mainNode==null){
            mainNode=new Node(element);
            size++;
            return true;
        }

        else{
            boolean res= addElementInSomeNode(mainNode,element);


            return res;
    }
//        if(mainNode==null){
//            mainNode= Node.getValue(element);
//            return true;
//        }
//        else{
//           boolean res=addElementInSomeNode(mainNode,element);
//            return res;
//        }


//        throw new ExerciseNotCompletedException();
    }

    public boolean addElementInSomeNode(Node<T> node, T value){


        if (value.compareTo(node.element)<0){

            if(node.left==null){
                node.left= new Node(value);
                size++;
                return true;
            }
            else{
                boolean res= addElementInSomeNode(node.left,value);
                return res;
            }

        }
        else if (value.compareTo(node.element)>0){
            if(node.right==null){
                node.right= new Node(value);
                size++;
                return true;
            }
            else{
                boolean res= addElementInSomeNode(node.right,value);
                return res;
            }

        }
        else{
            return false;
        }

    }


    @Override
    public boolean contains(T element) {

        if(element==null){
            throw new NullPointerException();
        }

        boolean contains=findThatElementContainsInTree(mainNode,element);
        return contains;
    }

    private boolean findThatElementContainsInTree(Node<T> node, T value){
        if(node==null){
            return false;
        }

            if(value.compareTo(node.element)==0){
                return true;
            }
            else if(value.compareTo(node.element)<0){
                boolean found=findThatElementContainsInTree(node.left,value);
                return found;
            }
            else{
                boolean found=findThatElementContainsInTree(node.right,value);
                return found;
            }



    }




    @Override
    public int size() {

        return size;
    }

    @Override
    public int depth() {

        if (mainNode == null) {
            return 0;
        }

        int result= searchMaxDepth(mainNode)-1;

        return result;

    }

    private int searchMaxDepth(Node<T> node){
        if(node==null){
            return 0;
        }

        int leftCountDepth=searchMaxDepth(node.left);
        int rightCountDepth=searchMaxDepth(node.right);

        if(leftCountDepth> rightCountDepth){
            return leftCountDepth+1;
        }
        else{
            return  rightCountDepth+1;
        }

        }



    @Override
    public void inOrderTraversal(Consumer<T> consumer) {

        Node<T> node = mainNode;
        Stack<Node<T>> stack = new Stack<>();

        while ( node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                Node<T> nodeRemovedItem = stack.pop();
                consumer.accept(nodeRemovedItem.element);
                node = nodeRemovedItem.right;
            }
        }



        // throw new ExerciseNotCompletedException();
    }

}
