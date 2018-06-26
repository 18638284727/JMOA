package com.jm.realm;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * 重写凭证匹配：
 * 继承 HashedCredentialsMatcher 重写 doCredentialsMatch 方法
 */
public class CountHashedCredentialsMatcher extends HashedCredentialsMatcher{

	private Cache<String, AtomicInteger> passwordRetryCache;  
	
	// 构造一个缓存管理在 bean 中注入缓存管理
	public CountHashedCredentialsMatcher(CacheManager cacheManager) {  
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");  
    } 
	
	@Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info)
	{
		// 获取登录用户名
		String username = (String) token.getPrincipal();  
        // 读取缓存是否存在该用户名(整数)
		AtomicInteger retryCount = passwordRetryCache.get(username); 
		// 如果返回 null 则表示缓存中没有,这是第一次,添加剂缓存
        if (retryCount == null) {  
            retryCount = new AtomicInteger(0);  
            passwordRetryCache.put(username, retryCount);  
        }  
        // 设置密码输入次数
        if (retryCount.incrementAndGet() > 5) {  
            // 编写逻辑
        }  
        // 如果认证成功,则调用 HashedCredentialsMatcher 的 doCredentialsMatch 继续向下认证,并删除缓存中的信息
        boolean matches = super.doCredentialsMatch(token, info);  
        if (matches) {  
            passwordRetryCache.remove(username);  
        }  
        return matches;
	}
	
}
