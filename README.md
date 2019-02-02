# 爬塔小猴MOD
------------
## 簡介

這是一個遊戲「Slay the Spire」的角色模組，目前含有：
* 新角色「爬塔小猴」
* 17張專屬卡牌
* 2個專屬遺物


## 相依資源

打包本MOD時請先準備以下.jar檔：
1. [ModTheSpire.jar](https://github.com/kiooeht/ModTheSpire)
2. [BaseMod.jar](https://github.com/daviscook477/BaseMod)
3. desktop-1.0.jar(在你的Slay the Spire安裝目錄下)

前二項也可以直接在Steam Workshop上取得。


## 模組打包

本MOD使用[maven](https://maven.apache.org/index.html)打包成.jar檔，你也可以使用其他工具。

若要使用maven，直接在模組根目錄底下執行`mvn package`。

請注意相依資源的路徑是否正確，寫在pom.xml以下部分：

	<dependency>
        <groupId>basemod</groupId>
        <artifactId>basemod</artifactId>
        <version>5.8.0</version>
        <scope>system</scope>
        <systemPath>${basedir}/../lib/BaseMod.jar</systemPath>
    </dependency>

輸出路徑在以下部分：

	<target>
        <copy file="target/CMMod.jar" tofile="../new_mods/CMMod.jar"/>
		<copy file="target/CMMod.jar"
			tofile="D:/Steam/steamapps/common/SlayTheSpire/mods/CMMod.jar"/>
    </target>

## 模組使用

請參照ModTheSpire的說明。
