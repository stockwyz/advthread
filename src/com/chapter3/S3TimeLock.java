package com.chapter3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class S3TimeLock implements Runnable {
	private static ReentrantLock lock = new ReentrantLock();
	
	@Override
	public void run() {
		try {
			if(lock.tryLock(5, TimeUnit.SECONDS)) {
				System.out.println(Thread.currentThread().getId() + " get lock!");
				Thread.sleep(8000L);
			} else {
				System.out.println(Thread.currentThread().getId() + " get lock failed");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if(lock.isHeldByCurrentThread())
				lock.unlock();
		}
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new S3TimeLock());
		Thread t2 = new Thread(new S3TimeLock());
		t1.start();
		t2.start();
	}
}
