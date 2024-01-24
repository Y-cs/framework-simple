# Galaxy

## Code Style

Galaxy的编码规范遵从于《阿里巴巴JAVA开发规约》和员工之间制定的代码风格文件。

### Guidelines

[Alibaba-Java-Coding-Guidelines](https://alibaba.github.io/Alibaba-Java-Coding-Guidelines/)

[Java开发手册(黄山版).pdf](https://github.com/alibaba/p3c/blob/master/Java%E5%BC%80%E5%8F%91%E6%89%8B%E5%86%8C(%E9%BB%84%E5%B1%B1%E7%89%88).pdf)

## Galaxy Code Style File

### Idea IDE

Galaxy代码风格文件在源代码下的`style/galaxy-code-style-for-idea.xml`文件中，开发者可以将其导入到Idea
IDE中，并让IDE帮助您格式化代码。

#### Import Way/导入方式

```
Preferences/Settings --> Editor --> Code Style --> Schema --> Import Schema --> IntelliJ IDEA code style XML
```

## IDE Plugin

*不是必须安装，如果你需要在开发的时候实时发现问题的话，你需要安装。*

### idea IDE

#### p3c

*由于官方版本支持太差,所以选择第三方维护版本*

[Alibaba Java Coding Guidelines(XenoAmess TPM)](https://plugins.jetbrains.com/plugin/14109-alibaba-java-coding-guidelines-xenoamess-tpm-)

#### checkstyle

[checkstyle插件idea安装](https://plugins.jetbrains.com/plugin/1065-checkstyle-idea)

1. `Preferences/Settings --> Other Settings --> Checkstyle`
   或者 `Preferences/Settings --> Tools --> Checkstyle`
2. 在checkstyle插件中设置checkstyle版本至少为8.30,并将扫描作用域设置为`All resource(including tests)`
3. 导入源代码下`style/GalaxyCheckStyle.xml`文件到checkstyle插件。
4. 用checkstyle插件扫描你修改的代码。

