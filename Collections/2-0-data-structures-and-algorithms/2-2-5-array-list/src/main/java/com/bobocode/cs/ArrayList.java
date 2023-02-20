package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.NoSuchElementException;

/**
 * {@link ArrayList} is an implementation of {@link List} interface. This resizable data structure
 * based on an array and is simplified version of {@link java.util.ArrayList}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @author Serhii Hryhus
 */
public class ArrayList<T> implements List<T> {

    Object arr[];

    int capacity;
    int size;


    /**
     * This constructor creates an instance of {@link ArrayList} with a specific capacity of an array inside.
     *
     * @param initCapacity - the initial capacity of the list
     * @throws IllegalArgumentException – if the specified initial capacity is negative or 0.
     */
    public ArrayList(int initCapacity) {

        if(initCapacity<=0){
            throw new IllegalArgumentException();
        }
        capacity=initCapacity;

        arr=new Object[initCapacity];



        //throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * This constructor creates an instance of {@link ArrayList} with a default capacity of an array inside.
     * A default size of inner array is 5;
     */
    public ArrayList() {

        capacity=5;

        arr=new Object[capacity];
        //throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Creates and returns an instance of {@link ArrayList} with provided elements
     *
     * @param elements to add
     * @return new instance
     */
    public static <T> List<T> of(T... elements) {

        ArrayList as=new ArrayList(elements.length);

        for (T item: elements  ) {
            as.add(item);
        }

        return as;


        //throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Adds an element to the array.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {

    int i=0;

    if(size==capacity){

        capacity *=2;

        Object newArr[]=new Object[capacity*2];

        for (Object item: arr  ) {
            newArr[i]=arr[i];
            i++;
        }
        newArr[i]=element;

        arr=newArr;
    }
    else{
        arr[size]=element;

    }

        size++;

    //    throw new ExerciseNotCompletedException(); // todo: implement this method


    }

    /**
     * Adds an element to the specific position in the array where
     *
     * @param index   index of position
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {

        if(index<0 || index>arr.length || index>size){
            throw new IndexOutOfBoundsException();

        }

        if(capacity<=size+1){


            Object newArr[]=new Object[capacity*2];

            for(int i=0;i<arr.length;i++){

                newArr[i] =arr[i];
            }

            arr=newArr;

        }



        Object copyArray[]=new Object[arr.length+1];



        if(index==0){

            copyArray[0]=element;

            for(int i=1;i<size;i++){
                copyArray[i]= arr[i];
            }


        }

        else if(index==arr.length){
            int i=0;
            for(Object elem:arr){
                copyArray[i]=elem;
                i++;
            }
            copyArray[i]=element;
        }

        else if(index>capacity){
           int i=0;

            for(;i<size;i++){
                copyArray[i]=arr[i];
            }
            arr[i]=element;
        }

        else{
            int i=0;
            for(;i<index;i++){
                copyArray[i]=arr[i];
            }

            copyArray[index]=element;

            for(int j=index+1;j<size+1;j++,i++){

                copyArray[j]=arr[i];

            }

        }
        arr=copyArray;
        size++;

        //throw new ExerciseNotCompletedException(); // todo: implement this method
    }






    /**
     * Retrieves an element by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index index of element
     * @return en element
     */
    @Override
    public T get(int index) {

        if(arr==null || index<0 || index>=arr.length || this.isEmpty() ){
            throw new IndexOutOfBoundsException();
        }

        return (T) arr[index];

        //  throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        if(arr==null || size==0 ){
            throw new NoSuchElementException();
        }
        return (T) arr[0];
        //throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {

        if(size==0 ){
            throw new NoSuchElementException();
        }

        return (T)arr[size-1];

        //throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Changes the value of array at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   position of value
     * @param element a new value
     */
    @Override
    public void set(int index, T element) {
        if(arr==null || index<0 || index>=arr.length || isEmpty() ){
            throw new IndexOutOfBoundsException();
        }

        arr[index]=element;


        //throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {

        if(index>=size || index<0){
            throw new IndexOutOfBoundsException();
        }

        T removedItem=(T)arr[index];

        for(int i=index;i<arr.length-1;i++){

          arr[i]=arr[i+1];

         }
        arr[arr.length-1]=null;

         size--;



        return removedItem;
       // throw new ExerciseNotCompletedException(); // todo: implement this method
    }



    /**
     * Checks for existing of a specific element in the list.
     *
     * @param element is element
     * @return If element exists method returns true, otherwise it returns false
     */
    @Override
    public boolean contains(T element) {

        for (Object item: arr
             ) {
            if(item==element){
                return true;
            }
        }
        return false;
        //throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {

        if(size==0){
            return true;
        }
        return false;
        //throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * @return amount of saved elements
     */
    @Override
    public int size() {
    return size;
        //throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        size=capacity=0;
        arr=null;
        //throw new ExerciseNotCompletedException(); // todo: implement this method
    }
}
