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

(deftest attack-test
  (let [kirk-attack 50
        gorn-defense 30
        gorn-health 100
        kirk (entity/create-entity "James T. Kirk", 0, 0, 50, kirk-attack, 20)
        gorn (entity/create-entity "Gorn Captain", 0, 0, gorn-health, 80, gorn-defense)
        expected-gorn (assoc gorn :health 80)
        actual-gorn (entity/attack kirk gorn)]
    (is (= expected-gorn actual-gorn))))

