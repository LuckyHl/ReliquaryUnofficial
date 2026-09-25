# Reliquary 1.7.10 — 非官方分支

> **这是一个非官方分支（unofficial fork）。** 与原作者无关，也未经其背书。
>
> ⚠️ **需要安装 [UniMixins](https://github.com/LegacyModdingMC/UniMixins) ≥ 0.2.1**。
>
> English version: **[README.md](README.md)**

---

## 上游来源

| 项目            | 说明                                         |
| ------------- | ------------------------------------------ |
| 原始模组          | **Reliquary** — 作者 x3n0ph0b3，后续维护者 TheMike |
| 分支来源          | `P3pp3rF1y/Reliquary` 的 **1.7.10 分支**      |
| **基于的上游版本**   | **1.2.1.483**                              |
| **本分支自己的版本号** | **1.0.0**（独立编号，不沿用上游）                      |
| 上游协议          | **GPLv3**（见 `COPYING.txt`）                 |
| **本分支源码仓库**   | **https://github.com/LuckyHl/ReliquaryUnofficial** |

本工程是在 **Minecraft 1.7.10 + Forge 10.13.4.1614** 环境下，  
用 GTNH ExampleMod 模板重建的 1.7.10 分支。

---

## 修改内容

**修改日期：2026-09-24**（GPLv3 §5(a) 要求的相关日期）

### 新增

- **两个 Mixin**（`MixinEntityPlayer` / `MixinItemRenderer`），修复"物品 NBT 频繁变化  
  打断右键使用状态"的问题 —— 表现为格挡姿势反复中断、功能时灵时不灵、位置抖动。
- **`hold_to_use` 配置项**（默认 `true`）：裂风者 / 幸运硬币 / 火占师之杖 / 蛇杖  
  —— 按住右键持续使用，不再因"使用时长到期"而周期性中断。
- **怜悯之十字架配置项**（3 项）：`undead_damage`（默认 `24`）、  
  `normal_damage`（默认 `12`）、`show_particles`（默认 `true`）。  
  ⚠️ **默认伤害有意高于上游**（24 / 12，上游是硬编码的 12 / 6）——  
  原版手感偏弱。两个数值都可在 `xreliquary.cfg` 里调整。
- **1.7.10 环境下的重建工程**（GTNH ExampleMod 模板、Kotlin DSL、RFG）。

### 修复

*（以下均为上游 1.7.10 代码中原本存在的问题。）*

- **右键使用状态被频繁的 NBT 变化打断**。
- **火占师之杖**：按住右键无法持续发射真火球 —— 只有第一个火球是真实的，  
  后续都是客户端单方面的"幽灵火球"。
- **怜悯之十字架**：攻击任何生物都不消耗耐久（只有挖方块才掉耐久），  
  导致它实际上近乎无限耐久。
- **怜悯之十字架**：附魔（锋利 / 亡灵杀手）无法增加它的伤害 ——  
  固定伤害值把附魔加成完全覆盖了。

### 变更

- **修改了中文翻译**（`zh_CN.lang`）。
- **火占师之杖**：材料吸收改为**只在服务端执行**  
  （上游是双端各自执行，可能导致客户端与服务端存储的材料数量不一致）。
- 从构建产物中清除了遗留的 `__OBFID` 字段  
  （现代重混淆工具链明确禁止；这些字段是死代码）。

### 未改动

- 上游 1.7.10 分支的原有行为、资源、翻译均完整保留。
- **未包含 1.21 分支的任何代码**（该分支自 2025-02 起为 ARR 协议）。

---

## 协议

本作品以 **GNU General Public License v3.0** 发布 —— 与上游一致。  
完整协议文本见 **`COPYING.txt`**。

> **例外**：翻书音效 `book.ogg` 由 **SmartWentCody**（freesound.org）创作，  
> 以 **CC BY 3.0** 授权。署名与许可说明位于 `COPYING.txt` 开头。

---

## 致谢

- **原作者**：x3n0ph0b3
- **上游维护者**：TheMike
- **1.7.10 分支重建与修复**：lkust，2026

> 作者信息（x3n0ph0b3 / TheMike）来自上游 **1.7.10 分支的 `mcmod.info`**。

---

## 保留的版权声明

*（GPLv3 §5(b) —— 上游的全部版权 / 署名声明均已保留。）*

| 资源 / 文件 | 声明 |
|---|---|
| 原始模组代码 | `作者：x3n0ph0b3, TheMike` —— 保留在 `src/main/resources/mcmod.info`（`authorList` / `credits`） |
| `book.ogg`（翻书音效） | **SmartWentCody**（freesound.org），**CC BY 3.0** 授权 —— 署名保留在 `COPYING.txt` / `LICENSE` 开头 |
| 内嵌 Sandstone（`lib/enderwizards/sandstone/`） | 版权归其原作者，文件按原样重分发，未作修改 |
| 协议文本 | GNU GPL v3.0 —— `COPYING.txt` / `LICENSE` |

> 模组显示名改为 **"Reliquary Unofficial"** 仅用于区分本分支与原版；
> **mod id 仍为 `xreliquary`**，未删除或替换任何原作者署名。

---

## 安装

1. 安装 **Minecraft 1.7.10** + **Forge 10.13.4.1614**。
2. 把 `build/libs/ReliquaryUnofficial-MC1.7.10-1.0.0.jar` 放入 `mods/` 目录。
   - ⚠️ **不要**使用带 `-dev` 后缀的 jar —— 那个仅用于开发环境。
   - ℹ️ 内部 mod id 仍是 `xreliquary`（为**存档兼容**保留），  
     所以 jar 名与内部 mod id **故意不同**。
3. **⚠️ 需要先安装 [UniMixins](https://github.com/LegacyModdingMC/UniMixins) ≥ 0.2.1** ——  
   本模组使用了 Mixin，但 jar 里**没有**打包 Mixin 框架。  
   ⚠️ **缺少 UniMixins 会导致游戏无法启动，而且不会出现任何错误提示** ——
   游戏窗口根本不会出现（崩溃发生在 `launchwrapper` 阶段，**早于** Forge 的 mod 加载，
   所以不会有"缺少前置模组"之类的提示）。
   **请务必确认 `mods/` 目录里已放入 UniMixins。**
4. **Sandstone 已内嵌**（`lib/enderwizards/sandstone/`，共 53 个 class）——  
   **不需要**单独安装 LibSandstone  
   （`mcmod.info` 里的 `libsandstone@[1.0.0,)` 由内嵌副本满足，  
   它自己注册了 `libsandstone` 这个 mod id）。
