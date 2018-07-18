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
  (let [attack (:attack attacker)
        defense (:defense target)
        hp-loss (max (- attack defense) 0)]
    (update target :health - hp-loss)))

(defn render
  "Returns the elements to render an entity.
  
  This is currently in a preliminary state. It will simply render a circle,
  the name of the entity, and the entity's current HP"
  [entity]
  (let [width 20
        height 20
        textSize 10
        x (:x (:pos entity))
        y (:y (:pos entity))
        textX (+ x width 5)
        healthTextY (+ y textSize)]
    [[:fill {:color "pink"} [:rect {:x x :y y :width width :height height}]]
     [:text {:value (:name entity) :x textX :y y :size textSize :font "Georgia" :style :normal}]
     [:text {:value (str (:health entity)) :x textX :y healthTextY :size textSize :font "Georgia" :style :normal}]]))

    