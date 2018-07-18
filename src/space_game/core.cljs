(ns space-game.core
  (:require [play-cljs.core :as p]
            [goog.events :as events]
            [space-game.grid :as grid]
            [space-game.entity :as entity])
  (:require-macros [space-game.music :refer [build-for-cljs]]))

(defonce game (p/create-game (.-innerWidth js/window) (.-innerHeight js/window)))
(defonce state (atom {}))

(def main-screen
  (reify p/Screen
    (on-show [this]
      (let [base-state {:text-x 20 :text-y 30}
            entities [(entity/create-entity "James T. Kirk" 100 40 100 30 10)
                      (entity/create-entity "Gorn Captain" 500 40 100 30 10)]]
        (reset! state (assoc base-state :entities entities))))
    (on-hide [this])
    (on-render [this]
      (let [rendering (concat
                       (grid/square-grid game 5 50 js/window.innerWidth js/window.innerHeight)
                       (reduce concat
                               (map entity/render (:entities @state))))]
        (p/render game rendering)))))

(events/listen js/window "mousemove"
               (fn [event]
                 (swap! state assoc :text-x (.-clientX event) :text-y (.-clientY event))))

(events/listen js/window "resize"
               (fn [event]
                 (p/set-size game js/window.innerWidth js/window.innerHeight)))

(doto game
  (p/start)
  (p/set-screen main-screen))

; uncomment to generate a song and play it!

;(defonce audio (js/document.createElement "audio"))
;(set! (.-src audio) (build-for-cljs))
;(.play audio)

