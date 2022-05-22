package com.bycw.database;


import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//导入Jedis常用的类
import redis.clients.jedis.*;

public class RedisCluster {
	Logger m_logger=LoggerFactory.getLogger(RedisCluster.class);
	//private Jedis m_jedisclient=null;
	
	//集群模式 和 Jedis 使用差不多
	private JedisCluster m_jedisclient=null;
	
	
	public void Init() {	
		//链接redis服务器
		//this.m_jedisclient=new Jedis("127.0.1",6379);
		
		m_jedisclient=new JedisCluster(new HostAndPort("127.0.1", 6379));
	}
	
	
	public void TestRedis() {
		//发送red is命令给服务器 来操作red is
		//end
		
		
		//作用1 memcach  把数据放到缓存内查询的时候 更快 分掉数据的压力
		//key:value  //用户信息  用户昵称  用户性别 用户年龄
		//哈希表  key: value -->[用户信息  用户昵称  用户性别 用户年龄]
		Map<String, String> map=new HashMap<String, String>();
		map.put("uname", "gerson");
		map.put("upwd", "1234567");
		map.put("uage", "18");
		//将整个用户数据map存到一个用户ID里面
		this.m_jedisclient.hmset("4301221984", map);
		//修改某个值
		this.m_jedisclient.hset("4301221984", "uname","Gerson");
		//获取某个值
		String nameString= this.m_jedisclient.hget("4301221984", "uname");
		//获取多个值
		List<String> values= this.m_jedisclient.hmget("4301221984", "uname","upwd","uage");
		
		
		//作用2 排行榜  世界排行榜 好友排行榜 有序集合--》 值 权重
		//worldrank 4301221984 100 4301221985 200 4301221986 300
		this.m_jedisclient.zincrby("worldRank", 1000, "4301221984");
		this.m_jedisclient.zincrby("worldRank", 2000, "4301221985");
		this.m_jedisclient.zincrby("worldRank", 3000, "4301221986");
		
		//从小到大排
		Set<Tuple> rSet= this.m_jedisclient.zrangeWithScores("worldRank", 0, 50);
		for(Tuple ts: rSet) {
			//getElement 4301221986    getScore 3000
			m_logger.info(ts.getElement()+":"+ts.getScore());
		}
		//从大到小排
		this.m_jedisclient.zrevrangeWithScores("worldRank", 0, 50);
		
		//作用3 事件订阅和发布
		
	}
	
	
	public  void Destory() {
		try {
			this.m_jedisclient.close();
			this.m_jedisclient=null;
		} catch (Exception e) {
			// TODO: handle exception
			m_logger.error(e.getMessage());
		}

		
	}
	
	
}
