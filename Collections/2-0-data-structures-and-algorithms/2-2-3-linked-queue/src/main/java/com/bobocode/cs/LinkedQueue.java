package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

/**
 * {@link LinkedQueue} implements FIFO {@link Queue}, using singly linked nodes. Nodes are stores in instances of nested
 * class Node. In order to perform operations {@link LinkedQueue#add(Object)} and {@link LinkedQueue#poll()}
 * in a constant time, it keeps to references to the head and tail of the queue.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> a generic parameter
 * @author Taras Boychuk
 * @author Ivan Virchenko
 */
public class LinkedQueue<T> implements Queue<T> {


   static class Node<T> {
        // todo:
        T element;
        Node<T> next;

        public Node(T el){
            this.element=el;
        }

    }

    // start
    Node<T> head;

    // end queue
    Node<T> tail;
    int size;

    /**
     * Adds an element to the end of the queue.
     *
     * @param element the element to add
     */
    public void add(T element) {
        Node<T> newItem = new Node<T>(element);
        if(size==0){

            this.head=newItem;
            this.tail=newItem;
        }
        else{


            this.tail.next =newItem;
            this.tail =newItem;


        }

        size++;
        //throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Retrieves and removes queue head.
     *
     * @return an element that was retrieved from the head or null if queue is empty
     */


    public T poll() {


        if(size==1){
            T elem= head.element;
            this.head=this.tail=null;
            size=0;
            return elem;
        }
        else if(size !=0){
            Node<T> oldHead=this.head;

            this.head=oldHead.next;
            this.tail=this.tail.next;

            size--;
            return oldHead.element;
        }

        else{
             size=0;
            return null;
        }



//       if(this.size==0){
//           return null;
//       }
//       if(this.size==1){
//           T val=this.head.element;
//          tail=head=null;
//
//           this.size=0;
//
//           return val;
//       }
//
//      Node<T> deleteItem=  this.head;
//
//       this.head=deleteItem.next;
//       size--;
//       return deleteItem.element;


        // throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Returns a size of the queue.
     *
     * @return an integer value that is a size of queue
     */
    public int size() {
        return size;
        //throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Checks if the queue is empty.
     *
     * @return {@code true} if the queue is empty, returns {@code false} if it's not
     */
    public boolean isEmpty() {
        if(head==null && tail==null || size==0){
            return true;
        }

        return false;
        //throw new ExerciseNotCompletedException(); // todo: implement this method
    }
}
