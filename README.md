# 抚心 Android

基于 Kotlin、Jetpack Compose 和 Material 3 构建的情绪支持 App 原型：

> 3 个支持方向 + 26 个工具入口 + 关系自诊分流 + 本地数据与后端 API 接入层

## 功能概览

| 支持方向 | 工具入口 |
| --- | --- |
| 自我情感疗愈 | AI 语言陪聊、情绪落点、疗愈日记、情绪急救、事实与猜测、自我价值修复、生活恢复计划、支持者准备 |
| 寻求复合机会 | 复合动机澄清、关系机会评估、关系事实卡、沟通目标选择、AI 消息草稿、消息风险检查、72 小时冷却、沟通结果记录、拒绝与边界处理 |
| 戒断 | 戒断目标、触发源识别、冲动延迟、冲动记录、不发送保管箱、替代行动、必要联系模式、复发复盘、每日戒断计划 |

首页提供关系自诊入口，根据选择跳转到相应支持方向。底部导航包含工具、记录和我的空间；工具页面包含单选项、文本输入和保存反馈。

## 当前实现

- **界面与导航**：已实现首页、三类工具目录、关系自诊分流、工具详情和游客模式页面。
- **交互原型**：26 个工具入口可打开详情页；详情页可选择选项、输入文本并显示保存状态。
- **本地数据层**：`FuxinLocalStore` 提供情绪值、草稿和日记的 SharedPreferences 读写方法。工具页目前使用页面状态显示保存反馈，记录页显示空状态。
- **后端接入层**：`FuxinApiClient` 定义游客会话、案例创建、自诊提交和报告读取请求。客户端尚未连接页面；运行这些请求需要匹配的本地后端服务。

## 开发环境

- Android Studio
- JDK 17+（Android Gradle Plugin 9.3.2 的运行要求；Java 源码编译目标为 11）
- Android SDK Platform 37
- 最低支持 Android 8.0（API 26）

## 构建与测试

Windows PowerShell：

```powershell
.\gradlew.bat test
.\gradlew.bat assembleDebug
```

macOS / Linux：

```bash
./gradlew test
./gradlew assembleDebug
```

Debug APK 输出位置：`app/build/outputs/apk/debug/app-debug.apk`。

## 运行 App

1. 使用 Android Studio 打开项目根目录。
2. 安装 Android SDK Platform 37，并将 Gradle JDK 设为 17 或更高版本。
3. 创建 Android 8.0（API 26）或更高版本的模拟器，或连接 Android 设备。
4. 选择 `app` 运行配置并点击 Run。

模拟器 API 地址默认为 `http://10.0.2.2:3000`，用于访问开发电脑上的本地服务。真机调试时，可将 `FuxinApiClient.DEFAULT_BASE_URL` 改为开发电脑在局域网中的地址。

## 页面使用流程

1. 首页浏览三个支持方向，或进入关系自诊并选择当前需求。
2. 打开一个工具，选择选项或填写文本。
3. 点击“保存这次记录”，页面显示保存反馈。
4. 使用底部导航切换工具、记录和我的空间。

## API 接口

客户端预留以下本地服务接口：

| 方法 | 路径 | 用途 |
| --- | --- | --- |
| `POST` | `/api/v1/guest/session` | 创建游客会话，读取 `token` 和 `expiresIn` |
| `POST` | `/api/v1/cases` | 创建案例，使用 Bearer Token 鉴权 |
| `POST` | `/api/v1/cases/{caseId}/diagnosis` | 提交自诊答案 |
| `POST` | `/api/v1/cases/{caseId}/inputs` | 提交补充文本并读取分析报告 |

报告包含阶段、置信度、事实、风险、不确定性和说明字段。后端服务需单独启动，并实现以上接口。

## 项目结构

```text
├── app/src/main/java/com/fuxin/app/
│   ├── MainActivity.kt                 # Compose 页面与工具目录
│   ├── data/local/FuxinLocalStore.kt   # SharedPreferences 数据读写
│   ├── data/remote/FuxinApiClient.kt   # HTTP API 客户端
│   ├── data/model/FuxinModels.kt       # 会话、案例与报告模型
│   └── ui/theme/                       # 颜色、字体与主题
├── app/src/main/res/                   # Android 资源和应用清单
├── app/src/test/                       # JVM 单元测试
├── app/src/androidTest/                # Android 仪器测试
├── gradle/libs.versions.toml           # 插件与依赖版本
└── gradlew / gradlew.bat               # Gradle Wrapper
```

## 隐私与产品说明

- App 以游客模式展示，当前界面不要求登录。
- 应用不读取第三方平台内容，也不会自动发送消息。
- 情绪支持功能用于自助记录与反思，不提供医疗诊断或心理治疗。
- 本地 SDK 路径写入 `local.properties`；该文件已加入 Git 忽略规则。
