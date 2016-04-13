package com.chapter3;

import java.util.concurrent.locks.ReentrantLock;

public class S1ReentrantLock implements Runnable {
	private static ReentrantLock lock = new ReentrantLock();
	private static int i = 0;
	
	public void run() {
		for(int j = 0; j < 1000000; j++) {
			lock.lock();
			try {
				i++;
			} finally {
				lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new S1ReentrantLock());
		Thread t2 = new Thread(new S1ReentrantLock());
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
}
