
plugins {
    id("com.gtnewhorizons.gtnhconvention")
}

// 覆盖默认产物名。
// GTNH 插件默认用 "${modId}-${modVersion}"（即 xreliquary-1.0.0），
// 但 ⚠️ modId 不能改（它决定存档里的物品 ID，改了会让旧存档物品全丢），
// 所以这里显式指定 jar 名前缀，产物为：
//   ReliquaryUnofficial-MC1.7.10-1.0.0.jar
base {
    archivesName.set("ReliquaryUnofficial-MC1.7.10")
}
