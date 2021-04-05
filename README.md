# wlhse


```
 __      __.____     ___ ___  ____________________
/  \    /  \    |   /   |   \/   _____/\_   _____/
\   \/\/   /    |  /    ~    \_____  \  |    __)_ 
 \        /|    |__\    Y    /        \ |        \
  \__/\  / |_______ \___|_  /_______  //_______  /
       \/          \/     \/        \/         \/
```


#### 介绍
该项目使用SSM（spring + spring mvc + mybatis）作为开发框架的后端项目，通过Json与前端进行交互。
同时使用到Mysql作为关系型数据库；使用redis做缓存；利用websocket进行服务端消息推送；
利用activeMQ进行消息的存储，已备高并发时消息的丢失；同时使用Maven构建项目。

#### 配置环境
1. jdk1.8
2. Tomcat8
3. MySql5.7
4. Maven
5. Redis NoSQL缓存
6. ActiveMQ 消息中间件

#### 参与贡献（排名不分先后）

1. two二贰2B
2. 习文风
3. shiAMao
4. bettyMei
5. ThomasYuan
6. wmq
7. 101111
8. 李家成
9. Minibear

#### 关于重构（写给未来的开发者）
必须重构的地方
1. 使用Spring AOP对所有数据库操作添加事务（现在不足以保证并发情况下对数据库的操作原子性）
2. service层代码耦合度太高，代码重用率太低，不便于维护。（抽象相同代码）
3. service取消所有捕获异常，统一由@ControllerAdvice捕获异常
4. NewStringRetrun返回json方法过于复杂，重构为R（来源renrenfast）。（可以取消使用FastJson）
5. service中if/else嵌套复杂，不适用嵌套，使用捕获异常，只有成功才return，其余抛出异常(使用WLHSEexception)
6. CodeDict使用enmu进行重构，建议重构后成功代表1000，失败代表2000，不需要太多状态。

```
重构前：
    if()
    {
        if()
            {
                
            }
        else
            {
    
            }
    }
    else{
        if()
            {
    
            }
        else{
            
        }
    }
重构后：
    if()
        throw new WLHSEexception()
    if()
        throw new WLHSEexception()
    retuen result;
```


建议重构地方
1. 可以将整体框架重构为spring boot
2. 导入excel可以根据实际业务更新为多线程执行
3. 关于setter/getter可以使用lombok(建议)。整洁代码
4. 关于鉴权可以考虑使用shiro
5. 使用@Util代替@Compent

#### 补充说明
1. 项目目前只是一个V1版本，我们深知不少值得优化和改进的地方，希望大家能不吝提出宝贵的意见，谢谢！
2. SpringBoot正在重构中：https://gitee.com/gang_bryant/WLHSE-SpringBoot
