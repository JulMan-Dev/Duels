package fr.minecraftjulman.duels.utils;

import java.util.Map.Entry;

import javax.annotation.Nullable;

import com.sun.istack.internal.NotNull;

/**
 * 
 * @author Juliano Mandrillon
 *
 * @param <K> Key Class
 * @param <V> Value Class
 */
public class EntryMaker<K, V> implements Entry<K, V> {
	private K key;
	private V value;
	
	/**
	 * 
	 */
	public EntryMaker(@NotNull K key, @Nullable V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V new_value) {
		V old = value;
		value = new_value;
		return old;
	}

	public Entry<K, V> toEntry() {
		return (Entry<K, V>) this;
	}
}
