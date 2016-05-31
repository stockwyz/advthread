package com.chapter3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class S8Semaphore implements Runnable {
	private Semaphore s = new Semaphore(5);
	@Override
	public void run() {
		try {
			s.acquire();
			Thread.sleep(2000L);
			System.out.println(Thread.currentThread().getName() + " is done!");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			s.release();
		}
	}
	private void release() {
		s.release();
	}
	
	private int drainPermits() {
		return s.drainPermits();
	}
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(20);
		S8Semaphore s = new S8Semaphore();
//		for(int i = 0; i < 20; i++) {
//			s.release();
//		}
//		System.out.println(s.drainPermits());
		for(int i = 1; i <= 20; i++) {
			service.submit(s);
		}
	}
}
