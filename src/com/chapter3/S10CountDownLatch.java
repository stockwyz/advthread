package com.chapter3;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class S10CountDownLatch implements Runnable {
	private static CountDownLatch count = new CountDownLatch(10);
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(new Random().nextInt(10) * 1000);
			count.countDown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Executor service = Executors.newFixedThreadPool(10);
		S10CountDownLatch c = new S10CountDownLatch();
		for(int i = 0; i < 10; i++) {
			service.execute(c);
		}
		try {
			count.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("fire");
	}

}
