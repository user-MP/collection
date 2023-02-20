package com.bobocode.cs;

import com.bobocode.cs.exception.EmptyStackException;
import com.bobocode.util.ExerciseNotCompletedException;






/**
 * {@link LinkedStack} is a stack implementation that is based on singly linked generic nodes.
 * A node is implemented as inner static class {@link Node<T>}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedStack<T> implements Stack<T> {


//    public static void main(String[] args) {
//        LinkedStack ls=new LinkedStack();
//        ls.push(1);
//        ls.push(2);
//        ls.push(3);
//        ls.push(4);
//        ls.push(5);
//        ls.push(6);
//        ls.push(7);
//        ls.push(8);
//        ls.push(9);
//        ls.push(10);
//
//
//        ls.pop();
//        ls.pop();
//        System.out.println(ls.size);
//    }


    static class Node<T> {

        T element;
        Node<T> next;

        public Node(T el){
            this.element=el;
        }

    }



   Node<T> firstNode;
   private int size=0;
    /**
     * This method creates a stack of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new stack of elements that were passed as method parameters
     */
    public static <T> LinkedStack<T> of(T... elements) {

        LinkedStack ls=new LinkedStack();


        ls.firstNode=new Node(elements[0]);


        for(int i=1;i<elements.length;i++){



          Node<T> newNode=new Node<>(elements[i]);

          Node<T> old=ls.firstNode;

           newNode.next=old;


          ls.firstNode=newNode;


        }


        ls.size=elements.length;
        return ls;



    }

    /**
     * The method pushes an element onto the top of this stack. This has exactly the same effect as:
     * addElement(item)
     *
     * @param element elements to add
     */
    @Override
    public void push(T element) {

        if(element==null){
            throw new NullPointerException();
        }

        Node<T> old=this.firstNode;
        Node<T> newNode=new Node<>(element);
        newNode.next=old;
        this.firstNode=newNode;
        this.size++;


        //throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * This method removes the object at the top of this stack
     * and returns that object as the value of this function.
     *
     * @return The object at the top of this stack
     * @throws EmptyStackException - if this stack is empty
     */
    @Override
    public T pop() {

        if(this.isEmpty()){
            throw new EmptyStackException();
        }

        Node<T> deleted=this.firstNode;

        Node<T> newNext=deleted.next;

        this.firstNode=newNext;

        this.size--;


      return deleted.element;


        //throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Returns the number of elements in the stack
     *
     * @return number of elements
     */
    @Override
    public int size() {
       return size;
        //throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Checks if a stack is empty
     *
     * @return {@code true} if a stack is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {

        if(firstNode==null || size==0 ){
            return true;
        }

        return false;
    }

}
