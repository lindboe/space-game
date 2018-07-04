(ns space-game.grid-test
  (:require [clojure.test :refer :all]
            [space-game.grid :as grid]))

(deftest shift-line-x-test
  (let [base-line {:x1 0 :x2 0 :y1 0 :y2 0}
        expected-line {:x1 5 :x2 5 :y1 0 :y2 0}]
    (is (= expected-line (grid/shift-line-x base-line 5)))))
