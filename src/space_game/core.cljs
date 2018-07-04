(ns space-game.core
  (:require [play-cljs.core :as p]
            [goog.events :as events]
            [space-game.grid :as grid])
  (:require-macros [space-game.music :refer [build-for-cljs]]))

(defonce game (p/create-game (.-innerWidth js/window) (.-innerHeight js/window)))
(defonce state (atom {}))

(def main-screen
  (reify p/Screen
    (on-show [this]
      (reset! state {:text-x 20 :text-y 30}))
    (on-hide [this])
    (on-render [this]
      (p/render game
                (grid/square-grid game 5 50 js/window.innerWidth js/window.innerHeight)))))

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

