(ns space-game.entity-test
  (:require [clojure.test :refer :all]
            [space-game.entity :as entity]))

(deftest create-entity-test
  (let [name "Kathryn Janeway"
        posX 10
        posY 20
        health 50
        attack 10
        defense 10
        janeway (entity/create-entity name posX posY health attack defense)]
    (is (= name (:name janeway)))
    (is (= {:x posX :y posY} (:pos janeway)))
    (is (= health (:health janeway)))
    (is (= attack (:attack janeway)))
    (is (= defense (:defense janeway)))))

