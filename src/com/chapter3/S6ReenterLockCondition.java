package com.chapter3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class S6ReenterLockCondition implements Runnable {
	private static ReentrantLock lock = new ReentrantLock();
	private static Condition condition = lock.newCondition();
	
	@Override
	public void run() {
		try {
			lock.lock();
			condition.await();
			System.out.println("Thread is go on!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if(lock.isHeldByCurrentThread())
				lock.unlock();
		}
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new S6ReenterLockCondition());
		t1.start();
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.lock();
		condition.signal();
		lock.unlock();
		System.out.println("Main Thread is over");
	}
}
