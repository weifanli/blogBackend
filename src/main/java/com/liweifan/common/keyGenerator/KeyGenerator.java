package com.liweifan.common.keyGenerator;

public interface KeyGenerator<T> {
	T getNext(String type);
	T getNext();
}
