(ns totuzen-aa
  (:require [clojure.string :as string]))

(def default-option
  {:top-corner "＿" :top-edge "人"
   :bottom-corner "￣" :bottom-edge1 "Y" :bottom-edge2 "^"
   :body-corner-left "＞" :body-corner-right "＜"
   :em-space "　" :space " "})

(defn str-point-raw [v]
  (->> v seq (map str)
       (map #(if (= (alength (.getBytes % "UTF-8")) 1) 1 2))
       (reduce +)))

(defn str-point [v]
  (quot (- (str-point-raw v) 1) 2))

(defn generate-top [width-point option]
  (let [{top-corner :top-corner top-edge :top-edge} option]
    (str top-corner
         (apply str (repeat (+ width-point 3) top-edge))
         top-corner)))

(defn generate-bottom [width-point option]
  (let [{bottom-corner :bottom-corner bottom-edge1 :bottom-edge1 bottom-edge2 :bottom-edge2} option]
    (str bottom-corner
         (string/join bottom-edge2 (repeat (+ width-point 2) bottom-edge1))
         bottom-corner)))

(defn generate-body [body-text width-point option]
  (let [{body-corner-left :body-corner-left body-corner-right :body-corner-right em-space :em-space space :space} option
        body-width-point (str-point body-text)
        diff-point (- width-point body-width-point)]
    (str body-corner-left
         em-space
         (apply str (repeat (quot diff-point 2) em-space))
         (apply str (repeat (mod diff-point 2) space))
         body-text
         (apply str (repeat (mod diff-point 2) space))
         (apply str (repeat (quot diff-point 2) em-space))
         em-space
         body-corner-right)))

(defn generate [text-list option]
  (let [width-point (apply max (map str-point text-list))]
    (->> (list (generate-bottom width-point option))
         (concat (map #(generate-body %1 width-point option) text-list))
         (cons (generate-top width-point option))
         (string/join "\n"))))

