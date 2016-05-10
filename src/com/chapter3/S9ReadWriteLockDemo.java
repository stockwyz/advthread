package com.chapter3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class S9ReadWriteLockDemo {
	private ReentrantLock lock = new ReentrantLock();
	private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private ReadLock readLock = readWriteLock.readLock();
	private WriteLock writeLock = readWriteLock.writeLock();
	private int value;
	
	public Object handleRead(Lock lock)
	{
		try {
			lock.lock();
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return value;
		} finally {
			lock.unlock();
		}
	}
	
	public void handleWrite(Lock lock, int index) {
		try	{
			lock.lock();
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			value = index;
		} finally {
			lock.unlock();
		}
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
