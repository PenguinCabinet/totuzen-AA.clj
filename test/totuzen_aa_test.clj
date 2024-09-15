(ns totuzen-aa-test
  (:require [clojure.test :refer :all]
            [totuzen-aa :refer :all]
            [clojure.string :as string]))

((deftest str-point-raw-test
   (testing "Context of the test assertions"
     (is (= (str-point-raw "a") 1))
     (is (= (str-point-raw "aa") 2))
     (is (= (str-point-raw "aaa") 3))
     (is (= (str-point-raw "aaaa") 4))
     (is (= (str-point-raw "aaaaa") 5))
     (is (= (str-point-raw "あ") 2))
     (is (= (str-point-raw "ああ") 4))
     (is (= (str-point-raw "あaあ") 5))
     (is (= (str-point-raw "あaあa") 6))
     (is (= (str-point-raw "あaあaあ") 8))
     (is (= (str-point-raw "あaあaあ") 8))
     (is (= (- (str-point-raw "aあaあa") (str-point-raw "a")) 6)))))

((deftest str-point-test
   (testing "Context of the test assertions"
     (is (= (str-point "a") 0))
     (is (= (str-point "aa") 0))
     (is (= (str-point "aaa") 1))
     (is (= (str-point "aaaa") 1))
     (is (= (str-point "aaaaa") 2))
     (is (= (str-point "あ") 0))
     (is (= (str-point "ああ") 1))
     (is (= (str-point "あaあ") 2))
     (is (= (str-point "あaあa") 2))
     (is (= (- (str-point "aあaあa") (str-point "a")) 3)))))

(deftest generate-top-test
  (testing "Context of the test assertions"
    (is (= (generate-top 0 default-option) "＿人人人＿"))
    (is (= (generate-top 1 default-option) "＿人人人人＿"))
    (is (= (generate-top 2 default-option) "＿人人人人人＿"))))

(deftest generate-bottom-test
  (testing "Context of the test assertions"
    (is (= (generate-bottom 0 default-option) "￣Y^Y￣"))
    (is (= (generate-bottom 1 default-option) "￣Y^Y^Y￣"))
    (is (= (generate-bottom 2 default-option) "￣Y^Y^Y^Y￣"))))

(deftest generate-body-test
  (testing "Context of the test assertions"
    (is (= (generate-body "a" (str-point "a") default-option) "＞　a　＜"))
    (is (= (generate-body "aa" (str-point "aa") default-option) "＞　aa　＜"))
    (is (= (generate-body "あ" (str-point "あ") default-option) "＞　あ　＜"))
    (is (= (generate-body "あa" (str-point "あa") default-option) "＞　あa　＜"))
    (is (= (generate-body "あaあ" (str-point "あaあ") default-option) "＞　あaあ　＜"))))

(def generate-test-testcase1 (string/join "\n"
                                          ["＿人人人人人人＿"
                                           "＞　aあaあa　＜"
                                           "￣Y^Y^Y^Y^Y￣"]))
(def generate-test-testcase2 (string/join "\n"
                                          ["＿人人人人人人＿"
                                           "＞　　 a 　　＜"
                                           "＞　aあaあa　＜"
                                           "＞　　aあa　　＜"
                                           "￣Y^Y^Y^Y^Y￣"]))

(def generate-test-testcase3 (string/join "\n"
                                          ["＿人人人人人人人＿"
                                           "＞　　 あa 　　＜"
                                           "＞　あああああ　＜"
                                           "＞　　 aaaa 　　＜"
                                           "＞　　あaあ　　＜"
                                           "￣Y^Y^Y^Y^Y^Y￣"]))

(deftest generate-test
  (testing "Context of the test assertions")
  (is (= (generate ["aあaあa"] default-option) generate-test-testcase1))
  (is (= (generate ["a" "aあaあa" "aあa"] default-option) generate-test-testcase2))
  (is (= (generate ["あa"
                    "あああああ"
                    "aaaa"
                    "あaあ"] default-option) generate-test-testcase3)))