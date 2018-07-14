(ns space-game.grid-state-test
  (:require [clojure.test :refer :all]
            [space-game.grid-state :as gs]))

(deftest test-grid-row-test
  (is (= '(0 0 0 0 0) gs/test-grid-row)))

(deftest test-grid-test
  (is (= gs/test-grid-row (first gs/test-grid)))
  (is (= 5 (count gs/test-grid))))

(deftest place-in-grid-test
  (let [new-grid (gs/place-in-grid gs/test-grid
                                   {:name :testperson
                                    :coordinates [2 4]})]
    (is (= :testperson (nth (nth new-grid 4) 2)))))
