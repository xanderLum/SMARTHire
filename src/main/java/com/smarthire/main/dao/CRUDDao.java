package com.smarthire.main.dao;

import java.util.List;

public interface CRUDDao<T> {
	public T create(T type) throws Exception;
	public T update(T type) throws Exception;
	public T merge(T type) throws Exception;
	public T read(Long id) throws Exception;
	public T delete(Long id) throws Exception;
	public List<T> getList() throws Exception;
}
