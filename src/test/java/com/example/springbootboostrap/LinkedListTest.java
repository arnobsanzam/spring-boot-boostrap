package com.example.springbootboostrap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class LinkedListTest {

    MyLinkedList linkedList = new MyLinkedList();

    @Test
    public void startTesting() {

        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);
        linkedList.reverse();
        linkedList.print();

        assertNotNull("");
        assertEquals("", "");
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class MyLinkedList {

    ListNode head;

    public void add(int value) {
        ListNode listNode = new ListNode();
        listNode.val = value;
        if (Objects.isNull(head)) {
            head = listNode;
        } else {
            ListNode nextNode = head;
            while (Objects.nonNull(nextNode.next)) {
                nextNode = nextNode.next;
            }
            nextNode.next = listNode;
        }
    }

    public void print() {
        ListNode node = head;
        while (Objects.nonNull(node)) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public void reverse() {
        if (Objects.isNull(head)) {

        } else {
            ListNode current = head;
            ListNode previous = null;
            ListNode next = null;

            while (Objects.nonNull(current)) {
                next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }

            head = previous;
        }
    }

}