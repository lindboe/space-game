(ns space-game.entity)

(defn create-entity
  "Return a new entity with the given name, position, health, and stats"
  [name x y health attack defense]
  {:name name
   :pos {:x x :y y}
   :health health
   :attack attack
   :defense defense})

(defn attack
  "Performs an attack by an attacker on a target, and returns the resulting
    target after stats have been modified
    
    Current algorithm is HP loss = Attacker's Attack - Defender's Defense"
  [attacker target]
  (update target :health - (- (:attack attacker) (:defense target))))