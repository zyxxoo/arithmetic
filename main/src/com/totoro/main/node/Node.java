package com.totoro.main.node;

/**
 * Created by zhangyan on 2017/5/4.
 */
public class Node<T> {
    public T value;
    public Node<T> next;
    public Node<T> pre;

    public Node(T value, Node<T> next, Node<T> pre){
        this.value = value;
        this.next = next;
        this.pre = pre;
    }

    public Node(){}

    public Node(T value){
        this.value = value;
    }

    public Node(Node<T> now, Node<T> next){
        this(now);
        if (next != null){
            this.next = new Node<T>(next);
        }
    }

    public Node(Node<T> now){
        if (now != null) {
            this.value = now.value;
            this.next = now.next;
        }
    }
}
