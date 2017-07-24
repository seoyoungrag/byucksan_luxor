package com.sds.acube.luxor.framework.cache;

public interface MemoryCache
{
    public void clear();
    public int size();
    public void remove(Object key);
}
