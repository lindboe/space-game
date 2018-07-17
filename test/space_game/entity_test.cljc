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
  "Ensure attacks work as expected"
  (let [gorn-health 100
        base-kirk (entity/create-entity "James T. Kirk", 0, 0, 50, 0, 20)
        base-gorn (entity/create-entity "Gorn Captain", 0, 0, gorn-health, 80, 0)]

    ; Test a regular attack, where the attacker does damage to the target
    (let [kirk-attack 50
          gorn-defense 30
          kirk (assoc base-kirk :attack kirk-attack)
          gorn (assoc base-gorn :defense gorn-defense)
          expected-gorn (assoc gorn :health 80)
          attacked-gorn (entity/attack kirk gorn)]
      (is (= expected-gorn attacked-gorn)))

    ; Test an attack where defense exceeds attack
    (let [kirk-attack 20
          gorn-defense 40
          kirk (assoc base-kirk :attack kirk-attack)
          gorn (assoc base-gorn :defense gorn-defense)
          attacked-gorn (entity/attack kirk gorn)]
        ; Defense exceeded attack, so Gorn should take no damage
        (is (= gorn attacked-gorn)))))
