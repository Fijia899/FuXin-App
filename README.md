# 抚心 Android

抚心是一款面向情绪疗愈、关系修复与失恋戒断场景的 Android 应用。

## 当前功能

- 三个支持方向：自我情感疗愈、寻求复合机会、戒断
- 关系自诊与模块推荐
- 情绪记录、冲动延迟、不发送保管箱和消息风险检查等工具入口
- 本机记录页面与游客模式
- Kotlin + Jetpack Compose + Material 3
- 后端 API 客户端骨架，默认连接 Android 模拟器的本地服务

## 开发环境

- Android Studio
- JDK 11+
- Android SDK 37
- 最低支持 Android 8.0（API 26）

## 构建与测试

```powershell
./gradlew test
./gradlew assembleDebug
```

本地 SDK 路径请写入未纳入版本控制的 `local.properties`。应用中的 API 地址目前默认为 `http://10.0.2.2:3000`，用于 Android 模拟器访问本机后端。

## 隐私说明

仓库不包含本机配置、签名文件、构建产物、IDE 缓存或任何密钥。正式发布前还需要补充生产环境地址、签名配置和隐私合规材料。
