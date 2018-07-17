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

(defn grid-line-positions
  "Given a square width, number of squares along the x and y axes, and window
  width and window height, give coordinates for beginning of each line in the x
  and y directions.

  Example:

  {:x [50 100 150 200 250]
   :y [60 110 160 210 260]}

  or maybe

  {:x-along-top [50 100 150 200 250]
   :y-along-left [60 110 160 210 260]
   :x-along-bottom [250 300 350 400 450]
   :y-along-right [260 310 360 410 460]}
  "
  [square-width squares-x squares-y window-width window-height]
  )

;; maybe instead of referring to these as "coordinates" and "position" i should
;; refer to the internal coordinates as "relative" and the display coordinates
;; as "absolute"?
(defn top-left-position-of-coord
  [coordinates top-of-grid-pos left-of-grid-pos square-width]
  (let [x-pos (+ top-of-grid-pos (* (first coordinates) square-width))
        y-pos (+ left-of-grid-pos (* (second coordinates) square-width))]
    [x-pos y-pos]))

(defn another-way-to-generate-grid
  [square-width squares-x squares-y window-width window-height]
   (let [grid-width (* squares-x square-width)
         grid-height (* squares-y square-width)
         width-center (/ window-width 2)
         height-center (/ window-height 2)
         half-width (/ grid-width 2)
         half-height (/ grid-height 2)
         left-x (int (- width-center half-width))
         top-y (int (- height-center half-height))
         horizontal-lines [[left-x, top-y, (+ left-x grid-width), top-y]
                           [left-x, top-y, (+ left-x grid-width), (+ top-y (* 1 square-width))]
                           [left-x, top-y, (+ left-x grid-width), (+ top-y (* 2 square-width))]
                           ]
         ])
  )

;; based on the above hardcoded horizontal lines
(defn repeat-horizontal-lines
  [num-repeats distance top-pos left-pos width]
  )

(defn render-entity
  [grid-coordinates]
  [:ellipse {:x 1 :y 1 :width 50 :height 50}])
;; I think I want a function that returns the grid as positional data, eg left line here, next line here... or can tell me where each coordinate is {[0 0] {:x1 1 :y1 1}...}; just something that can let me know the top-left of each coordinate
