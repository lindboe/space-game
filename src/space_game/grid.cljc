(ns space-game.grid)

(defn shift-line-x
  "Return a new line ({:x1 x1 :y1 y1 :x2 x2 :y2 y2}) shifted up or down by a given x value."
  [line x-shift]
  (-> line
      (update :x1 + x-shift)
      (update :x2 + x-shift)))

(defn shift-line-y
  "Return a new line shifted left or right by a given y value."
  [line y-shift]
  (-> line
      (update :y1 + y-shift)
      (update :y2 + y-shift)))

(defn repeat-lines-x
  "Given an initial line, construct a list of that line plus a given number of repeats of that line, shifted x amount"
  [init-line num-squares x-shift]
  (let [num-reps (+ 1 num-squares)] ;; number of lines to generate = squares + 1
    (for [i (range num-reps)]
      (shift-line-x init-line (* i x-shift)))))

(defn repeat-lines-y
  "Given an initial line, construct a list of that line plus a given number of repeats of that line, shifted y amount"
  [init-line num-squares y-shift]
  (let [num-reps (+ 1 num-squares)] ;; number of lines to generate = squares + 1
    (for [i (range num-reps)]
      (shift-line-y init-line (* i y-shift)))))

(defn coordinates-to-line-element
  "A helper function to put a map of coordinates in a line display element."
  [coordinates]
  [:line coordinates])

(defn square-grid
  "Draw a square grid with given number of squares along a side, of the given
        square width."
  [game num-squares square-width window-width window-height]
  (let [grid-width (* num-squares square-width)
        width-center (/ window-width 2)
        height-center (/ window-height 2)
        half-line-width (/ grid-width 2)
        top-line-left-x (int (- width-center half-line-width))
        top-line-right-x (int (+ width-center half-line-width))
        left-line-top-y (int (- height-center half-line-width))
        left-line-bottom-y (int (+ height-center half-line-width))
        top-line {:x1 top-line-left-x :y1 left-line-top-y :x2 top-line-right-x :y2 left-line-top-y}
        left-line {:x1 top-line-left-x :y1 left-line-top-y :x2 top-line-left-x :y2 left-line-bottom-y}
        grid
        (map coordinates-to-line-element
             (concat (repeat-lines-x left-line num-squares square-width)
                     (repeat-lines-y top-line num-squares square-width)))]
    grid))
