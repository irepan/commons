package com.itappservices.commons.util;

import java.util.ArrayList;

import com.itappservices.commons.util.exception.QueueEmptyException;
import com.itappservices.commons.util.exception.QueueFullException;

public class CircularArrayQueue<D> implements Queue<D> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2418270947770520288L;
	private static final int defaultCapacity = 5;
	private ArrayList<D> Queue;
	private final int elementNumber; // capacity
	private int front = 0;
	private int rear = 0;

	public CircularArrayQueue() {
		this(defaultCapacity);
	}

	public CircularArrayQueue(int capacity) {
		elementNumber = capacity;
		Queue = new ArrayList<D>(elementNumber);
	}

	@Override
	public int size() {
		if (rear > front)
			return rear - front;
		return elementNumber - front + rear;
	}

	@Override
	public boolean isEmpty() {
        return (rear == front) ? true : false;
	}

	@Override
	public boolean isFull() {
        int diff = rear - front; 
        if(diff == -1 || diff == (elementNumber -1))
            return true;
        return false;
	}

	@Override
	public void enqueue(D element) throws QueueFullException {
        if(isFull()){
            throw new QueueFullException("Queue is Full.");
        }else{
            Queue.set(rear, element);
            rear = (rear + 1) % elementNumber;
        }
	}

	@Override
	public D dequeue() throws QueueEmptyException {
        D item = null; 
        if(isEmpty()){
            throw new QueueEmptyException();
        }else{
            item = Queue.get(front);
            Queue.set(front, null);
            front = (front + 1) % elementNumber;
        }
       return item;
	}

}
