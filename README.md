# kalk

A CLI calculator written in Kotlin.

### Notable features

- This program implements the [shunting-yard](https://en.wikipedia.org/wiki/Shunting-yard_algorithm) in Kotlin as an extension method on `String` - see [String.toRPNExpression()](https://github.com/zachgrayio/kalk/blob/master/src/main/kotlin/io/zachgray/kalk/math/ext/String.kt). 
- Also implemented is an [RPN expression evaluator](https://github.com/zachgrayio/kalk/blob/master/src/main/kotlin/io/zachgray/kalk/math/RPNExpression.kt) which is fairly succinct thanks to the use of the `Operator` sealed class which can be found [here](https://github.com/zachgrayio/kalk/blob/master/src/main/kotlin/io/zachgray/kalk/math/Operator.kt). 

### Usage

- `$ kalk` for interactive mode
- `$ kalk 2 + 2` to verify two plus two is in fact four!

### Installation

- Install with NPM: `$ npm i -g kalk`

### Build & run from source

- `$ git clone git@github.com:zachgrayio/kalk.git`
- `$ cd kalk`
- `$ ./gradlew assembleDist`
- `$ unzip build/distributions/kalk-1.1.0.zip`
- inline: `$ kalk-1.1.0/bin/kalk 2 + 2`
- interactive: `$ kalk-1.1.0/bin/kalk`
