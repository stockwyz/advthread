package com.chapter3;

import java.util.concurrent.locks.ReentrantLock;

public class S4TryLock implements Runnable {
	private static ReentrantLock lock1 = new ReentrantLock();
	private static ReentrantLock lock2 = new ReentrantLock();
	private int lock;

	public S4TryLock(int lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		if (lock == 1) {
			if (lock1.tryLock()) {
				try {
					try {
						Thread.sleep(500L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					if(lock2.tryLock()) {
						try {
							System.out.println("lock:1 is done!");
						} finally {
							lock2.unlock();
						}
					}
				} finally {
					lock1.unlock();
				}
			}

		} else {
			if(lock2.tryLock()) {
				
			}
		}
	}

}
