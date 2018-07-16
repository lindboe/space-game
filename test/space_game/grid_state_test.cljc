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

(def test-minimal-grid
  (gs/initialize-grid 5 5 {[0 2] :enemy ;; [x y] :unitname
                           [3 4] :friendly}))

(deftest move-within-bounds?-test
  (testing "move is within grid bounds"
    (is (= true (gs/move-within-bounds? [0 0] test-minimal-grid))))
  (testing "move is outside grid bounds"
    (is (= false (gs/move-within-bounds? [5 0] test-minimal-grid)))
    (is (= false (gs/move-within-bounds? [0 5] test-minimal-grid)))))

(deftest move-to-unoccupied-square?-test
  (testing "move is to an unoccupied square"
    (is (= true (gs/move-to-unoccupied-square? [0 0] test-minimal-grid)))
  (testing "move is to an unoccupied square"
    (is (= false (gs/move-to-unoccupied-square? [0 2] test-minimal-grid))))))

(deftest valid-move?-test
  (testing "move is valid"
    (= true (gs/valid-move? [0 0] test-minimal-grid)))
  (testing "move is not valid"
    (= false (gs/valid-move? [0 2] test-minimal-grid))
    (= false (gs/valid-move? [5 0] test-minimal-grid))))
