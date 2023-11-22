package com.example.calculator.mathematicalSection;

public class SinglyLinkedList<E> {
    Node head;
    Node tail;
    int size;

    SinglyLinkedList() {
    }

    class Node {
        E data;
        Node next;

        Node(E data) {
            this.data = data;
        }
    }

    public void addFirst(E data) {
        Node node = new Node(data);                        //change the pointer to the new one
        node.next = this.head;
        this.head = node;
        if (this.size == 0) this.tail = this.head;
        this.size++;
    }


    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public E first() {
        if (!isEmpty()) {
            return this.head.data;
        }
        return null;
    }

    public E removeFirst() {
        E first = this.head.data;               //change the pointer to the next one
        this.head = this.head.next;
        this.size--;
        return first;
    }

}