(ns space-game.entity)

(defn create-entity
  "Return a new entity with the given name, position, health, and stats"
  [name x y health attack defense]
  {:name name
   :pos {:x x :y y}
   :health health
   :attack attack
   :defense defense})