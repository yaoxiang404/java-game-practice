# 🎮 游戏java练习（文字格斗游戏）

一个基于 **Java SE 控制台**的中文文字格斗游戏学习项目：支持账号注册/登录、角色创建、回合制战斗与成长系统。

## ✨ 功能特性

- **登录注册**：用户名 3~16 位（字母/数字，不能纯数字）、密码 3~8 位（字母+数字）、用户名唯一校验
- **角色系统**：玩家拥有生命值、攻击力、防御力与专属技能
- **回合制战斗**：每回合可选择 普通攻击 / 强力一击 / 生命汲取
- **4 种敌人**：初级战士、敏捷刺客、重装坦克、神秘法师，各有不同属性与技能机制（防御姿态、快速连击、火球术等）
- **成长系统**：每场胜利后回复生命值；每 3 场战斗属性提升；敌人属性随场次增强
- **循环挑战**：持续挑战直到被击败或主动退出

## 🧩 项目结构

```
TextFighting/
├── src/
│   ├── App.java                     # 程序入口（启动登录界面）
│   └── com/yaoxiang/
│       ├── domain/                  # 领域模型
│       │   ├── Character.java       # 角色基类（HP / 攻击 / 防御）
│       │   ├── HeroCharacter.java   # 玩家角色
│       │   ├── EnenyCharacter.java  # 敌人角色
│       │   └── User.java            # 用户（注册 / 登录）
│       └── ui/
│           ├── Login.java           # 登录注册界面
│           └── FightingGame.java    # 战斗主流程
│   └── com/yaoxiang1/test14/        # Java 小练习（Math / 随机数等，可独立运行）
├── TextFighting.iml
└── .gitignore
```

## 🚀 编译与运行

要求 **JDK 17+**（代码使用了 `switch` 箭头语法）。

> 源码中文为 GBK 编码，命令行编译时需指定 `-encoding GBK`。

```cmd
cd /d D:\IDEA\TextFighting

:: 编译
javac -encoding GBK -d out src/App.java src/com/yaoxiang/domain/*.java src/com/yaoxiang/ui/*.java

:: 运行
java -cp out App
```

在 IntelliJ IDEA 中直接运行 `App.main()` 即可。

## 🕹 玩法说明

1. 启动后进入登录界面：输入 `1` 登录 / `2` 注册 / `3` 退出
2. 注册时按提示输入用户名和密码（注意长度与字符限制）
3. 登录成功后进入战斗：
   - 每回合输入数字选择行动：普通攻击 / 强力一击 / 生命汲取
   - 击败敌人后回复生命值并累计胜场
   - 每 3 场战斗属性提升，敌人也越来越强
4. 被击败后游戏结束

## 📚 学习要点

- Java 面向对象：继承（Character → HeroCharacter / EnenyCharacter）、封装（User）
- 集合框架：`ArrayList` 管理用户与敌人
- 控制台 IO：`Scanner` 交互
- 随机数：`Random` 控制战斗掉落与敌人随机出现

## 📄 说明

个人 Java 学习练习项目，代码仅供学习交流。