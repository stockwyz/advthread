package com.chapter3;

import java.util.concurrent.locks.ReentrantLock;

public class S2InitLock implements Runnable {
	public static ReentrantLock lock1 = new ReentrantLock();
	public static ReentrantLock lock2 = new ReentrantLock();
	public int lock;
	
	public S2InitLock(int lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			if (lock == 1) {
				lock1.lockInterruptibly();
				try {
					Thread.sleep(500L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock2.lockInterruptibly();
			} else if (lock == 2) {
				lock2.lockInterruptibly();
				try {
					Thread.sleep(500L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock1.lockInterruptibly();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if(lock1.isHeldByCurrentThread()) 
				lock1.unlock();
			if(lock2.isHeldByCurrentThread())
				lock2.unlock();
			System.out.println(Thread.currentThread().getId() + ":Ïß³ÌÍË³ö!");
		}
	}
	
	public static void main(String[] args) {
		S2InitLock lock1 = new S2InitLock(1);
		S2InitLock lock2 = new S2InitLock(2);
		Thread t1 = new Thread(lock1);
		Thread t2 = new Thread(lock2);
		t1.start();
		t2.start();
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.interrupt();
		
	}

}
