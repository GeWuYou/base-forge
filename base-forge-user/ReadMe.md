#### **用户服务 (User Service)**

- **功能**: 用户注册、登录、角色分配、权限管理。
- **数据库**: 独立用户表、角色表、权限表。
- **通信方式**: 提供 REST API。
- 实现建议
    - 使用 Spring Security + OAuth2 实现认证授权。
    - 数据库推荐加密存储敏感信息（如密码）。