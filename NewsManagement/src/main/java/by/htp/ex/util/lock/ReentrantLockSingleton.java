package by.htp.ex.util.lock;

import java.util.concurrent.locks.ReentrantLock;

public final class ReentrantLockSingleton {
	private static ReentrantLock instance = new ReentrantLock();

	private ReentrantLockSingleton() {
	}

	public static ReentrantLock getInstance() {
		return instance;
	}

}
