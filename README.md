# Reliquary 1.7.10 — Unofficial Fork

> **This is an unofficial fork.** It is **not** affiliated with or endorsed by the original authors.
>
> ⚠️ **Requires [UniMixins](https://github.com/LegacyModdingMC/UniMixins) ≥ 0.2.1** to be installed.
>
> 中文说明见 **[README_zh_CN.md](README_zh_CN.md)**

---

## Upstream

| Item | Detail |
|---|---|
| Original mod | **Reliquary** — by x3n0ph0b3, later maintained by TheMike |
| Forked from | the **1.7.10 branch** of `P3pp3rF1y/Reliquary` |
| **Based on upstream version** | **1.2.1.483** |
| **This fork's own version** | **1.0.0** (independent numbering) |
| Upstream license | **GPLv3** (see `COPYING.txt`) |
| **This fork's source code** | **https://github.com/LuckyHl/ReliquaryUnofficial** |

This project is a rebuild of the 1.7.10 branch for **Minecraft 1.7.10 + Forge 10.13.4.1614**,
using the GTNH ExampleMod template.

---

## Modifications

**Date of modification: 2026-09-24** *(the "relevant date" required by GPLv3 §5(a))*

### Added

- **Two Mixins** (`MixinEntityPlayer` / `MixinItemRenderer`) fixing the
  "item NBT sync interrupting right-click use state" issue — which manifests as
  the block pose repeatedly breaking, abilities working intermittently, and position jitter.
- **`hold_to_use` config option** (default `true`) for Rending Gale, Coin of Fortune,
  Pyromancer's Staff and Serpent Staff — hold right-click to keep using, instead of the
  use state expiring periodically.
- **Mercy Cross config options** (3): `undead_damage` (default `24`),
  `normal_damage` (default `12`), `show_particles` (default `true`).
  ⚠️ **The default damage is intentionally higher than upstream**
  (24 / 12 vs. upstream's hardcoded 12 / 6) — the weapon felt underpowered.
  Both values are fully configurable in `xreliquary.cfg`.
- **1.7.10 rebuild project** (GTNH ExampleMod template, Kotlin DSL, RFG).

### Fixed

*(These are issues present in the upstream 1.7.10 code.)*

- **Right-click use state being interrupted** by frequent item NBT changes.
- **Pyromancer's Staff**: holding right-click could not fire fireballs continuously —
  only the first fireball was real, subsequent ones were client-side "ghost" fireballs.
- **Mercy Cross**: attacking any mob never consumed durability
  (it only wore down when mining blocks), making it effectively unbreakable.
- **Mercy Cross**: enchantments (Sharpness / Smite) did not increase its damage —
  the fixed damage value completely drowned out the enchantment bonus.

### Changed

- **Modified the Chinese translation** (`zh_CN.lang`).
- **Pyromancer's Staff**: material absorption now runs **server-side only**
  (upstream runs it on both sides, which can desync the stored material count
  between client and server).
- Removed leftover `__OBFID` fields from the build output
  (required by the modern reobfuscation toolchain; these fields were dead code).

### Not modified

- All original upstream behavior, assets and translations are preserved.
- **No code from the 1.21 branch is included** (that branch is ARR since 2025-02).

---

## License

Licensed under the **GNU General Public License v3.0** — the same license as upstream.
The full license text is in **`COPYING.txt`**.

> **Exception**: the book-opening sound `book.ogg` was created by **SmartWentCody**
> (freesound.org) and is licensed under **CC BY 3.0**.
> Attribution and license details are at the top of `COPYING.txt`.

---

## Credits

- **Original author**: x3n0ph0b3
- **Upstream maintainer**: TheMike
- **1.7.10 rebuild & fixes**: lkust, 2026

> Author information (x3n0ph0b3 / TheMike) is taken from the upstream
> **1.7.10 branch's `mcmod.info`**.

---

## Copyright notices retained

*(GPLv3 §5(b) — all copyright / attribution notices from upstream are kept.)*

| Asset / file | Notice |
|---|---|
| Original mod code | `Author: x3n0ph0b3, TheMike` — retained in `src/main/resources/mcmod.info` (`authorList` / `credits`) |
| `book.ogg` (book-opening sound) | **SmartWentCody** (freesound.org), licensed **CC BY 3.0** — notice retained at the top of `COPYING.txt` / `LICENSE` |
| Bundled Sandstone (`lib/enderwizards/sandstone/`) | Copyright of its original authors — files are redistributed unmodified |
| License text | GNU GPL v3.0 — `COPYING.txt` / `LICENSE` |

> The mod's display name was changed to **"Reliquary Unofficial"** only to
> distinguish this fork from the original; the **mod id remains `xreliquary`** and
> no original author attribution was removed or replaced.

---

## Installation

1. Install **Minecraft 1.7.10** + **Forge 10.13.4.1614**.
2. Drop `build/libs/ReliquaryUnofficial-MC1.7.10-1.0.0.jar` into your `mods/` folder.
   - ⚠️ Do **not** use the `-dev` jar — that one is for the development environment only.
   - ℹ️ The internal mod id is still `xreliquary` (kept for **save-file compatibility**),
     so the jar name and the internal mod id intentionally differ.
3. **⚠️ Install [UniMixins](https://github.com/LegacyModdingMC/UniMixins) ≥ 0.2.1 first** —
   this mod uses Mixins, but the jar does **not** bundle the Mixin framework.
   ⚠️ **Without UniMixins the game will fail to launch — and you will see NO error
   message**: the game window simply never appears (the crash happens in the
   `launchwrapper` phase, *before* Forge's mod loading, so no "missing dependency"
   prompt is shown).
   **Please double-check that UniMixins is in your `mods/` folder.**
4. **Sandstone is bundled** (`lib/enderwizards/sandstone/`, 53 classes) —
   you do **not** need to install LibSandstone separately
   (the `libsandstone@[1.0.0,)` entry in `mcmod.info` is satisfied by the bundled copy,
   which registers the `libsandstone` mod id itself).
