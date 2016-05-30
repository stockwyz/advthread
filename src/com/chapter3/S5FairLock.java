package com.chapter3;

import java.util.concurrent.locks.ReentrantLock;

public class S5FairLock implements Runnable {
	public static ReentrantLock lock = new ReentrantLock(true);

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				lock.lock();
				System.out.println(Thread.currentThread().getName() + "gain lock");
			} finally {
				lock.unlock();
			}
		}

	}
	
	public static void main(String args[]) {
		Thread t1 = new Thread(new S5FairLock());
		Thread t2 = new Thread(new S5FairLock());
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
