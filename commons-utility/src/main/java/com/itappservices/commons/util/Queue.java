package com.itappservices.commons.util;

import java.io.Serializable;

import com.itappservices.commons.util.exception.QueueEmptyException;
import com.itappservices.commons.util.exception.QueueFullException;

public interface Queue<D> extends Serializable {

	// return the size of the queue
	public int size();

	public boolean isEmpty();

	public boolean isFull();

	// insert an element into the queue
	public void enqueue(D element) throws QueueFullException;

	// removes an element from the queue
	public D dequeue() throws QueueEmptyException;
}
