package com.example.calculator.mathematicalSection;


public class LinkedStack<E> {
        SinglyLinkedList<E> singlyLinkedList = new SinglyLinkedList<>();

        public LinkedStack() {
        }

        public int size() {
            return singlyLinkedList.size();
        }                       //return size

        public boolean isEmpty() {
            return singlyLinkedList.isEmpty();
        }             //check if it's empty

        public void push(E element) {
            singlyLinkedList.addFirst(element);
        }   //add the newest element

        public E top() {
            return singlyLinkedList.first();
        }                 //return the newest element

        public E pop() {
            return singlyLinkedList.removeFirst();
        }           //return the newest element and remove it
    }

