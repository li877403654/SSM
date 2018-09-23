package com.design.framework.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * 缓存池
 * @author John
 * @see
 */
public class CachePool  {
	protected Logger logger = Logger.getLogger(getClass());
    private static CachePool instance;//缓存池唯一实例
    private static Map<String,Object> cacheItems;//缓存Map
      
    private CachePool(){
        cacheItems = new HashMap<String,Object>();
        new clearTThread().start();
    }
    /**
     * 得到唯一实例
     * @return
     */
    public synchronized static CachePool getInstance(){
        if(instance == null){
            instance = new CachePool();
        }
        return instance;
    }
    /**
     * 清除所有Item缓存
     */
    public synchronized void clearAllItems(){
        cacheItems.clear();
    }
    
    /**
     * 是否包含有这个key
     * @author JohnDeng
     * 2017年11月10日上午11:03:51
     * @param key
     * @return
     */
    public synchronized  boolean containKey(Object key){
    	 Set<String> keySet = cacheItems.keySet();
    	 for (Object keyName : keySet) {
    		 if(keyName.equals(key)){
    			 return true;
    		 }
    	 }
    	return false;
    }
    /**
     * 获取缓存实体
     * @param name
     * @return
     */
    public synchronized Object getCacheItem(String name){
        if(!cacheItems.containsKey(name)){
            return null;
        }
        CacheItem cacheItem = (CacheItem) cacheItems.get(name);
        if(cacheItem.isExpired()){
        	removeCacheItem(name);
        	return null;
        }
        return cacheItem.getEntity();
    }
    /**
     * 存放缓存信息
     * @param name
     * @param obj
     * @param expires
     */
    public synchronized void putCacheItem(String name,Object obj,long expires){
        if(!cacheItems.containsKey(name)){
            cacheItems.put(name, new CacheItem(obj, expires));
        }
        CacheItem cacheItem = (CacheItem) cacheItems.get(name);
        cacheItem.setCreateTime(new Date());
        cacheItem.setEntity(obj);
        cacheItem.setExpireTime(expires);
    }
    public synchronized void putCacheItem(String name,Object obj){
        putCacheItem(name,obj,-1);
    }
      
    /**
     * 移除缓存数据
     * @param name
     */
    public synchronized void removeCacheItem(String name){
        if(!cacheItems.containsKey(name)){
            return;
        }
        cacheItems.remove(name);
    }
      
    /**
     * 获取缓存数据的数量
     * @return
     */
    public int getSize(){
        return cacheItems.size();
    }
    
   private  class CacheItem {
	    private Date createTime = new Date();//创建缓存的时间
	    private long expireTime = 1;//缓存期满的时间  
	    private Object entity;//缓存的实体
	      
	    public CacheItem(Object obj,long expires){
	        this.entity = obj;
	        this.expireTime = expires;
	    }
	      
	    public boolean isExpired(){
	    	System.out.println("expireTime:"+expireTime);
	    	System.out.println("createTime:"+createTime.getTime());
	    	System.out.println("new:"+new Date().getTime());
	        return (expireTime!=-1 && new Date().getTime()-createTime.getTime() > expireTime);
	    }


		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public void setExpireTime(long expireTime) {
			this.expireTime = expireTime;
		}

		public Object getEntity() {
			return entity;
		}

		public void setEntity(Object entity) {
			this.entity = entity;
		}
		
	}
	   private class clearTThread extends Thread{
		   public clearTThread() {
			   logger.debug("\n===================start clearTThread===================");
			  
		   }
		   public void run() { 
			   logger.debug("\n===================cachepool size:"+ CachePool.getInstance().getSize()+"===================");
			   //System.out.println("通过Map.entrySet遍历key和value");
			   //System.out.println(getSize());
			   if(getSize()>0){
				   for (String key : cacheItems.keySet()) {
					   	//System.out.println("key= "+ key + " and value= " + cacheItems.get(key));
					   	CacheItem cacheItem = (CacheItem) cacheItems.get(key);
					   	if(cacheItem.isExpired()){
				        	removeCacheItem(key);
				        }
				   } 
			   }
		   } 
	   }
    
}
 	
 	
    
