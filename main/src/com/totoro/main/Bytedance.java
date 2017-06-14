package com.totoro.main;

import com.totoro.main.node.Node;

/**
 * Created by zhangyan on 2017/5/4.
 */
public class Bytedance {


    /**
     * 算法描述
     *
     * 两个单链表，每个 Node 为个位数，现在将两个链表相加
     */
    public static Node<Integer> add(Node<Integer> list1, Node<Integer> list2){
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        Node<Integer> list11 = reversal(list1);
        Node<Integer> list22 = reversal(list2);

        Node<Integer> ret = null;
        Node<Integer> cur_ret = null;

        Node<Integer> cur1 = list11;
        Node<Integer> cur2 = list22;
        int carry = 0;
        int value1 = 0;
        int value2 = 0;
        int this_value = 0;
        while (cur1 != null || cur2 != null || carry != 0){
            value1 = cur1 == null ? 0 : cur1.value;
            value2 = cur2 == null ? 0 : cur2.value;
            this_value = value1 + value2 + carry;
            if (this_value > 9){
                carry = 1;
                this_value -= 10;
            }else{
                carry = 0;
            }

            Node<Integer> node = new Node<>(this_value);

            if (ret == null){
                ret = node;
                cur_ret = ret;
            }else{
                cur_ret.next = node;
                cur_ret = node;
            }

            cur1 = cur1 == null ? null : cur1.next;
            cur2 = cur2 == null ? null : cur2.next;
        }


        return reversal(ret);
    }

    /**
     * 翻转单链表
     *
     * @param list2
     * @return
     */
    public static Node<Integer> reversal(Node<Integer> list2) {
        if (list2 == null) return null;

        Node<Integer> current_head = list2;
        Node<Integer> next = null;
        Node<Integer> ret = null;

        while (current_head != null){

            next = current_head.next;

            current_head.next = ret;

            ret = current_head;

            current_head = next;
        }

        return ret;
    }


    public static void main(String[] argvs){
        Node<Integer> _789 = createList(7, 8, 9);
        Node<Integer> _437 = createList(4, 3, 7);

        Node<Integer> _989_437 = add(_789, _437);
        System.out.println(print(_989_437));

    }

    public static Node<Integer> createList(int ...argvs){
        Node<Integer> node = null, current = null;

        for (int i : argvs){
            Node<Integer>  v = new Node<>(i);
            if (current == null){
                current = v;
                node = current;
            }else{
                current.next = v;
            }

            current = v;
        }

        return node;
    }

    public static String print(Node head){
        StringBuilder sb = new StringBuilder();
        while (head != null){
            sb.append(head.value);
            head = head.next;
        }

        return sb.toString();
    }
}
