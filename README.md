# 한글 자소 유틸

## 구현 기능

### 자소 분리

- 한글이 주어지면 자소 분리한 결과 반환
```java
String input = "안녕하세요!";
String result = HangulUtils.disassemble(input);
>>> "ㅇㅏㄴㄴㅕㅇㅎㅏㅅㅔㅇㅛ!"
```
- 한글과 영어가 합쳐진 문장 입력시 한글만 자소 분리

```java
String input = "안녕, Hello~";
String result = HangulUtils.disassemble(input);
>>> "ㅇㅏㄴㄴㅕㅇ, Hello~"
```


### 자소 합체

## Usage

- Java 11^

