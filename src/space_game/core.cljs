(ns space-game.core
  (:require [play-cljs.core :as p]
            [goog.events :as events])
  (:require-macros [space-game.music :refer [build-for-cljs]]))

(defonce game (p/create-game (.-innerWidth js/window) (.-innerHeight js/window)))
(defonce state (atom {}))

(defn square-grid
  "Draw a square grid with given number of squares along a side, of the given
  square width."
  [game num-squares square-width]
  (let [window-width (p/get-width game)
        window-height (p/get-height game)
        grid-width (* num-squares square-width)
        width-center (/ window-width 2)
        height-center (/ window-height 2)
        half-line-width (/ grid-width 2)
        top-line-left-x (int (- width-center half-line-width))
        top-line-right-x (int (+ width-center half-line-width))
        left-line-top-y (int (- height-center half-line-width))
        left-line-bottom-y (int (+ height-center half-line-width))]
    [[:line {:x1 top-line-left-x, :y1 left-line-top-y, :x2 top-line-right-x, :y2 left-line-top-y}]
     [:line {:x1 top-line-left-x, :y1 left-line-top-y, :x2 top-line-left-x, :y2 left-line-bottom-y}]]))

(def main-screen
  (reify p/Screen
    (on-show [this]
      (reset! state {:text-x 20 :text-y 30}))
    (on-hide [this])
    (on-render [this]
      (p/render game
         (square-grid game 5 50)))))


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

