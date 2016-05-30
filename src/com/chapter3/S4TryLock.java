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
			while (true) {
				if (lock1.tryLock()) {
					try {
						try {
							Thread.sleep(900L);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						if (lock2.tryLock()) {
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
			}
		} else {
			while (true) {
				if (lock2.tryLock()) {
					try {
						try {
							Thread.sleep(1000L);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if (lock1.tryLock()) {
							try {
								System.out.println("lock2 is done");
							} finally {
								lock1.unlock();
							}
						}
					} finally {
						lock2.unlock();
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		S4TryLock lock1 = new S4TryLock(1);
		S4TryLock lock2 = new S4TryLock(2);
		Thread t1 = new Thread(lock1);
		Thread t2 = new Thread(lock2);
		t1.start();
		t2.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
