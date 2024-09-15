<div align="center">

# 💥 totuzen-AA.clj

```
＿人人人人人人人人人人人人人人人人人人人＿
＞　突然のAAを生成するClojure Library　＜
＞　　　　　　　　 です 　　　　　　　　＜
￣Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y￣
```


<br>
<br>
</div>

## 🔨 使い方
deps.ednに追記
```clojure
:deps {io.github.PenguinCabinet/totuzen-AA.clj {:git/tag "v0.0.1" }}
```

## サンプル

```clojure
(require '[totuzen-aa :as t-aa])

(print (t-aa/generate ["Hello World!" "ハローワールド!"] t-aa/default-option))
```

```
＿人人人人人人人人人人＿
＞　　Hello World!　　＜
＞　ハローワールド!　＜
￣Y^Y^Y^Y^Y^Y^Y^Y^Y￣
```

### テスト
```bash
clojure -M:test
```

## 🎫 LICENSE

[MIT](./LICENSE)

## ✍ Author

[PenguinCabinet](https://github.com/PenguinCabinet)
