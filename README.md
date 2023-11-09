# 한글 자소 유틸

## 구현 기능

### 자소 분리

- 입력한 문자열 중, 한글을 자소 분리

    ```java
    String input = "안녕하세요!";
    String result = HangulUtils.disassemble(input);
    >>> "ㅇㅏㄴㄴㅕㅇㅎㅏㅅㅔㅇㅛ!"
    ```

- 한글과 영어가 합쳐진 문장 입력시, 한글만 자소 분리

    ```java
    String input = "안녕, Hello~";
    String result = HangulUtils.disassemble(input);
    >>> "ㅇㅏㄴㄴㅕㅇ, Hello~"
    ```

- 2개의 자음으로 이루어진 쌍자음을 분리

    ```java
    String input = "닭인가, 달걀인가?";
    String result = HangulUtils.disassembleAll(input);
    >>> "ㄷㅏㄹㄱㅇㅣㄴㄱㅏ, ㄷㅏㄹㄱㅑㄹㅇㅣㄴㄱㅏ?"
    ```

### 포함 여부

- `text`가 `substring`을 포함하는지 여부를 반환합니다.

    ```java
    String text = "도우미";
    String substring = "도움";
    
    HangulUtils.contains(text, substring);
    >>> true
    ```

- 자음도 완전히 분리해서 포함 여부를 확인합니다.

    ```java
    String text = "달걀";
    String substring = "닭";
    
    HangulUtils.contains(text, substring);
    >>> true
    ```

## Installation

To install the library, you can use **Maven** or **Gradle** to import it.

Replace `${version}` with the desired version.

The current version is `0.1.0`.

### pom.xml

```xml
<dependency>
    <groupId>io.github.jaewon-pro</groupId>
    <artifactId>hangul4j</artifactId>
    <version>${version}</version>
</dependency>
```

### build.gradle

```groovy
dependencies {
    implementation 'io.github.jaewon-pro:hangul4j:${version}'
}
```

## Requirements

- Java 8^

## License

[MIT](https://www.mit.edu/~amini/LICENSE.md)

## To see more

Please let me know if there's any improvement or any bugs!

https://central.sonatype.com/artifact/io.github.jaewon-pro/hangul4j
